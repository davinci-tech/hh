package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.wear.wallet.ui.dialog.WidgetBuilder;

/* loaded from: classes9.dex */
public class tlk {
    private static final String c = "com.huawei.wear.wallet.ui.dialog.HwDialogUtil";

    public static void faV_(Dialog dialog, int i, double d, boolean z) {
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            int dimensionPixelOffset = dialog.getContext().getResources().getDimensionPixelOffset(R.dimen._2131362023_res_0x7f0a00e7);
            String str = c;
            stq.c(str, "setDialogWidthAndHeight [dialog] phone not pad or open");
            attributes.y = i;
            attributes.gravity = 81;
            int b = tls.b(dialog.getContext());
            int e = tls.e(dialog.getContext());
            stq.c(str, "setDialogWidthAndHeight [dialog] phone width | height : " + b + " | " + e);
            if (b > e) {
                attributes.width = e - (dimensionPixelOffset * 2);
            } else {
                attributes.width = b - (dimensionPixelOffset * 2);
            }
            stq.c(str, "setDialogWidthAndHeight [dialog] lp w | h: " + attributes.width + " | " + attributes.height);
            window.setAttributes(attributes);
        }
    }

    public static void faS_(boolean z, Dialog dialog) {
        if (z) {
            return;
        }
        dialog.dismiss();
    }

    public static void faT_(View view, View view2, View view3) {
        if (view2.getVisibility() == 0 && view3.getVisibility() == 0) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public static void faY_(TextView textView, String str) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
    }

    public static void faU_(Context context, LinearLayout linearLayout) {
        int i;
        context.getResources().getConfiguration();
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen._2131362022_res_0x7f0a00e6);
        if (context instanceof Activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.heightPixels / 5;
        } else {
            i = 0;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        if (WidgetBuilder.a(context) || tls.a(context) == 1) {
            stq.c(c, "setDialogWidth [dialog] phone pad or open");
            tls.e(context);
            int b = tls.b(context);
            int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen._2131362024_res_0x7f0a00e8);
            layoutParams.height = -2;
            int i2 = b - (dimensionPixelOffset * 2);
            if (dimensionPixelOffset2 > i2) {
                layoutParams.width = i2;
                layoutParams.setMargins(0, i, 0, 0);
                return;
            } else {
                layoutParams.width = dimensionPixelOffset2;
                layoutParams.setMargins(0, 0, 0, 0);
                return;
            }
        }
        layoutParams.width = tls.b(context) - (dimensionPixelOffset * 2);
        layoutParams.setMargins(0, 0, 0, 0);
        String str = c;
        stq.c(str, "setDialogWidth [dialog] phone not pad or open w | h: " + layoutParams.width + " | " + layoutParams.height);
        stq.c(str, "setDialogWidth [dialog] lp top | w | h: " + i + "|" + layoutParams.width + " | " + layoutParams.height);
        linearLayout.setLayoutParams(layoutParams);
    }

    public static void faX_(final TextView textView, TextView textView2, Context context, int i) {
        if (textView2.getVisibility() == 0 || textView.getVisibility() != 0) {
            return;
        }
        textView.setPadding(0, context.getResources().getDimensionPixelOffset(i), 0, 0);
        textView.post(new Runnable() { // from class: tlk.4
            @Override // java.lang.Runnable
            public void run() {
                if (textView.getLineCount() == 1) {
                    textView.setGravity(1);
                } else {
                    textView.setGravity(GravityCompat.START);
                }
            }
        });
    }

    public static void faW_(Dialog dialog, int i, double d) {
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (WidgetBuilder.a(dialog.getContext())) {
                int dimensionPixelOffset = dialog.getContext().getResources().getDimensionPixelOffset(R.dimen._2131362022_res_0x7f0a00e6);
                int dimensionPixelOffset2 = dialog.getContext().getResources().getDimensionPixelOffset(R.dimen._2131362024_res_0x7f0a00e8);
                int b = tls.b(dialog.getContext());
                attributes.height = -2;
                int i2 = b - dimensionPixelOffset;
                if (dimensionPixelOffset2 > i2) {
                    attributes.width = i2;
                } else {
                    attributes.width = dimensionPixelOffset2;
                }
            } else {
                int dimensionPixelOffset3 = dialog.getContext().getResources().getDimensionPixelOffset(R.dimen._2131362022_res_0x7f0a00e6);
                stq.c(c, "current dialog device is not pad");
                attributes.y = i;
                attributes.gravity = 80;
                int b2 = tls.b(dialog.getContext());
                int e = tls.e(dialog.getContext());
                if (b2 > e) {
                    attributes.width = e - (dimensionPixelOffset3 * 2);
                } else {
                    attributes.width = b2 - (dimensionPixelOffset3 * 2);
                }
            }
            window.setAttributes(attributes);
        }
    }
}
