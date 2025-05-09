package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.util.concurrent.ExecutorService;

/* loaded from: classes9.dex */
public abstract class v5 {
    public static final String d = "IQuery";
    public ExecutorService b;

    /* renamed from: a, reason: collision with root package name */
    public w5 f5539a = new w5();
    public i5 c = i5.e;

    public abstract w5 b(i5 i5Var);

    public final v5 a(v5 v5Var) {
        if (this.f5539a.c()) {
            this.f5539a.b(false);
            v5Var.f5539a = this.f5539a;
            v5Var.a();
        } else {
            Logger.i(d, "the next quary will not execute!");
        }
        return this;
    }

    public final v5 a(i5 i5Var) {
        this.c = i5Var;
        return this;
    }

    public final v5 a() {
        this.c.b(this.f5539a);
        w5 b = b(this.c);
        this.f5539a = b;
        if (b == null) {
            this.f5539a = new w5();
        }
        this.c.a(this.f5539a);
        return this;
    }
}
