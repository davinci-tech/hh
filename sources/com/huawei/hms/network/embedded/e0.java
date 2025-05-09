package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.f0;
import com.huawei.hms.network.embedded.t;
import java.util.List;

/* loaded from: classes9.dex */
public class e0 extends f0 {
    public static final String h = "DNKeeperResolver";

    @Override // com.huawei.hms.network.embedded.f0
    public m0 c() {
        String str;
        int i;
        Logger.v(h, "Resolve to DNKeeper, host: %s", this.f5237a);
        m0 m0Var = new m0();
        l0 c = t.m().c();
        if (c != null) {
            t.i b = t.m().b(this.f5237a);
            if (b != null) {
                str = b.b();
                i = b.a();
            } else {
                str = null;
                i = 0;
            }
            m0Var = c.a(this.f5237a, str, i);
            m0Var.b(1);
            t.m().a(this.f5237a);
        }
        if (y.b(m0Var)) {
            Logger.w(h, "Resolve from DNKeeper is null, host: %s", this.f5237a);
            return m0Var;
        }
        List<String> d = m0Var.d();
        if (!d.isEmpty()) {
            m0Var.b(d);
        }
        Logger.v(h, this.f5237a + " Resolve to DNKeeper, result: " + m0Var);
        return m0Var;
    }

    public e0(String str, f0.a aVar) {
        super(str, 1, "dns_sync_query", aVar);
    }
}
