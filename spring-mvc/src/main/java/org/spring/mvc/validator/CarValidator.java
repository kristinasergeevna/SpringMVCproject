package org.spring.mvc.validator;

import org.spring.mvc.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by SDS on 14.04.2016.
 */
@Component
public class CarValidator implements Validator {

    public boolean supports(Class<?> aClass) {
        return Car.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Car car = (Car) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "field.required", "Required field");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required", "Required field");

        if (!errors.hasFieldErrors("price"))
            if (car.getPrice().intValue() == 0)
                errors.rejectValue("price", "not_zero", "Can't be free!");
            else if(car.getPrice().intValue() < 0)
                errors.rejectValue("price", "not_negative", "Can't be negative, because it's bad!");
    }
}
