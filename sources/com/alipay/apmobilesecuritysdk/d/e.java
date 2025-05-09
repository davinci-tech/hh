package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import defpackage.mi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes7.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public static Map<String, String> f839a;
    public static final String[] b = {"AD1", "AD2", "AD3", "AD8", "AD9", "AD10", "AD11", "AD12", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD34", "AA1", "AA2", "AA3", "AA4", "AC4", "AC10", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    public static void c(Context context, Map<String, String> map) {
        synchronized (e.class) {
            TreeMap treeMap = new TreeMap();
            f839a = treeMap;
            treeMap.putAll(b.a(context, map));
            f839a.putAll(d.a(context));
            f839a.putAll(c.a(context));
            f839a.putAll(a.a(context, map));
        }
    }

    public static String b(Context context, Map<String, String> map) {
        String b2;
        synchronized (e.class) {
            a(context, map);
            TreeMap treeMap = new TreeMap();
            for (String str : b) {
                if (f839a.containsKey(str)) {
                    treeMap.put(str, f839a.get(str));
                }
            }
            b2 = mi.b(a(treeMap));
        }
        return b2;
    }

    public static void a() {
        synchronized (e.class) {
            f839a = null;
        }
    }

    public static Map<String, String> a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (e.class) {
            if (f839a == null) {
                c(context, map);
            }
            f839a.putAll(d.a());
            map2 = f839a;
        }
        return map2;
    }

    public static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            StringBuilder sb = new StringBuilder();
            if (i != 0) {
                str3 = "&";
            }
            sb.append(str3);
            sb.append(str);
            sb.append("=");
            sb.append(str2);
            stringBuffer.append(sb.toString());
        }
        return stringBuffer.toString();
    }
}
