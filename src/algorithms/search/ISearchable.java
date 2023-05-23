package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    AState getStartState(); //start position
    AState getGoalState(); //goal position
    ArrayList<AState> getAllPossibleStates(AState state); // all the possible moves from given state
}

