package game;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Ball extends Graph{
	public static final double BALL_WIDTH = 40;
	public static final double BALL_HEIGHT = 40;
	private double speedX, speedY;
	private int directionX,directionY;
	private boolean start;
	public boolean living;

	public Ball(double x, double y,double width, double height) {
		super(x, y, width, height);
		setImage(new Image("image/ball.png"));
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public int getDirectionY() {
		return directionY;
	}

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}

	public int getDirectionX() {
		return directionX;
	}

	public void setDirectionX(int directonX) {
		this.directionX = directonX;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	@Override
	public void setX(double x) {
		if (x < 0 || x > 600- Ball.BALL_WIDTH) {
			setDirectionX(getDirectionX() * -1);
		}
		super.setX(x);
	}
	
	@Override
	public void setY(double y) {
		if (y < 0) {
			setDirectionY(getDirectionY() * -1);
		}
		else if( y >= 600) {
			setLiving(false);
		}
		super.setY(y);
	}
	
	public boolean collideWith(Graph graph) {
		if(graph.isLiving()) {
			Rectangle bound = getBound();
			if(bound.intersects(graph.getX(), graph.getY(), graph.getWidth(), graph.getHeight())){
				if (graph instanceof Bricks) {
					graph.setLiving(false);
				}
				setDirectionY(getDirectionY() * -1);
				return true;
			}
			return false;
		}
		return false;
	}
}
