package qr.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileDecoder {
    public void decodeFile(String base64String) {
        try {
            BufferedOutputStream fo = new BufferedOutputStream(new FileOutputStream(new File("/Users/takumasetoguchi/hogehoge.png")));
            ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(base64String));

            int ctx;
            byte[] b = new byte[1024];
            while ((ctx = bi.read()) != -1) {
                fo.write(ctx);
            }
            bi.close();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
