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
