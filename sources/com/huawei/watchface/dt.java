package com.huawei.watchface;

import android.content.SharedPreferences;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class dt {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10996a = "dt";

    public static void a(boolean z) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "DEFAULT_WATCH_FACE_OPERATION_RESULT_PREF");
        if (a2 == null) {
            HwLog.i(f10996a, "sharedPreferences is null");
            return;
        }
        a2.edit().putBoolean("DEFAULT_WATCH_FACE_OPERATION_RESULT_DATA_PREF", z).apply();
        HwLog.i(f10996a, "saveDefaultWatchFaceOperationResult: " + z);
    }

    public static boolean a() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "DEFAULT_WATCH_FACE_OPERATION_RESULT_PREF");
        if (a2 == null) {
            HwLog.i(f10996a, "sharedPreferences is null");
            return false;
        }
        HwLog.d(f10996a, "isDefaultWatchFaceOperationResultSuccess() completed.");
        return a2.getBoolean("DEFAULT_WATCH_FACE_OPERATION_RESULT_DATA_PREF", false);
    }
}
