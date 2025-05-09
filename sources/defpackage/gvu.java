package defpackage;

import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gvu {
    public static final String c = BaseApplication.getContext().getResources().getString(R.string._2130850262_res_0x7f0231d6);

    public static String c(int i) {
        if (i == 0 || i >= 6000) {
            return c;
        }
        return gvv.a(i);
    }

    public static String e(String str) {
        if (str == null || "".equals(str) || c.equals(str)) {
            return c;
        }
        try {
            return c(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return c;
        }
    }

    public static boolean a(int i) {
        char c2;
        if (i >= 10000000) {
            c2 = 1;
        } else if (i >= 1000000) {
            c2 = 2;
        } else {
            LogUtil.h("Track_PaceUtils", "isCompleteKiloOrMileIndex index exception ");
            c2 = 0;
        }
        if (i <= 0) {
            return false;
        }
        if (c2 != 1 || (i / 100000) % 100 == 0) {
            return c2 != 2 || ((i / 100000) / 10) % 100 == 0;
        }
        return false;
    }
}
