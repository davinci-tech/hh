package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SingleTextSection extends BaseSection {
    private View b;
    private HealthTextView d;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SingleTextSection(Context context) {
        this(context, null);
    }

    public SingleTextSection(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleTextSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SingleTextSection", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.single_text_layout, (ViewGroup) this, false);
        this.b = inflate;
        this.d = (HealthTextView) inflate.findViewById(R.id.heart_rate_reminder);
        return this.b;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SingleTextSection", "no need to bind");
            return;
        }
        LogUtil.a("SingleTextSection", "bindParamsToView");
        nsy.cMs_(this.d, nru.b(hashMap, "REPORT_TEXT", ""), true);
        c(hashMap, this.d);
    }

    private void c(HashMap<String, Object> hashMap, HealthTextView healthTextView) {
        int d = nru.d((Map) hashMap, "MARGIN_TOP", -1);
        if (d == -1 || !(healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
        layoutParams.topMargin = d;
        healthTextView.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SingleTextSection";
    }
}
