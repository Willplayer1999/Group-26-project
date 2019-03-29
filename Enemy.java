/*Group 26 CPSC 219 Project
 * This class is for the enemies you will face and their associated positions and statistics.
 * Current Issue: They don't fire, Not sure what to do about this.
 * Intentions for next Version: To literally have them fire and deal damage against player lives.
 * 
 */

import com.sun.javafx.geom.Shape;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy implements Collidable{
// Instance Variables
	private static final String enemySymbol = "X";
	//private Image image;
	private int enemyHealth; 
	// location of enemy
	private int x;
	private int y;
	// max X and max Y enemy can go
	private int radius = 22;
	private int maxY;
	private int maxX;
	private boolean hasFired = true;
	
	// Constructors
	//Constructor for GUI
	private Enemy(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
	//,GUIGame.SCREEN_WIDTH, GUIGame.SCREEN_HEIGHT
	}
	//Constructor for Text Based
	public Enemy(int i, int j) {
		this(i,j,0,TextGame.COLUMNS, TextGame.ROWS);
		this.x = i;
		this.y = j;
		
	}
	//Main Constructor
	private Enemy(int x, int y, int radius, int maxX, int maxY) {
		this.x = Math.min(maxX-1, Math.max(0, x));
		this.y = Math.min(maxY-1, Math.max(0, y));
		this.radius = Math.max(0, radius);
		this.hasFired = true;
		this.maxY = maxY;
		this.maxX = maxX;
	}
	// Copy Constructor
	public Enemy(Enemy e) {
		this(e.getX(), e.getY(), e.getRadius(), e.getMaxX(), e.getMaxY());
		this.hasFired = e.getHasFired();
	}

	// Getter Method
	/** public getEnemyName(){}
		public getEnemyImage(){}
	*/
	public int getRadius(){
		return radius;
	}
	
	public int getHealth(){
		return enemyHealth;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getMaxY() {
		return maxY;
	}

	public int getMaxX() {
		return maxX;
	}
	
	public boolean getHasFired(){
		return hasFired;
	}
	
	// Setter methods
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public void setEnemyHealth(int enemyHealth){
		this.enemyHealth = enemyHealth;
	}	
		
	public void setX(int x) {
		if (x < maxX && x >= 0) {
			this.x = x;
		} else if (x < 0) {
			this.x = 0;
		} else if (x >= maxX) {
			this.x = maxX - 1;
		}
	}

	public void setY(int y) {
		if (y < maxY && y >= 0) {
			this.y = y;
		} else if (y < 0) {
			this.y = 0;
		} else if (y >= maxY) {
			this.y = maxY - 1;
		}
	}
	
	public void setMaxX(){
		maxX = TextGame.COLUMNS;
	}
	
	public void setMaxY(){
		maxY = TextGame.ROWS;
	}
	
	public void setHasFired(boolean shot){
		this.hasFired = shot;
	}
	public void fire() {
			hasFired = true;
		
			
			
	}
	public Bullet shoot() {
		Bullet shot = null;
		//if (y != 0) {
			shot = new EnemyBullet(x, y);
			fire();
		//}
		return shot;
		}

	
	
	// Methods
	
	/**
	 * Right now in text base, player can't shoot. enemyhealth will not be changed
	 * not too sure how you guys want to do this
	public void updateHealth(){
		
	}
	*/
	
	public void draw(String[][] board) {
		if (board.length > y && board[0].length > x) {
			board[y][x] = new String(enemySymbol);
		}
	}
	public static Node draw(double x, double y, Pane z){
      Circle enemy = new Circle(22, Color.RED);
      enemy.setTranslateY(y);
      enemy.setTranslateX(x);
      enemy.setStroke(Color.BLACK);
      z.getChildren().add(enemy);
      return enemy;
	}
	//Gotta work on this
	public boolean collidedWith(Collidable c) {
		boolean collided = false;

		if (x + radius >= c.getX() && x <= c.getX() + c.getRadius() && 
				y + radius >= c.getY() && y <= c.getY() + c.getRadius()) {
			collided = true;
		}

		return collided;
	}
	@Override
	public int getWidth() {
		return 0;
	}
	@Override
	public int getHeight() {
		return 0;
	}
	
}
