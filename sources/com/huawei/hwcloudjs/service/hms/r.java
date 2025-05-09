package com.huawei.hwcloudjs.service.hms;

import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    private static r f6256a = new r();
    private final Map<String, Map<String, HuaweiApiClient>> b = new HashMap();
    private final Map<String, Map<String, HuaweiIdAuthService>> c = new HashMap();

    public void b(String str) {
        synchronized (this.b) {
            Map<String, HuaweiApiClient> remove = this.b.remove(str);
            if (remove != null) {
                Iterator<Map.Entry<String, HuaweiApiClient>> it = remove.entrySet().iterator();
                while (it.hasNext()) {
                    it.next().getValue().disconnect();
                }
            }
        }
    }

    public HuaweiApiClient b(String str, String str2) {
        synchronized (this.b) {
            Map<String, HuaweiApiClient> map = this.b.get(str);
            if (map == null) {
                return null;
            }
            return map.get(str2);
        }
    }

    public void a(String str, String str2, HuaweiIdAuthService huaweiIdAuthService) {
        synchronized (this.c) {
            Map<String, HuaweiIdAuthService> map = this.c.get(str);
            if (map == null) {
                map = new HashMap<>();
                this.c.put(str, map);
            }
            map.put(str2, huaweiIdAuthService);
        }
    }

    public void a(String str, String str2, HuaweiApiClient huaweiApiClient) {
        synchronized (this.b) {
            Map<String, HuaweiApiClient> map = this.b.get(str);
            if (map == null) {
                map = new HashMap<>();
                this.b.put(str, map);
            }
            map.put(str2, huaweiApiClient);
        }
    }

    public void a(String str) {
        synchronized (this.c) {
            this.c.remove(str);
        }
    }

    public HuaweiIdAuthService a(String str, String str2) {
        synchronized (this.c) {
            Map<String, HuaweiIdAuthService> map = this.c.get(str);
            if (map == null) {
                return null;
            }
            return map.get(str2);
        }
    }

    public static r a() {
        return f6256a;
    }

    private r() {
    }
}
