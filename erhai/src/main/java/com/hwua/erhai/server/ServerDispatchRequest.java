package com.hwua.erhai.server;

import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.service.ICarService;
import com.hwua.erhai.service.IUserService;
import com.hwua.erhai.service.impl.CarServiceImpl;
import com.hwua.erhai.service.impl.UserServiceImpl;
import com.hwua.erhai.util.Constant;
import com.hwua.erhai.util.JsonUtil;
import com.hwua.erhai.vo.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.net.Socket;
import java.util.List;

public class ServerDispatchRequest extends DispatchRequestRunnable {
    private final IUserService userService = new UserServiceImpl();
    private final ICarService carService = new CarServiceImpl();

    public ServerDispatchRequest(Socket socket) {
        super(socket);
    }

    @Override
    public String dispatch(String request) {
        //获取请求头
        Response response = null;
        Request requestObject = JsonUtil.fromJson(request, Request.class);
        String header = requestObject.getRequestType();
        switch (header) {
            case Constant.LOGIN: //登录验证
                response = dispatchLogin(request);
                break;
            case Constant.REGISTER: //注册验证
                response = dispatchRegister(request);
                break;
            case Constant.USER_QUERY_CARS: //查询汽车
                response = dispatchUserQueryCars(request);
                break;
            case Constant.ADMIN_QUERY_CARS: //查询汽车
                response = dispatchAdminQueryCars(request);
                break;
            case Constant.RENT_CAR: //租赁汽车
                response = dispatchRentCar(request);
                break;
            case Constant.RETURN_CAR: //还车
                response = dispatchReturnCar(request);
                break;
            case Constant.QUERY_RECORDS: //租赁记录
                response = dispatchQueryRecords(request);
                break;
            case Constant.QUERY_CATEGORYS: //查询类别和品牌
                response = dispatchQueryCategories(request);
                break;
            case Constant.QUERY_BRANDS: //查询类别和品牌
                response = dispatchQueryBrands(request);
                break;
            case Constant.ADD_CAR: //添加汽车
                response = dispatchAddCar(request);
                break;
            case Constant.UPDATE_CAR: //修改汽车
                response = dispatchUpdateCar(request);
                break;
            default:
                break;
        }
        //返回响应字符串
        return JsonUtil.toJson(response);
    }


    /**
     * 处理登录请求的方法
     *
     * @param request 请求字符串
     * @return 响应内容
     */
    private Response dispatchLogin(String request) {
        // 将通用的JSONObject对象，转化为具体的请求类型，从而更加方便地获得每个请求字段
        LoginRequest loginRequest = JsonUtil.fromJson(request, LoginRequest.class);
        //调用Service
        User user = userService.login(loginRequest.getUsername(),
                loginRequest.getPassword(), loginRequest.getType());
        if (user != null) {
            // code = 200 表示成功
            return new LoginResponse(200, "登录成功", user);
        } else {
            // code = 400 表示失败
            return new LoginResponse(400, "登录失败", user);
        }
    }

    /**
     * 处理注册请求
     *
     * @param request 请求字符串
     * @return 响应内容
     */
    private Response dispatchRegister(String request) {
        // 将通用的JSONObject对象，转化为具体的请求类型，从而更加方便地获得每个请求字段
        UserRegisterRequest registerRequest = JsonUtil.fromJson(request, UserRegisterRequest.class);

        //调用Service
        User user = new User(registerRequest.getUsername(), registerRequest.getPassword(),
                registerRequest.getSex(), registerRequest.getIdNumber(),
                registerRequest.getTel(), registerRequest.getAddr(), registerRequest.getType());
        boolean result = userService.register(user);
        if (result) {
            return new UserRegisterResponse(200, "注册成功", user);
        } else {
            return new UserRegisterResponse(400, "注册失败", null);
        }
    }

    /**
     * 处理普通用户查询汽车请求
     *
     * @param request 请求字符串
     * @return 响应内容
     */
    //ss
    private Response dispatchUserQueryCars(String request) {
        UserQueryCarsRequest userQueryCarsRequest =JsonUtil.fromJson(request,UserQueryCarsRequest.class);
        List<Car> cars =null;
        if ("1".equals(userQueryCarsRequest.getType())){
            cars =carService.queryCars("1");
        }else if ("2".equals(userQueryCarsRequest.getType())){
            cars=carService.queryCars("2");
        }else if ("3".equals(userQueryCarsRequest.getType())){
            cars=carService.queryCars("3");
        }else if ("4".equals(userQueryCarsRequest.getType())){
            cars=carService.queryCars(userQueryCarsRequest.getId());
        }
        if (cars ==null){
            return new UserQueryCarsResponse(400,"查询汽车失败",null);
        }else {
            return new UserQueryCarsResponse(200,"查询汽车成功", cars);
        }
    }

    /**
     * 处理管理员查询汽车请求
     *
     * @param request 请求字符串
     * @return 响应内容
     */
    private Response dispatchAdminQueryCars(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理租赁汽车请求
     *
     * @param request 请求字符串
     * @return 响应内容
     */
    private Response dispatchRentCar(String request) {
        // 将通用的JSONObject对象，转化为具体的请求类型，从而更加方便地获得每个请求字段
        RentCarRequest rentRequest = JsonUtil.fromJson(request, RentCarRequest.class);
        Record record = carService.rentCar(rentRequest.getUserId(), rentRequest.getCarId());
        if (record != null) {
            // code = 200 表示成功
            return new RentCarResponse(200, "租车成功", record);
        } else {
            // code = 400 表示失败
            return new RentCarResponse(400, "租车失败", null);
        }
    }

    /**
     * 处理还车请求
     *
     * @param request
     * @return
     */
    private Response dispatchReturnCar(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理查询租赁记录请求
     *
     * @param request
     * @return
     */
    private Response dispatchQueryRecords(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理查询汽车类别请求
     *
     * @param request
     * @return
     */
    private Response dispatchQueryCategories(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理查询汽车品牌请求
     *
     * @param request
     * @return
     */
    private Response dispatchQueryBrands(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理添加汽车请求
     *
     * @param request
     * @return
     */
    private Response dispatchAddCar(String request) {
        throw new NotImplementedException();
    }

    /**
     * 处理修改汽车的值请求
     *
     * @param request
     * @return
     */
    private Response dispatchUpdateCar(String request) {
        throw new NotImplementedException();
    }
}
