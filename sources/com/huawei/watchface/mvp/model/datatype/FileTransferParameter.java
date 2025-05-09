package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public class FileTransferParameter {
    private static final int WAIT_TIME_DEFAULT = 30;
    private String mFileProtocolVersion = "";
    private int mAppWaitTime = 30;
    private boolean mIsBitmapEnable = false;
    private int mTransferUnitSize = 0;
    private int mMaxApplyDataSize = 0;
    private int mInterval = 0;
    private int mReceivedFileSize = 0;
    private int mFileId = 0;
    private int mPackageNumberMax = 0;
    private boolean mIsTransferNotEncrypt = false;

    public String getFileProtocolVersion() {
        return this.mFileProtocolVersion;
    }

    public void setFileProtocolVersion(String str) {
        this.mFileProtocolVersion = str;
    }

    public int getAppWaitTime() {
        return this.mAppWaitTime;
    }

    public void setAppWaitTime(int i) {
        this.mAppWaitTime = i;
    }

    public boolean isBitmapEnable() {
        return this.mIsBitmapEnable;
    }

    public void setBitmapEnable(boolean z) {
        this.mIsBitmapEnable = z;
    }

    public int getTransferUnitSize() {
        return this.mTransferUnitSize;
    }

    public void setTransferUnitSize(int i) {
        this.mTransferUnitSize = i;
    }

    public int getMaxApplyDataSize() {
        return this.mMaxApplyDataSize;
    }

    public void setMaxApplyDataSize(int i) {
        this.mMaxApplyDataSize = i;
    }

    public int getInterval() {
        return this.mInterval;
    }

    public void setInterval(int i) {
        this.mInterval = i;
    }

    public int getReceivedFileSize() {
        return this.mReceivedFileSize;
    }

    public void setReceivedFileSize(int i) {
        this.mReceivedFileSize = i;
    }

    public int getFileId() {
        return this.mFileId;
    }

    public void setFileId(int i) {
        this.mFileId = i;
    }

    public boolean isTransferNotEncrypt() {
        return this.mIsTransferNotEncrypt;
    }

    public void setTransferNotEncrypt(boolean z) {
        this.mIsTransferNotEncrypt = z;
    }

    public int getPackageNumberMax() {
        return this.mPackageNumberMax;
    }

    public void setPackageNumberMax(int i) {
        this.mPackageNumberMax = i;
    }
}
