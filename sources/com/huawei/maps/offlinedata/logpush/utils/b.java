package com.huawei.maps.offlinedata.logpush.utils;

import android.content.Context;
import android.provider.Settings;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public final class b {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        boolean a2 = d.a();
        g.a("LocalSwitchUtils", "IS_CHINA_ROM is: " + a2);
        int i = a2 ? Settings.Secure.getInt(context.getContentResolver(), "user_experience_involved", 0) : 0;
        if (i == 0 && !a2) {
            i = Settings.Secure.getInt(context.getContentResolver(), "hw_app_analytics_state", i);
        }
        g.a("LocalSwitchUtils", "isEnabledUserExperience is: " + i);
        return i == 1;
    }
}
