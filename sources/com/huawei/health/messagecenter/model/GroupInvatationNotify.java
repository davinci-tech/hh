package com.huawei.health.messagecenter.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes8.dex */
public class GroupInvatationNotify {

    @SerializedName("expireDate")
    private long mExpire;

    @SerializedName("grpID")
    private long mGroupId;

    @SerializedName("grpName")
    private String mGroupName;

    @SerializedName("nickName")
    private String mNickname;

    @SerializedName("pushUID")
    private long mPushId;

    @SerializedName("type")
    private int mType;

    @SerializedName(HwPayConstant.KEY_USER_ID)
    private long mUserId;

    public int getType() {
        return this.mType;
    }

    public long getExpire() {
        return this.mExpire;
    }

    public long getGroupId() {
        return this.mGroupId;
    }

    public long getPushId() {
        return this.mPushId;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public String getNickname() {
        return this.mNickname;
    }

    public void setExpire(long j) {
        this.mExpire = j;
    }

    public void setGroupId(long j) {
        this.mGroupId = j;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public void setNickname(String str) {
        this.mNickname = str;
    }

    public void setPushId(long j) {
        this.mPushId = j;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }
}
