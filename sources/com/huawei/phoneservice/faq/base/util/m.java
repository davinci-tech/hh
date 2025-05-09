package com.huawei.phoneservice.faq.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes5.dex */
public final class m {
    public static void e(Context context, String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        try {
            if (l.e(str)) {
                str = FaqConstants.COMMON_FILE;
            }
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, str3);
            edit.apply();
        } catch (Exception e) {
            i.c("FaqSharePrefUtil", e.getMessage());
        }
    }

    public static String d(Context context, String str, String str2, String str3) {
        if (context == null) {
            return str3;
        }
        if (l.e(str)) {
            str = FaqConstants.COMMON_FILE;
        }
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public static void e(Context context, String str, String str2) {
        if (context == null) {
            i.c("FaqSharePrefUtil", "delete context is null");
            return;
        }
        if (l.e(str)) {
            str = FaqConstants.COMMON_FILE;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        if (sharedPreferences == null) {
            i.c("FaqSharePrefUtil", "delete sharedPreferences is null");
            return;
        }
        if (sharedPreferences.contains(str2)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                i.c("FaqSharePrefUtil", "delete editor is null");
            } else {
                edit.remove(str2);
                edit.apply();
            }
        }
    }

    public static void e(Context context, String str) {
        if (context != null) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("FAQ_MODULE_FILE_NAME", 0).edit();
                edit.putString("module", str);
                edit.commit();
            } catch (Exception e) {
                i.c("FaqSharePrefUtil", e.getMessage());
            }
        }
    }

    public static String d(Context context) {
        return context != null ? context.getSharedPreferences("FAQ_MODULE_FILE_NAME", 0).getString("module", "") : "";
    }

    public static void a(Context context, String str) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences("FAQ_LANGUAGE_FILE_NAME", 0).edit();
            edit.putString("language", str);
            edit.commit();
        }
    }

    public static String e(Context context) {
        return context != null ? context.getSharedPreferences("FAQ_LANGUAGE_FILE_NAME", 0).getString("language", "") : "";
    }
}
