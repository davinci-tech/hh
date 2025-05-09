package defpackage;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes3.dex */
public class cgu {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f711a;
    private String b;
    private int c;
    private int d;
    private int e;
    private float f;

    public void a(byte[] bArr) {
        this.f711a = bArr == null ? null : (byte[]) bArr.clone();
    }

    public void d(int i) {
        this.c = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public String b() {
        return (String) cpt.d(this.b);
    }

    public void b(String str) {
        this.b = (String) cpt.d(str);
    }

    public void e(int i) {
        this.e = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public void c(int i) {
        this.d = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public void e(float f) {
        this.f = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public byte[] d() {
        int i = this.e;
        if (this.c == 1) {
            i |= 128;
        }
        int i2 = (int) (this.f * 10.0f);
        byte[] bArr = this.f711a;
        return new byte[]{-37, BaseType.Vector, 9, bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], (byte) i, (byte) this.d, 0, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), -1, -1};
    }
}
