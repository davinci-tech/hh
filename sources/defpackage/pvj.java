package defpackage;

import java.io.Serializable;

/* loaded from: classes9.dex */
public class pvj implements Serializable {
    private static final long serialVersionUID = -9040197766300913173L;

    /* renamed from: a, reason: collision with root package name */
    private boolean f16273a;
    private int b;
    private int c;

    public pvj(int i, boolean z) {
        this.c = i;
        this.f16273a = z;
    }

    public int d() {
        return this.c;
    }

    public void e(int i) {
        this.c = i;
    }

    public boolean a() {
        return this.f16273a;
    }

    public void c(int i) {
        this.b = i;
    }

    public String toString() {
        return "FoodType [mType=" + this.c + ", mCanBeModified=" + this.f16273a + "]";
    }

    public int b() {
        return this.b;
    }
}
