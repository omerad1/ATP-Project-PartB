package algorithms.search;
import java.io.Serializable;
import java.util.*;

public class Solution implements Serializable {
    private ArrayList<AState> path;

    /**
     * constructor
     */
    public Solution() {
        path = new ArrayList<>();
    }

    /**
     * getter
     */
    public ArrayList<AState> getSolutionPath() {
        return this.path;
    }

    /**
     * adds new state to the building path
     * @param state : state represents location in the current path
     */
    public void addState(AState state){
        this.path.add(state);
    }

    /**
     * helper function to reverse path if needed
     */
    public void reversePath() {
        Collections.reverse(path);
    }

    public boolean isEmpty(){
        return path.size() == 0;
    }

    /**
     * returns the size of the solution
     */
    public int getSize(){
        return path.size();
    }

}
