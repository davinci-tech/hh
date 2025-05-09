package com.huawei.hms.update.ui;

import android.R;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.widget.Button;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.ButtonConfig;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
class HwDialogUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6106a = new Object();
    private static TypedValue b;

    /* renamed from: com.huawei.hms.update.ui.HwDialogUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6107a;

        static {
            int[] iArr = new int[ButtonConfig.Level.values().length];
            f6107a = iArr;
            try {
                iArr[ButtonConfig.Level.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6107a[ButtonConfig.Level.STRONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static int a(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    static void b(Button button, int i, int i2) {
        if (button == null) {
            return;
        }
        button.setTextColor(a(i, i2));
    }

    static int c(Context context, ButtonConfig.Level level) {
        return a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_pressed"));
    }

    static int d(Context context, ButtonConfig.Level level) {
        return level == ButtonConfig.Level.NORMAL ? a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color")) : a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_strong_text_color"));
    }

    static int b(Context context, ButtonConfig.Level level) {
        return level == ButtonConfig.Level.NORMAL ? a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color")) : a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_strong_text_color"));
    }

    static Drawable b(Context context, int i) {
        return context.getDrawable(i);
    }

    static ColorStateList a(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed}, new int[0]}, new int[]{i, i2});
    }

    static void a(Button button, int i, int i2) {
        if (button == null) {
            return;
        }
        button.setBackground(b(button.getContext(), ResourceLoaderUtil.getDrawableId("dialog_insert_bg")));
        button.setBackgroundTintList(a(i, i2));
    }

    static StateListDrawable a(Context context, int i, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(a(context, 25.0f));
        gradientDrawable.setColor(i);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(a(context, 25.0f));
        gradientDrawable2.setColor(i2);
        int a2 = a(context, 4.0f);
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) gradientDrawable, a2, 0, a2, 0);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, new InsetDrawable((Drawable) gradientDrawable2, a2, 0, a2, 0));
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, insetDrawable);
        return stateListDrawable;
    }

    static int a(Context context, ButtonConfig.Level level) {
        int i = AnonymousClass1.f6107a[level.ordinal()];
        if (i == 1) {
            return a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_error"));
        }
        if (i != 2) {
            return a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_normal"));
        }
        return a(context, ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_strong"));
    }

    static int a(Context context, int i) {
        return context.getColor(i);
    }

    static int a(Context context) {
        try {
            return ((UiModeManager) context.getSystemService("uimode")).getCurrentModeType();
        } catch (Exception e) {
            HMSLog.e("HwDialogUtil", "getCurrentUiModeType failed, " + e.getMessage());
            return 1;
        }
    }

    HwDialogUtil() {
    }
}
