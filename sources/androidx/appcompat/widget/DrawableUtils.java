package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class DrawableUtils {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] EMPTY_STATE_SET = new int[0];
    public static final Rect INSETS_NONE = new Rect();

    public static boolean canSafelyMutateDrawable(Drawable drawable) {
        return true;
    }

    private DrawableUtils() {
    }

    public static Rect getOpticalBounds(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = Api29Impl.getOpticalInsets(drawable);
            return new Rect(opticalInsets.left, opticalInsets.top, opticalInsets.right, opticalInsets.bottom);
        }
        return Api18Impl.getOpticalInsets(DrawableCompat.unwrap(drawable));
    }

    static void fixDrawable(Drawable drawable) {
        String name = drawable.getClass().getName();
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT >= 31 || !"android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            return;
        }
        forceDrawableStateChange(drawable);
    }

    private static void forceDrawableStateChange(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(CHECKED_STATE_SET);
        } else {
            drawable.setState(EMPTY_STATE_SET);
        }
        drawable.setState(state);
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    /* loaded from: classes8.dex */
    static class Api18Impl {
        private static final Field sBottom;
        private static final Method sGetOpticalInsets;
        private static final Field sLeft;
        private static final boolean sReflectionSuccessful;
        private static final Field sRight;
        private static final Field sTop;

        /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
        static {
            /*
                r0 = 1
                r1 = 0
                r2 = 0
                java.lang.String r3 = "android.graphics.Insets"
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Throwable -> L2f
                java.lang.Class<android.graphics.drawable.Drawable> r4 = android.graphics.drawable.Drawable.class
                java.lang.String r5 = "getOpticalInsets"
                java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch: java.lang.Throwable -> L2f
                java.lang.reflect.Method r4 = r4.getMethod(r5, r6)     // Catch: java.lang.Throwable -> L2f
                java.lang.String r5 = "left"
                java.lang.reflect.Field r5 = r3.getField(r5)     // Catch: java.lang.Throwable -> L30
                java.lang.String r6 = "top"
                java.lang.reflect.Field r6 = r3.getField(r6)     // Catch: java.lang.Throwable -> L31
                java.lang.String r7 = "right"
                java.lang.reflect.Field r7 = r3.getField(r7)     // Catch: java.lang.Throwable -> L2d
                java.lang.String r8 = "bottom"
                java.lang.reflect.Field r3 = r3.getField(r8)     // Catch: java.lang.Throwable -> L33
                r8 = r0
                goto L35
            L2d:
                r7 = r2
                goto L33
            L2f:
                r4 = r2
            L30:
                r5 = r2
            L31:
                r6 = r2
                r7 = r6
            L33:
                r8 = r1
                r3 = r2
            L35:
                if (r8 == 0) goto L44
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sGetOpticalInsets = r4
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sLeft = r5
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sTop = r6
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sRight = r7
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sBottom = r3
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sReflectionSuccessful = r0
                goto L50
            L44:
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sGetOpticalInsets = r2
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sLeft = r2
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sTop = r2
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sRight = r2
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sBottom = r2
                androidx.appcompat.widget.DrawableUtils.Api18Impl.sReflectionSuccessful = r1
            L50:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.DrawableUtils.Api18Impl.<clinit>():void");
        }

        private Api18Impl() {
        }

        static Rect getOpticalInsets(Drawable drawable) {
            if (Build.VERSION.SDK_INT < 29 && sReflectionSuccessful) {
                try {
                    Object invoke = sGetOpticalInsets.invoke(drawable, new Object[0]);
                    if (invoke != null) {
                        return new Rect(sLeft.getInt(invoke), sTop.getInt(invoke), sRight.getInt(invoke), sBottom.getInt(invoke));
                    }
                } catch (IllegalAccessException | InvocationTargetException unused) {
                }
            }
            return DrawableUtils.INSETS_NONE;
        }
    }

    /* loaded from: classes8.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static Insets getOpticalInsets(Drawable drawable) {
            return drawable.getOpticalInsets();
        }
    }
}
