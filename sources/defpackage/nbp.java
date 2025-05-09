package defpackage;

import java.util.Arrays;

/* loaded from: classes6.dex */
public class nbp {

    /* renamed from: a, reason: collision with root package name */
    private String f15237a;
    private byte[] b;
    private String c;
    private String d;
    private int e;

    public String e() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int a() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public String d() {
        return this.f15237a;
    }

    public void c(String str) {
        this.f15237a = str;
    }

    public byte[] c() {
        byte[] bArr = this.b;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.b = (byte[]) bArr.clone();
        } else {
            this.b = null;
        }
    }

    public String toString() {
        return "CommonDataType{key='" + this.d + "', type=" + this.e + ", value='" + this.f15237a + "', iconBytes=" + Arrays.toString(this.b) + ", iconType='" + this.c + "'}";
    }
}
