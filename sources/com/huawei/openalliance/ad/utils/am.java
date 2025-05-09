package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.SystemProperties;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.tb;
import com.huawei.openalliance.ad.tc;
import com.huawei.openalliance.ad.tf;
import java.util.List;

/* loaded from: classes5.dex */
public class am {
    private static boolean a(int i) {
        return (i & 1048576) != 0;
    }

    public static boolean b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            boolean a2 = a(new tc().a(i.d(context.getApplicationContext(), str), context));
            ho.b("HarmonyUtils", "isHarmonyApp: %s", Boolean.valueOf(a2));
            return a2;
        } catch (Throwable unused) {
            ho.c("HarmonyUtils", "isHarmonyApp exception");
            return false;
        }
    }

    public static Integer b(Context context) {
        String a2 = dd.a(SystemProperties.HW_SC_BUILD_OS_API_VERSION);
        if (ho.a()) {
            ho.a("HarmonyUtils", "hmSdkInt: %s", a2);
        }
        return cz.h(a2);
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            new tb().a(context, str, str2);
            return true;
        } catch (Throwable unused) {
            ho.c("HarmonyUtils", "handle harmony intent url fail");
            return false;
        }
    }

    public static boolean a(Context context, String str, ContentRecord contentRecord) {
        boolean z;
        ho.b("HarmonyUtils", "open harmony app main page");
        tf.a aVar = new tf.a();
        aVar.c(str).a(contentRecord);
        boolean z2 = false;
        try {
            z = i.a(context, str, aVar.a());
        } catch (Throwable unused) {
            ho.c("HarmonyUtils", "open app main page fail");
            z = false;
        }
        if (z) {
            return true;
        }
        try {
            tb tbVar = new tb();
            List<Intent> a2 = tbVar.a(str);
            if (!bg.a(a2)) {
                Intent intent = a2.get(0);
                intent.addFlags(268435456);
                tbVar.a(context, intent);
                z2 = true;
            }
            return z2;
        } catch (Throwable unused2) {
            ho.c("HarmonyUtils", "open harmony app main page fail");
            return z;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            if (i.a(context.getApplicationContext(), str)) {
                return true;
            }
            return !bg.a(new tb().a(str));
        } catch (Throwable unused) {
            ho.c("HarmonyUtils", "check app installed fail");
            return false;
        }
    }

    public static boolean a(Context context, AppInfo appInfo, String str, ContentRecord contentRecord) {
        ho.b("HarmonyUtils", "openHarmonyApp intent");
        boolean z = false;
        if (appInfo == null) {
            return false;
        }
        if (!TextUtils.isEmpty(appInfo.y())) {
            try {
                String y = appInfo.y();
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(str, y));
                intent.addFlags(268435456);
                new tb().a(context, intent);
                z = true;
            } catch (Throwable unused) {
                ho.c("HarmonyUtils", "handle harmony intent url fail");
            }
        }
        if (z || TextUtils.isEmpty(appInfo.getIntentUri())) {
            return z;
        }
        tf.a aVar = new tf.a();
        aVar.a(appInfo).a(contentRecord).c(str);
        return i.a(context, str, appInfo.getIntentUri(), aVar.a());
    }

    public static boolean a(Context context) {
        String a2 = dd.a(SystemProperties.HW_SC_BUILD_OS_ENABLE);
        if (ho.a()) {
            ho.a("HarmonyUtils", "hmftype: %s", a2);
        }
        return Boolean.parseBoolean(a2);
    }

    public static String a() {
        return "harmonyos,android";
    }
}
