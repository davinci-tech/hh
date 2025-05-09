package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class e3 {
    public static final String c = "ConnectionInfo";

    /* renamed from: a, reason: collision with root package name */
    public String f5226a;
    public WeakReference<y8> b;

    public boolean isHealthy(boolean z) {
        y8 y8Var;
        WeakReference<y8> weakReference = this.b;
        if (weakReference == null || (y8Var = weakReference.get()) == null) {
            return false;
        }
        if (y8Var.a(z)) {
            Logger.v(c, "the host is : %s,and the connection is healthy!", this.f5226a);
            return true;
        }
        Logger.v(c, "the host is : %s,but the connection is unhealthy!", this.f5226a);
        return false;
    }

    public String getHost() {
        return this.f5226a;
    }

    public e3(String str, y8 y8Var) {
        this.f5226a = str;
        this.b = new WeakReference<>(y8Var);
    }
}
