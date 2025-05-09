package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionActiveTrendListAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ccq;
import defpackage.edz;
import defpackage.nru;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionActiveRecordTrendList extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2665a;
    private SectionActiveTrendListAdapter b;
    private List<edz> c;
    private HealthRecycleView d;

    public SectionActiveRecordTrendList(Context context) {
        super(context);
        this.c = new ArrayList(10);
    }

    public SectionActiveRecordTrendList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ArrayList(10);
    }

    public SectionActiveRecordTrendList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList(10);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.f2665a == null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_record_trend_list, (ViewGroup) this, false);
            this.f2665a = inflate;
            HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.recycler_view_trend_list);
            this.d = healthRecycleView;
            ccq.b(healthRecycleView);
            this.d.setLayoutManager(new LinearLayoutManager(context, 1, false));
            SectionActiveTrendListAdapter sectionActiveTrendListAdapter = new SectionActiveTrendListAdapter(context, this.c);
            this.b = sectionActiveTrendListAdapter;
            this.d.setAdapter(sectionActiveTrendListAdapter);
        }
        return this.f2665a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.h("SectionActiveRecordTrendList", "no need to bind");
            return;
        }
        OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        if (a2 != null) {
            this.b.c(a2);
        }
        Object obj = hashMap.get("VIEW_LIST");
        if (obj instanceof List) {
            if (!this.c.isEmpty()) {
                this.c.clear();
            }
            this.c.addAll((List) obj);
            this.b.notifyDataSetChanged();
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionActiveRecordTrendList";
    }
}
