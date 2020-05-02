package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javax.swing.*;
import java.util.ArrayList;

/**
 * HintEventHandler is an event handler that gets a strategic hint when the hint button is clicked.
 *
 * @author parshva
 *
 */
public class HintEventHandler implements EventHandler<ActionEvent> {
    Othello othello;
    int row;
    int col;
    ArrayList<Button> buttons;
    Opponent nullOpponent;
    public HintEventHandler(Othello othello, ArrayList<Button> buttons) {
        this.othello = othello;

        this.buttons = buttons;
    }

    public void getGreedy() {
        OpponentStrategyStrategic oStrategic = new OpponentStrategyStrategic();
        Move strategicMove = oStrategic.copiedMove(this.othello.copy());
        //MakeMoveGreedy greedyMove = new MakeMoveGreedy(this.othello.copy());
        this.row = strategicMove.getRow();
        this.col = strategicMove.getCol();
        int index = this.col * 8 + this.row;
        this.buttons.get(index).setStyle("-fx-background-color: GOLD");

    }
    public void handle(ActionEvent event) {
        this.getGreedy();
    }
}

