package org.spring.mvc.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SDS on 06.04.2016.
 */
public class HelloWorldController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request,
                                     HttpServletResponse response) throws ServletException, IOException {
        String aMessage = "Hello Wonderfull World !";
         ModelAndView modelAndView = new ModelAndView("angelina_world");
       modelAndView.addObject("message", aMessage);
       return modelAndView;
    }
}
