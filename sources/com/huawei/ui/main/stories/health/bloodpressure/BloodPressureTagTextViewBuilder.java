package com.huawei.ui.main.stories.health.bloodpressure;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmk;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class BloodPressureTagTextViewBuilder implements ITagTextViewBuilder {

    /* renamed from: a, reason: collision with root package name */
    private String f10149a = "sans-serif-medium";
    private float c;
    private int d;
    private Context e;

    public BloodPressureTagTextViewBuilder(Context context) {
        context = context == null ? BaseApplication.e() : context;
        this.e = context;
        this.d = (context.getResources().getDisplayMetrics().widthPixels - (this.e.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) * 2)) - nsn.c(this.e, 24.0f);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        HealthTextView healthTextView = new HealthTextView(this.e);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(0, textSize());
        healthTextView.setText(nmkVar.e());
        if (nmkVar.g()) {
            healthTextView.setBackgroundResource(R.drawable._2131427611_res_0x7f0b011b);
            healthTextView.setTextColor(ContextCompat.getColor(this.e, R$color.textColorPrimaryInverse));
        } else {
            healthTextView.setBackgroundResource(R.drawable._2131427610_res_0x7f0b011a);
            healthTextView.setTextColor(ContextCompat.getColor(this.e, R$color.textColorSecondary));
        }
        healthTextView.setMaxLines(1);
        healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        healthTextView.setIncludeFontPadding(false);
        healthTextView.setMaxWidth(this.d);
        healthTextView.setTag(nmkVar);
        return healthTextView;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        float f = this.c;
        return f <= 0.0f ? nsn.c(this.e, 12.0f) : f;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(textSize());
        textPaint.setTypeface(Typeface.create(this.f10149a, 0));
        return Math.min(textPaint.measureText(nmkVar.e()), this.d);
    }
}
