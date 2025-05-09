package defpackage;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.network.Exchange;

/* loaded from: classes7.dex */
public final class uze {
    private final String b;
    private final Object c;
    private final int d;

    public uze(String str, Object obj) {
        if (str == null) {
            throw new NullPointerException("URI must not be null");
        }
        if (obj == null) {
            throw new NullPointerException("peer's identity must not be null");
        }
        this.b = str;
        this.c = obj;
        this.d = (str.hashCode() * 31) + obj.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        uze uzeVar = (uze) obj;
        return this.c.equals(uzeVar.c) && this.b.equals(uzeVar.b);
    }

    public int hashCode() {
        return this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("KeyUri[");
        sb.append(this.b);
        Object obj = this.c;
        if (obj instanceof InetSocketAddress) {
            obj = vcb.a((InetSocketAddress) obj);
        }
        sb.append(", ");
        sb.append(obj);
        sb.append("]");
        return sb.toString();
    }

    private static String b(uxt uxtVar) {
        if (uxtVar == null) {
            throw new NullPointerException("request must not be null");
        }
        return uxtVar.c() + ":" + uxtVar.getOptions().aa();
    }

    public static uze d(Exchange exchange) {
        if (exchange == null) {
            throw new NullPointerException("exchange must not be null");
        }
        return new uze(b(exchange.p()), exchange.q());
    }
}
