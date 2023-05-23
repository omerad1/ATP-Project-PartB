package algorithms.search;

import java.util.HashMap;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int nodesEvaluated; // int represent number of nodes evaluated by the searching algorithm during search
    private Solution solution; // solution to the problem received
    protected HashMap<AState, AState> parents; // a map of a "son" and "father" states

    /**
     * constructor
     */
    public ASearchingAlgorithm() {
        this.nodesEvaluated = 0;
        solution = new Solution();
        parents = new HashMap<>();
    }

    /**
     * getter for the number of the nodes that has been evaluated
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return nodesEvaluated;
    }

    protected Solution findSolution(AState goalState)
    {
        AState currState = goalState;
        while (currState != null) {
            solution.addState(currState);
            currState = parents.get(currState);
        }
        solution.reversePath();
        return solution;
    }

}
