package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import java.io.IOException;

/* loaded from: classes9.dex */
public final class t8 implements n7 {

    /* renamed from: a, reason: collision with root package name */
    public final q7 f5502a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        k9 k9Var = (k9) aVar;
        t7 request = k9Var.request();
        d9 f = k9Var.f();
        return k9Var.a(request, f, f.newExchange(aVar, !request.h().equals("GET")));
    }

    public t8(q7 q7Var) {
        this.f5502a = q7Var;
    }
}
