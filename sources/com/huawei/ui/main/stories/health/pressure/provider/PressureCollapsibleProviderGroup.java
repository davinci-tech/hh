package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import com.huawei.health.knit.data.KnitDataProviderGroup;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.qml;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class PressureCollapsibleProviderGroup<T> extends KnitDataProviderGroup<T> {
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10209a = false;
    private boolean e = true;

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (this.e) {
            return super.isActive(context);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.MinorProvider
    public void onSetSectionBeanData(SectionBean sectionBean, T t) {
        LogUtil.a("PressureCollapsibleProviderGroup", "onSetSectionBeanData");
        if (!(t instanceof qml)) {
            this.e = false;
            sectionBean.e(t);
        } else if (!((qml) t).i()) {
            LogUtil.a("PressureCollapsibleProviderGroup", "data is invalid");
            this.e = false;
            sectionBean.e(t);
        } else {
            this.e = true;
            super.onSetSectionBeanData(sectionBean, t);
            c(t);
            sectionBean.e(t);
        }
    }

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, T t) {
        super.parseParams(context, hashMap, t);
        hashMap.put("IS_GROUP_ARRAY_VISIBILITY", Integer.valueOf(this.d));
        hashMap.put("IS_SECTION_GROUP_EXPAND", Boolean.valueOf(this.f10209a));
        hashMap.put("BUTTON_TEXT", context.getResources().getString(R$string.IDS_sleep_expand_btn_show_more).toUpperCase());
        hashMap.put("ITEM_BUTTON_TEXT", context.getResources().getString(R$string.IDS_sleep_expand_btn_less).toUpperCase());
    }

    private void c(T t) {
        if ((t instanceof qml) && Utils.o()) {
            this.d = 0;
            LogUtil.a("PressureCollapsibleProviderGroup", "isOversea");
        }
    }
}
