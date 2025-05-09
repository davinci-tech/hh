package com.tencent.open.utils;

import androidx.core.view.MotionEventCompat;

/* loaded from: classes8.dex */
public final class o implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private int f11386a;

    public o(byte[] bArr) {
        this(bArr, 0);
    }

    public o(byte[] bArr, int i) {
        this.f11386a = ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[i] & 255);
    }

    public o(int i) {
        this.f11386a = i;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof o) && this.f11386a == ((o) obj).b();
    }

    public byte[] a() {
        int i = this.f11386a;
        return new byte[]{(byte) (i & 255), (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    public int b() {
        return this.f11386a;
    }

    public int hashCode() {
        return this.f11386a;
    }
}
