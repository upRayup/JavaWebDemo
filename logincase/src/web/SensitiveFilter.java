package web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */
@WebFilter("/*")
public class SensitiveFilter implements Filter {
    private List<String> list=new ArrayList<String>();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    //如果是，增强
                    String value= (String) method.invoke(req,args);
                    if (value!=null){
                        for(String s:list){
                            if(value.contains(s)){
                                value=value.replaceAll(s,"***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        //放行
        chain.doFilter(proxy_req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        try {
            //加载sensitiveword.txt文件，转成List
            //获取真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/sensitiveword.txt");
            //读取文件
            BufferedReader bf=new BufferedReader(new FileReader(realPath));
            String line=null;
            while((line=bf.readLine())!=null){
                list.add(line);
            }
            bf.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}
