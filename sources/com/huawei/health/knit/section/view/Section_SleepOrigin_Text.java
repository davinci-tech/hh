package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrt;
import defpackage.nsn;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section_SleepOrigin_Text extends BaseSection {
    private HealthTextView b;
    private LinearLayout d;

    public Section_SleepOrigin_Text(Context context) {
        super(context);
    }

    public Section_SleepOrigin_Text(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Section_SleepOrigin_Text(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_sleep_report_text, (ViewGroup) this, false);
        this.d = (LinearLayout) inflate.findViewById(R.id.sleep_parse);
        this.b = (HealthTextView) inflate.findViewById(R.id.sleep_report);
        if (nrt.a(context)) {
            this.b.setTextColor(context.getColor(R.color._2131296998_res_0x7f0902e6));
        } else {
            this.b.setTextColor(context.getColor(R.color._2131296921_res_0x7f090299));
        }
        this.b.setPadding(0, 0, 0, nsn.c(context, 12.0f));
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        HealthTextView healthTextView;
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_SleepOrigin_Text", "no need to bind");
            return;
        }
        LogUtil.a("Section_SleepOrigin_Text", "bindParamsToView");
        Object obj = hashMap.get("CORE_SLEEP_BTN_TIPS");
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue() && (this.b.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams.setMarginStart(getContext().getResources().getDimensionPixelOffset(R.dimen._2131362973_res_0x7f0a049d));
            layoutParams.setMarginEnd(getContext().getResources().getDimensionPixelOffset(R.dimen._2131362973_res_0x7f0a049d));
            this.b.setLayoutParams(layoutParams);
        }
        Object obj2 = hashMap.get("REPORT_TEXT");
        if (!(obj2 instanceof SpannableString) || (healthTextView = this.b) == null) {
            return;
        }
        healthTextView.setVisibility(0);
        this.b.setText((SpannableString) obj2);
        this.b.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_SleepOrigin_Text";
    }
}
