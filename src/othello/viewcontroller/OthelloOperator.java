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
import java.util.ArrayList;

/**
 * Operator keeps track of all the inputs of the game
 */
public class OthelloOperator {
    ArrayList<OthelloCommand> commandQueue;

    public OthelloOperator() {
        commandQueue = new ArrayList<OthelloCommand>();
    }

    public void acceptCommand(OthelloCommand command) {
        this.commandQueue.add(command);
    }

    void operateAll() throws Exception {
        for (OthelloCommand command: this.commandQueue) {
            command.execute();
        }
        commandQueue.clear();
    }
}
