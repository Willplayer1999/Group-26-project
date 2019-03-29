import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBullet extends Bullet{
	private static final String ENEMY_BULLET_SYMBOL = "*";
	//Constructor for text Based
	public EnemyBullet(int x, int y) {
		super(x,y);
	}
	//Constructor for GUI
	public EnemyBullet(int x, int y, int momentum) {
        super(x, y, momentum);
    }
	//Copy Constructor
	public EnemyBullet(EnemyBullet a) {
		super(a);
	}
	@Override
	public boolean move() {
		boolean edgy;
		if (getYCord() == getMaxYCord()-1) {
			edgy = false;
		} else {
			edgy = true;
			setYCord(getYCord()+ getMomentum());
		}
		return edgy;
	}
	@Override
	public void draw(String[][] board) {
		if( (getXCord() >= 0 && getXCord() <= getMaxXCord()) && (getYCord() >= 0 && getYCord() <= getMaxYCord()) ) {
    		board[getYCord()][getXCord()] = ENEMY_BULLET_SYMBOL;
    	}
	}
	@Override
	public void draw(Rectangle s) {
		s.setFill(Color.BLACK);
		s.setX(getXCord());
		s.setY(getYCord());
		s.setWidth(getWidth());
		s.setHeight(getHeight());;
	}
}