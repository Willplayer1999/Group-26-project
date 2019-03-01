/*Group 26 CPSC 219 Project
 * Our Platform class, Players must stand on platforms or lose lives. This sets up all their positions and what causes them to lose lives.
 * Current Issue: Starting Platform is too big, allowing user to sit back and snipe all the enemies without actually using the platforms as
 * intended.
 * New Intentions: The Platforms should move in the next Demo when we go into GUI. (Graphic User Interface).
 * 
 */

public class Platform{
	// Instance Variables
	private int xCord;
	private int yCord;
	private int maxXCord;
	private int maxYCord;
	private static final String PLATFORM_SYMBOL = "P";


	
	// Constructors	
	public Platform (int x, int y, int maxX, int maxY) {
		this.xCord = x;
		this.yCord = y;
		this.maxXCord = maxX;
		this.maxYCord = maxY;
	}
	
	public Platform (int x, int y) {
		this.xCord = x;
		this.yCord = y;
	}
	
	
	public Platform(Platform pl) {
		this.xCord = pl.xCord;
		this.yCord = pl.yCord;
		this.maxXCord = pl.maxXCord;
		this.maxYCord = pl.maxYCord;
	}
	
	// Methods
	public boolean overlapsWith(Avatar a) {
		boolean alive = false;
		if (a.getXCord() == xCord && a.getYCord() == yCord) {
			alive  = true;
		}
		return alive;
	}
	

	public void draw(String[][] board) {

		if (board.length > yCord && board[0].length > xCord) {
			board[yCord][xCord] = new String(PLATFORM_SYMBOL);
		} 

	}

	//get X position for Platform
	public int getX() {
		return xCord;
	}
	//get Y position for Platform
	public int getY() {
		return yCord;
	}
	//get MaxX position for Platform for reference if grid is bigger than intended
	public int getMaxX() {
		return maxXCord;
	}
	//get MaxY position for Platform for reference if grid is bigger than intended
	public int getMaxY() {
		return maxYCord;
	}
	//setter for X to allow different size grids.
	public void setX(int x) {
		if (x < maxXCord && x >= 0) {
			this.xCord = x;
		} else if (x < 0) {
			this.xCord = 0;
		} else if (x >= maxXCord) {
			this.xCord = maxXCord - 1;
		}
	}
	//setter for Y to allow different size grids.
	public void setY(int y) {
		if (y < maxYCord && y >= 0) {
			this.yCord = y;
		} else if (y < 0) {
			this.yCord = 0;
		} else if (y >= maxYCord) {
			this.yCord = maxYCord - 1;
		}
	}
	//Setter for max X if grid needs to be bigger
	public void setMaxX(int x) {
		this.maxXCord = x;
	}
	//Setter for may Y if grid needs to be bigger
	public void setMaxY(int y) {
		this.maxYCord = y;
	}


}