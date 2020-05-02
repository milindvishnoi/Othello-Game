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
/**
 * Opponent is an interface that creates different strategies
 *
 */
public class Opponent {
    private OpponentStrategy strategy;
    public Opponent() {
        this.strategy = new OpponentStrategyHuman();
    }

    public void setStrategy(OpponentStrategy strategy) {
        this.strategy = strategy;
    }

    public void move(Othello othello) {
        this.strategy.nextCommand(othello);
    }
}
