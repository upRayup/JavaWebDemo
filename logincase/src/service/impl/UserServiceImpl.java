package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.PageBean;
import domain.User;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao=new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void delUser(String id) {
        dao.del(Integer.parseInt(id));
    }

    @Override
    public User findbyID(String id) {
        return dao.findbyID(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delselected(String[] ids) {
        for (String id : ids) {
            dao.del(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
        PageBean<User> pb=new PageBean<User>();
        pb.setRows(rows);
        //计算总记录数
        int totalCount=dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //计算开始索引
        int start=(currentPage-1)*rows;
        //计算总页码
        int totalPage=totalCount%rows==0?totalCount/rows:totalCount/rows+1;
        pb.setTotalPage(totalPage);
        //每页结果
        List<User> list=dao.findByPage(start,rows,condition);
        pb.setList(list);
        pb.setCurrentPage(currentPage);
        return pb;
    }
}
