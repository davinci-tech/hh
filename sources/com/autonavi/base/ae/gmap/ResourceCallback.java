package com.autonavi.base.ae.gmap;

/* loaded from: classes8.dex */
public class ResourceCallback {
    private long instance;
    private int requestId;

    private static native void nativeCallCancel(int i);

    private static native void nativeCallFailed(long j, int i, String str);

    private static native void nativeCallSuccess(long j, int i, AMapAppResourceItem aMapAppResourceItem);

    public ResourceCallback() {
        this.instance = 0L;
        this.requestId = 0;
    }

    public ResourceCallback(long j, int i) {
        this.instance = j;
        this.requestId = i;
    }

    public long getInstance() {
        return this.instance;
    }

    public void setInstance(long j) {
        this.instance = j;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int i) {
        this.requestId = i;
    }

    public void callSuccess(AMapAppResourceItem aMapAppResourceItem) {
        nativeCallSuccess(this.instance, this.requestId, aMapAppResourceItem);
    }

    public void callFailed(String str) {
        nativeCallFailed(this.instance, this.requestId, str);
    }

    public void callCancel() {
        nativeCallCancel(this.requestId);
    }
}
