package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionDialog extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Context f2679a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private LinearLayout e;

    public SectionDialog(Context context) {
        this(context, null);
    }

    public SectionDialog(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionDialog(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2679a = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionDialog", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_dialog_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.layout_section_dialog);
        this.e = linearLayout;
        linearLayout.setVisibility(0);
        this.b = (HealthTextView) inflate.findViewById(R.id.tv_wechat_title);
        this.d = (HealthTextView) inflate.findViewById(R.id.tv_wechat_join);
        this.c = (HealthTextView) inflate.findViewById(R.id.tv_wechat_ignore);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionDialog", "no need to bind");
            return;
        }
        LogUtil.a("SectionDialog", "bindParamsToView");
        nsy.cMs_(this.b, nru.b(hashMap, "REPORT_TEXT", ""), true);
        nsy.cMs_(this.c, nru.b(hashMap, "LEFT_BUTTON_TEXT", ""), true);
        nsy.cMs_(this.d, nru.b(hashMap, "RIGHT_BUTTON_TEXT", ""), true);
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        HealthTextView healthTextView = this.c;
        if (healthTextView == null || a2 == null) {
            LogUtil.h("SectionDialog", "setOnSectionClickListener view or listener is null");
        } else {
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionDialog.this.e.setVisibility(8);
                    a2.onClick("LEFT_BUTTON_TEXT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionDialog.this.e.setVisibility(8);
                    a2.onClick("RIGHT_BUTTON_TEXT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionDialog";
    }
}
