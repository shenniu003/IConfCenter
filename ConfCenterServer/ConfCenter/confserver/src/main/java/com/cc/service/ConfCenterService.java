package com.cc.service;

import apimodel.*;
import com.cc.component.ConfCenterConf;
import com.ct.tconf.PropertiesConf;
import com.ct.tjedis.JedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
@Service
public class ConfCenterService {

    @Autowired
    private JedisTool jedisTool;

    @Autowired
    private ConfCenterConf confCenterConf;

    /**
     * 获取配置信息
     *
     * @param rq
     * @return
     */
    public MoGetConfRp getconf(MoGetConfRq rq) {

        MoGetConfRp rp = new MoGetConfRp();
        Map<String, Object> map = null;
        try {
            //未指定配置版本，采用默认配置版本号
            if (rq.getConfVersion().isEmpty()) {
                rq.setConfVersion(confCenterConf.confserver_confs_currentConfVersion);
            }
            if (rq.getConfVersion().isEmpty()) {
                rp.setMessage("未找到配置版本号");
                return rp;
            }

            //缓存key
            String cacheKey = String.format("confs_%s", rq.getConfVersion());

            //获取缓存中是否存在
            map = jedisTool.get(cacheKey, Map.class);
            if (map != null && !map.isEmpty()) {
                rp.setConfs(map);
                rp.setStatus(EnumHelper.EmRpStatus.成功.getVal());
                rp.setMessage(EnumHelper.EmRpStatus.成功.toString());
                rp.setConfVersion(rq.getConfVersion());
                return rp;
            }

            //读取配置文件
            String basepath = String.format("%s\\%s.%s",
                    confCenterConf.confserver_confs_basepath,
                    rq.getConfVersion(),
                    confCenterConf.confserver_confs_baseEndfix);
            map = PropertiesConf.readConfToMap(basepath);
            if (map.isEmpty()) {
                rp.setMessage("加载配置文件失败，稍后重试");
                return rp;
            }

            //存储到缓存中
            if (jedisTool.set(cacheKey, map, confCenterConf.confserver_confs_cacheTime)) {
                rp.setConfs(map);
                rp.setStatus(EnumHelper.EmRpStatus.成功.getVal());
                rp.setMessage(EnumHelper.EmRpStatus.成功.toString());
                rp.setConfVersion(rq.getConfVersion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rp;
    }

    /**
     * 通知所有客户端 重新获取配置
     *
     * @param rq
     * @return
     */
    public MoRefreshAllConfRp refreshAllConf(MoRefreshAllConfRq rq) {
        MoRefreshAllConfRp rp = new MoRefreshAllConfRp();

        jedisTool.publish(
                EnumHelper.EmChannel.客户端全部刷新配置channel.getKey(),
                rq.getConfVersion());

        rp.setStatus(EnumHelper.EmRpStatus.成功.getVal());
        rp.setMessage(EnumHelper.EmRpStatus.成功.toString());
        return rp;
    }
}
