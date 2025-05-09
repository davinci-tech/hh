package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.f0;

/* loaded from: classes9.dex */
public class j0 extends f0 {
    public static final String h = "HttpDnsResolver";

    @Override // com.huawei.hms.network.embedded.f0
    public m0 c() {
        m0 m0Var = new m0();
        n0 e = t.m().e();
        if (e != null) {
            m0Var = e.a(this.f5237a);
        }
        if (y.b(m0Var)) {
            Logger.w(h, "Resolve from HttpDns is null, host: %s", this.f5237a);
        }
        return m0Var;
    }

    public j0(String str, String str2, f0.a aVar) {
        super(str, 3, str2, aVar);
    }
}
