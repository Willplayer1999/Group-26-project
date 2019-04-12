package Game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Entities.*;
import Bullet.*;

/**
* Group 26 CPSC 219 Project TUT 06
* GUI class controls user interface
* GUi uses methods from Game to control platform movement, shooting and user input
*/
public class GUI extends Application
{
	
	/**
	 * Instance variables
	 * Initialize different Node, lists of Objects
	 */
	
	private Pane root;
	private AnimationTimer timer;
	
	private List<Node> platforms = new ArrayList<>();
	private List<Node> staticPlatform = new ArrayList<>();
	private List<Node> backBlock = new ArrayList<>();
	private List<Node> avatarBullets = new ArrayList<>();
	private List<Node> enemyBullets = new ArrayList<>();
	
	private Node avatar;
	private Node firstEnemy;
	private Node secondEnemy;
	private Node thirdEnemy;
	private Node fourthEnemy;
	//Level 2
	private Node fifthEnemy;
	private Node sixthEnemy;
	private Node seventhEnemy;
	private Node eighthEnemy;
	private Node ninthEnemy;
	//Level 3
	private Node tenthEnemy;
	private Node eleventhEnemy;
	private Node twelfthEnemy;
	private Node thirteenthEnemy;
	private Node fourteenthEnemy;
	private Node fifteenthEnemy;
	
	private Node fEB;
	private Node siEB;
	private Node seEB;
	private Node eiEB;
	private Node niEB;
	
	private Node teEB;
	private Node elEB;
	private Node twEB;
	private Node thEB;
	private Node foEB;
	private Node fiEB;
	
	private boolean level;
	private int check = 0;
	private int theLevel = 1;
	private int levelNumber =1;
	
	private int finalScore;
	private int highScore;
	private int displayScore;

	/**
	* check can be 1 or 0
	* We use setCheck to check if Avatar can respawn or not
	*/
	public void setCheck(int ch)
	{
		this.check = ch;
	}

	//Boolean values to determine if enemies have been hit by avatar bullet or not
	boolean enemyOne = false;
	boolean enemyTwo = false;
	boolean enemyThree = false;
	boolean enemyFour = false;
	//Level 2
	boolean enemyFive = false;
	boolean enemySix = false;
	boolean enemySeven = false;
	boolean enemyEight = false;
	boolean enemyNine = false;
	//level 3
	boolean enemyTen = false;
	boolean enemyEleven = false;
	boolean enemyTwelve = false;
	boolean enemyThirteen = false;
	boolean enemyFourteen = false;
	boolean enemyFifteen = false;
	
	//Changes direction of platforms
	int changeDirectionRight = 0;
	int changeDirectionMiddle = 0;
	int changeDirectionLeft = 0;
	int changeDirectionReverseRight = 1;
	int changeDirectionReverseMiddle = 1;
	int changeDirectionReverseLeft = 1;

	

