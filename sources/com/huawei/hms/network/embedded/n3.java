package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class n3 extends f3 {
    public static final String c = "SiteConnectCall";
    public static final z6 d = new z6(0, 1, TimeUnit.MILLISECONDS);

    @Override // com.huawei.hms.network.embedded.f3, com.huawei.hms.network.embedded.t6
    public t7 request() {
        return this.request;
    }

    @Override // com.huawei.hms.network.embedded.f3
    /* renamed from: clone */
    public t6 mo630clone() {
        return new n3(this.client, this.request);
    }

    public class a implements n7 {
        @Override // com.huawei.hms.network.embedded.n7
        public v7 intercept(n7.a aVar) throws IOException {
            n3.this.exchange = ((k9) aVar).e();
            Logger.v(n3.c, "Site connect success and return construct response");
            v7.a a2 = new v7.a().a(n3.this.request).a(200);
            y8 y8Var = n3.this.transmitter.i;
            return a2.a(y8Var == null ? r7.HTTP_1_1 : y8Var.d()).a("site detect success").a(w7.a(o7.a("text/plain; charset=UTF-8"), "site detect success")).a();
        }

        public a() {
        }
    }

    public static final class b implements f7 {
        public t7 b;

        @Override // com.huawei.hms.network.embedded.f7
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            Logger.v(n3.c, "ConnectDetectDNS lookup %s and result %s", str, this.b.j());
            return (List) (this.b.j() == null ? Collections.EMPTY_LIST : this.b.j());
        }

        public b(t7 t7Var) {
            this.b = t7Var;
        }
    }

    @Override // com.huawei.hms.network.embedded.f3
    public void addResponseInterceptor() {
        this.interceptors.add(new a());
    }

    public n3(q7 q7Var, t7 t7Var) {
        super(q7Var, t7Var);
        this.client = q7Var.t().a(d).a(new b(t7Var)).a();
        this.transmitter = new d9(this.client, this);
    }
}
