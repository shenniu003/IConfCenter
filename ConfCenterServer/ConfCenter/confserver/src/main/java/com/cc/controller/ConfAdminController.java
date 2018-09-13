package com.cc.controller;

import apimodel.EnumHelper;
import apimodel.MoRp;
import com.cc.service.ConfAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * 配置中心后台管理
 * Created by Administrator on 2018/9/13.
 */
@Controller
@RequestMapping("/ConfAdmin")
public class ConfAdminController {

    @Autowired
    ConfAdminService confAdminService;

    /**
     * 配置文件列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/listConf")
    public String listConf(Model model) {

        model.addAttribute("confs", confAdminService.getListConf());
        return "ConfAdmin/listConf";
    }

    /**
     * 启用某个配置+通知消费端
     *
     * @return
     */
    @PostMapping("/qyConf")
    @ResponseBody
    public MoRp qyConf(@RequestParam(name = "confPath") String confPath) {
        MoRp rp = new MoRp();
        rp.setStatus(EnumHelper.EmRpStatus.失败.getVal());
        if (confPath.isEmpty()) {
            rp.setMessage("启用的配置不能为空");
            return rp;
        }
        return confAdminService.qyConf(confPath);
    }
}
