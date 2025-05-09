package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionGridAdapter;
import com.huawei.health.knit.section.utils.SpacesItemDecoration;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.nrr;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionGridRecord extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f2688a;
    private List<SpannableString> b;
    private SectionGridAdapter c;
    private Context d;
    private View e;
    private List<Integer> f;
    private OnClickSectionListener g;
    private List<String> h;
    private HealthSubHeader i;

    public SectionGridRecord(Context context) {
        super(context);
    }

    public SectionGridRecord(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionGridRecord(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionGridRecord", "onCreateView");
        this.d = context;
        b();
        return this.e;
    }

    private void b() {
        int i;
        if (this.e == null) {
            LogUtil.h("SectionGridRecord", "initView mainView is null, start to inflate");
            this.e = LayoutInflater.from(this.d).inflate(R.layout.section_grid_record_layout, (ViewGroup) this, false);
        }
        this.f2688a = (HealthRecycleView) this.e.findViewById(R.id.section_abnormal_week_layout_recyclerview);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.e.findViewById(R.id.section_blood_pressure_week_sub_header);
        this.i = healthSubHeader;
        healthSubHeader.setGuideLine();
        if (nsn.ag(this.d)) {
            i = 4;
        } else {
            i = nsn.r() ? 2 : 3;
        }
        this.f2688a.setLayoutManager(new GridLayoutManager(this.d, i));
        ccq.b(this.f2688a);
        SectionGridAdapter sectionGridAdapter = new SectionGridAdapter(this.d);
        this.c = sectionGridAdapter;
        this.f2688a.setAdapter(sectionGridAdapter);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        int i;
        LogUtil.a("SectionGridRecord", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionGridRecord", "no need to bind");
            return;
        }
        if (nsn.ag(this.d)) {
            i = 4;
        } else {
            i = nsn.r() ? 2 : 3;
        }
        RecyclerView.LayoutManager layoutManager = this.f2688a.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanCount(i);
            this.f2688a.setLayoutManager(gridLayoutManager);
        }
        while (this.f2688a.getItemDecorationCount() > 0) {
            this.f2688a.removeItemDecorationAt(0);
        }
        this.f2688a.addItemDecoration(new SpacesItemDecoration(nrr.b(this.d), nrr.b(this.d), i, new int[]{0, 0, 0, 0}));
        nsy.d(this.i, 0, nru.b(hashMap, "TITLE", ""), 8, 8);
        this.h = nru.d(hashMap, "TOP_TEXT", String.class, new ArrayList());
        this.f = nru.d(hashMap, "TOP_TEXT_COLOR", Integer.class, new ArrayList());
        this.b = nru.d(hashMap, "BOTTOM_UNIT", SpannableString.class, new ArrayList());
        OnClickSectionListener onClickSectionListener = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        this.g = onClickSectionListener;
        this.c.e(this.h, this.f, this.b, onClickSectionListener);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionGridRecord";
    }
}
