package demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/req_demo1")
public class Request_demo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求行数据： GET /Servlet_DEMO/req_demo1?username=123456 HTTP/1.1

        //获取请求方式：GET
        String method = request.getMethod();
        System.out.println(method);
        //获取虚拟目录:/Servlet_DEMO
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        //获取资源文件(Servlet)路径：/req_demo1
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        //获取get方式的请求参数：username=123456
        String queryString = request.getQueryString();
        System.out.println(queryString);
        //获取请求URI: /Servlet_DEMO/req_demo1
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //获取请求URL：http://localhost/Servlet_DEMO:war exploded/req_demo1
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        //获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);
        //获取客户机的IP地址：
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
