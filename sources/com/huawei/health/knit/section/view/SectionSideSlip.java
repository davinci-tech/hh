package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionSideSlipAdapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ebv;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionSideSlip extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private SectionSideSlipAdapter f2733a;
    private HealthRecycleView b;
    private List<Integer> c;
    private HealthSubHeader d;
    private Context e;

    public SectionSideSlip(Context context) {
        super(context);
    }

    public SectionSideSlip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionSideSlip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_side_slip_layout, (ViewGroup) this, false);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_side_slip_sub_header);
        this.d = healthSubHeader;
        nsy.d(healthSubHeader, 0, context.getResources().getString(R$string.IDS_vip_monthly_coupon), 4, 0);
        this.d.setSubHeaderBackgroundColor(0);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section_side_slip_recycler_view);
        this.b = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        ebv ebvVar = new ebv();
        ebvVar.a((List) nru.d(hashMap, "ITEM_IMAGE", (Object) null));
        ebvVar.j((List) nru.d(hashMap, "NAME", (Object) null));
        ebvVar.c((List) nru.d(hashMap, "WEBVIEW_URL", (Object) null));
        List<Integer> list = (List) nru.d(hashMap, "ITEM_VIEW_TYPE", (Object) null);
        this.c = list;
        ebvVar.g(list);
        ebvVar.i((List) nru.d(hashMap, "ITEM_TITLE", (Object) null));
        ebvVar.h((List) nru.d(hashMap, "ITEM_DESCRIPTION", (Object) null));
        ebvVar.b((List) nru.d(hashMap, "ITEM_STATUS_BACKGROUND", (Object) null));
        ebvVar.f((List) nru.d(hashMap, "ITEM_PAGE_ATTRIBUTE", (Object) null));
        ebvVar.d((List) nru.d(hashMap, "ITEM_JOIN_NUMBER", (Object) null));
        ebvVar.e((List) nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        setAdapterData(ebvVar);
    }

    private void setAdapterData(ebv ebvVar) {
        SectionSideSlipAdapter sectionSideSlipAdapter = this.f2733a;
        if (sectionSideSlipAdapter == null) {
            SectionSideSlipAdapter sectionSideSlipAdapter2 = new SectionSideSlipAdapter(this.e, ebvVar);
            this.f2733a = sectionSideSlipAdapter2;
            this.b.setAdapter(sectionSideSlipAdapter2);
            this.f2733a.b(ebvVar);
        } else {
            sectionSideSlipAdapter.b(ebvVar);
        }
        this.d.setVisibility(8);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionSideSlip";
    }
}
