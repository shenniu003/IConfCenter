package com.cc.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/3.
 */
@Component
public class ConfCenterConf {

    /**
     * 配置中心上传配置文件存储路径
     */
    @Value("${confserver.confs.basepath}")
    public String confserver_confs_basepath;

    /**
     * 默认需要同步配置的版本
     */
    @Value("${confserver.confs.currentConfVersion}")
    public String confserver_confs_currentConfVersion;

}
