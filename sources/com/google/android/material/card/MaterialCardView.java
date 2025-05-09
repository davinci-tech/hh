package com.google.android.material.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import com.google.android.material.internal.ThemeEnforcement;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class MaterialCardView extends CardView {
    private final MaterialCardViewHelper cardViewHelper;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100827_res_0x7f06049b);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, new int[]{R.attr._2131101150_res_0x7f0605de, R.attr._2131101151_res_0x7f0605df}, i, R.style.Widget_MaterialComponents_CardView, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this);
        this.cardViewHelper = materialCardViewHelper;
        materialCardViewHelper.loadFromAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public void setStrokeColor(int i) {
        this.cardViewHelper.setStrokeColor(i);
    }

    public int getStrokeColor() {
        return this.cardViewHelper.getStrokeColor();
    }

    public void setStrokeWidth(int i) {
        this.cardViewHelper.setStrokeWidth(i);
    }

    public int getStrokeWidth() {
        return this.cardViewHelper.getStrokeWidth();
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f) {
        super.setRadius(f);
        this.cardViewHelper.updateForeground();
    }
}
