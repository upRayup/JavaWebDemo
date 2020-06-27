package web;

import domain.PageBean;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class findUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage==null || "".equals(currentPage)){
            currentPage="1";
        }

        if(rows==null || "".equals(rows)){
            rows="5";
        }
        //条件查询的数据
        Map<String, String[]> condition = request.getParameterMap();
        //调用service方法
        UserService userService=new UserServiceImpl();
        PageBean<User> pb=userService.findByPage(currentPage,rows,condition);
        //共享数据，转发
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
