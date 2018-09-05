package com.confclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ccc.service.ConfCenterClientService;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/5.
 */
@RestController
public class TestController {

    @Autowired
    ConfCenterClientService confCenterClientService;

    @GetMapping("/getAllConf")
    public Map<String, Object> getAllConf() {
        return confCenterClientService.getConfMaps();
    }
}
