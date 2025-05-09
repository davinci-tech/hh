package com.huawei.hms.network.embedded;

import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class x7 {

    /* renamed from: a, reason: collision with root package name */
    public final p6 f5570a;
    public final Proxy b;
    public final InetSocketAddress c;

    public String toString() {
        return "Route{" + this.c + "}";
    }

    public int hashCode() {
        return ((((this.f5570a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof x7) {
            x7 x7Var = (x7) obj;
            if (x7Var.f5570a.equals(this.f5570a) && x7Var.b.equals(this.b) && x7Var.c.equals(this.c)) {
                return true;
            }
        }
        return false;
    }

    public InetSocketAddress d() {
        return this.c;
    }

    public boolean c() {
        return this.f5570a.i != null && this.b.type() == Proxy.Type.HTTP;
    }

    public Proxy b() {
        return this.b;
    }

    public p6 a() {
        return this.f5570a;
    }

    public x7(p6 p6Var, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (p6Var == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f5570a = p6Var;
        this.b = proxy;
        this.c = inetSocketAddress;
    }
}
