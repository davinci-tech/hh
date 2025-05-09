package defpackage;

import android.content.res.Resources;
import android.graphics.Paint;
import android.util.TypedValue;

/* loaded from: classes6.dex */
public class pyb {
    private static final Object b = new Object();
    private static pyb c;

    public static pyb e() {
        pyb pybVar;
        synchronized (b) {
            if (c == null) {
                c = new pyb();
            }
            pybVar = c;
        }
        return pybVar;
    }

    public float dvs_(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }

    public float dvt_(Paint paint, String str) {
        if (str.length() == 0) {
            return 0.0f;
        }
        return paint.measureText(str, 0, str.length());
    }

    public float dvr_(Paint paint, String str) {
        if (str.length() == 0) {
            return 0.0f;
        }
        return dvs_(paint) * str.length();
    }

    public static float b(int i, float f) {
        return TypedValue.applyDimension(i, f, Resources.getSystem().getDisplayMetrics());
    }
}
