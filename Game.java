import java.util.ArrayList;
import java.util.Scanner;

import com.sun.media.jfxmediaimpl.platform.Platform;
public abstract class Game {
		private ArrayList<Platforms> platforms;
		private ArrayList<Enemy> enemy;
		private ArrayList<Bullet> bullets;
		private static Avatar avatar;
		private static int level = 1;
		private boolean alive;
		private static int score;
		//
		int changeDirection = 0;
		int changeDirectionReverse = 1;
		//

		private boolean moveLeft;

		public Game(ArrayList<Platforms> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar) {
			this.platforms = platforms;
			this.bullets = bullets;
			this.enemy = enemy;
			Game.avatar = avatar;
			
		}
		
		public Game() {
			enemy = new ArrayList<Enemy>();
			bullets = new ArrayList<Bullet>();
			platforms = new ArrayList<Platforms>();
			alive = false;
			level = 1;
			score = 0;
			
		}
		
		public void respawn(int x, int y) {
			avatar.setX(x);
			avatar.setY(y);;
			avatar.setLives(Avatar.getLives()-1);
			
		}
		

		public void setLevel(int current) {
			Game.level = current;
		}
		protected void setAvatar(Avatar avatar) {
			Game.avatar = avatar;
		}
		protected void setNewAvatar(Avatar avatar) {
			Game.avatar = new Avatar(avatar);
		}
		protected void setScore(int s) {
			Game.score = s;
		}

		public void addPlatform(Platforms p) {
			platforms.add(new Platforms(p));
		}
		
		
		public void addEnemy(Enemy enem) {
			enemy.add(new Enemy(enem));
		}
		
		public static Avatar getAvatar() {
			return avatar;
		}
		public Avatar getNewAvatar() {
			return new Avatar(avatar);
		}

		public ArrayList<Platforms> getPlatforms(){
			return platforms;
		}
		public ArrayList<Platforms> getNewPlatforms() {
			ArrayList<Platforms> returnList = new ArrayList<Platforms>();
			for (Platforms pl : platforms) {
				if (pl instanceof MovingPlatform) {
					returnList.add(new MovingPlatform((MovingPlatform) pl));
				}
			}
			return returnList;
		}
		
		protected ArrayList<Bullet> getBullets(){
			return bullets;
		}
		
		public ArrayList<Bullet> getNewBullets() {
			ArrayList<Bullet> fire = new ArrayList<Bullet>();
			for (Bullet bullet : bullets) {
				
				if (bullet instanceof AvatarBullet) {
					fire.add(new AvatarBullet((AvatarBullet) bullet));
					
				}
				
				else if (bullet instanceof EnemyBullet) {
					fire.add(new EnemyBullet((EnemyBullet) bullet));
					
				}
				
			}
			return fire;
		}
		public ArrayList<Enemy> getEnemies() {
			return enemy;
		}
		
