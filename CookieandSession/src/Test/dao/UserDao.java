package Test.dao;

import Test.userclass.User;
import Test.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDatasource());
    /**
     * 用户登陆的方法
     * @param loginusername 用户登陆名和密码
     * @return 所有用户信息
     */
    public User login(User loginusername){
        try {
            String sql="select * from USER where username=? and password=?";
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginusername.getUsername(), loginusername.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
