package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {

    // Interface method to define the strategy for handling client requests
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);
}
