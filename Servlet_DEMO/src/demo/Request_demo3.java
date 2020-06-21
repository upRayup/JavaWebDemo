package demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/request_demo3")
public class Request_demo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求头名称:user-agent
        String header = request.getHeader("user-agent");
        if(header.contains("Chrome")){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("我是谷歌O ");
        }
    }
}
