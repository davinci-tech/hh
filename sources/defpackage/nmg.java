package defpackage;

import android.graphics.RectF;

/* loaded from: classes6.dex */
public class nmg {
    private nmf b;

    nmg(nmf nmfVar) {
        this.b = nmfVar;
    }

    public float c() {
        return (this.b.e() - this.b.i()) - this.b.a();
    }

    public float e() {
        return (this.b.g() - this.b.b()) - this.b.c();
    }

    public RectF cAl_() {
        return new RectF(this.b.b(), this.b.i(), this.b.b() + e(), this.b.i() + c());
    }
}
