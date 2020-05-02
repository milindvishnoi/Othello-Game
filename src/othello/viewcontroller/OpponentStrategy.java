package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
/**
 * Opponent Strategy main class
 *
 * @author parshva
 *
 */
public interface OpponentStrategy {

        public void nextCommand(Othello othello);

        public Move copiedMove(Othello othello);



    }

