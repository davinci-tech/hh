package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class ParamValidFailDetail {

    @SerializedName("dataId")
    private long dataId;

    @SerializedName("resultIndex")
    private int resultIndex;

    @SerializedName("subResultCode")
    private int subResultCode;

    @SerializedName("subResultMsg")
    private String subResultMsg;

    public long getDataId() {
        return this.dataId;
    }

    public void setDataId(long j) {
        this.dataId = j;
    }

    public int getSubResultCode() {
        return this.subResultCode;
    }

    public void setSubResultCode(int i) {
        this.subResultCode = i;
    }

    public String getSubResultMsg() {
        return this.subResultMsg;
    }

    public void setSubResultMsg(String str) {
        this.subResultMsg = str;
    }

    public int getResultIndex() {
        return this.resultIndex;
    }

    public void setResultIndex(int i) {
        this.resultIndex = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ParamValidFailDetail{dataId = ");
        stringBuffer.append(this.dataId);
        stringBuffer.append(", subResultCode = ").append(this.subResultCode);
        stringBuffer.append(", subResultMsg = ").append(this.subResultMsg);
        stringBuffer.append(", resultIndex = ").append(this.resultIndex);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
