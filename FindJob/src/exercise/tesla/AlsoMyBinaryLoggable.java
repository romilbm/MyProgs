package exercise.tesla;

import java.io.IOException;

/**
 * Created by romil on 6/4/17.
 */
public class AlsoMyBinaryLoggable implements BinaryLoggable {
    String data = null;

    public AlsoMyBinaryLoggable(String data) {
        this.data = data;
    }

    public AlsoMyBinaryLoggable() {

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
