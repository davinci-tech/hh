package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class hjl {
    private static final int d = hcn.a(BaseApplication.getContext(), 16.0f);

    public static void bgJ_(View view, HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
        if (view == null || healthTextView == null) {
            LogUtil.h("Track_AiPreSportViewUtils", "autoResize failed, mDataPanelStartView or mShowSportTimeText or mShowSportTimeText is null");
            return;
        }
        if (bgK_(view) + bgK_(healthTextView) > nsn.h(healthTextView.getContext()) - nsn.c(healthTextView.getContext(), 15.0f)) {
            if (healthTextView2 != null) {
                healthTextView2.setTextSize(0, healthTextView2.getTextSize() - nsn.c(healthTextView.getContext(), 2.0f));
            }
            if (healthTextView3 != null) {
                healthTextView3.setTextSize(0, healthTextView3.getTextSize() - nsn.c(healthTextView.getContext(), 1.0f));
            }
            healthTextView.setTextSize(0, healthTextView.getTextSize() - nsn.c(healthTextView.getContext(), 2.0f));
            bgJ_(view, healthTextView, healthTextView2, healthTextView3);
        }
    }

    private static int bgK_(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredWidth();
    }

    public static void c(HealthTextView healthTextView, HealthTextView healthTextView2) {
        if (healthTextView == null || healthTextView2 == null) {
            LogUtil.h("Track_AiPreSportViewUtils", "autoResizeTipsText userTips == null || userTips2 == null");
        } else if (bgK_(healthTextView) > (nsn.h(healthTextView.getContext()) - (nsn.c(healthTextView.getContext(), 24.0f) * 4)) - nsn.c(healthTextView.getContext(), 8.0f)) {
            healthTextView.setTextSize(0, healthTextView.getTextSize() - nsn.c(healthTextView.getContext(), 2.0f));
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() - nsn.c(healthTextView.getContext(), 2.0f));
            c(healthTextView, healthTextView2);
        }
    }

    public static void bgL_(ViewGroup viewGroup) {
        if (viewGroup != null) {
            HealthTextView healthTextView = (HealthTextView) viewGroup.findViewById(R.id.use_tips_1);
            healthTextView.setText(nsf.h(R.string._2130839974_res_0x7f0209a6));
            HealthTextView healthTextView2 = (HealthTextView) viewGroup.findViewById(R.id.use_tips_2);
            healthTextView2.setText(nsf.h(R.string._2130839975_res_0x7f0209a7));
            ((HealthTextView) viewGroup.findViewById(R.id.how_to_use_text)).setText(R.string._2130839970_res_0x7f0209a2);
            float dimension = BaseApplication.getContext().getResources().getDimension(LanguageUtil.h(viewGroup.getContext()) ? R.dimen._2131365073_res_0x7f0a0cd1 : R.dimen._2131365075_res_0x7f0a0cd3);
            healthTextView.setTextSize(0, dimension);
            healthTextView2.setTextSize(0, dimension);
            c(healthTextView, healthTextView2);
            if (healthTextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) healthTextView.getLayoutParams();
                marginLayoutParams.topMargin = nsn.r(viewGroup.getContext());
                healthTextView.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public static void bgI_(ImageButton imageButton, ViewGroup viewGroup) {
        Rect rect = new Rect();
        imageButton.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        viewGroup.getGlobalVisibleRect(rect2);
        if (rect.left == 0 && rect2.right == 0) {
            LogUtil.a("Track_AiPreSportViewUtils", "adjustSwitchCameraPreSportButton Rect 0");
            return;
        }
        if (rect.left - rect2.right < d) {
            ViewGroup.LayoutParams layoutParams = imageButton.getLayoutParams();
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) layoutParams).bottomMargin = (viewGroup.getHeight() + ((int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362565_res_0x7f0a0305))) * 2;
                imageButton.setLayoutParams(layoutParams);
            }
        }
    }
}
