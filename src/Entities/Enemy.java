package Entities;
import Bullet.Bullet;
import Bullet.EnemyBullet;
import Collidable.Collidable;
import Game.TextGame;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/* @Group 26 CPSC 219 Project
* This class is for the enemies you will face and their associated positions and statistics.
* 
* The enemy class allows the game to create and interact with enemy position, allow Enemt to shoot
* 
* 
*
*/
public class Enemy implements Collidable
{
	// Instance Variables
	
	private static final String enemySymbol = "X";
	private int enemyHealth;
	// location of enemy
	private int x;
	private int y;
	// max X and max Y enemy can go
	private int radius = 22;
	private int maxY;
	private int maxX;
	private boolean hasFired = true;
	
	/**
	* Constructor for TextGame
	* Create alive enemy at some specific position on TextGame
	* i: x coordinate of enemy
	* j: y coordinate of enemy
	*/
	
	public Enemy(int i, int j)
	{
		this(i,j,0,TextGame.COLUMNS, TextGame.ROWS);
		this.x = i;
		this.y = j;
	}

	//Main Constructor
	/**
	*Constructor for TextGame
	* Create alive enemy at some specific position on TextGame
	* maxX and maxY is how far they can go on the board
	* x: x coordinate of enemy
	* y: y coordinate of enemy
	*/
	private Enemy(int x, int y, int radius, int maxX, int maxY)
	{
		this.x = Math.min(maxX-1, Math.max(0, x));
		this.y = Math.min(maxY-1, Math.max(0, y));
		this.radius = Math.max(0, radius);
		this.hasFired = true;
		this.maxY = maxY;
		this.maxX = maxX;
	}

	// Copy Constructor
	/**
	* Copy Constructor
	* parameter e to copy
	*/
	public Enemy(Enemy e)
	{
		this(e.getX(), e.getY(), e.getRadius(), e.getMaxX(), e.getMaxY());
		this.hasFired = e.getHasFired();
	}

	/**
	* getter for radius of enemy in GUI if it appear as a shape
	*/
	public int getRadius()
	{
		return radius;
	}

	/**
	* getter for health of enemy
	* when enemy health equals 0
	*/
	public int getHealth()
	{
		return enemyHealth;
	}

	/**
	* getter for x coordinate of enemy
	*/
	public int getX()
	{
		return x;
	}

	/**
	* getter for y coordinate of enemy
	*/
	public int getY()
	{
		return y;
	}

	/**
	* getter for maxY
	* maxY: how far in y direction (row) enemy can go
	*/
	public int getMaxY()
	{
		return maxY;
	}

	/**
	* getter for maxX
	* maxX: how far in x direction (column) enemy can go
	*/
	public int getMaxX()
	{
		return maxX;
	}

	/**
	* getter to check if enemy has shot or not
	*/
	public boolean getHasFired()
	{
		return hasFired;
	}

	// Setter methods
	/**
	* Setter for every single values of instance variables
	*/
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	/**
	* setter for enemy health
	*/
	public void setEnemyHealth(int enemyHealth)
	{
		this.enemyHealth = enemyHealth;
	}

	/**
	* setter for x coordinate
	* if x coordinate is negative, it will be 0
	* if x coordinate is more than maxX, it will be maxX
	* else x equal x
	*/
	public void setX(int x)
	{
		if (x < maxX && x >= 0)
		{
			this.x = x;
		}
		else if (x < 0)
		{
			this.x = 0;
		}
		else if (x >= maxX)
		{
			this.x = maxX - 1;
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
		if (y < maxY && y >= 0)
		{
			this.y = y;
		}
		else if (y < 0)
		{
			this.y = 0;
		}
		else if (y >= maxY)
		{
			this.y = maxY - 1;
		}
	}

	/**
	* setter for maxX 
	* maxX equals number of columns in Textgame
	*/
	public void setMaxX()
	{
		maxX = TextGame.COLUMNS;
	}

	/**
	* setter for maxY
	* maxY equals number of rows in Textgame
	*/
	public void setMaxY()
	{
		maxY = TextGame.ROWS;
	}

	/**
	* setter for hasFired
	* hasFired to check if enemy has shot or not
	*/
	public void setHasFired(boolean shot)
	{
		this.hasFired = shot;
	}

	/**
	* setter for hasfired
	* return true when enemy shoot
	*/
	public void fire()
	{
		hasFired = true;
	}

	/**
	* this method creates enemyBullet
	* if enemy hasn't shot or bullet has gone off the screen, new Bullet is created and shot
	*/
	public Bullet shoot()
	{
		Bullet shot = null;
		shot = new EnemyBullet(x, y);
		fire();
		return shot;
	}

	/**
	* This method is for drawing on textGame
	* Draw enemy on the board its x and y coordinates
	* Symbol of enemy is "X"
	*/
	public void draw(String[][] board)
	{
		if (board.length > y && board[0].length > x)
		{
			board[y][x] = new String(enemySymbol);
		}
	}	

	/**
	* This method is for TextGame
	* This method checks collision between enemy and bullet coordinates
	* If avatarBullet and enemy has the same coordinates, enemy will be killed
	* return boolean type to check if they are collided or not
	*/
	public boolean collidedWith(Collidable c)
	{
		boolean collided = false;
		if (x + radius >= c.getX() && x <= c.getX() + c.getRadius() && y + radius >= c.getY() && y <= c.getY() + c.getRadius())
		{
			collided = true;
		}
		return collided;
	}

	/**
	* This method is for drawing on GUIGame
	* Draw enemy on the board with its x and y coordinates
	* Enemy appears as a photo of UFO or it appears in a red circle shape
	* return Node so it will appears on GUI
	*/
	
	public static Node createEnemies(int xCord, int yCord)
	{
		Circle enemy = new Circle(22);
		Image img = new Image("https://i2.wp.com/78.media.tumblr.com/956241c12919ecbc1db62a909377b4a6/tumblr_mq3gij0Ebk1rt0tppo1_500.gif?zoom=1.5&w=605&ssl=1"); //https://i2.wp.com/78.media.tumblr.com/956241c12919ecbc1db62a909377b4a6/tumblr_mq3gij0Ebk1rt0tppo1_500.gif?zoom=1.5&w=605&ssl=1
		enemy.setFill(new ImagePattern(img));
		enemy.setTranslateY(yCord);
		enemy.setTranslateX(xCord);
		return enemy;
	}

	/**
	* Overriding method from Collidable interface
	* We were making different shape for enemy, therefore we have getter for width and height
	*/
	@Override
	public int getWidth()
	{
		return 0;
	}

	
	@Override
	public int getHeight()
	{
		return 0;
	}
}
