package com.xintu.sso.web.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月8日 下午2:58:59
 * @version 1.0
 * @Function: 
 * RibbonClients定义所有的ribbon客户的默认配置，如果只为特定ribbon客户提供配置，可以使用@RibbonClient，但是必须保证MyDefaultRibbonConfig不能被@ComponentScan扫描掉，否则MyDefaultRibbonConfig会被所有@RibbonClients共享
 * 如果配置文件配置了ribbon规则，则可以不配置该类
 * xt-sso-service:
  	ribbon:
    NIWSServerListClassName: com.xintu.sso.web.config.MyDiscoveryEnabledNIWSServerList
    NFLoadBalancerRuleClassName: com.xintu.sso.web.config.MyRule
 */
@RibbonClients(defaultConfiguration = MyDefaultRibbonConfig.class)
public class MyRibbonClients {

}