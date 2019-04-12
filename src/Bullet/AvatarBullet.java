package Bullet;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import java.util.List;

/**
* Group 26 CPSC 219 Project TUT 06
* Class that uses the Bullet constructors by extending it to create bullets specifically for the Avatar and makes this bullet move.
*/
public class AvatarBullet extends Bullet
{
	/**
	* Constructor that creates an AvatarBullet, to be used on the text version of the game, at a specified location, with a default momentum.
	* @param x The x coordinate of the specified location.
	* @param y The y coordinate of the specified location.
	*/
	public AvatarBullet(int x, int y)
	{
		super(x,y);
	}

	/**
	* Constructor that creates an AvatarBullet, to be used on the GUI version of the game, at a specified location and with a momentum.
	* @param x The x coordinate of the specified location.
	* @param y The y coordinate of the specified location.
	* @param momentum The momentum that the created bullet will have.
	*/
	public AvatarBullet(int x, int y, int momentum)
	{
		super(x, y, momentum);
	}

	/**
	* Constructor that creates an AvatarBullet, by taking any previously created bullet and copying all of its parameters.
	* @param a The bullet whose parameters will be copied into the new bullet.
	*/
	public AvatarBullet(AvatarBullet a)
	{
		super(a);
	}

	/**
	* Method for the GUI version that will draw the visuals of the AvatarBullet on a provided panel. It will be a black rectangle with specified length and width
	* @param d The width of the rectangle.
	* @param e The length of the rectangle.
	* @param p The panel provide where the bullet will be drawn.
	* @return The drwing of the bullet.
	*/
	public static Node createAvatarBullet(double d, double e, Pane p)
	{
		Rectangle bullet = new Rectangle (5, 20, Color.BLACK);
		bullet.setStroke(Color.BLACK);
		bullet.setTranslateX(d);
		bullet.setTranslateY(e);
		p.getChildren().add(bullet);
		return bullet;
	}

	/**
	* For every element in a list of drawn AvatarBullets, this method moves them in a vertical line.
	* @param b The list of drawn bullets.
	*/
	public static void moveBullet(List<Node> b)
	{
		for (Node bullet : b)
		{
			bullet.setTranslateY(bullet.getTranslateY()-5);
		}
	}

	/**
	* Overriden the abstract from the parent class.
	*/
	@Override
	public boolean move()
	{
		boolean edgy;
		if (getYCord() == 0)
		{
			edgy = false;
		}
		else
		{
			edgy = true;
			setYCord(getYCord() - getMomentum());
		}
		return edgy;
	}
}
