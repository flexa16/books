package by.overone.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/admin")
public class AdminPageController {

    @RequestMapping(method = RequestMethod.POST)
    public String adminPost() {
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String adminGet() {
        return "admin";
    }

}
