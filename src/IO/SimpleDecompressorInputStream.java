package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    /**
     * Constructs a SimpleDecompressorInputStream with the specified input stream.
     * @param input the input stream to read compressed data from.
     */
    public SimpleDecompressorInputStream(InputStream input) {
        this.in = input;
    }

    /**
     * Reads the next byte of compressed data from the input stream.
     * This method is overridden from the InputStream class.
     * @return the next byte of compressed data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * Reads compressed data from the input stream and fills the specified byte array
     * with the decompressed data.
     * @param bytes the byte array to store the decompressed data.
     * @return the number of bytes read and decompressed, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    public int read(byte[] bytes) throws IOException {
        if (bytes.length == 0)
            return -1;

        // Read the first 24 bytes from the input stream
        for (int i = 0; i < 24; i++) {
            int input = read();
            bytes[i] = (byte) input;
        }
        // Decompress the remaining bytes using the flag value
        int flag = 0;
        int input = read();
        int index = 24;
        while(input != -1 && index<bytes.length){
            for(int i =0; i<input;i++){
                bytes[index] = (byte)flag;
                index++;
            }
            flag = 1-flag;
            input = read();
        }

        return 1;
    }

    /**
     * Closes the input stream.
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws IOException {
        in.close();
    }
}
