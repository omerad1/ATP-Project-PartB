package algorithms.maze3D;

import algorithms.search.AState;

import java.util.Comparator;
import java.util.Objects;


public class Maze3DState extends AState {

    private final int row;
    private final int col;
    private final int depth;


    public Maze3DState(int depth, int row, int col, int weight) {
        this.row = row;
        this.col = col;
        this.depth = depth;
        this.weight = weight;
    }

    @Override
    public String toString(){
        String d = Integer.toString(depth);
        String r = Integer.toString(row);
        String c = Integer.toString(col);
        return "{"+d+","+r+","+c+"}";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDepth() {
        return depth;
    }

    public int getWeight(){
        return this.weight;
    }
    /**
     * equals override for mazeState, checks if two states are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Maze3DState otherState = (Maze3DState)obj;
        return this.depth == otherState.getDepth() && this.row == otherState.getRow() && this.col == otherState.getCol();
    }

    /**
     * hashCode override for mazeState, create an hashCode for a state, used by solution in hashSet
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.depth,this.row, this.col);
    }


    static class StateComparator implements Comparator<Maze3DState> {

        @Override
        public int compare(Maze3DState o1, Maze3DState o2) {
            return Integer.compare(o1.weight,o2.weight);
        }
    }
}
