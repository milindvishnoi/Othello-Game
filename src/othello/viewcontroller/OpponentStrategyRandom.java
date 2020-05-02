package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerRandom;
/**
 * Opponent Strategy Random class
 *
 * @author parshva
 *
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
