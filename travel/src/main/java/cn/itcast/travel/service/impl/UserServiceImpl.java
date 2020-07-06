package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    /**
     * 用户注册
     * @return
     */
    @Override
    public boolean regist(User user) {
        //查找是否有重复名字
        User u = dao.findByUsername(user.getUsername());
        if(u!=null){
            //有重复名字，注册失败
            return false;
        }
        //设置激活码和激活状态
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        dao.save(user);
        //激活邮件，发送正文
        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //判断激活码是否正确
        User user=dao.findBycode(code);
        if(user!=null){
            //激活码正确，更改激活状态
            dao.updateStatus(user);
            return true;
        }
        return false;
    }

    /**
     * 用户登陆方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return dao.findByUsernameandPassword(user.getUsername(),user.getPassword());
    }
}
