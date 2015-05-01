package qr.model;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.qrcode.QRCodeReader;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class MyQRCodeReader {

    public String readQRCoed(String base64String) {
        try {

            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(ImageIO.read((new ByteArrayInputStream(Base64.decode(base64String)))));
            //RGBLuminanceSource source = new RGBLuminanceSource(width, height, (int[])Base64.decode(base64String));
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            QRCodeReader reader = new QRCodeReader();
            Result result = reader.decode(bitmap);
            String message = result.getText();
            System.out.println(message);
            return message;
        } catch (NotFoundException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ChecksumException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FormatException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
//			e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
//			e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
