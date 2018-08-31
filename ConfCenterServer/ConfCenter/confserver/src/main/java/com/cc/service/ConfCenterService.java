package com.cc.service;

import apimodel.MoGetConfRp;
import apimodel.MoGetConfRq;
import com.ct.tjedis.JedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/31.
 */
@Service
public class ConfCenterService {

    @Autowired
    private JedisTool jedisTool;

    /**
     * 获取配置信息
     * @param rq
     * @return
     */
    public MoGetConfRp getconf(MoGetConfRq rq) {

        MoGetConfRp rp = new MoGetConfRp();

        return rp;
    }

    public void publish() {

        String channel = "allConfRefresh";

        jedisTool.publish(channel, "测试" + System.currentTimeMillis());

    }

    public void subscribe() {

        String channel = "allConfRefresh";

        jedisTool.subscribe(channel, b -> {
            System.out.println(b);
        });

    }
}
