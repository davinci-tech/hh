package defpackage;

import com.huawei.watchface.videoedit.param.CanvasConfig;
import defpackage.vab;
import java.net.InetSocketAddress;
import java.security.Principal;

/* loaded from: classes7.dex */
public class uzz extends vab {

    /* renamed from: a, reason: collision with root package name */
    public static final vab.d f17626a;
    public static final vab.d b;
    public static final vab.d c;
    public static final vab.d d;
    public static final vab.d e;
    public static final uzx<String> f;
    public static final uzx<Boolean> h;
    public static final uzx<Integer> i;
    public static final uzx<InetSocketAddress> k;
    public static final uzx<Boolean> m;
    public static final uzx<Integer> n;
    public static final uzx<vbj> s = new uzx<>("DTLS_SESSION_ID", vbj.class, p);
    public static final uzx<Integer> g = new uzx<>("DTLS_EPOCH", Integer.class, p);
    public static final uzx<String> j = new uzx<>("DTLS_CIPHER", String.class, p);
    public static final uzx<Long> o = new uzx<>("DTLS_HANDSHAKE_TIMESTAMP", Long.class, p);
    public static final uzx<vbj> l = new uzx<>("DTLS_READ_CONNECTION_ID", vbj.class, p);
    public static final uzx<vbj> r = new uzx<>("DTLS_WRITE_CONNECTION_ID", vbj.class, p);
    public static final uzx<String> t = new uzx<>("*DTLS_VIA_ROUTER", String.class, p);

    static {
        uzx<String> uzxVar = new uzx<>("*DTLS_HANDSHAKE_MODE", String.class, p);
        f = uzxVar;
        i = new uzx<>("*DTLS_AUTO_HANDSHAKE_TIMEOUT", Integer.class, p);
        n = new uzx<>("*DTLS_MESSAGE_SIZE_LIMIT", Integer.class, p);
        h = new uzx<>("*DTLS_EXTENDED_MASTER_SECRET", Boolean.class, p);
        m = new uzx<>("*DTLS_NEWEST_RECORD", Boolean.class, p);
        k = new uzx<>("*DTLS_PREVIOUS_ADDRESS", InetSocketAddress.class, p);
        f17626a = new vab.d().d(uzxVar, "none").e();
        c = new vab.d().d(uzxVar, "auto").e();
        b = new vab.d().d(uzxVar, "probe").e();
        d = new vab.d().d(uzxVar, "force").e();
        e = new vab.d().d(uzxVar, CanvasConfig.FULL_CONFIG).e();
    }

    public uzz(InetSocketAddress inetSocketAddress, String str, Principal principal, vab.d dVar) {
        super(inetSocketAddress, str, principal, dVar);
    }

    public final vbj a() {
        return (vbj) get(s);
    }

    @Override // defpackage.vab, defpackage.uzw
    public String toString() {
        return String.format("DTLS(%s,ID:%s)", e(), vcb.b(a().e(), 10));
    }
}
