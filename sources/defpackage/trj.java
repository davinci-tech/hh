package defpackage;

import android.text.TextUtils;

/* loaded from: classes9.dex */
public class trj {
    public static int b(Exception exc) {
        if (exc instanceof IllegalStateException) {
            String message = exc.getMessage();
            if (!TextUtils.isEmpty(message)) {
                try {
                    return Integer.parseInt(message);
                } catch (NumberFormatException unused) {
                    return 1;
                }
            }
        }
        return 12;
    }
}
