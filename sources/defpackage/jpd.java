package defpackage;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class jpd {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f14009a;
    private String b;
    private String c;
    private long d;
    private String e;
    private int h;
    private int j;

    public String d() {
        return this.b;
    }

    public int i() {
        return this.j;
    }

    public long c() {
        return this.d;
    }

    public int g() {
        return this.h;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.e;
    }

    public byte[] e() {
        byte[] bArr = this.f14009a;
        return bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }

    public void e(String str) {
        this.b = str;
    }

    public void d(int i) {
        this.j = i;
    }

    public void a(long j) {
        this.d = j;
    }

    public void c(int i) {
        this.h = i;
    }

    public void c(String str) {
        this.c = str;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.f14009a = (byte[]) bArr.clone();
        }
    }

    public String toString() {
        return "FaqMessageInfo{mMessageId='" + this.b + "', mMessageType=" + this.j + ", mMessageExpireTime=" + this.d + ", mMotorEnable=" + this.h + ", mMessageTitle='" + this.c + "', mMessageContext='" + this.e + "', mMessageIcon=" + Arrays.toString(this.f14009a) + '}';
    }
}
