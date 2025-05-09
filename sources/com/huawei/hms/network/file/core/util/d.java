package com.huawei.hms.network.file.core.util;

import com.huawei.openalliance.ad.constant.Constants;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class d {
    public static boolean c(Map<String, List<String>> map) {
        if (map == null) {
            return false;
        }
        List<String> a2 = a(map, "Accept-Ranges");
        if (!Utils.isEmpty(a2)) {
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                if ("bytes".equalsIgnoreCase(it.next().toLowerCase(Locale.ROOT))) {
                    return true;
                }
            }
        }
        return !Utils.isEmpty(a(map, "Content-Range"));
    }

    public static boolean b(Map<String, List<String>> map) {
        if (map == null) {
            return false;
        }
        List<String> a2 = a(map, "Content-Encoding");
        if (Utils.isEmpty(a2)) {
            return false;
        }
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            if (Constants.GZIP.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Map<String, List<String>> map) {
        if (map == null) {
            return false;
        }
        List<String> a2 = a(map, "Content-Encoding");
        if (Utils.isEmpty(a2)) {
            return false;
        }
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            if (!"none".equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static List<String> a(Map<String, List<String>> map, String str) {
        List<String> list = null;
        if (map != null) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(str)) {
                    list = entry.getValue();
                }
            }
        }
        return list;
    }
}
