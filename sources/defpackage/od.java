package defpackage;

import android.content.Context;
import androidx.core.content.PermissionChecker;

/* loaded from: classes2.dex */
public class od {
    public static boolean c(Context context, String... strArr) {
        for (String str : strArr) {
            if (PermissionChecker.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }
}
