package com.huawei.hms.hwid;

import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.result.AuthAccount;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static final g f4647a = new g();
    private Map<String, String> b = new ConcurrentHashMap();

    private g() {
    }

    public static g a() {
        return f4647a;
    }

    public void a(AuthAccount authAccount, AccountAuthParams accountAuthParams) {
        String json;
        as.b("[AccountSDK]AccountAuthMemCache", "saveDefaultAccountSignInAccount start.", true);
        if (authAccount != null) {
            try {
                json = authAccount.toJson();
            } catch (Throwable th) {
                as.d("[AccountSDK]AccountAuthMemCache", "store faild, exception:" + th.getClass().getSimpleName(), true);
                return;
            }
        } else {
            json = null;
        }
        a(json, accountAuthParams != null ? accountAuthParams.toJson() : null);
    }

    private void a(String str, String str2) {
        as.b("[AccountSDK]AccountAuthMemCache", "saveDefaultAccountSignInAccount start.", true);
        this.b.remove("AccountAuth");
        this.b.remove("AccountAuthParams");
        if (str != null) {
            this.b.put("AccountAuth", str);
        }
        if (str2 != null) {
            this.b.put("AccountAuthParams", str2);
        }
    }

    public final AuthAccount b() {
        as.b("[AccountSDK]AccountAuthMemCache", "getSignInAccount start.", true);
        try {
            String str = this.b.get("AccountAuth");
            if (str != null) {
                return AuthAccount.fromJson(str);
            }
        } catch (Throwable th) {
            as.d("[AccountSDK]AccountAuthMemCache", "getSignInAccount faild, exception:" + th.getClass().getSimpleName(), true);
        }
        return null;
    }

    public void c() {
        this.b.clear();
    }
}
