package com.huawei.hms.mlsdk.asr.engine.utils;

/* loaded from: classes9.dex */
public class b {
    private int b = 0;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f5089a = new byte[64];

    private void b(int i) {
        byte[] bArr = this.f5089a;
        int length = bArr.length;
        if (i > length) {
            int i2 = length << 1;
            if (i2 >= i) {
                i = i2;
            }
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.f5089a = bArr2;
        }
    }

    public void a(int i) {
        b(this.b + 4);
        byte[] bArr = this.f5089a;
        int i2 = this.b;
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        this.b = i2 + 4;
        bArr[i2 + 3] = (byte) i;
    }

    public void a(long j) {
        b(this.b + 8);
        byte[] bArr = this.f5089a;
        int i = this.b;
        bArr[i] = (byte) (j >>> 56);
        bArr[i + 1] = (byte) (j >> 48);
        bArr[i + 2] = (byte) (j >> 40);
        bArr[i + 3] = (byte) (j >> 32);
        bArr[i + 4] = (byte) (j >> 24);
        bArr[i + 5] = (byte) (j >> 16);
        bArr[i + 6] = (byte) (j >> 8);
        this.b = i + 8;
        bArr[i + 7] = (byte) j;
    }

    public void a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        b(this.b + bArr.length);
        System.arraycopy(bArr, 0, this.f5089a, this.b, bArr.length);
        this.b += bArr.length;
    }

    public byte[] a() {
        int i = this.b;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f5089a, 0, bArr, 0, i);
        return bArr;
    }
}
