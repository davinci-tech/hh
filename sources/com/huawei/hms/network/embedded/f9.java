package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: classes9.dex */
public final class f9 implements n7 {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f5252a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        boolean z;
        v7.a D;
        w7 a2;
        k9 k9Var = (k9) aVar;
        v8 e = k9Var.e();
        t7 request = k9Var.request();
        long currentTimeMillis = System.currentTimeMillis();
        e.a(request);
        v7.a aVar2 = null;
        if (!j9.b(request.h()) || request.b() == null) {
            e.i();
            z = false;
        } else {
            if ("100-continue".equalsIgnoreCase(request.a("Expect"))) {
                e.e();
                e.j();
                aVar2 = e.a(true);
                z = true;
            } else {
                z = false;
            }
            if (aVar2 != null) {
                e.i();
                if (!e.b().g()) {
                    e.h();
                }
            } else if (request.b().isDuplex()) {
                e.e();
                request.b().writeTo(ob.a(e.a(request, true)));
            } else {
                cb a3 = ob.a(e.a(request, false));
                request.b().writeTo(a3);
                a3.close();
            }
        }
        if (request.b() == null || !request.b().isDuplex()) {
            e.d();
        }
        if (!z) {
            e.j();
        }
        if (aVar2 == null) {
            aVar2 = e.a(false);
        }
        v7 a4 = aVar2.a(request).a(e.b().a()).b(currentTimeMillis).a(System.currentTimeMillis()).a();
        int w = a4.w();
        if (w == 100) {
            a4 = e.a(false).a(request).a(e.b().a()).b(currentTimeMillis).a(System.currentTimeMillis()).a();
            w = a4.w();
        }
        e.b(a4);
        if (this.f5252a && w == 101) {
            D = a4.D();
            a2 = f8.d;
        } else {
            D = a4.D();
            a2 = e.a(a4);
        }
        v7 a5 = D.a(a2).a();
        if ("close".equalsIgnoreCase(a5.H().a("Connection")) || "close".equalsIgnoreCase(a5.b("Connection"))) {
            e.h();
        }
        if ((w != 204 && w != 205) || a5.s().v() <= 0) {
            return a5;
        }
        throw new ProtocolException("HTTP " + w + " had non-zero Content-Length: " + a5.s().v());
    }

    public f9(boolean z) {
        this.f5252a = z;
    }
}
