package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;

public class UpdateCarResponse extends Response{
    private Car car;

    public UpdateCarResponse(int code, String msg, Car car) {
        super(code, msg);
        this.car = car;
    }
    public UpdateCarResponse(){

    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
