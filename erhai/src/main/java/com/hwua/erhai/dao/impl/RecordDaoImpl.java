package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IRecordDao;
import com.hwua.erhai.entity.Car;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.jdbc.DBUtil;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.jdbc.PreparedStatementSetter;
import com.hwua.erhai.jdbc.ResultSetHandler;
import com.hwua.erhai.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl extends JDBCTemplate implements IRecordDao {

    @Override
    public long addRecord(Connection conn, final long userId, final long carId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long id;
        String sql = "INSERT INTO t_record (id, user_id, car_id, start_date)"
                + " VALUES(null,?,?,?)";
        try {
            // 执行SQL语句
            pstmt = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, userId);
            pstmt.setLong(2, carId);
            pstmt.setString(3, Util.today());
            pstmt.executeUpdate();

            // 获取自增主键id
            rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            DBUtil.close(rs, pstmt);
        }

        // 返回的id是新增记录的主键
        return id;
    }

    @Override
    public int updateRecord(Connection conn, final long recordId, final String returnTime, final double payment) {
        PreparedStatement pstmt = null;

        long id;
        String sql = "update t_record set return_date=?,payment=? where id = ?";
        try {
            // 执行SQL语句
            pstmt = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(3, recordId);
            pstmt.setString(1, Util.today());
            pstmt.setDouble(2, payment);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(String.format("failed to execute sql[%s]", sql), e);
        } finally {
            DBUtil.close( pstmt);
        }

        // 返回的id是新增记录的主键
        return 1;
    }

    @Override
    public List<Record> queryRecordsByUserId(final long userId) {

        final List<Record> list = new ArrayList<>();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where user_Id = ?";

            query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setLong(1,userId);
                }
            }, new ResultSetHandler() {
                @Override
                public void handleRs(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        Record record = new Record(
                                rs.getLong(1),
                                rs.getLong(2),
                                rs.getLong(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getDouble(6)

                        );
                                list.add(record);
                    }

                }
            });


        return list;
    }

    @Override
    public Record queryRecordById(Connection conn, final long id) {
        Record record = new Record();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where user_Id = ?";

        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,id);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    record.setId(rs.getLong(1));
                    record.setUserId(rs.getLong(2));
                    record.setCarId(rs.getLong(3));
                    record.setStartDate(rs.getString(4));
                    record.setReturnDate(rs.getString(5));
                    record.setPayment(rs.getDouble(6));
                }

            }
        });


        return record;
    }

    @Override
    public Record queryNotReturnRecord(Connection conn, final long userId, final long carId) {
        Record record = new Record();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where (user_Id = ? or car_id = ?) and return_date is null ";

        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,userId);
                pstmt.setLong(2,carId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    record.setId(rs.getLong(1));
                    record.setUserId(rs.getLong(2));
                    record.setCarId(rs.getLong(3));
                    record.setStartDate(rs.getString(4));
                    record.setReturnDate(rs.getString(5));
                    record.setPayment(rs.getDouble(6));



                }

            }
        });


        return record;
    }

    @Override
    public List<Record> queryAllRecords() {
        final List<Record> list = new ArrayList<>();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record ";

        query(sql, null,
         new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Record record = new Record(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getLong(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6)

                    );
                    list.add(record);
                }

            }
        });


        return list;
    }

    @Override
    public List<Record> queryRecordsByCarId(final long carId) {
        final List<Record> list = new ArrayList<>();
        String sql = "select Id,user_id,car_id,start_date,return_date,payment from t_record where car_Id = ?";

        query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setLong(1,carId);
            }
        }, new ResultSetHandler() {
            @Override
            public void handleRs(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    Record record = new Record(
                            rs.getLong(1),
                            rs.getLong(2),
                            rs.getLong(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6)

                    );
                    list.add(record);
                }

            }
        });


        return list;
    }

}
