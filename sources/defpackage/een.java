package defpackage;

import android.widget.LinearLayout;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class een {
    public static void aha_(LinearLayout linearLayout, HealthTextView healthTextView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMarginStart((int) e(2.0f));
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams2.setMarginStart((int) e(14.0f));
        linearLayout.setLayoutParams(layoutParams2);
        healthTextView.setLayoutParams(layoutParams);
        healthTextView.setTextSize(1, 6.0f);
    }

    public static float e(float f) {
        return f * BaseApplication.getContext().getResources().getDisplayMetrics().density;
    }
}
