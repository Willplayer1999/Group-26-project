import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MovingPlatform extends Platforms{
	private static final String MOVING_PLATFORM_SYMBOL = "P";
	
	//Constructor for TextBased
	public MovingPlatform(int x, int y){
		super(x, y);
	}
	//Constructor for GUI
	public MovingPlatform(int x, int y, int maxX, int maxY){
		super(x, y, maxX, maxY);
	}
	//Copy Constructor
	public MovingPlatform(MovingPlatform pl){
		super(pl);
	}
	
	@Override
	public boolean move(){
		boolean edgy;
		if (getX() == getMaxXCord()) {
			edgy = false;
		} 
		else {
			edgy = true;
			setX(getX()+ getSpeed());
		}
		return edgy;
	}
	@Override	
	public void draw(String[][] board) {

		if (board.length > getY() && board[0].length > getX()) {
			board[getY()][getX()] = new String(MOVING_PLATFORM_SYMBOL);
		} 

	}
	
	public void draw(Rectangle s) {
		/**
		if (enemyImg != null) {
			g.drawImage(enemyImg, x, y, null);
		} else {
			*/
			s.setFill(Color.GREEN);
			s.setX(getX());
			s.setY(getY());
			s.setWidth(getWidth());
			s.setHeight(getHeight());;
		/*
		}
		*/
	}
	@Override
	public int getRadius() {
		return 0;
	}
}