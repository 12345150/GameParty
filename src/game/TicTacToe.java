package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
public class TicTacToe extends Application {
    public Tile[][] tileList = new Tile[3][3];
    public Stage primaryStage = new Stage();

    public GridPane pane = new GridPane();
    public BorderPane borderPane = new BorderPane();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();

                pane.add( tileList[i][j]=tile, j, i);
                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int row = GridPane.getRowIndex(tile);
                        int colum = GridPane.getColumnIndex(tile);
                        if (tileList[row][colum].getSet() !="X" && tileList[row][colum].getSet() !="O") {
                            tile.setX();
                            tileList[row][colum].setX();
                        } else {
                            return;
                        }
                        if (isWin("X")) {
                            changePane(1);
                        } else if (isFull()) {
                            changePane(0);
                        } else {
                            computerPlay();
                        }
                    }
                });
            }
        }
        borderPane.setCenter(pane);
        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void computerPlay() {
        while (true) {
            Random random = new Random();
            int i = random.nextInt(3);
            int j = random.nextInt(3);
            if (tileList[i][j].getSet()!= "X" && tileList[i][j].getSet() !="O") {
                setNewTile(i, j);
                break;
            }
        }
    }
    public void setNewTile(int i, int j){
        Tile tileO = new Tile();
        tileO.setO();
        pane.add(tileO, j, i);
        tileList[i][j].setO();
        if (isWin("O")) {
            changePane(-1);
        } else if (isFull()) {
            changePane(0);
        }
    }
    public void changePane(int i ){
        Label l = new Label();
        Label lCheer = new Label();
        if(i==1){
            lCheer.setText("Congratulations!");
            l.setText("You Win !");
        } else if (i==-1) {
            lCheer.setText("Sorry ");
            l.setText("You Loss !");
        }else if(i==0){
            lCheer.setText("OMG ");
            l.setText("A tie !");
        }
        showResult s = new showResult.Builder(lCheer).setLabel2(l).build();
        Label label1 = s.getLabel1();
        Label  label2 = s.getLabel2();
        label1.setFont(Font.font("Arial", FontWeight.BOLD,20));
        label2.setFont(Font.font("Arial", FontWeight.BOLD,20));
        Button buttonReStart = new Button("restart");
        buttonReStart.setFont(Font.font("Arial", FontWeight.BOLD,20));
        buttonReStart.setOnAction(e ->{
            TicTacToe newGame = new TicTacToe();
            newGame.start(primaryStage);
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event ->{
            pane.getChildren().clear();
            pane.add(label1,4,3);
            pane.add(label2,4,5);
            pane.add(buttonReStart,4,6);
            pane.setAlignment(Pos.CENTER);
            pane.setPadding(new Insets(25, 25, 25, 25));
        }));
        timeline.play();
    }

    public boolean isWin(String s){
        for(int i = 0;i<3;i++){
            if(tileList[i][0].getSet() ==s && tileList[i][1].getSet()==s && tileList[i][2].getSet()==s){
                return true;
            }
        }
        for(int i = 0;i<3;i++){
            if(tileList[0][i].getSet()==s &&tileList[1][i].getSet()==s && tileList[2][i].getSet()==s){
                return true;
            }
        }
        if(tileList[0][0].getSet() ==s && tileList[1][1].getSet() ==s && tileList[2][2].getSet()==s){
            return true;
        }
        if(tileList[2][0].getSet()==s && tileList[1][1].getSet()==s &&tileList[0][2].getSet()==s){
            return true;
        }
        return false;

    }
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tileList[i][j].getSet() =="X" || tileList[i][j].getSet()=="O") {
                    return false;
                }
            }
        }
        return true;
    }
}
class Tile extends StackPane {
    private Text text = new Text();

    public Tile() {
        text.setFont(Font.font(99));
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        getChildren().addAll(border, text);
        setAlignment(Pos.CENTER);
    }
    public void setX() {
        text.setText("X");
    }
    public void setO() {
        text.setText("O");

    }
    public String getSet(){
        return text.getText();
    }
}


class showResult{
    private Label label1;
    private Label label2;
    public static class Builder{
        private Label label1;
        private Label label2;
        public Builder(Label label1){
            label1.setFont(new Font("Cambria", 32));
            this.label1 = label1;
       }
        public Builder setLabel2(Label label2 ){
            label2.setFont(new Font("Cambria", 32));
            this.label2 = label2;
            return this;
        }
        public showResult build(){
            return new showResult(this);
        }
    }
    private showResult(Builder builder){
        this.label1 =builder.label1;
        this.label2 = builder.label2;
    }
    public  Label getLabel1(){
        return label1;
    }
    public Label getLabel2(){return label2;}


}

