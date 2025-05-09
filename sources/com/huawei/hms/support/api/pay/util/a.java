package com.huawei.hms.support.api.pay.util;

import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public class a {
    public static String a(Intent intent, String str) {
        try {
            return intent.getStringExtra(str);
        } catch (Throwable th) {
            HMSLog.e("IntentUtils", "safeGetStringExtra failed, " + th.getMessage());
            return "";
        }
    }

    public static long a(Intent intent, String str, long j) {
        try {
            return intent.getLongExtra(str, j);
        } catch (Throwable th) {
            HMSLog.e("IntentUtils", "safeGetLongExtra failed, " + th.getMessage());
            return j;
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
