package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private final int column_index;
    private final int row_index;

    /**
     * constructor
     * @param row_index : the row of the position
     * @param column_index : the column of the position
     */
    public Position(int row_index, int column_index) {
        this.column_index = column_index;
        this.row_index = row_index;
    }

    /**
     * getter for the column index of the position
     */
    public int getColumn_index() {
        return column_index;
    }

    /**
     * getter for the row index of the position
     */
    public int getRow_index() {
        return row_index;
    }


    /**
     * @return the Position in string as such "{rowIndex,colIndex}"
     */
    public String toString(){
        String r = Integer.toString(row_index);
        String c = Integer.toString(column_index);
        return "{"+r+","+c+"}";
    }

    /**
     * getter for the position in int[] representation
     */
    public int[] getPosition(){
        return new int[] {row_index,column_index};
    }

}
