package Bullet;

import Collidable.Collidable;
import Game.*;
import Game.TextGame;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
* Group 26 CPSC 219 Project TUT 06
* The abstract Bullet class controls bullet behaviors through drawing bullets on the board
* and check if bullet has collided with enemy/ avatar
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

	/**
	* Constructor for TextGame
	* Create bullet with momentum is 1
	* x: x coordinate of bullet
	* y: y coordinate of bullet
	*/
	public Bullet(int x, int y)
	{
		maxYCord = TextGame.ROWS-1;
		maxXCord = TextGame.COLUMNS-1;
		setXCord(x);
		setYCord(y);
		this.width = 0;
		this.height = 0;
		this.momentum = 2;
	}

	
	/**
	*Constructors for GUI game
	* momentum is 1
	* x: x coordinate of bullet
	* y: y coordinate of bullet
	*
	*/
	public Bullet(int x, int y, int momentum)
	{
		setXCord(x);
		setYCord(y);
		this.height = DEFAULT_GUI_HEIGHT;
		this.width = DEFAULT_GUI_WIDTH;
		if(momentum >= 0)
		{
			this.momentum = momentum;
		}
		else
		{
			this.momentum = DEFAULT_MOMENTUM;
		}
	}

	/**
	* Copy Constructor
	* bullet: to copy
	*/
	public Bullet(Bullet bullet)
	{
		this.xCord = bullet.getXCord();
		this.yCord = bullet.getYCord();
		this.maxXCord = bullet.getMaxXCord();
		this.maxYCord = bullet.getMaxYCord();
		this.height = bullet.getHeight();
		this.width = bullet.getWidth();
		this.momentum = DEFAULT_MOMENTUM;
	}

	/**
	* Abstract method for move
	* AvatarBullet and EnemyBullet are moving in different direction
	*/
	public abstract boolean move();

	/**
	* Draw bullet on the board of Textgame as a string
	* 
	*/
	public void draw(String[][] board)
	{
		if (board.length > yCord && board[0].length > xCord)
		{
			board[yCord][xCord] = new String(BULLET_SYMBOL);
		}
	}

	/**
	* Draw bullet on GUI
	* Bullet is a rectangle 
	*/
	public void draw(Rectangle s)
	{
		s.setFill(Color.BLACK);
		s.setX(getXCord());
		s.setY(getYCord());
		s.setWidth(getWidth());
		s.setHeight(getHeight());;
	}

	/**
	* This method is for TextGame
	* This method checks collision between enemy/avatar and bullet coordinates
	* If avatarBullet and enemy has the same coordinates, enemy will be killed
	* If enemyBullet and avatar has the same coordinates, enemy will be killed
	* return boolean type to check if they are collided or not
	*/
	public boolean collidedWith(Collidable c)
	{
		boolean collided = false;
		if (xCord + width >= c.getX() && xCord <= c.getX() + c.getWidth() && (yCord + height >= c.getY() || yCord + height == c.getY()-1) && yCord <= c.getY() + c.getHeight())
		{
			collided = true;
		}
		return collided;
	}

	/**
	* Getter for X coordinate
	* return x coordinate of bullet
	*/
	public int getXCord()
	{
		return xCord;
	}

	/**
	*Getter for y coordinate
	* return y coordinate of bullet
	*/
	public int getYCord()
	{
		return yCord;
	}

	/**
	* Getter for maxX coordinate
	* maxX (Columnns) bullet can reach
	*/
	public int getMaxXCord()
	{
		return maxXCord;
	}

	/**
	* Getter for maxY coordinate
	* maxY (Rows) bullet can reach
	*/
	public int getMaxYCord()
	{
		return maxYCord;
	}

	/**
	* Getter for bullet's width
	* return width for the bullet
	*/
	public int getWidth()
	{
		return width;
	}

	/**
	* Getter for bullet's height
	* return height for the bullet
	*/
	public int getHeight()
	{
		return height;
	}

	/**
	* setter for x coordinate
	* if x coordinate is negative, it will be 0
	* if x coordinate is more than maxX, it will be maxX
	* else x equal x 
	*/
	public void setXCord(int x)
	{
		if(x > maxXCord)
		{
			this.xCord = maxXCord;
		}
		else if(x <= 0)
		{
			this.xCord = 0;
		}
		else
		{
			this.xCord = x;
		}
	}

	/**
	
	* setter for y coordinate
	* if y coordinate is negative, it will be 0
	* if y coordinate is more than maxY, it will be maxY
	* else y equal y
	* 
	*/

	public void setYCord(int y)
	{
		if(y > maxYCord)
		{
			this.yCord = maxYCord;
		}
		else if(y <= 0)
		{
			this.yCord = 0;
		}
		else
		{
			this.yCord = y;
		}
	}

	/**
	* setter for maxX 
	* maxX equals number of columns in Textgame
	*/
	public void setMaxX(int x)
	{
		this.maxXCord = x;
	}
	
	/**
	* setter for maxY
	* maxY equals number of rows in Textgame
	*/
	public void setMaxY(int y)
	{
		this.maxYCord = y;
	}

	/**
	* getter for momentum
	* return speed of bullet
	*/
	public int getMomentum()
	{
		return momentum;
	}

	/**
	* setter for momentum
	*/
	public void setMomentum(int momentum)
	{
		this.momentum = momentum;
	}

	/**
	* setter for bullet's height
	* set height to a given value
	*/
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	* setter for bullet's width
	* set width to a given value
	*/
	public void setWidth(int width)
	{
		this.width = width;
	}
}
