package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.Optional;

public class OthelloApplicationEventHandler implements EventHandler<ActionEvent> {
    private Othello othello;
    private int column;
    private int row;
    private TextField output;
    private Alert winner;
    private Opponent opponent;
    private GameTimer gameTimer;
    private OthelloApplication othelloApplication;

    public OthelloApplicationEventHandler(Othello othello, int col, int row, Alert winner,
                                          Opponent opponent, GameTimer gameTimer,
                                          OthelloApplication othelloApplication
                                          ) {
        this.othello = othello;
        this.column = col;
        this.row = row;
        this.winner = winner;
        this.opponent = opponent;
        this.gameTimer = gameTimer;
        this.othelloApplication = othelloApplication;
    }

    private void callWinner() throws Exception {
        if (this.othello.isGameOver()) {
            this.winner.setTitle("The Game is Over!");
            if (this.othello.getWinner() =='X'){
                this.winner.setContentText("The Winner is: Player 1");
            }else if(this.othello.getWinner() == 'O'){
                this.winner.setContentText("The Winner is: Player 2");
            }else{
                this.winner.setContentText("It is a Tie");
            }
            Optional<ButtonType> result = this.winner.showAndWait();

            if (result.get() == ButtonType.OK) {
                OthelloApplication.currentStage.close();
                OthelloApplication.cheat = false;
                OthelloApplication.player1 = "Human";
                OthelloApplication.player2 = "Human";
                this.othelloApplication.start(new Stage());

            }
        }
    }

    public void handle(ActionEvent event) {
        if (!this.othello.isGameOver()) {
            char currentTurn = this.othello.getWhosTurn();
            boolean moved = this.othello.move(this.row, this.column);
            if (moved && !this.othello.isGameOver()) {
                OthelloApplication.moveStack.add(this.othello.copy());
                if (this.othello.getWhosTurn() == OthelloBoard.otherPlayer(currentTurn)) {
                    this.opponent.move(this.othello);
                } else {
                    try {
                        callWinner();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.gameTimer.changePlayer();
            }
            try {
                callWinner();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}