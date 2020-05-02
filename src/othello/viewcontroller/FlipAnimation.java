package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.management.timer.Timer;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.TimerTask;
/**
 * Observer class to implement flipping animation when a move is made.
 *
 * @author milind
 *
 */
public class FlipAnimation implements Observer {
    Othello othello;
    ArrayList<Button> buttons;
    String whosPlayer1, whosPlayer2;


    FlipAnimation(Othello othello, ArrayList<Button> buttons, String whosPlayer1, String whosPlayer2){
        this.othello = othello;
        this.buttons = buttons;
        this.whosPlayer1 = whosPlayer1;
        this.whosPlayer2 = whosPlayer2;
    }

    @Override
    public void update(Observable o) {
        try {
            ArrayList<String> flippedTokens = othello.getFlippedCoins();
            ArrayList<Button> circleList = new ArrayList<>();
            for (int row = 0; row < othello.DIMENSION; row++) {
                for (int col = 0; col < othello.DIMENSION; col++) {
                    if (flippedTokens.contains("" + row + col)) {
                        int temp = row * 8 + col;
                        circleList.add(buttons.get(temp));
                    }
                }
            }
            RotateTransition flipOthelloChip;
            for (Button button : circleList) {
                flipOthelloChip = new RotateTransition(Duration.millis(500), button.getGraphic());
                flipOthelloChip.setAxis(Rotate.Y_AXIS);
                flipOthelloChip.setFromAngle(180);
                flipOthelloChip.setToAngle(360);
                flipOthelloChip.setInterpolator(Interpolator.LINEAR);
                flipOthelloChip.setCycleCount(1);
                flipOthelloChip.play();
            }
        }catch (Exception e){}
    }
}
