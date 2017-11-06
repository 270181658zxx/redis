package com.imooc.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class jedisDemo1 {

    @Test
    public void demo1() {
        //1.设置ip地址
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.保存数据
        jedis.set("name", "imooc");
        //3.获取数据
        String value = jedis.get("name");
        System.out.println(value);
        //4.释放资源
        jedis.close();
    }

    @Test
    /**
     * 连接池的方式
     */ public void test() {
        //获取连接池
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //最大空闲连接数
        config.setMaxIdle(10);
        //获取连接池
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        //获取核心对象
        Jedis jedis = null;
        try {
            //通过连接池获取连接
            jedis = jedisPool.getResource();
            //设置数据
            jedis.set("name", "张三");
            //获取数据
            String value = jedis.get("name");
            System.out.println(value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }
}
