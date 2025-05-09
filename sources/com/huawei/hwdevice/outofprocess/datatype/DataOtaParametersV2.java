package com.huawei.hwdevice.outofprocess.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class DataOtaParametersV2 {
    private int appWaitTimeout;
    private int deviceRestartTimeout;
    private int otaUnitSize;

    @SerializedName("ackEnable")
    private boolean isAckEnable = false;
    private long otaInterval = 0;

    @SerializedName("offsetEnable")
    private boolean mOffsetEnable = false;

    public long getOtaInterval() {
        return ((Long) jdy.d(Long.valueOf(this.otaInterval))).longValue();
    }

    public void setOtaInterval(long j) {
        this.otaInterval = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public void setAckEnable(long j) {
        if (j == 1) {
            this.isAckEnable = ((Boolean) jdy.d(true)).booleanValue();
        }
    }

    public boolean getAckEnable() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.isAckEnable))).booleanValue();
    }

    public int getDeviceRestartTimeout() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceRestartTimeout))).intValue();
    }

    public void setDeviceRestartTimeout(int i) {
        this.deviceRestartTimeout = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getAppWaitTimeout() {
        return ((Integer) jdy.d(Integer.valueOf(this.appWaitTimeout))).intValue();
    }

    public void setAppWaitTimeout(int i) {
        this.appWaitTimeout = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getOtaUnitSize() {
        return ((Integer) jdy.d(Integer.valueOf(this.otaUnitSize))).intValue();
    }

    public void setOtaUnitSize(int i) {
        this.otaUnitSize = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public boolean getOffsetEnable() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.mOffsetEnable))).booleanValue();
    }

    public void setOffsetEnable(boolean z) {
        this.mOffsetEnable = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }
}
