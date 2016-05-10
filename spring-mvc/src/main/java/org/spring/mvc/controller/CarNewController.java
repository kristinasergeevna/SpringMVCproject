package org.spring.mvc.controller;

import org.spring.mvc.model.Car;
import org.spring.mvc.service.BrandManager;
import org.spring.mvc.service.CarManager;
import org.spring.mvc.validator.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SDS on 08.04.2016.
 */
@Controller
@RequestMapping("/new_car")
public class CarNewController {

    @Autowired
    CarManager carManager;

    @Autowired
    BrandManager brandManager;

    @Autowired
    CarValidator carValidator;

    @RequestMapping(value = "/addCar", method = RequestMethod.GET)
    public String getForm(ModelMap model) throws ServletException {
        Map map = referenceData();
        model.addAttribute("brandList", map.get("brandList"));
        model.addAttribute("car", new Car());
        return "carNew";
    }

    @RequestMapping(value = "/carsList", method = RequestMethod.GET)
    public String getList(ModelMap model) throws ServletException {
        Map map = referenceData();
        model.addAttribute("carList", map.get("carList"));
        return "carList";
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("car") Car car,
                           BindingResult result,
                           ModelMap model) throws ServletException {
        car.setBrand(brandManager.getBrandById(car.getBrand().getId()));
        carValidator.validate(car, result);
        if (result.hasErrors()) {
            Map map = referenceData();
            model.addAttribute("brandList", map.get("brandList"));
            return "carNew";
        }
        carManager.createCar(car);
        return "redirect:carsList";

    }

    protected Map referenceData() {

        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        dataMap.put("brandList", brandManager.getBrandList());
        dataMap.put("carList", carManager.getCarList());
        return dataMap;

    }

}
