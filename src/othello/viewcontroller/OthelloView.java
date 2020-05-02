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
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class OthelloView implements Observer {

    /**
     * This class contains the main OthelloView objects
     */

    RotateTransition flipOthelloChip = null;
    ArrayList<Integer> flipCircle = new ArrayList<Integer>();
    ArrayList<Button> buttons;
    Othello othello;
    TextField xScore;
    TextField oScore;
    HBox score;
    double radius;
    OthelloApplication oa = new OthelloApplication();

    /**
     *
     * @param buttons
     * @param xScore
     * @param oScore
     * @param score
     * @param othello
     * Initialize the Othello View
     */
    public OthelloView (ArrayList<Button> buttons, TextField xScore, TextField oScore,
                        HBox score, Othello othello){
        this.buttons = buttons;
        this.othello = othello;
        this.xScore = xScore;
        this.oScore = oScore;
        this.score = score;
    }

    /**
     *
     * @param o
     * Called every time there is a change in the Observable, to update different elements of the GUI
     */
    @Override
    public void update(Observable o) {
        //this will be called everytime there's a valid move done from the
        //OthelloApplicationEventHandler, so just go through all items in grid
        //and update the text for each button as necessary
        this.radius = othello.getRadius();
        int counter = 0;
        int numberOfX = 0;
        int numberOfO = 0;

        for(int i = 0; i < this.buttons.size(); i++) {
           this.buttons.get(i).setStyle("-fx-background-color: #05B300");
           this.buttons.get(i).setGraphic(null);
        }
        for (int col = 0; col < othello.DIMENSION; col++) {
            for (int row = 0; row < othello.DIMENSION; row++) {
                // This portion of the code runs through the pieces on the othello board and returns the amount of
                // each player

                if(othello.hasMove(row, col, othello.getWhosTurn())) {
                    if(this.othello.getWhosTurn() == OthelloBoard.P1) {
                        //this.buttons.get(counter).setStyle("-fx-background-color: #00ff00");
                        this.buttons.get(counter).setStyle("-fx-background-color: #00cc99");
                    } else{
                        this.buttons.get(counter).setStyle("-fx-background-color: #00cc99");
                    }
                }

               if(othello.getToken(row, col) == OthelloBoard.P1) {
                   numberOfX++;
                   Node token = getCircle(OthelloBoard.P1, row, col);
                   this.xScore.setText("X : "+ numberOfX);
                   this.buttons.get(counter).setGraphic(token);
               } else if(othello.getToken(row, col) == OthelloBoard.P2) {
                    numberOfO++;
                    this.oScore.setText("O: " + numberOfO);
                    Node token = getCircle(OthelloBoard.P2, row, col);
                    this.buttons.get(counter).setGraphic(token);
                }

                counter++;
            }
        }
    }


    /**
     *
     * @param whichPlayer
     * @param row
     * @param col
     * @return Circle
     * Creates the tokens to be placed on the OthelloBoard
     */
    private Circle getCircle(char whichPlayer, int row, int col){
        DropShadow shadow = oa.createshadow(10,10,5,5,10);
        Circle circle1 = new Circle(this.radius);
        circle1.setId(""+row+col);
        Circle circle2 = new Circle(this.radius);
        circle2.setId(""+row+col);
        circle1.setStyle("-fx-fill: BLACK");
        circle2.setStyle("-fx-fill: WHITE;");
        circle1.setEffect(shadow);
        circle2.setEffect(shadow);
        if (whichPlayer == OthelloBoard.P1) {
            return circle1;
        }
        return circle2;
    }

}
