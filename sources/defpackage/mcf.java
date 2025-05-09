package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes6.dex */
public final class mcf {
    public static void cfJ_(Context context, Intent intent) {
        if (context == null || intent == null) {
            mcj.b("ActivityUtils", "startActivity params is null");
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            mcj.d("ActivityUtils", "startActivity Exception: ", e);
        }
    }

    public static void cfK_(Activity activity, Intent intent, int i) {
        if (activity == null || intent == null) {
            mcj.b("ActivityUtils", "startActivityForResult params is null");
            return;
        }
        try {
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e) {
            mcj.d("ActivityUtils", "startActivityForResult Exception: ", e);
        }
    }
}
