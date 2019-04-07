package com.xintu.common.service;
/**
 * @author 林捷凯
 * @Time：2017年2月19日 下午7:02:08
 * @version 1.0
 */
public interface RedisFunction<T,E> {

	public T callback(E jedis);
}
