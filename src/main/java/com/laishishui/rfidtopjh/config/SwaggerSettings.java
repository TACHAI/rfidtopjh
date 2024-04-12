package com.laishishui.rfidtopjh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SwaggerSettings
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2020-05-21 9:13
 */
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerSettings {
    private Boolean enable=false;
}
