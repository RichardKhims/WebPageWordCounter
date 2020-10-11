package khims.richard.wpwc.writer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileWriter implements DataWriter {
    private String filename;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(InputStream input) throws Exception {
        OutputStream output = new FileOutputStream(filename);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        output.flush();
        output.close();
    }
}
