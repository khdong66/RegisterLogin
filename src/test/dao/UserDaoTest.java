package test.dao;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;
import org.junit.Test;

/**
 * UserDao的测试
 *
 * @author dongkaihua
 */
public class UserDaoTest {
    @Test
    public void testFindByUsername() {
        UserDao dao = new UserDao();
        User user = dao.findByUsername("李四");
        System.out.println(user);
    }

    @Test
    public void testAdd() {
        UserDao dao = new UserDao();
        User user = new User();
        user.setUsername("李四");
        user.setPassword("lisi");
        dao.add(user);
    }
}
