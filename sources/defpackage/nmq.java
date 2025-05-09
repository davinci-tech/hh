package defpackage;

import android.content.Context;
import android.graphics.Rect;
import com.huawei.ui.commonui.linechart.anchor.Layout;

/* loaded from: classes6.dex */
public class nmq {
    private Context b;
    private Layout e;

    public nmq(Context context, Layout layout) {
        this.b = context;
        this.e = layout;
    }

    public float e() {
        float f;
        int i;
        if ((!this.e.i && !nng.d(this.b)) || (this.e.i && nng.d(this.b))) {
            f = this.e.c + this.e.b;
            i = this.e.l;
        } else {
            f = this.e.n + this.e.o;
            i = this.e.l;
        }
        return f + i;
    }

    public float g() {
        return this.e.s + this.e.m + this.e.j + this.e.e;
    }

    public float c() {
        float f;
        int i;
        if ((!this.e.i && !nng.d(this.b)) || (this.e.i && nng.d(this.b))) {
            f = this.e.n + this.e.o;
            i = this.e.q;
        } else {
            f = this.e.c + this.e.b;
            i = this.e.q;
        }
        return f + i;
    }

    public float b() {
        float f = this.e.v + this.e.x + this.e.h + this.e.u + this.e.w + this.e.r + this.e.k;
        float f2 = this.e.t + this.e.p + this.e.k;
        return f2 > f ? f2 : f;
    }

    public float f() {
        return this.e.l + this.e.g;
    }

    public float j() {
        return (h() - this.e.f) - this.e.q;
    }

    private float h() {
        return this.e.l + this.e.o + this.e.n + this.e.d + this.e.b + this.e.c + this.e.q;
    }

    public Rect cBn_() {
        return new Rect(this.e.l, (int) (this.e.s + this.e.m), (int) (this.e.l + this.e.c + this.e.b + this.e.d + this.e.n + this.e.o), (int) (this.e.s + this.e.m + this.e.j));
    }

    public Rect cBm_() {
        return new Rect(this.e.l, (int) (this.e.s + this.e.m + this.e.j + this.e.e + this.e.f8852a + this.e.t), (int) (this.e.l + this.e.c + this.e.b + this.e.d + this.e.n + this.e.o), (int) (this.e.s + this.e.m + this.e.j + this.e.e + this.e.f8852a + this.e.t + this.e.p));
    }

    public float i() {
        return this.e.f8852a;
    }
}
