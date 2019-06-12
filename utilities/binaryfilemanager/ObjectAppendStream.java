package utilities.binaryfilemanager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectAppendStream extends ObjectOutputStream {

    /* CONSTRUCTORS _________________________________________________________ */
    public ObjectAppendStream(OutputStream out) throws IOException {
        super(out);
    }

    public ObjectAppendStream() throws IOException, SecurityException {
    }

    /* METHODS ______________________________________________________________ */
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
