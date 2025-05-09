package com.huawei.secure.android.common.encrypt.rootkey;

/* loaded from: classes9.dex */
public class RootKeyConfig {

    /* renamed from: a, reason: collision with root package name */
    private String f8597a;
    private String b;
    private String c;
    private byte[] d;
    private int e;
    private int f;
    private boolean g;

    public RootKeyConfig(String str, String str2, String str3, byte[] bArr, int i, int i2, boolean z) {
        this.f8597a = str;
        this.b = str2;
        this.c = str3;
        this.d = bArr;
        this.e = i;
        this.f = i2;
        this.g = z;
    }

    public int getExportLen() {
        return this.f;
    }

    public String getFirst() {
        return this.f8597a;
    }

    public int getIteration() {
        return this.e;
    }

    public byte[] getSalt() {
        return this.d;
    }

    public String getSecond() {
        return this.b;
    }

    public String getThird() {
        return this.c;
    }

    public boolean isSha256() {
        return this.g;
    }

    public void setExportLen(int i) {
        this.f = i;
    }

    public void setFirst(String str) {
        this.f8597a = str;
    }

    public void setIteration(int i) {
        this.e = i;
    }

    public void setSalt(byte[] bArr) {
        this.d = bArr;
    }

    public void setSecond(String str) {
        this.b = str;
    }

    public void setSha256(boolean z) {
        this.g = z;
    }

    public void setThird(String str) {
        this.c = str;
    }
}
