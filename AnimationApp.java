/*Group 26 CPSC 219 Project
* Effectively our game this contains information about shooting, and the Arraylists associated with everything including the getters and setters
 *  Intentions for next Version: To literally have them fire and deal damage against player lives. @Author Chad
 * 
 */
import java.util.ArrayList;
public class AnimationApp {
	//Initializing all of our instance variables.
	protected ArrayList<Platform> platforms;
	protected ArrayList<Enemy> enemy;
	protected ArrayList<Bullet> bullets;
	protected ArrayList<Bullet> enemyBullets;
	protected Avatar avatar;
	
	//Construct for AnimationApp
	public AnimationApp() {
		enemy = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		platforms = new ArrayList<Platform>();
		avatar = new Avatar(3,9,3);
	}
	
	//Copy Construct
	public AnimationApp(ArrayList<Platform> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar) {
		this.platforms = platforms;
		this.bullets = bullets;
		this.enemy = enemy;
		this.avatar = avatar;
		
	}

	//Method to allow enemy to shoot, Currently Broken
	public void enemyShoot() {
		for (Enemy enemy : enemy) {
				Bullet enemyShot = enemy.shoot();
				if (enemyShot!=null) {
					bullets.add(enemyShot);
				
			}
		}
	}

	//Method to allow user to shoot.
	public void avatarShoot() {
		Bullet playershot = avatar.shoot();
		if (playershot!= null) {
			bullets.add(playershot);
		}
		
	}

	//Method to Add Platforms to the ArrayList
	public void addPlatform(Platform p) {
		platforms.add(new Platform(p));
	}
	
	//Method to Add Enemies to the ArrayList
	public void addEnemy(Enemy enem) {
		enemy.add(new Enemy(enem));
	}
	
	//Getter to get Avatar.
	protected Avatar getAvatar() {
		return avatar;
	}
	//Getter to get new Avatar (USE IS LATER ON WHEN THE AVATAR DIES AND RESPAWNS)
	public Avatar getNewAvatar() {
		return new Avatar(avatar);
	}
	//Setter to set avatar and their variables.
	protected void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	//Getter to get Platforms to board.
	protected ArrayList<Platform> getPlatforms(){
		return platforms;
	}
	//Getter to get new platforms (USE IS LATER ON WHEN PLATFORMS MOVE AND CYCLE)
	public ArrayList<Platform> getNewPlatforms() {
		ArrayList<Platform> returnList = new ArrayList<Platform>();

		for (Platform pl : platforms) {
			returnList.add(new Platform(pl));
		}
		return returnList;
	}
	//Getter to get bullets. Possible Issue: Not Getting Enemy Bullets
	protected ArrayList<Bullet> getBullets(){
		return bullets;
	}
	//Getter to add new bullets, to make multiple versions of them
	public ArrayList<Bullet> getNewBullets() {
		ArrayList<Bullet> fire = new ArrayList<Bullet>();
		for (Bullet bullet : bullets) {
			fire.add(new Bullet((Bullet) bullet));
		}
		return fire;
	}
	//Getter to get Enemies.
	public ArrayList<Enemy> getEnemies() {
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		for (Enemy enem : enemy) {
			enemyList.add(new Enemy(enem));
		}
		return enemyList;
		
	}
	
}
