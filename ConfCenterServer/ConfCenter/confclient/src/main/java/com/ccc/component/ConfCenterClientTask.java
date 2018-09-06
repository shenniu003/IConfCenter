package com.ccc.component;

import apimodel.EnumHelper;
import apimodel.MoGetConfRp;
import com.ct.tjedis.JedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ccc.service.ConfCenterClientService;

import java.util.Date;

/**
 * 配置中心客户端任务
 * Created by Administrator on 2018/9/4.
 */
@Component
public class ConfCenterClientTask implements CommandLineRunner {

    @Autowired
    private ConfCenterClientService confCenterClientService;

    @Autowired
    private ConfCenterConf confCenterConf;

    @Autowired
    private JedisTool jedisTool;

    /**
     * 每分钟获取配置中心配置+（版本号不一致）更新本地缓存
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void refreshConf() {
        System.out.println(new Date() + ":当前配置版本号" + confCenterClientService.getCurrentVersion());
        //如果设置指定版本号不做更新
        if (!confCenterConf.confserver_confs_currentConfVersion.isEmpty()) {
            return;
        }
        System.out.println(new Date() + ":自动拉取配置中心配置");
        updateConf(confCenterClientService.getCurrentVersion());
    }

    /**
     * 程序启动执行服务
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        //订阅配置中心刷新配置通道
        jedisTool.subscribe(
                EnumHelper.EmChannel.客户端全部刷新配置channel.getKey(),
                b -> {
                    System.out.println(new Date() + ":收到配置中心刷新配置通知，版本号-" + b);
                    updateConf(b.toString());
                });
    }

    private void updateConf(String strVersion) {
        //获取配置中心配置
        MoGetConfRp rp = confCenterClientService.getConfCenterConf(strVersion);
        if (rp.getStatus() != EnumHelper.EmRpStatus.成功.getVal()) {
            return;
        }

        //版本号一致不做处理
        if (rp.getConfVersion().equals(confCenterClientService.getCurrentVersion())) {
            return;
        } else {
            System.out.println(new Date() + ":更新本地配置");
            //版本不一致，更新本地缓存
            confCenterClientService.setConf(rp);
        }
    }
}
