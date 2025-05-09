package defpackage;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes3.dex */
public class ags {
    private int c = 1024;
    private int d = 0;
    private byte[] b = new byte[1024];

    public int e() {
        return this.d;
    }

    public byte[] a() {
        int i = this.d;
        if (i <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.b, 0, bArr, 0, i);
        return bArr;
    }

    public void c(byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        byte[] bArr2 = this.b;
        int length = bArr2.length;
        int i2 = this.d;
        if (length - i2 >= i) {
            System.arraycopy(bArr, 0, bArr2, i2, i);
        } else {
            byte[] bArr3 = new byte[(bArr2.length + i) << 1];
            System.arraycopy(bArr2, 0, bArr3, 0, i2);
            System.arraycopy(bArr, 0, bArr3, this.d, i);
            this.b = bArr3;
        }
        this.d += i;
    }

    public void d() {
        this.b = new byte[this.c];
        this.d = 0;
    }

    public static String d(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i += 2;
            cArr2[i2] = cArr[b & BaseType.Obj];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cArr2);
        return sb.toString();
    }
}
