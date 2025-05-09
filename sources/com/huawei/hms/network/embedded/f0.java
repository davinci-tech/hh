package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public abstract class f0 implements Runnable {
    public static final String g = "DnRsv";

    /* renamed from: a, reason: collision with root package name */
    public final String f5237a;
    public final int b;
    public m0 c;
    public a d;
    public s0 e;
    public String f;

    public interface a {
        void a(String str, m0 m0Var);

        void a(String str, Throwable th);
    }

    public abstract m0 c();

    @Override // java.lang.Runnable
    public void run() {
        this.e.a();
        if (TextUtils.isEmpty(this.f5237a)) {
            this.e.a(new Exception("domain == null"));
            return;
        }
        a(c());
        if (!y.b(this.c)) {
            this.e.a(this.c);
            a aVar = this.d;
            if (aVar != null) {
                aVar.a(this.f5237a, this.c);
                return;
            }
            return;
        }
        Logger.i(g, t.m().a(this.b) + " query failed, dnsResult is null, domain:" + this.f5237a);
        StringBuilder sb = new StringBuilder("query failed, dnsResult is null, domain:");
        sb.append(this.f5237a);
        Exception exc = new Exception(sb.toString());
        this.e.a(exc);
        a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.a(this.f5237a, exc);
        }
    }

    public String b() {
        return this.f;
    }

    public void a(m0 m0Var) {
        this.c = m0Var;
    }

    public static final class b implements a {
        @Override // com.huawei.hms.network.embedded.f0.a
        public void a(String str, Throwable th) {
        }

        @Override // com.huawei.hms.network.embedded.f0.a
        public void a(String str, m0 m0Var) {
            a0.a(str, m0Var);
        }
    }

    public m0 a() {
        return this.c;
    }

    public f0(String str, int i, String str2, a aVar) {
        this.f5237a = str;
        this.b = i;
        this.f = str2;
        this.e = t.m().d().a(this);
        this.d = aVar;
    }

    public f0(String str, int i, String str2) {
        this.f5237a = str;
        this.b = i;
        this.f = str2;
        this.d = null;
        this.e = t.m().d().a(this);
    }
}
