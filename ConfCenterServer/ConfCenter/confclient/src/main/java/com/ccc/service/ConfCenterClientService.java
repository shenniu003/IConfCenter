package com.ccc.service;

import apimodel.EnumHelper;
import apimodel.MoGetConfRp;
import apimodel.MoGetConfRq;
import com.ccc.component.ConfCenterConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置中心客户端
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
    public static MoGetConfRp confRp;

    /**
     * 根据key获取val
     *
     * @param key
     * @return
     */
    public String getConfValByKey(String key) {
        if (key.isEmpty()) {
            return "";
        }
        return getConfMaps().get(key).toString();
    }

    /**
     * 获取配置中心全部配置
     *
     * @return
     */
    public Map<String, Object> getConfMaps() {
        getConf();
        return confRp == null ?
                new HashMap<>() : confRp.getConfs();
    }

    /**
     * 获取当前配置版本号
     *
     * @return
     */
    public String getCurrentVersion() {
        return confRp == null ? "" : confRp.getConfVersion();
    }

    /**
     * 获取当前配置版本号
     *
     * @return
     */
    public long getConfLastModified() {
        return confRp == null ? 0L : confRp.getConfLastModified();
    }

    /**
     * 更新本地配置缓存
     *
     * @param rp
     */
    public void setConf(MoGetConfRp rp) {
        if (rp == null || rp.getConfs().isEmpty()) {
            return;
        }
        this.confRp = rp;
    }

    /**
     * 本地缓存配置信息
     *
     * @return
     */
    public MoGetConfRp getConf() {

        if (confRp != null) {
            return confRp;
        }
        MoGetConfRp rp = this.getConfCenterConf(confCenterConf.confserver_confs_currentConfVersion);
        if (rp.getStatus() == EnumHelper.EmRpStatus.成功.getVal()) {
            confRp = rp;
        }
        return confRp;
    }

    /**
     * 获取配置中心配置数据
     *
     * @return
     */
    public MoGetConfRp getConfCenterConf(String strVersion) {

        System.out.println(new Date() + ":请求获取配置中心配置,配置中心地址：" +
                confCenterConf.confserver_confs_confserverurl + ",当前版本：" +
                strVersion);
        //获取中心配置
        MoGetConfRq rq = new MoGetConfRq();
        rq.setConfVersion(strVersion);
        return restTemplate.postForEntity(
                confCenterConf.confserver_confs_confserverurl + "/confcenter/getconf",
                rq,
                MoGetConfRp.class).getBody();
    }

}
