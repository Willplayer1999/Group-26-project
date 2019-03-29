import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Graphics;

public class Avatar implements Collidable{
	
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


  public Avatar(int x, int y)
  {
    xCord = x;
    yCord = y;
  }
  public Avatar(int xCord, int yCord, int radius, int maxXCord, int maxYCord)
  {
    this.xCord = xCord;
    this.yCord = yCord;
    this.radius = radius;
    this.maxXCord = maxXCord;
    this.maxYCord = maxYCord;
  }
  public Avatar(int xCord, int yCord, int radius)
  {
		this.xCord = xCord;
		this.yCord = yCord;
		this.radius = radius;
  }
  public Avatar(Avatar a)
  {
    this.xCord = a.getX();
    this.yCord = a.getY();
    this.radius = a.getRadius();
    this.maxXCord = a.getMaxXCord();
    this.maxYCord = a.getMaxYCord();
  }

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

  /*
  public AvatarBullet shoot(AvatarBullet shot)
  {
    while(getNextShot() == true)
    {
      AvatarBullet projectile = new AvatarBullet(shot);
      return projectile;
    }
  }
*/
  ///////////////////////////
	public Bullet shoot() {
		Bullet shot = null;
		if (yCord != maxYCord) {
			shot = new AvatarBullet(xCord, yCord);
			fire();
		}
		return shot;
		}
	
	public void fire() {
		Random rand = new Random();
		int n = rand.nextInt(50);
		if (n % 2 == 0) {
			hasFired = false;
		}else {
			hasFired = true;
		}
			
			
	}
	////////////////////////
  public boolean Collide(Collidable c)
  {
    boolean collided = false;
    if(c.collidedWith(c) == true)
    {
      collided = true;
    }
    return collided;
  }

  public void draw(String[][] board)
  {
		board[yCord][xCord] = PLAYER;
	}
  public static Node draw()
  {
//	  s.setFill(Color.BLACK);
//	  s.setRadius(22);
      Circle circle = new Circle(22, Color.BLACK);
      circle.setTranslateY(600 - 29);
      circle.setTranslateX(0);
      circle.setStroke(Color.BLACK);
      return circle;
  }

  public int getX()
  {
    return xCord;
  }
  public void setX(int xCord)
  {
    this.xCord = xCord;
  }
  public int getY()
  {
    return yCord;
  }
  public void setY(int y)
  {
    this.yCord = y;
  }
  public static int getLives()
  {
    return avatarLives;
  }
  public void setLives(int lives)
  {
    if (lives >= 0)
    {
      this.avatarLives = lives;
    }
  }

  public void setMaxXCord(int max)
  {
    this.maxXCord = max;
  }
  public void setMaxYCord(int max)
  {
    this.maxYCord = max;
  }

  public int getRadius()
  {
    return radius;
  }
  public void setRadius(int radius)
  {
    this.radius = radius;
  }
  public int getMaxXCord()
  {
    return maxXCord;
  }
  public int getMaxYCord()
  {
    return maxYCord;
  }
  public boolean getNextShot()
  {
    return nextShot;
  }
@Override
public int getWidth() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public int getHeight() {

	return 0;
}
public static int getScore() {

	return score;
}

public void setScore(int score) {
	if (score >= 0) {
		this.score = score;
	}
}

@Override
public boolean collidedWith(Collidable c) {
	return false;
}

}