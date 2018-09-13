package apimodel;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
public class MoGetConfRp extends MoRp {

    /**
     * 配置信息版本 用来获取指定版本配置
     */
    private String confVersion;
    /**
     * 配置信息
     */
    private Map<String, Object> confs;

    /**
     * 配置版本号
     */
    private long confLastModified;

    public long getConfLastModified() {
        return confLastModified;
    }

    public void setConfLastModified(long confLastModified) {
        this.confLastModified = confLastModified;
    }

    public String getConfVersion() {
        return confVersion;
    }

    public void setConfVersion(String confVersion) {
        this.confVersion = confVersion;
    }

    public Map<String, Object> getConfs() {
        return confs;
    }

    public void setConfs(Map<String, Object> confs) {
        this.confs = confs;
    }

}
