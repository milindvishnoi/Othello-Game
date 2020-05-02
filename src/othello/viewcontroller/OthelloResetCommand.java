package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.stage.Stage;

/**
 * Command implementation of Reset method
 */
public class OthelloResetCommand implements OthelloCommand {

    private Othello othello;
    private OthelloApplication othelloApplication;
    private OthelloApplication newGame;

    public OthelloResetCommand(OthelloApplication othelloApplication) {
        this.othelloApplication = othelloApplication;
        this.newGame = new OthelloApplication();
    }

    /**
     * Resetting the stack by clearing the moveStack and setting both player 1 and 2 to human
     * @return
     */


    @Override
    public void execute() throws Exception {
        OthelloApplication.currentStage.close();
        OthelloApplication.cheat = false;
        OthelloApplication.player1 = "Human";
        OthelloApplication.player2 = "Human";
        this.newGame.start(new Stage());

    }
}
