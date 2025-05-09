package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.EnvironmentInfo;

/* loaded from: classes3.dex */
public class dpd {
    public static int d(Context context, boolean z) {
        Context context2 = BaseApplication.getContext();
        if (z) {
            Resources resources = context2.getResources();
            int h = (nsn.h(context) - resources.getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2)) - resources.getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1);
            if (h > 0) {
                return Math.min(h, 984);
            }
        }
        if (!EnvironmentInfo.k()) {
            return nla.e(true, 0);
        }
        int e = nla.e(false, 3);
        if (!nsn.ac(context2)) {
            return e;
        }
        int i = (e * 1764) / 984;
        int j = nsn.j();
        LogUtil.a("HealthLife_ServicesUIDialogUtils", "getWidth columnWidth ", Integer.valueOf(e), " height ", Integer.valueOf(i), " screenHeight ", Integer.valueOf(j));
        return i <= j ? e : (j * 984) / 1764;
    }

    public static int a(Context context, boolean z, int i) {
        if (!z) {
            return i;
        }
        Resources resources = com.huawei.haf.application.BaseApplication.e().getResources();
        int f = (nsn.f(context) - resources.getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0)) - resources.getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
        LogUtil.a("HealthLife_ServicesUIDialogUtils", "getHeight height ", Integer.valueOf(i), " windowHeight ", Integer.valueOf(f));
        if (f > 0 && f < i) {
            i = f;
        }
        return Math.min(i, 984);
    }
}
