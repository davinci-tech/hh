package defpackage;

import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public class uyc {
    private final Object c;
    private final int d;
    private final uxu e;

    public uyc(uxu uxuVar, Object obj) {
        this.e = uxuVar;
        this.c = obj;
        int hashCode = uxuVar.hashCode();
        this.d = obj != null ? hashCode + (obj.hashCode() * 31) : hashCode;
    }

    public final int hashCode() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        uyc uycVar = (uyc) obj;
        if (this.d != uycVar.d || !this.e.equals(uycVar.e)) {
            return false;
        }
        Object obj2 = this.c;
        Object obj3 = uycVar.c;
        if (obj2 == obj3) {
            return true;
        }
        if (obj2 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("KeyToken[");
        Object obj = this.c;
        if (obj != null) {
            if (obj instanceof InetSocketAddress) {
                obj = vcb.a((InetSocketAddress) obj);
            }
            sb.append(obj);
            sb.append('-');
        }
        sb.append(this.e.e());
        sb.append(']');
        return sb.toString();
    }
}
