package com.huawei.ui.main.stories.fitness.activity.coresleep.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBootMaskingLayerView;
import defpackage.plb;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class BloodPressureBootPagerHelper extends BaseBootPagerHelper {
    public BloodPressureBootPagerHelper(Context context) {
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
            arrayList.add(Integer.valueOf(R.drawable._2131427644_res_0x7f0b013c));
            arrayList.add(Integer.valueOf(R.drawable._2131427646_res_0x7f0b013e));
            arrayList.add(Integer.valueOf(R.drawable._2131427648_res_0x7f0b0140));
        } else {
            arrayList.clear();
            arrayList.add(Integer.valueOf(R.drawable._2131427643_res_0x7f0b013b));
            arrayList.add(Integer.valueOf(R.drawable._2131427645_res_0x7f0b013d));
            arrayList.add(Integer.valueOf(R.drawable._2131427647_res_0x7f0b013f));
        }
        plb plbVar = new plb(BaseApplication.getContext(), arrayList, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodPressureBootPagerHelper.3
            {
                add(BaseApplication.getContext().getString(R$string.IDS_blood_pressure_guide_input));
                add(BaseApplication.getContext().getString(R$string.IDS_blood_pressure_guide_select));
                add(BaseApplication.getContext().getString(R$string.IDS_blood_pressure_guide_activity));
            }
        }, new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodPressureBootPagerHelper.2
            {
                add(BaseApplication.getContext().getString(R$string.IDS_bloodsugar_next));
                add(BaseApplication.getContext().getString(R$string.IDS_bloodsugar_next));
                add(BaseApplication.getContext().getString(R$string.IDS_device_show_complete));
            }
        });
        this.mBootPageView = (SleepBootMaskingLayerView) inflate.findViewById(R.id.sleep_boot_page);
        plbVar.drX_(this.mBootPageView);
        plbVar.a(healthViewPager);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected String getSharePreferenceString() {
        return "IS_FIRST_ENTRY_BLOOD_PRESSURE";
    }
}
