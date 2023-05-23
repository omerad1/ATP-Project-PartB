package algorithms.mazeGenerators;
import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{

    // Instance variables
    private int _rows; // number of rows in the maze
    private int cols; // number of columns in the maze
    private int[][] maze; // 2D array to store the maze

    @Override
    public Maze generate(int rows, int columns) {
        _rows = rows;
        cols = columns;

        // Create a new Maze object with the given number of rows and columns
        Maze temp = new Maze(rows, columns);
        maze = temp.getMaze(); // Get the maze as a 2D array

        // Create a new Random object to shuffle the directions
        Random rand = new Random();

        // Create a list of directions as integer arrays
        List<int[]> directions = new ArrayList<>(List.of(new int[]{0, 1}, new int[]{1, 0}));

        // Initialize the current position to (0, 0) and mark it as visited
        int[] current = {0, 0};
        maze[0][0] = 2;

        // While we have not reached the bottom-right corner of the maze
        while (current[0] != _rows - 1 || current[1] != cols - 1) {
            // Shuffle the list of directions to explore in random order
            Collections.shuffle(directions);
            // Check if we can move in the first direction
            if (CanMove(current[0] + directions.get(0)[0], current[1] + directions.get(0)[1])) {
                // If we can move, update the current position and mark it as visited
                current[0] += directions.get(0)[0];
                current[1] += directions.get(0)[1];
                maze[current[0]][current[1]] = 2;
            }
        }

        // After we have explored the entire maze, randomly generate walls and passages
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (maze[i][j] != 2)
                    // If the cell is not part of the path, randomly mark it as a wall or a passage
                    maze[i][j] = rand.nextInt(2);
                else
                    // If the cell is part of the path, mark it as a passage
                    maze[i][j] = 0;
            }
        }

        return temp; // Return the generated maze
    }

    // Helper method to check if a given position is a valid move
    private boolean CanMove(int r, int c){
        return r >= 0 && c >= 0 && r < _rows && c < cols && maze[r][c] != 2;
    }
}
