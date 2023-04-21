package game;

import java.util.ArrayList;
import java.util.List;

import game.Baffle;
import game.Ball;
import game.Bricks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GAME extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception{
		stage.setTitle("Break the Bricks");
		Parent root =  FXMLLoader.load(getClass().getResource("view.fxml"));
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	private Canvas canvas;
	private GraphicsContext graphic;
	private Baffle baffle;
	private List<Bricks> bricks;
	private Ball ball;
	private Timeline timeline;

	
	@FXML
	public void initialize(){
		initGame();
		this.graphic = canvas.getGraphicsContext2D();
		this.canvas.requestFocus();
		this.canvas.setFocusTraversable(true);
		this.canvas.setOnMousePressed((event) -> handleMousePressed(event));
		this.canvas.setOnMouseDragged(e -> handleMouseDragged(e));
		this.canvas.setOnMouseClicked((event) -> handleMouseClicked(event));
		run();
	}
	
	private double dragOffSetX;
	private void handleMousePressed(MouseEvent event) {
		this.dragOffSetX = event.getX() - this.baffle.getX();
	}
	
	private void handleMouseDragged(MouseEvent e) {
		if (this.baffle.getX() < 0 ) {
			this.baffle.setX(0);
		}
		else if(this.baffle.getX() > 600 - Baffle.BAFFLE_WIDTH ) {
			this.baffle.setX(600 - Baffle.BAFFLE_WIDTH);
		}
		else {
			this.baffle.setX(e.getX() - this.dragOffSetX);
		}
	}
	
	private void handleMouseClicked(MouseEvent event) {
		if (this.ball.isStart()) {
			return;
		}
		double moveX = event.getX();
		double moveY = event.getY();
		double x = this.ball.getX();
		double y = this.ball.getY();
		double tanAngle = Math.abs((moveX - x) / (moveY - y));
		//Right departure direction is positive
		if(moveX > x) {
			this.ball.setDirectionX(1);
		}
		else if(moveX < x) {
			this.ball.setDirectionX(-1);
		}
		else {
			this.ball.setDirectionX(0);
		}
		//Down departure direction is positive
		if(moveY > y) {
			this.ball.setDirectionY(1);
		}
		else if(moveY  < y) {
			this.ball.setDirectionY(-1);
		}
		else {
			this.ball.setDirectionY(0);
		}
		double atan = Math.atan(tanAngle);
		this.ball.setSpeedX(3 * Math.sin(atan));
		this.ball.setSpeedY(3 * Math.cos(atan));
		this.ball.setStart(true);
	}
	
	public void run() {
		timeline = new Timeline(new KeyFrame(Duration.millis(10),
				t -> this.showGame(graphic)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}


	public void initGame(){
		this.baffle = new Baffle((600-Baffle.BAFFLE_WIDTH)/2, 400, Baffle.BAFFLE_WIDTH, Baffle.BAFFLE_HEIGHT);
		this.ball = new Ball((600-Ball.BALL_WIDTH)/2, 400-Ball.BALL_HEIGHT, Ball.BALL_WIDTH, Ball.BALL_HEIGHT);
		this.bricks = new ArrayList<>();
	    for(int i = 0; i < 6; i++) {
	     for(int j = 0; j < 2; j++) {
	      this.bricks.add(new Bricks((i*Bricks.BRICKS_WIDTH), j*Bricks.BRICKS_HEIGHT, Bricks.BRICKS_WIDTH, Bricks.BRICKS_HEIGHT));
	     }
	    }
	}
	
	public void showGame(GraphicsContext graphic){
		graphic.clearRect(0, 0, 600, 600);
		if(this.ball.isLiving()) {
			boolean win = true;
			for (Bricks bricks: this.bricks) {
				if(bricks.isLiving()) {
					win = false;	
				}
				this.ball.collideWith(bricks);
				bricks.draw(graphic);
				if(win) {
					graphic.setFont(Font.font(50));
					graphic.fillText("You Win!", 175, 250);
					return;
				}
		    }
			this.ball.setX(this.ball.getX() + this.ball.getSpeedX()*this.ball.getDirectionX());
			this.ball.setY(this.ball.getY() + this.ball.getSpeedY()*this.ball.getDirectionY());
			this.baffle.draw(graphic);
			this.ball.collideWith(baffle);
			this.ball.draw(graphic);
		}
		else { 
			graphic.setFont(Font.font(50));
			graphic.fillText("You fail!", 190, 250);
		}
	}
}

