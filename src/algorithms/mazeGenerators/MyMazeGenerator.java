package algorithms.mazeGenerators;
import java.util.*;
import java.util.List;


public class MyMazeGenerator extends AMazeGenerator {
    private Maze maze;
    private enum Direction{ UP, DOWN, RIGHT, LEFT }

    /**
     * generates a complex maze using random dfs
     * in case that the maze was created without possible solution it will run again until success
     */
    @Override
    public Maze generate(int rows, int columns) {
        maze = new Maze(rows,columns);
        boolean mazeSuccess = randomDfs();
        while (!mazeSuccess)
            mazeSuccess = randomDfs();
        return maze;
    }

    /**
     * the main function in the class, generates the maze based on random dfs
     * @return true if the maze created has a solution, false otherwise
     */
    private boolean randomDfs()
    {
        // helper variables
        int[] start = {0,0};
        int[] goal = {maze.getRows()-1, maze.getColumns()-1};
        int[][] mazeArr = maze.getMaze();
        int[] next;

        for(int i = 0; i< maze.getRows() ; i++) // initialize maze full of walls
            Arrays.fill(mazeArr[i],1);

        mazeArr[0][0] = 0; // choose the initial cell and marked it as visited.
        Stack<int[]> stack = new Stack<>();
        stack.push(start); // push the start cell to the stack

        while(!stack.empty()){
            int[] curr = stack.pop();  // pop next cell
            if(Arrays.equals(curr, goal)) continue; // ensures only one entrance to goal
            List<int[]> neg = Neighbors(curr[0],curr[1]); // list of the neighbors
            if(neg.size() != 0){
                stack.push(curr);
                Collections.shuffle(neg);
                next = neg.get(0);
                mazeArr[next[0]][next[1]] = 0;
                stack.push(next);
            }
        }
        return (mazeArr[goal[0]][goal[1]] != 1);
    }


    /**
     * this function receives a position in the maze and finds its neighbors that aren't already visited
     * @param row : the position row
     * @param col : the position col
     * @return a list of the neighbors that can be visited from that position in int[] representation
     */
    private List<int[]> Neighbors(int row, int col) { // [row,col]

        List<int[]> neg = new ArrayList<>();
        if(row > 0 && can_visit_neighbor(row-1,col, Direction.DOWN))
            neg.add(new int[] {row-1,col}); // ↑
        if(row < maze.getRows()-1 && can_visit_neighbor(row+1,col, Direction.UP))
            neg.add(new int[] {row+1,col}); // ↓
        if(col < maze.getColumns() -1 && can_visit_neighbor(row,col+1, Direction.LEFT))
            neg.add(new int[] {row,col+1}); // →
        if(col > 0 && can_visit_neighbor(row,col-1, Direction.RIGHT))
            neg.add(new int[] {row,col-1}); // ←
        return neg;
    }


    /**
     * method that checks if given neighbor can be visited
     * @param r : index of the neighbor row
     * @param c : index of the neighbor column
     * @param cameFrom : the direction we have come from
     * @return : true if the neighbor can be visited and false if not
     */
    private boolean can_visit_neighbor(int r , int c, Direction cameFrom){
        int [][] temp = maze.getMaze();
        if(cameFrom == Direction.UP)
            return temp[r][c] == 1 && (r == maze.getRows()-1 || temp[r+1][c] == 1) && (c == maze.getColumns()-1 || temp[r][c+1] == 1) && (c == 0 || temp[r][c-1] == 1);
         if(cameFrom == Direction.DOWN)
            return (temp[r][c] == 1 && (r == 0 || temp[r-1][c] == 1) && (c == maze.getColumns()-1 || temp[r][c+1] == 1) && (c == 0 || temp[r][c-1] == 1));
         if(cameFrom == Direction.RIGHT)
            return (temp[r][c] == 1 && (r == maze.getRows()-1 || temp[r+1][c] == 1) && (r == 0 || temp[r-1][c] == 1) && (c == 0 || temp[r][c-1] == 1));
         return (temp[r][c] == 1 && (r == maze.getRows()-1 || temp[r+1][c] == 1) && (r == 0 || temp[r-1][c] == 1) && (c == maze.getColumns()-1 || temp[r][c+1] == 1));
    }

}