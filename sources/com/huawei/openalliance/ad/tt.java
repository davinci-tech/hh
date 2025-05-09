package com.huawei.openalliance.ad;

import android.app.Activity;
import com.huawei.operation.beans.TitleBean;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class tt {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, to> f7543a = new HashMap(5);
    private static final Map<String, Class<? extends to>> b;

    private static to b(String str) {
        Class<? extends to> cls = b.get(str);
        if (cls == null) {
            ho.a("ShareManager", "There is no matching type for %s", str);
            return null;
        }
        to newInstance = cls.newInstance();
        f7543a.put(str, newInstance);
        return newInstance;
    }

    public static boolean a(String str) {
        try {
            Class<? extends to> cls = b.get(str);
            if (cls != null) {
                return cls.newInstance().a();
            }
            ho.a("ShareManager", "There is no matching type for %s", str);
            return false;
        } catch (Throwable unused) {
            ho.c("ShareManager", "Share throws exception");
            return false;
        }
    }

    public static void a(String str, Activity activity, ts tsVar, tu tuVar) {
        ho.a("ShareManager", "shareAd : %s", str);
        try {
            Map<String, to> map = f7543a;
            to b2 = !map.containsKey(str) ? b(str) : map.get(str);
            if (b2 != null) {
                b2.a(activity, tsVar, tuVar);
            }
        } catch (Throwable unused) {
            ho.c("ShareManager", "Share throws exception");
        }
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put(TitleBean.RIGHT_BTN_TYPE_MORE, tq.class);
        hashMap.put("WX", ty.class);
        hashMap.put(Constants.SOURCE_QQ, tr.class);
        hashMap.put("WB", tx.class);
        hashMap.put("weLink", tw.class);
    }
}
