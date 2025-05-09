package com.huawei.hwcoresleepmgr.datatype;

import android.content.ComponentName;

/* loaded from: classes5.dex */
public class NotificationData {
    private ComponentName mComponentName;
    private String mDescription;
    private String mDetailUri;
    private long mEndTime;
    private String mFlag;
    private String mMsgId;
    private long mStartTime;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getFlag() {
        return this.mFlag;
    }

    public void setFlag(String str) {
        this.mFlag = str;
    }

    public String getDetailUri() {
        return this.mDetailUri;
    }

    public void setDetailUri(String str) {
        this.mDetailUri = str;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public void setComponentName(ComponentName componentName) {
        this.mComponentName = componentName;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public String getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(String str) {
        this.mMsgId = str;
    }

    public String toString() {
        return "NotificationData{mTitle='" + this.mTitle + "', mDescription='" + this.mDescription + "', mFlag='" + this.mFlag + "', mDetailUri='****', mComponentName=****, mMsgId=" + this.mMsgId + ", mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + '}';
    }
}
