package defpackage;

import com.huawei.devicesdk.entity.SimpleDataHead;

/* loaded from: classes3.dex */
public class bjh {
    private byte b;
    private SimpleDataHead d;
    private byte e;
    private byte[] c = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private byte[] f402a = new byte[2];

    public SimpleDataHead d() {
        return this.d;
    }

    public void c(SimpleDataHead simpleDataHead) {
        this.d = simpleDataHead;
    }

    public void a(byte b) {
        this.e = b;
    }

    public byte e() {
        return this.b;
    }

    public void c(byte b) {
        this.b = b;
    }

    public byte[] a() {
        return this.c;
    }

    public void c(byte[] bArr) {
        this.c = bArr;
    }

    public void d(byte[] bArr) {
        this.f402a = bArr;
    }
}
