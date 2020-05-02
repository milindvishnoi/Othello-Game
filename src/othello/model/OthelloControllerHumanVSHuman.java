package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;

/**
 * Run the main from this class to play two humans against each other. Only
 * minimal modifications to this class are permitted.
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSHuman extends OthelloControllerVerbose {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman() {
		super();
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this.othello, OthelloBoard.P2);
	}

	public void update(Observable o) {
		Othello othello = (Othello)o;
		if(othello.getWhosTurn() == OthelloBoard.P1) {


		}
		return;
	}

	/**
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSHuman oc = new OthelloControllerHumanVSHuman();
		oc.play();
	}

}


