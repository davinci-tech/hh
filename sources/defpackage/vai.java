package defpackage;

/* loaded from: classes7.dex */
public class vai extends vab {
    public static final uzx<String> c = new uzx<>("CONNECTION_ID", String.class, p);

    /* renamed from: a, reason: collision with root package name */
    public static final uzx<Long> f17632a = new uzx<>("CONNECTION_TIMESTAMP", Long.class, p);

    public String d() {
        return (String) get(c);
    }

    @Override // defpackage.vab, defpackage.uzw
    public String toString() {
        return String.format("TCP(%s,ID:%s)", e(), vcb.b(d(), 10));
    }
}
