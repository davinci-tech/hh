package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class u {
    private static void a(Context context, String str, Map<String, String> map, Map<Integer, String> map2) {
        List list = (List) be.b(map.get(str), List.class, new Class[0]);
        if (bg.a(list)) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int identifier = context.getResources().getIdentifier((String) it.next(), "id", context.getPackageName());
            if (identifier != 0) {
                map2.put(Integer.valueOf(identifier), str);
            }
        }
    }

    public static Map<Integer, String> a(Context context, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (!bl.a(map)) {
            a(context, "title", map, hashMap);
            a(context, "content", map, hashMap);
            a(context, "category", map, hashMap);
            a(context, Constants.AUTOCONTENT_SUBCATEGORY, map, hashMap);
        }
        return hashMap;
    }
}
