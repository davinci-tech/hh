package defpackage;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes7.dex */
public class uwp {
    private static Context d;

    public static int c(Context context) {
        if (context == null) {
            uwn.e("GlobalContext", "Context is null");
            return -1;
        }
        Context applicationContext = context.getApplicationContext();
        d = applicationContext;
        if (applicationContext != null && !TextUtils.isEmpty(applicationContext.getPackageName())) {
            return 0;
        }
        uwn.e("GlobalContext", "Invalid context.");
        return -1;
    }

    public static Context b() {
        return d;
    }

    public static String c() {
        return d.getPackageName();
    }
}
