package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.User;

import java.util.List;

public class UserQueryCarsResponse extends Response {
    private List<Car> cars;

    public UserQueryCarsResponse() {

    }

    public UserQueryCarsResponse(int code, String msg, List<Car> cars) {
        super(code, msg);
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}