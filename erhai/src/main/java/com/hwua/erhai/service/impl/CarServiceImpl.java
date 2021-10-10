package com.hwua.erhai.service.impl;

import com.hwua.erhai.dao.IBrandDao;
import com.hwua.erhai.dao.ICarDao;
import com.hwua.erhai.dao.ICategoryDao;
import com.hwua.erhai.dao.IRecordDao;
import com.hwua.erhai.dao.impl.BrandDaoImpl;
import com.hwua.erhai.dao.impl.CarDaoImpl;
import com.hwua.erhai.dao.impl.CategoryDaoImpl;
import com.hwua.erhai.dao.impl.RecordDaoImpl;
import com.hwua.erhai.entity.Brand;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Category;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.jdbc.ConnectionFactory;
import com.hwua.erhai.jdbc.DBUtil;
import com.hwua.erhai.service.ICarService;
import com.hwua.erhai.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements ICarService {
    private final ICarDao carDao = new CarDaoImpl();
    private final IRecordDao recordDao = new RecordDaoImpl();
    private final ICategoryDao categoryDao = new CategoryDaoImpl();
    private final IBrandDao brandDao = new BrandDaoImpl();

    @Override
    public List<Car> queryUsableCars(String type, String value) {

        List<Car> cars=null;
        if ("1".equals(type)){
            cars=carDao.queryUsableCarsByPriceDesc();
        }
        else if ("2".equals(type)){
            cars=carDao.queryUsableCarsByPriceAsc();
        }
        else if ("3".equals(type)){
            cars = carDao.queryAllUsableCars();

        }else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return cars;
    }
    public List<Car> queryUsableCars(String type) {

        List<Car> cars=null;
        if ("1".equals(type)){
            cars=carDao.queryUsableCarsByPriceDesc();
        }
        else if ("2".equals(type)){
            cars=carDao.queryUsableCarsByPriceAsc();
        }
        else if ("3".equals(type)){
            cars = carDao.queryAllUsableCars();

        }else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return cars;
    }
    public List<Car> queryCars(String type, String value) {

        List<Car> cars=null;
        if ("1".equals(type)){
            cars=carDao.queryCarsByPriceDesc();
        }
        else if ("2".equals(type)){
            cars=carDao.queryCarsByPriceAsc();
        }
        else if ("3".equals(type)){
            cars = carDao.queryAllCars();
        } else if ("5".equals(type)){
            cars = carDao.queryCarsByCategoryId(Integer.parseInt(value));

        }else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return cars;
    }

    @Override
    public List<Car> queryCars(String type) {
        Car car=new Car();

        List<Car> cars=null;
        if ("1".equals(type)){
            cars=carDao.queryCarsByPriceDesc();
        }
        else if ("2".equals(type)){
            cars=carDao.queryCarsByPriceAsc();
        }
        else if ("3".equals(type)){
            cars = carDao.queryAllCars();

        }
      else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return cars;
    }
    @Override
    public Record rentCar(long userId, long carId) {
        Connection conn = ConnectionFactory.getConnection();
        Record record = null;
        long id;
        try {
            // 一次成功的租车，涉及修改汽车表和租车记录表这两张独立的表，
            // 所以需要启动事务来保证要么两者同时修改成功，要么两者同时不做修改（也就是失败后回滚）
            // 启动本次连接的事务功能
            conn.setAutoCommit(false);
            // 查询当前汽车是否可租赁
            Car car = carDao.queryCarById(conn, carId);
            if (car != null && car.getStatus() == 0 && car.getUsable() == 0) {
                // 如果可以租赁，修改汽车表
                int rows = carDao.updateCar(conn, carId, 1, 0);
                if (rows == 1) {// 返回修改的记录行数为1，说明修改汽车成功
                    // 添加记录
                    id = recordDao.addRecord(conn, userId, carId);
                    record = recordDao.queryRecordById(conn, id);
                } else {
                    throw new Exception(String.format("carDao.updateCar failed, carId[%s]", carId));
                }
            }
            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 如果修改汽车表和租车记录表时发生异常，就回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
        return record;
    }

    @Override
    public Record returnCar(long userId, long carId) {
        Connection conn = ConnectionFactory.getConnection();
        Record record = null;
        long id;
        try {
            // 一次成功的租车，涉及修改汽车表和租车记录表这两张独立的表，
            // 所以需要启动事务来保证要么两者同时修改成功，要么两者同时不做修改（也就是失败后回滚）
            // 启动本次连接的事务功能
            conn.setAutoCommit(false);
            // 查询当前汽车是否可租赁


                // 如果可以租赁，修改汽车表

                    id = recordDao.updateRecord(conn,record.getId(), Util.today(), record.getPayment());
                    record = recordDao.queryRecordById(conn, id);


            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 如果修改汽车表和租车记录表时发生异常，就回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
        return record;
    }

    @Override
    public List<Record> queryRecords(String type, String value) {
        Record record = new Record();
        List<Record> records=null;
        if ("1".equals(type)){
            records=recordDao.queryRecordsByUserId(record.getUserId());
        }
        else if ("2".equals(type)){
            records=recordDao.queryRecordsByCarId(record.getCarId());
        }
        else if ("3".equals(type)){
            records = recordDao.queryAllRecords();
        }else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return records;
    }
    @Override
    public List<Record> queryRecords(String type) {
        Record record = new Record();
        List<Record> records=null;
        if ("1".equals(type)){
            records=recordDao.queryRecordsByUserId(record.getUserId());
        }
        else if ("2".equals(type)){
            records=recordDao.queryRecordsByCarId(record.getCarId());
        }
        else if ("3".equals(type)){
            records = recordDao.queryAllRecords();
        }else{
            throw new UnsupportedOperationException(String.format("unsupported type [%s]",type));
        }

        return records;
    }
    @Override
    public List<Category> queryAllCategories() {

        List<Category> list=null;
        list=categoryDao.queryAllCategories();
        return list;
    }

    @Override
    public List<Brand> queryAllBrands() {

        List<Brand> list =null;
        list=brandDao.queryAllBrand();
        return list;
    }

    @Override
    public boolean addCar(Car car) {
       int b =carDao.addCar(car);
       if (b!=0){
           return true;
       }else
       {
           return  false;
       }

    }

    @Override
    public Car updateCar(String type, String value, long carId) {
        Connection conn = ConnectionFactory.getConnection();
        Car car =null;
        try {
         if ("1".equals(type)){
          int rows =carDao.updateCar(conn,carId,Integer.parseInt(value),0);
          if (rows ==1 ){
              car=carDao.queryCarById(conn,carId);
          }else {
              throw new Exception(String.format("carDao.updateCar failed,carId[%s]",carId));
          }
         }
         else if ("2".equals(type)){
             int rows =carDao.updateCanLendCarRentById(carId,Double.parseDouble(value));
             if (rows==1){
                 car=carDao.queryCarById(conn ,carId);
             }else {
                 throw new Exception(String.format("carDao.updateCar failed,carId[%s]",carId));
             }
         }else if ("3".equals(type)){
          int rows =carDao.updateCanLendCarUsableById(carId,Integer.parseInt(value));
          if (rows==1){
              car=carDao.queryCarById(conn,carId);
          }else {
              throw new Exception(String.format("carDao.updateCar failed,carId[%s]",carId));
          }
         }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
        return car;
    }




}
