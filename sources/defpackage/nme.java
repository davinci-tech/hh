package defpackage;

import android.content.Context;

/* loaded from: classes6.dex */
public class nme {

    /* renamed from: a, reason: collision with root package name */
    private static float f15385a = 1.0f;

    protected nme() {
        throw new UnsupportedOperationException();
    }

    public static void d(Context context) {
        f15385a = context.getResources().getDisplayMetrics().density;
    }

    public static float a(float f) {
        return f * f15385a;
    }
}
