package com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand;

/* loaded from: classes3.dex */
public abstract class AbstractCommand {
    protected byte mCode;
    protected byte mCommand;
    protected byte mHead;
    protected byte mOrder;

    public abstract byte getCheck();

    public abstract byte[] getPara();

    public abstract byte[] getParameterLength();

    public abstract byte[] toByteArray();

    public byte getHead() {
        return this.mHead;
    }

    public byte getOrder() {
        return this.mOrder;
    }

    public byte getCommand() {
        return this.mCommand;
    }

    public byte getCode() {
        return this.mCode;
    }

    public int toByteArray(byte[] bArr) {
        bArr[0] = this.mHead;
        bArr[1] = this.mOrder;
        bArr[2] = this.mCommand;
        bArr[3] = this.mCode;
        return 3;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public byte f2287a;
        public byte b;
        public byte c;
        public byte d;

        public a(byte b) {
            this.d = b;
        }

        public void d(byte b) {
            this.d = b;
        }

        public void d(int i) {
            this.c = (byte) (i & 255);
        }

        public void c(byte b) {
            this.c = b;
        }

        public void b(int i) {
            this.b = (byte) (i & 255);
        }

        public void b(byte b) {
            this.b = b;
        }

        public void e(byte b) {
            this.f2287a = b;
        }

        public int c(byte[] bArr) {
            this.d = bArr[0];
            this.f2287a = bArr[1];
            this.c = bArr[2];
            this.b = bArr[3];
            return 3;
        }
    }
}
