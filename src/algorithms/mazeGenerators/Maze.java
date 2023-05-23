package algorithms.mazeGenerators;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class Maze implements Serializable {
    private int[][] maze;
    private final Position startPosition;
    private final Position goalPosition;
    private final int rows;

    private final int columns;

    /**
     * constructor
     * @param columns : int represents the columns in the maze.
     * @param rows : int represents the rows in the maze.
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        maze = new int[rows][columns]; // filled with zeros by default
        startPosition = new Position(0,0);// default value of start position
        goalPosition = new Position(rows-1 , columns-1); // default value of goal position
    }

    public Maze(byte[] mazeInBytes) {
       ByteBuffer buffer_a = ByteBuffer.wrap(mazeInBytes, 0, 4);
       ByteBuffer buffer_b = ByteBuffer.wrap(mazeInBytes, 4, 4);
       startPosition = new Position(buffer_a.getInt(),buffer_b.getInt());
       buffer_a = ByteBuffer.wrap(mazeInBytes, 8, 4);
       buffer_b = ByteBuffer.wrap(mazeInBytes, 12, 4);
       goalPosition = new Position(buffer_a.getInt(),buffer_b.getInt());

       buffer_a = ByteBuffer.wrap(mazeInBytes, 16, 4);
       buffer_b = ByteBuffer.wrap(mazeInBytes, 20, 4);
       rows = buffer_a.getInt();
       columns = buffer_b.getInt();
       int index = 24;
       maze = new int[rows][columns];
       for (int i = 0; i <rows ; i++) {
           for (int j = 0; j < columns; j++) {
                maze[i][j] = mazeInBytes[index++];
            }

        }
    }

    /**
     * getter for number of rows in the maze
     */
    public int getRows() {
        return rows;
    }

    /**
     * getter for number of columns in the maze
     */
    public int getColumns() {
        return columns;
    }

    /**
     * getter for the maze matrix
     */
    public int[][] getMaze() {
        return maze;
    }
    /**
     * setter for the maze matrix
     */
    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    /**
     * getter for the start position in the maze
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * getter for the goal position in the maze
     */
    public Position getGoalPosition() {
        return goalPosition;
    }

    /**
     * method to print the maze
     */
    public void print(){
        final String START = " \u001B[32mS\u001B[0m "; // green S with 1 space after
        final String END = " \u001B[31mE\u001B[0m "; // red E with 1 space after
        for(int i=0 ; i<rows ; i++){
            for(int j=0 ; j<columns ; j++){
                if(i== startPosition.getRow_index() && j== startPosition.getColumn_index())
                    System.out.print(START);
                else if(i ==goalPosition.getRow_index() && j==goalPosition.getColumn_index())
                    System.out.print(END);
                else if (maze[i][j] == 1)
                    System.out.print(" ■ ");
                else{
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    public byte[] toByteArray() {
        int bufferSize = 4 + 4 + 4 + 4 + 4 + 4 + (rows * columns); // Size of int in bytes is 4
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
        byteBuffer.putInt(startPosition.getRow_index());
        byteBuffer.putInt(startPosition.getColumn_index());
        byteBuffer.putInt(goalPosition.getRow_index());
        byteBuffer.putInt(goalPosition.getColumn_index());
        byteBuffer.putInt(rows);
        byteBuffer.putInt(columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                byteBuffer.put((byte)maze[i][j]);
            }
        }
        return byteBuffer.array();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze1 = (Maze) o;
        return rows == maze1.rows && columns == maze1.columns && Arrays.equals(maze, maze1.maze) && Objects.equals(startPosition, maze1.startPosition) && Objects.equals(goalPosition, maze1.goalPosition);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(startPosition, goalPosition, rows, columns);
        result = 31 * result + Arrays.hashCode(maze);
        return result;
    }
}
