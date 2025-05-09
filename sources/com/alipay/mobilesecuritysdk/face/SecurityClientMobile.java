package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import defpackage.mq;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class SecurityClientMobile {
    public static String GetApdid(Context context, Map<String, String> map) {
        String a2;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put("utdid", mq.b(map, "utdid", ""));
            hashMap.put("tid", mq.b(map, "tid", ""));
            hashMap.put(JsbMapKeyNames.H5_USER_ID, mq.b(map, JsbMapKeyNames.H5_USER_ID, ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a2 = a.a(context);
        }
        return a2;
    }
}
