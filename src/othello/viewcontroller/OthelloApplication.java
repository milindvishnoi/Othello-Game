/*
 ** This code is provided solely for the personal and private use of
 ** students taking the CSC207H5 course at the University of Toronto.
 ** Copying for purposes other than this use is expressly prohibited.
 ** All forms of distribution of this code, whether as given or with
 ** any changes, are expressly prohitbited.
 **
 ** Authors: Arnold Rosenbloom, Gautam Gireesh, Arjun Ganguly, Parshva, Milind Vishnoi
 **
 ** All of the files in this directory and all subdirectories are:
 ** Copyright (c) 2019 Arnold Rosenbloom.
 */

package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.InvalidObjectException;
import java.util.ArrayList;


public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put 
	// --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	// Introducing players
	public static String player1 = "Human";
	public static String player2 = "Human";
	public ArrayList<Button> buttons = new ArrayList<>();
	static TextField whosPlayer1, whosPlayer2;
	private static int initialWidth = 750, initialHeight = 600;
	static boolean cheat;
	private static OthelloView oView;
	private static OthelloCheatView cView;
	private static CurrTokenView currTokenView;
	Othello othello;
	private Circle currentBlack;
	private HBox cplayer;
	static Stage currentStage;
	static ArrayList<Othello> moveStack;
	private static Alert winner;
	static Opponent opponent;
	private static OthelloOperator operator;
	private double radius = 12;
	private GameTimer gameTimer;
	private VBox newTleft;
	private GameTimer gametimer;

	@Override
		public void start (Stage stage) throws Exception {
		currentStage = stage;
		othello = new Othello();
		opponent = new Opponent();
		operator = new OthelloOperator();
		moveStack = new ArrayList<Othello>();
		winner = new Alert(Alert.AlertType.INFORMATION);
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		HBox score = new HBox();
		HBox cplayer = new HBox();

		Dialog<Integer> dialog = new Dialog<>();
		dialog.setTitle("Othello");
		dialog.setHeaderText("Please enter the total time allotted for each user:");
		Label p1Label = new Label("Player 1: ");
		Label p2Label = new Label("Player 2: ");

		TextField p1mins = new TextField();
		p1mins.setPromptText("Minutes");

		TextField p1secs = new TextField();
		p1secs.setPromptText("Seconds");

		TextField p2mins = new TextField();
		p2mins.setPromptText("Minutes");

		TextField p2secs = new TextField();
		p2secs.setPromptText("Seconds");

		GridPane timeGrid = new GridPane();
		timeGrid.add(p1Label, 1, 1);
		timeGrid.add(p1mins, 2, 1);
		timeGrid.add(p1secs, 3, 1);
		timeGrid.add(p2Label, 1, 2);
		timeGrid.add(p2mins, 2, 2);
		timeGrid.add(p2secs, 3, 2);

		dialog.getDialogPane().setContent(timeGrid);
		ButtonType OK = new ButtonType("Done");
		dialog.getDialogPane().getButtonTypes().add(OK);
		dialog.showAndWait();
		int p1InMins = 5, p1InSecs = 0, p2InMins = 5, p2InSecs = 0;
		boolean validInputs = false;
		while (!validInputs) {
			try {
				p1InMins = Integer.parseInt(p1mins.getText());
				p1InSecs = Integer.parseInt(p1secs.getText());
				p2InMins = Integer.parseInt(p2mins.getText());
				p2InSecs = Integer.parseInt(p2secs.getText());
				boolean validMins = p1InMins >= 0 && p2InMins >= 0;
				boolean validSecs1 = 60 > p1InSecs && p1InSecs >= 0;
				boolean validSecs2 = 60 > p2InSecs && p2InSecs >= 0;
				if (!(validMins && validSecs1 && validSecs2)) {
					throw new InvalidObjectException("Inputs must be greater than or equal to 0, " +
							"and seconds must also be less than 60");
				}
				validInputs = true;
			} catch (Exception e) {
				dialog.setHeaderText("Looks like your inputs are invalid, please try again!\n" +
						"Please enter the total time allotted for each user:");
				dialog.showAndWait();
			}
		}

		OthelloApplication.whosPlayer1 = new TextField(OthelloApplication.player1);
		OthelloApplication.whosPlayer2 = new TextField(OthelloApplication.player2);


		TextField currentState = new TextField("Current State: Pending ");
		currentState.setDisable(true);

		TextField xScore = new TextField(" X : 2");
		xScore.setDisable(true);
		xScore.setMaxWidth(80);
		xScore.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");

		TextField oScore = new TextField("O : 2");
		oScore.setDisable(true);
		oScore.setMaxWidth(80);
		oScore.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");

		Button askHint = new Button("Hint!");

		currentState.setMaxWidth(400);
		currentState.setStyle("-fx-background-color: transparent; -fx-text-inner-color: white");

		currentBlack = new Circle(10, 10, 10);
		currentBlack.setStyle("-fx-fill: BLACK");
		score.setSpacing(10);
		score.getChildren().add(xScore);
		score.getChildren().add(oScore);
		score.getChildren().add(currentState);

		oScore.setDisable(true);
		oScore.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");
		currentState.setStyle("-fx-background-color: transparent; -fx-text-inner-color: white");
		cplayer.getChildren().add(currentBlack);
		score.getChildren().add(cplayer);
		pane.setTop(score);


		//setting up player tags
		GridPane displayWhichPlayer = new GridPane();
		displayWhichPlayer.setPadding(new Insets(0));
		TextField displayPlayer1 = new TextField("Player 1:");
		displayPlayer1.setDisable(true);
		displayPlayer1.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;" +
				"-fx-font: 20px ComicSans;");
		displayPlayer1.setPrefWidth(100);


		OthelloApplication.whosPlayer1.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;" +
				"-fx-font: 20px ComicSans;");
		OthelloApplication.whosPlayer1.setDisable(true);
		OthelloApplication.whosPlayer1.setPrefWidth(100);

		TextField displayPlayer2 = new TextField("Player 2:");
		displayPlayer2.setDisable(true);
		displayPlayer2.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;" +
				"-fx-font: 20px ComicSans;");
		displayPlayer2.setPrefWidth(100);


		OthelloApplication.whosPlayer2.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;" +
				"-fx-font: 20px ComicSans;");
		OthelloApplication.whosPlayer2.setDisable(true);
		OthelloApplication.whosPlayer2.setPrefWidth(100);


		Button restartButton = new Button("Restart");

		// Add Current State of Othello to the Stack

		// Create Undo
		Button undoButton = new Button("Undo");

		//Strategy Hint Button
		Button stratButton = new Button("Hint");
		stratButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Hint?", operator, this ));



		// Add Hint Buttons and Cheater Mode CheckBox
		CheckBox cheaterMode = new CheckBox("Cheater Mode?");



		//Setting up the timer
		gametimer = new GameTimer(othello, p1InMins, p1InSecs, p2InMins, p2InSecs, whosPlayer1, whosPlayer2);
		newTleft = gametimer.getGUI();

		// Game Board
		GridPane grid = getBoard(returnOthello(), winner, gametimer);
		pane.setCenter(grid);

		cheaterMode.setStyle("-fx-text-fill: white");
		cheaterMode.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Cheater Mode?", operator, this ));


		//setting on the pane
		displayWhichPlayer.add(displayPlayer1, 0, 1);
		displayWhichPlayer.add(OthelloApplication.whosPlayer1, 1, 1);
		displayWhichPlayer.add(displayPlayer2, 0, 2);
		displayWhichPlayer.add(OthelloApplication.whosPlayer2, 1, 2);
		pane.setRight(displayWhichPlayer);
		displayWhichPlayer.add(cheaterMode, 0, 9);
		displayWhichPlayer.add(stratButton, 0, 6);
		displayWhichPlayer.add(newTleft, 0, 12);
        score.getChildren().add(undoButton);
        score.getChildren().add(restartButton);
        score.setSpacing(30);




		displayWhichPlayer.setVgap(10);
		restartButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Restart", operator, this ));

		// setting the buttons to change the players mid-game.
		TextField textField = new TextField("Game Mode: ");
		textField.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");
		textField.setDisable(false);

		//making buttons for GameMode and setting action for eachs
		Button humanPlayerButton = new Button("Human");
		humanPlayerButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Human", operator, this ));

		Button greedyPlayerButton = new Button("Greedy");
		greedyPlayerButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Greedy", operator, this ));

		Button randomPlayerButton = new Button("Random");
		randomPlayerButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Random", operator, this ));

		Button strategyPlayerButton = new Button("Strategy");
		strategyPlayerButton.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Strategy", operator, this ));

		askHint.setOnAction(new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gameTimer, "Hint?", operator, this ));
		// putting all buttons and textField on a Hbox and then onto Pane
		HBox changePlayer = new HBox();
		changePlayer.setPadding(new Insets(5, 5, 5, 5));
		changePlayer.setSpacing(20);
		changePlayer.getChildren().addAll(textField, humanPlayerButton, greedyPlayerButton,
				randomPlayerButton, strategyPlayerButton);
		pane.setBottom(changePlayer);

		pane.setStyle("-fx-background-color: #4F4F4F");

		oView = new OthelloView(buttons, xScore, oScore, score, othello);
		cView = new OthelloCheatView(buttons, returnOthello());
		currTokenView = new CurrTokenView(returnOthello(), cplayer, currentState);
		FlipAnimation flipAnimation = new FlipAnimation(othello, buttons, OthelloApplication.whosPlayer1.getText(),
				OthelloApplication.whosPlayer2.getText());

		othello.addObserver(oView);
		othello.addObserver(cView);
		othello.addObserver(currTokenView);
		othello.addObserver(flipAnimation);

		// Add Output TextField
		undoButton.setOnAction( new OthelloInterfaceEventHandler(opponent, this.buttons, winner,
				othello, gametimer, "Undo", operator, this ));

		// SCENE
		Scene scene = new Scene(pane, 500, 500);
		stage.setTitle("Othello");
		stage.setScene(scene);
		stage.setMinWidth(initialWidth);
		stage.setMinHeight(initialHeight);
		moveStack.add(othello.copy());
		// LAUNCH THE GUI
		stage.show();

		// This is to resize the the scene according to the size change
		pane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				radius = (double) newValue / 50;
				othello.setRadius(radius);
				for (int i = 0; i < Othello.DIMENSION; i++) {
					for (int j = 0; j < Othello.DIMENSION; j++) {
						buttons.get(8*i + j).setPrefHeight((double)newValue/2);
						score.setPrefHeight((double)newValue/25);
						if(buttons.get(8*i +j).getGraphic() instanceof Circle) {
							Circle c = (Circle) buttons.get(8 * i + j).getGraphic();
							c.setRadius(radius);
						}
					}
				}
			}
		});
		pane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				radius = (double)newValue / 30;
				for (int i = 0; i < othello.DIMENSION; i++) {
					for (int j = 0; j < othello.DIMENSION; j++) {
						buttons.get(8*i + j).setPrefWidth((double)newValue/2);
					}
				}
			}
		});



		// Create a Grid Layout
	}

	private GridPane getBoard(Othello othello, Alert winner, GameTimer gametimer1) {
		while(!buttons.isEmpty()){
			buttons.remove(0);
		}
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(8);
		int dimension = Othello.DIMENSION;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				String token = String.valueOf(othello.getToken(i, j));
				Button button = new Button();
				button.setPrefSize(60, 60);
				button.setOnAction(new OthelloApplicationEventHandler(othello, i, j, winner,
						opponent, gametimer1, this
				));
				button.setStyle("-fx-background-color: #05B300");
				button.getStyleClass().add("button-grid");
				if (i == 4 && j == 2) {
					button.setStyle("-fx-background-color: #00cc99");
				}
				if (i == 2 && j == 4) {
					button.setStyle("-fx-background-color: #00cc99");
				}
				if (i == 3 && j == 5) {
					button.setStyle("-fx-background-color: #00cc99");
				}
				if (i == 5 && j == 3) {
					button.setStyle("-fx-background-color: #00cc99");
				}
				DropShadow shadow = createshadow(10, 10, 5, 5, 10);
				Circle circle1 = new Circle(radius);
				Circle circle2 = new Circle(radius);
				circle1.setStyle("-fx-fill: BLACK; ");
				circle2.setStyle("-fx-fill: WHITE;");
				if (token.equals("X")) {
					circle1.setEffect(shadow);
					button.setGraphic(circle1);
				} else if (token.equals("O")) {
					circle2.setEffect(shadow);
					button.setGraphic(circle2);
				}
				buttons.add(button);
				grid.add(button, i, j);
			}
		}
		return grid;
	}




		//Create Timer

			public DropShadow createshadow( long width, long height, long xcor, long ycor, long rad){
				DropShadow ds = new DropShadow();
				ds.setWidth(width);
				ds.setHeight(height);
				ds.setOffsetX(xcor);
				ds.setOffsetY(ycor);
				ds.setRadius(rad);
				return ds;
			}

			public static void main(String[] args){
//				OthelloApplication view = new OthelloApplication();
				launch(args);
			}

			private Othello returnOthello() {
				return this.othello;
			}
		}


