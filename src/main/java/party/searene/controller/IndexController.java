package party.searene.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import party.searene.client.XRClient;

/**
 * Created by searene on 11/27/16.
 */
@Controller
public class IndexController {

    private String title = "Downloader";

    @Autowired
    private XRClient xrClient;

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }
}