package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> queue;
    HashSet<AState> visited;

    /**
     * constructor
     */
    public BreadthFirstSearch() {
        this.queue = new ArrayDeque<>();
        visited = new HashSet<>();
    }

    /**
     * runs a bfs algorithm on a searchable until it reaches the endGoal, then return the solution to the searchable
     * @param searchable a searchable to run the bfs on and find a solution
     * @return a solution to the searchable
     */
    @Override
    public Solution solve(ISearchable searchable) {
        if(searchable == null)
            return new Solution();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();
        if(startState.equals(goalState)) {
            nodesEvaluated++;
            return findSolution(startState);
        }
        AState currState;
        visited.add(startState);
        queue.add(startState);
        while (!queue.isEmpty()) {
            currState = queue.poll();
            ArrayList<AState> neighbors = searchable.getAllPossibleStates(currState);
            if(neighbors == null)
                return new Solution();
            for (AState neighbor : neighbors) { // add neighbors to the queue
                if(!visited.contains(neighbor)) {
                    parents.put(neighbor, currState); // {son,parent}
                    visited.add(neighbor);
                    nodesEvaluated++;
                    queue.add(neighbor);
                }
                if(neighbor.equals(goalState)) {
                    return findSolution(goalState);
                }
            }
        }
        return new Solution();
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
