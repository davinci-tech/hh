package com.huawei.watchface.utils.callback;

/* loaded from: classes7.dex */
public interface IBaseResponseCallback {
    public static final int ERROR_CODE_BLUE_DATA_ERROR = 2;
    public static final int ERROR_CODE_BLUE_NOT_CONNECTED = 1;

    default void onError(int i) {
    }

    void onResponse(int i, Object obj);
}
