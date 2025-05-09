package com.huawei.health.knit.section.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionActiveRecordWeekAdapter;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ccq;
import defpackage.eeb;
import defpackage.koq;
import defpackage.nru;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionActiveRecordWeek extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private final List<eeb> f2666a;
    private SectionActiveRecordWeekAdapter b;
    private View d;

    public SectionActiveRecordWeek(Context context) {
        super(context);
        this.f2666a = new ArrayList();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.d == null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_record_week, (ViewGroup) this, false);
            this.d = inflate;
            HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section_active_record_week_recycler_view);
            ccq.b(healthRecycleView);
            healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            SectionActiveRecordWeekAdapter sectionActiveRecordWeekAdapter = new SectionActiveRecordWeekAdapter();
            this.b = sectionActiveRecordWeekAdapter;
            sectionActiveRecordWeekAdapter.e(this.f2666a);
            healthRecycleView.setAdapter(this.b);
        }
        return this.d;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            ReleaseLogUtil.a("R_SectionActiveRecordWeek", "bindParamsToView currentParams is null");
            return;
        }
        Object obj = hashMap.get("VIEW_LIST");
        if (koq.e(obj, eeb.class)) {
            this.f2666a.clear();
            this.f2666a.addAll((List) obj);
        }
        SectionActiveRecordWeekAdapter sectionActiveRecordWeekAdapter = this.b;
        if (sectionActiveRecordWeekAdapter != null) {
            sectionActiveRecordWeekAdapter.e(this.f2666a);
            this.b.b(nru.a(hashMap, "CLICK_EVENT_LISTENER", null));
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "R_SectionActiveRecordWeek";
    }
}
