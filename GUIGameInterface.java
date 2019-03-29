import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUIGameInterface extends Application {
	private Pane root;
    private AnimationTimer timer;

    private List<Node> platforms = new ArrayList<>();
//    private List<Platforms> staticPlatform = new ArrayList<>();
    private List<Node> backBlock = new ArrayList<>();
    private List<Node> enemies = new ArrayList<>(); 
    private List<Node> bullets = new ArrayList<>();
    private List<Node> enemyBullets = new ArrayList<>();
    private Node avatar;
	private int health;
	private int theLevel;
	private int playerScore;
	
	
	public void GUIGameInterface() {
		health = 3;
		theLevel = 1;
		playerScore = 0;
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
        Label lives = new Label("Lives:" + Avatar.getLives());
        lives.setFont(new Font("Arial", 48));
        lives.setTextFill(Color.web("#0CFF00"));
        Label level = new Label("Level:" + Game.getLevel());
        level.setFont(new Font("Arial", 48));
        level.setTextFill(Color.web("#0CFF00"));
        Label score = new Label("Score:"+ Game.getScore());
        score.setFont(new Font("Arial", 48));
        score.setTextFill(Color.web("#0CFF00"));
        topData.setStyle("-fx-background-color: #000000;");
        topData.getChildren().add(lives);
        topData.getChildren().add(score);
        topData.getChildren().add(level);
        topData.setSpacing(40);
        root = new Pane();
        root.getChildren().add(background);
        root.getChildren().add(topData);
        root.setPrefSize(GUIGame.SCREEN_HEIGHT, GUIGame.SCREEN_WIDTH);
        avatar = Avatar.draw();
      //Adds the static platforms
        for(int i=0; i<=560; i=i+60) {
        	Platforms.draw(i,600-60, root);
        }
        
        for(int i=0; i<=560; i=i+60) {
        	Platforms.draw(i,600-540, root);
        }
      
        Platforms.draw(0,600-480, root);
        Platforms.draw(62,600-480, root);
        Platforms.draw(124,600-480, root);
        
        Platforms.draw(0,600-360, root);
        Platforms.draw(62,600-360, root);
        Platforms.draw(124,600-360, root);
        
        Platforms.draw(0,600-240, root);
        Platforms.draw(62,600-240, root);
        Platforms.draw(124,600-240, root);
        
        Platforms.draw(0,600-120, root);
        Platforms.draw(62,600-120, root);
        Platforms.draw(124,600-120, root);

        Platforms.draw(370,600-420, root);
        Platforms.draw(432,600-420, root);
        Platforms.draw(494,600-420, root);
        
        Platforms.draw(370,600-300, root);
        Platforms.draw(432,600-300, root);
        Platforms.draw(494,600-300, root);
        
        Platforms.draw(370,600-180, root);
        Platforms.draw(432,600-180, root);
        Platforms.draw(494,600-180, root);
        
        Enemy.draw(90, 90, root);
        Enemy.draw(210, 90, root);
        Enemy.draw(390, 90, root);
        Enemy.draw(510, 90, root);
        //Node avatar = new Avatar(300,300, 22);
        root.getChildren().add(avatar);
        
        return root;
    }

	
    public void start(Stage stage) throws Exception {
    	stage.setTitle("G A L L A G E R");
        stage.setScene(new Scene(createNodes()));
        stage.show();
	}
    
    public static void main(String[] args) {
        launch(args);
    }
}
