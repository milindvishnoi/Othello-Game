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

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerGreedy;
/**
 * Opponent Strategy Greedy main class
 *
 */
public class OpponentStrategyGreedy implements OpponentStrategy{

    @Override
    public void nextCommand(Othello othello) {
        PlayerGreedy playerGreedy = new PlayerGreedy(othello, othello.getWhosTurn());
        Move greedyMove = playerGreedy.getMove();
        othello.move(greedyMove.getRow(), greedyMove.getCol());
    }

    @Override
    public Move copiedMove(Othello othello) {
        PlayerGreedy playerGreedy = new PlayerGreedy(othello.copy(), othello.getWhosTurn());
        Move greedyMove = playerGreedy.getMove();
        return greedyMove;
    }
}
