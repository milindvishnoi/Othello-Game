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

public class OthelloUndoCommand implements OthelloCommand {
    /**
     * This class contains the Command Implementation of the Undo Method
     */
    OthelloApplication oApplication;
    Othello undoOthello;

    /**
     *
     * @param othelloApplication
     * @param othello
     * Initialize the OthelloUndoCommand
     */

    public OthelloUndoCommand(OthelloApplication othelloApplication, Othello othello){
        this.oApplication = othelloApplication;
        this.undoOthello = othello;
    }

    /**
     *
     * @throws Exception
     * This gets triggered every time the user hits the Undo Button. Sets the othello state to the
     * one previous
     */
    @Override
    public void execute() throws Exception {
        if (OthelloApplication.moveStack.size() >= 2) {
            OthelloApplication.moveStack.remove(OthelloApplication.moveStack.size() - 1);
            this.undoOthello = OthelloApplication.moveStack.get(OthelloApplication.moveStack.size() - 1);
            this.oApplication.othello.changeState(this.undoOthello);
            this.oApplication.othello.notifyObservers();

            if (OthelloApplication.moveStack.size() == 1) {
                System.out.println(OthelloApplication.moveStack.get(0).getBoardString());
                this.undoOthello = OthelloApplication.moveStack.get(OthelloApplication.moveStack.size() - 1);
                OthelloApplication.moveStack.remove(OthelloApplication.moveStack.size() - 1);
                OthelloApplication.moveStack.add(this.undoOthello.copy());
                this.oApplication.othello.changeState(this.undoOthello);
                this.oApplication.othello.notifyObservers();


                if (OthelloApplication.player1.equals("Random")) {
                    OthelloApplication.opponent.move(this.oApplication.othello);
                }

                if (OthelloApplication.player1.equals("Greedy")) {
                    OthelloApplication.opponent.move(this.oApplication.othello);
                }
                if (OthelloApplication.player1.equals("Strategy")) {
                    OthelloApplication.opponent.move(this.oApplication.othello);
                }
            }
        }


        }
    }

