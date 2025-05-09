package com.huawei.ui.commonui.flowlayout.textviewbuilder;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmk;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class SportTagTextViewBuilder implements ITagTextViewBuilder {
    private Context d;

    public SportTagTextViewBuilder(Context context) {
        this.d = context == null ? BaseApplication.e() : context;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        HealthTextView healthTextView = new HealthTextView(this.d);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(textSize());
        healthTextView.setText(nmkVar.e());
        healthTextView.setTextColor(this.d.getResources().getColor(R$color.textColorPrimary));
        healthTextView.setBackgroundResource(R$drawable.round_rect_gray);
        healthTextView.setTag(nmkVar);
        return healthTextView;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        return (nsn.c(this.d, 12.0f) * 1.0f) / nsn.g(this.d);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        if (LanguageUtil.h(this.d)) {
            return (((this.d.getResources().getDisplayMetrics().widthPixels - (this.d.getResources().getDimensionPixelSize(R.dimen._2131362563_res_0x7f0a0303) * (r6 - 1))) - (this.d.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) * 2)) - (nsn.c(this.d, 24.0f) * r6)) / (nsn.ag(this.d) ? 5 : 3);
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((textSize() * nsn.g(this.d)) + 0.5f);
        textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        return textPaint.measureText(nmkVar.e());
    }
}
