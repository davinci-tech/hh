package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.q0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class n0 {
    public static final String c = "HttpDnsClient";
    public static final String d = "?type=ALL&domains=";
    public static final String e = "/v1/";
    public static final String f = "/batch-resolve";

    /* renamed from: a, reason: collision with root package name */
    public String f5382a;
    public final q0 b = new q0(a());

    public o0 b() {
        return o0.e();
    }

    public ArrayList<m0> a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        list.remove(o0.e().b());
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        q0.a a2 = this.b.a();
        return (a2 == null || !a2.a()) ? this.b.a(list) : new ArrayList<>();
    }

    public final String a() {
        String str;
        if (!TextUtils.isEmpty(this.f5382a)) {
            return this.f5382a;
        }
        String c2 = o0.e().c();
        String d2 = o0.e().d();
        if (TextUtils.isEmpty(c2) || TextUtils.isEmpty(d2)) {
            str = "";
        } else {
            str = c2 + e + d2 + "/batch-resolve?type=ALL&domains=";
        }
        this.f5382a = str;
        return str;
    }

    public m0 a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new m0();
        }
        q0.a a2 = this.b.a();
        if (a2 != null && a2.a()) {
            Logger.w("HttpDnsClient", "HttpDns server is retry-after");
            return new m0();
        }
        if (str.equals(o0.e().b())) {
            return b(str);
        }
        m0 a3 = this.b.a(str);
        a3.b(3);
        a3.a(0);
        return a3;
    }

    private m0 b(String str) {
        m0 a2 = a0.a(str);
        return y.b(a2) ? h0.b.lookup(str) : a2;
    }
}
