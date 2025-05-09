package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.f0;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes9.dex */
public class k0 extends f0 {
    public static final String h = "LocalDNSResolver";

    @Override // com.huawei.hms.network.embedded.f0
    public m0 c() {
        StringBuilder sb;
        m0 m0Var = new m0();
        try {
            m0Var = y.a(InetAddress.getAllByName(this.f5237a));
        } catch (IllegalArgumentException e) {
            e = e;
            sb = new StringBuilder("LocalDNSResolver query failed, IllegalArgumentException Exception: ");
            sb.append(this.f5237a);
            Logger.w(h, sb.toString(), e);
        } catch (NullPointerException e2) {
            e = e2;
            sb = new StringBuilder("LocalDNSResolver query failed, NullPointerException Exception: ");
            sb.append(this.f5237a);
            Logger.w(h, sb.toString(), e);
        } catch (UnknownHostException unused) {
            Logger.w(h, "LocalDNSResolver query failed,UnknownHostException:" + this.f5237a);
        }
        if (this.f5237a.equalsIgnoreCase(o0.e().b())) {
            for (String str : o0.e().a()) {
                if (!m0Var.d().contains(str)) {
                    m0Var.a(str);
                }
            }
        }
        m0Var.b(2);
        m0Var.a(0);
        return m0Var;
    }

    public k0(String str, String str2, f0.a aVar) {
        super(str, 2, str2, aVar);
    }
}
