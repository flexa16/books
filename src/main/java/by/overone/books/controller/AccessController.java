package by.overone.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AccessController {

    @RequestMapping(name = "/access", method = RequestMethod.GET)
    public String access(Model model) {
        model.addAttribute("message", "Access denied");
        return "access";
    }
}
