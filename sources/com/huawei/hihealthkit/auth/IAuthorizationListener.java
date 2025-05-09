package com.huawei.hihealthkit.auth;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;

/* loaded from: classes4.dex */
public interface IAuthorizationListener {
    public static final String TAG = "IAuthorizationListener";

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
