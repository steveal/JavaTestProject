package com.test.jedis.pushpop;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisRPOPClient1 {
	public static void main(String[] args) {
		Jedis jRedis = new Jedis("10.45.80.26",6379);  

		while(true) {
			try {
				Thread.sleep(600);
				String s = jRedis.rpop("redistest");
				if(s!=null) {
					System.out.println("Client1:  " + s);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
