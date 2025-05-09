package com.huawei.maps.offlinedata.service.cloud;

import com.huawei.maps.offlinedata.service.cloud.dto.AuthResponseDTO;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6464a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g = true;

    public static a a() {
        if (f6464a == null) {
            synchronized (a.class) {
                if (f6464a == null) {
                    f6464a = new a();
                }
            }
        }
        return f6464a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        AuthResponseDTO b = c.a().b();
        if (b == null) {
            return false;
        }
        this.d = b.getP();
        this.b = b.getPoliticalView();
        this.c = b.getReviewNumberOfWatchMap();
        this.e = b.getWatchMapVersion();
        this.f = b.getWatchMapContourVersion();
        Boolean enableAutoUpdates = b.getEnableAutoUpdates();
        if (enableAutoUpdates == null) {
            g.c("AuthClient", "the cloud don't has field: enableAutoUpdates.");
            enableAutoUpdates = true;
        }
        this.g = enableAutoUpdates.booleanValue();
        g.a("AuthClient", "response body p:" + this.d);
        g.a("AuthClient", "response body reviewNumberOfWatchMap:" + this.c);
        return true;
    }
}
