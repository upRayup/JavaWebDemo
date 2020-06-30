package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProvinceDao;
import dao.impl.ProvinceDaoimpl;
import domain.Province;
import redis.clients.jedis.Jedis;
import service.ProvinceService;
import util.JedisPoolUtils;

import java.util.List;

public class ProvinceServiceimpl implements ProvinceService {
    private ProvinceDao dao=new ProvinceDaoimpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 结合Jedis做数据缓存
     * @return
     */
    @Override
    public String findAllJson() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String province = jedis.get("province");
        if(province==null || province.length()==0){
            System.out.println("Jedis中没有数据，查询数据库了");
            //Jedis中没有数据，说明是第一次查询，从数据库中拿出来
            try {
                List<Province> p = dao.findAll();
                ObjectMapper mapper=new ObjectMapper();
                String p_json = mapper.writeValueAsString(p);
                //拿出来后放入Jedis数据库缓存中
                jedis.set("province",p_json);
                return p_json;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Jedis中有数据");
        return province;
    }

}
