package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream outputStream) {
        out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        // Write a single byte to the underlying output stream
        out.write(b);
    }

    public void write(byte[] bytes) throws IOException {
        // Check if the input bytes are valid and have a length of at least 25
        if (bytes == null || bytes.length < 25)
            throw new IOException();

        int i = 0;
        for (; i < 24; i++) {
            // Write the first 24 bytes as-is to the output stream
            write(bytes[i]);
        }

        int counter = 0;
        int curr = 0;
        for (; i < bytes.length; i++) {
            if (bytes[i] == curr) {
                // If the current byte is equal to the previous byte, increment the counter
                if (counter == 256) {
                    // If the counter reaches the maximum value (256), write a marker value of 255
                    // followed by a counter value of 0 to the output stream
                    write(255);
                    write(0);
                    counter = 0;
                }
                counter++;
            } else {
                // If the current byte is different from the previous byte, write the counter value
                // followed by the current byte to the output stream
                write(counter);
                curr = 1 - curr; // Toggle the value of curr (either 0 or 1)
                counter = 1;
            }
        }
    }
}
