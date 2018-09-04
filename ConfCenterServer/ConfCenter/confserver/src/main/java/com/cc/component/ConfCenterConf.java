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
     * 配置文件后缀，计划支持：properties,config,yml
     */
    @Value("${confserver.confs.baseEndfix}")
    public String confserver_confs_baseEndfix;

    /**
     * 缓存读取配置文件时间 单位：秒 1*60*60*24 = 86400
     */
    @Value("${confserver.confs.cacheTime}")
    public long confserver_confs_cacheTime;

    /**
     * 默认需要同步配置的版本
     */
    @Value("${confserver.confs.currentConfVersion}")
    public String confserver_confs_currentConfVersion;

}
