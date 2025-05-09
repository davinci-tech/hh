package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionActiveStandDataCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2668a;
    private HealthTextView c;
    private HealthTextView e;

    public SectionActiveStandDataCard(Context context) {
        super(context);
    }

    public SectionActiveStandDataCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionActiveStandDataCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_active_stand_data_card, (ViewGroup) this, false);
        this.f2668a = inflate;
        this.e = (HealthTextView) inflate.findViewById(R.id.section_active_stand_data_card_sum_value);
        this.c = (HealthTextView) this.f2668a.findViewById(R.id.section_active_stand_data_card_average_value);
        return this.f2668a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.h("SCUI_SectionActiveStandDataCard", "no need to bind");
            return;
        }
        LogUtil.a("SCUI_SectionActiveStandDataCard", "bindParamsToView");
        nsy.cMr_(this.e, nru.e(hashMap, "CUMULATIVE_SUM", ""));
        nsy.cMr_(this.c, nru.e(hashMap, "DAILY_AVERAGE", ""));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SCUI_SectionActiveStandDataCard";
    }
}
