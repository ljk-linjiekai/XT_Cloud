package com.xintu.sso.web.feign.impl;

import com.xintu.sso.domain.model.User;
import com.xintu.sso.web.feign.UserServiceApi;
import com.xintu.sso.web.feign.UserServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class UserServiceApiImpl implements UserServiceApi {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceApiImpl.class);

    @Autowired
    private UserServiceClient userServiceClient;

    @SuppressWarnings("finally")
    @Override
    public ResponseEntity<String> check(String param, Integer type, String callback) {
        long s1 = System.currentTimeMillis();
        ResponseEntity<String> check = null;
        try {
            check = userServiceClient.check(param, type, callback);
            logger.info("调用userServiceClient.check,costTime::{},结果::{}", System.currentTimeMillis() - s1,
                    check.getStatusCode());
        } catch (Exception e) {
            logger.error("调用userServiceClient.check,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
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
            queryUserByTicket = userServiceClient.queryUserByTicket(ticket, callback);
            logger.info("调用userServiceClient.queryUserByTicket,costTime::{},结果::{}", System.currentTimeMillis() - s1,
                    queryUserByTicket.getBody());
        } catch (Exception e) {
            logger.error("调用userServiceClient.queryUserByTicket,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
        } finally {
            return queryUserByTicket;
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> doRegister(User user) {
        long s1 = System.currentTimeMillis();
        ResponseEntity<Map<String, Object>> mapResponseEntity = null;
        try {
            mapResponseEntity = userServiceClient.doRegister(user);
            logger.info("调用userServiceClient.doRegister,costTime::{},结果::{}", System.currentTimeMillis() - s1,
                    mapResponseEntity.getBody());
        } catch (Exception e) {
            logger.error("调用userServiceClient.doRegister,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
        } finally {
            return mapResponseEntity;
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        long s1 = System.currentTimeMillis();
        ResponseEntity<Map<String, Object>> mapResponseEntity = null;
        try {
            // 调用后台服务
            mapResponseEntity = userServiceClient.doLogin(user);
            logger.info("调用userServiceClient.doLogin,costTime::{},结果::{}", System.currentTimeMillis() - s1,
                    mapResponseEntity.getBody());
            //TODO 设置cookie

        } catch (Exception e) {
            logger.error("调用userServiceClient.doLogin,costTime::{},异常:{}", System.currentTimeMillis() - s1, e);
        } finally {
            return mapResponseEntity;
        }
    }

    @Override
    public String check() {
        long s1 = System.currentTimeMillis();
        String check = userServiceClient.check();
        logger.info("调用userServiceClient.check,costTime::{},结果::{}", System.currentTimeMillis() - s1, check);
        return check;
    }


}
