package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ConfigurationHelper;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class gpa {

    /* renamed from: a, reason: collision with root package name */
    private static ArrayList<View> f12890a = new ArrayList<>();
    private static int b = 5;

    public static void aRn_(final ViewGroup viewGroup, int i, Activity activity) {
        if (viewGroup == null || activity == null) {
            LogUtil.h("MainGuideViewManager", "rootView is null or activity is null");
            return;
        }
        b = i;
        ((ImageView) viewGroup.findViewById(R.id.close_img)).setOnClickListener(new View.OnClickListener() { // from class: gpc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gpa.aRl_(viewGroup, view);
            }
        });
        final HealthViewPager healthViewPager = (HealthViewPager) viewGroup.findViewById(R.id.view_pager);
        f12890a = new ArrayList<>();
        Context context = BaseApplication.getContext();
        f12890a.add(LayoutInflater.from(context).inflate(R.layout.page_item_update_guide_1, (ViewGroup) null));
        f12890a.add(LayoutInflater.from(context).inflate(R.layout.page_item_update_guide_2, (ViewGroup) null));
        View inflate = LayoutInflater.from(context).inflate(R.layout.page_item_update_guide_3, (ViewGroup) null);
        aRk_(inflate);
        f12890a.add(inflate);
        healthViewPager.setAdapter(new ntq(f12890a));
        healthViewPager.setTag(0);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) viewGroup.findViewById(R.id.dots_page_indicator);
        healthDotsPageIndicator.setOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: gpa.3
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                HealthViewPager.this.setTag(Integer.valueOf(i2));
            }
        });
        healthDotsPageIndicator.setViewPager(healthViewPager);
        aRo_(viewGroup, healthViewPager, f12890a);
        aRj_(activity, f12890a, healthDotsPageIndicator);
        if (koq.d(f12890a, 2)) {
            aRi_(activity);
        }
        ash.a("MAIN_GUIDE_VIEW_FLAG", "true");
        ash.a("MAIN_NEED_NOVA_FLAG", "needNova");
    }

    static /* synthetic */ void aRl_(ViewGroup viewGroup, View view) {
        LogUtil.a("MainGuideViewManager", "close btn clicked");
        viewGroup.setVisibility(8);
        ObserverManagerUtil.c("MAIN_GUIDE_VIEW_COMPLETED", new Object[0]);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void aRk_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.text);
        if (nsn.ae(com.huawei.haf.application.BaseApplication.e())) {
            healthTextView.setText("点击开始健身、跳绳、瑜伽等运动");
        } else {
            healthTextView.setText("点击开始跑步、健身、骑行、跳绳等运动");
        }
    }

    public static void aRi_(final Activity activity) {
        if (koq.b(f12890a, 2)) {
            LogUtil.h("MainGuideViewManager", "index out of mPageViews size");
            return;
        }
        final ImageView imageView = (ImageView) f12890a.get(2).findViewById(R.id.image);
        if (imageView == null) {
            return;
        }
        imageView.post(new Runnable() { // from class: gpa.4
            @Override // java.lang.Runnable
            public void run() {
                if (imageView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
                    int h = nsn.h(activity) / gpa.b;
                    int width = ((h - imageView.getWidth()) / 2) + h;
                    LogUtil.a("MainGuideViewManager", "sport tab icon startMargin:", Integer.valueOf(width), "  spiltWidth:", Integer.valueOf(h));
                    if (width > 0) {
                        h = width;
                    }
                    layoutParams.setMarginStart(h);
                    imageView.setLayoutParams(layoutParams);
                }
            }
        });
    }

    private static void aRo_(final ViewGroup viewGroup, final HealthViewPager healthViewPager, final ArrayList<View> arrayList) {
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
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: gpg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    gpa.aRm_(HealthViewPager.this, i, healthButton, arrayList, viewGroup, view);
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

    static /* synthetic */ void aRm_(HealthViewPager healthViewPager, int i, HealthButton healthButton, ArrayList arrayList, ViewGroup viewGroup, View view) {
        int intValue = healthViewPager.getTag() instanceof Integer ? ((Integer) healthViewPager.getTag()).intValue() : i - 1;
        int intValue2 = healthButton.getTag() instanceof Integer ? ((Integer) healthButton.getTag()).intValue() : i - 1;
        if (intValue != intValue2) {
            LogUtil.b("MainGuideViewManager", "error state happened here! curIndex = ", Integer.valueOf(intValue), ", go back to btnIndex = ", Integer.valueOf(intValue2));
            healthViewPager.setCurrentItem(intValue2);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (i == arrayList.size()) {
                viewGroup.setVisibility(8);
                ObserverManagerUtil.c("MAIN_GUIDE_VIEW_COMPLETED", new Object[0]);
            } else {
                healthViewPager.setCurrentItem(i);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private static void aRj_(Activity activity, ArrayList<View> arrayList, HealthDotsPageIndicator healthDotsPageIndicator) {
        if (!nsn.cLh_(activity)) {
            LogUtil.a("MainGuideViewManager", "adaptTahiti is not needChange");
            return;
        }
        boolean z = ConfigurationHelper.getDensityDpi(activity.getResources()) > gpn.c();
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
        for (int i = 0; i < arrayList.size(); i++) {
            View view = arrayList.get(i);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.text);
            HealthButton healthButton = (HealthButton) view.findViewById(R.id.button);
            if (z) {
                float f = dimensionPixelSize;
                healthTextView.setTextSize(0, f);
                healthButton.setAutoTextSize(0, f);
            }
            if (!(healthTextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                LogUtil.h("MainGuideViewManager", "text.getLayoutParams() is not ViewGroup.MarginLayoutParams");
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) healthTextView.getLayoutParams();
                if (i == 0) {
                    marginLayoutParams.bottomMargin = activity.getResources().getDimensionPixelSize(R.dimen._2131363112_res_0x7f0a0528);
                    healthTextView.setLayoutParams(marginLayoutParams);
                } else if (z && i == 1) {
                    marginLayoutParams.bottomMargin = activity.getResources().getDimensionPixelSize(R.dimen._2131363160_res_0x7f0a0558);
                    healthTextView.setLayoutParams(marginLayoutParams);
                } else {
                    LogUtil.a("MainGuideViewManager", "loadGuideView index is 3 or densityChange is false");
                }
            }
        }
        if (!(healthDotsPageIndicator.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            LogUtil.h("MainGuideViewManager", "dotsPageIndicator LayoutParams is error");
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) healthDotsPageIndicator.getLayoutParams();
        marginLayoutParams2.bottomMargin = activity.getResources().getDimensionPixelSize(R.dimen._2131363083_res_0x7f0a050b);
        healthDotsPageIndicator.setLayoutParams(marginLayoutParams2);
    }

    public static boolean e() {
        return (!LanguageUtil.h(BaseApplication.getContext()) || Utils.o() || CommonUtil.bu() || EnvironmentInfo.k() || "true".equals(ash.b("MAIN_GUIDE_VIEW_FLAG"))) ? false : true;
    }
}
