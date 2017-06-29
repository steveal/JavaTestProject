package com.test.jedis.pushpop;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisClusterPublish {

	public static void main(String[] args) {
		
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("10.45.80.26", 6379));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.lpush("redistest", "testcluster");
	}

}
