package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        // Write a single byte to the underlying output stream
        out.write(b);
    }

    /**
     * Compresses the given byte array using the LZ compression algorithm.
     *
     * @param bytes the byte array to compress
     * @return a list of compressed bytes
     */
    public static List<Byte> compressLZ(byte[] bytes) {
        List<Byte> compressed = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int counter = 1;
        String current = "";
        for (byte b : bytes) {
            current += Byte.toString(b);
            if (!map.containsKey(current)) {
                map.put(current, counter++);
                if (current.length() == 1)
                    compressed.add(b);
                else {
                    String prefix = current.substring(0, current.length() - 1);
                    int add = current.charAt(current.length() - 1) - '0';
                    compressed.add((byte) (map.get(prefix) * 2 + add));
                }
                current = "";
            }
        }

        if (map.containsKey(current)) {
            if (current.length() == 1)
                compressed.add(Byte.valueOf(current));
            else {
                String prefix = current.substring(0, current.length() - 1);
                int add = current.charAt(current.length() - 1) - '0';
                compressed.add((byte) (map.get(prefix) * 2 + add));
            }
        } else if (!current.equals("")) {
            compressed.add(map.get(current).byteValue());
        }
        return compressed;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length < 25)
            throw new IOException();
        int i = 0;
        for (; i < 24; i++)
            out.write(bytes[i]);

        int arrayLength = bytes.length - 24;
        int numChunks = (int) Math.ceil((double) arrayLength / 255);
        byte[][] smallerArrays = new byte[numChunks][];

        for (int k = 0; k < numChunks; k++) {
            int start = (k * 255) + 24;
            int end = start + Math.min(255, arrayLength - start + 24);
            smallerArrays[k] = Arrays.copyOfRange(bytes, start, end);
        }
        for (byte[] subBytes : smallerArrays) {
            List<Byte> compressed = compressLZ(subBytes);
            out.write(255); // Write marker value 255 to indicate the start of compressed data
            for (byte gamad : compressed) {
                out.write(gamad); // Write each compressed byte to the output stream
            }
            out.write(255); // Write marker value 255 to indicate the end of compressed data
        }
    }
}
