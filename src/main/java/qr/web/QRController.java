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
        System.out.println("***** hoge is called..." );
        //初年度登録年月/有効期限の満了する月/形式/前前重量/前後重量/後前重量/後後重量/
        //2/-117510101/141115/0308/LA
        try {
            String base64String = param.split(",")[1];
            System.out.println(base64String);
            base64String = base64String.replaceAll(" ", "+");//FIXME TODO jQuery.ajaxのdataは+がdataの区切りでspaceになるので暫定対応処理
            System.out.println(base64String);
//        qrReader.decodeFile(base64String);
            decoder.decodeFile(base64String);
            String result = qrReader.readQRCoed(base64String);
            System.out.println("**** " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "2/-117510101/141115/0308/LA";
    }
}
