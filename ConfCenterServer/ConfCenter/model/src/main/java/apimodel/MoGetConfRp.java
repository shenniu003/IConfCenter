package apimodel;

import java.util.Map;

/**
 * Created by Administrator on 2018/8/31.
 */
public class MoGetConfRp extends MoRp {
    public Map<String, Object> getConfs() {
        return confs;
    }

    public void setConfs(Map<String, Object> confs) {
        this.confs = confs;
    }

    private Map<String, Object> confs;

}
