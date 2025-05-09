package defpackage;

/* loaded from: classes5.dex */
public class kii {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14394a = new Object();
    private static int d;
    private static kii e;
    private byte[] b;
    private int c;
    private kii f;

    public byte[] a() {
        return (byte[]) jdy.d(this.b);
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void b(int i) {
        this.c = i;
    }

    public void b(byte[] bArr) {
        this.b = bArr;
    }

    public static kii d() {
        synchronized (f14394a) {
            kii kiiVar = e;
            if (kiiVar != null) {
                e = kiiVar.f;
                kiiVar.f = null;
                d--;
                return kiiVar;
            }
            return new kii();
        }
    }

    public void c() {
        this.c = 0;
        this.b = null;
        synchronized (f14394a) {
            int i = d;
            if (i < 4) {
                this.f = e;
                e = this;
                d = i + 1;
            }
        }
    }
}
