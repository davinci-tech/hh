package com.huawei.hihealthkit.data.store;

import android.util.Log;

/* loaded from: classes.dex */
public interface HiRealTimeListener {
    public static final String TAG = "HiRealTimeListener";

    void onChange(int i, String str);

    void onResult(int i);

    default void onResultHandler(int i) {
        try {
            onResult(i);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33);
        }
    }

    default void onChangeHandler(int i, String str) {
        try {
            onChange(i, str);
        } catch (Exception unused) {
            Log.e(TAG, "onChangeHandler Exception");
            onResult(33);
        }
    }
}
