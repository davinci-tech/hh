package com.huawei.ui.commonui.flowlayout.textviewbuilder;

import android.content.Context;
import android.graphics.Typeface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmk;
import defpackage.nsn;

/* loaded from: classes9.dex */
public class OOBETagTextViewBuilder implements ITagTextViewBuilder {
    private Context b;

    public OOBETagTextViewBuilder(Context context) {
        this.b = context == null ? BaseApplication.e() : context;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        HealthTextView healthTextView = new HealthTextView(this.b);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(textSize());
        healthTextView.setText(nmkVar.e());
        healthTextView.setTextColor(this.b.getResources().getColor(R$color.textColorPrimary));
        healthTextView.setBackgroundResource(R$drawable.oobe_round_rect_gray);
        healthTextView.setTypeface(Typeface.create(this.b.getResources().getString(R$string.textFontFamilyRegular), 0));
        healthTextView.setTag(nmkVar);
        healthTextView.setMinWidth(this.b.getResources().getDimensionPixelSize(R.dimen._2131362190_res_0x7f0a018e));
        return healthTextView;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        return this.b.getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6) / nsn.g(this.b);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        return this.b.getResources().getDimensionPixelSize(R.dimen._2131362190_res_0x7f0a018e);
    }
}
