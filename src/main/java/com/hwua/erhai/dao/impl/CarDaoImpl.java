package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.ICarDao;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl extends JDBCTemplate implements ICarDao {

    @Override
    public List<Car> queryAllCars() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "ORDER BY car.id";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }
            }
        });
        return list;
    }

    @Override
    public List<Car> queryCarsByPriceAsc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "ORDER BY car.id ASC";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }
            }
        });
        return list;

    }

    @Override
    public List<Car> queryCarsByPriceDesc() {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                + "WHERE car.brand_id = b.id AND car.category_id = cay.id "
                + "ORDER BY car.id DESC";
        query(sql, null, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }
            }
        });
        return list;
    }

    @Override
    public List<Car> queryCarsByCategoryId(final int categoryId) {

        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.category_id = ?"
                ;
<<<<<<< HEAD
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,categoryId);
            }
        }, new ResultSetHandler() {

=======

        query(sql, null, new ResultSetHandler() {
>>>>>>> 6fb05ac (todo categoryID)
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
         return list;
    }
// todo
    @Override
    public List<Car> queryCarsByBrandId(final int brandId) {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.BrandId = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,brandId);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public Car queryCarById(Connection conn, final long id) {
        Car car = null;
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                           car.setId(rs.getLong(1));
                           car.setBrandId(rs.getInt(2));
                           car.setBrandName(rs.getString(3));
                           car.setModel( rs.getString(4));
                           car.setCategoryId( rs.getInt(5));
                           car.setCategoryName(rs.getString(6));
                           car.setComments(rs.getString(7));
                           car.setRent(rs.getDouble(8));
                           car.setStatus(rs.getInt(9));
                           car.setUsable(rs.getInt(10));

                }

            }
        });
        return car;
    }

    @Override
    public int updateCar(Connection conn, final long id, final int status, final int beforeStatus) {
        String sql = "update testdb001.t_car set  car.status =? where car.id =?";
        update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,status);
                pstmt.setLong(2,id);
            }
        });
        return  1;
    }

    @Override
    public List<Car> queryCarById(final long id) {
        final List<Car> list = new ArrayList<>();
        String sql = "SELECT car.id, b.id, b.name, car.model, cay.id, cay.name,"
                + "car.t_comments, car.rent,car.status, car.usable "
                + "FROM t_car car, t_brand b, t_category cay "
                +"where car.brand_id = b.id AND car.category_id = cay.id "
                +"and car.Id = ?"
                ;
        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {

            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Car car = new Car(
                            rs.getLong(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getInt(9),
                            rs.getInt(10));
                    list.add(car);
                }

            }
        });
        return list;
    }

    @Override
    public int addCar(final Car car) {

    }

    @Override
    public int updateCanLendCarRentById(final long carId, final double rent) {
        throw new NotImplementedException();
    }

    @Override
    public int updateCanLendCarUsableById(final long carId, final int usable) {
        throw new NotImplementedException();
    }

    @Override
    public List<Car> queryAllUsableCars() {
        throw new NotImplementedException();
    }

    @Override
    public List<Car> queryUsableCarsByPriceAsc() {
        throw new NotImplementedException();
    }

    @Override
    public List<Car> queryUsableCarsByPriceDesc() {
        throw new NotImplementedException();
    }

    @Override
    public List<Car> queryUsableCarsByCategoryId(final int categoryId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Car> queryUsableCarsByBrandId(final int brandId) {
        throw new NotImplementedException();
    }

}
