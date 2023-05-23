package algorithms.search;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch{
    public class BestFirstSearchComparator implements Comparator<AState> {

        @Override
        public int compare(AState s1, AState s2) {
            double eval1 = s1.weight; // assuming that AState has a method to get the evaluation function value
            double eval2 = s2.weight;
            return Double.compare(eval1, eval2);
        }
    }
    public BestFirstSearch() {
        this.queue = new PriorityQueue<>(new BestFirstSearchComparator());
        visited = new HashSet<>();
    }
    @Override
    public String getName() {
        return "Best First Search";
    }
}
