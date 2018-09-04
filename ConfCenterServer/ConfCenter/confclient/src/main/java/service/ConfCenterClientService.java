package service;

import apimodel.MoGetConfRp;
import apimodel.MoGetConfRq;
import component.ConfCenterConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/4.
 */
@Service
public class ConfCenterClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfCenterConf confCenterConf;

    /**
     * 从配置中心获取来的配置信息
     */
    public static Map<String, Object> mapConfis;

    /**
     * 获取配置中心配置
     *
     * @param rq
     * @return
     */
    public MoGetConfRp getconf(MoGetConfRq rq) {

        //获取中心配置
        MoGetConfRp rp = restTemplate.postForObject(
                confCenterConf + "/confcenter/getconf",
                rq,
                MoGetConfRp.class);

        //本地缓存


        return rp;
    }
}
