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
