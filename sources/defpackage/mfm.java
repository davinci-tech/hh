package defpackage;

import android.app.Activity;
import android.view.View;

/* loaded from: classes6.dex */
public class mfm {
    public static <T extends View> T cgM_(View view, int i) {
        return (T) view.findViewById(i);
    }

    public static <T extends View> T cgL_(Activity activity, int i) {
        return (T) activity.findViewById(i);
    }
}
