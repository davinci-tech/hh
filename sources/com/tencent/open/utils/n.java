package com.tencent.open.utils;

/* loaded from: classes8.dex */
public final class n implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private long f11385a;

    public n(long j) {
        this.f11385a = j;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof n) && this.f11385a == ((n) obj).b();
    }

    public byte[] a() {
        long j = this.f11385a;
        return new byte[]{(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & 4278190080L) >> 24)};
    }

    public long b() {
        return this.f11385a;
    }

    public int hashCode() {
        return (int) this.f11385a;
    }
}
