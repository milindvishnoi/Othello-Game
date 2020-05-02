package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class TimeHandler implements EventHandler<ActionEvent> {
    /**
     * This class contains the functionality of the Timer Object
     */
    private int secs;
    private int mins;
    private Label label;
    private Othello othello;
    private char currPlayer;

    /**
     *
     * @param label
     * @param othello
     * @param initSecs
     * @param initMins
     * @param currPlayer
     * Initialize the TimeHandler
     */

    public TimeHandler(Label label, Othello othello, int initSecs, int initMins, char currPlayer) {
        this.othello = othello;
        this.label = label;
        this.secs = initSecs;
        this.mins = initMins;
        this.currPlayer = currPlayer;

        this.label.setText(initMins + ":" + initSecs);
    }

    /**
     *
     * @return Time in Seconds
     * Returns the second aspect of the time
     */

    public int getSecs(){
        return this.secs;
    }

    /**
     *
     * @return Time in Minutes
     * Return the minutes aspect of the time
     */
    public int getMins(){
        return this.mins;
    }

    @Override
    /**
     * Triggered every second to check if time has run out and to update the time;
     */
    public void handle(ActionEvent event) {
        if (this.mins == 0 && this.secs == 0) {
            this.othello.endGame(this.currPlayer);

        } else {
            if (this.secs == 0){
                this.secs = 59;
                this.mins--;
            } else {
                this.secs--;
            }

            String secsString = String.valueOf(this.secs);
            if (secsString.length() == 1) {secsString = "0" + secsString;}
            this.label.setText(this.mins + ":" + secsString);
        }

    }

}
