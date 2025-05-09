package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import java.util.Map;

/* loaded from: classes2.dex */
public class afw {
    private static GrsClient b;

    public static String a(Context context, String str) {
        return afv.c(context, str);
    }

    private static boolean e(Context context) {
        afr e = afr.e();
        String d = e.d(context);
        String c = e.c(context);
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        if (!TextUtils.isEmpty(d)) {
            grsBaseInfo.setAppName(d);
        }
        if (!TextUtils.isEmpty(c)) {
            grsBaseInfo.setSerCountry(c);
        }
        try {
            b = new GrsClient(context, grsBaseInfo);
            return true;
        } catch (NullPointerException unused) {
            afu.a("GrsConfigObtainer", "init grs failed,context is null");
            return false;
        }
    }

    public static Map<String, String> b(Context context, String str) {
        synchronized (afw.class) {
            if (b == null) {
                afu.e("GrsConfigObtainer", "grs not init ,do init ");
                if (!e(context)) {
                    afu.e("GrsConfigObtainer", "grs init failed");
                    return null;
                }
            }
            return b.synGetGrsUrls(str);
        }
    }
}
