package com.huawei.hms.iap.util;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public class c {
    public static boolean a(Intent intent, String str, boolean z) {
        try {
            return intent.getBooleanExtra(str, z);
        } catch (Throwable th) {
            HMSLog.e("IntentUtils", "safeGetBooleanExtra failed, " + th.getMessage());
            return z;
        }
    }

    public static String a(Intent intent, String str, String str2) {
        String a2 = a(intent, str);
        return TextUtils.isEmpty(a2) ? str2 : a2;
    }

    public static String a(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable th) {
            HMSLog.e("IntentUtils", "safeGetStringExtra failed, " + th.getMessage());
            return "";
        }
    }

    public static int a(Intent intent, String str, int i) {
        try {
            return intent.getIntExtra(str, i);
        } catch (Throwable th) {
            HMSLog.e("IntentUtils", "safeGetIntExtra failed, " + th.getMessage());
            return i;
        }
    }
}
