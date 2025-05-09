package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class AssetMessageTemple {

    @SerializedName("assertDesc")
    private String assertDesc;

    @SerializedName("assertId")
    private String assertId;

    @SerializedName("assertName")
    private String assertName;

    @SerializedName("operateType")
    private int operateType;

    public String getAssertDesc() {
        return this.assertDesc;
    }

    public void setAssertDesc(String str) {
        this.assertDesc = str;
    }

    public String getAssertName() {
        return this.assertName;
    }

    public void setAssertName(String str) {
        this.assertName = str;
    }

    public String getAssertId() {
        return this.assertId;
    }

    public void setAssertId(String str) {
        this.assertId = str;
    }

    public int getOperateType() {
        return this.operateType;
    }

    public void setOperateType(int i) {
        this.operateType = i;
    }
}
