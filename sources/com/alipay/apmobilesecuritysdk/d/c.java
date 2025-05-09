package com.alipay.apmobilesecuritysdk.d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.e.f;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.huawei.health.messagecenter.model.CommonUtil;
import defpackage.ie;
import defpackage.mq;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class c {
    public static Map<String, String> a(Context context) {
        ie e = ie.e(APSecuritySdk.getInstance(context));
        HashMap hashMap = new HashMap();
        f a2 = com.alipay.apmobilesecuritysdk.e.e.a(context);
        String l = e.l(context);
        String s = e.s(context);
        if (a2 != null) {
            if (mq.e(l)) {
                l = a2.b();
            }
            if (mq.e(s)) {
                s = a2.e();
            }
        }
        f fVar = new f("", l, "", "", s);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommonUtil.IMEI, fVar.a());
                jSONObject.put("imsi", fVar.b());
                jSONObject.put("mac", fVar.c());
                jSONObject.put("bluetoothmac", fVar.d());
                jSONObject.put("gsi", fVar.e());
                String jSONObject2 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a("device_feature_file_name", "device_feature_file_key", jSONObject2);
                com.alipay.apmobilesecuritysdk.f.a.a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e2) {
                com.alipay.apmobilesecuritysdk.c.a.a(e2);
            }
        }
        hashMap.put("AD1", "");
        hashMap.put("AD2", l);
        hashMap.put("AD3", ie.c(context));
        hashMap.put("AD5", ie.a(context));
        hashMap.put("AD6", ie.j(context));
        hashMap.put("AD7", ie.i(context));
        hashMap.put("AD9", e.m(context));
        hashMap.put("AD10", s);
        hashMap.put("AD11", ie.c());
        hashMap.put("AD12", e.v());
        hashMap.put("AD13", ie.e());
        hashMap.put("AD14", ie.i());
        hashMap.put("AD15", ie.g());
        hashMap.put("AD16", ie.j());
        hashMap.put("AD17", "");
        hashMap.put("AD19", ie.f(context));
        hashMap.put("AD20", ie.f());
        hashMap.put("AD22", "");
        hashMap.put("AD23", ie.g(context));
        hashMap.put("AD24", mq.f(ie.e(context)));
        hashMap.put("AD26", e.n(context));
        hashMap.put("AD27", ie.k());
        hashMap.put("AD28", ie.q());
        hashMap.put("AD29", ie.p());
        hashMap.put("AD30", ie.m());
        hashMap.put("AD31", ie.r());
        hashMap.put("AD32", ie.n());
        hashMap.put("AD33", ie.l());
        hashMap.put("AD34", ie.h(context));
        hashMap.put("AD35", ie.o(context));
        hashMap.put("AD36", e.q(context));
        hashMap.put("AD37", ie.o());
        hashMap.put("AD38", ie.h());
        hashMap.put("AD39", ie.d(context));
        hashMap.put("AD40", ie.b(context));
        hashMap.put("AD41", ie.b());
        hashMap.put("AD42", ie.d());
        return hashMap;
    }
}
