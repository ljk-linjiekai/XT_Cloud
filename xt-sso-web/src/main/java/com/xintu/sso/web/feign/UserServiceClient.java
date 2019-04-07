package com.xintu.sso.web.feign;

import com.xintu.sso.domain.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月2日 下午8:21:11
 * @version 1.0 Function: 自定义调用客户端
 */
@FeignClient(value = "xt-sso-service",path = "/user")
public interface UserServiceClient {

	@RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> check(@PathVariable("param") String param, @PathVariable("type") Integer type,
			@RequestParam(value = "callback", required = false) String callback);

	@RequestMapping(value = "/{ticket}", method = RequestMethod.GET)
	public ResponseEntity<String> queryUserByTicket(@PathVariable("ticket") String ticket,
			@RequestParam(value = "callback", required = false) String callback);

	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doRegister(@RequestBody User user);

	//FeignClient只允许有一个RequestBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doLogin(@RequestBody User use);

}
