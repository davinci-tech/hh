package com.huawei.health.knit.data;

import android.content.Context;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class KnitDataProviderGroup<T> extends MinorProvider<T> {
    private List<KnitDataProvider<T>> c = new ArrayList();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (koq.b(this.c)) {
            return false;
        }
        Iterator<KnitDataProvider<T>> it = this.c.iterator();
        while (it.hasNext()) {
            if (it.next().isActive(context)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        LogUtil.a("KnitDataProviderGroup", "loadDefaultData");
        if (koq.b(this.c)) {
            return;
        }
        Iterator<KnitDataProvider<T>> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().loadDefaultData(sectionBean);
        }
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        super.loadData(context, sectionBean);
        LogUtil.a("KnitDataProviderGroup", "loadData");
        if (koq.b(this.c)) {
            return;
        }
        Iterator<KnitDataProvider<T>> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().loadData(context, sectionBean);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, T t) {
        super.parseParams(context, hashMap, t);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public List<KnitDataProvider<T>> getDataProviderList() {
        return this.c;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.c)) {
            return;
        }
        for (KnitDataProvider<T> knitDataProvider : this.c) {
            if (knitDataProvider != null) {
                knitDataProvider.onDestroy();
            }
        }
        this.c.clear();
        this.c = null;
    }

    @Override // com.huawei.health.knit.data.MinorProvider
    public void onSetSectionBeanData(SectionBean sectionBean, T t) {
        LogUtil.a("KnitDataProviderGroup", "onSetSectionBeanData");
        if (koq.b(this.c)) {
            return;
        }
        for (KnitDataProvider<T> knitDataProvider : this.c) {
            if (knitDataProvider instanceof MinorProvider) {
                ((MinorProvider) knitDataProvider).onSetSectionBeanData(sectionBean, t);
            }
        }
    }

    public KnitDataProviderGroup e(List<KnitDataProvider<T>> list) {
        this.c.clear();
        this.c.addAll(list);
        return this;
    }
}
