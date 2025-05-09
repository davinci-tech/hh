package com.huawei.hms.scankit.p;

import android.graphics.Point;

/* loaded from: classes9.dex */
public class e0 {

    /* renamed from: a, reason: collision with root package name */
    private int f5767a;
    private int b;
    private String c;
    private Point d;
    private int e;
    private boolean f;
    private boolean g;

    public static class b {
        private Point e;

        /* renamed from: a, reason: collision with root package name */
        private int f5768a = 0;
        private int b = 1;
        private int c = 0;
        private String d = "off";
        private boolean f = true;
        private boolean g = false;

        public b a(int i) {
            this.b = i;
            return this;
        }

        public b b(int i) {
            this.c = i;
            return this;
        }

        public b a(Point point) {
            this.e = point;
            return this;
        }

        public b b(boolean z) {
            this.f = z;
            return this;
        }

        public b a(boolean z) {
            this.g = z;
            return this;
        }

        public e0 a() {
            return new e0(this.f5768a, this.b, this.c, this.d, this.e, this.f).a(this.g);
        }
    }

    public int b() {
        return this.f5767a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public String f() {
        return this.c;
    }

    private e0(int i, int i2, int i3, String str, Point point, boolean z) {
        this.f5767a = i;
        this.b = i2;
        this.e = i3;
        this.c = str;
        this.d = point;
        this.f = z;
    }

    public void a(int i) {
        this.e = i;
    }

    public Point a() {
        return this.d;
    }

    public void a(Point point) {
        this.d = point;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public e0 a(boolean z) {
        this.g = z;
        return this;
    }
}
