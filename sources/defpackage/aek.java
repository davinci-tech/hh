package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Shader;

/* loaded from: classes8.dex */
public final class aek {
    private int b;
    private final ColorStateList d;
    private Shader e;

    private aek(Shader shader, ColorStateList colorStateList, int i) {
        this.e = shader;
        this.d = colorStateList;
        this.b = i;
    }

    static aek b(int i) {
        return new aek(null, null, i);
    }

    public Shader gm_() {
        return this.e;
    }

    public void gn_(Shader shader) {
        this.e = shader;
    }

    public int c() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public boolean a() {
        return this.e != null;
    }

    public boolean b() {
        ColorStateList colorStateList;
        return this.e == null && (colorStateList = this.d) != null && colorStateList.isStateful();
    }

    public boolean a(int[] iArr) {
        if (b()) {
            ColorStateList colorStateList = this.d;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.b) {
                this.b = colorForState;
                return true;
            }
        }
        return false;
    }

    public boolean d() {
        return a() || this.b != 0;
    }
}
