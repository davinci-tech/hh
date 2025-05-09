package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AdConfigMapKey;
import com.huawei.openalliance.ad.constant.SplashPreloadMode;
import com.huawei.openalliance.ad.ho;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class cf {
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        if (com.huawei.openalliance.ad.constant.SplashPreloadMode.OPTIONAL_VALUE.contains(r1) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> b(com.huawei.openalliance.ad.beans.server.AdContentRsp r4) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            if (r4 != 0) goto L8
            return r0
        L8:
            java.util.List r4 = r4.f()
            boolean r1 = com.huawei.openalliance.ad.utils.bg.a(r4)
            if (r1 != 0) goto L50
            java.util.Iterator r4 = r4.iterator()
        L16:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L50
            java.lang.Object r1 = r4.next()
            com.huawei.openalliance.ad.beans.metadata.Precontent r1 = (com.huawei.openalliance.ad.beans.metadata.Precontent) r1
            if (r1 != 0) goto L25
            goto L16
        L25:
            java.lang.String r2 = r1.a()
            boolean r3 = com.huawei.openalliance.ad.utils.cz.b(r2)
            if (r3 == 0) goto L37
            java.lang.String r1 = "PreloadUtil"
            java.lang.String r2 = "empty preload slot id"
            com.huawei.openalliance.ad.ho.a(r1, r2)
            goto L16
        L37:
            java.lang.Integer r1 = r1.n()
            if (r1 == 0) goto L4a
            java.lang.String r1 = com.huawei.openalliance.ad.utils.cz.a(r1)
            java.util.List<java.lang.String> r3 = com.huawei.openalliance.ad.constant.SplashPreloadMode.OPTIONAL_VALUE
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L4a
            goto L4c
        L4a:
            java.lang.String r1 = "1"
        L4c:
            r0.put(r2, r1)
            goto L16
        L50:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.cf.b(com.huawei.openalliance.ad.beans.server.AdContentRsp):java.util.Map");
    }

    public static Map<String, String> a(AdContentRsp adContentRsp) {
        String str;
        HashMap hashMap = new HashMap();
        if (adContentRsp == null) {
            return hashMap;
        }
        List<Ad30> c = adContentRsp.c();
        if (!bg.a(c)) {
            for (Ad30 ad30 : c) {
                if (ad30 != null) {
                    String a2 = ad30.a();
                    if (cz.b(a2)) {
                        str = "empty slot id";
                    } else {
                        String g = ad30.g();
                        if (cz.b(g)) {
                            str = "empty config map";
                        } else {
                            Map map = (Map) be.b(g, Map.class, new Class[0]);
                            if (!bl.a(map)) {
                                String str2 = (String) map.get(AdConfigMapKey.SPLASH_PRELOAD_MODE);
                                if (cz.b(str2) || !SplashPreloadMode.OPTIONAL_VALUE.contains(str2)) {
                                    str2 = "1";
                                }
                                hashMap.put(a2, str2);
                            }
                        }
                    }
                    ho.a("PreloadUtil", str);
                }
            }
        }
        return hashMap;
    }
}
