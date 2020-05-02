package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerGreedy;
/**
 * Opponent Strategy Greedy main class
 *
 * @author parshva
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
