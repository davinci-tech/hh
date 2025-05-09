package com.huawei.hihealthkit.data.store;

import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public interface HiSportDataCallback {
    public static final String TAG = "HiSportDataCallback";

    void onDataChanged(int i, Bundle bundle);

    void onResult(int i);

    default void onResultHandler(int i) {
        try {
            onResult(i);
        } catch (Exception unused) {
            Log.e(TAG, "onResultHandler Exception");
            onResult(33);
        }
    }

    default void onDataChangedHandler(int i, Bundle bundle) {
        try {
            onDataChanged(i, bundle);
        } catch (Exception unused) {
            Log.e(TAG, "onDataChangedHandler Exception");
            onResult(33);
        }
    }
}
