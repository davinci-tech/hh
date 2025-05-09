package com.autonavi.aps.amapapi.trans;

import android.content.Context;
import com.amap.api.col.p0003sl.hn;
import com.amap.api.col.p0003sl.hq;
import com.amap.api.col.p0003sl.ht;
import com.amap.api.col.p0003sl.hy;
import com.amap.api.col.p0003sl.ia;
import com.amap.api.col.p0003sl.jt;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.col.p0003sl.kb;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class c {
    private static c b;

    /* renamed from: a, reason: collision with root package name */
    jt f1642a;
    private Context c;
    private int d = com.autonavi.aps.amapapi.utils.b.i;
    private boolean e = false;
    private int f = 0;

    private c(Context context) {
        this.f1642a = null;
        this.c = null;
        try {
            ht.a().a(context);
        } catch (Throwable unused) {
        }
        this.c = context;
        this.f1642a = jt.a();
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    public final void a(long j, boolean z, int i) {
        try {
            this.e = z;
            this.d = Long.valueOf(j).intValue();
            this.f = i;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "LocNetManager", "setOption");
        }
    }

    public final d a(Context context, byte[] bArr, String str, String str2, boolean z) {
        try {
            HashMap hashMap = new HashMap(16);
            d dVar = new d(context, com.autonavi.aps.amapapi.utils.b.c());
            try {
                hashMap.put("Content-Type", FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
                hashMap.put(j2.v, Constants.GZIP);
                hashMap.put("gzipped", "1");
                hashMap.put("Connection", "Keep-Alive");
                hashMap.put("User-Agent", "AMAP_Location_SDK_Android 6.1.0");
                hashMap.put("KEY", hn.f(context));
                hashMap.put("enginever", com.autonavi.aps.amapapi.utils.b.f1647a);
                String a2 = hq.a();
                String a3 = hq.a(context, a2, "key=" + hn.f(context));
                hashMap.put("ts", a2);
                hashMap.put("scode", a3);
                if (Double.valueOf(com.autonavi.aps.amapapi.utils.b.f1647a).doubleValue() >= 5.3d) {
                    hashMap.put("aps_s_src", "openapi");
                }
                hashMap.put("encr", "1");
                dVar.b(hashMap);
                String str3 = !z ? "locf" : "loc";
                dVar.b(true);
                dVar.a(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "6.1.0", str3, 3));
                dVar.a(z);
                dVar.b(str);
                dVar.c(str2);
                dVar.c(i.a(bArr));
                dVar.setProxy(hy.a(context));
                HashMap hashMap2 = new HashMap(16);
                hashMap2.put("output", "bin");
                hashMap2.put("policy", "3103");
                int i = this.f;
                if (i == 0) {
                    hashMap2.remove("custom");
                } else if (i == 1) {
                    hashMap2.put("custom", "language:cn");
                } else if (i == 2) {
                    hashMap2.put("custom", "language:en");
                } else {
                    hashMap2.remove("custom");
                }
                dVar.a(hashMap2);
                dVar.setConnectionTimeout(this.d);
                dVar.setSoTimeout(this.d);
                if (!this.e) {
                    return dVar;
                }
                dVar.setHttpProtocol(ka.c.HTTPS);
                return dVar;
            } catch (Throwable unused) {
                return dVar;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    public final kb a(d dVar) throws Throwable {
        if (this.e) {
            dVar.setHttpProtocol(ka.c.HTTPS);
        }
        return jt.a(dVar);
    }

    public final String a(Context context, double d, double d2) {
        try {
            HashMap hashMap = new HashMap(16);
            d dVar = new d(context, com.autonavi.aps.amapapi.utils.b.c());
            hashMap.clear();
            hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
            hashMap.put("Connection", "Keep-Alive");
            hashMap.put("User-Agent", "AMAP_Location_SDK_Android 6.1.0");
            HashMap hashMap2 = new HashMap(16);
            hashMap2.put("custom", "26260A1F00020002");
            hashMap2.put(MedalConstants.EVENT_KEY, hn.f(context));
            int i = this.f;
            if (i == 0) {
                hashMap2.remove("language");
            } else if (i == 1) {
                hashMap2.put("language", "zh-CN");
            } else if (i == 2) {
                hashMap2.put("language", "en");
            } else {
                hashMap2.remove("language");
            }
            hashMap2.put("curLocationType", i.m(this.c) ? "coarseLoc" : "fineLoc");
            String a2 = hq.a();
            String a3 = hq.a(context, a2, ia.b(hashMap2));
            hashMap2.put("ts", a2);
            hashMap2.put("scode", a3);
            dVar.b(("output=json&radius=1000&extensions=all&location=" + d2 + "," + d).getBytes("UTF-8"));
            dVar.b(false);
            dVar.a(true);
            dVar.a(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "6.1.0", "loc", 3));
            dVar.a(hashMap2);
            dVar.b(hashMap);
            dVar.setProxy(hy.a(context));
            dVar.setConnectionTimeout(com.autonavi.aps.amapapi.utils.b.i);
            dVar.setSoTimeout(com.autonavi.aps.amapapi.utils.b.i);
            try {
                dVar.c("http://dualstack-arestapi.amap.com/v3/geocode/regeo");
                dVar.b("http://restsdk.amap.com/v3/geocode/regeo");
                if (this.e) {
                    dVar.setHttpProtocol(ka.c.HTTPS);
                }
                return new String(jt.a(dVar).f1250a, "utf-8");
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "LocNetManager", "post");
                return null;
            }
        } catch (Throwable unused) {
        }
    }
}
