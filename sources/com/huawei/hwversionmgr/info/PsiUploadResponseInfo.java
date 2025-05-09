package com.huawei.hwversionmgr.info;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class PsiUploadResponseInfo implements Serializable {

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

    public String toString() {
        return "PsiUploadResponseInfo{resultCode='" + this.resultCode + "', resultMsg='" + this.resultMsg + "'}";
    }
}
