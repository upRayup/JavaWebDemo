package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.RouteSellerDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao=new RouteDaoImpl();
    private RouteImgDao imgdao=new RouteImgDaoImpl();
    private RouteSellerDao sellerdao=new RouteSellerDaoImpl();
    private FavoriteDao favoritedao=new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pageBean=new PageBean<Route>();
        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置每页显示数量
        pageBean.setPageSize(pageSize);
        //设置总页码
        int totalCount=dao.findtotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);
        //设置每页数据
        List<Route> list=dao.findByPage(cid,(currentPage-1)*pageSize,pageSize,rname);
        pageBean.setList(list);
        //设置总页数
        int totalPage=totalCount%pageSize==0 ? totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(String ridstr) {
        int rid=Integer.parseInt(ridstr);
        //根据rid找线路对象
        Route route=dao.findOne(rid);
        //加入图片信息
        List<RouteImg> routeimgs = imgdao.findByRid(rid);
        route.setRouteImgList(routeimgs);
        //加入商家信息
        Seller seller = sellerdao.findBySid(route.getSid());
        route.setSeller(seller);
        //查询收藏次数
        int count=favoritedao.findCountByRid(rid);
        route.setCount(count);
        return route;
    }

    @Override
    public Boolean isfavorite(int rid, int uid) {
        Favorite favorite = favoritedao.findByRidAndUid(rid, uid);
        //判断是否存在这个对象，是返回true，不是返回false
        return favorite!=null;
    }

    @Override
    public void addfavorite(int rid, int uid) {
        favoritedao.add(rid,uid);
    }
}
