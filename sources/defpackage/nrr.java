package defpackage;

import android.content.Context;
import com.huawei.health.R;

/* loaded from: classes6.dex */
public class nrr {
    public static int e(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int d(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
    }
}
