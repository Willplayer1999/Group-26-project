import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Group 26 CPSC 219 Project
* There are 2 types of platform: Static Platform and Moving Platform
* This class creates platforms, move platforms, check collision between platforms and avatar
* The moving platforms will be moving sideways and change direction when they reach the edge
*/
public class Platforms implements Collidable
{
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

	
	/**
	* Constructor for GUI
	* Create platform at some positions with default height and width
	* speed is how fast platforms are moving
	*/
	public Platforms (int x, int y, int maxX, int maxY)
	{
		setX(x);
		setY(y);
		this.height = DEFAULT_PLATFORM_HEIGHT_IN_GUI;
		this.width = DEFAULT_PLATFORM_WIDTH_IN_GUI;
		this.speed = DEFAULT_SPEED;
	}

	/**
	* Constructor for TextGame
	* Create platform at some initial position on TextGame
	* x: x coordinate of platform
	* y: y coordinate of platform
	*/
	public Platforms (int x, int y)
	{
		this(x,y,TextGame.COLUMNS, TextGame.ROWS);
		this.xCord = x;
		this.yCord = y;
	}

	/**
	* Copy Constructors
	* platform: to copy
	*/
	public Platforms(Platforms pl)
	{
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
	* Check collision between avatar and platforms
	* if avatar and platform collided, return true
	* else if avatar falls off platform, avatar will lose a live and respawn 
	*/
	public boolean collidedWith(Collidable c)
	{
		boolean collided = false;
		if (c.getX() == xCord && c.getY() == yCord)
		{
			collided = true;
		}
		return collided;
	}

	/**
	* This method is for drawing on textGame
	* Draw platforms on the board with its x and y coordinates
	* Symbol of platform is "P"
	* Symbol of main platform is "M"
	* Main platform is the first and last row
	*/
	
	public void draw(String[][] board)
	{
		if (yCord == 9|| yCord == 0)
		{
			board[yCord][xCord] = new String(MAIN_PLATFORM_SYMBOL);
		}
		else if (board.length > yCord && board[0].length > xCord)
		{
			board[yCord][xCord] = new String(PLATFORM_SYMBOL);
		}
	}

	/**
	* This method is for drawing on GUIGame
	* Draw platform on the board with its x and y coordinates
	* Enemy appears as a photo or it appears in green square shape
	* return Node
	*/
	public static Node draw(int x, int y, Pane z)
	{
		Rectangle rect = new Rectangle(60, 58, Color.GREEN);
		rect.setTranslateX(x);
		rect.setTranslateY(y);
		if(y == 540 || y == 60)
		{
			rect.setFill(Color.WHITE);
		}
		rect.setStroke(Color.BLACK);
		z.getChildren().add(rect);
		return rect;
	}


	/**
	* 
	*/
	public boolean move()
	{
		return false;
	}

	/**
	* Getter for X coordinate
	* return x coordinate of platform
	*/
	public int getX()
	{
		return xCord;
	}

	/**
	* Getter for Y coordinate
	* return Y coordinate of platform
	*/
	public int getY()
	{
		return yCord;
	}

	/**
	* setter for maxX 
	* maxX equals number of columns in Textgame
	*/
	public int getMaxXCord()
	{
		return maxXCord;
	}

	/**
	* setter for maxY
	* maxY equals number of rows in Textgame
	*/
	public int getMaxYCord()
	{
		return maxYCord;
	}

	/**
	* getter for maxX
	* maxX: how far in x direction (column) enemy can go
	*/
	public int getXCord()
	{
		return xCord;
	}

	/**
	* setter for maxX 
	* maxX equals number of columns in Textgame
	*/
	public void setXCord(int x)
	{
		this.xCord = x;
	}

	/**
	* setter for x coordinate
	* if x coordinate is negative, it will be 0
	* if x coordinate is more than maxX, it will be maxX
	* else x equal x 
	*/
	public void setX(int x)
	{
		if (x < maxXCord && x >= 0)
		{
			this.xCord = x;
		}
		else if (x < 0)
		{
			this.xCord = 0;
		}
		else if (x >= maxXCord)
		{
			this.xCord = maxXCord - 1;
		}
	}

	/**
	* setter for y coordinate
	* if y coordinate is negative, it will be 0
	* if y coordinate is more than maxY, it will be maxY
	* else y equal y
	* 
	*/
	public void setY(int y)
	{
		if (y < maxYCord && y >= 0)
		{
			this.yCord = y;
		}
		else if (y < 0)
		{
			this.yCord = 0;
		}
		else if (y >= maxYCord)
		{
			this.yCord = maxYCord - 1;
		}
	}

	/**
	* setter for maxX 
	* maxX equals number of columns in Textgame
	*/
	public void setMaxXCord(int x)
	{
		this.maxXCord = x;
	}

	/**
	* setter for maxY
	* maxY equals number of rows in Textgame
	*/
	public void setMaxYCord(int y)
	{
		this.maxYCord = y;
	}


	/**
	* getter for platform's width
	* return platform's width
	*/
	public int getWidth()
	{
		return width;
	}

	/**
	* getter for platform's height
	* return platform's height
	*/
	public int getHeight()
	{
		return height;
	}
	
	/**
	* setter for platform's width
	* set width to a given value
	*/
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	* setter for platform's height
	* set height to a given value
	*/
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	* getter for speed
	* return speed of moving platforms
	*/
	public int getSpeed()
	{
		return speed;
	}

	/**
	* setter for speed
	* set speed of platforms to an int
	*/
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	//GUIMethods
	/**
	*
	*/
	public static Node createPlatform(int x, int y, Pane p)
	{
		Rectangle rect = new Rectangle(60, 58);
    	Image img = new Image("file:Images/platform.jpg"); //"https://www.filterforge.com/filters/13302.jpg"
    	rect.setFill(new ImagePattern(img));
    	rect.setStroke(Color.BLACK);
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        p.getChildren().add(rect);
        return rect;
	}

	

	/**
	* This method is for drawing on GUIGame
	* Draw platforms on the board with its x and y coordinates
	* Static platform is on the top and bottom of the screen
	* return Node 
	*/
	public static Node createStaticPlatform(int x, int y, Pane p)
	{
		Rectangle rect = new Rectangle(58, 58);
    	Image img = new Image("file:Images/mainplatform.png"); //https://cartocdn-gusc.global.ssl.fastly.net/opmbuilder/api/v1/map/named/opm-mars-basemap-v0-1/0,1,2,3,4/3/5/3.png");
    	rect.setFill(new ImagePattern(img));
        //rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setTranslateX(x);
        rect.setTranslateY(y);
		p.getChildren().add(rect);
		return rect;
	}

	/**
	* Override method from Collidable
	* We don't use this method anymore! 
	*/
	@Override
	public int getRadius()
	{
		return 0;
	}
}
