package com.huawei.hms.mlsdk.asr.energy.vo;

/* loaded from: classes9.dex */
public class SampleBuffer {
    private byte[] buffer;
    private int capacity;
    private int size;

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f5056a;
        private int b;
        private int c;

        public b a(byte[] bArr) {
            this.f5056a = bArr == null ? null : (byte[]) bArr.clone();
            return this;
        }

        public b a(int i) {
            this.b = i;
            return this;
        }

        public SampleBuffer a() {
            return new SampleBuffer(this.f5056a, this.b, this.c);
        }
    }

    public byte[] getBuffer() {
        byte[] bArr = this.buffer;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.size;
    }

    private SampleBuffer(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.capacity = i;
        this.size = i2;
    }
}
