/*Group 26 CPSC 219 Project
 * This class is for the enemies you will face and their associated positions and statistics.
 * Current Issue: They don't fire, Not sure what to do about this.
 * Intentions for next Version: To literally have them fire and deal damage against player lives.
 * 
 */

import java.util.Random;
public class Enemy {
	// Instance Variables
	private static final String enemySymbol = "X";
	//private Image image;
	private int enemyHealth; 
	// location of enemy
	private int x;
	private int y;
	// max X and max Y enemy can go
	private int maxY;
	private int maxX;

	private boolean hasFired = true;
	
	// Constructors
	private Enemy(int x, int y, int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.hasFired = true;
		this.maxY = maxY;
		this.maxX = maxX;

	}

	public Enemy(int i, int j) {
		this.x = i;
		this.y = j;
	}
	public Enemy() {
		
	}

	// Copy Constructor
	public Enemy(Enemy e) {
		this(e.getX(), e.getY(), e.getMaxX(), e.getMaxY());
		this.hasFired = e.getHasFired();
	}

	// Getter Methodv (WILL USE LATER FOR GUI)
	/** public getEnemyName(){}
		public getEnemyImage(){}
	*/
	//Get enemy health, Currently enemyHealth is at 1hp
	public int getHealth(){
		return enemyHealth;
	}
	//Get enemy X
	public int getX() {
		return x;
	}
	//Get Enemy y
	public int getY() {
		return y;
	}
	//Get Max Y for position reference
	public int getMaxY() {
		return maxY;
	}
	//get Max X for position Reference.
	public int getMaxX() {
		return maxX;
	}
	//Getter to see if enemy has fired (Anything to do with firing on enemy is currently bugged.)
	public boolean getHasFired(){
		return hasFired;
	}
	
	// Setter methods
	//Set for enemy health (Enemy health at the moment is a standard 1
	public void setEnemyHealth(int enemyHealth){
		this.enemyHealth = enemyHealth;
	}	
	//Set for Variable X Position
	public void setX(int x) {
		if (x < maxX && x >= 0) {
			this.x = x;
		} else if (x < 0) {
			this.x = 0;
		} else if (x >= maxX) {
			this.x = maxX - 1;
		}
	}
	//Set for Variable Y Position
	public void setY(int y) {
		if (y < maxY && y >= 0) {
			this.y = y;
		} else if (y < 0) {
			this.y = 0;
		} else if (y >= maxY) {
			this.y = maxY - 1;
		}
	}
	//setter for Max X
	public void setMaxX(){
		maxX = TextBase.COLUMN;
	}
	//setter for Max Y
	public void setMaxY(){
		maxY = TextBase.ROW;
	}
	//Setter for shooting
	public void setHasFired(boolean shot){
		this.hasFired = shot;
	}
	//Randomizer for the enemy to see if HasFired gets switched to true.
	//(Anything to do with firing on enemy is currently bugged.)
	public void fire() {
		Random rand = new Random();
		int n = rand.nextInt(50);
		if (n % 2 == 0) {
			hasFired = false;
		}else {
			hasFired = true;
		}
			
			
	}
	//Allowing the enemy to shoot when allowed.
	//(Anything to do with firing on enemy is currently bugged.)
	public Bullet shoot() {
		Bullet shot = null;
		if (y != 0) {
			shot = new Bullet(x, y);
			fire();
		}

		
		return shot;
		}

	
	
	// Methods
	
	/**
	 * Right now in text base, player can't shoot. enemyhealth will not be changed
	 * not too sure how you guys want to do this
	public void updateHealth(){
		
	}
	*/
	//Allowing the enemy to be drawn on the board for the viewing pleasure of the user.
	public void draw(String[][] board) {
		if (board.length > y && board[0].length > x) {
			board[y][x] = new String(enemySymbol);
		}
	}
	
}
