package defpackage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class gvw extends DataOutputStream {
    private byte[] b;

    public gvw(OutputStream outputStream) {
        super(outputStream);
        this.b = new byte[8];
    }

    public final void b(int i) throws IOException {
        this.out.write(i & 255);
        this.out.write((i >>> 8) & 255);
    }

    public final void e(int i) throws IOException {
        this.out.write(i & 255);
        this.out.write((i >>> 8) & 255);
        this.out.write((i >>> 16) & 255);
        this.out.write((i >>> 24) & 255);
    }

    public final void b(long j) throws IOException {
        byte[] bArr = this.b;
        bArr[0] = (byte) j;
        bArr[1] = (byte) (j >>> 8);
        bArr[2] = (byte) (j >>> 16);
        bArr[3] = (byte) (j >>> 24);
        bArr[4] = (byte) (j >>> 32);
        bArr[5] = (byte) (j >>> 40);
        bArr[6] = (byte) (j >>> 48);
        bArr[7] = (byte) (j >>> 56);
        this.out.write(this.b, 0, 8);
    }
}
