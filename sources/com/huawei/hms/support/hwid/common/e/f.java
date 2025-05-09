package com.huawei.hms.support.hwid.common.e;

import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;

/* loaded from: classes9.dex */
public class f {
    public static void a(String str, String str2, String str3) {
        a(str, str2, 0, str3);
    }

    public static void a(String str, String str2, int i, String str3) {
        g.a("HiAnalyticsUtil", "report:" + str2 + "|" + str + "|" + str3 + "|HiAnalyticsStatus:" + b.a(i) + "|" + i + "|60900100", false);
        HiAnalyticsClient.reportExit(com.huawei.hms.support.hwid.common.a.a().b(), str2, str, str3, b.a(i), i, AuthInternalPickerConstant.HMS_SDK_VERSION);
    }
}
