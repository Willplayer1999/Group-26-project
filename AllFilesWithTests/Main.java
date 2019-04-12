import java.util.Scanner;


/**
* Group 26 CPSC 219 Project TUT 06
* Main class that uses all other files to make the game run properly.
*/
public class Main
{
	/**
	* Single main method for running the game. It first prompts the user enter 1 or 2 to select which version they want to play, text or GUI.
	* If they input 1 they are directed to the text game, with a confirmation and an instructions message, and it runs while the variable choice is 1 by creating a new TextGame and calling its run method.
	* Then after the game ends the user is once again prompeted to select wheter they want to play again or not. If they input 1 then the game keeps running and thay can play again.
	* If they input anything else a "THANKS FOR PLAYING" message is printed, the game closes and the system exits the application.
	* If they input 2 they are directed to the GUI game, with a confirmation and an instructions message, and it runs by creating a new Gallager and calling its main method with the keyboard input as parameters.
	* It surpresses unused warnings so that the game can run properly until it uses all its functions or not depending on how the game is played. After the winning or losing conditions are met the game application is closed.
	* @param args Anything the user input on the command line.
	*/
	public static void main (String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("WELCOME TO GALLAGER, PRESS 1 TO PLAY TEXT BASED GAME OR PRESS 2 TO PLAY GUI GAME");
		int choice = in.nextInt();
		if (choice == 1)
		{
			System.out.println("YOU HAVE SELECTED THE TEXT BASED GAME");
			System.out.println("USE W A S D TO MOVE YOUR AVATAR AND DESTROY THE ENEMIES");
			while (choice == 1)
			{
				TextGame textgame = new TextGame();
				textgame.run();
				System.out.println("PRESS 1 IF YOU WISH TO PLAY THE GAME AGAIN, ANY OTHER DIGIT TO EXIT");
				choice = in.nextInt();
				if (choice != 1)
				{
					System.out.println("THANKS FOR PLAYING");
					in.close();
					System.exit(0);
				}
			}
		}
		else if (choice == 2)
		{
			System.out.println("YOU HAVE SELECTED THE GUI GAME");
			System.out.println("USE W A S D TO MOVE YOUR AVATAR AND DESTROY THE ENEMIES");
			@SuppressWarnings("unused")
			GUI gallager = new GUI();
			GUI.main(args);
			in.close();
		}
	}
}
