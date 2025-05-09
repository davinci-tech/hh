package defpackage;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import java.net.URI;
import java.net.URISyntaxException;

/* loaded from: classes5.dex */
public class ktf {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String scheme = new URI(str.trim().replaceAll(" ", Constants.PERCENT_20).replaceAll("\\^", "%5E")).getScheme();
            return scheme == null ? "" : scheme;
        } catch (URISyntaxException unused) {
            ksy.b("UriUtils", "URISyntaxException", true);
            return "";
        }
    }
}
