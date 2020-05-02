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
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * GameTimer class that creates 2 timers for each player and handles pausing timer
 * and restarting when moves are made.
 *
 */
public class GameTimer{

    private char currPlayer;
    private Timeline timer1, timer2;
    private Label minsAndSecs1, minsAndSecs2;
    private Othello othello;
    private TextField player1, player2;

    public GameTimer(Othello othello, int P1InMins, int P1InSecs, int P2InMins, int P2InSecs,
                     TextField player1, TextField player2) {
        this.othello = othello;
        this.currPlayer = othello.getWhosTurn();
        this.minsAndSecs1 = new Label("00");
        this.minsAndSecs2 = new Label("00");
        this.player1 = player1;
        this.player2 = player2;
        minsAndSecs1.setTextFill(Color.web("white"));
        minsAndSecs1.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");
        minsAndSecs1.setDisable(true);
        minsAndSecs1.setAlignment(Pos.CENTER);

        minsAndSecs2.setTextFill(Color.web("white"));
        minsAndSecs2.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");
        minsAndSecs2.setDisable(true);
        minsAndSecs2.setAlignment(Pos.CENTER);

        timer1 = new Timeline(new KeyFrame(Duration.seconds(1),
                new TimeHandler(minsAndSecs1, othello,P1InSecs, P1InMins, this.othello.getBoard().P1)));

        timer2 = new Timeline(new KeyFrame(Duration.seconds(1),
                new TimeHandler(minsAndSecs2, othello, P2InSecs, P2InMins, this.othello.getBoard().P2)));

        timer1.setCycleCount(Animation.INDEFINITE);
        timer2.setCycleCount(Animation.INDEFINITE);
        timer1.play();
    }

    public void changePlayer() {
        if (this.currPlayer == OthelloBoard.P1 && this.player1.getText().equals("Human")) {
            timer1.stop();
            timer2.play();
            this.currPlayer = OthelloBoard.P2;
        } else if (this.currPlayer == OthelloBoard.P2 && this.player2.getText().equals("Human")){
            timer2.stop();
            timer1.play();
            this.currPlayer = OthelloBoard.P1;
        }
    }


    /**
     * Helper function which is used in OthelloApplication to update the GUI.
     *
     * @return HBox of the time
     *
     */
    public VBox getGUI() {
        VBox timeGUI = new VBox();
//        timeGUI.setSpacing(3);
        TextField timehead = new TextField("TIME");
        TextField top = new TextField("P1:");

        TextField middle = new TextField("P2:");
        TextField placeholder = new TextField("");
        timeGUI.setAlignment(Pos.CENTER);

        timehead.setMaxWidth(80);
        timehead.setDisable(true);
        timehead.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;-fx-font: 16 arial;");

//        top.setMaxWidth(80);
        top.setDisable(true);
        top.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");

        placeholder.setDisable(true);
        placeholder.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");

//        middle.setMaxWidth(37);
        middle.setDisable(true);
        middle.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;");

        timeGUI.getChildren().add(timehead);
        timeGUI.getChildren().add(top);
        timeGUI.getChildren().add(this.minsAndSecs1);
        timeGUI.getChildren().add(placeholder);
        timeGUI.getChildren().add(middle);
        timeGUI.getChildren().add(this.minsAndSecs2);
        return timeGUI;
    }
}
