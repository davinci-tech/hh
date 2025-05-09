package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class w9 implements g9 {
    public static final String i = "host";
    public static final String n = "encoding";
    public final n7.a b;
    public final y8 c;
    public final v9 d;
    public volatile y9 e;
    public final r7 f;
    public volatile boolean g;
    public static final String h = "connection";
    public static final String j = "keep-alive";
    public static final String k = "proxy-connection";
    public static final String m = "te";
    public static final String l = "transfer-encoding";
    public static final String o = "upgrade";
    public static final List<String> p = f8.a(h, "host", j, k, m, l, "encoding", o, ":method", ":path", ":scheme", ":authority");
    public static final List<String> q = f8.a(h, "host", j, k, m, l, "encoding", o);

    @Override // com.huawei.hms.network.embedded.g9
    public void d() throws IOException {
        this.d.flush();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void cancel() {
        this.g = true;
        if (this.e != null) {
            this.e.a(r9.CANCEL);
        }
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void c() throws IOException {
        this.e.f().close();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public zb b(v7 v7Var) {
        return this.e.g();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public j7 b() throws IOException {
        return this.e.l();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public void a(t7 t7Var) throws IOException {
        if (this.e != null) {
            return;
        }
        this.e = this.d.a(b(t7Var), t7Var.b() != null);
        if (this.g) {
            this.e.a(r9.CANCEL);
            throw new IOException("Canceled");
        }
        this.e.j().timeout(this.b.c(), TimeUnit.MILLISECONDS);
        this.e.n().timeout(this.b.b(), TimeUnit.MILLISECONDS);
    }

    @Override // com.huawei.hms.network.embedded.g9
    public yb a(t7 t7Var, long j2) {
        return this.e.f();
    }

    @Override // com.huawei.hms.network.embedded.g9
    public y8 a() {
        return this.c;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public v7.a a(boolean z) throws IOException {
        v7.a a2 = a(this.e.k(), this.f);
        if (z && c8.f5203a.a(a2) == 100) {
            return null;
        }
        return a2;
    }

    @Override // com.huawei.hms.network.embedded.g9
    public long a(v7 v7Var) {
        return i9.a(v7Var);
    }

    public static List<s9> b(t7 t7Var) {
        j7 e = t7Var.e();
        ArrayList arrayList = new ArrayList(e.d() + 4);
        arrayList.add(new s9(s9.k, t7Var.h()));
        arrayList.add(new s9(s9.l, m9.a(t7Var.k())));
        String a2 = t7Var.a("Host");
        if (a2 != null) {
            arrayList.add(new s9(s9.n, a2));
        }
        arrayList.add(new s9(s9.m, t7Var.k().s()));
        int d = e.d();
        for (int i2 = 0; i2 < d; i2++) {
            String lowerCase = e.a(i2).toLowerCase(Locale.US);
            if (!p.contains(lowerCase) || (lowerCase.equals(m) && e.b(i2).equals("trailers"))) {
                arrayList.add(new s9(lowerCase, e.b(i2)));
            }
        }
        return arrayList;
    }

    public static v7.a a(j7 j7Var, r7 r7Var) throws IOException {
        j7.a aVar = new j7.a();
        int d = j7Var.d();
        o9 o9Var = null;
        for (int i2 = 0; i2 < d; i2++) {
            String a2 = j7Var.a(i2);
            String b = j7Var.b(i2);
            if (a2.equals(":status")) {
                o9Var = o9.a("HTTP/1.1 " + b);
            } else if (!q.contains(a2)) {
                c8.f5203a.a(aVar, a2, b);
            }
        }
        if (o9Var != null) {
            return new v7.a().a(r7Var).a(o9Var.b).a(o9Var.c).a(aVar.a());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public w9(q7 q7Var, y8 y8Var, n7.a aVar, v9 v9Var) {
        this.c = y8Var;
        this.b = aVar;
        this.d = v9Var;
        this.f = q7Var.v().contains(r7.H2_PRIOR_KNOWLEDGE) ? r7.H2_PRIOR_KNOWLEDGE : r7.HTTP_2;
    }
}
