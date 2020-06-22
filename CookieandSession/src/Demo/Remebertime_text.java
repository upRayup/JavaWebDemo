package Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
案例需求：
	1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
	2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 */
@WebServlet("/Remebertime_text")
public class Remebertime_text extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //响应头设置字符编码
        response.setContentType("text/html;charset=utf-8");

        boolean flag=false;
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String str_date = sdf.format(date);
        //特殊字符可能无法辨识，使用URL编码
        String encode = URLEncoder.encode(str_date, "utf-8");

        //获取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length!=0){
            //查看是否有这个cookie
            for (Cookie cookie : cookies) {
                //如果有，调用里面的值就是上次访问时间了
                if(cookie.getName().equals("lasttime")){
                    flag=true;
                    String value = cookie.getValue();
                    String decode = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("欢迎回来，您上次访问时间为:"+decode);
                    //赋新的时间值，再传到浏览器上
                    cookie.setValue(encode);
                    cookie.setMaxAge(60*60*24*30);//存活时间为一个月
                    response.addCookie(cookie);
                }
            }
        }
        if(cookies==null || cookies.length==0 || flag==false){
            response.getWriter().write("您好，欢迎您首次访问。");
            //赋新的时间值，再传到浏览器上
            Cookie cookie=new Cookie("lasttime",encode);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
