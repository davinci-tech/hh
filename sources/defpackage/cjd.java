package defpackage;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class cjd {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f738a;
    private byte[] d;
    private float h;
    private int c = 0;
    private int b = 0;
    private int i = 0;
    private int e = -1;

    public int c() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int e() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public byte[] a() {
        byte[] bArr = this.f738a;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public void c(byte[] bArr) {
        if (bArr != null) {
            this.f738a = (byte[]) bArr.clone();
        }
    }

    public byte[] j() {
        byte[] bArr = this.d;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public void e(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        this.d = (byte[]) bArr.clone();
    }

    public float i() {
        return this.h;
    }

    public void c(float f) {
        this.h = f;
    }

    public int b() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public String toString() {
        return "ConstructionsUserBean{height=" + this.c + ", age=" + this.b + ", sex=" + this.i + ", huids=" + Arrays.toString(this.f738a) + ", uids=" + Arrays.toString(this.d) + ", weight=" + this.h + ", mouths=" + this.e + '}';
    }
}
