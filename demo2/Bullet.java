/*Group 26 CPSC 219 Project
 * Where our projectiles come from, including their instance variables, getters and setters.
 *  Intentions for next Version: To literally have them fire and deal damage against player lives.
 *  @Author Chad
 * 
 */
public class Bullet extends TextBase {
	//Instance Variables
	private String SYMBOL = "|";
	private int xCord;
	private int yCord;
	private int maxXCord;
	private int maxYCord;
	private int momentum;
	//Main Constructor
	public Bullet(int x, int y) {
		maxYCord = TextBase.ROW-1;
		maxXCord = TextBase.COLUMN-1;
		setX(x);
		setY(y);
		this.momentum = 2;
		
	}
	//Copy Constructor
	public Bullet(Bullet bullet) {
		this.xCord = bullet.getX();
		this.yCord = bullet.getY();
		this.maxXCord = bullet.getMaxX();
		this.maxYCord = bullet.getMaxY();
		this.setMomentum(2);
	}
	//Blank Constructor
	public Bullet() {
	}
	//Allows the Avatar's bullets to move in direction. Should also when they hit end of screen to disappear.
	public boolean avatarPew() {
		boolean atEdgeYet;
		if (getY() != 0) {
			atEdgeYet = true;
			setY(getY() - getMomentum());
			
		}else {
			atEdgeYet = false;
		}
		return atEdgeYet;
	}
	//Allows the Enemies Bullets to move in direction. Should Also when they hit the end of the screen to disappear.
	//Current Bug: Enemies can't fire at all.
	public boolean enemyPew() {
		boolean atEdgeYet;
		if (getY() <= getMaxY()) {
			atEdgeYet = false;
			
		}else {
			atEdgeYet = true;
			setY(getY() + getMomentum());
		}
		return atEdgeYet;
	}
		
	//Draw the | on the board indiciating the Bullets.
	public void draw(String[][] board) {
		if (board.length > yCord && board[0].length > xCord) {
			board[yCord][xCord] = new String(SYMBOL);
		}
	}
	//Getter for X
	public int getX() {
		return xCord;
	}
	//Getter for Y
	public int getY() {
		return yCord;
	}
	//Getter for Max X
	public int getMaxX() {
		return maxXCord;
	}
	//Getter for Max Y
	public int getMaxY() {
		return maxYCord;
	}
	//Setter for X, This is to properly set X if we change the size of the grid.
	public void setX(int x) {
		if(x > maxXCord) {
			this.xCord = maxXCord;
			
		} else if(x <= 0) {
			this.xCord = 0;
			
		} else {
			this.xCord = x;
			
		}
	}
	//Setter for Y, This is to properly set Y if we need to change the size of the grid.
	public void setY(int y) {
			if(y > maxYCord) {
				this.yCord = maxYCord;
				
			} else if(y <= 0) {
				this.yCord = 0;
				
			} else {
				this.yCord = y;
				
			}
		}
	//Setter for Max X size
	public void setMaxX(int x) {
		this.maxXCord = x;
	}
	//Setter for Max Y Size
	public void setMaxY(int y) {
		this.maxYCord = y;
	}
	//Getter for Momentum (How fast bullet moves)
	public int getMomentum() {
		return momentum;
	}
	//Setter for Momentum. (How Fast Bullet Moves)
	public void setMomentum(int momentum) {
		this.momentum = momentum;
	}
}
