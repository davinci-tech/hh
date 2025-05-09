package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.f0;
import com.huawei.hms.network.inner.DomainManager;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public class g0 {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5255a = "DNResolverManager";
    public static Set<String> b = Collections.synchronizedSet(new HashSet());
    public static final r0 c = r0.a();

    public static void a(String str, String str2, int i) {
        if (DomainManager.getInstance().isExcludePrefetch(str)) {
            Logger.v(f5255a, "exclude dnsLazyUpdate");
        } else {
            if (TextUtils.isEmpty(str) || b.contains(str)) {
                return;
            }
            b.add(str);
            Logger.i(f5255a, "enter Dns lazy update flow, domain: %s, trigger type: %s, source: %s", str, str2, t.m().a(i));
            c.a(i == 3 ? new b(str, i, str2) : new k0(str, str2, new a()));
        }
    }

    public static class a implements f0.a {
        @Override // com.huawei.hms.network.embedded.f0.a
        public void a(String str, Throwable th) {
            Logger.v(g0.f5255a, "lazyUpdateCallback onFailure");
            g0.b.remove(str);
        }

        @Override // com.huawei.hms.network.embedded.f0.a
        public void a(String str, m0 m0Var) {
            Logger.v(g0.f5255a, "lazyUpdateCallback onRespone : " + m0Var);
            a0.a(str, m0Var);
            g0.b.remove(str);
        }
    }

    public static m0 a(String str, int i) throws UnknownHostException {
        m0 a2 = i != 1 ? c.a(str) : h0.c.lookup(str);
        Logger.i(f5255a, str + " from server result is: " + a2);
        return a2;
    }

    public static class b extends f0 {
        @Override // com.huawei.hms.network.embedded.f0
        public m0 c() {
            m0 b = g0.c.b(this.f5237a);
            g0.b.remove(this.f5237a);
            return b;
        }

        public b(String str, int i, String str2) {
            super(str, i, str2);
        }
    }

    public static m0 a(f0 f0Var) {
        f0Var.run();
        return f0Var.a();
    }
}
