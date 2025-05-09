package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SingleButtonSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2753a;
    private HealthButton c;

    public SingleButtonSection(Context context) {
        this(context, null);
    }

    public SingleButtonSection(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleButtonSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        if (this.f2753a == null) {
            LogUtil.a("SingleButtonSection", "onCreateView");
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_single_button, (ViewGroup) this, false);
            this.f2753a = inflate;
            this.c = (HealthButton) inflate.findViewById(R.id.view_more_data);
            this.f2753a.setVisibility(8);
        }
        return this.f2753a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SingleButtonSection", "bindParamsToView");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SingleButtonSection", "no need to bind");
            return;
        }
        this.c.setText(nru.b(hashMap, "SINGLE_BUTTON", ""));
        final int d = nru.d((Map) hashMap, "CARD_INDEX", 0);
        if (d == 0 || d == 1) {
            nsy.cMA_(this.f2753a, 8);
            return;
        }
        nsy.cMA_(this.f2753a, 0);
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        if (a2 != null) {
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SingleButtonSection.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    a2.onClick(d);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SingleButtonSection";
    }
}
