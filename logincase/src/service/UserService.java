package service;

import domain.PageBean;
import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登陆方法
     * @return
     */
    public User login(User user);

    /**
     * 添加数据
     * @param user
     */
    public void addUser(User user);

    /**
     * 删除数据
     * @param id
     */
    void delUser(String id);

    /**
     * 通过ID查找数据
     * @param id
     */
    User findbyID(String id);

    /**
     * 更新数据
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除选中数据
     * @param ids
     */
    void delselected(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findByPage(String currentPage, String rows, Map<String, String[]> condition);
}
