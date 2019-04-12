package Entities;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import java.util.Random;

import Bullet.AvatarBullet;
import Bullet.Bullet;
import Collidable.Collidable;

/**
* Group 26 CPSC 219 Project TUT 06
* CLass that is responsible for creating the Game's avatar, placing it on the board, giving it its dimensions and appearance,
* determining if it has collided with anything on the board by implementing the interface Collidable
* and determining whether or not it should fire.
* It is also responsible for using user input for movement.
*/
public class Avatar implements Collidable
{
	private int xCord;
	private int yCord;
	private int radius = 22;
	private int maxXCord;
	private int maxYCord;
	private static String PLAYER = "O";
	private static final int SPEED = 1;
	private static int avatarLives = 10;
	private boolean nextShot = true;
	private boolean hasFired = true;
	private static int score;
	public static final int DEFAULT_GUI_WIDTH = 30;
	public static final int DEFAULT_GUI_HEIGHT = 30;

	/**
	* Constructor for the text version of the game that creates the Avatar with defaul values at a sepecified location.
	* @param x The x coordinate of the specified location.
	* @param y The y coordinate of the specified location.
	*/
	public Avatar(int x, int y)
	{
		xCord = x;
		yCord = y;
	}

	/**
	* Constructor for the GUI version of the game that creates the Avatar with a specified radius, a limit of how far the player can move on the board and at a sepecified location.
	* @param xCord The x coordinate of the specified location where Avatar will be created.
	* @param yCord The y coordinate of the specified location where Avatar will be created.
	* @param radius The radius of the hitbox of the Avatar.
	* @param maxXCord The horizontal limit to which that player can move without triggering a specific action.
	* @param maxYCord The vertical limit to which that player can move without triggering a specific action.
	*/
	public Avatar(int xCord, int yCord, int radius, int maxXCord, int maxYCord)
	{
		this.xCord = xCord;
		this.yCord = yCord;
		this.radius = radius;
		this.maxXCord = maxXCord;
		this.maxYCord = maxYCord;
	}

	/**
	* Constructor for GUI that creates the Avatar with a specified radius and at a specified location.
	* @param xCord The x coordinate of the specified location where Avatar will be created.
	* @param yCord The y coordinate of the specified location where Avatar will be created.
	* @param radius The radius of the hitbox of the Avatar.
	*/
	public Avatar(int xCord, int yCord, int radius)
	{
		this.xCord = xCord;
		this.yCord = yCord;
		this.radius = radius;
	}

	/**
	* A copy constructor that creates a new Avatar with same parameters as any provided Avatar that has been previously created.
	* @param a The provided Avatar whose parameters will be copied into the new one.
	*/
	public Avatar(Avatar a)
	{
		this.xCord = a.getX();
		this.yCord = a.getY();
		this.radius = a.getRadius();
		this.maxXCord = a.getMaxXCord();
		this.maxYCord = a.getMaxYCord();
	}

	/**
	* Translate user input into the Avatar movement based on which key, out of W, A, S and D, that the player clicked.
	* @param s The String which will determine which way the Avatar will move, that is given by the user's input.
	*/
	public void move(String s)
	{
		if (s.equals("A"))
		{
			if (xCord - SPEED >= 0)
			{
				xCord-= SPEED;
			}
		}
		else if (s.equals("D"))
		{
			if ((xCord + SPEED) < 9)
			{
				xCord+= SPEED;
			}
		}
		else if (s.equals("W"))
		{
			if ((yCord - SPEED) >= 0)
			{
				yCord-= SPEED;
			}
		}
		else if (s.equals("S"))
		{
			if ((yCord + SPEED) < 10)
			{
				yCord+= SPEED;
			}
		}
	}

	/**
	*	Creates and returns a bullet for the Avatar to shoot as long as he's inside the bounds of the board.
	* @return The Bullet created for the Avatar to shoot.
	*/
	public Bullet shoot()
	{
		Bullet shot = null;
		if (yCord != maxYCord)
		{
			shot = new AvatarBullet(xCord, yCord);
			fire();
		}
		return shot;
	}

	/**
	* Shoots that bullet that shoot() had created, by setting hasFired to true or false based on whether a randomly picked integer is divisible by 2 or not, effectively making the Avatar fire once every two counts.
	*/
	public void fire()
	{
		Random rand = new Random();
		int n = rand.nextInt(50);
		if (n % 2 == 0)
		{
			setHasFired(false);
		}
		else
		{
			setHasFired(true);
		}
	}

	/**
	* Checks whether or not the Avatar has collided with any given object.
	* @param c The Collidable object given that will be checked to determine whether Avatar has collided with it.
	* @return A boolean of whether the Avatar has collided with the given object or not.
	*/
	public boolean Collide(Collidable c)
	{
		boolean collided = false;
		if(c.collidedWith(c) == true)
		{
			collided = true;
		}
		return collided;
	}

	/**
	* Takes the text version's board and places the created Avatar in it, on the position that it was specified to be.
	* @param board The text base board where Avatar will be placed.
	*/
	public void draw(String[][] board)
	{
		board[yCord][xCord] = PLAYER;
	}

