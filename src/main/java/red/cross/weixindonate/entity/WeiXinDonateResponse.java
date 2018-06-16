package red.cross.weixindonate.entity;

public class WeiXinDonateResponse<T> {
    private String errorMsg;
    private T data;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
