package com.huawei.healthcloud.plugintrack.golf.bean;

import com.huawei.healthcloud.plugintrack.golf.Utils;

/* loaded from: classes4.dex */
public class GolfCourseStateInfo implements GolfBeanToBytes {
    private static final int DEFAULT_INT_LENGTH = 4;
    public static final int GOLF_COURSE_NOT_UPDATE = 0;
    public static final int GOLF_COURSE_UPDATE = 1;
    private int mCourseId;
    private int mRspState;
    private int mState;

    public int getCourseId() {
        return this.mCourseId;
    }

    public void setCourseId(int i) {
        this.mCourseId = i;
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i) {
        this.mState = i;
    }

    public void setRspState(int i) {
        this.mRspState = i;
    }

    public int getRspState() {
        return this.mRspState;
    }

    public String toString() {
        return "GolfCourseStateInfo{courseId=" + this.mCourseId + ", state=" + this.mState + '}';
    }

    @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfBeanToBytes
    public byte[] toBytes() {
        byte[] bArr = new byte[12];
        System.arraycopy(Utils.int2Bytes(this.mCourseId), 0, bArr, 0, 4);
        System.arraycopy(Utils.int2Bytes(this.mState), 0, bArr, 4, 4);
        return bArr;
    }
}
