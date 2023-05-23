package algorithms.mazeGenerators;

import java.util.Arrays;

public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * simply creates an empty maze, need only to initialize it using the maze class
     */
    @Override
    public Maze generate(int rows, int columns) {
        return new Maze(rows, columns);
    }
}
