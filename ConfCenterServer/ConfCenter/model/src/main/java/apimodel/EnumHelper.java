package apimodel;

/**
 * Created by Administrator on 2018/9/3.
 */
public class EnumHelper {

    /**
     * 接口统一返回状态
     */
    public enum EmRpStatus {
        失败(-1),
        成功(0);

        private int status;

        EmRpStatus(int status) {
            this.status = status;
        }

        public int getVal() {
            return status;
        }

    }

    /**
     * 所有redis的channel名
     */
    public enum EmChannel {
        客户端全部刷新配置channel("allConfRefreshChannel", "allRefresh");

        private String key;
        private String val;

        EmChannel(String key, String val) {
            this.key = key;
            this.val = val;
        }

        public String getKey() {
            return this.key;
        }

        public String getVal() {
            return this.val;
        }
    }
}
