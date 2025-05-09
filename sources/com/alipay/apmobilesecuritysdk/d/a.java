package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import defpackage.id;
import defpackage.mq;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class a {
    public static Map<String, String> a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (a.class) {
            String b = mq.b(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            id.b();
            hashMap.put("AA2", id.a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAYSDK");
            hashMap.put("AA4", "3.4.0.202303020703");
            hashMap.put("AA6", b);
        }
        return hashMap;
    }
}
