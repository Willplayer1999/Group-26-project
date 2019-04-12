package Game;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.List;

import Entities.*;
import Bullet.*;


/**
* Group 26 CPSC 219 Project TUT 06
* Abstract class that is extended by TextGame that builds all of the main mechanics for both versions of the game. It has all the constructors that creates the game object, it has the respawn mechanic,
* it has most of the shooting mechanics, all types of collision and all types of movement.
*/
public abstract class Game
{
	private ArrayList<Platforms> platforms;
	private ArrayList<Enemy> enemy;
	private ArrayList<Bullet> bullets;
	private static Avatar avatar;
	private static int level;
	private boolean alive;
	private static int score;
	//GUI
	private static int playerScore = 0;
	int changeDirection = 0;
	int changeDirectionReverse = 1;
	int changeDirectionGUI = 0;
	int changeDirectionReverseGUI = 1;
	//Boolean values to determine if enemies have been hit by avatar bullet or not
	static boolean enemyOne = false;
	static boolean enemyTwo = false;
	static boolean enemyThree = false;
	static boolean enemyFour = false;
	static boolean enemyFive = false;
	static boolean enemySix = false;
	static boolean enemySeven = false;
	static boolean enemyEight = false;

	private static boolean collision;
	public static int health = 10;

	//GUI
	/**
	* Gets the Avatar's number of health.
	* @return An integer representing the number of health.
	*/
	public static int getHealth()
	{
		return health;
	}

	/**
	* Sets the Avatar's health to a given amount.
	* @param healtha The number of healths the Avatar's health will be set to
	*/
	public static void setHealth(int healtha)
	{
		health = healtha;
	}

	/**
	* Gets the state of collision.
	* @return A boolean that represents whether collision is true or false.
	*/
	public static boolean getCollision()
	{
		return collision;
	}

	/**
	* Sets the state of collision.
	* @param b The boolean state to which collision wil be set to.
	*/
	public static void setCollision(boolean b)
	{
		collision = b;
	}

	/**
	* Get the user's score
	* @return An integer that represents the user's score.
	*/
	public static int getPlayerScore()
	{
		return playerScore;
	}

	/**
	* Sets the user's score to a given value.
	* @param score The value to which the score will be set to.
	*/
	public static void setPlayerScore(int score)
	{
		Game.playerScore = score;
	}
	/**
	* Sets the state of whether the enemy has been hit or not.
	* @param state The boolean value to which the enemie's collision will be set to.
	*/
	public static void setEnemyOne(boolean state)
	{
		enemyOne = state;
	}

	/**
	* Sets the state of whether the enemy has been hit or not.
	* @param state The boolean value to which the enemie's collision will be set to.
	*/
	public static void setEnemyTwo(boolean state)
	{
		enemyTwo = state;
	}

	/**
	* Sets the state of whether the enemy has been hit or not.
	* @param state The boolean value to which the enemie's collision will be set to.
	*/
	public static void setEnemyThree(boolean state)
	{
		enemyThree = state;
	}


	/**
	* Sets the state of whether the enemy has been hit or not.
	* @param state The boolean value to which the enemie's collision will be set to.
	*/
	public static void setEnemyFour(boolean state)
	{
		enemyFour = state;
	}
	
	/**
	* Sets the state of whether the enemy has been hit or not.
	* @param state The boolean value to which the enemie's collision will be set to.
	*/
	public static void setEnemyFive(boolean state)
	{
		enemyFive = state;
	}

	/**
	* Creates a game with an Avatar, a list of platforms, a list of enemies and a list of bullets.
	* @param platforms The list of platforms provided to put in the game.
	* @param bullets The list of bullets provided to put in the game.
	* @param enemy The list of enemies provided to put in the game.
	* @param avatar The Avatar given to put in the game.
	*/
	public Game(ArrayList<Platforms> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar)
	{
		this.platforms = platforms;
		this.bullets = bullets;
		this.enemy = enemy;
		Game.avatar = avatar;
	}
	/**
	* Creates an empty Game and sets the Avatar to dead.
	*/
	public Game()
	{
		enemy = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		platforms = new ArrayList<Platforms>();
		setAlive(false);
		level = 1;
		score = 0;
	}

