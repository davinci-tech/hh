package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;

/* loaded from: classes9.dex */
public class svw {
    public static String d(Context context, String str, String str2) {
        String n = OverSeaMangerUtil.c(context).e().n();
        return e(context, str, str2) + "?clientVersion=" + n;
    }

    public static String e(Context context, String str, String str2) {
        svg.a();
        String d = svg.d(context, str2, OverSeaMangerUtil.c(context).e().d());
        if (!swe.b((CharSequence) d, true) && d.contains("/WiseCloudWalletPassService/app/gateway") && d.endsWith("/WiseCloudWalletPassService/app/gateway")) {
            return d.replace("/WiseCloudWalletPassService/app/gateway", str);
        }
        return d + str;
    }
}
