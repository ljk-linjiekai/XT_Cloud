package com.xintu.sso.web.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.xintu.sso.web.feign.UserServiceApi;
import com.xintu.sso.web.feign.UserServiceClient;

@Service("userServiceEurekaClient")
public class UserServiceEurekaClient implements UserServiceClient {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserServiceApi userServiceApi;

	@SuppressWarnings("finally")
	@Override
	public ResponseEntity<String> check(String param, Integer type, String callback) {
		long s1 = System.currentTimeMillis();
		ResponseEntity<String> check = null;
		try {
			check = userServiceApi.check(param, type, callback);
			logger.info("调用userServiceApi.check,costTime::{},结果::{}", System.currentTimeMillis() - s1,
					check.getStatusCode());
		} catch (Exception e) {
			logger.error("调用userServiceApi.check,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
		} finally {
			return check;
		}

	}

	@SuppressWarnings("finally")
	@Override
	public ResponseEntity<String> queryUserByTicket(String ticket, String callback) {
		long s1 = System.currentTimeMillis();
		ResponseEntity<String> queryUserByTicket = null;
		try {
			queryUserByTicket = userServiceApi.queryUserByTicket(ticket, callback);
			logger.info("调用userServiceApi.queryUserByTicket,costTime::{},结果::{}", System.currentTimeMillis() - s1,
					queryUserByTicket.getBody());
		} catch (Exception e) {
			logger.error("调用userServiceApi.queryUserByTicket,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
		} finally {
			return queryUserByTicket;
		}
	}

	@Override
	public String test() {
		long s1 = System.currentTimeMillis();
		String test = userServiceApi.test();
		logger.info("调用userServiceApi.test,costTime::{},结果::{}", System.currentTimeMillis() - s1, test);
		return test;
	}

}
