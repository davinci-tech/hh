package com.huawei.hms.iapfull;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes4.dex */
public class o0 {
    public static String a(Context context, String str, String str2) {
        y0.b("convertReturnCodeToString", "errorCode=" + str);
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str3 = "hwpay_returnCode" + str + "des";
        int identifier = TextUtils.isEmpty(str3) ? 0 : context.getResources().getIdentifier(str3, "string", context.getPackageName());
        return identifier > 0 ? context.getString(identifier) : str2;
    }
}
