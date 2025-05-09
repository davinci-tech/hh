package defpackage;

import android.content.SharedPreferences;

/* loaded from: classes3.dex */
public class bew {
    public static void a(String str, long j) {
        SharedPreferences.Editor edit = bec.e().getSharedPreferences("code_rule_sp", 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long d(String str, long j) {
        return bec.e().getSharedPreferences("code_rule_sp", 0).getLong(str, j);
    }

    public static void c(String str, String str2) {
        SharedPreferences.Editor edit = bec.e().getSharedPreferences("code_rule_sp", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String d(String str, String str2) {
        return bec.e().getSharedPreferences("code_rule_sp", 0).getString(str, str2);
    }
}
