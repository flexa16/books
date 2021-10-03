package by.overone.books.controller;

import by.overone.books.model.entitity.AppUser;
import by.overone.books.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService service;

    @RequestMapping(path ="/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        AppUser user = new AppUser();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid AppUser user) {
        ModelAndView modelAndView =new ModelAndView();
        AppUser appUser = service.findUserByEmail(user.getEmail());
        if (appUser != null) {
            modelAndView.addObject("message", "User email is already exist");
        } else if("".equals(user.getPassword())){
            modelAndView.addObject("message", "Incorrect password");
        } else {
            service.registerNewUser(user);
            modelAndView.addObject("message", "User has been registered successfully");
        }
        modelAndView.addObject("user", new AppUser());
        modelAndView.setViewName("register");
       return modelAndView;
    }

}
