package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.constant.FlavorConstants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ct {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7668a = new byte[0];
    private static ct b;
    private String c;
    private String d;
    private String e;

    public void a(Context context, boolean z) {
    }

    public void d(String str) {
        if (v.a()) {
            v.a(str);
        }
    }

    public void c(String str) {
        this.c = str;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public void b(Context context) {
        if (cz.b(this.c)) {
            ho.c("ServerConfig", "asyncGetUrl, appNameBlank");
        } else {
            ho.b("ServerConfig", "asyncGetUrl");
            m.b(new a(context, this.c));
        }
    }

    public String b() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(Context context) {
        ho.b("ServerConfig", "init");
        m.b(new a(context, this.c));
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final Context f7669a;
        private final String b;

        @Override // java.lang.Runnable
        public void run() {
            if (this.b == null) {
                ho.d("ServerConfig", "Must set application name for GRS: HiAd.setApplicationName");
                return;
            }
            String o = com.huawei.openalliance.ad.bz.a(this.f7669a).o();
            com.huawei.openalliance.ad.cp a2 = com.huawei.openalliance.ad.ca.a(this.f7669a);
            a2.a(this.b);
            a2.b(o);
            ho.b("ServerConfig", "country:" + o);
            Map<String, String> c = a2.c(FlavorConstants.HIAD_GRS_SERVICE_NAME);
            HashMap hashMap = new HashMap();
            if (c != null && !c.isEmpty()) {
                for (Map.Entry<String, String> entry : c.entrySet()) {
                    String c2 = ao.c(entry.getValue());
                    hashMap.put(entry.getKey() + o, c2);
                    StringBuilder sb = new StringBuilder("url:");
                    sb.append(dl.a(c2));
                    ho.b("ServerConfig", sb.toString());
                }
                fh.b(this.f7669a).a(hashMap);
            }
            if (v.a()) {
                v.a(this.f7669a);
            }
        }

        public a(Context context, String str) {
            this.f7669a = context;
            this.b = str;
        }
    }

    public static ct a() {
        ct ctVar;
        synchronized (f7668a) {
            if (b == null) {
                b = new ct();
            }
            ctVar = b;
        }
        return ctVar;
    }

    private ct() {
    }
}
