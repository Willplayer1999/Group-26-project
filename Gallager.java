import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;//Imported for enemies which are polygons
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;


public class Gallager extends Application {

    private Pane root;
    private AnimationTimer timer;

    private List<Node> platforms = new ArrayList<>();
    private List<Node> staticPlatform = new ArrayList<>();
    private List<Node> backBlock = new ArrayList<>();
    private List<Node> enemies = new ArrayList<>(); 
    private List<Node> bullets = new ArrayList<>();
    private List<Node> enemyBullets = new ArrayList<>();
    private Node avatar;
    
    private int check = 0;
    public void setCheck(int ch) {
    	this.check = ch;
    }
	boolean collided = false;
	
//Boolean values to determine if enemies have been hit by avatar bullet or not
	boolean enemyOne = false;
	boolean enemyTwo = false;
	boolean enemyThree = false;
	boolean enemyFour = false;
//Set methods that set the state of whether enemy has been hit by avatar bullet or not
	public void setEnemyOne(boolean state) {
		this.enemyOne = state;
	}
	public void setEnemyTwo(boolean state) {
		this.enemyTwo = state;
	}
	public void setEnemyThree(boolean state) {
		this.enemyThree = state;
	}
	public void setEnemyFour(boolean state) {
		this.enemyFour = state;
	}
//Changes direction of platforms	
	int changeDirectionRight = 0;
	int changeDirectionMiddle = 0;
	int changeDirectionLeft = 0;

	int changeDirectionReverseRight = 1;
	int changeDirectionReverseMiddle = 1;
	int changeDirectionReverseLeft = 1;
//CHAD'S CODE FOR THE UPPER SECTION
		private int health;
		private int theLevel;
		private int playerScore;
		
		
		public Gallager() {
			health = 3;
			theLevel = 1;
			playerScore = 0;
		}
		
		public int getHealth() {
			return health;
		}
		public void setHealth(int health) {
			this.health = health;
		}
		public int getLevel() {
			return theLevel;
		}
		public void setLevel(int level) {
			this.theLevel = level;
		}
		public int getScore() {
			return playerScore;
		}
		public void setScore(int score) {
			this.playerScore = score;
		}

