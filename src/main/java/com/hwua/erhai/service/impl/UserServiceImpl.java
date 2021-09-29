package com.hwua.erhai.service.impl;

import com.hwua.erhai.dao.IUserDao;
import com.hwua.erhai.dao.impl.UserDaoImpl;
import com.hwua.erhai.entity.User;
import com.hwua.erhai.service.IUserService;

public class UserServiceImpl implements IUserService {
    //目的:能够调用Dao中的方法
    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String userName, String password, int type) {
        //通过传入的参数,查找是否有这样的用户
        User result = userDao.queryUser(userName, password, type);

        if (result != null && result.getUserName() != null) {//如果有,返回这个用户
            return result;
        } else {//如果没有,返回null
            return null;
        }
    }

    @Override
    public boolean register(User user) {
        // 先从数据库中查看是否已经存在同名的用户了
        User result = userDao.queryUser(user.getUserName());
        // 只有当数据库中没有同名的用户时，我们才能创建新的用户
        if (result == null || result.getUserName() == null) {
            int rows = userDao.addUser(user);
            return rows == 1;
        }
        return false;
    }
}
