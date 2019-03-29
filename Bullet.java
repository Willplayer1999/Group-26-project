import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 */
public abstract class Bullet{
	private static final int DEFAULT_GUI_WIDTH = 5;
	private static final int DEFAULT_GUI_HEIGHT = 20;
	private static final String BULLET_SYMBOL = "|";
	private static final int DEFAULT_MOMENTUM = 1;
	private int xCord;
	private int yCord;
	private int maxXCord;
	private int maxYCord;
	private int momentum;
	private int width;
	private int height;
	
	//Constructor for text game
	public Bullet(int x, int y) {
		maxYCord = TextGame.ROWS-1;
		maxXCord = TextGame.COLUMNS-1;
		setXCord(x);
		setYCord(y);
		this.width = 0;
		this.height = 0;
		this.momentum = 2;
		
	}
	//Constructor for GUI 
	public Bullet(int x, int y, int momentum){
    	//maxXCord = GUIGame.SCREEN_WIDTH-1;
    	//maxYCord = GUIGame.SCREEN_HEIGHT-1;
    	setXCord(x);
    	setYCord(y);
        this.height = DEFAULT_GUI_HEIGHT;
        this.width = DEFAULT_GUI_WIDTH;
        
        if(momentum >= 0) {
        	this.momentum = momentum;
        } else {
        	this.momentum = DEFAULT_MOMENTUM;
        }
        
    }
	//Copy Constructor
	public Bullet(Bullet bullet) {
		this.xCord = bullet.getXCord();
		this.yCord = bullet.getYCord();
		this.maxXCord = bullet.getMaxXCord();
		this.maxYCord = bullet.getMaxYCord();
		this.height = bullet.getHeight();
        this.width = bullet.getWidth();
		this.momentum = DEFAULT_MOMENTUM;
	}

	public Bullet() {
	}
	
	public abstract boolean move();
	
	
	public void draw(String[][] board) {
		if (board.length > yCord && board[0].length > xCord) {
			board[yCord][xCord] = new String(BULLET_SYMBOL);
		}
	}
	
	public void draw(Rectangle s) {
		s.setFill(Color.BLACK);
		s.setX(getXCord());
		s.setY(getYCord());
		s.setWidth(getWidth());
		s.setHeight(getHeight());;
	}
	
	public boolean collidedWith(Collidable c) {
      
        boolean collided = false;
    	
    	if (xCord + width >= c.getX() && xCord <= c.getX() + c.getWidth() && 
				(yCord + height >= c.getY() || yCord + height == c.getY()-1) && yCord <= c.getY() + c.getHeight()) {
			collided = true;
		}

        return collided;
    }
/*	
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
		
*/

	public int getXCord() {
		return xCord;
	}
	public int getYCord() {
		return yCord;
	}
	public int getMaxXCord() {
		return maxXCord;
	}
	public int getMaxYCord() {
		return maxYCord;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public void setXCord(int x) {
		if(x > maxXCord) {
			this.xCord = maxXCord;
			
		} else if(x <= 0) {
			this.xCord = 0;
			
		} else {
			this.xCord = x;
			
		}
	}

	public void setYCord(int y) {
			if(y > maxYCord) {
				this.yCord = maxYCord;
				
			} else if(y <= 0) {
				this.yCord = 0;
				
			} else {
				this.yCord = y;
				
			}
		}
	
	public void setMaxX(int x) {
		this.maxXCord = x;
	}
	public void setMaxY(int y) {
		this.maxYCord = y;
	}
	public int getMomentum() {
		return momentum;
	}
	public void setMomentum(int momentum) {
		this.momentum = momentum;
	}
	public void setHeight(int height){
		this.height = height;
	}
	public void setWidth(int width){
		this.width = width;
	}
}
