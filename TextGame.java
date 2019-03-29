import java.util.ArrayList;
import java.util.Scanner;

public class TextGame extends Game {
	public static final int ROWS = 10;
	public static final int COLUMNS = 9;
	
	private static final String EMPTY_SYMBOL = "-";
	private String[][] board;
	private boolean go;
	
	public TextGame() {
		super();  
        Avatar avatar = new Avatar(COLUMNS/2, ROWS - 1);

        super.setAvatar(avatar);
        
		//board = initBoard();
	}
    
    public TextGame(ArrayList<Platforms> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar) {
        super(platforms, bullets, enemy, avatar);
    }
	
	public void run() {
		go = true;
		addEnemy(new Enemy(1,0));
		addEnemy(new Enemy(3,0));
		addEnemy(new Enemy(5,0));
		addEnemy(new Enemy(7,0));
		
//		//First                           //Index
		addPlatform(new Platforms(0, 1));//0
		addPlatform(new Platforms(1, 1));//1
		addPlatform(new Platforms(2, 1));//2
		//Second
		addPlatform(new Platforms(6, 2));//3
		addPlatform(new Platforms(7, 2));//4
		addPlatform(new Platforms(8, 2));//5
		//Third
		addPlatform(new Platforms(0, 3));//6
		addPlatform(new Platforms(1, 3));//7
		addPlatform(new Platforms(2, 3));//8
		//Fourth
		addPlatform(new Platforms(6, 4));//9
		addPlatform(new Platforms(7, 4));//10
		addPlatform(new Platforms(8, 4));//11
		//Fifth
		addPlatform(new Platforms(0, 5));//12
		addPlatform(new Platforms(1, 5));//13
		addPlatform(new Platforms(2, 5));//14
		//Sixth
		addPlatform(new Platforms(6, 6));//15
		addPlatform(new Platforms(7, 6));//16
		addPlatform(new Platforms(8, 6));//17
		//Seventh
		addPlatform(new Platforms(0, 7));//18
		addPlatform(new Platforms(1, 7));//19
		addPlatform(new Platforms(2, 7));//20
		//Eight
		addPlatform(new Platforms(6, 8));//21
		addPlatform(new Platforms(7, 8));//22
		addPlatform(new Platforms(8, 8));//23
		
		
		for(int i = 0; i < COLUMNS; i++) {
			addPlatform(new Platforms(i, COLUMNS));
		}
		
		while (go) {
			drawBoard();
			printBoard();
			moveplatform();
			avatarShoot();
			enemyShoot();
			String input = getInput();
			move(input);
			checkCollision();
			//CHAD ADDED THIS SO WE RESPAWN WHEN WE FALL OFF PLATFORM
			if (overlapsWith() == false) {
				respawn(COLUMNS/2, ROWS - 1);
			}
		
			if (noLives()) {
				System.out.println("GAME OVER!");
				go = false;
			}
		}
	}

	public void drawBoard() {
		clearBoard();
		ArrayList<Enemy> enemies = getEnemies();
		ArrayList<Bullet> bullets = getBullets();
		ArrayList<Platforms> platforms = getPlatforms();
		Avatar avatar = getNewAvatar();

		for (Enemy enemy : enemies) {
			enemy.draw(board);
		}
		for (Bullet bullet : bullets) {
			bullet.draw(board);
		}
		for (Platforms platform : platforms) {
			platform.draw(board);
		}
		avatar.draw(board);
	}

	public void printBoard() {
		Avatar avatar = getAvatar();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("Lives: " + avatar.getLives());
		System.out.println("Score: " + super.getScore());
	}

	public String getInput() {
		Scanner in = new Scanner(System.in);
		String commune = in.nextLine().toUpperCase();
		return commune;
	}

	public void move(String wasd) {
		super.move();
		Avatar avatar = getAvatar();
		avatar.move(wasd);
	}

	public void clearBoard() {
		board = new String[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				board[row][col] = EMPTY_SYMBOL;
			}
		}
	}
	public static void main(String [] args){
		TextGame textgame = new TextGame();
		textgame.run();
	}


}