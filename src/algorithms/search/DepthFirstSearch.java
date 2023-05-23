package algorithms.search;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{
    /**
     * method that uses dfs algorithm in order to solve the searchable received
     * @param searchable : searchable object to look inside for a path from start to goal
     * @return : solution that holds path to the searchable object
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if(searchable == null)
            return new Solution();
        // call dfs on the searchable starting from start position
        boolean worked = dfs(searchable.getStartState(), searchable.getGoalState(), searchable);
        if(!worked)
            return new Solution();
        return findSolution(searchable.getGoalState());
    }

    /**
     * helper function to implement depth first search on the given searchable
     * @param start : current state of the dfs
     * @param goal : goal state for the dfs
     * @param searchable : searchable object to search for goal inside
     */
    private boolean dfs(AState start, AState goal, ISearchable searchable) {
        Stack<AState> stack = new Stack<>(); // stack
        HashSet<AState> visited = new HashSet<>(); // visited list
        stack.push(searchable.getStartState());
        visited.add(start);
        if(start.equals(goal)) {
            nodesEvaluated++;
            return true;
        }
        while (!stack.empty()) {
            AState state = stack.pop();
            ArrayList<AState> ways = searchable.getAllPossibleStates(state);
            if(ways == null)
                return false;
            Collections.reverse(ways); // reverse the possible ways in order to pop the ways cells from the beginning
            for (AState cell : ways) {
                if (!visited.contains(cell)) { // iterate over all possible states can be reached from given state
                    parents.put(cell, state); // {son,parent}
                    this.nodesEvaluated++;
                    visited.add(cell);
                    stack.push(cell);
                }
                if(cell.equals(goal))
                    return true;
            }
        }
        return false;
    }

    /**
     * getter for the algorithm name
     */
    @Override
    public String getName() {
        return "Depth First Search";
    }
}
