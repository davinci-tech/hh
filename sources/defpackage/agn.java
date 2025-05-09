package defpackage;

import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import java.util.Locale;

/* loaded from: classes3.dex */
public class agn {
    private static int c = -1;

    public static boolean d() {
        if (c == -1) {
            c = (MLAsrConstants.LAN_ZH.equals(agv.d("ro.product.locale.language")) && "CN".equals(agv.d("ro.product.locale.region"))) ? 0 : 1;
        }
        return c == 1;
    }

    public static String c() {
        StringBuilder sb;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        String script = locale.getScript();
        if (TextUtils.isEmpty(script) && TextUtils.isEmpty(variant)) {
            sb = new StringBuilder();
            sb.append(language);
            sb.append("_");
            sb.append(country);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("_");
            sb2.append(script);
            sb2.append("_");
            sb2.append(country);
            sb2.append("_");
            sb2.append(variant);
            sb = sb2;
        }
        return sb.toString();
    }
}
