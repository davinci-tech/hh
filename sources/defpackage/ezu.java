package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import com.huawei.health.utils.LanguageUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ezu {
    private static LanguageUtils.SystemLocaleChangeReceiver b;

    public static void c(Context context) {
        if (b == null) {
            LogUtil.a("SystemLangChangeReceiverHelper", "Enter registerSystemLanguageChange()");
            b = new LanguageUtils.SystemLocaleChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            context.registerReceiver(b, intentFilter);
        }
    }

    public static void a(Context context) {
        if (b != null) {
            LogUtil.a("SystemLangChangeReceiverHelper", "Enter unregisterSystemLanguageChange()");
            try {
                context.unregisterReceiver(b);
            } catch (IllegalArgumentException e) {
                LogUtil.a("SystemLangChangeReceiverHelper", "unregisterSystemLanguageChange，IllegalArgumentException e = ", e.getMessage());
            } catch (RuntimeException e2) {
                LogUtil.a("SystemLangChangeReceiverHelper", "unregisterSystemLanguageChange，RuntimeException e = ", e2.getMessage());
            }
            b = null;
        }
    }
}
