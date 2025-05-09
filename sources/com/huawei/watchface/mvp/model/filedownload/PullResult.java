package com.huawei.watchface.mvp.model.filedownload;

/* loaded from: classes7.dex */
public class PullResult {
    public static final int DOWNLOAD_URL_EMPTY = -13;
    public static final int FILE_CREATE_EXCEPTION = -14;
    public static final int FILE_WRITE_EXCEPTION = -15;
    public static final int PULL_CANCEL = -10;
    public static final int PULL_DECOMPRESS_FAIL = -6;
    public static final int PULL_DECRYPT_FAIL = -12;
    public static final int PULL_DEFAULT = -100;
    public static final int PULL_FAIL = 0;
    public static final int PULL_HASH_FAIL = -11;
    public static final int PULL_INSTALLING = 6;
    public static final int PULL_IN_PAUSE = 3;
    public static final int PULL_IN_RESTART = 4;
    public static final int PULL_NETWORK_ERROR = -4;
    public static final int PULL_NO_SUCH_FILE = -5;
    public static final int PULL_NO_SUCH_TASK = -7;
    public static final int PULL_STATUS_IN_PROGRESS = 7;
    public static final int PULL_STORAGE_NOT_ENOUGH = -9;
    public static final int PULL_SUCCEEDED = 1;
    public static final int PULL_TASK_IS_PENDING = -8;
    public static final int PULL_TIMEOUT = -3;
    public static final int PULL_UNEXPECTED_INTERRUPTION = -1;
    public static final int PULL_VERIFICATION_FAILED = -2;
    public static final int PULL_WAIT_INSTALL = 5;
    public static final int REQUEST_URL_EXCEPTION = -106;
    private String descInfo;
    private int installationType;
    private String mFileUrlJson;
    private String mPathName;
    private int mPulledSize;
    private int mResultCode;
    private String mResultValue;
    private int mStatus;
    private int mTotalSize;
    private String mUuid;

    public String getDescInfo() {
        return this.descInfo;
    }

    public void setDescInfo(String str) {
        this.descInfo = str;
    }

    public void addDescInfo(String str) {
        if (this.descInfo == null) {
            setDescInfo(str);
        }
        this.descInfo += ";\n" + str;
    }

    public int getInstallationType() {
        return this.installationType;
    }

    public void setInstallationType(int i) {
        this.installationType = i;
    }

    public String fetchUuid() {
        return this.mUuid;
    }

    public void configUuid(String str) {
        this.mUuid = str;
    }

    public String fetchFileUrlJson() {
        return this.mFileUrlJson;
    }

    public void configFileUrlJson(String str) {
        this.mFileUrlJson = str;
    }

    public int fetchStatus() {
        return this.mStatus;
    }

    public void configStatus(int i) {
        this.mStatus = i;
    }

    public int fetchResultCode() {
        return this.mResultCode;
    }

    public void configResultCode(int i) {
        this.mResultCode = i;
    }

    public String fetchResultValue() {
        return this.mResultValue;
    }

    public void configTotalSize(int i) {
        this.mTotalSize = i;
    }

    public int fetchTotalSize() {
        return this.mTotalSize;
    }

    public void configPulledSize(int i) {
        this.mPulledSize = i;
    }

    public int fetchPulledSize() {
        return this.mPulledSize;
    }

    public void configResultValue(String str) {
        this.mResultValue = str;
    }

    public String fetchPathName() {
        return this.mPathName;
    }

    public void configPathName(String str) {
        this.mPathName = str;
    }

    public String toString() {
        return "PullResult{status:'" + this.mStatus + "'resultCode:'" + this.mResultCode + "'resultValue:'" + this.mResultValue + "'}";
    }
}
