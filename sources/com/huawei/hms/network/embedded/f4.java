package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.d1;
import java.net.Proxy;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes9.dex */
public class f4 implements d1.a {

    /* renamed from: a, reason: collision with root package name */
    public a1 f5242a;

    @Override // com.huawei.hms.network.embedded.d1.a
    public boolean isAvailable() {
        return true;
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public d1 newTask() {
        return new e4(this);
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.f5242a.getSslSocketFactory();
    }

    public Proxy getProxy() {
        return this.f5242a.getProxy();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.f5242a.getHostnameVerifier();
    }

    @Override // com.huawei.hms.network.embedded.d1.a
    public String getChannel() {
        return "type_urlconnection";
    }

    public f4(a1 a1Var) {
        this.f5242a = a1Var;
    }
}
