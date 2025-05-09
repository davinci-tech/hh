package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.h;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.mq;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class b {
    public static Map<String, String> a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (b.class) {
            hashMap = new HashMap();
            String b = mq.b(map, "tid", "");
            String b2 = mq.b(map, "utdid", "");
            String b3 = mq.b(map, JsbMapKeyNames.H5_USER_ID, "");
            String b4 = mq.b(map, "appName", "");
            String b5 = mq.b(map, "appKeyClient", "");
            String b6 = mq.b(map, "tmxSessionId", "");
            String f = h.f(context);
            String b7 = mq.b(map, "sessionId", "");
            hashMap.put("AC1", b);
            hashMap.put("AC2", b2);
            hashMap.put("AC3", "");
            hashMap.put("AC4", f);
            hashMap.put("AC5", b3);
            hashMap.put("AC6", b6);
            hashMap.put("AC7", "");
            hashMap.put("AC8", b4);
            hashMap.put("AC9", b5);
            if (mq.b(b7)) {
                hashMap.put("AC10", b7);
            }
        }
        return hashMap;
    }
}
