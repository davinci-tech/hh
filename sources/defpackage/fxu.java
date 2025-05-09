package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class fxu {
    private e d;

    private fxu() {
    }

    static class b {
        private static final fxu d = new fxu();
    }

    public static final fxu c() {
        return b.d;
    }

    public void c(Context context, IBaseResponseCallback iBaseResponseCallback) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_plan_fragment_guide, (ViewGroup) null, false);
        if (inflate == null) {
            LogUtil.h("PlanGuideViewManager", "rootView is null");
            return;
        }
        LogUtil.a("PlanGuideViewManager", "showGuideView");
        this.d = new e(inflate.getContext(), inflate);
        final HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.guide_view_pager);
        ArrayList<View> arrayList = new ArrayList<>();
        arrayList.add(LayoutInflater.from(context).inflate(R.layout.plan_guide_page_item_1, (ViewGroup) null));
        arrayList.add(LayoutInflater.from(context).inflate(R.layout.plan_guide_page_item_2, (ViewGroup) null));
        arrayList.add(LayoutInflater.from(context).inflate(R.layout.plan_guide_page_item_3, (ViewGroup) null));
        healthViewPager.setAdapter(new ntq(arrayList));
        healthViewPager.setTag(0);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate.findViewById(R.id.guide_dots_page_indicator);
        healthDotsPageIndicator.setOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: fxu.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                healthViewPager.setTag(Integer.valueOf(i));
            }
        });
        healthDotsPageIndicator.setViewPager(healthViewPager);
        b(healthViewPager, arrayList, iBaseResponseCallback);
        this.d.show();
    }

    public boolean d() {
        e eVar = this.d;
        return eVar != null && eVar.isShowing();
    }

    public boolean e() {
        return (!LanguageUtil.m(BaseApplication.getContext()) || Utils.o() || CommonUtil.bu() || "true".equals(ash.b("PLAN_GUIDE_FLAG"))) ? false : true;
    }

    private void b(final HealthViewPager healthViewPager, final ArrayList<View> arrayList, final IBaseResponseCallback iBaseResponseCallback) {
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362996_res_0x7f0a04b4);
        Iterator<View> it = arrayList.iterator();
        final int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            View next = it.next();
            i++;
            HealthTextView healthTextView = (HealthTextView) next.findViewById(R.id.text);
            final HealthButton healthButton = (HealthButton) next.findViewById(R.id.button);
            healthButton.setTag(Integer.valueOf(i2));
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: fxw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fxu.this.aIl_(healthViewPager, i, healthButton, arrayList, iBaseResponseCallback, view);
                }
            });
            ((ImageView) next.findViewById(R.id.close_img)).setOnClickListener(new View.OnClickListener() { // from class: fxz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fxu.this.aIm_(view);
                }
            });
            if (nsn.r()) {
                float f = dimensionPixelSize;
                healthTextView.setTextSize(0, f);
                healthButton.setAutoTextSize(0, f);
                healthButton.setAutoTextInfo(dimensionPixelSize, 1, 0);
            }
            i2++;
        }
    }

    /* synthetic */ void aIl_(HealthViewPager healthViewPager, int i, HealthButton healthButton, ArrayList arrayList, IBaseResponseCallback iBaseResponseCallback, View view) {
        ash.a("PLAN_GUIDE_FLAG", "true");
        int intValue = healthViewPager.getTag() instanceof Integer ? ((Integer) healthViewPager.getTag()).intValue() : i - 1;
        int intValue2 = healthButton.getTag() instanceof Integer ? ((Integer) healthButton.getTag()).intValue() : i - 1;
        if (intValue != intValue2) {
            LogUtil.b("PlanGuideViewManager", "error state happened here! curIndex = ", Integer.valueOf(intValue), ", go back to btnIndex = ", Integer.valueOf(intValue2));
            healthViewPager.setCurrentItem(intValue2);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (i == arrayList.size()) {
                a();
                iBaseResponseCallback.d(0, Integer.valueOf(i));
            } else {
                healthViewPager.setCurrentItem(i);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void aIm_(View view) {
        LogUtil.a("PlanGuideViewManager", "close btn clicked");
        ash.a("PLAN_GUIDE_FLAG", "true");
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        e eVar = this.d;
        if (eVar != null) {
            eVar.dismiss();
            this.d = null;
        }
    }

    static class e extends BaseDialog {
        private final View c;
        private final Context e;

        e(Context context, View view) {
            super(context, R.style.DialogFullScreen, 0);
            this.c = view;
            this.e = context;
            e();
        }

        private void e() {
            Window window = getWindow();
            if (window == null) {
                return;
            }
            setContentView(this.c);
            aIn_(this.c);
            window.setType(1000);
            window.setBackgroundDrawableResource(R.color._2131296922_res_0x7f09029a);
        }

        private void aIn_(View view) {
            if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.bottomMargin = (nsn.ab(getContext()) ? nsn.q(getContext()) : 0) + this.e.getResources().getDimensionPixelOffset(c());
                view.setLayoutParams(layoutParams);
            }
        }

        private int c() {
            return nsn.aa(this.e) ? R.dimen._2131362886_res_0x7f0a0446 : R.dimen._2131363060_res_0x7f0a04f4;
        }
    }
}
