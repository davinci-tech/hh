package defpackage;

import defpackage.vab;
import java.net.InetSocketAddress;

/* loaded from: classes7.dex */
public class van extends vab {
    public static final uzx<String> c = new uzx<>("PLAIN", String.class, p);

    public van(InetSocketAddress inetSocketAddress) {
        super(inetSocketAddress, null, new vab.d().d(c, ""));
    }

    @Override // defpackage.vab, defpackage.uzw
    public String toString() {
        return String.format("UDP(%s)", e());
    }
}
