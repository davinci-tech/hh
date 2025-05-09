package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes7.dex */
public class fgt {
    public static int c(String str, String str2, int i) {
        return c(str, str2, i, 0);
    }

    public static int c(String str, String str2, int i, int i2) {
        if (TextUtils.isEmpty(str) || i < 0) {
            return i2;
        }
        String[] split = str.split(str2);
        if (i < split.length && split.length != 0) {
            String str3 = split[i];
            if (TextUtils.isEmpty(str3)) {
                return i2;
            }
            try {
                return Integer.parseInt(str3);
            } catch (NumberFormatException e) {
                LogUtil.h("Suggestion_AudioVersionSplitUtil", "acquireValueByIndex: ", LogAnonymous.b((Throwable) e));
            }
        }
        return i2;
    }

    public static String d(String str, String str2, String str3) {
        return str3 + "_" + str + "_" + str2;
    }
}
