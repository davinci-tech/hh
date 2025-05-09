package com.huawei.ui.commonui.flowlayout.textviewbuilder;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmk;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class SearchTagTextViewBuilder implements ITagTextViewBuilder {
    private int b;
    private Context d;
    private float f;
    private int e = R$color.textColorPrimary;

    /* renamed from: a, reason: collision with root package name */
    private String f8836a = "sans-serif-medium";
    private int c = R$drawable.round_rect_gray;

    public SearchTagTextViewBuilder(Context context) {
        context = context == null ? BaseApplication.e() : context;
        this.d = context;
        this.b = (context.getResources().getDisplayMetrics().widthPixels - (this.d.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b) * 2)) - nsn.c(this.d, 24.0f);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        HealthTextView healthTextView = new HealthTextView(this.d);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(0, textSize());
        healthTextView.setText(nmkVar.e());
        healthTextView.setTextColor(this.d.getResources().getColor(this.e));
        healthTextView.setBackgroundResource(this.c);
        healthTextView.setMaxLines(1);
        healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        healthTextView.setIncludeFontPadding(false);
        healthTextView.setMaxWidth(this.b);
        healthTextView.setTag(nmkVar);
        return healthTextView;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        float f = this.f;
        return f <= 0.0f ? nsn.c(this.d, 12.0f) : f;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(textSize());
        textPaint.setTypeface(Typeface.create(this.f8836a, 0));
        return Math.min(textPaint.measureText(nmkVar.e()), this.b);
    }

    public SearchTagTextViewBuilder b(int i) {
        this.f = this.d.getResources().getDimension(i);
        return this;
    }

    public SearchTagTextViewBuilder a(int i) {
        this.e = i;
        return this;
    }

    public SearchTagTextViewBuilder c(int i) {
        this.f8836a = this.d.getResources().getString(i);
        return this;
    }

    public SearchTagTextViewBuilder d(int i) {
        this.c = i;
        return this;
    }
}
