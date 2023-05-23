package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Configurations {
    private final int threadPoolSize;
    private final ASearchingAlgorithm mazeSearchingAlgorithm;
    private final AMazeGenerator mazeGeneratingAlgorithm;
    private static Configurations instance = null;

    private Configurations() throws IOException {
        // Load properties from the configuration file
        Properties prop = new Properties();
        InputStream ip = getClass().getResourceAsStream("/config.properties");
        prop.load(ip);

        // Retrieve values from the properties
        threadPoolSize = Integer.parseInt(prop.getProperty("threadPoolSize"));
        String searchingAlg = prop.getProperty("mazeSearchingAlgorithm");
        String generAlg = prop.getProperty("mazeGeneratingAlgorithm");

        // Create the maze generating algorithm based on the configuration
        switch (generAlg) {
            case "MyMazeGenerator" -> mazeGeneratingAlgorithm = new MyMazeGenerator();
            case "EmptyMazeGenerator" -> mazeGeneratingAlgorithm = new EmptyMazeGenerator();
            case "SimpleMazeGenerator" -> mazeGeneratingAlgorithm = new SimpleMazeGenerator();
            default -> throw new InvalidPropertiesFormatException("Invalid maze generating algorithm specified in config.properties");
        }

        // Create the maze searching algorithm based on the configuration
        switch (searchingAlg) {
            case "BestFirstSearch" -> mazeSearchingAlgorithm = new BestFirstSearch();
            case "DepthFirstSearch" -> mazeSearchingAlgorithm = new DepthFirstSearch();
            case "BreadthFirstSearch" -> mazeSearchingAlgorithm = new BreadthFirstSearch();
            default -> throw new InvalidPropertiesFormatException("Invalid maze searching algorithm specified in config.properties");
        }
    }

    public static Configurations getInstance() throws IOException {
        if (instance == null)
            instance = new Configurations();
        return instance;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public ASearchingAlgorithm getMazeSearchingAlgorithm() {
        return mazeSearchingAlgorithm;
    }

    public AMazeGenerator getMazeGeneratingAlgorithm() {
        return mazeGeneratingAlgorithm;
    }
}
