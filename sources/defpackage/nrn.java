package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Looper;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes6.dex */
public class nrn {
    public static int c(int i, float f, boolean z) {
        if (f < 0.0f || f > 1.0f) {
            return i;
        }
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) (f * (z ? 255 : 255 & (i >> 24)))) << 24);
    }

    public static int d(int i) {
        return BaseApplication.getContext().getResources().getColor(i);
    }

    public static int d(Context context, int i) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getColor(i);
    }

    public static int c(int i, float f) {
        return c(i, f, true);
    }

    public static int e(int i) {
        return ((int) (((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d))) >= 192 ? 1 : 0;
    }

    public static void cKd_(final View view) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            view.setLayerType(2, cKf_(0.0f));
        } else {
            HandlerExecutor.a(new Runnable() { // from class: nrn.1
                @Override // java.lang.Runnable
                public void run() {
                    nrn.cKd_(view);
                }
            });
        }
    }

    public static void cKe_(final View view) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            view.setLayerType(2, cKf_(1.0f));
        } else {
            HandlerExecutor.a(new Runnable() { // from class: nrn.2
                @Override // java.lang.Runnable
                public void run() {
                    nrn.cKe_(view);
                }
            });
        }
    }

    private static Paint cKf_(float f) {
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        return paint;
    }
}
