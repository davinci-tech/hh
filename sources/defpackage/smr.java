package defpackage;

import android.content.Context;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* loaded from: classes7.dex */
public class smr {
    public static Context b(Context context, int i, int i2) {
        return context.getTheme().resolveAttribute(i, new TypedValue(), false) ? context : new ContextThemeWrapper(context, i2);
    }
}
