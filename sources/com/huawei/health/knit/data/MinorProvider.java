package com.huawei.health.knit.data;

import android.content.Context;
import com.huawei.health.knit.section.model.SectionBean;

/* loaded from: classes3.dex */
public abstract class MinorProvider<T> extends BaseKnitDataProvider<T> {
    private boolean isDirty;
    protected T mData;
    private SectionBean mSectionBean;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.mSectionBean = sectionBean;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        this.mSectionBean = sectionBean;
        setSectionBeanData();
    }

    public void onMajorProviderDataArrived(T t) {
        this.mData = t;
        this.isDirty = true;
        setSectionBeanData();
    }

    private void setSectionBeanData() {
        SectionBean sectionBean = this.mSectionBean;
        if (sectionBean == null || !this.isDirty) {
            return;
        }
        onSetSectionBeanData(sectionBean, this.mData);
        this.isDirty = false;
    }

    public void onSetSectionBeanData(SectionBean sectionBean, T t) {
        this.mSectionBean.e(this.mData);
    }
}
