package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{

    /**
     * this method checks and calculates how long in takes in millis to generate a maze.
     * @param columns : int represents the columns in the maze.
     * @param rows : int represents the rows in the maze.
     * @return : the time it took to generate the maze.
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start = System.currentTimeMillis();
        generate(rows,columns);
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    public abstract Maze generate (int rows, int columns);
}
