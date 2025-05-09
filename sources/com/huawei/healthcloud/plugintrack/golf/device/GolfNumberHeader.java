package com.huawei.healthcloud.plugintrack.golf.device;

import com.huawei.healthcloud.plugintrack.golf.Utils;

/* loaded from: classes4.dex */
public class GolfNumberHeader {
    private static final int NUM_BYTES_LENGTH = 4;
    private int mCourseNum;

    public int getCourseNum() {
        return this.mCourseNum;
    }

    public void setCourseNum(int i) {
        this.mCourseNum = i;
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[4];
        System.arraycopy(Utils.int2Bytes(this.mCourseNum), 0, bArr, 0, 4);
        return bArr;
    }
}
