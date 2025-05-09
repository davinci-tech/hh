package com.huawei.ads.adsrec;

import android.text.TextUtils;
import defpackage.vp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class u {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f1684a = new byte[0];
    private static Map<String, Map<Integer, List<String>>> b = new HashMap();

    public static void a(String str, int i, List<String> list) {
        synchronized (f1684a) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Map<Integer, List<String>> map = b.get(str);
            if (map != null) {
                map.put(Integer.valueOf(i), list);
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.valueOf(i), list);
                b.put(str, hashMap);
            }
        }
    }

    public static List<String> a(String str, int i) {
        Map<Integer, List<String>> map;
        synchronized (f1684a) {
            if (!TextUtils.isEmpty(str) && !vp.a(b)) {
                if (!b.containsKey(str) || (map = b.get(str)) == null || map.size() == 0) {
                    return null;
                }
                return map.get(Integer.valueOf(i));
            }
            return null;
        }
    }
}
