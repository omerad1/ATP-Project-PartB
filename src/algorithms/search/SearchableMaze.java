package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    /**
     * constructor
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * a getter for the startState of the maze
     */
    @Override
    public AState getStartState() {
        Position start = maze.getStartPosition();
        return new MazeState(start.getRow_index(),start.getColumn_index(), 0);
    }

    /**
     * a getter for the goalState of the maze
     */
    @Override
    public AState getGoalState() {
        Position goal = maze.getGoalPosition();
        return new MazeState(goal.getRow_index(),goal.getColumn_index(), 0);
    }

    /**
     * given a state, returns all the possible states reachable from that state in this case
     * all the state that are valid(in the maze) and are not walls(1)
     * @param state : a given state that we want to find all the possible states from it
     * @return a list of possible states from the given state order clockwise starting from up
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if(state == null)
            return null;
        ArrayList<AState> states = new ArrayList<>();
        int row = maze.getRows() - 1;
        int col = maze.getColumns() - 1;

        int[][] board = maze.getMaze(); // the maze board

        int s_r = ((MazeState)state).getRow(); // state row
        int s_c = ((MazeState)state).getCol(); // state column

        //up
        if (s_r > 0 && board[s_r-1][s_c] == 0){
            MazeState state1 = new MazeState(s_r-1, s_c, state.weight+10);
            states.add(state1);
        }
        //up-right
        if (s_r > 0 && s_c<col && board[s_r-1][s_c+1] == 0 && (board[s_r-1][s_c] == 0 || board[s_r][s_c+1] == 0)){
            MazeState state2 = new MazeState(s_r-1, s_c+1, state.weight+15);
            states.add(state2);
        }
        //right
        if (s_c<col && board[s_r][s_c+1] == 0){
            MazeState state3 = new MazeState(s_r, s_c+1, state.weight+10);
            states.add(state3);
        }
        //down-right
        if (s_r<row && s_c<col && board[s_r+1][s_c+1] == 0 && (board[s_r+1][s_c] == 0 || board[s_r][s_c+1] == 0)){
            MazeState state4 = new MazeState(s_r+1, s_c+1, state.weight+15);
            states.add(state4);
        }
        //down
        if (s_r<row && board[s_r+1][s_c] == 0){
            MazeState state5 = new MazeState(s_r+1, s_c, state.weight+10);
            states.add(state5);
        }
        //down-left
        if (s_r<row && s_c > 0 &&board[s_r+1][s_c-1] == 0 && (board[s_r][s_c - 1] == 0 || board[s_r+1][s_c] == 0)){
            MazeState state6 = new MazeState(s_r+1, s_c-1, state.weight+15);
            states.add(state6);
        }
        //left
        if (s_c > 0 && board[s_r][s_c - 1] == 0){
            MazeState state7 = new MazeState(s_r,s_c - 1, state.weight+10);
            states.add(state7);
        }
        //up-left
        if (s_r > 0 && s_c > 0 && board[s_r - 1][s_c - 1] == 0 && (board[s_r-1][s_c] == 0 || board[s_r][s_c-1] == 0)){
            MazeState state8 = new MazeState(s_r-1, s_c-1, state.weight+15);
            states.add(state8);
        }
        return states;
    }

}
