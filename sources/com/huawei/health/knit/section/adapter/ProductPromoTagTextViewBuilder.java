package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nmk;
import defpackage.nsn;

/* loaded from: classes3.dex */
public class ProductPromoTagTextViewBuilder implements ITagTextViewBuilder {
    private Context c;

    public ProductPromoTagTextViewBuilder(Context context) {
        this.c = context == null ? BaseApplication.e() : context;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public HealthTextView build(nmk nmkVar) {
        HealthTextView healthTextView = new HealthTextView(this.c);
        healthTextView.setGravity(17);
        healthTextView.setTextSize(textSize());
        healthTextView.setText(nmkVar.e());
        healthTextView.setTextColor(this.c.getResources().getColor(R$color.search_product_price_color));
        healthTextView.setBackgroundResource(R$drawable.round_rect_promo_label);
        healthTextView.setIncludeFontPadding(false);
        healthTextView.setPadding(0, 0, 0, 0);
        healthTextView.setMaxLines(1);
        healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        healthTextView.setTag(nmkVar);
        return healthTextView;
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textSize() {
        return (nsn.c(this.c, 9.0f) * 1.0f) / nsn.g(this.c);
    }

    @Override // com.huawei.ui.commonui.flowlayout.textviewbuilder.ITagTextViewBuilder
    public float textViewWidth(nmk nmkVar) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize((textSize() * nsn.g(this.c)) + 0.5f);
        textPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        return textPaint.measureText(nmkVar.e());
    }
}
