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
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observer;
import ca.utoronto.utm.util.Observable;
import javafx.animation.*;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Observer class for Cheater mode
 */
public class OthelloCheatView implements Observer {

    ArrayList<Button> buttons;
    Othello othello;

    public OthelloCheatView(ArrayList<Button> buttons, Othello othello) {
        this.buttons = buttons;
        this.othello = othello;
    }

    @Override
    public void update(Observable o) {
        if (OthelloApplication.cheat) {
            HintEventHandler cheaterMode = new HintEventHandler(this.othello, this.buttons);
            cheaterMode.getGreedy();
        }
    }
    public void updateOthello(Othello othello) {
        this.othello = othello;
        this.update(this.othello);
    }
}
