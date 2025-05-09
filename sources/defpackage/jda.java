package defpackage;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class jda {

    /* renamed from: a, reason: collision with root package name */
    private int f13750a;
    private byte[] b;
    private int c;
    private int d;
    private int e;

    public void b(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void c(int i) {
        this.f13750a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void d(int i) {
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void e(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void d(byte[] bArr) {
        if (bArr != null) {
            this.b = (byte[]) jdy.d(Arrays.copyOf(bArr, bArr.length));
        }
    }
}
