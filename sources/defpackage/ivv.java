package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class ivv {
    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("##");
        if (split.length > 0) {
            return split[0];
        }
        return null;
    }

    public static int b(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split("##");
        if (split.length <= 1 || (str2 = split[1]) == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            LogUtil.b("TAG", "getAppInfoUid() NumberFormatException");
            return 0;
        }
    }
}
