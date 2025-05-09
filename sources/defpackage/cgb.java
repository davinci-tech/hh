package defpackage;

/* loaded from: classes3.dex */
public class cgb {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f695a;
    private int b;
    private int c;
    private int d;
    private byte e;

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int a() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int e() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public byte[] d() {
        byte[] bArr = this.f695a;
        return bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }

    public void e(byte[] bArr) {
        if (bArr != null) {
            this.f695a = (byte[]) bArr.clone();
        } else {
            this.f695a = null;
        }
    }

    public byte c() {
        return this.e;
    }

    public void d(byte b) {
        this.e = b;
    }
}
