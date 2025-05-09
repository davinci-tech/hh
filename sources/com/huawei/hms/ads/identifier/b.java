package com.huawei.hms.ads.identifier;

import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, List<String>> f4317a;

    private static boolean a(List<String> list, String str) {
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (str.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
        }
        Log.d("WhiteList", "check false!");
        return false;
    }

    public static boolean a(String str, String str2) {
        return a(f4317a.get(str), str2);
    }

    static {
        HashMap hashMap = new HashMap();
        f4317a = hashMap;
        hashMap.put("com.huawei.hwid", Arrays.asList("b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"));
        hashMap.put("com.huawei.hwid.tv", Arrays.asList("3517262215d8d3008cbf888750b6418edc4d562ac33ed6874e0d73aba667bc3c"));
        hashMap.put("com.huawei.hms", Arrays.asList("e49d5c2c0e11b3b1b96ca56c6de2a14ec7dab5ccc3b5f300d03e5b4dba44f539"));
    }
}
