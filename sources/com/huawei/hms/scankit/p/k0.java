package com.huawei.hms.scankit.p;

import android.graphics.Rect;

/* loaded from: classes9.dex */
public class k0 {

    /* renamed from: a, reason: collision with root package name */
    private int f5813a;
    private Rect b;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public Rect f5814a;
        public int b;

        public a(Rect rect, int i) {
            this.f5814a = rect;
            this.b = i;
        }
    }

    public k0(int i, Rect rect) {
        this.f5813a = i;
        this.b = new Rect(rect);
    }

    public int a() {
        return this.f5813a;
    }

    public Rect b() {
        return this.b;
    }
}
