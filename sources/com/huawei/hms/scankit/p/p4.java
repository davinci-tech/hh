package com.huawei.hms.scankit.p;

import org.apache.commons.io.FilenameUtils;

/* loaded from: classes9.dex */
public abstract class p4 {

    /* renamed from: a, reason: collision with root package name */
    private final int f5854a;
    private final int b;

    protected p4(int i, int i2) {
        this.f5854a = i;
        this.b = i2;
    }

    public final int a() {
        return this.b;
    }

    public abstract p4 a(int i, int i2, int i3, int i4);

    public abstract byte[] a(int i, byte[] bArr);

    public abstract byte[] b();

    public final int c() {
        return this.f5854a;
    }

    public final String toString() {
        int i = this.f5854a;
        byte[] bArr = new byte[i];
        StringBuilder sb = new StringBuilder(this.b * (i + 1));
        for (int i2 = 0; i2 < this.b; i2++) {
            bArr = a(i2, bArr);
            for (int i3 = 0; i3 < this.f5854a; i3++) {
                int i4 = bArr[i3] & 255;
                sb.append(i4 < 64 ? '#' : i4 < 128 ? '+' : i4 < 192 ? FilenameUtils.EXTENSION_SEPARATOR : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
