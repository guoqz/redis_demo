package com.qtummatrix.utils;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPool jedisPool;

    public static Jedis getJedis(){
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
            //连接池最大连接数
            jedisPoolConfig.setMaxTotal(10);
            //最大空闲数
            jedisPoolConfig.setMaxIdle(10);
            //最小空闲数
            jedisPoolConfig.setMinIdle(1);
            //最大等待时间
            jedisPoolConfig.setMaxWaitMillis(3000);
            //验证从连接池中获取的连接对象是否有效
            jedisPoolConfig.setTestOnBorrow(true);
            //验证向连接池中返还连接对象时是否有效
            jedisPoolConfig.setTestOnReturn(true);

            jedisPool=new JedisPool(jedisPoolConfig,
                    "127.0.0.1", 6379, 5000, "root");

        }
        //从连接池中取出一个jedis对象
        Jedis jedis=jedisPool.getResource();
        return jedis;
    }
}
