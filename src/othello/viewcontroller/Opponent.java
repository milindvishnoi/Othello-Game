package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
/**
 * Opponent is an interface that creates different strategies
 *
 * @author parshva
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
