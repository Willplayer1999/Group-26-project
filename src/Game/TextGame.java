package Game;
import java.util.ArrayList;
import java.util.Scanner;

import Bullet.Bullet;
import Entities.Avatar;
import Entities.Enemy;
import Entities.Platforms;

/**
* Group 26 CPSC 219 Project TUT 06
* Class that extends Game so it can use its constructors and game mechanics and lays all the foundation for the text version of the game.
*/
public class TextGame extends Game
{
	public static final int ROWS = 10;
	public static final int COLUMNS = 9;
	private static final String EMPTY_SYMBOL = "-";
	private String[][] board;
	private boolean go;

	/**
	* Main constructor with no parameters that creates any empty game by calling super, then creates an Avatar at the center of the bottom row and adds it to the game by calling setAvatar in parent.
	*/
	public TextGame()
	{
		super();
		Avatar avatar = new Avatar(COLUMNS/2, ROWS - 1);
		super.setAvatar(avatar);
	}
	/**
	* Constructor that creates a text game with a given array list of platforms, one of bullets, one of enemies and an Avatar, and does so by calling the parent constructor with these parameters.
	* @param platforms The array list of platforms that'll be added to the game.
	* @param bullets The array list of bullets that'll be added to the game.
	* @param enemy The array list of enemies that'll be added to the game.
	* @param avatar The Avatar that'll be added to the game.
	*/
	public TextGame(ArrayList<Platforms> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar)
	{
		super(platforms, bullets, enemy, avatar);
	}

	/**
	* Runs the game by setting a boolean variable to true then adding several enemies and platforms to their respective lists in their designated positions
	* and while the variable is true, it will keep calling to draw and print the board, move the platforms, make the enemies and the avatar shoot, make the avatar move acording to user input and check for collisions.
	* If the player moves outside of the platforms, the method respawn is called, which resets the board and player position keep the defeated enemies dead and avatar with one less life.
	* The variable will only be set to false and make tha game stop running once avatar has no more lives.
	*/
	public void run()
	{
		go = true;
		for(int row= 1; row <8;row=row+2)
		{
			for(int col = 0; col<3; col++)
			{
				addPlatform(new Platforms(col, row));
			}
		}
		for(int row1= 1; row1 <8;row1=row1+2)
		{
			for(int col1 = 0; col1<3; col1++)
			{
				addPlatform(new Platforms(col1+6, row1+1));
			}
		}
		for(int i = 0; i < COLUMNS; i++)
		{
			addPlatform(new Platforms(i, COLUMNS));
		}
		for(int i = 0; i < COLUMNS; i++)
		{
			addPlatform(new Platforms(i, 0));
		}
		addEnemy(new Enemy(1,0));
		addEnemy(new Enemy(3,0));
		addEnemy(new Enemy(5,0));
		addEnemy(new Enemy(7,0));
		while (go)
		{
			drawBoard();
			printBoard();
			moveplatform();
			avatarShoot();
			enemyShoot();
			String input = getInput();
			move(input);
			checkCollision();
			if (overlapsWith() == false)
			{
				respawn(COLUMNS/2, ROWS - 1);
			}
			if (noLives())
			{
				System.out.println("GAME OVER!");
				getAvatar().setLives(3);
				go = false;
			}
		}
	}
	
	/**
	* Calls clearBoard to make it a blank space in which it proceeds to draw everything on. In order to draw everything it first gets the enemies list, the platforms list,
	* the bullets list and the new Avatar with each of their getter methods, then it calls draw on every element of every list and on the avatar.
	*/ void drawBoard()
	{
		clearBoard();
		ArrayList<Enemy> enemies = getEnemies();
		ArrayList<Bullet> bullets = getBullets();
		ArrayList<Platforms> platforms = getPlatforms();
		Avatar avatar = getNewAvatar();
		for (Bullet bullet : bullets)
		{
			bullet.draw(board);
		}
		for (Platforms platform : platforms)
		{
			platform.draw(board);
		}
		for (Enemy enemy : enemies)
		{
			enemy.draw(board);
		}
		avatar.draw(board);
	}

	/**
	* Suppresses the unused warnings so that everything can be printed properly, then it gets an Avatar so that it can get its lives later, then for every column in every row it prints an empty string
	* then prints a new line at the end of every row and finally prints a lives and score panel undrneath the board, getting that info by calling getLives on the Avatar and getScore from the parent class.
	*/
	public void printBoard()
	{
		@SuppressWarnings("unused")
		Avatar avatar = getAvatar();
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("Lives: " + Avatar.getLives());
		System.out.println("Score: " + super.getScore());
	}

	/**
	* Suppresses resource warning to get the input properly, then it takes whatever the the user inputted and converts it into a string with all of its characters upper-cased and returns that string.
	* @return A String that represents the upper case version of whatever the user inputted.
	*/
	public String getInput()
	{
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String commune = in.nextLine().toUpperCase();
		return commune;
	}

	/**
	* Calls the move method on parent, then gets an Avatar and calls move on it.
	* @param wasd The string that will be translated into the avatar movement.
	*/
	public void move(String wasd)
	{
		super.move();
		Avatar avatar = getAvatar();
		avatar.move(wasd);
	}

	/**
	* For every column in every row, it puts the default EMPTY_SYMBOL on it.
	*/
	public void clearBoard()
	{
		board = new String[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++)
		{
			for(int col = 0; col < COLUMNS; col++)
			{
				board[row][col] = EMPTY_SYMBOL;
			}
		}
	}

	/**
	* Executable main method that runs a text game by creating a new one and calling  run on it.
	* @param args The command lines on the command prompt.
	*/
	public static void main(String [] args)
	{
		TextGame textgame = new TextGame();
		textgame.run();
	}
}
