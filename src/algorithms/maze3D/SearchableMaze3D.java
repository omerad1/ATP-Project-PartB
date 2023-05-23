package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;

    /**
     * constructor
     */
    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
    }

    /**
     * a getter for the startState of the maze
     */
    @Override
    public AState getStartState() {
        Position3D start = maze.getStartPosition();
        return new Maze3DState(start.getDepth_index(), start.getRow_index(), start.getColumn_index(), 0);
    }

    /**
     * a getter for the goalState of the maze
     */
    @Override
    public AState getGoalState() {
        Position3D goal = maze.getGoalPosition();
        return new Maze3DState(goal.getDepth_index(), goal.getRow_index(), goal.getColumn_index(), 0);
    }

    /**
     Returns an ArrayList of all possible states from the given state.
     The method checks each possible direction the state can move to and creates a new Maze3DState
     for each valid direction with the updated depth, row, column, and weight based on the given state.
     If the given state is null, null is returned.
     @param state the current state
     @return an ArrayList of all possible states from the given state.
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if(state == null)
            return null;
        ArrayList<AState> states = new ArrayList<>();
        int depth = maze.getDepth() -1;
        int row = maze.getRows() - 1;
        int col = maze.getColumns() - 1;

        int[][][] board = maze.getMap(); // the maze board

        int s_d = ((Maze3DState)state).getDepth(); // state depth
        int s_r = ((Maze3DState)state).getRow(); // state row
        int s_c = ((Maze3DState)state).getCol(); // state column
        int weight = ((Maze3DState) state).getWeight() + 10;

        // ↑
        if (s_r > 0 && board[s_d][s_r-1][s_c] == 0){
            Maze3DState state1 = new Maze3DState(s_d,s_r-1, s_c, weight);
            states.add(state1);
        }
        // →
        if (s_c<col && board[s_d][s_r][s_c+1] == 0){
            Maze3DState state2 = new Maze3DState(s_d,s_r, s_c+1, weight);
            states.add(state2);
        }
        // ↓
        if (s_r<row && board[s_d][s_r+1][s_c] == 0){
            Maze3DState state3 = new Maze3DState(s_d,s_r+1, s_c, weight);
            states.add(state3);
        }
        // ←
        if (s_c > 0 && board[s_d][s_r][s_c - 1] == 0){
            Maze3DState state4 = new Maze3DState(s_d,s_r, s_c-1, weight);
            states.add(state4);
        }
        // ↖
        if (s_d > 0 && board[s_d-1][s_r][s_c] == 0){
            Maze3DState state5 = new Maze3DState(s_d-1,s_r, s_c, weight);
            states.add(state5);
        }
        // ↙
        if (s_d < depth && board[s_d+1][s_r][s_c] == 0){
            Maze3DState state6 = new Maze3DState(s_d+1,s_r, s_c, weight);
            states.add(state6);
        }
        return states;
    }
}
