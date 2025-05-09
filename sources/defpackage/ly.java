package defpackage;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class ly {
    public static void e(lv lvVar, Context context, String str) {
        try {
            String c = c(str);
            ma.a("mspl", "trade token: " + c);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            mb.c(lvVar, context, "pref_trade_token", c);
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "SaveTradeTokenError", th);
            ma.c(th);
        }
    }

    public static String c(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(";");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("result={") && split[i].endsWith("}")) {
                String[] split2 = split[i].substring(8, r3.length() - 1).split("&");
                int i2 = 0;
                while (true) {
                    if (i2 >= split2.length) {
                        break;
                    }
                    if (split2[i2].startsWith("trade_token=\"") && split2[i2].endsWith("\"")) {
                        str2 = split2[i2].substring(13, r1.length() - 1);
                        break;
                    }
                    if (split2[i2].startsWith("trade_token=")) {
                        str2 = split2[i2].substring(12);
                        break;
                    }
                    i2++;
                }
            }
        }
        return str2;
    }

    public static String c(lv lvVar, Context context) {
        String d = mb.d(lvVar, context, "pref_trade_token", "");
        ma.a("mspl", "get trade token: " + d);
        return d;
    }
}
