package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface RouteSellerDao {
    public Seller findBySid(int sid);
}
