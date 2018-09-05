package apimodel;

/**
 * Created by Administrator on 2018/8/31.
 */
public class MoRefreshAllConfRq extends MoRq {
    /**
     * 配置信息版本 用来获取指定版本配置
     */
    private String confVersion;

    public String getConfVersion() {
        return confVersion;
    }

    public void setConfVersion(String confVersion) {
        this.confVersion = confVersion;
    }
}
