package org.spring.mvc.controller;

import org.spring.mvc.service.CarManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SDS on 08.04.2016.
 */

public class CarListController implements Controller {

        public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

             CarManager carManager = new CarManager();

           ModelAndView modelAndView = new ModelAndView("carList");

             modelAndView.addObject("carList", carManager.getCarList());

            return modelAndView;

        }
}
