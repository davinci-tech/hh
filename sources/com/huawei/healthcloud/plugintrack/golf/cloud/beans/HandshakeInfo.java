package com.huawei.healthcloud.plugintrack.golf.cloud.beans;

import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseMapInfo;

/* loaded from: classes8.dex */
public class HandshakeInfo {
    private GolfCourseMapInfo mCourseMapInfo;
    private int mMessageId;

    public GolfCourseMapInfo getCourseMapInfo() {
        return this.mCourseMapInfo;
    }

    public void setCourseMapInfo(GolfCourseMapInfo golfCourseMapInfo) {
        this.mCourseMapInfo = golfCourseMapInfo;
    }

    public int getMessageId() {
        return this.mMessageId;
    }

    public void setMessageId(int i) {
        this.mMessageId = i;
    }
}
