package com.xintu.sso.web.feign;

import org.springframework.http.ResponseEntity;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月2日 下午8:21:11
 * @version 1.0 
 * @Function: 自定义调用客户端，实现中直接调用服务api
 */
public interface ConfigServiceClient {

	public String check();
}
