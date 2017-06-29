package com.test.jedis.pushpop;

import redis.clients.jedis.Jedis;

public class RedisLPUSH {

	public static void main(String[] args) {
		Jedis jRedis = new Jedis("10.45.80.26",6379);
		for(int i = 0; i< 50 ; i++) {
			try {
				Thread.sleep(1000);
				jRedis.lpush("redistest", "msg:" + i);
				System.out.println("push redistest:  " + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
