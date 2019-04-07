package com.xintu.sso.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;

@Configuration
public class MyDefaultRibbonConfig {
	
	@Bean
	public IRule ribbonRule() {
		return new MyRule();
	}

	@Bean
	public IPing ribbonPing() {
		return new MyPingUrl(new NIWSDiscoveryPing());
	}

}