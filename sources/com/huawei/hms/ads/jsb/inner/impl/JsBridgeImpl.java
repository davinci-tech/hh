package com.huawei.hms.ads.jsb.inner.impl;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.jsb.JsbConfig;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.g;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.j;
import com.huawei.openalliance.ad.m;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.m;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JsBridgeImpl {
    public static void invoke(Context context, String str, String str2, RemoteCallResultCallback<String> remoteCallResultCallback, Class<String> cls) {
        if (context == null || TextUtils.isEmpty(str2)) {
            ho.d("JsBridgeImpl", "param is invalid, please check it!");
            j.a(remoteCallResultCallback, str, 1001, null, true);
            return;
        }
        g a2 = m.a().a(str);
        m.a aVar = m.a.IO;
        if (a2 != null) {
            aVar = a2.a();
            if (context instanceof Activity) {
                a2.a((Activity) context);
            }
        }
        com.huawei.openalliance.ad.utils.m.a(new a(context.getApplicationContext(), a2, str, str2, remoteCallResultCallback), aVar, false);
    }

    public static String invoke(Context context, String str, String str2) {
        Object obj;
        JSONObject jSONObject = new JSONObject();
        int i = 1011;
        if (context != null) {
            try {
            } catch (Throwable th) {
                ho.c("JsBridgeImpl", "call method : " + th.getClass().getSimpleName());
                obj = "call " + str + " " + th.getClass().getSimpleName() + ":" + th.getMessage();
            }
            if (!TextUtils.isEmpty(str2)) {
                g a2 = com.huawei.openalliance.ad.m.a().a(str);
                if (a2 != null) {
                    ho.b("JsBridgeImpl", "call api: " + str);
                    obj = a2.a(context.getApplicationContext(), new JSONObject(str2).optString("content"));
                    i = 1000;
                } else {
                    obj = null;
                }
                try {
                    jSONObject.put("code", i);
                    jSONObject.put("data", obj);
                } catch (Throwable th2) {
                    ho.c("JsBridgeImpl", "call method : " + th2.getClass().getSimpleName());
                }
                return jSONObject.toString();
            }
        }
        ho.c("JsBridgeImpl", "param is invalid, please check it!");
        jSONObject.put("msg", "invalid params");
        jSONObject.put("code", 1011);
        return jSONObject.toString();
    }

    public static void initConfig(Context context, JsbConfig jsbConfig) {
        com.huawei.hms.ads.jsb.a.a(context).a(jsbConfig);
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final Context f4341a;
        private final String b;
        private final String c;
        private final RemoteCallResultCallback<String> d;
        private g e;

        @Override // java.lang.Runnable
        public void run() {
            JsBridgeImpl.b(this.f4341a, this.e, this.b, this.c, this.d);
        }

        public a(Context context, g gVar, String str, String str2, RemoteCallResultCallback<String> remoteCallResultCallback) {
            this.f4341a = context;
            this.b = str;
            this.c = str2;
            this.d = remoteCallResultCallback;
            this.e = gVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, g gVar, String str, String str2, RemoteCallResultCallback<String> remoteCallResultCallback) {
        if (gVar != null) {
            ho.b("JsBridgeImpl", "call method: " + str);
            if (ho.a()) {
                ho.a("JsBridgeImpl", "param: %s", dl.a(str2));
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString("content");
                gVar.a(jSONObject.optString("url"));
                String optString2 = jSONObject.optString(JsbMapKeyNames.H5_CLIENT_ID);
                String optString3 = jSONObject.optString("webid");
                if (ho.a()) {
                    ho.a("JsBridgeImpl", "call method %s, webID: %s", str, optString3);
                }
                if (TextUtils.isEmpty(optString3)) {
                    gVar.b(optString2);
                } else {
                    gVar.b(optString3);
                }
                gVar.a(context, optString, remoteCallResultCallback);
                return;
            } catch (Throwable th) {
                ho.c("JsBridgeImpl", "call method %s, ex: %s", str, th.getClass().getSimpleName());
                j.a(remoteCallResultCallback, str, 1011, th.getClass().getSimpleName() + ":" + th.getMessage(), true);
                ho.a(3, th);
                return;
            }
        }
        String str3 = "api for " + str + " is not found";
        ho.b("JsBridgeImpl", "call " + str3);
        j.a(remoteCallResultCallback, str, 1011, str3, true);
    }
}
