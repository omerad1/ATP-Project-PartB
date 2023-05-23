package algorithms.search;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class MazeState extends AState implements Serializable {
    private final int row;
    private final int col;

    /**
     * constructor
     *
     * @param row    : the state row
     * @param col    : the state column
     * @param weight : weight of the move to the state
     */
    public MazeState(int row, int col, int weight) {
        this.row = row;
        this.col = col;
        this.weight = weight;
    }

    /**
     * getter for the state row
     */
    public int getRow() {
        return row;
    }

    /**
     * getter for the state column
     */
    public int getCol() {
        return col;
    }

    /**
     * toString override for a mazeState
     */
    @Override
    public String toString() {
        return "{"+this.row+","+this.col+"}";
    }

    /**
     * equals override for mazeState, checks if two states are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        MazeState otherState = (MazeState)obj;
        return this.row == otherState.getRow() && this.col == otherState.getCol();
    }

    /**
     * hashCode override for mazeState, create an hashCode for a state, used by solution in hashSet
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }

}
