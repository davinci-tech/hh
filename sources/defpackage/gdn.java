package defpackage;

import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: classes4.dex */
public class gdn {
    public static void aLx_(View view, float f, float f2, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "Alpha", f, f2);
        ofFloat.setDuration(i);
        ofFloat.start();
    }
}
