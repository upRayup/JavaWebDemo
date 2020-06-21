package ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletcontext_demo1")
public class Servletcontext_demo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext对象的两种方式
        //1.
        ServletContext sc1 = request.getServletContext();
        System.out.println(sc1);
        //2.
        ServletContext sc2 = this.getServletContext();
        System.out.println(sc2);
        //判断是否相等
        System.out.println(sc1==sc2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
