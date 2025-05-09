package defpackage;

/* loaded from: classes7.dex */
public class vak extends vai {
    public static final uzx<String> e = new uzx<>("TLS_SESSION_ID", String.class, p);
    public static final uzx<String> d = new uzx<>("TLS_CIPHER", String.class, p);

    public String c() {
        return (String) get(e);
    }

    public String a() {
        return (String) get(d);
    }

    @Override // defpackage.vai, defpackage.vab, defpackage.uzw
    public String toString() {
        return String.format("TLS(%s,%s,%s,%s)", e(), vcb.b(d(), 10), vcb.b(c(), 10), a());
    }
}
