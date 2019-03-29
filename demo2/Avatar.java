/*Group 26 CPSC 219 Project
 * Our Avatar class, this has everything to do with the player, and what they can do.
 * Current Issue: None.
 * 
 */

public class Avatar {
	//Instance Variables
	private int avatarLives;
	private int xCord = 3;
	private int yCord = 9;
	private int maxXCord; // column which is 10 right?
	private int maxYCord; // row which is 7 right?
	private static String PLAYER = "O";
	private static int SPEED = 1;
	private boolean nextShot;


	//Main Constructor
	Avatar(int xCoord, int yCoord, int maxXCoord, int maxYCoord, int avaHealth){
		xCord = xCoord;
		yCord = yCoord;
		maxXCord = maxXCoord;
		maxYCord = maxYCoord; 
		avatarLives = avaHealth;
		setNextShot(true);
	}
	// Copy Constructor
	Avatar(Avatar avatar){
		this.xCord = avatar.getXCord();
		this.yCord = avatar.getYCord();
		this.maxXCord = avatar.getMaxXCord();
		this.maxYCord = avatar.getMaxYCord();
		this.avatarLives = avatar.getAvatarLives();
		this.setNextShot(true);
		
	}
	//3 int Constructor for position and life, for the board
	public Avatar(int i, int j, int health) {
		xCord = i;
		yCord = j;
		avatarLives = health;

	}
	//Getter for lives.
	public int getAvatarLives(){
		return avatarLives;
	}
	//Setter for Lives, and setting it to 3. (Will need to change later on)
	public void setAvatarLives(int avatarLives){
		this.avatarLives = 3;
		if (avatarLives >= 0){
			this.avatarLives = avatarLives;
		}
	}
	//User movement, when they hit a key and enter, they will move in that direction
	//if they enter an incorrect key, their turn is skipped and the board resets.
	public void move(String input){
		if (input.equals("W")) {
			if (yCord - SPEED >= 0){
				yCord -= SPEED;
			}
		}
		else if (input.equals("A")) {
			if (xCord - SPEED >= 0) {
				xCord -= SPEED;
			}
		}
		else if (input.equals("S")) {
			if (yCord + SPEED <  10){
				yCord += SPEED;
			}
		}
		else if (input.equals ("D")) {
			if (xCord + SPEED < 7){
				xCord += SPEED;
			}
		}
		else {
			System.out.println("Invalid Input");
		}
	}

	//Update lives, should be used to lower lives when user dies.
	public int updateLives(){
		return avatarLives-1;
	}
	//Shooting, to create bullets for Avatar.
	public Bullet shoot(){
		Bullet nextShot = null;
		nextShot = new Bullet(xCord,yCord-1);
		return nextShot;
		
	}
	//Getter for XCord
	public int getXCord() {
		return xCord;
	}
	//Getter for YCord
	public int getYCord() {
		return yCord;
	}
	//Getter for MaxXCord (Size of grid)
	public int getMaxXCord() { 
		return maxXCord;
	}
	//Getter for MaxYCord (Size of Grid)
	public int getMaxYCord() { 
		return maxYCord;
	}
	//setter for X Cord.
	public void setXCoord(int x) {
		this.xCord = x;
	}
	//Setter for YCord.
	public void setYCoord(int y) {
		this.yCord = y;
	}
	//Setter for the max x Cord (Variable size possible)
	public void setMaxXCoord(int x) {
		this.maxXCord = x;
	}
	//setter for the max Y Cord (Variable Size Possible)
	public void setMaxYCoord(int y) {
		this.maxYCord = y;
	}
	//Draw Function to allow the user to actually appear on the grid for the user viewing pleasure.
	public void draw(String[][] board) {
			if (board.length > yCord && board[0].length > xCord) {
				board[yCord][xCord] = new String(PLAYER);
			} 
		
		}
	//Get the next shot
	public boolean getNextShot() {
		return nextShot;
	}
	//Set the next Shot.
	public void setNextShot(boolean nextShot) {
		this.nextShot = nextShot;
	}
}
