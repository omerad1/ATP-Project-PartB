package algorithms.maze3D;

public interface IMazeGenerator3D {
    /**
     *generate 3D maze in size depth*row*column
     */
    Maze3D generate(int depth, int row, int column);

    /**
     * calculates the number of milliseconds it takes to generate a maze
     */
    long measureAlgorithmTimeMillis (int depth, int rows, int columns);

}
