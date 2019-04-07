package com.xintu.common.service.redis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xintu.common.service.RedisFunction;
import com.xintu.common.service.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolService implements RedisService {
	
	@Autowired(required = false)
	private JedisPool jedisPool;
	
	private <T> T execute(RedisFunction<T,Jedis> fun) {
		Jedis jedis = null;
		T result;
		try {
			jedis = jedisPool.getResource();
			result = fun.callback(jedis);
		} finally {
			jedis.close();
		}
		
		return result;
	}

	@Override
	public String set(final String key, final String value) {
		return execute(new RedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.set(key, value);
			}
			
		});
	}
	
	@Override
	public String setex(final String key, final int seconds, final String value) {
		return execute(new RedisFunction<String, Jedis>() {
			
			@Override
			public String callback(Jedis jedis) {
				return jedis.setex(key, seconds, value);
			}
			
		});
	}

	@Override
	public String get(final String key) {
		return execute(new RedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.get(key);
			}

		});

	}

	@Override
	public Long expire(final String key, final int seconds) {
		return execute(new RedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
			
		});

	}

	@Override
	public Long del(final String key) {
		
		return execute(new RedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.del(key);
			}
			
		});

	}

	@Override
	public Long incr(final String key) {
		return execute(new RedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.incr(key);
			}
			
		});

	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		return execute(new RedisFunction<Long, Jedis>() {

			@Override
			public Long callback(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
			
		});
	}

	@Override
	public String hget(final String key, final String field) {
		return execute(new RedisFunction<String, Jedis>() {

			@Override
			public String callback(Jedis jedis) {
				return jedis.hget(key, field);
			}
			
		});

	}
	@Override
	public Boolean hexists(final String key, final String field) {
		return execute(new RedisFunction<Boolean, Jedis>() {
			
			@Override
			public Boolean callback(Jedis jedis) {
				return jedis.hexists(key, field);
			}
			
		});
		
	}
	@Override
	public List<String> hvals(final String key) {
		return execute(new RedisFunction<List<String>, Jedis>() {
			
			@Override
			public List<String> callback(Jedis jedis) {
				return jedis.hvals(key);
			}
			
		});
		
	}
	@Override
	public Long hdel(final String key, final String field) {
		return execute(new RedisFunction<Long, Jedis>() {
			
			@Override
			public Long callback(Jedis jedis) {
				return jedis.hdel(key, field);
			}
			
		});
		
	}

}
