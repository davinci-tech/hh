package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rhy {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PrivacyWhiteListUtil", "isWhiteUrl currentUrl isEmpty!");
            return false;
        }
        if (str.toLowerCase(Locale.ENGLISH).startsWith("https://") || str.toLowerCase(Locale.ENGLISH).startsWith("http://")) {
            String c = c(str);
            if (TextUtils.isEmpty(c)) {
                return false;
            }
            return c.contains("consumer.huawei.com") || c.contains("ec.europa.eu") || c.contains("termstest1.hwcloudtest.cn");
        }
        return d(str);
    }

    public static boolean d(String str) {
        return "hwpps://privacy/".equals(str) || Constants.OAID_SETTING_URL.equals(str);
    }

    private static String c(String str) {
        LogUtil.a("PrivacyWhiteListUtil", "getHostByJDK enter");
        try {
            return new URI(str).getHost();
        } catch (URISyntaxException unused) {
            LogUtil.a("PrivacyWhiteListUtil", "getHost error!");
            return null;
        }
    }
}
