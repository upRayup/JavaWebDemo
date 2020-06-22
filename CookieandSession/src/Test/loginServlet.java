package Test;

import Test.dao.UserDao;
import Test.userclass.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request编码
        request.setCharacterEncoding("utf-8");
        //获取参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String checkCode = request.getParameter("checkCode");
//        User user=new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setCheckCode(checkCode);
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserDao userDao=new UserDao();
        //先判断验证码是否正确
        String checkCode_session = (String)request.getSession().getAttribute("checkCode_session");
        request.getSession().removeAttribute("checkCode_session");
        if(checkCode_session!=null && checkCode_session.equalsIgnoreCase(user.getCheckCode())){//忽略大小写
            //如果正确，判断用户名密码是否正确
            User login = userDao.login(user);
            if(login!=null){
                //登陆成功，存储用户信息
                request.getSession().setAttribute("username",login.getUsername());
                //重定向到success.jsp
                response.sendRedirect(request.getContextPath()+"/success.jsp");

            }else{//登陆失败，转发到登陆界面
                request.setAttribute("login_error","用户名或密码不正确");
                request.getRequestDispatcher("/login.jsp").forward(request,response);

            }
        }else{ //如果不正确，转发到登陆界面
            request.setAttribute("cc_error","验证码不正确");
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
