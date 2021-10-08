package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;

public class UserQueryCarsResponse extends Response{
    private Car car;

    public UserQueryCarsResponse() {

    }

    public UserQueryCarsResponse(int code, String msg, Car car) {
        super(code, msg);
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
