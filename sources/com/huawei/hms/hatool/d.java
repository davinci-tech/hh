package com.huawei.hms.hatool;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes4.dex */
public class d {
    public static String c(Context context, String str) {
        StringBuilder sb;
        String packageName = context.getPackageName();
        String n = a1.n("_hms_config_tag", "oper");
        if (TextUtils.isEmpty(n)) {
            sb = new StringBuilder("hms_");
            sb.append(str);
            sb.append("_");
            sb.append(packageName);
        } else {
            StringBuilder sb2 = new StringBuilder("hms_");
            sb2.append(str);
            sb2.append("_");
            sb2.append(packageName);
            sb2.append("_");
            sb2.append(n);
            sb = sb2;
        }
        return sb.toString();
    }

    public static void b(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            v.e("hmsSdk", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences b = b(context, str);
        if (b != null) {
            SharedPreferences.Editor edit = b.edit();
            edit.putString(str2, str3);
            edit.commit();
        }
    }

    public static void b(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            v.f("hmsSdk", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences b = b(context, str);
        if (b != null) {
            SharedPreferences.Editor edit = b.edit();
            edit.putLong(str2, j);
            edit.commit();
        }
    }

    private static SharedPreferences b(Context context, String str) {
        return context.getSharedPreferences(c(context, str), 0);
    }

    public static void a(Context context, String str, String... strArr) {
        String str2;
        if (context == null || TextUtils.isEmpty(str)) {
            str2 = "clearData(): parameter error.context,spname";
        } else {
            if (strArr != null) {
                SharedPreferences b = b(context, str);
                if (b != null) {
                    SharedPreferences.Editor edit = b.edit();
                    if (strArr.length == 0) {
                        edit.clear();
                        edit.commit();
                        return;
                    }
                    for (String str3 : strArr) {
                        if (b.contains(str3)) {
                            edit.remove(str3);
                            edit.commit();
                        }
                    }
                    return;
                }
                return;
            }
            str2 = "clearData(): No data need to be deleted,keys is null";
        }
        v.f("hmsSdk", str2);
    }

    public static Map<String, ?> a(Context context, String str) {
        return b(context, str).getAll();
    }

    public static String a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            v.f("hmsSdk", "context is null or spName empty or spkey is empty");
            return str3;
        }
        SharedPreferences b = b(context, str);
        return b != null ? b.getString(str2, str3) : str3;
    }

    public static long a(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            v.f("hmsSdk", "context is null or spName empty or spkey is empty");
            return j;
        }
        SharedPreferences b = b(context, str);
        return b != null ? b.getLong(str2, j) : j;
    }
}
