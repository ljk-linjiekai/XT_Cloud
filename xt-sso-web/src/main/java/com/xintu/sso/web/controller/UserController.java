package com.xintu.sso.web.controller;

import com.xintu.sso.domain.model.User;
import com.xintu.sso.web.feign.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 林捷凯
 * @Time：2017年2月22日 下午3:03:05
 * @version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserServiceApi userServiceApi;

	@RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> check(@PathVariable("param") String param, @PathVariable("type") Integer type,
			@RequestParam(value = "callback", required = false) String callback) {
		return userServiceApi.check(param, type, callback);
	}

	@RequestMapping(value = "/{ticket}", method = RequestMethod.GET)
	public ResponseEntity<String> queryUserByTicket(@PathVariable("ticket") String ticket,
			@RequestParam(value = "callback", required = false) String callback) {
		return userServiceApi.queryUserByTicket(ticket,callback);
	}

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doRegister(@RequestBody User user){
		return userServiceApi.doRegister(user);
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doLogin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
		return userServiceApi.doLogin(user,request,response);
	}

	@RequestMapping(value = "/checkService", method = RequestMethod.GET)
	public String check() {
		return userServiceApi.check();
	}
}
