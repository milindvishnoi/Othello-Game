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
import ca.utoronto.utm.othello.model.PlayerStrategic;
/**
 * Opponent Strategy Strategic class
 *
 */
public class OpponentStrategyStrategic implements OpponentStrategy {

    @Override
    public void nextCommand(Othello othello) {
        PlayerStrategic strategicPlayer = new PlayerStrategic(othello, othello.getWhosTurn());
        Move strategicMove = strategicPlayer.getMove();
        othello.move(strategicMove.getRow(), strategicMove.getCol());
    }

    @Override
    public Move copiedMove(Othello othello) {
        PlayerStrategic strategicPlayer = new PlayerStrategic(othello.copy(), othello.getWhosTurn());
        Move strategicMove = strategicPlayer.getMove();
        return strategicMove;
    }
}
