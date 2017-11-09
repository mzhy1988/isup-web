package com.hns.iusp.ws.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



//public common class
/**
 * FileUtil , a set of functions of file operation.
 *
 */
public class FileUtil {
    public static final int READ_SIZE = 8192 * 4;

    public static byte[] readBytes(InputStream is) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[]		      buffer = null;

        if (is == null) {
            return null;
        }
        byte[] bufferTmp = new byte[1024];  
        int len;  
        while ((len = is.read(bufferTmp)) > -1 ) {  
        	os.write(bufferTmp, 0, len);  
        }  
        os.close();
        buffer = os.toByteArray();
        return buffer;
    }
}
