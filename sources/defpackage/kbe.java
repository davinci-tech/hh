package defpackage;

/* loaded from: classes5.dex */
public class kbe {
    private int b = 0;
    private byte[] c = null;

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void c(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public byte[] b() {
        byte[] bArr = this.c;
        return bArr != null ? (byte[]) jdy.d((byte[]) bArr.clone()) : bArr;
    }

    public void c(byte[] bArr) {
        if (bArr != null) {
            this.c = (byte[]) jdy.d((byte[]) bArr.clone());
        }
    }
}
