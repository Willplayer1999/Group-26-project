/*Group 26 CPSC 219 Project
 * This is what sets everything we need to see on screen including drawing our grid, and printing it, and taking the input from the users, (allowing them to fire)
 * Current Issues: Enemies Will not shoot, Not entirely sure why, or how to fix it.
 * Intentions in next Version: We wish for the platforms to move, looping around the screen (Think like Frogger) and the enemies to actually fire and deal damage.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class TextBase extends AnimationApp {
	//Instance Variables
	static int ROW = 10;
	static int COLUMN = 7;
	private String[][] board;
	private static String emptySpace = "-";
	private boolean go;
	private int health;
	//Constructor
	public TextBase() {
		super();
		Avatar ava = new Avatar(COLUMN, ROW, health);
		health = 3;
		setAvatar(ava);
		board = initBoard();
		
	}
	//Effectively how the game runs, while Go is true, game goes. When it turns to false, game ends.
	public void go() {
		go = true;
		addEnemy(new Enemy(1, 0)); 
		addEnemy(new Enemy(3, 0)); 
		addEnemy(new Enemy(5, 0));
		setAvatar(new Avatar(3,9,3));
		avatar.setAvatarLives(3);
		for (int i = 0; i < COLUMN; i++) {
			addPlatform(new Platform(i, ROW-1));
			
		}
		
		//Loops here to allow platforms to be generated at desired locations.
		for (int x = 0; x < COLUMN; x++){
			for(int y = 1; y < ROW; y++){
				if(y %2 == 1 && x == 2){
					int a= x;
					addPlatform(new Platform(a,y));
					addPlatform(new Platform(a+1,y));
					addPlatform(new Platform(a+2,y));
				}
				else if(y%4 == 2 && x == 0){
					int b= x;
					addPlatform(new Platform(b,y));
					addPlatform(new Platform(b+1,y));
					addPlatform(new Platform(b+2,y));
				}
				else if(y % 4 == 0 && x == 4){
					int c= x;
					addPlatform(new Platform(c,y));
					addPlatform(new Platform(c+1,y));
					addPlatform(new Platform(c+2,y));	
				}
			
			}
		}	
	
	
		//Infinite loop until go goes to false.
		while (go) {
			drawBoard();
			printBoard();
			enemyShoot();
			avatarShoot();
			String input = getInput();
			move(input);

			

			
			if (overlapsWith() == false) {
				health--;
			}
			//if the user lives equal zero or lower, the user loses, end the process.
			if (health <= 0) {
				drawBoard();
				printBoard();
				System.out.println("Game Over");
				go = false;
			}
			//If all enemies die, the user wins, end the process.
			if (enemy.isEmpty()) {
				System.out.println("You Win");
				go = false;
			}
		}
	}
		
	//Allows the user to move their avatar across the board.
	public void move(String wasd) {
		Avatar avatar = getAvatar();
		avatar.move(wasd);
		}
	//Board Drawing so its drawn in the background, before being printed for the user.
	public void drawBoard() {
		clearBoard();
		ArrayList<Enemy> enemy = getEnemies();
		ArrayList<Platform> platforms = getPlatforms();
		ArrayList<Bullet> bullets = getBullets();
		Avatar avatar = getAvatar();
		
		for (Enemy enemies : enemy) {
			enemies.draw(board);
		}
		for (Platform platform : platforms) {
			platform.draw(board);
		}
		for (Bullet avatarBullet : bullets) {
			avatarBullet.draw(board);
			avatarBullet.avatarPew();
			avatarBullet.enemyPew();
	}
	
		 avatar.draw(board);
	}
	//This allows the board to be cleared so a new instance can be placed, allowing the animation effect of things "moving"
	public void clearBoard() {	
		board = new String[ROW][COLUMN];
		for(int row = 0; row < ROW; row++) {
			for(int col = 0; col < COLUMN; col++) {
				board[row][col] = emptySpace;
			}
		}
	
	}
	//Literally print the board for the users viewing pleasure, allowing them to see their lives, and the grid.
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
			
		}
		 System.out.println("Lives: " + health);
		 
	}
	public String[][] initBoard(){
		clearBoard();
		return board;
	}
	//Input from the User, Required to have them move around

	public String getInput() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String commune = in.nextLine().toUpperCase();
		return commune;
		
	}
	/*
	 * overlapsWith is to indicate if user falls off platforms, if they do, take -1 lives
	 */
	
	public boolean overlapsWith() {
		boolean alive = false;
		for (Platform platform : platforms) {
			if (getAvatar().getXCord() == platform.getX() && getAvatar().getYCord() == platform.getY()) {
				alive  = true;
			}
		}
		return alive;
	}
	
	public static void main(String[] args) {
		TextBase textgame = new TextBase();
		textgame.go();
	}
}