	/**
	* Resets the Avatar's position to the initial position and removes a life.
	* @param x The x coordinate of the initial position.
	* @param y The y coordinate of the initial position.
	*/
	public void respawn(int x, int y)
	{
		avatar.setX(x);
		avatar.setY(y);;
		avatar.setLives(Avatar.getLives()-1);
	}

	/**
	* Sets the level to a given value.
	* @param current The value to which the level will be set.
	*/
	public static void setLevel(int current)
	{
		Game.level = current;
	}

	/**
	* Sets the Avatar to be a different given Avatar.
	* @param avatar The Avatar that the current one will be changed to.
	*/
	protected void setAvatar(Avatar avatar)
	{
		Game.avatar = avatar;
	}

	/**
	* Sets the Avatar to a new one with the same parameters by using the copy constructor.
	* @param avatar The Avatar whose parameters will be copied.
	*/
	protected void setNewAvatar(Avatar avatar)
	{
		Game.avatar = new Avatar(avatar);
	}

	/**
	* Sets score to a given value.
	* @param s The value to which the score will be set.
	*/
	protected void setScore(int s)
	{
		Game.score = s;
	}

	/**
	* Adds a given platform to the platforms list.
	* @param p The platform to be added.
	*/
	public void addPlatform(Platforms p)
	{
		platforms.add(new Platforms(p));
	}

	/**
	* Adds a given enemy to the enemies list.
	* @param enem The enemy to be added.
	*/
	public void addEnemy(Enemy enem)
	{
		enemy.add(new Enemy(enem));
	}

	/**
	* Gets the Avatar.
	* @return An Avatar.
	*/
	public static Avatar getAvatar()
	{
		return avatar;
	}


	/**
	* Uses the copy constructor to get a new Avatar with the same parameters as a previous one.
	* @return A new Avatar with similar parameters as aprevious one.
	*/
	public Avatar getNewAvatar()
	{
		return new Avatar(avatar);
	}

	/**
	* Gets a list of platforms.
	* @return The array list of platforns.
	*/
	public ArrayList<Platforms> getPlatforms()
	{
		return platforms;
	}

	/**
	* For every platform in the game's platform list add a new platform with same parameters, using the copy constructor, to a new list of platforms.
	* @return An Array List of copied platforms.
	*/
	public ArrayList<Platforms> getNewPlatforms()
	{
		ArrayList<Platforms> returnList = new ArrayList<Platforms>();
		for (Platforms pl : platforms)
		{
			if (pl instanceof Platforms)
			{
				returnList.add(new Platforms((Platforms) pl));
			}
		}
		return returnList;
	}
	/**
	* Gets the list of bullets.
	* @return The array list of bullets.
	*/
	protected ArrayList<Bullet> getBullets()
	{
		return bullets;
	}

	/**
	* For every bullet in the game's bullet list add a new bullet with same parameters, using the copy constructor, to a new list of bullets.
	* @return An Array List of copied bullets.
	*/
	public ArrayList<Bullet> getNewBullets()
	{
		ArrayList<Bullet> fire = new ArrayList<Bullet>();
		for (Bullet bullet : bullets)
		{
			if (bullet instanceof AvatarBullet)
			{
				fire.add(new AvatarBullet((AvatarBullet) bullet));
			}
			else if (bullet instanceof EnemyBullet)
			{
				fire.add(new EnemyBullet((EnemyBullet) bullet));
			}
		}
		return fire;
	}

	/**
	* Gets the list of enemies.
	* @return The array list of enemies.
	*/
	public ArrayList<Enemy> getEnemies()
	{
		return enemy;
	}

