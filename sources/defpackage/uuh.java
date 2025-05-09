package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes7.dex */
public final class uuh {
    public static Uri fgy_(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    public static void fgw_(Uri.Builder builder, String str, Object obj) {
        if (obj == null || obj.toString() == null) {
            return;
        }
        builder.appendQueryParameter(str, obj.toString());
    }

    public static Long fgx_(Uri uri, String str) {
        String queryParameter = uri.getQueryParameter(str);
        if (queryParameter != null) {
            return Long.valueOf(Long.parseLong(queryParameter));
        }
        return null;
    }

    public static String e(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey() + "=" + e(entry.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    public static String e(String str) {
        utq.a(str);
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("Unable to encode using UTF-8");
        }
    }
}
