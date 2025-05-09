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
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBootMaskingLayerView;
import defpackage.plb;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class BloodSugarBootPagerHelper extends BaseBootPagerHelper {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f9841a;

    public BloodSugarBootPagerHelper(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected void configBootPage(LinearLayout linearLayout) {
        if (linearLayout == null) {
            return;
        }
        this.mContainer = linearLayout;
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.sleep_activity_extra_layout, (ViewGroup) linearLayout, false);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.sleep_boot_view_pager);
        ArrayList arrayList = new ArrayList();
        if (!LanguageUtil.h(this.mContext)) {
            arrayList.clear();
            arrayList.add(Integer.valueOf(R.drawable._2131427653_res_0x7f0b0145));
            arrayList.add(Integer.valueOf(R.drawable._2131427654_res_0x7f0b0146));
            arrayList.add(Integer.valueOf(R.drawable._2131427656_res_0x7f0b0148));
        } else {
            arrayList.clear();
            arrayList.add(Integer.valueOf(R.drawable._2131427652_res_0x7f0b0144));
            arrayList.add(Integer.valueOf(R.drawable._2131427654_res_0x7f0b0146));
            arrayList.add(Integer.valueOf(R.drawable._2131427655_res_0x7f0b0147));
        }
        plb plbVar = new plb(BaseApplication.getContext(), arrayList, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodSugarBootPagerHelper.4
            {
                add(BaseApplication.getContext().getString(R$string.IDS_blood_sugar_guide_input));
                add(BaseApplication.getContext().getString(R$string.IDS_blood_sugar_guide_select));
                add(BaseApplication.getContext().getString(R$string.IDS_blood_sugar_guide_activity));
            }
        }, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodSugarBootPagerHelper.3
            {
                add(BaseApplication.getContext().getString(R$string.IDS_bloodsugar_next));
                add(BaseApplication.getContext().getString(R$string.IDS_bloodsugar_next));
                add(BaseApplication.getContext().getString(R$string.IDS_device_show_complete));
            }
        });
        this.mBootPageView = (SleepBootMaskingLayerView) inflate.findViewById(R.id.sleep_boot_page);
        plbVar.drX_(this.mBootPageView);
        plbVar.a(healthViewPager);
        plbVar.e(this.f9841a);
        healthViewPager.setAdapter(plbVar);
        HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) inflate.findViewById(R.id.sleep_dots_page_indicator);
        healthDotsPageIndicator.setRtlEnable(LanguageUtil.bc(BaseApplication.getContext()));
        healthDotsPageIndicator.setViewPager(healthViewPager);
        linearLayout.addView(inflate);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        this.f9841a = iBaseResponseCallback;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected String getSharePreferenceString() {
        return "IS_FIRST_ENTRY_BLOOD_SUGAR";
    }
}
