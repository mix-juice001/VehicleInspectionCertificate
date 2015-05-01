package qr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qr.model.FileDecoder;
import qr.model.MyQRCodeReader;

@RestController
public class QRController {

    @Autowired
    private MyQRCodeReader qrReader;

    @Autowired
    private FileDecoder decoder;

    @RequestMapping(value = "/qr/read", method = RequestMethod.POST)
    String hoge(@RequestParam("q") String param) {
        String base64String = param.split(",")[1];
        System.out.println(base64String);
        base64String = base64String.replaceAll(" ", "+");//FIXME TODO jQuery.ajaxÇÃdataÇÕ+Ç™dataÇÃãÊêÿÇËÇ≈spaceÇ…Ç»ÇÈÇÃÇ≈ébíË
        System.out.println(base64String);
//        qrReader.decodeFile(base64String);
        decoder.decodeFile(base64String);
        String result = qrReader.readQRCoed(base64String);
        System.out.println("**** " + result);
        return result;
    }
}
