package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao=new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //先用redis查询有没有
        Jedis jedis= JedisUtil.getJedis();
        //使用sortedset排序查询
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs=null;
        //redis里没有，需要从数据库里查
        if(category==null || category.size()==0){
            cs = dao.findAll();
            //在数据库里查完，放入redis缓存中
            for(int i=0;i<cs.size();i++){
                //用sortedset存储的时候需要有一个分数依据来排序
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            //redis里有，但是拿出来是set类型，转成List再返回
            cs=new ArrayList<>();
            for(Tuple tuple:category){
                Category cate=new Category();
                cate.setCid((int)tuple.getScore());
                cate.setCname(tuple.getElement());
                cs.add(cate);
            }
        }
        return cs;
    }
}
