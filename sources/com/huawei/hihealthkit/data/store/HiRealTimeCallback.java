package com.huawei.hihealthkit.data.store;

import android.os.Bundle;
import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes.dex */
public interface HiRealTimeCallback {
    public static final String TAG = "HiRealTimeCallback";

    void onDataChanged(Bundle bundle);

    void onResult(int i, String str);

    default void onResultHandler(int i, String str) {
        try {
            onResult(i, str);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33, HiHealthError.e(33));
        }
    }

    default void onDataChangedHandler(Bundle bundle) {
        try {
            onDataChanged(bundle);
        } catch (Exception unused) {
            Log.e(TAG, "onDataChangedHandler Exception");
            onResult(33, HiHealthError.e(33));
        }
    }
}
