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
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section_SleepReport_Text_01 extends BaseSection {
    private Context b;
    private HealthTextView c;
    private LinearLayout d;

    public Section_SleepReport_Text_01(Context context) {
        this(context, null);
    }

    public Section_SleepReport_Text_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section_SleepReport_Text_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_SleepReport_Text_01", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_sleep_report_text, (ViewGroup) this, false);
        this.d = (LinearLayout) inflate.findViewById(R.id.sleep_parse);
        this.c = (HealthTextView) inflate.findViewById(R.id.sleep_report);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_SleepReport_Text_01", "no need to bind");
            return;
        }
        LogUtil.a("Section_SleepReport_Text_01", "bindParamsToView");
        String b = nru.b(hashMap, "REPORT_TEXT", "");
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            return;
        }
        nsy.cMs_(healthTextView, b, true);
        if (nrt.a(this.b)) {
            this.c.setTextColor(this.b.getColor(R.color._2131296998_res_0x7f0902e6));
        } else {
            this.c.setTextColor(this.b.getColor(R.color._2131296921_res_0x7f090299));
        }
    }

    public void setRootLayoutVisibility(int i) {
        LinearLayout linearLayout = this.d;
        if (linearLayout != null) {
            linearLayout.setVisibility(i);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_SleepReport_Text_01";
    }
}
