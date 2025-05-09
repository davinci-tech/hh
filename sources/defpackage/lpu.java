package defpackage;

import com.huawei.operation.utils.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class lpu {
    private static String[] b = {"0", "1", "2"};
    private static String e;

    public static String b() {
        return e;
    }

    public static void a(String str) {
        if (lop.e("ODSA_SUPPORT_URL_ENCODER_TOKEN", (Boolean) false).booleanValue()) {
            try {
                e = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException unused) {
                loq.b("TokenUtils", "UnsupportedEncodingException.");
            }
        } else {
            e = str;
        }
        StringBuilder sb = new StringBuilder("update token to xxx, length is ");
        sb.append(str != null ? Integer.valueOf(str.length()) : Constants.NULL);
        loq.d("TokenUtils", sb.toString());
    }
}
