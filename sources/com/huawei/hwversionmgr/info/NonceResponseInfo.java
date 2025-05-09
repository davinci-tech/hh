package com.huawei.hwversionmgr.info;

import com.google.gson.annotations.SerializedName;
import com.tencent.connect.common.Constants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class NonceResponseInfo implements Serializable {

    @SerializedName(Constants.NONCE)
    private String nonce;

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }

    public String getNonce() {
        return this.nonce;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public String toString() {
        return "NonceResponseInfo{resultCode='" + this.resultCode + "', resultMsg='" + this.resultMsg + "', nonce='" + this.nonce + "'}";
    }
}
