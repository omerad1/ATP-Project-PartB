package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Comparator;

public abstract class AState {
    protected int weight;
    public abstract String toString();
    public abstract boolean equals(Object obj);
    /**
     * helper class to compare maze states
     */
}
