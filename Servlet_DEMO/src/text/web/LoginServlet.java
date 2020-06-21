package text.web;

import org.apache.commons.beanutils.BeanUtils;
import text.dao.UserDao;
import text.userclass.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        //新建user对象
//        User user=new User();
//        user.setUsername(username);
//        user.setPassword(password);
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //使用UserDao来判断数据库中是否有匹配数据
        UserDao userDao=new UserDao();
        User login = userDao.login(user);
        if(login!=null){
            request.setAttribute("user",user);
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }else{
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
