package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class PaceIndexStruct {
    private int paceIndex;
    private int recordId;

    public int getRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.recordId))).intValue();
    }

    public void setRecordId(int i) {
        this.recordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPaceIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.paceIndex))).intValue();
    }

    public void setPaceIndex(int i) {
        this.paceIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