		public ArrayList<Enemy> getNewEnemies() {
			ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
			for (Enemy enem : enemy) {
				
				enemyList.add(new Enemy(enem));
			}
			return enemyList;
			
		}
		public static int getScore() {
			return score;
		}
		public static int getLevel() {
			return level;
		}
		public void enemyShoot() {
			for (Enemy enemies : enemy) {
				if (enemies.getHasFired()) {
					Bullet enemyPew = enemies.shoot();
					if (enemyPew != null) {
						bullets.add(enemyPew);
					}
				}
			}
		}
		public void avatarShoot() {
			Bullet avatarPew = avatar.shoot();
			
			if (avatarPew!= null) {
				bullets.add(avatarPew);
			}
		}
			
		
	public void move() {
		for (int a = 0; a < bullets.size(); a++) {
			Bullet bullet = bullets.get(a);
			if (!bullet.move()) {
				bullets.remove(a);
				a--;
			}
		}
	}
	public void moveplatform() {
		for (Platforms platform : platforms) {
			if (platforms.indexOf(platform)==0 || platforms.indexOf(platform)==6 || platforms.indexOf(platform)==12 || platforms.indexOf(platform)==18) {
	        	if (platform.getXCord() == 6 ) {
	        		changeDirection = 1;
	        	}
	        	if (platform.getXCord() == 0) {
	        		changeDirection = 0;
	        	}
	        	if (changeDirection==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirection==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	}
        	if (platforms.indexOf(platform)==1 ||platforms.indexOf(platform)==7 ||platforms.indexOf(platform)== 13 || platforms.indexOf(platform)== 19) {
	        	if (platform.getXCord() == 7 ) {
	        		changeDirection = 1;
	        	}
	        	if (platform.getXCord() == 1) {
	        		changeDirection = 0;
	        	}
	        	if (changeDirection==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirection==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	}
        	if (platforms.indexOf(platform)==2 || platforms.indexOf(platform)==8 || platforms.indexOf(platform)== 14 || platforms.indexOf(platform)== 20) {
	        	if (platform.getXCord() == 8 ) {
	        		changeDirection = 1;
	        	}
	        	if (platform.getXCord() == 2) {
	        		changeDirection = 0;
	        	}
	        	if (changeDirection==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirection==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	}
        	//Other
        	if (platforms.indexOf(platform)==3 || platforms.indexOf(platform)==9 || platforms.indexOf(platform)==15 || platforms.indexOf(platform)==21) { 
	        	if (platform.getXCord() == 6) {
	        		changeDirectionReverse = 1;
	        	}
	        	if (platform.getXCord() == 0) {
	        		changeDirectionReverse = 0;
	        	}
	        	if (changeDirectionReverse==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirectionReverse==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	}
        	if (platforms.indexOf(platform)==4 || platforms.indexOf(platform)==10 || platforms.indexOf(platform)==16 || platforms.indexOf(platform)==22) { 
	        	if (platform.getXCord() == 7) {
	        		changeDirectionReverse = 1;
	        	}
	        	if (platform.getXCord() == 1) {
	        		changeDirectionReverse = 0;
	        	}
	        	if (changeDirectionReverse==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirectionReverse==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	}
        	if (platforms.indexOf(platform)==5 || platforms.indexOf(platform)==11 || platforms.indexOf(platform)==17 || platforms.indexOf(platform)==23) { 
	        	if (platform.getXCord() == 8) {
	        		changeDirectionReverse = 1;
	        	}
	        	if (platform.getXCord() == 2) {
	        		changeDirectionReverse = 0;
	        	}
	        	if (changeDirectionReverse==0) {
	            	platform.setXCord(platform.getXCord()+1);
	        	}
	        	if(changeDirectionReverse==1) {
	        		platform.setXCord(platform.getXCord()-1);
	        	}
        	} 
	}	
		
		/////////////////////////////////////////////////////////////////////////////
		/*
		for (int b = 0; b < platforms.size(); b++) {
			if (platforms.get(b).getX() == 4 && platforms.get(b).getY()==7) {
				platforms.get(b).setX(platforms.get(b).getX()+1);
			}			
		}
		*/
		
		/*
		//An attempt at writing platform
		for (int b = 0; b < platforms.size(); b++) {
			Platforms platform = platforms.get(b);
			if (platform.getX() >= 10) {
				platform.setX(platform.getX()-1);
			}else if (platform.getX() <= 0) {
				platform.setX(platform.getX()+1);
			}
			
		}
		*/
		
		}
	public boolean noLives() {
		return Avatar.getLives() <= 0;
	}
	public void checkCollision() {
	//for Bullets against Avatar and Enemy 
		for(int bulletIndex = 0; bulletIndex < bullets.size(); bulletIndex++) {
			Bullet b = bullets.get(bulletIndex);
			
			if (b instanceof AvatarBullet) {
				boolean collided = false;
				for (int enemyIndex = 0; enemyIndex < enemy.size(); enemyIndex++) {
					Enemy e = enemy.get(enemyIndex);

					if(((AvatarBullet) b).collidedWith(e)){
						bullets.remove(bulletIndex);
						bulletIndex--;
						enemy.remove(enemyIndex);
						enemyIndex --;
						collided = true;
						int score = getScore()+1;
						setScore(score);
					}
				}
			}
			else if (b instanceof EnemyBullet) {
				if (((EnemyBullet) b).collidedWith(avatar)) {
					bullets.remove(bulletIndex);
					bulletIndex--;
					int health = Avatar.getLives()-1;
					avatar.setLives(health);
				}
			}	
		}
		for(int platformIndex = 0; platformIndex < platforms.size(); platformIndex++) {
			Platforms p = platforms.get(platformIndex);
			if (p instanceof Platforms) {
				//boolean collided = false;	
				//Avatar a = avatar.get(enemyIndex);
				if(((Platforms) p).collidedWith(avatar)){
					alive = true;
					break;
					//System.out.println("Alive");
				}
				else {
					alive = false;
					break;
					//System.out.println("Dead");
				}
			}
		}
	}
	//CHAD ADDED THIS SO WE RESPAWN WHEN FALLING OFF PLATFORM
	public boolean overlapsWith() {
		boolean alive = false;
		for (Platforms platform : platforms) {
			if (getAvatar().getX() == platform.getX() && getAvatar().getY() == platform.getY()) {
				alive  = true;
			}
		}
		return alive;
	}
/*	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
			System.out.print("Type T to play the Command Prompt version, Or press G to play GUI");
			String choice = in.next();
			if (choice == "T" || choice == "t") {
				boolean running = true;
				
				while (running) {
					TextGame text = new TextGame();
				System.out.println("WELCOME TO GALLAGER VERSION TEXT USE WASD TO MOVE, KILL ALL THE TARGETS TO PROGRESS");
				text.run();
				//something something if they want to play again
				}
			}
			else if (choice == "G" || choice == "g") {
				boolean running = true;
				while(running) {
					GUI gui = new GUI();
					System.out.println("WELCOME TO GALLAGER VERSION GUI, USE WASD TO MOVE, KILL ALL TARGETS TO PROGRESS");
					gui.run();
				}
			}else {
				System.out.println("Incorrect Input, please initialize Game and Try again");
			}
	}
	*/
}




