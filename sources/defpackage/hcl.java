package defpackage;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import com.huawei.openalliance.ad.constant.OsType;

/* loaded from: classes4.dex */
public class hcl {
    public static boolean d(Context context) {
        if (context instanceof Activity) {
            return !((Activity) context).isFinishing();
        }
        return false;
    }

    public static int e(Context context, float f) {
        return (int) ((f * context.getApplicationContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels - c(context);
    }

    public static int c(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
        int dimensionPixelSize = identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
        return dimensionPixelSize == 0 ? e(context, 25.0f) : dimensionPixelSize;
    }
}
