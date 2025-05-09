package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.util.Map;

/* loaded from: classes8.dex */
public class gil {
    private static boolean e = true;

    public static String c(long j) {
        return UnitUtil.a((int) (j / 1000));
    }

    public static Activity aNe_(Context context) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "scanForActivity() context is null");
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return aNe_(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static AppCompatActivity d(Context context) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "getAppCompActivity() context is null");
            return null;
        }
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return d(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void e(Context context, int i) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "setRequestedOrientation() context is null");
        } else if (d(context) != null) {
            d(context).setRequestedOrientation(i);
        } else {
            aNe_(context).setRequestedOrientation(i);
        }
    }

    public static Window aNd_(Context context) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "getWindow() context is null");
            return null;
        }
        if (d(context) != null) {
            return d(context).getWindow();
        }
        return aNe_(context).getWindow();
    }

    public static void e(Context context, String str, long j) {
        if (!e) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "saveProgress() unSave progress");
            return;
        }
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "saveProgress() context or url is null");
            return;
        }
        if (j < 5000) {
            j = 0;
        }
        SharedPreferences aNc_ = aNc_(context);
        if (aNc_ != null) {
            aNc_.edit().putLong("Ingots_IngotsVideoPlayerUtils" + str, j).apply();
        }
    }

    public static long e(Context context, String str) {
        if (!e) {
            return 0L;
        }
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "getSavedProgress() context or url is null");
            return 0L;
        }
        SharedPreferences aNc_ = aNc_(context);
        if (aNc_ == null) {
            return 0L;
        }
        return aNc_.getLong("Ingots_IngotsVideoPlayerUtils" + str, 0L);
    }

    public static void c(Context context, String str) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "clearSavedProgress() context is null");
            return;
        }
        SharedPreferences aNc_ = aNc_(context);
        if (aNc_ == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "clearSavedProgress() sharePreference is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            aNc_.edit().clear().apply();
            return;
        }
        aNc_.edit().putLong("Ingots_IngotsVideoPlayerUtils" + str, 0L).apply();
    }

    private static SharedPreferences aNc_(Context context) {
        if (context == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "getSharePreference() context is null");
            return null;
        }
        return context.getSharedPreferences("IngotsVideoPlayer_progress", 0);
    }

    public static String b(Map<String, String> map, int i) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "getCurrentFromDataSource() map is null");
            return "";
        }
        int i2 = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (i2 == i) {
                return entry.getValue();
            }
            i2++;
        }
        return "";
    }

    public static boolean b(Map<String, String> map, String str) {
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            LogUtil.h("Ingots_IngotsVideoPlayerUtils", "dataSourceObjectsContainsUri() map or url is null");
            return false;
        }
        return map.containsValue(str);
    }
}
