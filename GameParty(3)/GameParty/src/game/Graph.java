package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Graph {
	private double x,y,width,height;
	private Image image;
	public GraphicsContext graphic;
	private boolean living;
	
	
	public Graph(double x, double y,double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.living = true;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void draw(GraphicsContext graphic) {
		if (living) {
			graphic.drawImage(image, x, y, width, height);
		}
	}
	
	public void setLiving(boolean living) {
		this.living = living;
	}
	
	public boolean isLiving() {
		return living;
	}
	
	public Rectangle getBound() {
		return new Rectangle(x, y, width, height);
	}
}
