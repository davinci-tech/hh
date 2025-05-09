package com.huawei.maps.offlinedata.service.cloud;

import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.maps.offlinedata.logpush.dto.b;
import com.huawei.maps.offlinedata.utils.g;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile GrsClient f6469a;
    private static volatile d b;
    private String c;
    private String d;

    private d() {
        d();
    }

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    private void d() {
        String a2 = com.huawei.maps.offlinedata.service.cloud.utils.c.a();
        g.a("GrsUtil", "initGrs countryCode:" + a2);
        f6469a = new GrsClient(com.huawei.maps.offlinedata.utils.a.a(), c(a2));
    }

    public String b() {
        if (TextUtils.isEmpty(this.c)) {
            Map<String, String> a2 = a("commonService.maps.offlinedata");
            g.c("GrsUtil", "getServerAddress: " + a2.toString());
            if (!a2.isEmpty()) {
                String str = a2.get("ROOT");
                if (!TextUtils.isEmpty(str)) {
                    this.c = str;
                } else {
                    g.c("GrsUtil", "query url from grs failed, root is empty.");
                }
            } else {
                g.c("GrsUtil", "query url from grs failed, url is empty.");
            }
            g.a("GrsUtil", "Address: " + this.c);
        }
        return this.c;
    }

    public String c() {
        if (TextUtils.isEmpty(this.d)) {
            Map<String, String> a2 = a("commonService.maps.offlinedata");
            if (a2.isEmpty()) {
                g.c("GrsUtil", "query hianalytics from grs failed, url is empty.");
                return this.d;
            }
            if (!TextUtils.isEmpty(a2.get("HIANALYTICS"))) {
                this.d = a2.get("HIANALYTICS");
            } else {
                g.c("GrsUtil", "query hianalytics from grs failed, url is empty.");
            }
            g.a("GrsUtil", "HiAnalytics Address: " + this.d);
        }
        return this.d;
    }

    public Map<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query from grs serviceName must not be null.");
        }
        Map<String, String> synGetGrsUrls = f6469a.synGetGrsUrls(str);
        if (synGetGrsUrls != null && !synGetGrsUrls.isEmpty()) {
            return synGetGrsUrls;
        }
        b(str);
        return new HashMap();
    }

    public void b(String str) {
        g.d("GrsUtil", "GRS returns empty.");
        if (str.equals("com.huawei.cloud.hianalytics")) {
            return;
        }
        com.huawei.maps.offlinedata.logpush.c.a(new b.a().a("GET_GRS_URL_FAILED").b("GRS returns empty, service name is " + str).a());
    }

    private GrsBaseInfo c(String str) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        String d = d(str);
        if (d.equals("AA") || d.equals("UNKNOWN")) {
            grsBaseInfo.setSerCountry(null);
        } else {
            grsBaseInfo.setSerCountry(d);
        }
        return grsBaseInfo;
    }

    private String d(String str) {
        return str != null ? str.replaceAll("\\s", "") : "";
    }
}
