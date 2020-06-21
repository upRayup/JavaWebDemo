package demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/response_demo1")
public class Response_demo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo1被访问到了");
//        //设置响应状态码为302，也就是重定向
//        resp.setStatus(302);
//        //设置重定向的页面
//        resp.setHeader("location","/Servlet_Demo/response_demo2");
        //重定向的简便写法
        resp.sendRedirect("/Servlet_Demo/response_demo2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
