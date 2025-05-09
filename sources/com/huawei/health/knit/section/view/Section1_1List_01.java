package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.section.adapter.Section1_1List_01Adapter;
import com.huawei.health.section.adapter.SectionX_YList_01Adapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import defpackage.eeh;
import defpackage.eet;

/* loaded from: classes3.dex */
public class Section1_1List_01 extends SectionX_YList_01 {
    private int e;

    public Section1_1List_01(Context context) {
        super(context);
        this.e = 1;
    }

    public Section1_1List_01(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 1;
    }

    public Section1_1List_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 1;
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected View layoutInflater() {
        return LayoutInflater.from(this.context).inflate(R.layout.section1_1list_01_layout_new, (ViewGroup) this, false);
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected void setRecyclerViewLayout(eeh eehVar) {
        this.e = Math.max(1, eet.d(eehVar.a(), eehVar.j()));
        a();
    }

    private void a() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        int[] iArr = {getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), 0, getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d), 0};
        if (this.sectionRecycleView == null) {
            LogUtil.h("Section1_1List_01", "setLayoutManager() sectionRecycleView is null");
            return;
        }
        if (this.sectionRecycleView.getItemDecorationCount() > 0 && this.sectionRecycleView.getItemDecorationAt(0) != null) {
            this.sectionRecycleView.removeItemDecorationAt(0);
        }
        this.sectionRecycleView.addItemDecoration(new ColumnLayoutItemDecoration(dimensionPixelSize, 0, this.e, iArr));
        this.sectionRecycleView.setLayoutManager(new HealthLinearLayoutManager(this.context, 0, false));
        this.sectionRecycleView.setOnScrollListener(new MarketingBiUtils.FitnessOnScrollListener(this.mPageType, this.mSubHeaderTitle, 3));
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected SectionX_YList_01Adapter getSectionListAdapter(Context context, eeh eehVar) {
        return new Section1_1List_01Adapter(context, eehVar);
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }
}
