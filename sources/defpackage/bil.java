package defpackage;

/* loaded from: classes3.dex */
public class bil {
    private byte[] c;
    private int e;

    public byte[] d() {
        byte[] bArr = this.c;
        return bArr != null ? bArr : new byte[0];
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            this.c = bArr;
        }
    }

    public int a() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }
}
