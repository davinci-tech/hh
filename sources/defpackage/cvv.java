package defpackage;

/* loaded from: classes3.dex */
public class cvv {
    private long e = -1;
    private byte[] c = null;

    public long a() {
        return this.e;
    }

    public void d(long j) {
        this.e = j;
    }

    public byte[] d() {
        return this.c;
    }

    public void b(byte[] bArr) {
        this.c = bArr;
    }

    public String toString() {
        return "SamplePointInfo{mKey=" + this.e + ", mByteValue=" + blt.b(blq.d(this.c)) + '}';
    }
}
