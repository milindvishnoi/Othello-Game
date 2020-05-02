package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
/**
 * PlayerStrategy makes a move by considering all possible corner moves first and
 * makes a move there if possible.
 *
 * Second option, the sides. Scans through all edges
 * and makes the best edge move which flips the most tokens.
 *
 * Third option is it plays
 * a move that maximizes it's presence in the middle 4x4 portion of the board.
 *
 * Lastly, it attempts to play a greedy move, i.e anywhere that gives it maximum tokens.
 *
 * @author gautam
 *
 */
public class PlayerStrategic extends PlayerGreedy {
    ArrayList<Move> am = new ArrayList<>();

    public PlayerStrategic(Othello othello, char player) {
        super(othello, player);
    }

    /**
     * Checks if there is a corner move first, then side move, then a move that
     * maximizes the middle 4x4 square, finally a greedy move and returns a move
     * object accordingly.
     *
     * @return Move object
     */
    @Override
    public Move getMove() {
        Othello othelloCopy = othello.copy();
        int bestMoveCount = othelloCopy.getCount(this.player);
        int checker = 0;
        Move bestMove;
        if(getCorner() != null){return getCorner();}
        else if(getSides() != null){return getSides();}
        else if(getfourbyfour() != null){return getfourbyfour();}
        else if(super.getMove() != null){return super.getMove();}
        return null;
    }

    /**
     * Checks if there is a Strategic corner move
     *
     * @return Move object
     */
    private Move getCorner() {
        Othello othelloCopy = othello.copy();
        int bestMoveCount = othelloCopy.getCount(this.player);
        Move bestMove = null;
        am.add(new Move(0, 0));
        am.add(new Move(0, othello.DIMENSION - 1));
        am.add(new Move(othello.DIMENSION - 1, 0));
        am.add(new Move(othello.DIMENSION - 1, othello.DIMENSION - 1));
        for(Move m : am){
            othelloCopy = othello.copy();
            if (othelloCopy.move(m.getRow(), m.getCol()) && (othelloCopy.getCount(this.player) > bestMoveCount)) {
                bestMoveCount = othelloCopy.getCount(this.player);
                bestMove = new Move(m.getRow(), m.getCol());
            }
        }
        am.clear();
        return bestMove;
    }

    /**
     * Checks if there is a Strategic side move
     *
     * @return Move object
     */
    private Move getSides(){
        Othello othelloCopy = othello.copy();
        int bestMoveCount = othelloCopy.getCount(this.player);
        Move bestMove = null;
        for(int col = 0; col < othello.DIMENSION; col++){
            am.add(new Move(0, col));
            am.add(new Move(othello.DIMENSION - 1, col));
        }
        for(int row = 0; row < othello.DIMENSION; row++){
            am.add(new Move(row, 0));
            am.add(new Move(row, othello.DIMENSION - 1));
        }
        for(Move m: am){
            othelloCopy = othello.copy();
            if (othelloCopy.move(m.getRow(), m.getCol()) && (othelloCopy.getCount(this.player) > bestMoveCount)) {
                bestMoveCount = othelloCopy.getCount(this.player);
                bestMove = new Move(m.getRow(), m.getCol());
            }
        }
        am.clear();
        return bestMove;
    }

    /**
     * Checks if there is a Strategic move to maximize it's presence in the middle 4x4
     *
     * @return Move object
     */
    private Move getfourbyfour(){
        Othello othelloCopy = othello.copy();
        int bestCount = 0;
        Move bestMove = null;
        for(int col = 0; col <= 5; col++){
            am.add(new Move(2,col));
            am.add(new Move(5,col));
        }
        for(int row = 0; row <= 5; row++){
            am.add(new Move(row,2));
            am.add(new Move(row, 5));
        }
        for(Move m:  am){
            if (othelloCopy.move(m.getRow(), m.getCol()) && (getcountfourbyfour(this.player) > bestCount)) {
                bestCount = getcountfourbyfour(this.player);
                bestMove = new Move(m.getRow(), m.getCol());
            }
        }
        if(bestCount >= 8){
            am.clear();
            return bestMove;
        }
        return null;
    }

    /**
     * Helper function for getfourbyfour() to return the number of tokens of the player in the middle 4x4
     *
     * @return number of tokens of current player in middle 4x4
     */
    private int getcountfourbyfour(char player){
        int count = 0;
        for(int row = 2; row <= 5 ; row++){
            for(int col = 2; col <= 5; col++){
                if(othello.getToken(row, col) == player){
                    count += 1;
                }
            }
        }
        return count;
    }
}