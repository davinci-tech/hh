package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes8.dex */
public class abc {
    public static SharedPreferences ft_(Context context, String str, int i) {
        if (context == null) {
            return null;
        }
        Context createDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
        SharedPreferences sharedPreferences = createDeviceProtectedStorageContext.getSharedPreferences("move2DE_records", 0);
        if (!sharedPreferences.getBoolean(str, false)) {
            if (!createDeviceProtectedStorageContext.moveSharedPreferencesFrom(context, str)) {
                abd.b("SharedPreferenceUtil4DE", "Failed to move shared preference");
                return context.getSharedPreferences(str, i);
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, true);
            edit.apply();
        }
        context = createDeviceProtectedStorageContext;
        return context.getSharedPreferences(str, i);
    }
}
