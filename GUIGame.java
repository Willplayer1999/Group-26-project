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

public class GUIGame extends Game{
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	
	public GUIGame(ArrayList<Platforms> platforms, ArrayList<Bullet> bullets, ArrayList<Enemy> enemy, Avatar avatar) {
		super(platforms,bullets, enemy, avatar);
	}
	public GUIGame() {
		super();

		Avatar avatar = new Avatar(SCREEN_WIDTH/2, SCREEN_HEIGHT*7/8, 22);
//		Avatar.setMaxXCord(SCREEN_WIDTH);
//		Avatar.setMaxY(SCREEN_HEIGHT);
		super.setAvatar(avatar);
	}
	
}