	/**
	* Method for getting the specified location's where Avatar should be placed x coordinate.
	* @return The integer that is location's x coordinate.
	*/
	public int getX()
	{
		return xCord;
	}

	/**
	* Method for setting the specified location's x coordinate.
	* @param xCord The new x coordinate to which the location will be set to.
	*/
	public void setX(int xCord)
	{
		this.xCord = xCord;
	}

	/**
	* Method for getting the specified location's where Avatar should be placed y coordinate.
	* @return The integer that is location's y coordinate.
	*/
	public int getY()
	{
		return yCord;
	}

	/**
	* Method for setting the specified location's y coordinate.
	* @param y The new y coordinate to which the location will be set to.
	*/
	public void setY(int y)
	{
		this.yCord = y;
	}

	/**
	* Method for getting the number of lives the Avatar has at any given moment
	* @return An integer that represents the number of lives the Avatar has at the moment given.
	*/
	public static int getLives()
	{
		return avatarLives;
	}

	/**
	* Method for setting the amount of lives the Avatar has, but does nothing if the nubmer given to set the lives to is negative.
	* @param lives The number of lives Avatar will be set to have.
	*/
	public void setLives(int lives)
	{
		if (lives >= 0)
		{
			Avatar.avatarLives = lives;
		}
	}

	/**
	* Method for setting the horizontal limit of the board that the player can move to, to any given x coordinate.
	* @param max The x coordinate that the limit will be set to.
	*/
	public void setMaxXCord(int max)
	{
		this.maxXCord = max;
	}

	/**
	* Method for setting the vertical limit of the board that the player can move to, to any given y coordinate.
	* @param max The y coordinate that the limit will be set to.
	*/
	public void setMaxYCord(int max)
	{
		this.maxYCord = max;
	}

	/**
	* Method for getting the Radius of the Avatar.
	* @return An int that represents the radius of the Avatar.
	*/
	public int getRadius()
	{
		return radius;
	}

	/**
	* Method for setting the radius of the Avatar to any given radius.
	* @param radius The value to which the radius of the Avatar will be set to.
	*/
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	/**
	* Method for getting the horizontal limit of the board to which the Avatar can move to.
	* @return An int that represents the x coordinate of the limit.
	*/
	public int getMaxXCord()
	{
		return maxXCord;
	}

	/**
	* Method for getting the vertical limit of the board to which the Avatar can move to.
	* @return An int that represents the y coordinate of the limit.
	*/
	public int getMaxYCord()
	{
		return maxYCord;
	}

	/**
	* Method for getting whether the Avatar has a next shot or not.
	* @return A boolean that says whether the Avatar has a next shot or not.
	*/
	public boolean getNextShot()
	{
		return nextShot;
	}

	/**
	* Overriden method from the implemented class that gets the length of the Avatar's image.
	* @return An integer that represents the length of the Avatar's image.
	*/
	@Override
	public int getWidth()
	{
		return 0;
	}

	/**
	* Overriden method from the implemented class that gets the height of the Avatar's image.
	* @return An integer that represents the height of the Avatar's image.
	*/
	@Override
	public int getHeight()
	{
		return 0;
	}

	/**
	* Method for getting the Avatar's score at any given time.
	* @return An int that represents the score of the Avatar at the given time.
	*/
	public static int getScore()
	{
		return score;
	}

	/**
	* Method for setting the score the Avatar has, but does nothing if the nubmer given to set the score to is negative.
	* @param score The score to which the Avatar's score will be set to.
	*/
	public void setScore(int score)
	{
		if (score >= 0)
		{
			Avatar.score = score;
		}
	}

	/**
	* Draws the Avatar's image for GUI as a circle with specific measures and parameters and a location that fit the board and uses an image file to change the circle's appearance.
	* @return A Node that is the drawing of the Avatar.
	*/
	public static Node createAvatar()
	{
		Circle circle = new Circle(22); 
	    Image img = new Image("file:Images/avatar.gif"); //https://040592.carrd.co/assets/images/image01.gif?v73617328834451")
		circle.setFill(new ImagePattern(img));
	    circle.setTranslateY(660 - 29);
	    circle.setTranslateX(0);
	    return circle;
	}

	/**
	* Overriden method from the implemented class that gets that player has not collided with anything yet.
	* @param c The given object that Avatar will or will not supposedly collide with, but in this case will not collide with.
	* @return A boolean set to false indicating that the Avatar has not collided with anything yet, alloing the game to keep running.
	*/
	@Override
	public boolean collidedWith(Collidable c)
	{
		return false;
	}
	
	/**
	* Method for getting whether or not the Avatar has fired.
	* @return A boolean determined by whether the Avatar has fired or not.
	*/
	public boolean getHasFired() 
	{
		return hasFired;
	}
	/**
	* Method for setting whether or not the Avatar has fired.
	* @param hasFired The boolean to which the variable will be set to.
	*/
	public void setHasFired(boolean hasFired) 
	{
		this.hasFired = hasFired;
	}
}

