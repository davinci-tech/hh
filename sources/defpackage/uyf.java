package defpackage;

import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public final class uyf {
    private final int b;
    private final Object c;
    private final int d;

    public uyf(int i, Object obj) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("MID must be a 16 bit unsigned int: " + i);
        }
        if (obj == null) {
            throw new NullPointerException("peer must not be null");
        }
        this.d = i;
        this.c = obj;
        this.b = (i * 31) + obj.hashCode();
    }

    public Object c() {
        return this.c;
    }

    public int hashCode() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        uyf uyfVar = (uyf) obj;
        if (this.d != uyfVar.d) {
            return false;
        }
        return this.c.equals(uyfVar.c);
    }

    public String toString() {
        Object obj = this.c;
        if (obj instanceof InetSocketAddress) {
            obj = vcb.a((InetSocketAddress) obj);
        }
        return "KeyMID[" + obj + '-' + this.d + ']';
    }
}
