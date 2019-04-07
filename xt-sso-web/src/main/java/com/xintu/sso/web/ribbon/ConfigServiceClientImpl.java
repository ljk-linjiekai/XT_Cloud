package com.xintu.sso.web.ribbon;

import com.xintu.sso.web.feign.ConfigServiceApi;
import com.xintu.sso.web.feign.ConfigServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configServiceClientImpl")
public class ConfigServiceClientImpl implements ConfigServiceClient {
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConfigServiceApi api;

	@Override
	public String check() {
		String check = System.currentTimeMillis() +" success";
		logger.info(check);
		return check;
	}
}
