package qr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        return "index";
    }

    @RequestMapping(value = "/webcamera", method = RequestMethod.GET)
    String webcamera() {
        return "webcamera";
    }

    @RequestMapping(value = "/file")
    String fileApi() {
        return "fileapi";
    }
}
