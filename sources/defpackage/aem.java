package defpackage;

import android.graphics.LinearGradient;
import android.graphics.Shader;

/* loaded from: classes8.dex */
public class aem extends LinearGradient {

    /* renamed from: a, reason: collision with root package name */
    private final float f186a;
    private final float[] b;
    private final Shader.TileMode c;
    private final float d;
    private final int[] e;
    private final float f;
    private final float h;

    public aem(float f, float f2, float f3, float f4, int[] iArr, float[] fArr, Shader.TileMode tileMode) {
        super(f, f2, f3, f4, iArr, fArr, tileMode);
        this.f186a = f;
        this.f = f2;
        this.d = f3;
        this.h = f4;
        this.e = iArr;
        this.b = fArr;
        this.c = tileMode;
    }

    public float b() {
        return this.f186a;
    }

    public float g() {
        return this.f;
    }

    public float c() {
        return this.d;
    }

    public float f() {
        return this.h;
    }

    public int[] a() {
        return this.e;
    }

    public float[] e() {
        return this.b;
    }

    public Shader.TileMode go_() {
        return this.c;
    }
}
