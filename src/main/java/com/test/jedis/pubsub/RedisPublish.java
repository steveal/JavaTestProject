package com.test.jedis.pubsub;
import redis.clients.jedis.Jedis;  
  
public class RedisPublish {  
    public static void main(String[] args){  
        Jedis jRedis = new Jedis("10.45.80.26",6379);  
        jRedis.publish("JRedisChat","my name is chenLong");  
        jRedis.publish("JRedisChat","Hello chenLong!");  
    } 
}