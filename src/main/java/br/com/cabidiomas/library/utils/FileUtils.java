package br.com.cabidiomas.library.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static byte[] partToBytes(Part file) throws IOException {
        InputStream is = file.getInputStream();
        byte[] bytes = new byte[Math.toIntExact(file.getSize())];
        IOUtils.readFully(is, bytes);
        return bytes;
    }
}
