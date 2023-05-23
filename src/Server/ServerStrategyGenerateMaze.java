package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    Configurations configurations;

    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            configurations = Configurations.getInstance();
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            // Read the maze size from the client
            int[] mazeSize = (int[]) fromClient.readObject();

            // Get the maze generator algorithm from configurations
            IMazeGenerator mazeGenerator = configurations.getMazeGeneratingAlgorithm();

            // Generate the maze
            Maze maze = mazeGenerator.generate(mazeSize[0], mazeSize[1]);

            // Compress the maze using MyCompressorOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(byteArrayOutputStream);

                compressorOutputStream.write(maze.toByteArray());
                compressorOutputStream.flush();
                compressorOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Get the compressed maze as a byte array
            byte[] compressed = byteArrayOutputStream.toByteArray();

            // Send the compressed maze to the client
            toClient.writeObject(compressed);
            toClient.flush();

            // Close streams
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
