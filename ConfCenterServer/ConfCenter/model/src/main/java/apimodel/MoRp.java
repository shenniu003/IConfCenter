package apimodel;

/**
 * Created by Administrator on 2018/8/31.
 */
public class MoRp {

    public MoRp() {
        this.setStatus(EnumHelper.EmRpStatus.失败.getVal());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 接口数据相应状态  -1：失败  0：成功
     */
    private int status;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
