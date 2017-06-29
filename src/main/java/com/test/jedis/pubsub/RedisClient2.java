package com.test.jedis.pubsub;
import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPubSub;  
  
public class RedisClient2 {  
    public static void main(String[] args){  
        Jedis jRedis = new Jedis("10.45.80.26",6379);  
        MyJedisPubSubAdapter jedisPubSub=new MyJedisPubSubAdapter() {  
            @Override  
            public void onMessage(String channel, String message) {  
                super.onMessage(channel, message);  
                System.out.println("client2" + message);  
            }  
        };  
        jRedis.subscribe(jedisPubSub,"JRedisChat");  
    }  
}