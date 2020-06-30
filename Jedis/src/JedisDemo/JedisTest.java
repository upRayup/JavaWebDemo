package JedisDemo;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.JedisPoolUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis的测试类
 */
public class JedisTest {
    /**
     * String类型
     */
    @Test
    public void Test01(){
        //创建Jedis对象
        Jedis jedis=new Jedis("localhost");
        //执行命令
        jedis.set("username","Ray");
        jedis.setex("tempcode",10,"520");
        String username = jedis.get("username");
        System.out.println(username);
        //关闭连接
        jedis.close();
    }
    /**
     * Hash类型
     */
    @Test
    public void Test02(){
        //创建Jedis对象
        Jedis jedis=new Jedis();
        //执行命令
        jedis.hset("user","name","Ray");
        jedis.hset("user","age","22");
        jedis.hset("user","gender","male");

        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keys = user.keySet();
        for (String key : user.keySet()) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
        //关闭连接
        jedis.close();
    }
    /**
     * List类型
     */
    @Test
    public void Test03(){
        //创建Jedis对象
        Jedis jedis=new Jedis();
        //执行命令
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        String mylist1 = jedis.lpop("mylist");
        System.out.println(mylist1);
        String mylist2 = jedis.rpop("mylist");
        System.out.println(mylist2);

        List<String> mylist3 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist3);
        //关闭连接
        jedis.close();
    }
    /**
     * set类型
     */
    @Test
    public void Test04(){
        //创建Jedis对象
        Jedis jedis=new Jedis();
        //执行命令
        jedis.sadd("myset","Issac","12","male");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        //关闭连接
        jedis.close();
    }
    /**
     * sortedset类型
     */
    @Test
    public void Test05(){
        //创建Jedis对象
        Jedis jedis=new Jedis();
        //执行命令
        jedis.zadd("mysort",5,"chen");
        jedis.zadd("mysort",10,"xin");
        jedis.zadd("mysort",8,"yi");
        Set<String> mysort = jedis.zrange("mysort", 0, -1);
        System.out.println(mysort);
        //关闭连接
        jedis.close();
    }
    @Test
    public void Test06(){
        //配置对象
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        //从连接池中获取
        JedisPool jedisPool=new JedisPool(jedisPoolConfig,"localhost",6379);
        Jedis jedis=jedisPool.getResource();
        jedis.set("age","haha");
        String age = jedis.get("age");
        System.out.println(age);
        //归还
        jedis.close();
    }
    @Test
    public void Test07(){
        Jedis jedis= JedisPoolUtils.getJedis();
        jedis.set("age","heihei");
        String age = jedis.get("age");
        System.out.println(age);
        //归还
        jedis.close();
    }
}
