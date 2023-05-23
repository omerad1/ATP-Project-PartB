package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Maze3D {
    private int[][][] map;
    private final Position3D StartPosition;
    private final Position3D GoalPosition;
    private final int rows;
    private final int columns;
    private final int depth;

    /**
     * constructor - creates the 3d maze
     */
    public Maze3D(int depth, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.depth = depth;
        StartPosition = new Position3D(0, 0, 0);// default value of start position
        GoalPosition = new Position3D(0, rows - 1, columns - 1); // default value of goal position
        map = new int[depth][rows][columns];
    }

    public int[][][] getMap() {
        return map;
    }

    public Position3D getStartPosition() {
        return StartPosition;
    }

    public Position3D getGoalPosition() {
        return GoalPosition;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getDepth() {
        return depth;
    }

    public void setMap(int[][][] map) {
        this.map = map;
    }

    /**
     * prints the maze layer by layer
     */
    public void print() {
        for (int[][] floor : map) {
            System.out.print("+");
            for (int i = 0; i < floor.length; i++) {
                System.out.print("----");
            }
            System.out.println("+");
            for (int[] row : floor) {
                System.out.print("|"); // add a vertical border at the beginning of each row
                for (int cell : row) {
                    String cellValue = String.valueOf(cell);
                    System.out.print(" " + cellValue + " "); // print each cell with spaces for padding
                }
                System.out.println("|"); // add a vertical border at the end of each row
            }
            System.out.print("+");
            for (int i = 0; i < floor.length; i++) {
                System.out.print("----");
            }
            System.out.println("+");
        }
    }

}
