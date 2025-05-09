package com.huawei.hms.iap.util;

import android.content.Intent;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: classes9.dex */
public class IapClientHelper {
    public static String parseRespMessageFromIntent(Intent intent) {
        return a(intent, "errMsg", "");
    }

    public static int parseRespCodeFromIntent(Intent intent) {
        return a(intent, "returnCode", -1);
    }

    public static String parseCountryFromIntent(Intent intent) {
        return a(intent, "country", (String) null);
    }

    public static String parseCarrierIdFromIntent(Intent intent) {
        return a(intent, CommonConstant.KEY_CARRIER_ID, (String) null);
    }

    @Deprecated
    public static int parseAccountFlagFromIntent(Intent intent) {
        return a(intent, CommonConstant.KEY_ACCOUNT_FLAG, 0);
    }

    private static String a(Intent intent, String str, String str2) {
        if (intent != null) {
            return c.a(intent, str, str2);
        }
        HMSLog.i("IapClientHelper", "parseStringDataFromIntent: intent is null.");
        return str2;
    }

    private static int a(Intent intent, String str, int i) {
        if (intent != null) {
            return c.a(intent, str, i);
        }
        HMSLog.e("IapClientHelper", "parseIntDataFromIntent: intent is null.");
        return -1;
    }
}
