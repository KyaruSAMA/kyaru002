package com.hwua.erhai.dao.impl;

import com.hwua.erhai.dao.IRecordDao;
import com.hwua.erhai.entity.Record;
import com.hwua.erhai.jdbc.DBUtil;
import com.hwua.erhai.jdbc.JDBCTemplate;
import com.hwua.erhai.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
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
        throw new NotImplementedException();
    }

    @Override
    public List<Record> queryRecordsByUserId(final long userId) {
        throw new NotImplementedException();
    }

    @Override
    public Record queryRecordById(Connection conn, final long id) {
        throw new NotImplementedException();
    }

    @Override
    public Record queryNotReturnRecord(Connection conn, final long userId, final long carId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Record> queryAllRecords() {
        throw new NotImplementedException();
    }

    @Override
    public List<Record> queryRecordsByCarId(final long carId) {
        throw new NotImplementedException();
    }

}
