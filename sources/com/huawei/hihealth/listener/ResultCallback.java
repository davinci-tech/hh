package com.huawei.hihealth.listener;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes.dex */
public interface ResultCallback {
    public static final String TAG = "ResultCallback";

    void onResult(int i, Object obj);

    default void onResultHandler(int i, Object obj) {
        try {
            onResult(i, obj);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33, HiHealthError.e(33));
        }
    }
}
