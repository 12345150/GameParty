package game;

import game.Graph;
import javafx.scene.image.Image;

public class Bricks extends Graph{
	public static final double BRICKS_WIDTH = 150;
	public static final double BRICKS_HEIGHT = 60;

	public Bricks(double x, double y,double width, double height) {
		super(x, y, width, height);
		double num = Math.random();
		if (num <= 0.3) {
			setImage(new Image("image/bricks_blue.png"));
		}
		else if (num <= 0.6) {
			setImage(new Image("image/bricks_green.png"));
		}
		else {
			setImage(new Image("image/bricks_yellow.png"));
		}
		
		
	}
	
}
