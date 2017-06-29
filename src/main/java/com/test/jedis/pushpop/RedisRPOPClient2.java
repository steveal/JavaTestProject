package com.test.jedis.pushpop;

import redis.clients.jedis.Jedis;

public class RedisRPOPClient2 {
	public static void main(String[] args) {
		Jedis jRedis = new Jedis("10.45.80.26",6379);  
		
		while(true) {
			try {
				Thread.sleep(700);
				String s = jRedis.rpop("redistest");
				if(s!=null) {
					System.out.println("Client2:  " + s);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
