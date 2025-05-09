package com.huawei.login.ui.login;

import android.content.Context;

/* loaded from: classes.dex */
public interface MainLoginCallBack {
    public static final int MSG_HMS_VERSION_ERROR = 8003;
    public static final int MSG_HWID_ACCOUNT_NOT_LOGIN = 8004;
    public static final int MSG_HWID_IS_LOW_VERSION = 8009;
    public static final int MSG_HWID_STOPED = 8006;
    public static final int MSG_NO_NETWORK = 8005;
    public static final int MSG_SHOW_HMS_DIALOG = 8008;
    public static final int MSG_SHOW_MIGRATE_UNAUTO = 8000;
    public static final int MSG_START_APK_SERVICE_ERROR = 8007;
    public static final int MSG_USE_HWID_ACCOUNT = 8002;
    public static final int MSG_USE_WEAR_ACCOUNT = 8001;
    public static final int PHASE_AFTER_BROWSE_CALLBACK = 9006;
    public static final int PHASE_BEFORE_BROWSE_CALLBACK = 9005;
    public static final int PHASE_HWID_LOGIN_SUCCESS = 9004;
    public static final int PHASE_LOGIN_AUTH = 9003;
    public static final int PHASE_LOGIN_LEAVE = 9002;
    public static final int PHASE_NOCLOUD_NOLOGIN = 9007;
    public static final int PHASE_RETRIGGER_LOGIN = 9000;
    public static final int PHASE_SELECT_AREA = 9001;

    void browseCallback(boolean z);

    void handlerMessage(int i);

    void handlerPhase(int i);

    boolean isHmsUsableVersion(Context context);

    boolean isNoAgreePrivacy(int i);
}
