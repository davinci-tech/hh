package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.openalliance.ad.dy;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.lb;
import com.huawei.openalliance.ad.net.http.f;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cy;
import com.huawei.openalliance.ad.utils.o;
import java.io.InputStream;

/* loaded from: classes5.dex */
public abstract class b implements h {

    /* renamed from: a, reason: collision with root package name */
    protected Context f7301a;

    protected String c(a aVar) {
        if (aVar == null) {
            return null;
        }
        return a(aVar) ? FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM : aVar.g;
    }

    protected String b(a aVar) {
        return (aVar != null && aVar.l == 2) ? "fb" : "json";
    }

    protected boolean a(a aVar) {
        if (aVar == null) {
            return false;
        }
        return aVar.l == 1 || aVar.l == 2;
    }

    protected String a(e eVar, a aVar) {
        return aVar.a() ? aVar.b() : new f.a().a(eVar.h, aVar.c()).a(aVar.i).a().c();
    }

    protected long a(e eVar, a aVar, int i, InputStream inputStream, long j, Response response) {
        if (!a(aVar)) {
            lb a2 = aVar.m != null ? aVar.m : lb.a.a(aVar.b);
            response.a((Response) a2.a(i, inputStream, j, eVar.f));
            if (ho.a()) {
                ho.a("BaseCaller", "normal rsp: %s", be.b(response));
            }
            return a2.a();
        }
        byte[] b = cy.b(inputStream);
        long currentTimeMillis = System.currentTimeMillis();
        if (ho.a()) {
            ho.a("BaseCaller", "contentLength: %s, rsp fb data: %s", Long.valueOf(j), new String(b, "UTF-8"));
            ho.a("BaseCaller", "content base64: %s", o.a(b));
        }
        response.a((Response) dy.a(b));
        response.d(aVar.l);
        return currentTimeMillis;
    }

    public b(Context context) {
        this.f7301a = context;
    }
}
