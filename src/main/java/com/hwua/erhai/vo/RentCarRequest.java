package com.hwua.erhai.vo;

public class RentCarRequest extends Request {
    private int userId;
    private int carId;

    public RentCarRequest(String requestType, int userId, int carId) {
        super(requestType);
        this.userId = userId;
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
