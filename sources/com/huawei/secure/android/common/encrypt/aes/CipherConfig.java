package com.huawei.secure.android.common.encrypt.aes;

import javax.crypto.Cipher;

/* loaded from: classes9.dex */
public class CipherConfig {

    /* renamed from: a, reason: collision with root package name */
    private Cipher f8586a;
    private byte[] b;
    private int c;
    private int d;
    private byte[] e;
    private int f;

    public CipherConfig(Cipher cipher, byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.f8586a = cipher;
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = bArr2;
        this.f = i3;
    }

    public Cipher getCipher() {
        return this.f8586a;
    }

    public byte[] getInput() {
        return this.b;
    }

    public int getInputLen() {
        return this.d;
    }

    public int getInputOffset() {
        return this.c;
    }

    public byte[] getOutput() {
        return this.e;
    }

    public int getOutputOffset() {
        return this.f;
    }

    public void setCipher(Cipher cipher) {
        this.f8586a = cipher;
    }

    public void setInput(byte[] bArr) {
        this.b = bArr;
    }

    public void setInputLen(int i) {
        this.d = i;
    }

    public void setInputOffset(int i) {
        this.c = i;
    }

    public void setOutput(byte[] bArr) {
        this.e = bArr;
    }

    public void setOutputOffset(int i) {
        this.f = i;
    }
}
