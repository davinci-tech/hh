package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import defpackage.otb;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class MaterialSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        List<GlobalSearchContent> e = otb.e(a.w);
        if (otb.a(this) && e.size() > 3) {
            e = e.subList(0, 3);
        }
        sectionBean.e(e);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        super.parseParams(context, hashMap, list);
        hashMap.put("SECTION_SIMPLE_LIST_DATA", list);
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", context.getString(R.string._2130850462_res_0x7f02329e));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, a.w);
            hashMap.put("ITEM_LIMIT", 3);
        } else {
            hashMap.put("SHOWMORE", null);
            hashMap.put("SHOW_MORE_CLICK_EVENT", null);
        }
    }
}
