package component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/9/3.
 */
@Component
public class ConfCenterConf {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }

    /**
     * #配置中心服务地址
     */
    @Value("${confserver.confs.confserverurl}")
    public String confserver_confs_confserverurl;

}