	/**
	* This method creates all entities with specific coordinates for all levels
	* return type Parent so we can add all to Scene later
	* This method contains timer to run GUI
	* topData contains live, score and level
	* back block is the background behind. We use to check if Avatar on the back block, it will lose a live and respawn
	* when bullets go to the end of the screen, avatar and enemy shoots another bullet
	* Bullet speed is increasing depends on level
	* 
	*/
	public Parent createNodes()
	{
		//Label:Live, Score, Level
		Rectangle background = new Rectangle ();
		background.setX(0);
		background.setY(0);
		background.setWidth(600);
		background.setHeight(60);
		background.setFill(Color.BLACK);
		
		HBox topData = new HBox();
		
		int pHealth = Game.getHealth();
		
		Label lives = new Label("Lives:" + pHealth);
		lives.setFont(new Font("Arial", 48));
		lives.setTextFill(Color.web("#0CFF00"));
		
		
		
		int pScore = Game.getPlayerScore();
		
		Label score = new Label("Score:" + pScore);
		score.setFont(new Font("Arial", 48));
		score.setTextFill(Color.web("#0CFF00"));
		
		Label level = new Label("Level:" + pScore);
		level.setFont(new Font("Arial", 48));
		level.setTextFill(Color.web("#0CFF00"));
		
		topData.setStyle("-fx-background-color: #000000;");
		topData.getChildren().add(lives);
		topData.getChildren().add(score);
		topData.getChildren().add(level);
		topData.setSpacing(35);
		
		root = new Pane();
		root.setPrefSize(600, 660);
		
		//Adds the background behind the platforms
		backBlock.add(createBackBlock(0,0));
		
		//Adds the static platforms
		for(int i=0; i<=560; i=i+60)
		{
			staticPlatform.add(Platforms.createStaticPlatform(i,600, root));
		}
		for(int i=0; i<=560; i=i+60)
		{
			staticPlatform.add(Platforms.createStaticPlatform(i,600-540, root));
		}
		
		//Adds Moving Platforms
		for(int row= 120; row <481;row=row+120)
		{
			for(int col = 0; col<125; col=col+62)
			{
				platforms.add(Platforms.createPlatform(col,row, root));
			}
		}
		for(int row1= 180; row1 <541;row1=row1+120)
		{
			for(int col1 = 370; col1<495; col1=col1+62)
			{
				platforms.add(Platforms.createPlatform(col1,row1, root));
			}
		}
		
		//Creates Avatar and adds Top Data
		avatar = Avatar.createAvatar();
		firstEnemy = Enemy.createEnemies(90, 90);
		secondEnemy = Enemy.createEnemies(210, 90);
		thirdEnemy = Enemy.createEnemies(390, 90);
		fourthEnemy = Enemy.createEnemies(510, 90);
		//level 2
		fifthEnemy = Enemy.createEnemies(-100,90);
		sixthEnemy = Enemy.createEnemies(-100,90);
		seventhEnemy = Enemy.createEnemies(-100,90);
		eighthEnemy = Enemy.createEnemies(-100,90);
		ninthEnemy = Enemy.createEnemies(-100, 90);
		//level 3
		tenthEnemy = Enemy.createEnemies(-200, 90);
		eleventhEnemy = Enemy.createEnemies(-200, 90);
		twelfthEnemy = Enemy.createEnemies(-200, 90);
		thirteenthEnemy = Enemy.createEnemies(-200, 90);
		fourteenthEnemy = Enemy.createEnemies(-200, 90);
		fifteenthEnemy = Enemy.createEnemies(-200, 90);
		
		
		fEB = EnemyBullet.createLevelTwoEnemyBullet(fifthEnemy.getTranslateX()-2,fifthEnemy.getTranslateY());
		siEB = EnemyBullet.createLevelTwoEnemyBullet(sixthEnemy.getTranslateX()-2,sixthEnemy.getTranslateY());
		seEB = EnemyBullet.createLevelTwoEnemyBullet(seventhEnemy.getTranslateX()-2,seventhEnemy.getTranslateY());
		eiEB = EnemyBullet.createLevelTwoEnemyBullet(eighthEnemy.getTranslateX()-2,eighthEnemy.getTranslateY());
		niEB = EnemyBullet.createLevelTwoEnemyBullet(ninthEnemy.getTranslateX()-2,ninthEnemy.getTranslateY());
		
		enemyBullets.add(fEB);
		enemyBullets.add(siEB);
		enemyBullets.add(seEB);
		enemyBullets.add(eiEB);
		enemyBullets.add(niEB);
		

		teEB = EnemyBullet.createLevelTwoEnemyBullet(tenthEnemy.getTranslateX()-2,tenthEnemy.getTranslateY());
		elEB = EnemyBullet.createLevelTwoEnemyBullet(eleventhEnemy.getTranslateX()-2,eleventhEnemy.getTranslateY());
		twEB = EnemyBullet.createLevelTwoEnemyBullet(twelfthEnemy.getTranslateX()-2,twelfthEnemy.getTranslateY());
		thEB = EnemyBullet.createLevelTwoEnemyBullet(thirteenthEnemy.getTranslateX()-2,thirteenthEnemy.getTranslateY());
		foEB = EnemyBullet.createLevelTwoEnemyBullet(fourteenthEnemy.getTranslateX()-2,fourteenthEnemy.getTranslateY());
		fiEB = EnemyBullet.createLevelTwoEnemyBullet(fifteenthEnemy.getTranslateX()-2,fifteenthEnemy.getTranslateY());
		

		enemyBullets.add(teEB);
		enemyBullets.add(elEB);
		enemyBullets.add(twEB);
		enemyBullets.add(thEB);
		enemyBullets.add(foEB);
		enemyBullets.add(fiEB);
		
		root.getChildren().add(avatar);
		root.getChildren().addAll(firstEnemy, secondEnemy, thirdEnemy, fourthEnemy);
		

		root.getChildren().add(background);
		root.getChildren().add(topData);
		
		//Adds bullets (Avatar Bullets) and Enemy Bullets
		avatarBullets.add(AvatarBullet.createAvatarBullet(avatar.getTranslateX()+28,avatar.getTranslateY(), root));
		enemyBullets.add(EnemyBullet.createEnemyBullet(firstEnemy.getTranslateX()-2,firstEnemy.getTranslateY(), root));
		enemyBullets.add(EnemyBullet.createEnemyBullet(secondEnemy.getTranslateX()-2,secondEnemy.getTranslateY(), root));
		enemyBullets.add(EnemyBullet.createEnemyBullet(thirdEnemy.getTranslateX()-2,thirdEnemy.getTranslateY(), root));
		enemyBullets.add(EnemyBullet.createEnemyBullet(fourthEnemy.getTranslateX()-2,fourthEnemy.getTranslateY(), root));
		//Adds enemies
		//TIMER
		//I don't understand how a method inside a method works and have no idea how to javadoc this method.
		timer = new AnimationTimer()
		{
			@Override
			public void handle(long go)
			{
				//If Avatar falls off the platform, check becomes = 1 and it respawns
				if (check==1)
				{
					respawn();
				}
				//Makes Bullets (Avatar Bullets) disappear if they reach 60px
				for (Node avatarBullet : avatarBullets)
				{
					if(avatarBullet.getTranslateY() < 60)
					{
						avatarBullets.remove(0);
						root.getChildren().remove(avatarBullet);
						avatarBullets.add(AvatarBullet.createAvatarBullet(avatar.getTranslateX(),avatar.getTranslateY(), root));
					}
				}
				//Makes Enemy Bullets disappear if they reach 550px
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					//If enemyOne is false, avatar bullet has not collided with enemy one and so the old bullet is removed and new one is created
					if(enemyOne == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(firstEnemy.getTranslateX()-2,firstEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyTwo == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(secondEnemy.getTranslateX()-2,secondEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyThree == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(thirdEnemy.getTranslateX()-2,thirdEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyFour == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(fourthEnemy.getTranslateX()-2,fourthEnemy.getTranslateY(), root));
					}
				}
				//
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyFive == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(fifthEnemy.getTranslateX()-2,fifthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemySix == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(sixthEnemy.getTranslateX()-2,sixthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemySeven == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(seventhEnemy.getTranslateX()-2,seventhEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyEight == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(eighthEnemy.getTranslateX()-2,eighthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyNine == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(ninthEnemy.getTranslateX()-2,ninthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyTen == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(tenthEnemy.getTranslateX()-2,tenthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyEleven == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(eleventhEnemy.getTranslateX()-2,eleventhEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyTwelve == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(twelfthEnemy.getTranslateX()-2,twelfthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyThirteen == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(thirteenthEnemy.getTranslateX()-2,thirteenthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyFourteen == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(fourteenthEnemy.getTranslateX()-2,fourteenthEnemy.getTranslateY(), root));
					}
				}
				if(enemyBullets.get(0).getTranslateY()>750)
				{
					if(enemyFifteen == false)
					{
						root.getChildren().remove(enemyBullets.get(0));
						enemyBullets.remove(0);
						enemyBullets.add(EnemyBullet.createEnemyBullet(fifteenthEnemy.getTranslateX()-2,fifteenthEnemy.getTranslateY(), root));
					}
				}
				
				if((Game.checkEnemyBulletCollision(enemyBullets, avatar, staticPlatform, platforms))== true)
				{
					respawn();
				}
				Game.checkCollisionGUI(platforms, avatar, firstEnemy, secondEnemy, thirdEnemy, fourthEnemy, staticPlatform);
				
				movePlatform(1);
				collisionTimer();
				Game.checkAvatarBulletCollision(avatarBullets, firstEnemy, secondEnemy, thirdEnemy, fourthEnemy, fifthEnemy, sixthEnemy, seventhEnemy, eighthEnemy, ninthEnemy, tenthEnemy, eleventhEnemy, twelfthEnemy, thirteenthEnemy, fourteenthEnemy, fifteenthEnemy, root);
				checkDeath();
				if(avatar.getTranslateY()<600)
				{
					AvatarBullet.moveBullet(avatarBullets); //Avatar Bullet
				}
				else
				{
					for (Node avatarBullet : avatarBullets)
					{
						avatarBullets.remove(0);
						root.getChildren().remove(avatarBullet);
						avatarBullets.add(AvatarBullet.createAvatarBullet(avatar.getTranslateX()-2,avatar.getTranslateY()-5, root));
					}
				}
				
				lives.setText("Lives:" + Game.getHealth());
				score.setText("Score:" + Game.getPlayerScore());
				int forLevel = Game.getPlayerScore();
				if(Game.getPlayerScore()==4) {
					setLevelNumber(2);
				}
				if(Game.getPlayerScore()==10) {
					setLevelNumber(3);
				}
				
				level.setText("Level:" + levelNumber);
				EnemyBullet.moveEnemyBullet(enemyBullets, 1);
				movePlatform(1);
				
				// Increase Bullet speed when level increases
				if(levelNumber == 1) {
					movePlatform(1);
					EnemyBullet.moveEnemyBullet(enemyBullets, 1);
				}
				if(levelNumber == 2) {
					movePlatform(1);
					EnemyBullet.moveEnemyBullet(enemyBullets, 4);
				}
				if(levelNumber == 3) {
					movePlatform(1);
					EnemyBullet.moveEnemyBullet(enemyBullets, 8);
				}
			}
		};
		timer.start();
		return root;
	}
		

	/**
	*
	*/
	//Creates the Back Block
    private Node createBackBlock(int x, int y) 
    {
    	Rectangle rect = new Rectangle(600, 600);
    	Image img = new Image("file:Images/back.gif");
    	rect.setFill(new ImagePattern(img));
        rect.setStroke(Color.BLACK);
        rect.setOpacity(0.5);
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        root.getChildren().add(rect);
        return rect;
    }

    /**
	* This method is used to move platforms
	* Each square is one platform in the list
	* We use index and coordinates to control direction
	* There are 3 platforms in 1 line, they move sideways until it reaches the walls
	*/
	private void movePlatform(int speed)
	{
		for (Node platform : platforms)
		{
			//PLATFORMS GOING FROM LEFT TO RIGHT
			//3RD LTR
			if (platforms.indexOf(platform)==2 || platforms.indexOf(platform)==5 || platforms.indexOf(platform)==8 || platforms.indexOf(platform)==11)
			{ //<= 3 for the platforms starting Left to right
				if (platform.getTranslateX() == 600-70)
				{
					changeDirectionRight = 1;
				}
				if (platform.getTranslateX() == 124)
				{
					changeDirectionRight = 0;
				}
				if (changeDirectionRight==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionRight==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
			//2ND LTR
			if (platforms.indexOf(platform)==1 || platforms.indexOf(platform)==4 || platforms.indexOf(platform)==7 || platforms.indexOf(platform)==10)
			{
				//<= 3 for the platforms starting Left to right
				if (platform.getTranslateX() == 600-132)
				{
					changeDirectionMiddle = 1;
				}
				if (platform.getTranslateX() == 62)
				{
					changeDirectionMiddle = 0;
				}
				if (changeDirectionMiddle==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionMiddle==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
			//1ST LTR
			if (platforms.indexOf(platform)==0 || platforms.indexOf(platform)==3 || platforms.indexOf(platform)==6 || platforms.indexOf(platform)==9)
			{
				//<= 3 for the platforms starting Left to right
				if (platform.getTranslateX() == 600-194)
				{
					changeDirectionLeft = 1;
				}
				if (platform.getTranslateX() == 0)
				{
					changeDirectionLeft = 0;
				}
				if (changeDirectionLeft==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionLeft==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
			///PLATFORMS GOING FROM RIGHT TO LEFT
			//3RD RTL
			if (platforms.indexOf(platform)==14 || platforms.indexOf(platform)==17 || platforms.indexOf(platform)==20 || platforms.indexOf(platform)==23)
			{
				//> 3 for the platforms starting Right to Left
				if (platform.getTranslateX() == 600-70)
				{
					changeDirectionReverseRight = 1;
				}
				if (platform.getTranslateX() == 124)
				{
					changeDirectionReverseRight = 0;
				}
				if (changeDirectionReverseRight==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionReverseRight==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
			//2ND RTL
			if (platforms.indexOf(platform)==13 || platforms.indexOf(platform)==16 || platforms.indexOf(platform)==19 || platforms.indexOf(platform)==22)
			{ //> 3 for the platforms starting Right to Left
				if (platform.getTranslateX() == 600-132)
				{
					changeDirectionReverseMiddle = 1;
				}
				if (platform.getTranslateX() == 62)
				{
					changeDirectionReverseMiddle = 0;
				}
				if (changeDirectionReverseMiddle==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionReverseMiddle==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
			//1ST RTL
			if (platforms.indexOf(platform)==12 || platforms.indexOf(platform)==15 || platforms.indexOf(platform)==18 || platforms.indexOf(platform)==21)
			{
				//> 3 for the platforms starting Right to Left
				if (platform.getTranslateX() == 600-194)
				{
					changeDirectionReverseLeft = 1;
				}
				if (platform.getTranslateX() == 0)
				{
					changeDirectionReverseLeft = 0;
				}
				if (changeDirectionReverseLeft==0)
				{
					platform.setTranslateX(platform.getTranslateX()+speed);
				}
				if(changeDirectionReverseLeft==1)
				{
					platform.setTranslateX(platform.getTranslateX()-speed);
				}
			}
		}
	}
	/**
	* This method start timer when return true
	* and stop the timer when player lose, return false and show GameOver screen or You Win screen
	* when player kills all enemies and reaches certain scores,
	* level increase and more enemies appear
	*/
	public boolean collisionTimer()
	{

		if (Game.getCollision() == true)
		{
			timer.start();
			if (Game.getPlayerScore()== 4)
    		{
				
    			respawn();
    			Game.setHealth(Game.getHealth()+1);
    			try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
				fifthEnemy.setTranslateX(90);
				root.getChildren().add(fEB);
    			root.getChildren().addAll(fifthEnemy);
    			
    			sixthEnemy.setTranslateX(210);
				root.getChildren().add(siEB);
    			root.getChildren().addAll(sixthEnemy);
    			
    			seventhEnemy.setTranslateX(330);
				root.getChildren().add(seEB);
    			root.getChildren().addAll(seventhEnemy);
    			
    			eighthEnemy.setTranslateX(450);
				root.getChildren().add(eiEB);
    			root.getChildren().addAll(eighthEnemy);
    			
    			ninthEnemy.setTranslateX(570);
				root.getChildren().add(niEB);
    			root.getChildren().addAll(ninthEnemy);
    			

    			
    			Game.setPlayerScore(5);
    		}
			if (Game.getPlayerScore()== 10)
    		{
				
    			respawn();
    			Game.setHealth(Game.getHealth()+1);
    			
    			tenthEnemy.setTranslateX(30);
				root.getChildren().add(teEB);
    			root.getChildren().addAll(tenthEnemy);
    			
    			eleventhEnemy.setTranslateX(150);
				root.getChildren().add(elEB);
    			root.getChildren().addAll(eleventhEnemy);
    			
    			twelfthEnemy.setTranslateX(270);
				root.getChildren().add(twEB);
    			root.getChildren().addAll(twelfthEnemy);
    			
    			thirteenthEnemy.setTranslateX(330);
				root.getChildren().add(thEB);
    			root.getChildren().addAll(thirteenthEnemy);
    			
    			fourteenthEnemy.setTranslateX(450);
				root.getChildren().add(foEB);
    			root.getChildren().addAll(fourteenthEnemy);
    			
    			fifteenthEnemy.setTranslateX(570);
				root.getChildren().add(fiEB);
    			root.getChildren().addAll(fifteenthEnemy);
    			
    			Game.setPlayerScore(11);
    			
    		}
			
			return true;
		}
		else
		{
			if(Game.getHealth()==0) {
				gameOver(" GAME OVER!");
			}
			if(Game.getPlayerScore()== 17) {
				gameOver("   YOU WIN!");
			}
		
				return false;
		}
	}

	/**
	* This method is used to check collision between player and platforms
	* If player falls off platforms and on back block, they lose a live and setCheck to 0.
	* We use setCheck to respawn
	*/
	public void checkDeath()
	{
		for (Node back : backBlock)
		{
			for (Node platform : platforms)
			{
				for (Node mainPlat : staticPlatform)
				{
					if(mainPlat.getBoundsInParent().intersects(avatar.getBoundsInParent()))
					{
						back.setOpacity(0.3);
						avatar.setTranslateX(avatar.getTranslateX());
						setCheck(0);
						return;
					}
					else if(platform.getBoundsInParent().intersects(avatar.getBoundsInParent()))
					{
						back.setOpacity(0.3);
						avatar.setTranslateX(avatar.getTranslateX());
						setCheck(0);
						return;
					}
					else if(back.getBoundsInParent().intersects(avatar.getBoundsInParent()))
					{
						setCheck(1);
					}
					else
					{
						back.setOpacity(1);
						setCheck(0);
					}
				}
			}
		}
	}


	/**
	* This method is place Avatar in inital position and lose a live
	* respawn is called when avatar falls off platforms or get shot by enemyBullet
	*/
	public void respawn()
	{
		avatar.setTranslateX(0);
		avatar.setTranslateY(660-30);
		Game.setHealth(Game.getHealth()-1);
	}

	/**
	* This method draws GameOver or YouWin screen when live equals 0 or player kills all the enemies
	* The screen will show the highest score form the file and their score
	* Player can press X to exit the game
	*/
	
	public void gameOver(String displayMessage) {
		finalScore = Game.getPlayerScore();

		try {
			FileReader reader = new FileReader("HighScore.txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			highScore = Integer.parseInt(line);
			buffReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find high score. CANNOT FIND FILE");
		} catch (IOException e) {
			System.out.println("Error reading high score");
		}

		if (finalScore > highScore) {
			displayScore = finalScore;
			try {
				FileWriter writer = new FileWriter("HighScore.txt", false);
				BufferedWriter buffWriter = new BufferedWriter(writer);
				buffWriter.write(Integer.toString(finalScore));
				buffWriter.close();
			} catch (IOException e) {
				System.out.println("Could not record high score. IO");
			}
		} else {
			displayScore = highScore;
		}
		Rectangle box = new Rectangle ();
		box.setX(0);
		box.setY(0);
		box.setWidth(600);
		box.setHeight(600);
		box.setFill(Color.BLACK);

		int pScore = Game.getPlayerScore();
		Text score = new Text("   Score:" + pScore + "     " + "HighScore:" + displayScore);
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
	    score.setFill(Color.WHITESMOKE);
		
		Text over = new Text(displayMessage);
        over.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 80));
        over.setFill(Color.WHITESMOKE);
       
		Text exit = new Text("   Press X to exit. ");
        exit.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        exit.setFill(Color.WHITESMOKE);
         
        HBox dataScore = new HBox();
        dataScore.getChildren().addAll(score);
        dataScore.setAlignment(Pos.CENTER);
        
		HBox data = new HBox();
		data.getChildren().addAll(exit);
		data.setAlignment(Pos.CENTER);
        
        VBox layoutGameOver = new VBox();
        layoutGameOver.getChildren().addAll(dataScore, over, data);
        layoutGameOver.setSpacing(100);
        
		root.getChildren().add(box);
		root.getChildren().add(layoutGameOver);
	}

	/**
	* This method controls what is on the start screen. Allow player to press button play or quit
	* When player press Play button, the screen will switch to scene of Level 1, Level 2 and Level 3
	* If player is in playing scene, they have to press key WASD to move avatar
	* or player can press X to exit the game anytime
	*/
	@Override
	public void start(Stage stage) throws Exception
	{
		//Setting title to the Stage
		Stage window = stage;
		window.setTitle("G A L L A G E R");
		window.getIcons().add(new Image("file:Images/back.gif"));
		window.setY(0);
		// Start Scene
		BorderPane root = new BorderPane();
		Text name = new Text("GALLAGER");
		name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
		name.setFill(Color.WHITESMOKE);
		Text copyright = new Text(" @Copyright: Group26-CPSC 219-W2019-UCALGARY-Calgary,Alberta \n");
		copyright.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 15));
		copyright.setFill(Color.WHITESMOKE);
		Button playButton = new Button("PLAY");
		playButton.setMaxSize(300,100);
		String styles = "-fx-background-color: #ffffff;" + "-fx-font-size: 3em; ";
		playButton.setStyle(styles);
		Button quitButton = new Button("QUIT");
		quitButton.setMaxSize(300,100);
		quitButton.setStyle(styles);
		VBox layout = new VBox();
		layout.setSpacing(50);
		layout.getChildren().addAll(playButton, quitButton);
		layout.setAlignment(Pos.CENTER);
		
		Image img = new Image("file:Images/back.gif");
		root.setBackground(new Background(new BackgroundFill(new ImagePattern(img), new CornerRadii(2), new Insets(2))));

		root.setTop(name);
		root.setCenter(layout);
		root.setBottom(copyright);
		Scene startScene = new Scene(root, 600, 600);
			
		// Scene for level 1
		Scene sceneL1 = new Scene(createNodes());
		sceneL1.setOnKeyPressed(e ->
		{
            switch (e.getCode()) {
            
            case S:
            	if(avatar.getTranslateY()<600 && avatar.getTranslateY()>0) {
            		avatar.setTranslateY(avatar.getTranslateY() + 57);
            	}
                break;
            case W:
            	if(avatar.getTranslateY()<660 && avatar.getTranslateY()>0) {
            		avatar.setTranslateY(avatar.getTranslateY() - 57);
            	}
                break;
            case A:
            	if(avatar.getTranslateX()<600 && avatar.getTranslateX()>60) {
            		avatar.setTranslateX(avatar.getTranslateX() - 57);
            	}
                break;
            case D:
            	if(avatar.getTranslateX()<540 && avatar.getTranslateX()>0) {
            		avatar.setTranslateX(avatar.getTranslateX() + 57);
            	}
                break;
            case X:
            	window.close();
                break;
           
            default : break;
            }
        });
		
		// Changing scene
		playButton.setOnAction(e -> window.setScene(sceneL1));
		quitButton.setOnAction(e -> Platform.exit());
		window.setScene(startScene);
		window.setFullScreen(true);
		window.setFullScreen(false);
		window.show();
	}

	/**
	* Main method to launch GUI
	* This method is called in Main class when player choose option to play GUI game
	*/
	public static void main(String[] args)
	{
		launch(args);
	}


	public int getLevelNumber() {
		return levelNumber;
	}


	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

}