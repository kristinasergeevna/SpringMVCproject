package org.spring.mvc.model;

import java.math.BigDecimal;

/**
 * Created by SDS on 08.04.2016.
 */
public class Car {
    private Long id;
    private Brand brand;
    private String model;
    private BigDecimal price;
    //private Long brandId;
   /* public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand=" + brand +
                ", model='" + model + '\'' +
                ", price=" + price +
               // ", brandId=" + brandId +
                '}';
    }
}
