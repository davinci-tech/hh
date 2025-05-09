package defpackage;

import android.content.Context;
import android.os.Binder;
import com.huawei.hidatamanager.util.LogUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class iwi {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f13637a;
    private static Map<Integer, String> b = null;
    private static final String d = "PackageNameUtil";
    private static final Object e = new Object();

    static {
        ArrayList arrayList = new ArrayList();
        f13637a = arrayList;
        b = new ConcurrentHashMap();
        arrayList.add("com.huawei.health.h5.cycle-calendar");
        arrayList.add("com.huawei.health.h5.ecgce");
        arrayList.add("com.huawei.health.h5.ppg");
        arrayList.add("com.huawei.health.h5.sleeping-music");
        arrayList.add("com.huawei.health.h5.vascular-health");
        arrayList.add("com.huawei.health.h5.sleepdetection");
        arrayList.add("com.huawei.audioaccessorymanager");
        arrayList.add("com.huawei.study.hiresearch");
    }

    public static void d(Context context, String str) {
        String str2 = d;
        LogUtils.i(str2, "saveCallingPackageName enter");
        if (context == null) {
            LogUtils.w(str2, "saveCallingPackageName fail: context is null");
            return;
        }
        if (str == null) {
            LogUtils.w(str2, "saveCallingPackageName fail: packageName is null");
            return;
        }
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid != null) {
            for (String str3 : packagesForUid) {
                if (str3.equals(str)) {
                    b.put(Integer.valueOf(Binder.getCallingPid()), str);
                    LogUtils.i(d, "saveCallingPackageName successed");
                    return;
                }
            }
        }
        LogUtils.w(d, "saveCallingPackageName fail");
    }

    public static String a(Context context) {
        String str = d;
        LogUtils.i(str, "getCallingPackageName enter");
        if (context == null) {
            LogUtils.w(str, "getCallingPackageName fail: context is null");
            return "";
        }
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null) {
            return "";
        }
        if (packagesForUid.length == 1) {
            return packagesForUid[0];
        }
        String str2 = b.get(Integer.valueOf(Binder.getCallingPid()));
        for (String str3 : packagesForUid) {
            if (str3.equals(str2)) {
                return str3;
            }
        }
        return "";
    }

    public static String c(String str, boolean z) {
        return "com.huawei.health.h5.ecg".equals(str) ? "com.huawei.health.ecg.collection" : (str.startsWith("com.huawei.health.device") || f13637a.contains(str) || z) ? "com.huawei.health" : str;
    }
}
