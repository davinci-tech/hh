package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Hashtable;

/* loaded from: classes5.dex */
public class lni {
    private static int e = Build.VERSION.SDK_INT;

    public static boolean e(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            loq.b("ContentValues", "Permission is empty or context is null");
            return false;
        }
        Hashtable hashtable = new Hashtable();
        if (e < 23) {
            return true;
        }
        if (!hashtable.containsKey(str) || ((Integer) hashtable.get(str)).intValue() == -1) {
            hashtable.put(str, Integer.valueOf(context.checkSelfPermission(str)));
        }
        return ((Integer) hashtable.get(str)).intValue() == 0;
    }
}
