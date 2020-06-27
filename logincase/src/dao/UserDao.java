package dao;

import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {
    /**
     * 查看所有数据
     * @return
     */
    public List<User> findAll();

    /**
     * 查找登陆数据是否正确
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username, String password);

    /**
     * 添加数据
     * @param user
     */
    void add(User user);

    /**
     * 删除数据
     * @param id
     */
    void del(int id);

    /**
     * 通过ID找数据
     * @param parseInt
     */
    User findbyID(int id);

    /**
     * 更新数据
     * @param user
     */
    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 查询每页结果
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
