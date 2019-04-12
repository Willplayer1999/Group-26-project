import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
* Group 26 CPSC 219 Project TUT 06
* Class that uses the Bullet constructors by extending it to create bullets specifically for the Enemies and makes this bullet move.
*/
public class EnemyBullet extends Bullet
{
	private static final String ENEMY_BULLET_SYMBOL = "*";

	/**
	* Constructor that creates an EnemyBullet, to be used on the text version of the game, at a specified location, with a default momentum.
	* @param x The x coordinate of the specified location.
	* @param y The y coordinate of the specified location.
	*/
	public EnemyBullet(int x, int y)
	{
		super(x,y);
	}
	/**
	* Constructor that creates an EnemyBullet, to be used on the GUI version of the game, at a specified location and with a given momentum.
	* @param x The x coordinate of the specified location.
	* @param y The y coordinate of the specified location.
	* @param momentum The momentum that the created bullet will have.
	*/
	public EnemyBullet(int x, int y, int momentum)
	{
		super(x, y, momentum);
	}

	/**
	* Constructor that creates an AvatarBullet, by taking a previously created bullet and copying all of its parameters.
	* @param a The bullet whose parameters will be copied into the new bullet.
	*/
	public EnemyBullet(EnemyBullet a)
	{
		super(a);
	}

	/**
	* Overriden the abstract method from the parent class for moving the bullets down by the momentum value number of spaces at a time in the text version of the game.
	* @return A boolean that determines whether or not the bullet the bullet is at its limit of movemnt, allowing it to move if it is returned true.
	*/
	@Override
	public boolean move()
	{
		boolean edgy;
		if (getYCord() == getMaxYCord()-1)
		{
			edgy = false;
		}
		else
		{
			edgy = true;
			setYCord(getYCord()+ getMomentum());
		}
		return edgy;
	}

	/**
	* Method for the GUI version Level 1 and Level 2 that will draw the visuals of the EnemyBullet on a provided panel. It will be an orange rectangle at a specified location.
	* @param f The x coordinate of the rectangle's location.
	* @param g The y coordinate of the rectangle's location.
	* @param p The panel provide where the bullet will be drawn.
	* @return The drawing of the bullet.
	*/
	public static Node createEnemyBullet(double f, double g, Pane p)
	{
		Rectangle enemyBullet = new Rectangle (5, 20, Color.ORANGE);
		enemyBullet.setStroke(Color.BLACK);
		enemyBullet.setTranslateX(f);
		enemyBullet.setTranslateY(g);
		p.getChildren().add(enemyBullet);
		return enemyBullet;
	}
	
	
	public static Node createLevelTwoEnemyBullet(double f, double g)
	{
		Rectangle enemyBullet = new Rectangle (5, 20, Color.ORANGE);
		enemyBullet.setStroke(Color.BLACK);
		enemyBullet.setTranslateX(f);
		enemyBullet.setTranslateY(g);
		
		return enemyBullet;
	}

	/**
	* For every element in a list of drawn EnemyBullets in the GUI version, this method moves them down in a vertical line two pixels at a time.
	* @param eB The list of drawn bullets.
	*/
	public static void moveEnemyBullet(List<Node> eB, int speed)
	{
		for (Node enemyBullet : eB)
		{
			enemyBullet.setTranslateY(enemyBullet.getTranslateY()+speed);
		}
	}

	/**
	* Overriden method from the parent class that draws the EnemyBullets on the text board, but only if their coordinates are inside the bounds of the board.
	* @param board The board in which the bullets are drawn.
	*/
	@Override
	public void draw(String[][] board)
	{
		if( (getXCord() >= 0 && getXCord() <= getMaxXCord()) && (getYCord() >= 0 && getYCord() <= getMaxYCord()) )
		{
			board[getYCord()][getXCord()] = ENEMY_BULLET_SYMBOL;
		}
	}

}
