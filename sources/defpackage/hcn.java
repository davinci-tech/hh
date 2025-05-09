package defpackage;

import android.content.Context;
import android.util.TypedValue;

/* loaded from: classes4.dex */
public class hcn {
    public static int a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
