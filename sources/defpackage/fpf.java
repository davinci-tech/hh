package defpackage;

import android.content.Context;
import android.provider.Settings;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class fpf {
    public static int c(Context context) {
        if (context == null) {
            LogUtil.b("Suggestion_LongViewSystemHelper", "getSystemBrightness context == null");
            return 0;
        }
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode") == 0 ? Settings.System.getInt(context.getContentResolver(), "screen_brightness") : 127;
        } catch (Settings.SettingNotFoundException unused) {
            LogUtil.b("Suggestion_LongViewSystemHelper", "getSystemBrightness SettingNotFoundException");
            return 0;
        }
    }
}