		public Parent createNodes() {	    	
	    	
	    	//Label:Live, Score, Level
			Rectangle background = new Rectangle ();
			background.setX(0);
			background.setY(0);
			background.setWidth(600);
			background.setHeight(60);
			background.setFill(Color.BLACK);
	    	HBox topData = new HBox();
	        Label lives = new Label("Lives:" + getHealth());
	        lives.setFont(new Font("Arial", 48));
	        lives.setTextFill(Color.web("#0CFF00"));
	        Label level = new Label("Level:" + getLevel());
	        level.setFont(new Font("Arial", 48));
	        level.setTextFill(Color.web("#0CFF00"));
	        Label score = new Label("Score:" + getScore());
	        score.setFont(new Font("Arial", 48));
	        score.setTextFill(Color.web("#0CFF00"));
	        topData.setStyle("-fx-background-color: #000000;");
	        topData.getChildren().add(lives);
	        topData.getChildren().add(score);
	        topData.getChildren().add(level);
	        topData.setSpacing(40);
	        

	        root = new Pane();
	        root.setPrefSize(600, 600);

//Adds the background behind the platforms
         backBlock.add(createBackBlock(0,0));

//Adds the static platforms
        for(int i=0; i<=560; i=i+60) {
        	staticPlatform.add(createStaticPlatform(i,600-60));
        }
        
        for(int i=0; i<=560; i=i+60) {
        	staticPlatform.add(createStaticPlatform(i,600-540));
        }

//Adds the Platforms that go from Left To Right
        ///PLATFORM 1
        platforms.add(createPlatform(0,600-480));
        platforms.add(createPlatform(62,600-480));
        platforms.add(createPlatform(124,600-480));
        ///PLATFORM 2
        platforms.add(createPlatform(0,600-360));
        platforms.add(createPlatform(62,600-360));
        platforms.add(createPlatform(124,600-360));
        ///PLATFORM 3
    	platforms.add(createPlatform(0,600-240));
        platforms.add(createPlatform(62,600-240));
        platforms.add(createPlatform(124,600-240));
        ///PLATFORM 4
    	platforms.add(createPlatform(0,600-120));
    	platforms.add(createPlatform(62,600-120));
    	platforms.add(createPlatform(124,600-120));

//Adds the Platforms that go from Right To Left
    	//PLATFORM 5
        platforms.add(createPlatform(370,600-420));
        platforms.add(createPlatform(432,600-420));
        platforms.add(createPlatform(494,600-420)); //14

        ///PLATFORM 6
        platforms.add(createPlatform(370,600-300));
        platforms.add(createPlatform(432,600-300));
        platforms.add(createPlatform(494,600-300)); //17

        ///PLATFORM 7
        platforms.add(createPlatform(370,600-180));
        platforms.add(createPlatform(432,600-180));
        platforms.add(createPlatform(494,600-180)); //20

//Creates Avatar and adds Top Data
        avatar = createAvatar();
        root.getChildren().add(avatar);
        root.getChildren().add(background);
        root.getChildren().add(topData);
        
    	
//Adds bullets (Avatar Bullets) and Enemy Bullets        
        bullets.add(createAvatarBullet(avatar.getTranslateX()+28,avatar.getTranslateY()));

		enemyBullets.add(createEnemyBullet(82,82));
		enemyBullets.add(createEnemyBullet(207,82));
		enemyBullets.add(createEnemyBullet(382,82));
		enemyBullets.add(createEnemyBullet(500,82));
        
//Adds enemies 
        enemies.add(createEnemies(30,37,70,37,50,67));
        enemies.add(createEnemies(155,37,195,37,175,67));
        enemies.add(createEnemies(155+175,37,195+175,37,175+175,67));
        enemies.add(createEnemies(155+295,37,195+295,37,175+295,67));

//TIMER
        timer = new AnimationTimer() {
            @Override
            public void handle(long go) {
//If Avatar falls off the platform, check becomes = 1 and it respawns
            	if (check==1) {
            		respawn();
            	}
//Makes Bullets (Avatar Bullets) disappear if they reach 60px
            	for (Node bullet : bullets) {
                	if(bullet.getTranslateY() < 60) {
                		bullets.remove(0);
                		root.getChildren().remove(bullet);
                		bullets.add(createAvatarBullet(avatar.getTranslateX()-2,avatar.getTranslateY()));             
                	}
            	}
//Makes Enemy Bullets disappear if they reach 550px
        	
        		if(enemyBullets.get(0).getTranslateY()>550) {
//If enemyOne is false, avatar bullet has not collided with enemy one and so the old bullet is removed and new one is created            			
	    			if(enemyOne == false) {
	    				root.getChildren().remove(enemyBullets.get(0));
	    				enemyBullets.remove(0);	
	    				enemyBullets.add(createEnemyBullet(82,82));
	    			}
	    			if(enemyTwo == false) {
	    				root.getChildren().remove(enemyBullets.get(0));
	    				enemyBullets.remove(0);	
	                	enemyBullets.add(createEnemyBullet(207,82));
	    			}
	    			if(enemyThree == false) {
	    				root.getChildren().remove(enemyBullets.get(0));
	    				enemyBullets.remove(0);	
	    				enemyBullets.add(createEnemyBullet(382,82));
	    			}
	    			if(enemyFour == false) {
	    				root.getChildren().remove(enemyBullets.get(0));
	    				enemyBullets.remove(0);	
	                	enemyBullets.add(createEnemyBullet(500,82));
	    			}
				}
        	
            	
            	movePlatform();
            	checkCollision();
            	checkAvatarBulletCollision();
            	checkEnemyBulletCollision();
            	checkDeath();
            	moveBullet(); //Avatar Bullet
            	moveEnemyBullet();
            	lives.setText("Lives:" + getHealth());
            	score.setText("Score:" + playerScore);
            	

            }
        };
        timer.start();
        return root;

    }

//Creates Enemies as polygon shapes		
        private Node createEnemies(double x, double a, double y, double b, double z, double c)
        {
            Polygon enemie = new Polygon();
            enemie.setTranslateX(a);
            enemie.setTranslateY(b);
            enemie.setTranslateZ(c);
            
            enemie.getPoints().addAll(new Double[]{x, a, y, b, z, c});

            root.getChildren().add(enemie);
            enemie.setFill(Color.RED);
            enemie.setStroke(Color.BLACK);
            return enemie;
        }

//Creates Avatar as a Circle

