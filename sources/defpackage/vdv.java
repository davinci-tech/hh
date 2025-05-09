package defpackage;

import java.util.Arrays;
import java.util.Date;

/* loaded from: classes7.dex */
public class vdv extends vbj {
    public vdv() {
        this(a());
    }

    public vdv(byte[] bArr) {
        super(bArr);
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Random bytes array's length must be 32");
        }
    }

    public String c(int i) {
        StringBuilder sb = new StringBuilder();
        byte[] c = c();
        Date date = new Date((((c[0] & 255) << 24) | ((c[1] & 255) << 16) | ((c[2] & 255) << 8) | (c[3] & 255)) * 1000);
        String b = vcb.b(i);
        sb.append(b);
        sb.append("GMT Unix Time: ");
        sb.append(date);
        sb.append(vcb.a());
        byte[] copyOfRange = Arrays.copyOfRange(c, 4, 32);
        sb.append(b);
        sb.append("Random Bytes: ");
        sb.append(vcb.e(copyOfRange));
        sb.append(vcb.a());
        return sb.toString();
    }

    @Override // defpackage.vbj
    public String toString() {
        return c(0);
    }

    public static byte[] a() {
        byte[] d = vbj.d(vep.d(), 32);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        d[0] = (byte) (currentTimeMillis >> 24);
        d[1] = (byte) (currentTimeMillis >> 16);
        d[2] = (byte) (currentTimeMillis >> 8);
        d[3] = (byte) currentTimeMillis;
        return d;
    }
}
