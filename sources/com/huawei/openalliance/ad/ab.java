package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.jsb.inner.data.JsbCallBackData;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ab extends z {

    static class a implements com.huawei.openalliance.ad.inter.listeners.a {

        /* renamed from: a, reason: collision with root package name */
        private String f6552a;
        private RemoteCallResultCallback<String> b;

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void e() {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void f() {
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void d() {
            j.a(this.b, this.f6552a, 1000, new JsbCallBackData(null, true, "interstitial.cb.close"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void c() {
            j.a(this.b, this.f6552a, 1000, new JsbCallBackData(null, false, "interstitial.cb.completed"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void b() {
            j.a(this.b, this.f6552a, 1000, new JsbCallBackData(null, false, "interstitial.cb.click"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a(int i, int i2) {
            j.a(this.b, this.f6552a, 200, new JsbCallBackData(Integer.valueOf(i), false, "interstitial.cb.error"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.a
        public void a() {
            j.a(this.b, this.f6552a, 1000, new JsbCallBackData(null, false, "interstitial.cb.show"));
        }

        a(RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
            this.b = remoteCallResultCallback;
            this.f6552a = str;
        }
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        ContentRecord b = b(context, str);
        if (b == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        }
        com.huawei.openalliance.ad.inter.data.d a2 = ox.a(context, b);
        if (a2 == null) {
            ho.b("JsbStartInterstitialAdActivity", "ad is null, start activity failed");
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(JsbMapKeyNames.H5_CUSTOM_DATA);
            String optString2 = jSONObject.optString(JsbMapKeyNames.H5_USER_ID);
            boolean optBoolean = jSONObject.optBoolean("muted", true);
            boolean optBoolean2 = jSONObject.optBoolean(JsbMapKeyNames.H5_INTERSTITIAL_DATA_ALERTS, true);
            if (!TextUtils.isEmpty(optString)) {
                a2.setCustomData(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                a2.setUserId(optString2);
            }
            a2.f(optBoolean);
            a2.h(optBoolean2);
        } catch (Throwable unused) {
            ho.c("JsbStartInterstitialAdActivity", "content parse error");
        }
        a2.a(new a(remoteCallResultCallback, this.f7108a));
        bx.a(a(context), a2);
        b(remoteCallResultCallback, false);
    }

    public ab() {
        super("pps.activity.interstitial");
    }
}
