package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Request;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class s3 {
    public static final String b = "HostRoute";
    public static volatile s3 c = null;
    public static final int d = 1000;
    public static final int e = 500;
    public static final int f = 400;
    public static final int g = 3;

    /* renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, a> f5470a = new ConcurrentHashMap<>();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f5471a = 500;
        public int b = 1;
    }

    public void a(long j, String str) {
        int i;
        if (j <= 0 || str == null) {
            return;
        }
        if (this.f5470a.get(str) == null) {
            this.f5470a.putIfAbsent(str, new a());
        }
        a aVar = this.f5470a.get(str);
        if (j > 400) {
            aVar.f5471a = 1000;
            aVar.b = 1;
        } else if (aVar.f5471a == 1000 && (i = aVar.b) < 3) {
            aVar.b = i + 1;
        } else {
            aVar.b = 1;
            aVar.f5471a = 500;
        }
    }

    public Request a(h1.d dVar) {
        a aVar = this.f5470a.get(new d3(dVar.getUrl()).getHost());
        if (aVar == null || aVar.f5471a == dVar.getNetConfig().getConcurrentConnectDelay()) {
            Logger.v(b, "request not change: ");
            return dVar;
        }
        Logger.v(b, "  old delay time = " + dVar.getNetConfig().getConcurrentConnectDelay() + " new delay time " + aVar.f5471a);
        dVar.getNetConfig().setValue("core_concurrent_connect_delay", aVar.f5471a);
        return dVar;
    }

    public static s3 getInstance() {
        if (c == null) {
            synchronized (s3.class) {
                if (c == null) {
                    c = new s3();
                }
            }
        }
        return c;
    }
}
