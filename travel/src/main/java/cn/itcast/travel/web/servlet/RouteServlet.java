package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.sun.xml.internal.rngom.parse.host.Base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    /**
     * 分页数据展示
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得数据
        String cidstr = request.getParameter("cid");
        String currentPagestr = request.getParameter("currentPage");
        String pageSizestr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");  //线路名称
        System.out.println(rname);
        int cid = 0;
        if (cidstr != null && cidstr.length() > 0 && !("null").equals(cidstr)) {
            cid = Integer.parseInt(cidstr);
        }
        int currentPage = 1;
        if (currentPagestr != null && currentPagestr.length() > 0) {
            currentPage = Integer.parseInt(currentPagestr);
        } else {
            //如果为空或长度小于等于0
            currentPage = 1;
        }
        int pageSize = 5;
        if (pageSizestr != null && pageSizestr.length() > 0) {
            pageSize = Integer.parseInt(pageSizestr);
        } else {
            //如果为空或长度小于等于0
            pageSize = 5;
        }
        //使用service对象方法
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //转化成json数据写回页面
        writeValue(pb, response);
    }

    /**
     * 查询线路具体信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得页面传过来的rid
        String rid = request.getParameter("rid");
        //调用service对象方法
        Route route = routeService.findOne(rid);
        //转换成json写回页面
        writeValue(route, response);
    }

    /**
     * 判断用户是否已经收藏过该线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取线路数据
        String ridstr = request.getParameter("rid");
        int rid = Integer.parseInt(ridstr);
        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //调用service对象方法
        Boolean flag = routeService.isfavorite(rid, uid);
        //把数据转化成json写回
        writeValue(flag, response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取线路数据
        String ridstr = request.getParameter("rid");
        int rid = Integer.parseInt(ridstr);
        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            return;
        } else {
            uid = user.getUid();
        }
        //调用service对象方法
        routeService.addfavorite(rid, uid);
    }
}
