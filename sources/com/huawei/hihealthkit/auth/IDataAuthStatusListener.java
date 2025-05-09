package com.huawei.hihealthkit.auth;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes8.dex */
public interface IDataAuthStatusListener {
    public static final String TAG = "IDataAuthStatusListener";

    void onResult(int i, String str, int[] iArr, int[] iArr2);

    default void onResultHandler(int i, String str, int[] iArr, int[] iArr2) {
        try {
            onResult(i, str, iArr, iArr2);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33, HiHealthError.e(33), null, null);
        }
    }
}
