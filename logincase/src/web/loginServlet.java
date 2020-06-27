package web;

import dao.UserDao;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //判断验证码是否正确
        if(checkcode==null || !checkcode.equalsIgnoreCase(verifycode)){
            //不正确，记录错误信息，返回登陆界面
            request.setAttribute("login_error","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //验证码正确
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        //通过service判断用户名密码是否正确
        UserService userService=new UserServiceImpl();
        User login = userService.login(user);
        if(login==null){
            //用户名密码错误
            request.setAttribute("login_error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        session.setAttribute("loginuser",login);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
