package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param user
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 根据激活码找用户
     * @param code
     * @return
     */
    User findBycode(String code);

    /**
     * 更新激活状态
     * @param user
     */
    void updateStatus(User user);

    User findByUsernameandPassword(String username, String password);
}
