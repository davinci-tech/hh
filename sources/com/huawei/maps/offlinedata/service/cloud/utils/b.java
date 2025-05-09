package com.huawei.maps.offlinedata.service.cloud.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.common.util.AGCUtils;
import com.huawei.hms.mlsdk.common.AgConnectInfo;

/* loaded from: classes5.dex */
public class b {
    public static String a(Context context) {
        String a2 = a.a(context, AgConnectInfo.AgConnectKey.APPLICATION_ID);
        return TextUtils.isEmpty(a2) ? AGCUtils.getAppId(context) : a2;
    }

    public static String a() {
        String str = Build.MODEL;
        if (str == null || str.trim().isEmpty()) {
            str = "unknownModel";
        }
        return str.length() > 20 ? str.substring(0, 20) : str;
    }
}
