package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private Configurations configurations;
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService executorService; // Thread pool

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        try {
            this.configurations = Configurations.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // initialize a new fixed thread pool with a specified number of threads:
        this.executorService = Executors.newFixedThreadPool(configurations.getThreadPoolSize());
    }

    public void start() {
        // Start the server in a new thread
        new Thread(this::runStart).start();
    }

    private void runStart() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Server started at port: " + port);

            while (!stop) {
                try {
                    // Accept client connection
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket);

                    // Submit the client handling task to the thread pool
                    executorService.execute(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                }
            }

            // Shutdown the thread pool when stopping the server
            executorService.shutdown();

            // Wait for all tasks to complete before stopping the server
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            // Close the server socket
            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            // Process the question and get the answer using the server strategy
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
            System.out.println("Done handling client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        // Set the stop flag to true to stop the server loop
        stop = true;
    }
}
