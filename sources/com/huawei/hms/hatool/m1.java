package com.huawei.hms.hatool;

import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;

/* loaded from: classes4.dex */
public class m1 {
    private static m1 b = new m1();

    /* renamed from: a, reason: collision with root package name */
    private a f4603a = new a();

    public String c() {
        return this.f4603a.f4604a;
    }

    public long b() {
        return this.f4603a.c;
    }

    public void a(String str, String str2) {
        long b2 = b();
        String c = w0.c(str, str2);
        if (c == null || c.isEmpty()) {
            v.e("WorkKeyHandler", "get rsa pubkey config error");
            return;
        }
        if (b2 == 0) {
            b2 = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - b2 <= 43200000) {
            return;
        }
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        String a2 = h0.a(c, generateSecureRandomStr);
        this.f4603a.a(b2);
        this.f4603a.b(generateSecureRandomStr);
        this.f4603a.a(a2);
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        String f4604a;
        String b;
        long c = 0;

        void b(String str) {
            m1.this.f4603a.f4604a = str;
        }

        void a(String str) {
            m1.this.f4603a.b = str;
        }

        void a(long j) {
            m1.this.f4603a.c = j;
        }

        a() {
        }
    }

    public String a() {
        return this.f4603a.b;
    }

    public static m1 d() {
        return b;
    }
}
