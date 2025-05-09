package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class mco {
    private static final ConcurrentHashMap<String, SharedPreferences> d = new ConcurrentHashMap<>();

    private static SharedPreferences cfL_(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            mcj.b("SpUtils", "getSharedPreferences sharedFile is null.");
            return null;
        }
        ConcurrentHashMap<String, SharedPreferences> concurrentHashMap = d;
        SharedPreferences sharedPreferences = concurrentHashMap.get(str);
        if (sharedPreferences == null && (sharedPreferences = d(context).getSharedPreferences(str, 0)) != null) {
            concurrentHashMap.put(str, sharedPreferences);
        }
        return sharedPreferences;
    }

    private static Context d(Context context) {
        return context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
    }

    public static String c(Context context, String str, String str2, String str3) {
        SharedPreferences cfL_ = cfL_(context, str3);
        if (cfL_ == null) {
            mcj.b("SpUtils", "get key:" + str + " from " + str3 + " error. SharedPreferences is null.");
            return str2;
        }
        return cfL_.getString(str, str2);
    }

    public static void b(Context context, String str, String str2, String str3) {
        SharedPreferences cfL_ = cfL_(context, str3);
        if (cfL_ == null) {
            mcj.b("SpUtils", "put key:" + str + " into " + str3 + " error. SharedPreferences is null.");
            return;
        }
        SharedPreferences.Editor edit = cfL_.edit();
        edit.putString(str, str2);
        edit.apply();
    }
}
