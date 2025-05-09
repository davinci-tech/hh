package defpackage;

import android.graphics.Color;

/* loaded from: classes4.dex */
public class hld {
    private static final int b = Color.rgb(251, 101, 34);
    private int e = b;
    private float f = 16.0f;
    private boolean c = true;
    private boolean d = false;
    private hjd g = null;

    /* renamed from: a, reason: collision with root package name */
    private hjd f13232a = null;
    private float i = 0.0f;

    public hld c(int i) {
        this.e = i;
        return this;
    }

    public hld b(float f) {
        this.f = f;
        return this;
    }

    public hld c(boolean z) {
        this.c = z;
        return this;
    }

    public hld a(boolean z) {
        this.d = z;
        return this;
    }

    public hld a(hjd hjdVar) {
        this.g = hjdVar;
        return this;
    }

    public hld c(hjd hjdVar) {
        this.f13232a = hjdVar;
        return this;
    }

    public int e() {
        return this.e;
    }

    public float b() {
        return this.f;
    }

    public boolean h() {
        return this.d;
    }

    public hjd a() {
        return this.g;
    }

    public hjd c() {
        return this.f13232a;
    }

    public boolean g() {
        return this.c;
    }

    public float d() {
        return this.i;
    }

    public void a(float f) {
        this.i = f;
    }
}
