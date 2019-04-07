package com.xintu.sso.services.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林捷凯
 * @Time：2018年9月8日 下午4:49:31
 * @version 1.0
 */
@RestController
public class MoniterController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String queryUserByTicket() {
		return "ljk";
	}
}
