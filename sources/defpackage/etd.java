package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class etd {
    public static String a() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        if (locale == null) {
            LogUtil.h("Track_MultiLanguageMap", "acquireLanguagePostFix(), locale == null");
            return null;
        }
        return mtj.e(locale);
    }

    public static String c() {
        String a2 = a();
        if (a2 == null || "zh-CN".equals(a2)) {
            return "";
        }
        return "_" + a2.replace(Marker.ANY_NON_NULL_MARKER, "_").replace(Constants.LINK, "_");
    }

    public static String c(String str) {
        if (str == null || "zh-CN".equals(str)) {
            return "";
        }
        return "_" + str.replace(Marker.ANY_NON_NULL_MARKER, "_").replace(Constants.LINK, "_");
    }
}
