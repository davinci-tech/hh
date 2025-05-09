package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.d1;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes9.dex */
public class k3 implements d1.a {
    public static final String b = "OkRequestTaskFactory";

    /* renamed from: a, reason: collision with root package name */
    public final q7 f5342a;

    public static class a implements f7 {
        public o2 b;

        @Override // com.huawei.hms.network.embedded.f7
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            return this.b.lookup(str);
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && this.b.equals(((a) obj).b);
        }

        public a(o2 o2Var) {
            this.b = o2Var;
        }
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public d1 newTask() {
        return new j3(this.f5342a);
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public boolean isAvailable() {
        return this.f5342a != null;
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public String getChannel() {
        return "type_okhttp";
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
    
        r1.a(com.huawei.hms.network.httpclient.okhttp.OkHttpClientGlobal.getInstance().newConnectionPool()).a(r8.getSocketFactory());
        com.huawei.hms.framework.common.Logger.i(com.huawei.hms.network.embedded.k3.b, "bind network client = " + r8 + " " + r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k3(com.huawei.hms.network.embedded.a1 r11) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.k3.<init>(com.huawei.hms.network.embedded.a1):void");
    }
}
