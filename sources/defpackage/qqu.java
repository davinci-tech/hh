package defpackage;

import android.content.Context;
import android.text.TextUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes7.dex */
public class qqu {
    public static void a(Context context) {
        String num = Integer.toString(10006);
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferenceManager.e(context, num, "health_blood_sugar_diet_remind_time", String.valueOf(currentTimeMillis), new StorageParams());
    }

    public static boolean e(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(10006), "health_blood_sugar_diet_remind_time");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        try {
            return HiDateUtil.t(Long.parseLong(b)) != HiDateUtil.t(System.currentTimeMillis());
        } catch (NumberFormatException unused) {
            return true;
        }
    }
}
