package web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强转为HTTPServlet
        HttpServletRequest request=(HttpServletRequest)req;
        //获取请求资源路径
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/loginServlet") || requestURI.contains("/login.jsp") || requestURI.contains("/css/") || requestURI.contains("/fonts/") || requestURI.contains("/js/") || requestURI.contains("/checkCodeServlet")) {
            //放行
            chain.doFilter(req, resp);
        }else{
            //访问其他页面时，判断是否已经登陆了
            Object loginuser = request.getSession().getAttribute("loginuser");
            if(loginuser!=null){
                //登陆了
                //放行
                chain.doFilter(req, resp);
            }else{
                request.setAttribute("login_error","请先登陆");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }
}
