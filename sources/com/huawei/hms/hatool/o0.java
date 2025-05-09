package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* loaded from: classes4.dex */
public class o0 {
    private static o0 c;

    /* renamed from: a, reason: collision with root package name */
    private String f4607a;
    private String b;

    private boolean f() {
        return true;
    }

    public void b() {
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        if (c(b(generateSecureRandomStr))) {
            this.f4607a = generateSecureRandomStr;
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.f4607a)) {
            this.f4607a = c();
        }
        return this.f4607a;
    }

    private static void g() {
        synchronized (o0.class) {
            if (c == null) {
                c = new o0();
            }
        }
    }

    private String e() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = new x().a();
        }
        return this.b;
    }

    public static o0 d() {
        if (c == null) {
            g();
        }
        return c;
    }

    private boolean c(String str) {
        v.c("hmsSdk", "refresh sp aes key");
        if (TextUtils.isEmpty(str)) {
            v.c("hmsSdk", "refreshLocalKey(): encrypted key is empty");
            return false;
        }
        d.b(q0.i(), "Privacy_MY", "PrivacyData", str);
        d.b(q0.i(), "Privacy_MY", "flashKeyTime", System.currentTimeMillis());
        return true;
    }

    private String c() {
        String a2 = d.a(q0.i(), "Privacy_MY", "PrivacyData", "");
        if (!TextUtils.isEmpty(a2)) {
            return a(a2);
        }
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        c(b(generateSecureRandomStr));
        return generateSecureRandomStr;
    }

    private String b(String str) {
        return f() ? AesGcmKS.encrypt("analytics_keystore", str) : n.b(str, e());
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x003b, code lost:
    
        if (f() != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r3) {
        /*
            r2 = this;
            boolean r0 = r2.f()
            if (r0 == 0) goto Ld
            java.lang.String r0 = "analytics_keystore"
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS.decrypt(r0, r3)
            goto Lf
        Ld:
            java.lang.String r0 = ""
        Lf:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L4e
            java.lang.String r0 = "hmsSdk"
            java.lang.String r1 = "deCrypt work key first"
            com.huawei.hms.hatool.v.c(r0, r1)
            java.lang.String r0 = r2.e()
            java.lang.String r0 = com.huawei.hms.hatool.n.a(r3, r0)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L3e
            r3 = 16
            java.lang.String r0 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.generateSecureRandomStr(r3)
            java.lang.String r3 = r2.b(r0)
            r2.c(r3)
            boolean r3 = r2.f()
            if (r3 == 0) goto L4e
            goto L4b
        L3e:
            boolean r3 = r2.f()
            if (r3 == 0) goto L4e
            java.lang.String r3 = r2.b(r0)
            r2.c(r3)
        L4b:
            com.huawei.hms.hatool.x.c()
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.o0.a(java.lang.String):java.lang.String");
    }
}
