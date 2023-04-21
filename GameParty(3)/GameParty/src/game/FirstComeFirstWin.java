package game;



import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;




public class FirstComeFirstWin extends Application {
	


	
	Scene startScene;
	Scene gameScene;
	Scene endScene;
    private int playerScore = 0;
    private int computerScore = 0;
    private int roundsToWin = 5; 
    Stage primaryStage;

	

    @Override
    public void start(Stage primaryStage) throws Exception {    	
    	this.primaryStage = primaryStage;
    	primaryStage.setTitle("~THE DIVEL IS COMING! CAN I AVOID FATAL WOUNDS!?~");

    	
    	// startScene
    	Label titleLabel1 = new Label("First Come First Win");
        titleLabel1.setTextFill(Color.web("#6189D0"));
        titleLabel1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,25));

        
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        startButton.setOnAction(e -> primaryStage.setScene(gameScene));

        
        Button SCexitButton = new Button("Exit Game");
        SCexitButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,14));
        SCexitButton.setOnAction(e -> primaryStage.close());

        
        VBox startLayout = new VBox(20, titleLabel1, startButton, SCexitButton);
        startLayout.setAlignment(Pos.CENTER);
        startScene = new Scene(startLayout, 600, 600);


    	
      
 
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
        gameScene = new Scene(gameLayout, 600, 600);

        
        
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
            exitButton.setOnAction(e -> primaryStage.close());
            HBox endButtonLayout = new HBox(20, restartButton, exitButton);
            endButtonLayout.setAlignment(Pos.CENTER);
            VBox endLayout = new VBox(20, endLabel, endButtonLayout);
            endLayout.setAlignment(Pos.CENTER);
            endScene = new Scene(endLayout, 600, 600);
            
    
            primaryStage.setScene(startScene);
            primaryStage.setTitle("First Come First Win");
            primaryStage.show();
            
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
            if (playerScore >= roundsToWin) {
                resultLabel.setText("You win the game!");
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
    
    
    public static void main(String[] args) {
    	launch(args);
    }
    
    

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
 
