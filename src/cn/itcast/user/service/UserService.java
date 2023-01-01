package cn.itcast.user.service;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

/**
 * 业务逻辑层
 *
 * @author dongkaihua
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 注册功能
     *
     * @param user
     * @throws UserException
     */
    public void regist(User user) throws UserException {
        /*
        1. 使用用户名去查询，如果返回null，完成添加
        2. 如果返回的不是null，抛出异常
         */
        User _user = userDao.findByUsername(user.getUsername());
        if (_user != null) throw new UserException("用户名" + user.getUsername() + "，已被注册过来！");
        userDao.add(user);
    }

    /**
     * 登录功能
     * @param form
     * @return
     */
    public User login(User form) throws UserException {
        // 使用form中的username进行查询，得到User user
        User user = userDao.findByUsername(form.getUsername());
        // 如果返回null，说明用户名不存在，抛出异常，异常信息为"用户名不存在"
        if (user == null) throw new UserException("用户名不存在");
        // 比较user的password和form的password，如果不同，抛出异常，异常信息为"密码错误"！
        if (!form.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误");
        }
        // 返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码，而user是所有用户信息！
        return user;
    }
}
