package defpackage;

import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public class eem {
    private final Drawable b;
    private final String c;
    private final int d;

    public eem(String str, Drawable drawable, int i) {
        this.c = str;
        this.b = drawable;
        this.d = i;
    }

    public String a() {
        return this.c;
    }

    public Drawable agU_() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public String toString() {
        return "ActivityStatus{mText='" + this.c + "', mColorDrawable=" + this.b + ", mVisibility=" + this.d + '}';
    }
}
