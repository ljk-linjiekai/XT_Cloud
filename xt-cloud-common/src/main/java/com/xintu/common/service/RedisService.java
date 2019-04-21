package com.xintu.common.service;

import java.util.List;

/**
 * @author 林捷凯
 * @Time：2017年2月19日 下午6:53:17
 * @version 1.0
 */
public interface RedisService {

	// 设置
	public String set(String key, String value);

	// 设置并同时设置过期时间
	public String setex(String key, int seconds, String value);

	// 设置key过期
	public Long expire(String key, int seconds);

	// 获取key值
	public String get(String key);

	// 删除key
	public Long del(String key);

	// 自增
	public Long incr(String key);

	// 对散列设置键值
	public Long hset(String key, String field, String value);

	// 获取散列设置键值
	public String hget(String key, String field);

	// 判断field是否存在key对应的散列中
	public Boolean hexists(String key, String field);

	// 获取key散列表对应的所有值
	public List<String> hvals(String key);

	// 根据key和field删除对应的field的值
	public Long hdel(String key, String field);
}
