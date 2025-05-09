package com.huawei.hihealth.listener;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes.dex */
public interface IuniversalCallback {
    public static final String TAG = "IuniversalCallback";

    void onResult(int i, Object obj, String str);

    default void onResultHandler(int i, Object obj, String str) {
        try {
            onResult(i, obj, str);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33, null, HiHealthError.e(33));
        }
    }
}
