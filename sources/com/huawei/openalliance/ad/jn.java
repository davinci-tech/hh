package com.huawei.openalliance.ad;

import android.content.Context;
import android.util.Pair;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jn {

    /* renamed from: a, reason: collision with root package name */
    private static final HashMap<Integer, String> f7133a;
    private static final ConcurrentHashMap<String, jp> b;
    private static String c;

    public static int[] b() {
        int[] iArr = new int[1];
        if (jl.a().b()) {
            ho.b("CustMonitorFactory", "req, support mz");
            iArr[0] = 1;
        } else {
            iArr[0] = 0;
        }
        c = a(iArr);
        return iArr;
    }

    private static void b(final Context context, final int i, final ContentRecord contentRecord) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jn.2
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).a(i, contentRecord);
            }
        });
    }

    public static boolean a(int i, String str) {
        String k;
        try {
            HashMap<Integer, String> hashMap = f7133a;
            if (hashMap.containsKey(Integer.valueOf(i)) && (k = com.huawei.openalliance.ad.utils.cz.k(str)) != null) {
                return k.endsWith(hashMap.get(Integer.valueOf(i)));
            }
            return false;
        } catch (Throwable th) {
            ho.c("CustMonitorFactory", "checkUrlContainMonitor err: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean a(int i) {
        if (i == 1) {
            return jl.a().b();
        }
        return false;
    }

    public static boolean a() {
        return !com.huawei.openalliance.ad.utils.cz.b(e.a()) && Integer.parseInt(e.a()) >= 30477310;
    }

    public static void a(String str, jp jpVar) {
        if (str == null || jpVar == null) {
            ho.a("CustMonitorFactory", "setContentIdToMonitor, param is null");
        } else {
            ho.a("CustMonitorFactory", "setContentIdToMonitor, contentId is %s, monitor is %s", str, jpVar.getClass().getSimpleName());
            b.put(str, jpVar);
        }
    }

    private static void a(final Context context, final int i, final ContentRecord contentRecord) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.jn.1
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).a(jn.c, i, contentRecord);
            }
        });
    }

    private static String a(int[] iArr) {
        StringBuilder sb = new StringBuilder("{");
        for (int i : iArr) {
            sb.append(i);
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    private static String a(int i, Monitor monitor, String str) {
        if (!monitor.a().equals(str)) {
            return "";
        }
        List<String> b2 = monitor.b();
        if (com.huawei.openalliance.ad.utils.bg.a(b2)) {
            return "";
        }
        for (String str2 : b2) {
            if (a(i, str2)) {
                return str2;
            }
        }
        return "";
    }

    public static jp a(String str) {
        if (str != null) {
            ConcurrentHashMap<String, jp> concurrentHashMap = b;
            if (concurrentHashMap.containsKey(str)) {
                return concurrentHashMap.get(str);
            }
        }
        ho.a("CustMonitorFactory", "no this contentId's monitor");
        return new jo();
    }

    public static jp a(Context context, boolean z, ou ouVar, ContentRecord contentRecord, boolean z2) {
        if (context == null || contentRecord == null || ouVar == null) {
            ho.b("CustMonitorFactory", "getMonitor, param is null.");
            return new jo();
        }
        int a2 = contentRecord.a();
        ho.b("CustMonitorFactory", "sdkMonitor is %s", Integer.valueOf(a2));
        if (a2 != 0) {
            b(context, a2, contentRecord);
        }
        return a2 != 1 ? new jo() : a(context, contentRecord, ouVar, z2, z);
    }

    private static jp a(Context context, ContentRecord contentRecord, ou ouVar, boolean z, boolean z2) {
        ho.b("CustMonitorFactory", "isCheckUiengine %s, isImage %s.", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (z && !a()) {
            ho.c("CustMonitorFactory", "uiengine ver is too low to support mz");
            return new jo();
        }
        Pair<String, String> a2 = a(context, contentRecord);
        ho.a("CustMonitorFactory", "monitor url, imp %s, click %s", a2.first, a2.second);
        if (!com.huawei.openalliance.ad.utils.cz.b((String) a2.first) || !com.huawei.openalliance.ad.utils.cz.b((String) a2.second)) {
            return z2 ? new jq((String) a2.first, (String) a2.second, ouVar, contentRecord) : new jr((String) a2.first, (String) a2.second, ouVar, contentRecord);
        }
        ho.b("CustMonitorFactory", "getMzMonitor, monitor url is blank.");
        a(context, contentRecord.a(), contentRecord);
        return new jo();
    }

    private static Pair<String, String> a(Context context, ContentRecord contentRecord) {
        Pair<String, String> a2 = a(contentRecord.a(), contentRecord.a(context));
        return (com.huawei.openalliance.ad.utils.cz.b((String) a2.first) && com.huawei.openalliance.ad.utils.cz.b((String) a2.second)) ? new Pair<>("", "") : a2;
    }

    private static Pair<String, String> a(int i, List<Monitor> list) {
        String str = "";
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return new Pair<>("", "");
        }
        String str2 = "";
        for (Monitor monitor : list) {
            String a2 = a(i, monitor, "imp");
            String a3 = a(i, monitor, "click");
            if (!com.huawei.openalliance.ad.utils.cz.b(a2)) {
                str = a2;
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(a3)) {
                str2 = a3;
            }
            if (!com.huawei.openalliance.ad.utils.cz.b(str) && !com.huawei.openalliance.ad.utils.cz.b(str2)) {
                return new Pair<>(str, str2);
            }
        }
        return new Pair<>(str, str2);
    }

    static {
        HashMap<Integer, String> hashMap = new HashMap<>();
        f7133a = hashMap;
        b = new ConcurrentHashMap<>();
        hashMap.put(1, "miaozhen.com");
    }
}
