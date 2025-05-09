package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.updatesdk.a.b.c.c.c;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class afv {
    private static final Map<String, Map<String, String>> e = new HashMap(1);

    private static void c(Map<String, String> map) {
    }

    public static String c(Context context, String str) {
        Map<String, String> b;
        synchronized (afv.class) {
            String c = afr.e().c(context);
            Map<String, Map<String, String>> map = e;
            Map<String, String> map2 = map.get(c);
            String str2 = map2 != null ? map2.get(str) : "";
            if (TextUtils.isEmpty(str2) && (b = afw.b(context, "com.huawei.cloud.agdsdkV02")) != null) {
                map.put(c, b);
                str2 = b.get(str);
                c(b);
            }
            if (TextUtils.isEmpty(str2)) {
                afu.e("ServerUrlCache", "get grs url is empty!");
                return str2;
            }
            return str2 + c.CLIENT_API;
        }
    }
}
