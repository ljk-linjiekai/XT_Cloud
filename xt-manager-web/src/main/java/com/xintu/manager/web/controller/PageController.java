package com.xintu.manager.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/page")
@Controller
public class PageController {

	/**
	 * 跳转到某个页面
	 * @param pageName 页面名称；转发到/WEB-INF/views/页面名称.jsp
	 * @return
	 */
	@RequestMapping("{pageName}")
	public String toPage(@PathVariable("pageName")String pageName){
		return pageName;
	}
	
	
	
}
