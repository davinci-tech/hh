package defpackage;

/* loaded from: classes5.dex */
public class izj {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f13682a;
    private int d;
    private boolean c = false;
    private boolean b = true;
    private boolean e = true;

    public boolean d() {
        return this.c;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public int a() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public byte[] e() {
        byte[] bArr = this.f13682a;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public void d(byte[] bArr) {
        this.f13682a = bArr == null ? null : (byte[]) bArr.clone();
    }

    public boolean c() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean b() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }
}
