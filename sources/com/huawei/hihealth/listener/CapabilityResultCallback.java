package com.huawei.hihealth.listener;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes.dex */
public interface CapabilityResultCallback {
    public static final String TAG = "CapabilityCallback";

    void onResult(int i, String str);

    default void onResultHandler(int i, String str) {
        try {
            onResult(i, str);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33, HiHealthError.e(33));
        }
    }
}
