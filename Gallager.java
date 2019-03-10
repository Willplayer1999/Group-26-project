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
    
	int changeDirection = 0;
	int changeDirectionReverse = 1;

    private Parent createNodes() {
        root = new Pane();
        root.setPrefSize(600, 600);
        
        //start from left to right
        platforms.add(createPlatform(0,600-480));
        platforms.add(createPlatform(0,600-360));
    	platforms.add(createPlatform(0,600-240));
    	platforms.add(createPlatform(0,600-120));
    	//start from right to left
        platforms.add(createPlatform(370,600-420));
        platforms.add(createPlatform(370,600-300));
    	platforms.add(createPlatform(370,600-180));
    	
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
        Circle circle = new Circle(29, Color.BLACK);
        circle.setTranslateY(600 - 29);
        return circle;
    }

    private Node createPlatform(int x, int y) {
    	Rectangle rect = new Rectangle(180, 58, Color.GREEN); 
        rect.setTranslateX(x);
        rect.setTranslateY(y);
        
        root.getChildren().add(rect);
        return rect;
    }

    private void movePlatform() {
        for (Node platform : platforms) {
        	if (platforms.indexOf(platform)<=3) { //<= 3 for the platforms starting Left to right
        	if (platform.getTranslateX() == 600-190) {
        		changeDirection = 1; 
        	}
        	if (platform.getTranslateX() == 0) {
        		changeDirection = 0; 
        	}
        	if (changeDirection==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirection==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        	if (platforms.indexOf(platform)>3) { //> 3 for the platforms starting Right to Left
        	if (platform.getTranslateX() == 600-190) {
        		changeDirectionReverse = 1; 
        	}
        	if (platform.getTranslateX() == 0) {
        		changeDirectionReverse = 0; 
        	}
        	if (changeDirectionReverse==0) {
            	platform.setTranslateX(platform.getTranslateX()+1);
        	}
        	if(changeDirectionReverse==1) {
        		platform.setTranslateX(platform.getTranslateX()-1);
        	}
        	}
        }
       
    }

    private void checkCollision() {
        for (Node platform : platforms) {
            if (platform.getBoundsInParent().intersects(avatar.getBoundsInParent())) {
				collided = true;
            	platform.setOpacity(0.5);
                avatar.setTranslateX(platform.getTranslateX()+90);
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
                avatar.setTranslateX(avatar.getTranslateX() - 120);
                break;
            case D:
                avatar.setTranslateX(avatar.getTranslateX() + 120);
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