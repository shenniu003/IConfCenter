package com.cc.controller;

import apimodel.MoGetConfRp;
import apimodel.MoGetConfRq;
import apimodel.MoRefreshAllConfRp;
import apimodel.MoRefreshAllConfRq;
import com.cc.service.ConfCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/8/31.
 */
@RestController
@RequestMapping("/confcenter")
public class ConfCenterController {

    @Autowired
    private ConfCenterService confCenterService;

    /**
     * 根据版本号 获取配置
     *
     * @param rq
     * @return
     */
    @PostMapping("/getconf")
    public MoGetConfRp getconf(@RequestBody MoGetConfRq rq) {
        return confCenterService.getconf(rq);
    }
}
