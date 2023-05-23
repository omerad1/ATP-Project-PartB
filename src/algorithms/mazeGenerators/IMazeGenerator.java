package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     * generates a maze of size rows*columns
     */
    Maze generate (int rows, int columns );

    /**
     * calculates the number of milliseconds it takes to generate a maze
     */
    long measureAlgorithmTimeMillis (int rows, int columns);

}
