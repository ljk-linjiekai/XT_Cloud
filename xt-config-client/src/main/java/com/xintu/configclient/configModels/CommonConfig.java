package com.xintu.configclient.configModels;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope //开启更新功能
@Data
@ConfigurationProperties
@Component
public class CommonConfig {

    private String profile;
}
