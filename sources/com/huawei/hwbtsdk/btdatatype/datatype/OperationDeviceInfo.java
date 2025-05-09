package com.huawei.hwbtsdk.btdatatype.datatype;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.bmb;

/* loaded from: classes5.dex */
public class OperationDeviceInfo extends DeviceInfo {
    private long mEndConnectTime;
    private int mErrorCode;
    private String mErrorMeathod;
    private long mStartConnectTime;

    public long getStartConnectTime() {
        return ((Long) bmb.d(Long.valueOf(this.mStartConnectTime))).longValue();
    }

    public void setStartConnectTime(long j) {
        this.mStartConnectTime = ((Long) bmb.d(Long.valueOf(j))).longValue();
    }

    public long getEndConnectTime() {
        return ((Long) bmb.d(Long.valueOf(this.mEndConnectTime))).longValue();
    }

    public void setEndConnectTime(long j) {
        this.mEndConnectTime = ((Long) bmb.d(Long.valueOf(j))).longValue();
    }

    public int getErrorCode() {
        return ((Integer) bmb.d(Integer.valueOf(this.mErrorCode))).intValue();
    }

    public void setErrorCode(int i) {
        this.mErrorCode = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getErrorMeathod() {
        return (String) bmb.d(this.mErrorMeathod);
    }

    public void setErrorMeathod(String str) {
        this.mErrorMeathod = (String) bmb.d(str);
    }
}
