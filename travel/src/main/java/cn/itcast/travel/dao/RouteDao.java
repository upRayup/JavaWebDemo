package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 查询总记录数
     * @param rid
     * @param rname
     * @return
     */
    public int findtotalCount(int cid, String rname);

    /**
     * 查询当前页码的数据
     * @param rid
     * @param start
     * @param pagesize
     * @param rname
     * @return
     */
    public List<Route> findByPage(int cid, int start, int pagesize, String rname);

    /**
     * 根据rid找线路对象
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
