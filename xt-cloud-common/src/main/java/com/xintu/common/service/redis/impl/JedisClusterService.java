package com.xintu.common.service.redis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xintu.common.service.RedisService;

import redis.clients.jedis.JedisCluster;

public class JedisClusterService implements RedisService {
	
	//非必要注入；当能查找得到的时候则注入
	@Autowired(required = false)
	private JedisCluster jedisCluster;

	@Override
	public String set(String key, String value) {
		String result = jedisCluster.set(key, value);
		return result;
	}

	@Override
	public String setex(String key, int seconds, String value) {
		String result = jedisCluster.setex(key, seconds, value);
		return result;

	}

	@Override
	public String get(String key) {
		String result = jedisCluster.get(key);
		return result;

	}

	@Override
	public Long expire(String key, int seconds) {
		Long result = jedisCluster.expire(key, seconds);
		return result;

	}

	@Override
	public Long del(String key) {
		Long result = jedisCluster.del(key);
		return result;

	}

	@Override
	public Long incr(String key) {
		Long result = jedisCluster.incr(key);
		return result;

	}

	@Override
	public Long hset(String key, String field, String value) {
		Long result = jedisCluster.hset(key, field, value);
		return result;
	}

	@Override
	public String hget(String key, String field) {
		String result = jedisCluster.hget(key, field);
		return result;
	}

	@Override
	public Boolean hexists(String key, String field) {
		Boolean result = jedisCluster.hexists(key, field);
		return result;
	}
	@Override
	public List<String> hvals(String key) {
		List<String> result = jedisCluster.hvals(key);
		return result;
	}
	
	@Override
	public Long hdel(String key, String field) {
		Long result = jedisCluster.hdel(key, field);
		return result;
	}

}
