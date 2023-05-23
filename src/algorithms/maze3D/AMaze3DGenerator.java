package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    @Override
    public abstract Maze3D generate(int depth, int row, int column);

    /**
     * this method checks and calculates how long in takes in millis to generate a maze.
     * @param columns : int represents the columns in the maze.
     * @param rows : int represents the rows in the maze.
     * @return : the time it took to generate the maze.
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int rows, int columns) {
        long start = System.currentTimeMillis();
        generate(depth,rows,columns);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
