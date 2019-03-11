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
import java.util.ArrayList;
import java.util.List;

public class Gallager extends Application {

    private Pane root;
    private AnimationTimer timer;

    private List<Node> platforms = new ArrayList<>();
    private Node avatar;
	boolean collided = false;
    
	int changeDirectionRight = 0;
	int changeDirectionMiddle = 0;
	int changeDirectionLeft = 0;
	
	int changeDirectionReverseRight = 1;
	int changeDirectionReverseMiddle = 1;
	int changeDirectionReverseLeft = 1;
	
//////////
	private int xPlat = 90;
	private int yPlat = 29;
	private int xForm = 0;
	private int yForm = 0;
	//Things Chad has added.
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

	//
	public int getXPlat() {
		return xPlat;
	}
	public void setXPlat(int xPlat) {
		this.xPlat = xPlat;
	}
	public int getYPlat() {
		return yPlat;
	}
	public void setYPlat(int yPlat) {
		this.yPlat = yPlat;
	}
	public int getXForm() {
		return xForm;
	}
	public void setXForm(int xForm) {
		this.xForm = xForm;
	}
	public int getYForm() {
		return yForm;
	}
	public void setYForm(int yForm) {
		this.yForm = yForm;
	}
//////////////
    private Parent createNodes() {
	//Things Chad Has added.
	HBox topData = new HBox();
    	Label lives = new Label("Lives:" + health);
    	lives.setFont(new Font("Arial", 30));
    	lives.setTextFill(Color.web("#0CFF00"));
    	Label level = new Label("Level:" + theLevel);
    	level.setFont(new Font("Arial", 30));
    	level.setTextFill(Color.web("#0CFF00"));
    	Label score = new Label("Score:" + playerScore);
    	score.setFont(new Font("Arial", 30));
    	score.setTextFill(Color.web("#0CFF00"));
    	topData.setStyle("-fx-background-color: #000000;");
    	topData.getChildren().add(lives);
    	topData.getChildren().add(score);
    	topData.getChildren().add(level);
    	topData.setSpacing(153);
	//
        root = new Pane();
        root.setPrefSize(600, 600);
        
        //start from left to right
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
    	
    	//start from right to left
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
    	
        avatar = createAvatar();
        root.getChildren().add(avatar);

        timer = new AnimationTimer() {
            @Override
            public void handle(long go) {
            	movePlatform();
            	checkCollision();


            }
        };
        timer.start();
        return root;
    }

    private Node createAvatar() {
        Circle circle = new Circle(22, Color.BLACK);
        circle.setTranslateY(600 - 29);
        return circle;
    }

    private Node createPlatform(int x, int y) {
    	Rectangle rect = new Rectangle(60, 58, Color.GREEN); 
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        
        root.getChildren().add(rect);
        return rect;
    }

    private void movePlatform() {
        for (Node platform : platforms) {
        	//3rd platform of each going from left to right
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
        	//2nd platform of each going from left to right
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
        	//1st platform of each going from left to right
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

    public void checkCollision() {
////xplat yplat
        for (Node platform : platforms) {
            if (platform.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
				collided = true;
            	platform.setOpacity(0.5);
                avatar.setTranslateX(platform.getTranslateX()+30);
                avatar.setTranslateY(platform.getTranslateY()+29);
                return;
            }
            else {
            	platform.setOpacity(1.0);
            }
        }

        if (avatar.getTranslateY() <= 0) {
            timer.stop();
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createNodes()));

        stage.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case S:
                avatar.setTranslateY(avatar.getTranslateY() + 60);
                break;
            case W:
            	avatar.setTranslateY(avatar.getTranslateY() - 60);
                break;
            case A:
           		avatar.setTranslateX(avatar.getTranslateX() - 60);	             
                break;
            case D:
               	avatar.setTranslateX(avatar.getTranslateX() + 60);
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
/**
				for (Node platform : platforms){
				if(collided= true){
					avatar.setTranslateX(platform.getTranslateX());
				}
				}
*/
