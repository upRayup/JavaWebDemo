package demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/*
获取请求参数通用方式：不论get还是post请求方式都可以使用下列方法来获取请求参数
			1. String getParameter(String name):根据参数名称获取参数值    username=zs&password=123
			2. String[] getParameterValues(String name):根据参数名称获取参数值的数组  hobby=xx&hobby=game
			3. Enumeration<String> getParameterNames():获取所有请求的参数名称
			4. Map<String,String[]> getParameterMap():获取所有参数的map集合
 */
@WebServlet("/request_demo5")
public class Request_demo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post中文乱码解决，设置字符编码为utf-8
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String[] parameterValues = request.getParameterValues("hobby");
//        for (String parameterValue : parameterValues) {
//            System.out.println(parameterValue);
//        }
        Enumeration<String> parameterNames = request.getParameterNames();
//        while(parameterNames.hasMoreElements()){
//            String s = parameterNames.nextElement();
//            String parameter = request.getParameter(s);
//            System.out.println(s+"=="+parameter);
//        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        for (String string : strings) {
            String[] strings1 = parameterMap.get(string);
            System.out.println(string);
            for (String s : strings1) {
                System.out.println(s);
            }
            System.out.println("---------");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
