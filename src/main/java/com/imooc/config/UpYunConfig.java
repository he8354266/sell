package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author CodingSir
 * @Title: project
 * @Package ========
 * @Description: ========
 * @date 2020/8/28  17:40
 */
@Component
@ConfigurationProperties("upyun")
@Data
public class UpYunConfig {
    private String bucketName;

    private String username;

    private String password;

    private String imageHost;
}
