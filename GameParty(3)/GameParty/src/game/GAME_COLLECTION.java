package game;


import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GAME_COLLECTION extends Application {
	
	private int playerScore = 0;
    private int computerScore = 0;
    private int roundsToWin = 5; 
    Scene startScene;
	Scene gameScene;
	Scene endScene;
	Scene sceneg2;
	Scene freeplay;
	Stage primaryStage;
    GridPane pane = new GridPane();
    BorderPane borderPane = new BorderPane();
    public Tile[][] tileList = new Tile[3][3];
    
    int WRN = 0;

    Scene scenes0;
    Scene scenes1;
    Scene scenes2;
    Scene scenes3;
    Scene scenes4;
    Scene scenes5;


    public void start(Stage primaryStage) throws Exception {
    	
    	//music
    	File file1 = new File("src/39467.wav");		
    	URI uri1 = file1.toURI();	
    	URL url1 = uri1.toURL();	
    	String play = url1.toString();
    	Media media = new Media(play);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();

        primaryStage.setOnCloseRequest(event -> {
            mediaPlayer.stop();
            System.exit(0);
        });
    	
    	//GAME2
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
        Button eb = new Button("exit game");
        eb.setOnAction(e -> primaryStage.setScene(scenes2));
        eb.setFont(Font.font("Arial", FontWeight.BOLD,20));
        borderPane.setBottom(eb);
        Scene sceneg2 = new Scene(borderPane, 600, 625);
        
    	
    	primaryStage.setTitle("FirstComeFirstWin");

        // START SIDE
        StackPane root1 = new StackPane(); 
        
        Image img1 = new Image("img/begin.jpg");
        ImageView IV1 = new ImageView(img1);
        root1.getChildren().add(IV1);
        
        Button b1 = new Button("");
        b1.setPrefWidth(800);
        b1.setPrefHeight(600);
        b1.setBorder(null);
        b1.setBackground(null);
        b1.setOnAction(e -> primaryStage.setScene(scenes1));
        root1.getChildren().add(b1);
        
        
        scenes0 = new Scene(root1, 800, 600);
        

        
        
        // STEP1 game1
        StackPane root2 = new StackPane(); 
        
        Image img2 = new Image("img/step1.jpg");
        ImageView IV2 = new ImageView(img2);
        root2.getChildren().add(IV2);
        
        Button b2 = new Button("LET's GO");
        b2.setOnAction(e -> primaryStage.setScene(scenes2));
        b2.setFont(Font.font("Arial", FontWeight.BOLD,20));
        root2.getChildren().add(b2);
        
        Button s1 = new Button("~Start~");
        s1.setOnAction(e -> primaryStage.setScene(startScene));
        s1.setFont(Font.font("Arial", FontWeight.BOLD,20));
        root2.getChildren().add(s1);
        
        root2.setAlignment(Pos.CENTER);
        StackPane.setAlignment(b2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(s1, Pos.CENTER);

        
        scenes1 = new Scene(root2, 800, 600);
        
        

        // STEP2 game2
        StackPane root3 = new StackPane(); 
        
        Image img3 = new Image("img/step2.jpg");
        ImageView IV3 = new ImageView(img3);
        root3.getChildren().add(IV3);
        
        Button b3 = new Button("LET's GO");
        b3.setOnAction(e -> primaryStage.setScene(scenes3));
        b3.setFont(Font.font("Arial", FontWeight.BOLD,20));
        root3.getChildren().add(b3);
                
        Button s2 = new Button("~Start~");
        s2.setOnAction(e -> primaryStage.setScene(sceneg2));
        s2.setFont(Font.font("Arial", FontWeight.BOLD,20));
        root3.getChildren().add(s2);
                
        root2.setAlignment(Pos.CENTER);
        StackPane.setAlignment(b3, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(s2, Pos.CENTER);
                
        scenes2 = new Scene(root3, 800, 600);

        // STEP3 game3
        StackPane root4 = new StackPane(); 
        
        Image img4 = new Image("img/step3.jpg");
        ImageView IV4 = new ImageView(img4);
        root4.getChildren().add(IV4);
        
        Button b4 = new Button("LET's GO");
        b4.setOnAction(e -> 
        {if(WRN>=2){
        primaryStage.setScene(scenes4);}
        else{
        	primaryStage.setScene(scenes5);
        }}
                		);
        b4.setFont(Font.font("Arial", FontWeight.BOLD,20));
        root4.getChildren().add(b4);
        
        //go g3
        Button s3 = new Button("~Start~");
        s3.setFont(Font.font("Arial", FontWeight.BOLD,20));
        s3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                GAME g3 = new GAME();
                try {
					g3.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
        root4.getChildren().add(s3);
                
        root4.setAlignment(Pos.CENTER);
        StackPane.setAlignment(b4, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(s3, Pos.CENTER);
        
        scenes3 = new Scene(root4, 800, 600);
        

        // CLEAR THE GAME
        StackPane root5 = new StackPane(); 
        
        Image img5 = new Image("img/step4.jpg");
        ImageView IV5 = new ImageView(img5);
        root5.getChildren().add(IV5);
        
        Button sr = new Button("~UNLOCK THE FREE MODEL~");
        sr.setOnAction(e -> primaryStage.setScene(freeplay));
        sr.setFont(Font.font("Arial", FontWeight.BOLD,20));
        StackPane.setAlignment(sr, Pos.BOTTOM_CENTER);
        root5.getChildren().add(sr);
        
        scenes4 = new Scene(root5, 800, 600);
        
        //FREE MODEL
        StackPane free = new StackPane(); 
        ImageView IVf = new ImageView(img1);
        free.getChildren().add(IVf);
        
        Button g1w = new Button("~ G A M E 1 ~");
        g1w.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                FirstComeFirstWin g1 = new FirstComeFirstWin();
                try {
					g1.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
        g1w.setFont(Font.font("Arial", FontWeight.BOLD,20));
        g1w.resize(142,37);
        free.getChildren().add(g1w);
        
        Button g2w = new Button("~ G A M E 2 ~");
        g2w.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                TicTacToe gt = new TicTacToe();
                try {
					gt.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
        g2w.setFont(Font.font("Arial", FontWeight.BOLD,20));
        g2w.resize(142,37);
        free.getChildren().add(g2w);
        
        Button g3w = new Button("~ G A M E 3 ~");
        g3w.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                GAME gx = new GAME();
                try {
					gx.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
        g3w.setFont(Font.font("Arial", FontWeight.BOLD,20));
        g3w.resize(142,37);
        free.getChildren().add(g3w);
        
        g1w.setTranslateX(-260);
        g1w.setTranslateY(-193);
        g2w.setTranslateX(-260);
        g2w.setTranslateY(15);
        g3w.setTranslateX(-260);
        g3w.setTranslateY(225);
        
        freeplay = new Scene(free,800,600);
        
        
        //GAME OVER
        StackPane root6 = new StackPane(); 
        
        Image img6 = new Image("img/gameover.jpg");
        ImageView IV6 = new ImageView(img6);
        root6.getChildren().add(IV6);
        
        //Go to the beginning
        Button sf = new Button("~RESTART~");
        sf.setOnAction(e -> primaryStage.setScene(scenes0));
        sf.setFont(Font.font("Arial", FontWeight.BOLD,20));
        StackPane.setAlignment(sf, Pos.BOTTOM_CENTER);
        root6.getChildren().add(sf);
        
        scenes5 = new Scene(root6, 800, 600);



        primaryStage.setScene(scenes0);
        primaryStage.show();
        
        
        //game1
        Label titleLabel1 = new Label("First Come First Win");
        titleLabel1.setTextFill(Color.web("#6189D0"));
        titleLabel1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,25));

        
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        startButton.setOnAction(e -> primaryStage.setScene(gameScene));

        
        Button SCexitButton = new Button("Exit Game");
        SCexitButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        SCexitButton.setOnAction(e -> primaryStage.setScene(scenes5));

        
        VBox startLayout = new VBox(20, titleLabel1, startButton, SCexitButton);
        startLayout.setAlignment(Pos.CENTER);
        startScene = new Scene(startLayout, 800, 600);


    	
      
 
    	//gameScene
        Label playerScoreLable = new Label("Player Score: 0");
        playerScoreLable.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,18));
        Label computerScoreLable = new Label("Computer Score: 0");
        computerScoreLable.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,18));
        Label resultLable = new Label("");
        resultLable.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        
        //Choices' button
        Button rockButton = new Button("Rock");
        rockButton.setOnAction(e -> playRound("rock", resultLable));
        rockButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        
        Button paperButton = new Button("Paper");
        paperButton.setOnAction(e -> playRound("paper", resultLable));
        paperButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));

        Button scissorsButton = new Button("Scissors");
        scissorsButton.setOnAction(e -> playRound("scissors", resultLable));
        scissorsButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        
        Button endButton = new Button("End Game");
        endButton.setOnAction(e -> primaryStage.setScene(endScene));
        endButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        
      
        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(rockButton, 0, 0);
        gridPane.add(paperButton, 1, 0);
        gridPane.add(scissorsButton, 2, 0); // column=2 row=0

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(playerScoreLable, computerScoreLable, gridPane, resultLable);

        //Scene setting (vBox, weight,height)
        HBox gameButtonLayout = new HBox(20, rockButton, paperButton, scissorsButton);
        gameButtonLayout.setAlignment(Pos.CENTER);
        VBox gameLayout = new VBox(20, playerScoreLable, computerScoreLable, gameButtonLayout, resultLable, endButton);
        gameLayout.setAlignment(Pos.CENTER);
        gameScene = new Scene(gameLayout, 800, 600);

        
        
        // End Scene
        Label endLabel = new Label("");
        endLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,20));
        
        Button restartButton = new Button("Restart Game");
        restartButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        restartButton.setOnAction(e -> {
            playerScore = 0;
            computerScore = 0;
            playerScoreLable.setText("Player: " + playerScore);
            computerScoreLable.setText("Computer: " + computerScore);
            resultLable.setText("");
            primaryStage.setScene(startScene);
        });
        
            Button exitButton = new Button("Exit Game");
            exitButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
            exitButton.setOnAction(e -> primaryStage.setScene(scenes1));
            HBox endButtonLayout = new HBox(20, restartButton, exitButton);
            endButtonLayout.setAlignment(Pos.CENTER);
            VBox endLayout = new VBox(20, endLabel, endButtonLayout);
            endLayout.setAlignment(Pos.CENTER);
            endScene = new Scene(endLayout, 800, 600);
            
    
            
    }

    //Set Game Logic
    private void playRound(String playerSelection, Label resultLabel) {
        String computerSelection = generateComputerSelection();
        String result = determineWinner(playerSelection, computerSelection);
        		// determine winner and update scores
        		if (playerSelection.equals(computerSelection)) {
        		result = "Tie!";
        		resultLabel.setTextFill(javafx.scene.paint.Color.BLUE);
        		} else if (playerSelection.equals("rock") && computerSelection.equals("scissors") ||
        		playerSelection.equals("paper") && computerSelection.equals("rock") ||
        		playerSelection.equals("scissors") && computerSelection.equals("paper")) {
        		result = "Player wins!";
        		playerScore++;
        		resultLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        		} else {
        		result = "Computer wins!";
        		computerScore++;
        		resultLabel.setTextFill(javafx.scene.paint.Color.RED);
        		}
        
        		
        // update labels
        resultLabel.setText(result);
        resultLabel.setAlignment(Pos.CENTER);
        resultLabel.setWrapText(true);
        Label playerLabel = (Label) gameScene.getRoot().getChildrenUnmodifiable().get(0);
        Label computerLabel = (Label) gameScene.getRoot().getChildrenUnmodifiable().get(1);
        playerLabel.setText("Player: " + playerScore);
        computerLabel.setText("Computer: " + computerScore);

        // check for end of game
        if (playerScore >= roundsToWin || computerScore >= roundsToWin) {
            if (playerScore >= roundsToWin && computerScore < roundsToWin) {
                resultLabel.setText("You win the game!");
                WRN++;
            } else {
                resultLabel.setText("Computer wins the game!");
            }
            Label endLabel = (Label) endScene.getRoot().getChildrenUnmodifiable().get(0);
            endLabel.setText(resultLabel.getText());
            endLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,18));
         
            primaryStage.setScene(endScene);
        }
    }

    private String generateComputerSelection() {
        String[] options = {"rock", "paper", "scissors"};
        int randomIndex = (int) (Math.random() * options.length);
        return options[randomIndex];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "tie";
        } else if (playerChoice.equals("rock") && computerChoice.equals("scissors") ||
        		playerChoice.equals("paper") && computerChoice.equals("rock") ||
        		playerChoice.equals("scissors") && computerChoice.equals("paper")) {
            return "player";
        } else {
            return "computer";
        }  
    }
    
    
    //GAME2
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
    
    //GAME2 ENDINGs
    public void changePane(int i ){
        Label l = new Label();
        Label lCheer = new Label();
        if(i==1){
            lCheer.setText("Congratulations!");
            l.setText("You Win !");
            WRN++;
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event ->{
            pane.getChildren().clear();
            pane.add(label1,4,3);
            pane.add(label2,4,5);
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
    public class Tile extends StackPane {
        Text text = new Text();

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
    
    


    public static void main(String[] args) {
        launch(args);
    }
}
