package algorithms.maze3D;

public class Position3D {
    private final int column_index;
    private final int row_index;
    private final int depth_index;

    /**
     * constructor
     * @param depth_index : the depth of the position
     * @param row_index : the row of the position
     * @param column_index : the column of the position
     */
    public Position3D(int depth_index,int row_index, int column_index) {
        this.column_index = column_index;
        this.row_index = row_index;
        this.depth_index = depth_index;
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
     * getter for the depth index of the position
     */
    public int getDepth_index() {
        return depth_index;
    }

    /**
     * @return the Position in string as such "{rowIndex,colIndex}"
     */
    public String toString(){
        String d = Integer.toString(depth_index);
        String r = Integer.toString(row_index);
        String c = Integer.toString(column_index);
        return "{"+d+","+r+","+c+"}";
    }

    /**
     * getter for the position in int[] representation
     */
    public int[] getPosition(){
        return new int[] {depth_index,row_index,column_index};
    }

    public  boolean equals(Position3D other){
        return other.getColumn_index() == column_index && other.getRow_index() == row_index && other.getDepth_index() == depth_index;
    }
}
