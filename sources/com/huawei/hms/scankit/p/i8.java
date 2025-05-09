package com.huawei.hms.scankit.p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;

/* loaded from: classes9.dex */
public class i8 {
    public static int a(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", OsType.ANDROID));
    }

    public static Point b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static int a(Context context, int i) {
        return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
