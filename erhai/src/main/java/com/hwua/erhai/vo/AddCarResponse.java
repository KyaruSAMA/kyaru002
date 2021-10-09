package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;

public class AddCarResponse extends Response{
    private Car car;

    public AddCarResponse(int code, String msg, Car car) {
        super(code, msg);
        this.car = car;
    }
    public AddCarResponse(){

    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
