package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerStrategic;
/**
 * Opponent Strategy Strategic class
 *
 * @author parshva
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
