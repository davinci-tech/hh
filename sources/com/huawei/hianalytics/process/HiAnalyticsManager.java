package com.huawei.hianalytics.process;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.d1;
import com.huawei.hianalytics.i;
import com.huawei.hianalytics.j;
import com.huawei.hianalytics.j1;
import com.huawei.hianalytics.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HiAnalyticsManager {
    public static void clearCachedData() {
        k a2 = k.a();
        if (a2.f46a == null) {
            HiLog.sw("HADM", "sdk not init");
        } else {
            if (!j1.f3879a.a() || a2.f47a.size() <= 0) {
                return;
            }
            Iterator<d1> it = a2.f47a.values().iterator();
            while (it.hasNext()) {
                it.next().f27a.clearCacheDataByTag();
            }
        }
    }

    public static String createUUID(Context context) {
        k.a().getClass();
        if (context != null) {
            return j.c(context);
        }
        HiLog.sw("HADM", "sdk not init");
        return "";
    }

    public static void disableDefaultInstanceReport() {
        k a2 = k.a();
        a2.getClass();
        a2.f47a.remove("ha_default_collection");
    }

    public static List<String> getAllTags() {
        k a2 = k.a();
        a2.getClass();
        return new ArrayList(a2.f47a.keySet());
    }

    public static boolean getInitFlag(String str) {
        k a2 = k.a();
        a2.getClass();
        if (str != null) {
            return a2.f47a.containsKey(str);
        }
        HiLog.sw("HADM", "tag can't be null");
        return false;
    }

    public static boolean isDebugMode(Context context) {
        k.a().getClass();
        if (context != null) {
            return j.m561a(context);
        }
        HiLog.sw("HADM", "sdk not init");
        return false;
    }

    public static void setAppid(String str) {
        Context context = k.a().f46a;
        if (context == null) {
            HiLog.sw("HADM", "sdk not init");
            return;
        }
        String packageName = context.getPackageName();
        if (!j.m564a("appID", str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}")) {
            str = packageName;
        }
        i.a().m550a().g = str;
    }

    public static void setCacheSize(int i) {
        String str;
        if (k.a().f46a == null) {
            HiLog.sw("HADM", "sdk not init");
            return;
        }
        int i2 = 10;
        if (i <= 10) {
            i2 = 5;
            str = i < 5 ? "parameter under size" : "parameter overlarge";
            i.a().m550a().f48a = i * 1048576;
        }
        HiLog.w("ParamCheckUtils", str);
        i = i2;
        i.a().m550a().f48a = i * 1048576;
    }

    public static void setCustomPkgName(String str) {
        if (k.a().f46a != null) {
            HiLog.sw("HADM", "sdk not init");
        } else if (TextUtils.isEmpty(str) || str.length() > 256) {
            HiLog.w("HADM", "customPkgName check failed");
        } else {
            i.a().m550a().n = str;
        }
    }

    public static HiAnalyticsInstance getInstanceByTag(String str) {
        return k.a().a(str);
    }
}
