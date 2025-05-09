package defpackage;

import android.view.View;

/* loaded from: classes8.dex */
public final class abj {
    public static <T extends View> T fw_(View view, int i) {
        if (view != null) {
            return (T) view.findViewById(i);
        }
        return null;
    }
}
