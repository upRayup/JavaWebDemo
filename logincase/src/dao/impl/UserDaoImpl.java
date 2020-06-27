package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //定义sql
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    /**
     * 用户名密码是否能正确登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void del(int id) {
        String sql="delete from user where id=?";
        template.update(sql,id);
    }

    @Override
    public User findbyID(int id) {
        String sql="select * from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void update(User user) {
        String sql="update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql="select count(*) from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List<Object> param=new ArrayList<Object>();
        //遍历map
        Set<String> keyset = condition.keySet();
        for (String key : keyset) {
            //跳过不想要的键值对
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            String value = condition.get(key)[0];
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ?");
                param.add("%"+value+"%");
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,param.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List<Object> param=new ArrayList<Object>();
        //遍历map
        Set<String> keyset = condition.keySet();
        for (String key : keyset) {
            //跳过不想要的键值对
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            String value = condition.get(key)[0];
            if(value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                param.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,? ");
        param.add(start);
        param.add(rows);
        return template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), param.toArray());
    }
}
