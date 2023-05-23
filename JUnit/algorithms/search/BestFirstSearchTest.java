package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    IMazeGenerator mg = new MyMazeGenerator();
    Maze maze = mg.generate(30, 30);
    IMazeGenerator smg = new MyMazeGenerator();
    Maze smaze = smg.generate(100, 100);

    SearchableMaze searchableMaze = new SearchableMaze(maze);
    SearchableMaze searchableSMaze = new SearchableMaze(smaze);
    BestFirstSearch bestFirstSearch = new BestFirstSearch();

    @Test
    void testWrongParameters(){
        int[][] blockedMaze = new int[smaze.getRows()][smaze.getColumns()];
        for (int[] ints : blockedMaze) Arrays.fill(ints, 1); // initialize maze full of walls
        smaze.setMaze(blockedMaze);
        SearchableMaze blocked = new SearchableMaze(smaze);
        assertTrue(bestFirstSearch.solve(blocked).isEmpty());
    }
    @Test
    void testNodeEvaluatedWhenSupersizeMaze(){
        bestFirstSearch.solve(searchableMaze);
        int a = bestFirstSearch.nodesEvaluated;
        bestFirstSearch.solve(searchableSMaze);
        int b = bestFirstSearch.nodesEvaluated;
        assertTrue(a<b);
    }
    @Test
    void testNullMazeEmptySolution(){
        assertTrue(bestFirstSearch.solve(null).isEmpty());
        assertEquals(bestFirstSearch.solve(null).getSolutionPath().size(),0);
    }
    @Test
    void name() {
        assertEquals(bestFirstSearch.getName(),"Best First Search");
    }

    @RepeatedTest(100)
    void testAlwaysWorking(){
        Maze temp = mg.generate(40,40);
        SearchableMaze smz = new SearchableMaze(temp);
        assertFalse(bestFirstSearch.solve(smz).isEmpty());
        assertTrue(bestFirstSearch.nodesEvaluated > 40);
        assertTrue(bestFirstSearch.nodesEvaluated< 1600);
    }
    @RepeatedTest(10)
    void testTimes(){
        Maze temp = mg.generate(1000,1000);
        SearchableMaze smz = new SearchableMaze(temp);
        long start = System.currentTimeMillis();
        bestFirstSearch.solve(smz).isEmpty();
        long finish = System.currentTimeMillis();
        finish = finish - start;
        assertTrue(finish<60000);
    }
    Maze temp = mg.generate(4,4);
    int[][] arr =   {
            {0,0,0,0},
            {1,1,1,0},
            {1,1,0,0},
            {0,0,0,0}
    };

    @Test
    void evaluateTest(){
        temp.setMaze(arr);
        SearchableMaze smz = new SearchableMaze(temp);
        bestFirstSearch.solve(smz);
        assertEquals(bestFirstSearch.nodesEvaluated,7);
    }
    @Test
    void pathTest(){
        {
            temp.setMaze(arr);
            SearchableMaze smz = new SearchableMaze(temp);
            Solution solution = bestFirstSearch.solve(smz);
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            assertEquals(solutionPath.get(0).toString(),"{0,0}");
            assertEquals(solutionPath.get(1).toString(),"{0,1}");
            assertEquals(solutionPath.get(2).toString(),"{0,2}");
            assertEquals(solutionPath.get(3).toString(),"{1,3}");
            assertEquals(solutionPath.get(4).toString(),"{2,3}");
            assertEquals(solutionPath.get(5).toString(),"{3,3}");
        }
    }
}