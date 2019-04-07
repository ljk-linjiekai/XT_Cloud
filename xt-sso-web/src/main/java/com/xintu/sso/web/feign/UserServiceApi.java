package com.xintu.sso.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月2日 下午8:21:11
 * @version 1.0 Function: 自定义调用客户端
 */
@FeignClient(value = "xt-sso-service")
public interface UserServiceApi {

	@RequestMapping(value = "/user/check/{param}/{type}", method = RequestMethod.GET)
	public ResponseEntity<String> check(@PathVariable("param") String param, @PathVariable("type") Integer type,
			@RequestParam(value = "callback", required = false) String callback);

	@RequestMapping(value = "/user/{ticket}", method = RequestMethod.GET)
	public ResponseEntity<String> queryUserByTicket(@PathVariable("ticket") String ticket,
			@RequestParam(value = "callback", required = false) String callback);

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test();
}
