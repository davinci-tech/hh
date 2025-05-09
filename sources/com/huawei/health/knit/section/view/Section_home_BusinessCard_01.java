package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nru;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section_home_BusinessCard_01 extends BaseSection {
    private LinearLayout b;
    private ColumnLinearLayout e;

    public Section_home_BusinessCard_01(Context context) {
        this(context, null);
    }

    public Section_home_BusinessCard_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section_home_BusinessCard_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_home_busineeecard_01, (ViewGroup) this, false);
        this.b = (LinearLayout) inflate.findViewById(R.id.home_businesscard_layout);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_home_BusinessCard_01", "no need to bind");
            return;
        }
        Object d = nru.d(hashMap, "BUSINESS_HOME", (Object) null);
        if (d instanceof ColumnLinearLayout) {
            this.e = (ColumnLinearLayout) d;
        }
        this.b.addView(this.e);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_home_BusinessCard_01";
    }
}
