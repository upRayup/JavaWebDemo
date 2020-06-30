package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        try {
            InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
            Properties pro=new Properties();
            pro.load(is);
            //获取数据，设置到JedisPoolConfig中
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            //初始化JedisPool
            jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接方法
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
