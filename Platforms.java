import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*Group 26 CPSC 219 Project
 * Our Platform class, Players must stand on platforms or lose lives. This sets up all their positions and what causes them to lose lives.
 * Current Issue: Starting Platform is too big, allowing user to sit back and snipe all the enemies without actually using the platforms as
 * intended.
 * New Intentions: The Platforms should move in the next Demo when we go into GUI. (Graphic User Interface).
 * 
 */

public class Platforms implements Collidable{
	// Instance Variables
	private int xCord;
	private int yCord;
	private int maxXCord;
	private int maxYCord;
	private int width;
	private int height;
	private int speed;
	private static final int DEFAULT_SPEED = 1; //change according to needs - speed of platforms
	private static final String PLATFORM_SYMBOL = "P";
	private static final String MAIN_PLATFORM_SYMBOL = "M";
	private static final int DEFAULT_PLATFORM_WIDTH_IN_GUI = 60;
	private static final int DEFAULT_PLATFORM_HEIGHT_IN_GUI = 58;


	
	// Constructors	
	//Main Constructor for GUI
	public Platforms (int x, int y, int maxX, int maxY) {
		/**
		this.xCord = x;
		this.yCord = y;
		this.maxXCord = maxX;
		this.maxYCord = maxY;
		*/
		setX(x);
		setY(y);
		//maxXCord = GUIGame.SCREEN_WIDTH - 1;
		//maxYCord = GUIGame.SCREEN_HEIGHT - 1;
		this.height = DEFAULT_PLATFORM_HEIGHT_IN_GUI;
        this.width = DEFAULT_PLATFORM_WIDTH_IN_GUI;
		this.speed = DEFAULT_SPEED;
	}
	//Main Constructor for Text Game
	public Platforms (int x, int y) {
		this(x,y,TextGame.COLUMNS, TextGame.ROWS);
		this.xCord = x;
		this.yCord = y;
	}
		
/*		if(speed >= 0) {
        	this.speed = speed;
        } else {
        	this.speed = DEFAULT_SPEED;
        }
        */
	
	
	public Platforms(Platforms pl) {
		this.xCord = pl.xCord;
		this.yCord = pl.yCord;
		this.maxXCord = pl.maxXCord;
		this.maxYCord = pl.maxYCord;
		this.width = pl.getWidth();
    	this.height =pl.getHeight();
		this.speed = DEFAULT_SPEED;
	}
	
	// Methods
	/**
	public boolean overlapsWith(Avatar a) {
		boolean alive = false;
		if (a.getXCord() == xCord && a.getYCord() == yCord) {
			alive  = true;
		}
		return alive;
	}
	*/
	public boolean collidedWith(Collidable c) {
        boolean collided = false;
    	if (c.getX() == xCord && c.getY() == yCord) {
			collided = true;
		}
        return collided;
    }

	public void draw(String[][] board) {
		if (yCord == 9) {
			board[yCord][xCord] = new String(MAIN_PLATFORM_SYMBOL);
		} else if (board.length > yCord && board[0].length > xCord) {
			board[yCord][xCord] = new String(PLATFORM_SYMBOL);
		} 

	}
	
    public static Node draw(int x, int y, Pane z) {
    	Rectangle rect = new Rectangle(60, 58, Color.GREEN);
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        if(y == 540 || y == 60) {
        	rect.setFill(Color.WHITE);
        }
        rect.setStroke(Color.BLACK);

        z.getChildren().add(rect);
        return rect;
    }
    
	//Abstract Method that dictates how the platforms move
	public boolean move() {
		return false;
	}
	
	public int getX() {
		return xCord;
	}
	public int getY() {
		return yCord;
	}
	public int getMaxXCord() {
		return maxXCord;
	}
	public int getMaxYCord() {
		return maxYCord;
	}
	///
	public int getXCord() {
		return xCord;
	}
	public void setXCord(int x) {
		this.xCord = x;
	}
	///
	
	public void setX(int x) {
		if (x < maxXCord && x >= 0) {
			this.xCord = x;
		} else if (x < 0) {
			this.xCord = 0;
		} else if (x >= maxXCord) {
			this.xCord = maxXCord - 1;
		}
	}
	public void setY(int y) {
		if (y < maxYCord && y >= 0) {
			this.yCord = y;
		} else if (y < 0) {
			this.yCord = 0;
		} else if (y >= maxYCord) {
			this.yCord = maxYCord - 1;
		}
	}
	public void setMaxXCord(int x) {
		this.maxXCord = x;
	}
	public void setMaxYCord(int y) {
		this.maxYCord = y;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public int getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	


}