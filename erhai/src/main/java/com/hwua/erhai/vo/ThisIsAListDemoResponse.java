package com.hwua.erhai.vo;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ThisIsAListDemoResponse extends Response {
    // 不要使用final字段，并且必须给每个字段生成getter和setter函数
    private List<Car> cars;

    public ThisIsAListDemoResponse(int code, String msg, List<Car> cars) {
        super(code, msg);
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public static void main(String[] args) {
        int code = 200;
        String msg = "成功";
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long id = i;
            int brandId = i;
            String brandName = "brandName_" + i;
            String model = "model_" + i;
            int categoryId = i;
            String categoryName = "categoryName_" + i;
            String comments = "comments_" + i;
            double rent = i;
            int status = i % 2;
            Car car = new Car(id, brandId, brandName, model, categoryId,
                    categoryName, comments, rent, status);
            cars.add(car);
        }
        ThisIsAListDemoResponse thisIsAListDemoResponse = new ThisIsAListDemoResponse(code, msg, cars);
        String jsonString = JsonUtil.toJson(thisIsAListDemoResponse);
        System.out.println("jsonString = " + jsonString);

        ThisIsAListDemoResponse response = JsonUtil.fromJson(jsonString, ThisIsAListDemoResponse.class);
        System.out.println("response = " + JsonUtil.toJson(response));

        Response demoResponse = JsonUtil.fromJson(jsonString, Response.class);
        System.out.println("demoResponse = " + JsonUtil.toJson(demoResponse));
    }
}
