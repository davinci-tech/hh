package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionSimpleRecordAdapter;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ecd;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionSimpleRecord extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f2734a;
    private Context b;
    private HealthRecycleView c;
    private SectionSimpleRecordAdapter d;
    private View e;

    public SectionSimpleRecord(Context context) {
        super(context);
    }

    public SectionSimpleRecord(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionSimpleRecord(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionSimpleRecord", "onCreateView");
        this.b = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_simple_record_layout, (ViewGroup) this, false);
        this.e = inflate;
        this.f2734a = (HealthSubHeader) inflate.findViewById(R.id.section_simple_sub_header);
        this.c = (HealthRecycleView) this.e.findViewById(R.id.simple_record_recyclerview);
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionSimpleRecord", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionSimpleRecord", "no need to bind");
            return;
        }
        nsy.d(this.f2734a, 0, nru.b(hashMap, "ITEM_TITLE", ""), 4, 8);
        this.f2734a.setSubHeaderBackgroundColor(0);
        List d = nru.d(hashMap, "LEFT_ICON_TEXT", String.class, new ArrayList());
        List d2 = nru.d(hashMap, "CLICK_EVENT_LISTENER", View.OnClickListener.class, new ArrayList());
        int d3 = nru.d((Map) hashMap, "RIGHT_ICON_IMAGE", 0);
        int d4 = nru.d((Map) hashMap, CommonConstant.RETKEY.STATUS, 0);
        if (this.d == null) {
            this.d = new SectionSimpleRecordAdapter(d4);
            this.c.setLayoutManager(new LinearLayoutManager(this.b));
            this.c.setNestedScrollingEnabled(false);
            this.c.a(false);
            this.c.d(false);
            this.c.setAdapter(this.d);
        }
        this.d.b(new ecd(d, d2, d3));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionSimpleRecord";
    }
}
