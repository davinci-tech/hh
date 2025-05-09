package defpackage;

import android.app.Activity;

/* loaded from: classes7.dex */
public class snc {
    public static boolean ehX_(Activity activity) {
        if (activity == null) {
            return false;
        }
        return activity.isInMultiWindowMode();
    }
}
