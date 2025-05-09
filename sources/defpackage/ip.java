package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* loaded from: classes7.dex */
public final class ip {
    public static void b(Context context, String str, Map<String, String> map) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        if (edit != null) {
            for (String str2 : map.keySet()) {
                edit.putString(str2, map.get(str2));
            }
            edit.commit();
        }
    }

    public static String e(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }
}
