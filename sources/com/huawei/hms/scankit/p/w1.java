package com.huawei.hms.scankit.p;

import java.util.List;

/* loaded from: classes9.dex */
public final class w1 {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f5901a;
    private int b;
    private final String c;
    private final List<byte[]> d;
    private final String e;
    private Integer f;
    private Integer g;
    private Object h;
    private final int i;
    private final int j;

    public w1(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public int a() {
        return this.b;
    }

    public void b(Integer num) {
        this.f = num;
    }

    public byte[] c() {
        return this.f5901a;
    }

    public String d() {
        return this.c;
    }

    public w1(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f5901a = bArr;
        this.b = bArr == null ? 0 : bArr.length * 8;
        this.c = str;
        this.d = list;
        this.e = str2;
        this.i = i2;
        this.j = i;
    }

    public void a(int i) {
        this.b = i;
    }

    public Object b() {
        return this.h;
    }

    public void a(Integer num) {
        this.g = num;
    }

    public void a(Object obj) {
        this.h = obj;
    }
}
