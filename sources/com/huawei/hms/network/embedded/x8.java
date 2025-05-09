package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;

/* loaded from: classes9.dex */
public final class x8 implements n7 {

    /* renamed from: a, reason: collision with root package name */
    public final q7 f5571a;

    @Override // com.huawei.hms.network.embedded.n7
    public v7 intercept(n7.a aVar) throws IOException {
        k9 k9Var = (k9) aVar;
        t7 request = k9Var.request();
        k9Var.f().prepareToConnect(request);
        k9Var.f().getExchangeFinder().a(this.f5571a, request);
        return new v7.a().a(r7.HTTP_2).a("").a(new l9("", 1L, null)).a(200).a(request).a();
    }

    public x8(q7 q7Var) {
        this.f5571a = q7Var;
    }
}