	/**
	* For every enemy in the game's enemy list add a new enemy with same parameters, using the copy constructor, to a new list of enemies.
	* @return An Array List of copied enemies.
	*/
	public ArrayList<Enemy> getNewEnemies()
	{
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		for (Enemy enem : enemy)
		{
			enemyList.add(new Enemy(enem));
		}
		return enemyList;
	}

	/**
	* Gets the game's score.
	* @return An int that represents the score value.
	*/
	public static int getScore()
	{
		return score;
	}

	/**
	* Gets the level the user is currently on.
	* @return An int that represents the level.
	*/
	public static int getLevel()
	{
		return level;
	}


	/**
	* For every enemy there is, if it has fired, call shoot on it and if the bullet returned is not null, then add it to the list.
	*/
	public void enemyShoot()
	{
		for (Enemy enemies : enemy)
		{
			if (enemies.getHasFired())
			{
				Bullet enemyPew = enemies.shoot();
				if (enemyPew != null)
				{
					bullets.add(enemyPew);
				}
			}
		}
	}

	/**
	* Call shoot on Avatar and if the bullet returned is not null, then add it to the list.
	*/
	public void avatarShoot()
	{
		Bullet avatarPew = avatar.shoot();
		if (avatarPew!= null)
		{
			bullets.add(avatarPew);
		}
	}

	/**
	* For every bullet, if calling move on it returns false, then remove that bullet from the list and go back one index on the list.
	*/
	public void move()
	{
		for (int a = 0; a < bullets.size(); a++)
		{
			Bullet bullet = bullets.get(a);
			if (!bullet.move())
			{
				bullets.remove(a);
				a--;
			}
		}
	}

	/**
	* For every platform, if their index is 0, 3, 6, or 9, if its x is 6, change direction is 1, and if their x is 0, change direction is 0, then calls changeDir with the platform.
	* If their index is 1, 4, 7, or 10, if its x is 7, change direction is 1, and if their x is 0, change direction is 0, then calls changeDir with the platform.
	* If their index is 2, 5, 8, or 11, if its x is 8, change direction is 1, and if their x is 0, change direction is 0, then calls changeDir with the platform.
	* If their index is 12, 15, 18, or 21, if its x is 6, change direction is 1, and if their x is 0, change direction is 0, then calls changeDirReverse with the platform.
	* If their index is 13, 16, 19, or 22, if its x is 7, change direction is 1, and if their x is 0, change direction is 0, then calls changeDirReverse with the platform.
	* If their index is 14, 17, 20, or 23, if its x is 8, change direction is 1, and if their x is 0, change direction is 0, then calls changeDirReverse with the platform.
	*/
	public void moveplatform()
	{
		for (Platforms platform : platforms)
		{
			if (platforms.indexOf(platform)==0 || platforms.indexOf(platform)==3 || platforms.indexOf(platform)==6 || platforms.indexOf(platform)==9)
			{
				if (platform.getXCord() == 6 )
				{
					changeDirection = 1;
				}
				if (platform.getXCord() == 0)
				{
					changeDirection = 0;
				}
				changeDir(platform);
			}
			if (platforms.indexOf(platform)==1 ||platforms.indexOf(platform)==4 ||platforms.indexOf(platform)== 7 || platforms.indexOf(platform)== 10)
			{
				if (platform.getXCord() == 7 )
				{
					changeDirection = 1;
				}
				if (platform.getXCord() == 1)
				{
					changeDirection = 0;
				}
				changeDir(platform);
			}
			if (platforms.indexOf(platform)==2 || platforms.indexOf(platform)==5 || platforms.indexOf(platform)== 8 || platforms.indexOf(platform)== 11)
			{
				if (platform.getXCord() == 8 )
				{
					changeDirection = 1;
				}
				if (platform.getXCord() == 2)
				{
					changeDirection = 0;
				}
				changeDir(platform);
			}
			//Other
			if (platforms.indexOf(platform)==12 || platforms.indexOf(platform)==15 || platforms.indexOf(platform)==18 || platforms.indexOf(platform)==21)
			{
				if (platform.getXCord() == 6)
				{
					changeDirectionReverse = 1;
				}
				if (platform.getXCord() == 0)
				{
					changeDirectionReverse = 0;
				}
				changeDirReverse(platform);
			}
			if (platforms.indexOf(platform)==13 || platforms.indexOf(platform)==16 || platforms.indexOf(platform)==19 || platforms.indexOf(platform)==22)
			{
				if (platform.getXCord() == 7)
				{
					changeDirectionReverse = 1;
				}
				if (platform.getXCord() == 1)
				{
					changeDirectionReverse = 0;
				}
				changeDirReverse(platform);
			}
			if (platforms.indexOf(platform)==14 || platforms.indexOf(platform)==17 || platforms.indexOf(platform)==20 || platforms.indexOf(platform)==23)
			{
				if (platform.getXCord() == 8)
				{
					changeDirectionReverse = 1;
				}
				if (platform.getXCord() == 2)
				{
					changeDirectionReverse = 0;
				}
				changeDirReverse(platform);
			}
		}
	}

