package red.cross.weixindonate.entity;

import java.io.Serializable;

public class ResultState {

    //@SerializedName("errcode")
    private int errcode; // 状态

    //@SerializedName("errmsg")
    private String errmsg; //信息

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
