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
import ca.utoronto.utm.othello.model.PlayerRandom;
/**
 * Opponent Strategy Random class
 */
public class OpponentStrategyRandom implements OpponentStrategy {
    @Override
    public void nextCommand(Othello othello) {
        PlayerRandom playerRandom = new PlayerRandom(othello, othello.getWhosTurn());
        Move randomMove = playerRandom.getMove();
        othello.move(randomMove.getRow(), randomMove.getCol());
    }

    @Override
    public Move copiedMove(Othello othello) {
        PlayerRandom playerRandom = new PlayerRandom(othello.copy(), othello.getWhosTurn());
        Move randomMove = playerRandom.getMove();
       return randomMove;
    }

}
