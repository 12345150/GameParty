package game;


import javafx.scene.image.Image;

public class Baffle extends Graph{
	public static final double BAFFLE_WIDTH =  180;
	public static final double BAFFLE_HEIGHT = 30;
	
	
	public Baffle(double x, double y,double width, double height) {
		super(x, y, width, height);
		setImage(new Image("image/baffle.png"));
	}
	
}