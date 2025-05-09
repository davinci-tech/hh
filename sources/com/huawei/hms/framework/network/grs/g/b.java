package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final Context f4540a;
    private final GrsBaseInfo b;
    private final com.huawei.hms.framework.network.grs.e.a c;

    /* JADX WARN: Removed duplicated region for block: B:15:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(boolean r13) {
        /*
            r12 = this;
            com.huawei.hms.framework.network.grs.e.a r0 = r12.c
            com.huawei.hms.framework.network.grs.e.c r0 = r0.a()
            java.lang.String r1 = "geoipCountryCode"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.a(r1, r2)
            java.lang.String r1 = "geoip.countrycode"
            java.util.Map r0 = com.huawei.hms.framework.network.grs.a.a(r0, r1)
            java.lang.String r3 = "ROOT"
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "geoIpCountry is: "
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "GeoipCountry"
            com.huawei.hms.framework.common.Logger.i(r5, r4)
            com.huawei.hms.framework.network.grs.e.a r4 = r12.c
            com.huawei.hms.framework.network.grs.e.c r4 = r4.a()
            java.lang.String r6 = "geoipCountryCodetime"
            java.lang.String r7 = "0"
            java.lang.String r4 = r4.a(r6, r7)
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L56
            java.lang.String r6 = "\\d+"
            boolean r6 = r4.matches(r6)
            if (r6 == 0) goto L56
            long r6 = java.lang.Long.parseLong(r4)     // Catch: java.lang.NumberFormatException -> L50
            goto L58
        L50:
            r4 = move-exception
            java.lang.String r6 = "convert urlParamKey from String to Long catch NumberFormatException."
            com.huawei.hms.framework.common.Logger.w(r5, r6, r4)
        L56:
            r6 = 0
        L58:
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto L68
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            boolean r4 = com.huawei.hms.framework.network.grs.h.e.a(r4)
            if (r4 == 0) goto Le9
        L68:
            com.huawei.hms.framework.network.grs.g.j.c r7 = new com.huawei.hms.framework.network.grs.g.j.c
            com.huawei.hms.framework.network.grs.GrsBaseInfo r4 = r12.b
            android.content.Context r6 = r12.f4540a
            r7.<init>(r4, r6)
            r7.a(r1)
            com.huawei.hms.framework.network.grs.e.a r4 = r12.c
            com.huawei.hms.framework.network.grs.e.c r10 = r4.c()
            if (r10 == 0) goto La7
            java.lang.String r4 = "services"
            java.lang.String r2 = r10.a(r4, r2)
            java.lang.String r6 = r7.c()     // Catch: org.json.JSONException -> L8b
            java.lang.String r2 = com.huawei.hms.framework.network.grs.g.h.a(r2, r6)     // Catch: org.json.JSONException -> L8b
            goto L9e
        L8b:
            r2 = move-exception
            java.lang.String r2 = r2.getMessage()
            java.lang.String r2 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r2)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r6 = "getGeoipCountry merge services occure jsonException. %s"
            com.huawei.hms.framework.common.Logger.w(r5, r6, r2)
            r2 = 0
        L9e:
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 != 0) goto La7
            r10.b(r4, r2)
        La7:
            if (r13 == 0) goto Ld7
            com.huawei.hms.framework.network.grs.e.a r13 = r12.c
            com.huawei.hms.framework.network.grs.g.g r13 = r13.b()
            r2 = -1
            com.huawei.hms.framework.network.grs.g.d r13 = r13.a(r7, r1, r10, r2)
            if (r13 == 0) goto Lc5
            java.lang.String r13 = r13.j()
            java.util.Map r13 = com.huawei.hms.framework.network.grs.a.a(r13, r1)
            java.lang.Object r13 = r13.get(r3)
            java.lang.String r13 = (java.lang.String) r13
            r0 = r13
        Lc5:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r1 = "sync request to query geoip.countrycode is:"
            r13.<init>(r1)
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            com.huawei.hms.framework.common.Logger.i(r5, r13)
            goto Le9
        Ld7:
            java.lang.String r13 = "async request to query geoip.countrycode"
            com.huawei.hms.framework.common.Logger.i(r5, r13)
            com.huawei.hms.framework.network.grs.e.a r13 = r12.c
            com.huawei.hms.framework.network.grs.g.g r6 = r13.b()
            r8 = 0
            java.lang.String r9 = "geoip.countrycode"
            r11 = -1
            r6.a(r7, r8, r9, r10, r11)
        Le9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.b.a(boolean):java.lang.String");
    }

    public b(Context context, com.huawei.hms.framework.network.grs.e.a aVar, GrsBaseInfo grsBaseInfo) {
        this.f4540a = context;
        this.b = grsBaseInfo;
        this.c = aVar;
    }
}
