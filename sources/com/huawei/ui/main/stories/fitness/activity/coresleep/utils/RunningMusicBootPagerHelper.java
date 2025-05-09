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
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RunningMusicBootPagerHelper extends BaseBootPagerHelper {
    public RunningMusicBootPagerHelper(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected void configBootPage(LinearLayout linearLayout) {
        if (linearLayout == null) {
            return;
        }
        this.mContainer = linearLayout;
        ArrayList arrayList = new ArrayList();
        boolean bd = CommonUtil.bd();
        if (!Utils.j() || bd) {
            return;
        }
        arrayList.add(Integer.valueOf(R.drawable._2131431329_res_0x7f0b0fa1));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(BaseApplication.getContext().getString(R$string.IDS_other_music_guide_content));
        List<String> arrayList3 = new ArrayList<>();
        arrayList3.add(BaseApplication.getContext().getString(R$string.IDS_other_music_guide_title));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(BaseApplication.getContext().getString(R$string.IDS_sns_compelete));
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.sleep_activity_extra_layout, (ViewGroup) linearLayout, false);
        this.mBootPageView = (SleepBootMaskingLayerView) inflate.findViewById(R.id.sleep_boot_page);
        HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.sleep_boot_view_pager);
        plb plbVar = new plb(BaseApplication.getContext(), arrayList, arrayList2, arrayList4);
        plbVar.d(arrayList3);
        plbVar.drX_(this.mBootPageView);
        plbVar.a(healthViewPager);
        plbVar.c(this.mHealthPageResTrigger);
        healthViewPager.setAdapter(plbVar);
        inflate.findViewById(R.id.sleep_dots_page_indicator).setVisibility(8);
        linearLayout.addView(inflate);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper
    protected String getSharePreferenceString() {
        return "IS_FIRST_ENTRY_SPORT";
    }
}
