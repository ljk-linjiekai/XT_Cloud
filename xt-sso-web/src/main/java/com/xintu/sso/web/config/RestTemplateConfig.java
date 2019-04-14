package com.xintu.sso.web.config;

import lombok.Data;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月2日 下午8:20:56
 * @version 1.0 Function: rest配置类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.resttemplate.cz.b2c")
@Data
public class RestTemplateConfig {

	private Integer maxConnectionTotal;
	private Integer defaultMaxPerRoute;
	private Integer connectTimeout;
	private Integer readTimeout;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection
		cm.setMaxTotal(maxConnectionTotal);
		// Increase default max connection per route
		cm.setDefaultMaxPerRoute(defaultMaxPerRoute);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		RequestConfig.Builder builder = RequestConfig.custom();
		builder.setSocketTimeout(20000);

		RequestConfig requestConfig = builder.build();
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		HttpClient httpClient = httpClientBuilder.setConnectionManager(cm).build();
		HttpComponentsClientHttpRequestFactory httpFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		httpFactory.setConnectTimeout(connectTimeout);
		httpFactory.setReadTimeout(readTimeout);
		RestTemplate rest = new RestTemplate(httpFactory);
		return rest;
	}
}
