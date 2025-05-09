package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes9.dex */
public class trw {
    public static boolean e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("WearEngine_Package_Name_Store", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("WearEnginePackageNameIdentity", new TreeSet());
        stringSet.add(str);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet("WearEnginePackageNameIdentity", stringSet);
        return edit.commit();
    }

    public static Set<String> a(Context context) {
        if (context == null) {
            return new TreeSet();
        }
        return context.getSharedPreferences("WearEngine_Package_Name_Store", 0).getStringSet("WearEnginePackageNameIdentity", new TreeSet());
    }
}
