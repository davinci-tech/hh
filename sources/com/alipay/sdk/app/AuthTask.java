package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.m.u.h;
import defpackage.kg;
import defpackage.kh;
import defpackage.kl;
import defpackage.kr;
import defpackage.ls;
import defpackage.lv;
import defpackage.lw;
import defpackage.mc;
import defpackage.md;
import defpackage.mj;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class AuthTask {
    public static final Object c = h.class;

    /* renamed from: a, reason: collision with root package name */
    public Activity f853a;
    public mj b;

    public class a implements h.g {
        public a() {
        }

        @Override // com.alipay.sdk.m.u.h.g
        public void a() {
            AuthTask.this.b();
        }

        @Override // com.alipay.sdk.m.u.h.g
        public void b() {
        }
    }

    public AuthTask(Activity activity) {
        this.f853a = activity;
        lw.c().c(this.f853a);
        this.b = new mj(activity, "去支付宝授权");
    }

    private h.g c() {
        return new a();
    }

    private void e() {
        mj mjVar = this.b;
        if (mjVar != null) {
            mjVar.a();
        }
    }

    public String auth(String str, boolean z) {
        String innerAuth;
        synchronized (this) {
            innerAuth = innerAuth(new lv(this.f853a, str, "auth"), str, z);
        }
        return innerAuth;
    }

    public Map<String, String> authV2(String str, boolean z) {
        Map<String, String> c2;
        synchronized (this) {
            lv lvVar = new lv(this.f853a, str, "authV2");
            c2 = mc.c(lvVar, innerAuth(lvVar, str, z));
        }
        return c2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0069, code lost:
    
        if (defpackage.kr.a().y() == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String innerAuth(defpackage.lv r9, java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.innerAuth(lv, java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String aM_(android.app.Activity r4, java.lang.String r5, defpackage.lv r6) {
        /*
            r3 = this;
            r3.e()
            r0 = 0
            ln r1 = new ln     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r1.<init>()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            lh r4 = r1.a(r6, r4, r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            org.json.JSONObject r4 = r4.a()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r5 = "form"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r5 = "onload"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.util.List r4 = defpackage.ls.b(r4)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r3.b()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r5 = 0
        L25:
            int r1 = r4.size()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            if (r5 >= r1) goto L4a
            java.lang.Object r1 = r4.get(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            ls r1 = (defpackage.ls) r1     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.r.a r1 = r1.a()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            com.alipay.sdk.m.r.a r2 = com.alipay.sdk.m.r.a.WapPay     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            if (r1 != r2) goto L47
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            ls r4 = (defpackage.ls) r4     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            java.lang.String r4 = r3.b(r6, r4)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L57
            r3.b()
            return r4
        L47:
            int r5 = r5 + 1
            goto L25
        L4a:
            r3.b()
            goto L6b
        L4e:
            r4 = move-exception
            java.lang.String r5 = "biz"
            java.lang.String r1 = "H5AuthDataAnalysisError"
            defpackage.kl.e(r6, r5, r1, r4)     // Catch: java.lang.Throwable -> L86
            goto L68
        L57:
            r4 = move-exception
            com.alipay.sdk.m.j.c r5 = com.alipay.sdk.m.j.c.NETWORK_ERROR     // Catch: java.lang.Throwable -> L86
            int r5 = r5.b()     // Catch: java.lang.Throwable -> L86
            com.alipay.sdk.m.j.c r5 = com.alipay.sdk.m.j.c.b(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r0 = "net"
            defpackage.kl.e(r6, r0, r4)     // Catch: java.lang.Throwable -> L86
            r0 = r5
        L68:
            r3.b()
        L6b:
            if (r0 != 0) goto L77
            com.alipay.sdk.m.j.c r4 = com.alipay.sdk.m.j.c.FAILED
            int r4 = r4.b()
            com.alipay.sdk.m.j.c r0 = com.alipay.sdk.m.j.c.b(r4)
        L77:
            int r4 = r0.b()
            java.lang.String r5 = r0.a()
            java.lang.String r6 = ""
            java.lang.String r4 = defpackage.kh.c(r4, r5, r6)
            return r4
        L86:
            r4 = move-exception
            r3.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.aM_(android.app.Activity, java.lang.String, lv):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        mj mjVar = this.b;
        if (mjVar != null) {
            mjVar.b();
        }
    }

    private String aL_(Activity activity, String str, lv lvVar) {
        String e = lvVar.e(str);
        List<kr.e> k = kr.a().k();
        if (!kr.a().o || k == null) {
            k = kg.d;
        }
        if (md.e(lvVar, this.f853a, k, true)) {
            h hVar = new h(activity, lvVar, c());
            String c2 = hVar.c(e, false);
            hVar.a();
            if (!TextUtils.equals(c2, "failed") && !TextUtils.equals(c2, "scheme_failed")) {
                return TextUtils.isEmpty(c2) ? kh.a() : c2;
            }
            kl.b(lvVar, "biz", "LogBindCalledH5");
            return aM_(activity, e, lvVar);
        }
        kl.b(lvVar, "biz", "LogCalledH5");
        return aM_(activity, e, lvVar);
    }

    private String b(lv lvVar, ls lsVar) {
        String[] c2 = lsVar.c();
        Bundle bundle = new Bundle();
        bundle.putString("url", c2[0]);
        Intent intent = new Intent(this.f853a, (Class<?>) H5AuthActivity.class);
        intent.putExtras(bundle);
        lv.e.a(lvVar, intent);
        this.f853a.startActivity(intent);
        Object obj = c;
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException unused) {
                return kh.a();
            }
        }
        String e = kh.e();
        return TextUtils.isEmpty(e) ? kh.a() : e;
    }
}
