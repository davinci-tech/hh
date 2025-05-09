package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ScrollView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdv {
    public static Bitmap bGg_(View view) {
        if (view == null) {
            LogUtil.a("Track_ScreenShotUtils", "shotScreen view is null");
            return null;
        }
        if (view instanceof ScrollView) {
            return bGf_((ScrollView) view);
        }
        return bGe_(view);
    }

    private static Bitmap bGf_(ScrollView scrollView) {
        Bitmap bitmap;
        if (scrollView.getHeight() == 0) {
            LogUtil.a("Track_ScreenShotUtils", "getBitmapByView scrollView.height == 0");
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < scrollView.getChildCount(); i2++) {
            if (scrollView.getChildAt(i2) != null) {
                i += scrollView.getChildAt(i2).getHeight();
            }
        }
        try {
            bitmap = Bitmap.createBitmap(scrollView.getWidth(), i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            LogUtil.h("Track_ScreenShotUtils", ExceptionUtils.d(e));
            bitmap = null;
        }
        if (bitmap == null) {
            LogUtil.h("Track_ScreenShotUtils", "getBitmapByView 1 No enough memory");
            return null;
        }
        scrollView.draw(new Canvas(bitmap));
        return bitmap;
    }

    private static Bitmap bGe_(View view) {
        Bitmap bitmap;
        if (view.getHeight() == 0) {
            LogUtil.a("Track_ScreenShotUtils", "getBitmapByView 2 view.height == 0");
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            LogUtil.h("Track_ScreenShotUtils", e.getMessage());
            bitmap = null;
        }
        if (bitmap == null) {
            LogUtil.h("Track_ScreenShotUtils", "getBitmapByView view No enough memory");
            return null;
        }
        view.draw(new Canvas(bitmap));
        return bitmap;
    }
}
