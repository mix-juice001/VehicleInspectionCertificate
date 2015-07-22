package qr.model;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.detector.Detector;
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
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

            BitMatrix bm = binaryBitmap.getBlackMatrix();
            Detector detector = new Detector(bm);
            DetectorResult detectorResult = detector.detect();

            String position = "Found at points ";
            for (ResultPoint point : detectorResult.getPoints()) {
                position += point.toString() + ", ";
            }
            System.out.println(position);

            BitMatrix bitMatrix = detectorResult.getBits();
            Decoder decoder = new Decoder();
            DecoderResult decoderResult = decoder.decode(bitMatrix);
            System.out.println(decoderResult.getText());

            return decoderResult.getText();

//            QRCodeReader reader = new QRCodeReader();
//            Result result = reader.decode(bitmap);
//            String message = result.getText();
//            System.out.println(message);
//            return message;
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (ChecksumException e) {
            throw new RuntimeException(e);
        } catch (FormatException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
