package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class bn {
    private static Object b(Context context, String str, String str2) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.get(str2);
        } catch (Throwable th) {
            ho.c("MetaDataUtils", "getMetaData %d err: %s", str2, th.getClass().getSimpleName());
            return null;
        }
    }

    public static Integer a(Context context, String str, String str2) {
        try {
            Object b = b(context, str, str2);
            if (b != null) {
                return cz.h(b.toString());
            }
            return null;
        } catch (Throwable th) {
            ho.c("MetaDataUtils", "getIntegerMetaData %s err: %s", str2, th.getClass().getSimpleName());
            return null;
        }
    }
}
