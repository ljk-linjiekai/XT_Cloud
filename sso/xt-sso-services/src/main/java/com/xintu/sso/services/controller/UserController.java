package com.xintu.sso.services.controller;

import com.xintu.sso.domain.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.xintu.sso.api.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林捷凯
 * @version 1.0
 * @Time：2017年2月22日 下午3:03:05
 */

@RequestMapping("/user")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    //cookie的名字
    public static final String COOKIE_TICKET_NAME = "TT_TICKET";
    //cookie的有效时间
    private static final int COOKIE_TICKET_MAX_AGE = 3600;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check() {
        String s = "success";
        logger.info("check::{}",s);
        return s;
    }

    /**
     * 查询param对应的参数是否可用， type为类型，可选参数1、2、3分别代表username、phone、email 支持jsonp
     *
     * @param param 检查的数据
     * @param type  可选参数1、2、3分别代表username、phone、email
     * @return
     * @author 林捷凯
     * @Time：2017年2月22日 下午3:19:19
     */
    @RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
    public ResponseEntity<String> check(@PathVariable("param") String param, @PathVariable("type") Integer type,
                                        @RequestParam(value = "callback", required = false) String callback) {
        try {
            // 判断请求参数
                if (type > 0 && type < 4) {
                String result = "";
                userService.check(param, type);
                if (StringUtils.isNotBlank(callback)) {
                    result = callback + "(" + result + ");";

                }
                // 验证成功返回200
                ResponseEntity<String> ok = ResponseEntity.ok(result);
                logger.info("检查用户是否有登陆标识:{}", ok.getStatusCode());
                return ok;
            } else {
                // 根据接口文档，请求参数不合法时返回400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

        } catch (Exception e) {
            logger.error("检查用户登陆标识 异常:{}", e);
        }
        // 返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * queryUserByTicket:().
     *
     * @param ticket
     * @return
     * @author 林捷凯
     * @Time：2017年2月22日 下午3:40:43
     */
    @RequestMapping(value = "/{ticket}", method = RequestMethod.GET)
    public ResponseEntity<String> queryUserByTicket(@PathVariable("ticket") String ticket,
                                                    @RequestParam(value = "callback", required = false) String callback) {
        try {
            // 判断请求参数
            if (StringUtils.isNotBlank(ticket)) {
                String result = "";
                userService.queryUserStrByTicket(ticket);
                if (StringUtils.isNotBlank(callback)) {
                    result = callback + "(" + result + ");";

                }
                // 验证成功返回200
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            // TODO Auto-gene
            // rated catch block
            e.printStackTrace();
        }
        // 返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 注册用户
     * doRegister:().
     *
     * @param user
     * @return
     * @author 林捷凯
     * @Time：2017年3月4日 上午9:34:15
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> doRegister(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        try {
            userService.save(user);
            map.put("status", 200);
            logger.info("{}：用户注册成功", user.getUsername());
        } catch (Exception e) {
            logger.error("{}：用户注册失败：{}", user.getUsername(), e);
        }

        return ResponseEntity.ok(map);

    }


    /**
     * 登录用户
     * doLogin:().
     *
     * @param user
     * @return
     * @author 林捷凯
     * @Time：2017年3月4日 上午9:41:56
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> doLogin(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        try {
            //登录
            String ticket = userService.login(user.getUsername(), user.getPassword());
            if (ticket != null) {
                //将登录信息设置到cookie中
               // CookieUtils.setCookie(request, response, COOKIE_TICKET_NAME, ticket, COOKIE_TICKET_MAX_AGE);
                map.put("status", 200);
            }
            logger.info("{}：用户登录成功", user.getUsername());
        } catch (Exception e) {
            logger.error("{}：用户登录失败", user.getUsername(), e);
        }

        return ResponseEntity.ok(map);
    }
}
