package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    Configurations configurations;

    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            configurations = Configurations.getInstance();

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            // Get the temporary directory path
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            // Read the maze from the client
            Maze mazeFromClient = (Maze) fromClient.readObject();

            // Create a unique file name for the maze
            String mazeFileName = "" + mazeFromClient.hashCode() + "";

            // Create a file to store the solution
            File file = new File(tempDirectoryPath, mazeFileName);
            Solution solution;

            if (file.exists()) {
                // If the solution file exists, read the solution from it
                FileInputStream in = new FileInputStream(file);
                ObjectInputStream solReader = new ObjectInputStream(in);
                solution = (Solution) solReader.readObject();
                solReader.close();
            } else {
                // If the solution file doesn't exist, solve the maze and store the solution
                file.createNewFile();
                SearchableMaze searchableMaze = new SearchableMaze(mazeFromClient);
                solution = configurations.getMazeSearchingAlgorithm().solve(searchableMaze);
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                     ObjectOutputStream solWriter = new ObjectOutputStream(fileOutputStream)) {
                    // Write the solution to the file
                    solWriter.writeObject(solution);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Send the solution to the client
            toClient.writeObject(solution);
            toClient.flush();

            // Close streams
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
