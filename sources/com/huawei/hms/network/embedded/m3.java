package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;

/* loaded from: classes9.dex */
public class m3 extends f3 {
    public static final String c = "OnlyConnectCall";

    public class a implements n7 {
        @Override // com.huawei.hms.network.embedded.n7
        public v7 intercept(n7.a aVar) throws IOException {
            m3.this.exchange = ((k9) aVar).e();
            return new v7.a().a(m3.this.request).a(200).a(r7.HTTP_1_1).a("connect success").a(w7.a(o7.a("text/plain; charset=UTF-8"), "connect success")).a();
        }

        public a() {
        }
    }

    @Override // com.huawei.hms.network.embedded.f3
    /* renamed from: clone */
    public t6 mo630clone() {
        return new m3(this.client, this.request);
    }

    @Override // com.huawei.hms.network.embedded.f3
    public void addResponseInterceptor() {
        this.interceptors.add(new a());
    }

    public m3(q7 q7Var, t7 t7Var) {
        super(q7Var, t7Var);
    }
}
