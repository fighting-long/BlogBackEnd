package my.lxh.blog.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author lxh
 * @date 2021-01-07 12:13
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注入ServerEndpointExporter bean对象，自动注册使用了@ServerEndpoint注解的bean
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}
