package exercise.tesla;

import java.io.IOException;

public class MyBinaryLoggable implements BinaryLoggable {

    String data = null;

    public MyBinaryLoggable(String data) {
        this.data = data;
    }

    @Override
    public byte[] toBytes() throws IOException {
        return data.getBytes();
    }

    @Override
    public void fromBytes(byte[] rawBytes) throws IOException {
        data = new String(rawBytes);
    }

    public String toString() {
        return data;
    }
}
