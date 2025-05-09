package defpackage;

import android.content.Context;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;

/* loaded from: classes7.dex */
public class skj {
    public static boolean b(Context context) {
        return context != null && e(context) && d(context);
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        String country = context.getResources().getConfiguration().locale.getCountry();
        return "CN".equals(country) || "HK".equals(country) || "MO".equals(country) || "TW".equals(country);
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        return MLAsrConstants.LAN_ZH.equals(context.getResources().getConfiguration().locale.getLanguage());
    }
}