	/**
	* If changeDir is 0, add to the x coordinate of the platform, and if changeDir is 1, subtract from the x coordinate of the platform.
	* @param platform The platform which wil have its movement direction changed.
	*/
	public void changeDir(Platforms platform)
	{
		if (changeDirection==0)
		{
			//TextBasedPart
			platform.setXCord(platform.getXCord()+1);
		}
		if(changeDirection==1)
		{
			platform.setXCord(platform.getXCord()-1);
		}
	}

	/**
	* If changeDirReverse is 0, add to the x coordinate of the platform, and if changeDirReverse is 1, subtract from the x coordinate of the platform.
	* @param platform The platform which wil have its movement direction changed.
	*/
	public void changeDirReverse(Platforms platform)
	{
		if (changeDirectionReverse==0)
		{
			platform.setXCord(platform.getXCord()+1);
		}
		if(changeDirectionReverse==1)
		{
			platform.setXCord(platform.getXCord()-1);
		}
	}

	/**
	* Checks whether or not the player has any more lives
	* @return A boolean that represents whether the Avatar has no lives or not.
	*/
	public boolean noLives()
	{
		return Avatar.getLives() <= 0;
	}

	/**
	* For every bullet, if an AvatarBullet hits an enemy, remove both and increase score. If an EnemyBullet hits the Avatar, then remove the bullet and reduce the Avatar's number of lives.
	* For every platform, if Avatar is not colliding with it, Avatar is set to dead.
	*/
	public void checkCollision()
	{
		//for Bullets against Avatar and Enemy
		for(int bulletIndex = 0; bulletIndex < bullets.size(); bulletIndex++)
		{
			Bullet b = bullets.get(bulletIndex);
			if (b instanceof AvatarBullet)
			{
				@SuppressWarnings("unused")
				boolean collided = false;
				for (int enemyIndex = 0; enemyIndex < enemy.size(); enemyIndex++)
				{
					Enemy e = enemy.get(enemyIndex);
					if(((AvatarBullet) b).collidedWith(e))
					{
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
			else if (b instanceof EnemyBullet)
			{
				if (((EnemyBullet) b).collidedWith(avatar))
				{
					bullets.remove(bulletIndex);
					bulletIndex--;
					int health = Avatar.getLives()-1;
					avatar.setLives(health);
				}
			}
		}
		for(int platformIndex = 0; platformIndex < platforms.size(); platformIndex++)
		{
			Platforms p = platforms.get(platformIndex);
			if (p instanceof Platforms)
			{
				//boolean collided = false;
				//Avatar a = avatar.get(enemyIndex);
				if(((Platforms) p).collidedWith(avatar))
				{
					setAlive(true);
					break;
				}
				else
				{
					setAlive(false);
					break;
				}
			}
		}
	}

	/**
	* Checks if there was a collision between EnemyBullet and Avatar.
	* @param eB A list of drawn enemy bullets.
	* @param a A drawn Avatar.
	* @param sP A list of drawn static platforms.
	* @param mP A list of drawn moving platforms.
	* @return A boolean that represents whether or not the enemy bullet has collided with Avatar.
	*/
	public static boolean checkEnemyBulletCollision(List<Node> eB, Node a, List<Node> sP, List<Node> mP)
	{
		for(Node enemyBullet : eB)
		{
			for (Node staticPlat : sP)
			{
				for (Node movingPlat : mP)
				{
					if (staticPlat.getBoundsInParent().intersects(a.getBoundsInParent()) && enemyBullet.getBoundsInParent().intersects(a.getBoundsInParent()) )
					{
						return false;
					}
					if (enemyBullet.getBoundsInParent().intersects(a.getBoundsInParent()) && movingPlat.getBoundsInParent().intersects(a.getBoundsInParent()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	//Check collision between Avatar bullet and Enemy
	/**
	* Check if there was a collision between an AvatarBullet and an Enemy and handles accordingly.
	* @param aB A list of drawn AvatarBullets.
	* @param fE A drawn Enemy.
	* @param sE A drawn Enemy.
	* @param tE A drawn Enemy.
	* @param foE A drawn Enemy.
	* @param p The panel in which everything is drawn.
	*/
	public static void checkAvatarBulletCollision(List<Node> aB, Node fE, Node sE, Node tE, Node foE, Node fiE, Node siE, Node seE, Node eiE, Node niE, Node tenE, Node eleE, Node tweE, Node thE, Node fouE, Node fifE, Pane p)
	{
		for(Node avatarBullet : aB)
		{
			if(fE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				//remove enemy
				fE.setTranslateX(-200);
				p.getChildren().remove(fE);
				setEnemyOne(true);
				playerScore++;
			}
			if(sE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				sE.setTranslateX(-200);
				p.getChildren().remove(sE);
				setEnemyTwo(true);
				playerScore++;
			}
			if(tE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				tE.setTranslateX(-200);
				p.getChildren().remove(tE);
				setEnemyThree(true);
				playerScore++;
			}
			if(foE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				foE.setTranslateX(-200);
				p.getChildren().remove(foE);
				setEnemyFour(true);
				playerScore++;
			}
			if(fiE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				fiE.setTranslateX(-200);
				p.getChildren().remove(fiE);
				setEnemyFive(true);
				playerScore++;
			}
			if(siE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				siE.setTranslateX(-200);
				p.getChildren().remove(siE);
				setEnemyFive(true);
				playerScore++;
			}
			if(seE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				seE.setTranslateX(-200);
				p.getChildren().remove(seE);
				setEnemyFive(true);
				playerScore++;
			}
			if(eiE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				eiE.setTranslateX(-200);
				p.getChildren().remove(eiE);
				setEnemyFive(true);
				playerScore++;
			}
			if(niE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				niE.setTranslateX(-200);
				p.getChildren().remove(niE);
				setEnemyFive(true);
				playerScore++;
			}
			if(tenE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				tenE.setTranslateX(-200);
				p.getChildren().remove(tenE);
				setEnemyFive(true);
				playerScore++;
			}
			if(eleE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				eleE.setTranslateX(-200);
				p.getChildren().remove(eleE);
				setEnemyFive(true);
				playerScore++;
			}
			if(tweE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				tweE.setTranslateX(-200);
				p.getChildren().remove(tweE);
				setEnemyFive(true);
				playerScore++;
			}
			if(thE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				thE.setTranslateX(-200);
				p.getChildren().remove(thE);
				setEnemyFive(true);
				playerScore++;
			}
			if(fouE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				fouE.setTranslateX(-200);
				p.getChildren().remove(fouE);
				setEnemyFive(true);
				playerScore++;
			}
			if(fifE.getBoundsInParent().intersects(avatarBullet.getBoundsInParent()))
			{
				fifE.setTranslateX(-200);
				p.getChildren().remove(fifE);
				setEnemyFive(true);
				playerScore++;
			}
		}
	}


	/**
	* Chacks if there is a collision between Avatar and Platform.
	* @param pl A list of drawn platforms.
	* @param a A drawn Avatar.
	* @param fE A drawn Enemy.
	* @param sE A drawn Enemy.
	* @param tE A drawn Enemy.
	* @param foE A drawn Enemy.
	* @param sP A list of drawn static platforms.
	* @return A boolean that represents whether or not Avatar is on platform.
	*/
	public static boolean checkCollisionGUI(List<Node> pl, Node a, Node fE, Node sE, Node tE, Node foE, List<Node> sP)
	{
		for (Node platform : pl)
		{
			if (platform.getBoundsInParent().intersects(a.getBoundsInParent()))
			{
				platform.setOpacity(0.5);
				a.setTranslateX(platform.getTranslateX()+30);
				a.setTranslateY(platform.getTranslateY()+29);
			}
			//If Platform and Avatar don't collide, the opacity is full
			else
			{
				platform.setOpacity(1.0);
			}
			if (platform.getBoundsInParent().intersects(fE.getBoundsInParent()))
			{
				platform.setOpacity(0.5);
				fE.setTranslateX(platform.getTranslateX()+30);
				fE.setTranslateY(platform.getTranslateY()+29);
			}
			if (platform.getBoundsInParent().intersects(sE.getBoundsInParent()))
			{
				platform.setOpacity(0.5);
				sE.setTranslateX(platform.getTranslateX()+30);
				sE.setTranslateY(platform.getTranslateY()+29);
			}
			if (platform.getBoundsInParent().intersects(tE.getBoundsInParent()))
			{
				platform.setOpacity(0.5);
				tE.setTranslateX(platform.getTranslateX()+30);
				tE.setTranslateY(platform.getTranslateY()+29);
			}
			if (platform.getBoundsInParent().intersects(foE.getBoundsInParent()))
			{
				platform.setOpacity(0.5);
				foE.setTranslateX(platform.getTranslateX()+30);
				foE.setTranslateY(platform.getTranslateY()+29);
			}
		}
		for (Node staticPlat : sP)
		{
			if (staticPlat.getBoundsInParent().intersects(a.getBoundsInParent()))
			{
				staticPlat.setOpacity(0.5);
				a.setTranslateX(staticPlat.getTranslateX()+30);
				a.setTranslateY(staticPlat.getTranslateY()+29);
				setCollision(true);
			}
			else
			{
				staticPlat.setOpacity(1.0);
			}
		}
		//IF AVATAR REACHES TOP, TIMER STOPS
		if (getHealth()==0 || Game.getPlayerScore()== 17)
		{
			setCollision(false);
		}
		return getCollision();
	}

	//CHAD ADDED THIS SO WE RESPAWN WHEN FALLING OFF PLATFORM
	/**
	* Respawns Avatar when it moves off of platform.
	* @return A boolean that represents whether or not the Avatar is on the platform.
	*/
	public boolean overlapsWith()
	{
		boolean alive = false;
		for (Platforms platform : platforms)
		{
			if (getAvatar().getX() == platform.getX() && getAvatar().getY() == platform.getY())
			{
				alive  = true;
			}
		}
		return alive;
	}
	
	/**
	* Gets the state of whether Avatar is alive or not.
	* @return A boolean that represents whether the Avatar is alive or not.
	*/
	public boolean isAlive() {
		return alive;
	}

	/**
	* Sets the state of alive to a given state.
	* @param alive The state to which alive will be set to.
	*/
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}