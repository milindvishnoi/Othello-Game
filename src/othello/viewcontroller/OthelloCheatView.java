package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observer;
import ca.utoronto.utm.util.Observable;
import javafx.animation.*;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Observer class for Cheater mode
 */
public class OthelloCheatView implements Observer {

    ArrayList<Button> buttons;
    Othello othello;

    public OthelloCheatView(ArrayList<Button> buttons, Othello othello) {
        this.buttons = buttons;
        this.othello = othello;
    }

    @Override
    public void update(Observable o) {
        if (OthelloApplication.cheat) {
            HintEventHandler cheaterMode = new HintEventHandler(this.othello, this.buttons);
            cheaterMode.getGreedy();
        }
    }
    public void updateOthello(Othello othello) {
        this.othello = othello;
        this.update(this.othello);
    }
}
