package com.huawei.hms.hwid;

import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    private static final y f4652a = new y();
    private Map<String, String> b = new ConcurrentHashMap();

    private y() {
    }

    public static y a() {
        return f4652a;
    }

    public void a(AuthHuaweiId authHuaweiId, HuaweiIdAuthParams huaweiIdAuthParams) {
        String json;
        as.b("[HUAWEIIDSDK]HuaweiIdAuthMemCache", "saveDefaultHuaweiIdSignInAccount start.", true);
        if (authHuaweiId != null) {
            try {
                json = authHuaweiId.toJson();
            } catch (Throwable th) {
                as.d("[HUAWEIIDSDK]HuaweiIdAuthMemCache", "store faild, exception:" + th.getClass().getSimpleName(), true);
                return;
            }
        } else {
            json = null;
        }
        a(json, huaweiIdAuthParams != null ? huaweiIdAuthParams.toJson() : null);
    }

    private void a(String str, String str2) {
        as.b("[HUAWEIIDSDK]HuaweiIdAuthMemCache", "saveDefaultHuaweiIdSignInAccount start.", true);
        this.b.remove("HuaweiIdAccount");
        this.b.remove("HuaweiIdAuthParams");
        if (str != null) {
            this.b.put("HuaweiIdAccount", str);
        }
        if (str2 != null) {
            this.b.put("HuaweiIdAuthParams", str2);
        }
    }

    public final AuthHuaweiId b() {
        try {
            String str = this.b.get("HuaweiIdAccount");
            if (str != null) {
                return AuthHuaweiId.fromJson(str);
            }
        } catch (Throwable th) {
            as.d("[HUAWEIIDSDK]HuaweiIdAuthMemCache", "getHuaweiSignInAccount faild, exception:" + th.getClass().getSimpleName(), true);
        }
        return null;
    }

    public void c() {
        this.b.clear();
    }
}
