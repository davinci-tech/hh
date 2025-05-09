package com.huawei.hms.support.hwid.common.e;

import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes4.dex */
public class g {
    public static void a(String str, String str2, boolean z) {
        if (z) {
            HMSLog.i(str, "Account_Advanced_log[6.11.0.300]" + str2);
        }
    }

    public static void a(String str, String str2) {
        a(str, str2, true);
    }

    public static void b(String str, String str2, boolean z) {
        if (z) {
            HMSLog.w(str, "Account_Advanced_log[6.11.0.300]" + str2);
        }
    }

    public static void c(String str, String str2, boolean z) {
        if (z) {
            HMSLog.e(str, "Account_Advanced_log[6.11.0.300]" + str2);
        }
    }

    public static void b(String str, String str2) {
        c(str, str2, true);
    }
}
