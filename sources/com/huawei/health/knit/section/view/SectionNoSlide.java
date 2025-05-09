package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.knit.section.adapter.SectionNoSlideAdapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.dqj;
import defpackage.ebv;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionNoSlide extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private int f2707a;
    private Context b;
    private SectionNoSlideAdapter c;
    private HealthRecycleView d;
    private View e;
    private HealthSubHeader i;

    public SectionNoSlide(Context context) {
        super(context);
        this.f2707a = 0;
    }

    public SectionNoSlide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2707a = 0;
    }

    public SectionNoSlide(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2707a = 0;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.b = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_no_slide_recycle_view, (ViewGroup) this, false);
        this.e = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_no_slip_sub_header);
        this.i = healthSubHeader;
        nsy.d(healthSubHeader, 0, this.b.getResources().getString(R$string.IDS_vip_monthly_coupon), 4, 0);
        this.i.setSubHeaderBackgroundColor(0);
        this.d = (HealthRecycleView) this.e.findViewById(R.id.no_slide_recyclerview);
        this.d.setLayoutManager(new LinearLayoutManager(this.b, 0, false));
        View view = this.e;
        ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this));
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap.isEmpty()) {
            return;
        }
        ebv ebvVar = new ebv();
        ebvVar.i((List) nru.d(hashMap, "ITEM_TITLE", (Object) null));
        ebvVar.h((List) nru.d(hashMap, "ITEM_DESCRIPTION", (Object) null));
        ebvVar.b((List) nru.d(hashMap, "ITEM_STATUS_BACKGROUND", (Object) null));
        ebvVar.f((List) nru.d(hashMap, "ITEM_PAGE_ATTRIBUTE", (Object) null));
        ebvVar.d((List) nru.d(hashMap, "ITEM_JOIN_NUMBER", (Object) null));
        ebvVar.e((List) nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        this.i.setMoreViewClickListener((View.OnClickListener) nru.d(hashMap, "COMMON_CLICK_EVENT", (Object) null));
        if (this.c == null) {
            SectionNoSlideAdapter sectionNoSlideAdapter = new SectionNoSlideAdapter(this.b, ebvVar);
            this.c = sectionNoSlideAdapter;
            this.d.setAdapter(sectionNoSlideAdapter);
        }
        this.c.d(ebvVar);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        dqj.n();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionNoSlide";
    }
}
