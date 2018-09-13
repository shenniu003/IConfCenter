package com.cc.service;

import apimodel.EnumHelper;
import apimodel.MoRp;
import com.cc.component.ConfCenterConf;
import com.ct.tconf.LoadConf;
import com.ct.tjedis.JedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 配置中心后台管理
 * Created by Administrator on 2018/9/13.
 */
@Service
public class ConfAdminService {

    @Autowired
    private ConfCenterConf confCenterConf;

    @Autowired
    private JedisTool jedisTool;

    /**
     * 配置文件列表
     *
     * @return
     */
    public List<File> getListConf() {
        File baseFile = new File(confCenterConf.confserver_confs_basepath);
        File[] files = baseFile.listFiles();
        List<File> list = Arrays.asList(files).
                stream().
                sorted(Comparator.comparing(File::lastModified).reversed()).
                collect(Collectors.toList());
        return list;
    }

    /**
     * 启用某个配置+通知消费端（订阅channel规则：confs_配置文件名）
     *
     * @param confPath
     * @return
     */
    public MoRp qyConf(String confPath) {
        MoRp rp = new MoRp();
        rp.setStatus(EnumHelper.EmRpStatus.失败.getVal());

        try {
            //读取配置文件
            Map<String, Object> map = LoadConf.readConfToMap(confPath);
            if (map.isEmpty()) {
                rp.setMessage("加载配置文件失败，稍后重试");
                return rp;
            }

            //文件名称
            String filePathToName = LoadConf.getFilePathToName(confPath,true);

            //缓存key
            String cacheKey = String.format("confs_%s", filePathToName);

            //存储到缓存中 永久
            if (jedisTool.set(cacheKey, map, 0)) {

                //发布消息，通知客户端更新配置
                jedisTool.publish(cacheKey, filePathToName);
                rp.setStatus(EnumHelper.EmRpStatus.成功.getVal());
                rp.setMessage(EnumHelper.EmRpStatus.成功.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rp;
    }
}
