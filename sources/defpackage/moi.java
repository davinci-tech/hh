package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class moi {
    public static String e(Context context, int i, Object... objArr) {
        if (context == null) {
            LogUtil.h("Suggestion_I18nUtil", "context == null");
            return "";
        }
        return String.format(Locale.ENGLISH, context.getResources().getString(i), objArr);
    }

    public static String e(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }
}
