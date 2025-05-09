package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section_SleepReport_Text_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2752a;
    private HealthTextView c;
    private LinearLayout d;

    public Section_SleepReport_Text_02(Context context) {
        this(context, null);
    }

    public Section_SleepReport_Text_02(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section_SleepReport_Text_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_SleepReport_Text_02", "onCreateView");
        if (this.f2752a == null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_sleep_report_text, (ViewGroup) this, false);
            this.f2752a = inflate;
            this.d = (LinearLayout) inflate.findViewById(R.id.sleep_parse);
            HealthTextView healthTextView = (HealthTextView) this.f2752a.findViewById(R.id.sleep_report);
            this.c = healthTextView;
            healthTextView.setTextSize(2, 12.0f);
            if (nrt.a(context)) {
                this.c.setTextColor(context.getColor(R.color._2131296998_res_0x7f0902e6));
            } else {
                this.c.setTextColor(context.getColor(R.color._2131296921_res_0x7f090299));
            }
        }
        return this.f2752a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_SleepReport_Text_02", "no need to bind");
            return;
        }
        LogUtil.a("Section_SleepReport_Text_02", "bindParamsToView");
        if (this.c == null) {
            return;
        }
        nsy.cMs_(this.c, nru.b(hashMap, "REPORT_TEXT", ""), true);
        this.c.setPadding(nsn.c(BaseApplication.getContext(), 12.0f), 0, nsn.c(BaseApplication.getContext(), 12.0f), 0);
    }

    public void setRootLayoutVisibility(int i) {
        LinearLayout linearLayout = this.d;
        if (linearLayout != null) {
            linearLayout.setVisibility(i);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_SleepReport_Text_02";
    }
}
