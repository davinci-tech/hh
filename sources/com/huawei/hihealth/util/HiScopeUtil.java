package com.huawei.hihealth.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import health.compact.a.HiCommonUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HiScopeUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f4143a;

    static {
        ArrayList arrayList = new ArrayList(20);
        f4143a = arrayList;
        arrayList.add("com.huawei.android.hms.health.profile");
        arrayList.add("com.huawei.android.hms.health.profile.readonly");
        arrayList.add("com.huawei.android.hms.health.sport");
        arrayList.add("com.huawei.android.hms.health.sport.readonly");
        arrayList.add("com.huawei.android.hms.health.health.wgt");
        arrayList.add("com.huawei.android.hms.health.health.wgt.readonly");
        arrayList.add("com.huawei.android.hms.health.health.slp");
        arrayList.add("com.huawei.android.hms.health.health.slp.readonly");
        arrayList.add("com.huawei.android.hms.health.health.hr");
        arrayList.add("com.huawei.android.hms.health.health.hr.readonly");
        arrayList.add("com.huawei.android.hms.health.health.ecg");
        arrayList.add("com.huawei.android.hms.health.health.ecg.readonly");
        arrayList.add("com.huawei.android.hms.health.health.bg");
        arrayList.add("com.huawei.android.hms.health.health.bg.readonly");
        arrayList.add("com.huawei.android.hms.health.health.bf");
        arrayList.add("com.huawei.android.hms.health.health.bf.readonly");
        arrayList.add("com.huawei.android.hms.health.motionpath");
        arrayList.add("com.huawei.android.hms.health.motionpath.readonly");
    }

    public static boolean c(String str) {
        return !HiCommonUtil.b(str) && f4143a.contains(str);
    }

    public static String e(Context context) {
        Object obj;
        if (context == null) {
            LogUtil.d("Debug_HiScopeUtil", "context is null");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        LogUtil.d("Debug_HiScopeUtil", "pm = ", packageManager);
        if (packageManager == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null && (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) != null) {
                LogUtil.d("Debug_HiScopeUtil", "appId = ", String.valueOf(obj));
                return String.valueOf(obj);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return "";
    }

    public static String c(Context context, String str) {
        Object obj;
        LogUtil.b("Debug_HiScopeUtil", "getThirdMetaDataAppId...");
        if (context == null) {
            LogUtil.e("Debug_HiScopeUtil", "getThirdMetaDataAppId:context is null.");
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.e("Debug_HiScopeUtil", "getThirdMetaDataAppId:packageManager is null.");
            return "";
        }
        LogUtil.b("Debug_HiScopeUtil", "getThirdMetaDataAppId:packageName is ", str);
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("com.huawei.hms.client.appid")) == null) {
                return "";
            }
            String valueOf = String.valueOf(obj);
            LogUtil.d("Debug_HiScopeUtil", "appId = ", valueOf);
            return (valueOf == null || !valueOf.startsWith("appid=")) ? valueOf : valueOf.substring(6);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("Debug_HiScopeUtil", "getThirdMetaDataAppId:NameNotFoundException");
            return "";
        }
    }

    public static List<String> d(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            String l = l(it.next().getType());
            if (c(l) && !arrayList.contains(l)) {
                arrayList.add(l);
            }
        }
        return arrayList;
    }

    public static List<String> a(int[] iArr) {
        if (HiCommonUtil.e(iArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            String l = l(i);
            if (c(l) && !arrayList.contains(l)) {
                arrayList.add(l);
            }
        }
        return arrayList;
    }

    public static List<String> b(int[] iArr) {
        if (HiCommonUtil.e(iArr)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            String d = d(i);
            if (c(d) && !arrayList.contains(d)) {
                arrayList.add(d);
            }
        }
        return arrayList;
    }

    public static List<String> e(int[] iArr) {
        return a(iArr);
    }

    /* renamed from: com.huawei.hihealth.util.HiScopeUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            b = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[HiHealthDataType.Category.SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[HiHealthDataType.Category.SESSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[HiHealthDataType.Category.SEQUENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[HiHealthDataType.Category.REALTIME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[HiHealthDataType.Category.STAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static String l(int i) {
        if (i == -1) {
            return "com.huawei.android.hms.health.profile";
        }
        switch (AnonymousClass1.b[HiHealthDataType.e(i).ordinal()]) {
            case 1:
                return b(i);
            case 2:
                return n(i);
            case 3:
                return j(i);
            case 4:
                return i(i);
            case 5:
                return c(i);
            case 6:
                return k(i);
            default:
                return null;
        }
    }

    private static String d(int i) {
        if (i == -1) {
            return "com.huawei.android.hms.health.profile.readonly";
        }
        switch (AnonymousClass1.b[HiHealthDataType.e(i).ordinal()]) {
            case 1:
                return e(i);
            case 2:
                return h(i);
            case 3:
                return g(i);
            case 4:
                return f(i);
            case 5:
                return a(i);
            case 6:
                return o(i);
            default:
                return null;
        }
    }

    private static String k(int i) {
        if (i <= 41000) {
            return "com.huawei.android.hms.health.sport";
        }
        if (i <= 43900) {
            return "com.huawei.android.hms.health.motionpath";
        }
        if (i <= 44099 || i <= 44299) {
            return "com.huawei.android.hms.health.health.slp";
        }
        if (i > 46010 && i > 47000) {
            return null;
        }
        return "com.huawei.android.hms.health.health.hr";
    }

    private static String o(int i) {
        if (i <= 41000) {
            return "com.huawei.android.hms.health.sport.readonly";
        }
        if (i <= 43900) {
            return "com.huawei.android.hms.health.motionpath.readonly";
        }
        if (i <= 44099 || i <= 44299) {
            return "com.huawei.android.hms.health.health.slp.readonly";
        }
        if (i > 46010 && i > 47000) {
            return null;
        }
        return "com.huawei.android.hms.health.health.hr.readonly";
    }

    private static String n(int i) {
        if (i == 10001) {
            return "com.huawei.android.hms.health.health.bg";
        }
        if (i == 10002) {
            return "com.huawei.android.hms.health.health.bf";
        }
        if (i != 10006) {
            return null;
        }
        return "com.huawei.android.hms.health.health.wgt";
    }

    private static String h(int i) {
        if (i == 10001) {
            return "com.huawei.android.hms.health.health.bg.readonly";
        }
        if (i == 10002) {
            return "com.huawei.android.hms.health.health.bf.readonly";
        }
        if (i != 10006) {
            return null;
        }
        return "com.huawei.android.hms.health.health.wgt.readonly";
    }

    private static String j(int i) {
        if (i <= 21000) {
            return "com.huawei.android.hms.health.sport";
        }
        if (i <= 22199) {
            return "com.huawei.android.hms.health.health.slp";
        }
        return null;
    }

    private static String g(int i) {
        if (i <= 21000) {
            return "com.huawei.android.hms.health.sport.readonly";
        }
        if (i <= 22199) {
            return "com.huawei.android.hms.health.health.slp.readonly";
        }
        return null;
    }

    private static String i(int i) {
        if (i <= 30999) {
            return "com.huawei.android.hms.health.motionpath";
        }
        return null;
    }

    private static String f(int i) {
        if (i <= 30999) {
            return "com.huawei.android.hms.health.motionpath.readonly";
        }
        return null;
    }

    private static String c(int i) {
        if (i != 50001) {
            return null;
        }
        return "com.huawei.android.hms.health.health.hr";
    }

    private static String a(int i) {
        if (i != 50001) {
            return null;
        }
        return "com.huawei.android.hms.health.health.hr.readonly";
    }

    private static String b(int i) {
        if (i < 1000) {
            return "com.huawei.android.hms.health.sport";
        }
        if (i == 2001) {
            return "com.huawei.android.hms.health.health.wgt";
        }
        if (i == 2002) {
            return "com.huawei.android.hms.health.health.hr";
        }
        if (i == 2004) {
            return "com.huawei.android.hms.health.health.wgt";
        }
        if (i == 2018 || i == 2105) {
            return "com.huawei.android.hms.health.health.hr";
        }
        if (i == 2106) {
            return "com.huawei.android.hms.health.health.bg";
        }
        switch (i) {
            case 2006:
            case 2007:
                return "com.huawei.android.hms.health.health.bf";
            case 2008:
            case 2009:
            case 2010:
            case 2011:
            case 2012:
            case 2013:
            case 2014:
            case 2015:
                return "com.huawei.android.hms.health.health.bg";
            default:
                return null;
        }
    }

    private static String e(int i) {
        if (i < 1000) {
            return "com.huawei.android.hms.health.sport.readonly";
        }
        if (i == 2001) {
            return "com.huawei.android.hms.health.health.wgt.readonly";
        }
        if (i == 2002) {
            return "com.huawei.android.hms.health.health.hr.readonly";
        }
        if (i == 2004) {
            return "com.huawei.android.hms.health.health.wgt.readonly";
        }
        if (i == 2018 || i == 2105) {
            return "com.huawei.android.hms.health.health.hr.readonly";
        }
        if (i == 2106) {
            return "com.huawei.android.hms.health.health.bg.readonly";
        }
        switch (i) {
            case 2006:
            case 2007:
                return "com.huawei.android.hms.health.health.bf.readonly";
            case 2008:
            case 2009:
            case 2010:
            case 2011:
            case 2012:
            case 2013:
            case 2014:
            case 2015:
                return "com.huawei.android.hms.health.health.bg.readonly";
            default:
                return null;
        }
    }
}
