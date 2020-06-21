package ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletcontext_demo4")
public class Servletcontext_demo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = this.getServletContext();
        //获取b.txt的真实路径（服务器路径）
        String realPath = sc.getRealPath("/b.txt");
        System.out.println(realPath);
        //获取c.txt的真实路径（服务器路径）
        String realPath1 = sc.getRealPath("/WEB-INF/c.txt");
        System.out.println(realPath1);
        //获取a.txt的真实路径（服务器路径）
        String realPath2 = sc.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(realPath2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
