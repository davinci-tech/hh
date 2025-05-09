package com.huawei.updatesdk.a.b.c;

import com.huawei.updatesdk.a.b.c.c.d;
import com.huawei.updatesdk.service.appmgr.bean.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Class> f10815a;

    public static d a(String str) {
        Class cls = f10815a.get(str);
        if (cls != null) {
            return (d) cls.newInstance();
        }
        throw new InstantiationException("ResponseBean class not found, method:" + str);
    }

    static {
        HashMap hashMap = new HashMap();
        f10815a = hashMap;
        hashMap.put(com.huawei.updatesdk.service.appmgr.bean.d.APIMETHOD, e.class);
        hashMap.put("client.getMarketInfo", com.huawei.updatesdk.service.appmgr.bean.b.class);
    }
}
