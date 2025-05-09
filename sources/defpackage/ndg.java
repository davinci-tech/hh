package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes6.dex */
public class ndg {
    public static boolean e(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        return edit.commit();
    }

    public static String a(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static boolean d(Context context, String str, String str2, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i);
        return edit.commit();
    }

    public static int b(Context context, String str, String str2, int i) {
        return context.getSharedPreferences(str, 0).getInt(str2, i);
    }
}
