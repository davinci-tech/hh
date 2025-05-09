package com.huawei.openalliance.ad.views.gif;

import android.graphics.Bitmap;

/* loaded from: classes5.dex */
class c {

    /* renamed from: a, reason: collision with root package name */
    int f8086a;
    Bitmap b;
    int c;

    public String toString() {
        return "GifFrame{frameIndex=" + this.f8086a + ", delay=" + this.c + '}';
    }

    c a() {
        c cVar = new c();
        cVar.f8086a = this.f8086a;
        cVar.c = this.c;
        return cVar;
    }

    c(int i, Bitmap bitmap, int i2) {
        this.f8086a = i;
        this.b = bitmap;
        this.c = i2;
    }

    c() {
    }
}