    private Node createAvatar() {
        Circle circle = new Circle(22, Color.BLACK);
        circle.setTranslateY(600 - 29);
        circle.setTranslateX(0);
        return circle;
    }

//Creates Avatar Bullet
    public Node createAvatarBullet(double d, double e){
    	Rectangle bullet = new Rectangle (5, 20, Color.BLACK);
        bullet.setTranslateX(d);
        bullet.setTranslateY(e);
        
        root.getChildren().add(bullet);
        return bullet;
    }
//Moves bullet (Avatar Bullet)  by 5px upwards  
    private void moveBullet() {
        for (Node bullet : bullets) {
        	bullet.setTranslateY(bullet.getTranslateY()-5);        	
        }
    }
    
//Creates Enemy Bullet
    public Node createEnemyBullet(double f, double g){
    	Rectangle enemyBullet = new Rectangle (5, 20, Color.BLACK);
        enemyBullet.setTranslateX(f);
        enemyBullet.setTranslateY(g);
        
        root.getChildren().add(enemyBullet);
        return enemyBullet;
    }
//Moves Enemy Bullet by 2px downwards
    private void moveEnemyBullet() {
        for (Node enemyBullet : enemyBullets) {
        	enemyBullet.setTranslateY(enemyBullet.getTranslateY()+2);        	
        }
    }

//Creates the Back Block
    private Node createBackBlock(int x, int y) {
    	Rectangle rect = new Rectangle(600, 600);

        rect.setFill(Color.SKYBLUE);
        rect.setStroke(Color.BLACK);
        rect.setOpacity(0.5);

        rect.setTranslateX(x);
        rect.setTranslateY(y);

        root.getChildren().add(rect);
        return rect;
    }
    
//Creates the moving platforms
    private Node createPlatform(int x, int y) {
    	Rectangle rect = new Rectangle(60, 58, Color.GREEN);
        rect.setTranslateX(x);
        rect.setTranslateY(y);

        root.getChildren().add(rect);
        return rect;
    }
    
//Creates the Static Platform 
    private Node createStaticPlatform(int x, int y) {
    	Rectangle rect = new Rectangle(58, 58);

        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        rect.setTranslateX(x);
        rect.setTranslateY(y);

        root.getChildren().add(rect);
        return rect;
    }

//Makes the moving platform, move
    private void movePlatform() {
        for (Node platform : platforms) {
        	//PLATFORMS GOING FROM LEFT TO RIGHT
        	//3RD LTR
        	if (platforms.indexOf(platform)==2 || platforms.indexOf(platform)==5 || platforms.indexOf(platform)==8 || platforms.indexOf(platform)==11) { //<= 3 for the platforms starting Left to right
        	if (platform.getTranslateX() == 600-70) {
        		changeDirectionRight = 1;
        	}
        	if (platform.getTranslateX() == 124) {
        		changeDirectionRight = 0;
        	}
        	if (changeDirectionRight==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionRight==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	//2ND LTR
        	if (platforms.indexOf(platform)==1 || platforms.indexOf(platform)==4 || platforms.indexOf(platform)==7 || platforms.indexOf(platform)==10) { //<= 3 for the platforms starting Left to right
        	if (platform.getTranslateX() == 600-132) {
        		changeDirectionMiddle = 1;
        	}
        	if (platform.getTranslateX() == 62) {
        		changeDirectionMiddle = 0;
        	}
        	if (changeDirectionMiddle==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionMiddle==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	//1ST LTR
        	if (platforms.indexOf(platform)==0 || platforms.indexOf(platform)==3 || platforms.indexOf(platform)==6 || platforms.indexOf(platform)==9) { //<= 3 for the platforms starting Left to right
        	if (platform.getTranslateX() == 600-194) {
        		changeDirectionLeft = 1;
        	}
        	if (platform.getTranslateX() == 0) {
        		changeDirectionLeft = 0;
        	}
        	if (changeDirectionLeft==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionLeft==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	///PLATFORMS GOING FROM RIGHT TO LEFT
        	//3RD RTL
        	if (platforms.indexOf(platform)==14 || platforms.indexOf(platform)==17 || platforms.indexOf(platform)==20) { //> 3 for the platforms starting Right to Left
        	if (platform.getTranslateX() == 600-70) {
        		changeDirectionReverseRight = 1;
        	}
        	if (platform.getTranslateX() == 124) {
        		changeDirectionReverseRight = 0;
        	}
        	if (changeDirectionReverseRight==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionReverseRight==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	//2ND RTL
        	if (platforms.indexOf(platform)==13 || platforms.indexOf(platform)==16 || platforms.indexOf(platform)==19) { //> 3 for the platforms starting Right to Left
        	if (platform.getTranslateX() == 600-132) {
        		changeDirectionReverseMiddle = 1;
        	}
        	if (platform.getTranslateX() == 62) {
        		changeDirectionReverseMiddle = 0;
        	}
        	if (changeDirectionReverseMiddle==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionReverseMiddle==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	//1ST RTL
        	if (platforms.indexOf(platform)==12 || platforms.indexOf(platform)==15 || platforms.indexOf(platform)==18) { //> 3 for the platforms starting Right to Left
        	if (platform.getTranslateX() == 600-194) {
        		changeDirectionReverseLeft = 1;
        	}
        	if (platform.getTranslateX() == 0) {
        		changeDirectionReverseLeft = 0;
        	}
        	if (changeDirectionReverseLeft==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionReverseLeft==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        }

    }
//Checks collision between platform and avatar
    public void checkCollision() {
        for (Node platform : platforms) {
            if (platform.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
					collided = true;
					platform.setOpacity(0.5);
					avatar.setTranslateX(platform.getTranslateX()+30);
					avatar.setTranslateY(platform.getTranslateY()+29);

            }
            //If Platform and Avatar don't collide, the opacity is full            
            else {
            	platform.setOpacity(1.0);
            }
        }

        for (Node staticPlat : staticPlatform) {
            if (staticPlat.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
            	staticPlat.setOpacity(0.5);

                avatar.setTranslateX(staticPlat.getTranslateX()+30);
                avatar.setTranslateY(staticPlat.getTranslateY()+29);
                timer.start();
            }
            else {
            	staticPlat.setOpacity(1.0);
            }
        }
//IF AVATAR REACHES TOP, TIMER STOPS        
        if (avatar.getTranslateY() <= 120) {
            timer.stop();
        }
    }

//checks collision between avatar backblock and static platform to determine death if it falls off platform
    public void checkDeath() {
        for (Node back : backBlock) {
        	for (Node platform : platforms) {
        		for (Node mainPlat : staticPlatform) {

        			if(mainPlat.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
        				back.setOpacity(0.3);
        				avatar.setTranslateX(avatar.getTranslateX());
        				setCheck(0);
    	            	return;

                	}
                	else if(platform.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
        	           	back.setOpacity(0.3);
        	           	avatar.setTranslateX(avatar.getTranslateX());
        	           	setCheck(0);
        	           	return;
                	}
                	else if(back.getBoundsInParent().intersects(avatar.getBoundsInParent())){
                		setCheck(1);
                	}
                	else {
                		back.setOpacity(1);
                		setCheck(0);
                	}
        		}
        	}
        }
    }
//Places Avatar in initial position
    public void respawn() {
		avatar.setTranslateX(0);
		avatar.setTranslateY(600-30);
		setHealth(health-1);
    }
    
//Check collision between Avatar bullet and Enemy
    public void checkAvatarBulletCollision() {
        for (Node mainStat : staticPlatform) {
        	for(Node bullet : bullets) {
        		//If it is the upper static platform AND the X value of the platform is 60 (where enemy one is..)
        		if(mainStat.getTranslateY()<300 && mainStat.getTranslateX()== 60) {
              		 if (mainStat.getBoundsInParent().intersects(bullet.getBoundsInParent())) { 		
         				//remove enemy
              			 root.getChildren().remove(enemies.get(0));
            			setEnemyOne(true);
            			playerScore++;

              		 }
        		}
        		if(mainStat.getTranslateY()<300 && mainStat.getTranslateX()==180) {
             		 if (mainStat.getBoundsInParent().intersects(bullet.getBoundsInParent())) { 	
         				root.getChildren().remove(enemies.get(1));
            			setEnemyTwo(true);
            			playerScore++;
            			

              		 }
        		}
        		if(mainStat.getTranslateY()<300 && mainStat.getTranslateX()==360) {
             		 if (mainStat.getBoundsInParent().intersects(bullet.getBoundsInParent())) { 		
        				root.getChildren().remove(enemies.get(2));
        				setEnemyThree(true);
        				playerScore++;

             		 }
	       		}
	       		if(mainStat.getTranslateY()<300 && mainStat.getTranslateX()==480) {
            		 if (mainStat.getBoundsInParent().intersects(bullet.getBoundsInParent())) { 	
        				root.getChildren().remove(enemies.get(3));
        				setEnemyFour(true);
        				playerScore++;

            		 }
	       		}
        	}
        }
    }
  //Check collision between Enemy bullet and Avatar
    public void checkEnemyBulletCollision() {
    	for(Node enemyBullet : enemyBullets) {        		
	  		 if (enemyBullet.getBoundsInParent().intersects(avatar.getBoundsInParent())) { 		
				respawn();
	  		 }
    	}
    }
	  		   


    @Override
    public void start(Stage stage) throws Exception {
        //Setting title to the Stage
        stage.setTitle("G A L L A G E R");
        stage.setScene(new Scene(createNodes()));

        stage.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case S:
                avatar.setTranslateY(avatar.getTranslateY() + 57);
               
                break;
            case W:
            	avatar.setTranslateY(avatar.getTranslateY() - 57);
                break;
            case A:
           		avatar.setTranslateX(avatar.getTranslateX() - 57);
                break;
            case D:
               	avatar.setTranslateX(avatar.getTranslateX() + 57);
                break;
            default : break;

            }
        });

        stage.show();
    }


    
    public static void main(String[] args) {
        launch(args);
    }
}
