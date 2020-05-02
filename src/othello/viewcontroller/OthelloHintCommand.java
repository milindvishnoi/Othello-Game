package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Command implementation of Hint method for Othello
 */
public class OthelloHintCommand implements OthelloCommand {
    Othello othello;
    ArrayList<Button> buttons;
    int row;
    int col;
    public OthelloHintCommand(Othello othello, ArrayList<Button> buttons) {
        this.othello = othello;
        this.buttons = buttons;
    }

    @Override
    public void execute() throws Exception {
        OpponentStrategyStrategic oStrategic = new OpponentStrategyStrategic();
        Move strategicMove = oStrategic.copiedMove(this.othello.copy());
        //MakeMoveGreedy greedyMove = new MakeMoveGreedy(this.othello.copy());
        this.row = strategicMove.getRow();
        this.col = strategicMove.getCol();
        int index = this.col * 8 + this.row;
        this.buttons.get(index).setStyle("-fx-background-color: GOLD");
    }
}
