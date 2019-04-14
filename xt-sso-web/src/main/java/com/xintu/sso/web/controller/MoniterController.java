package com.xintu.sso.web.controller;

import com.xintu.sso.web.feign.UserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林捷凯
 * @version 1.0
 * @Time：2018年9月8日 下午4:49:31
 */
@RestController
public class MoniterController {
    private static final Logger logger = LoggerFactory.getLogger(MoniterController.class);


    @RequestMapping(value = "/check", method = RequestMethod.GET)
        public String check() {
        String s = "success";
        logger.info("check::{}",s);
        return s;
    }
}
