package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

public class RunSearchOnMaze3D {
    public static void main(String[] args) {
        IMazeGenerator3D mg = new MyMaze3DGenerator();
        boolean runTest;
        for (int i = 0; i < 15; i++) {
            Maze3D maze = mg.generate(1,1, 1);
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            long start = System.currentTimeMillis();
            runTest = solveProblem(searchableMaze, new DepthFirstSearch());
            long finish = System.currentTimeMillis();
            System.out.println(finish-start);
            runTest = runTest && solveProblem(searchableMaze, new BreadthFirstSearch());
            long finish2 = System.currentTimeMillis();
            System.out.println(finish2-finish);
            runTest = runTest && solveProblem(searchableMaze, new BestFirstSearch());
            long finish3 = System.currentTimeMillis();
            System.out.println(finish3-finish2);
            if(!runTest)
                break;
        }
    }


    private static boolean solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        Solution sol = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        return checkEmpty(sol, searcher);

    }
    private static boolean checkEmpty(Solution solToCheck, ISearchingAlgorithm searcher) {
        if(solToCheck.isEmpty() || solToCheck.getSize() == 0) {
            System.out.println(searcher.getName() + " failed!");
            return false;
        }
        return true;
    }
}