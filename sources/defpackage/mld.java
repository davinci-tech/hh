package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class mld {
    public static int d(Context context, float f) {
        if (context == null) {
            LogUtil.h("DensityUtil", "dip2px: context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float c(Context context) {
        Resources resources;
        DisplayMetrics displayMetrics;
        if (context == null || (resources = context.getResources()) == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            return 1.0f;
        }
        return displayMetrics.density;
    }
}
