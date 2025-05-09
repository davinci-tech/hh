package com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class HwStaticsRequestData {

    @SerializedName("dataType")
    private String mDataType;

    @SerializedName("requestDataId")
    private int mRequestDataId;

    @SerializedName("requestDataString")
    private String mRequestDataString;

    public String getDataType() {
        return this.mDataType;
    }

    public void setDataType(String str) {
        this.mDataType = str;
    }

    public String getRequestDataString() {
        return this.mRequestDataString;
    }

    public void setRequestDataString(String str) {
        this.mRequestDataString = str;
    }

    public int getRequestDataId() {
        return this.mRequestDataId;
    }

    public void setRequestDataId(int i) {
        this.mRequestDataId = i;
    }
}
