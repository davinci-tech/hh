package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBootMaskingLayerView;
import defpackage.oju;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class ManagementBootPageHelper extends BaseBootPagerHelper {
    public ManagementBootPageHelper(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    public void configBootPage(LinearLayout linearLayout) {
        LogUtil.d("ManagementBootPageHelper", "configBootPage");
        if (linearLayout == null) {
            LogUtil.d("ManagementBootPageHelper", "container is null");
            return;
        }
        this.mContainer = linearLayout;
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.sleep_activity_extra_layout, (ViewGroup) linearLayout, false);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.sleep_boot_view_pager);
        ArrayList arrayList = new ArrayList();
        if (!LanguageUtil.h(this.mContext)) {
            arrayList.clear();
            arrayList.add(Integer.valueOf(R$drawable.management_boot_page_oversea));
        } else {
            arrayList.clear();
            arrayList.add(Integer.valueOf(R$drawable.management_boot_page));
        }
        oju ojuVar = new oju(BaseApplication.getContext(), arrayList, new ArrayList<String>() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.ManagementBootPageHelper.4
            {
                add(BaseApplication.getContext().getString(R$string.IDS_function_set_drag_des));
            }
        }, new ArrayList<String>() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.ManagementBootPageHelper.1
            {
                add(BaseApplication.getContext().getString(R$string.IDS_function_set_know));
            }
        });
        this.mBootPageView = (SleepBootMaskingLayerView) inflate.findViewById(R.id.sleep_boot_page);
        ojuVar.drX_(this.mBootPageView);
        ojuVar.a(healthViewPager);
        healthViewPager.setAdapter(ojuVar);
        ((HealthDotsPageIndicator) inflate.findViewById(R.id.sleep_dots_page_indicator)).setVisibility(8);
        linearLayout.addView(inflate);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    public String getSharePreferenceString() {
        return "IS_FIRST_ENTRY_FUNCTION_SET_MANAGEMENT";
    }
}
