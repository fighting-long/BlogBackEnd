package my.lxh.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lxh
 * @date 2020-12-09 21:00
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket returnDocket(Environment environment){
        // 如果 不是在dev配置文件下开发 则关闭swagger
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .groupName("吕小辉")
                .select()
                .apis(RequestHandlerSelectors.basePackage("my.lxh.blog.controller"))
                .build();
    }

}
