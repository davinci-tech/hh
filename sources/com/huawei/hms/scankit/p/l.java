package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
final class l {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f5819a;
    private int b = 0;

    l(int i) {
        this.f5819a = new byte[i];
    }

    private void a(int i, boolean z) {
        if (!w7.a(this.f5819a, i)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.f5819a[i] = z ? (byte) 1 : (byte) 0;
    }

    void a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.b;
            this.b = i3 + 1;
            a(i3, z);
        }
    }

    byte[] a(int i) {
        int length = this.f5819a.length * i;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = this.f5819a[i2 / i];
        }
        return bArr;
    }
}
