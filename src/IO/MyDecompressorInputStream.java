package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyDecompressorInputStream extends InputStream {
    InputStream input;

    public MyDecompressorInputStream(InputStream fileInputStream) {
        input = fileInputStream;
    }

    @Override
    public int read() throws IOException {
        // Read a single byte from the underlying input stream
        return input.read();
    }

    /**
     * Helper method to read a sequence of bytes until a specific condition is met.
     *
     * @return a list of bytes read from the input stream
     */
    public List<Byte> read_helper() {
        int i = 0;
        int b;
        List<Byte> bytes = new ArrayList<>();
        try {
            int found = 0;
            while (i < 255) {
                b = read();
                if (b == -1) {
                    break;
                }
                if (b == 255) {
                    if (found == 0) {
                        found = 1;
                        continue;
                    } else {
                        break;
                    }
                }
                bytes.add((byte) b);
                i++;
            }
        } catch (IOException e) {
            return null;
        }
        return bytes;
    }

    /**
     * Decompresses a list of bytes using a specific algorithm.
     *
     * @param bytes the list of bytes to decompress
     * @return the decompressed byte array
     */
    public byte[] deCompressor(List<Byte> bytes) {
        List<Character> decompressed = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        int counter = 1;
        for (byte b : bytes) {
            String curr = Integer.toBinaryString(b & 0xFF);
            char lsb = curr.charAt(curr.length() - 1);
            if (b != 0 && b != 1)
                curr = curr.substring(0, curr.length() - 1);
            int intValue = Integer.parseInt(curr, 2);
            if (map.containsKey(intValue) && b != 0 && b != 1) {
                String prev = map.get(intValue);
                curr = prev + lsb;
            }
            map.put(counter++, curr);
            for (char c : curr.toCharArray()) {
                decompressed.add(c);
            }
        }
        byte[] res = new byte[decompressed.size()];
        for (int i = 0; i < decompressed.size(); i++)
            res[i] = (byte) (decompressed.get(i) - '0');
        return res;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        if (bytes.length == 0)
            return -1;
        // Read the first 24 bytes from the input stream
        for (int i = 0; i < 24; i++) {
            int input = read();
            bytes[i] = (byte) input;
        }
        int current_index = 24;
        List<Byte> byteList = read_helper();
        while (!byteList.isEmpty()) {
            byte[] curr_list = deCompressor(byteList);
            for (int i = current_index; i < current_index + curr_list.length; i++) {
                bytes[i] = curr_list[i - current_index];
            }
            byteList = read_helper();
            current_index += curr_list.length;
        }
        return 1;
    }
}
