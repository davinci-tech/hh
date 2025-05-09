package com.huawei.pluginachievement.manager.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class GiftRecord {

    @SerializedName("awardName")
    private String mAwardName;

    @SerializedName("nickName")
    private String mNickName;

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public String getAwardName() {
        return this.mAwardName;
    }

    public void setAwardName(String str) {
        this.mAwardName = str;
    }
}
