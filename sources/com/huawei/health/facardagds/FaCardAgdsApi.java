package com.huawei.health.facardagds;

import android.content.Intent;

/* loaded from: classes3.dex */
public interface FaCardAgdsApi {
    public static final int CONNECTION_FAILED = 1001;
    public static final int CONNECTION_FAILED_REQUEST_CODE = 101;
    public static final int CONNECTION_SUSPENDED = 1000;
    public static final String DEFAULT = "default";
    public static final String DIET_FA_CARD = "dietfacard";
    public static final int GET_LOAD_RESULT_CODE_FAIL = -1;
    public static final int LOAD_RESULT_CODE_FAIL_SERVER_OFF_SHELVE = 3;
    public static final int OPEN_FA_DETAIL_REQUEST_FAIL = 1002;
    public static final int PROTOCOL_AND_VERSION_UPDATE_REQUEST_CODE = 100;
    public static final String SLEEP_FA_CARD = "sleepfacard";
    public static final String SPORT_FA_CARD = "sportfacard";

    public interface OpenAgdsResultCallback {
        void onResponse(int i);
    }

    void addToDeskTopBi(Intent intent, int i);

    void close();

    int faQuickServicesBiEvent(int i, int i2);

    int faQuickServicesBiEvent(int i, int i2, int i3);

    int faQuickServicesBiEvent(int i, int i2, int i3, String str);

    int getLoadResultCode(Intent intent);

    int getResultcodeAgreeProtocol();

    int getResultcodeNotAgreeProtocol();

    void open(int i, String str, OpenAgdsResultCallback openAgdsResultCallback);
}
