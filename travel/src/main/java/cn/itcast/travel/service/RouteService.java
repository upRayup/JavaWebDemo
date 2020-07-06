package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 查询线路具体的信息
     * @param rid
     * @return
     */
    Route findOne(String rid);

    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    Boolean isfavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     * @return
     */
    void addfavorite(int rid, int uid);
}
