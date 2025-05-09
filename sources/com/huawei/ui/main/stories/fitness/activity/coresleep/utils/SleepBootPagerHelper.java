package com.huawei.ui.main.stories.fitness.activity.coresleep.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBootMaskingLayerView;
import defpackage.jdx;
import defpackage.plb;
import defpackage.pqg;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class SleepBootPagerHelper extends BaseBootPagerHelper {
    private IBaseResponseCallback b;
    private boolean d;

    public SleepBootPagerHelper(Context context) {
        super(context);
        this.d = true;
    }

    public boolean b() {
        String d = pqg.d(this.mContext, getSharePreferenceString());
        return "default_value".equals(d) || String.valueOf(true).equals(d);
    }

    public void b(boolean z) {
        this.d = z;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    public void showBootPage(final LinearLayout linearLayout) {
        if (linearLayout == null && this.mContainer == null) {
            return;
        }
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (!SleepBootPagerHelper.this.b()) {
                    if (SleepBootPagerHelper.this.mHealthPageResTrigger != null) {
                        SleepBootPagerHelper.this.mHealthPageResTrigger.setIsFirstEntryNoDataSleep(false);
                        return;
                    }
                    return;
                }
                SleepBootPagerHelper.this.showBootPageInner(linearLayout);
            }
        });
    }

    public void dsi_(LinearLayout linearLayout) {
        if (this.mBootPageView == null || this.mBootPageView.getVisibility() != 0) {
            ReleaseLogUtil.a("SleepBootPagerHelper", "onConfigurationChanged mBootPageView is null");
        } else {
            this.mContainer = null;
            showBootPageInner(linearLayout);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected void configBootPage(LinearLayout linearLayout) {
        if (!this.d || linearLayout == null) {
            return;
        }
        this.mContainer = linearLayout;
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.sleep_activity_extra_layout, (ViewGroup) linearLayout, false);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.sleep_boot_view_pager);
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        plb plbVar = new plb(BaseApplication.getContext(), arrayList, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper.2
        }, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper.4
        }, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.SleepBootPagerHelper.3
        });
        this.mBootPageView = (SleepBootMaskingLayerView) inflate.findViewById(R.id.sleep_boot_page);
        plbVar.drX_(this.mBootPageView);
        plbVar.a(healthViewPager);
        plbVar.c(this.mHealthPageResTrigger);
        plbVar.e(this.b);
        healthViewPager.setAdapter(plbVar);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate.findViewById(R.id.sleep_dots_page_indicator);
        healthDotsPageIndicator.setRtlEnable(LanguageUtil.bc(BaseApplication.getContext()));
        healthDotsPageIndicator.setViewPager(healthViewPager);
        linearLayout.addView(inflate);
        pqg.d(this.mContext, getSharePreferenceString(), String.valueOf(false));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected String getSharePreferenceString() {
        return "IS_FIRST_ENTRY_SLEEP_BED_SCIENCE";
    }
}
