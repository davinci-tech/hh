package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class af {
    public static boolean a() {
        return false;
    }

    public static boolean a(Map<String, List<String>> map, String str, String str2) {
        if (!bl.a(map) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return a(map.get(str), str2);
        }
        ho.b("FlavorUtils", "inWhiteList invalid input.");
        return false;
    }

    private static boolean a(List<String> list, String str) {
        if (list == null) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }
}
