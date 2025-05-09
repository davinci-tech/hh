package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.section.adapter.Section16_9List_02Adapter;
import com.huawei.health.section.adapter.SectionX_YList_01Adapter;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import defpackage.eeh;
import defpackage.eet;

/* loaded from: classes3.dex */
public class Section16_9List_02 extends SectionX_YList_01 {
    public Section16_9List_02(Context context) {
        super(context);
    }

    public Section16_9List_02(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Section16_9List_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected View layoutInflater() {
        return LayoutInflater.from(this.context).inflate(R.layout.section1_1list_01_layout, (ViewGroup) this, false);
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected void setRecyclerViewLayout(eeh eehVar) {
        eet.c(this.context, this.sectionRecycleView, new HealthLinearLayoutManager(this.context), false, 0);
    }

    @Override // com.huawei.health.knit.section.view.SectionX_YList_01
    protected SectionX_YList_01Adapter getSectionListAdapter(Context context, eeh eehVar) {
        return new Section16_9List_02Adapter(context, eehVar);
    }
}
