package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.t7;
import com.huawei.hms.network.embedded.v7;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOException;
import java.util.List;

/* loaded from: classes9.dex */
public final class e9 implements n7 {

    /* renamed from: a, reason: collision with root package name */
    public final c7 f5233a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        t7 request = aVar.request();
        t7.a i = request.i();
        u7 b = request.b();
        if (b != null) {
            o7 contentType = b.contentType();
            if (contentType != null) {
                i.b("Content-Type", contentType.toString());
            }
            long contentLength = b.contentLength();
            if (contentLength != -1) {
                i.b("Content-Length", Long.toString(contentLength));
                i.b("Transfer-Encoding");
            } else {
                i.b("Transfer-Encoding", "chunked");
                i.b("Content-Length");
            }
        }
        boolean z = false;
        if (request.a("Host") == null) {
            i.b("Host", f8.a(request.k(), false));
        }
        if (request.a("Connection") == null) {
            i.b("Connection", "Keep-Alive");
        }
        if (request.a(j2.v) == null && request.a("Range") == null) {
            i.b(j2.v, Constants.GZIP);
            z = true;
        }
        List<b7> a2 = this.f5233a.a(request.k());
        if (!a2.isEmpty()) {
            i.b("Cookie", a(a2));
        }
        if (request.a("User-Agent") == null) {
            i.b("User-Agent", g8.a());
        }
        v7 a3 = aVar.a(i.a());
        i9.a(this.f5233a, request.k(), a3.y());
        v7.a a4 = a3.D().a(request);
        if (z && Constants.GZIP.equalsIgnoreCase(a3.b("Content-Encoding")) && i9.b(a3)) {
            kb kbVar = new kb(a3.s().x());
            a4.a(a3.y().c().d("Content-Encoding").d("Content-Length").a());
            a4.a(new l9(a3.b("Content-Type"), -1L, ob.a(kbVar)));
        }
        return a4.a();
    }

    private String a(List<b7> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            b7 b7Var = list.get(i);
            sb.append(b7Var.e());
            sb.append('=');
            sb.append(b7Var.i());
        }
        return sb.toString();
    }

    public e9(c7 c7Var) {
        this.f5233a = c7Var;
    }
}
