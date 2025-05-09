package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes7.dex */
public class trx {
    public static boolean c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("WearEngine_Permission_Identify_Store", 0).edit();
        edit.putString("WearEnginePermissionIdentity", str);
        return edit.commit();
    }

    public static String c(Context context) {
        return context == null ? "" : context.getSharedPreferences("WearEngine_Permission_Identify_Store", 0).getString("WearEnginePermissionIdentity", "");
    }
}
