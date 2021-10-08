package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;

import java.util.List;

public class AdminQueryCarsResponse extends Response {
    private List<Car> cars;

    public AdminQueryCarsResponse() {

    }

    public AdminQueryCarsResponse(int code, String msg, List<Car> cars) {
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
