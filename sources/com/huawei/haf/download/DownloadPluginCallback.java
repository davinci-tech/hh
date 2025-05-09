package com.huawei.haf.download;

/* loaded from: classes.dex */
public interface DownloadPluginCallback {
    public static final int REASON_CANCEL = 1;
    public static final int REASON_FAIL = 3;
    public static final int REASON_NOT_CONFIG = 4;
    public static final int REASON_NO_AUTHORIZE = 7;
    public static final int REASON_NO_NETWORK = 6;
    public static final int REASON_PARTIAL_FAIL = 5;
    public static final int REASON_SUCCESS = 0;
    public static final int REASON_TIMEOUT = 2;
    public static final int REASON_TIMEOUT_CONTINUE = 8;
    public static final int RESULT_FAIL = -1;
    public static final int RESULT_SUCCESS = 0;

    boolean onDownloadPluginResult(int i, String str, int i2);

    void onDownloadResult(int i, int i2);

    void showDownloadProgress(int i, int i2, int i3);

    void startDownloadProgress(int i);
}
