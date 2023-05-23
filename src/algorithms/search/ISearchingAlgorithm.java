package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable searchable); // using searching algorithm to solve the given searchable
    String getName(); // returns algorithm name
    int getNumberOfNodesEvaluated(); // returns the number of nodes that has been evaluated during the search

}
