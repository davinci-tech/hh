package com.huawei.ui.commonui.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes9.dex */
public class HealthRatioImageView extends AppCompatImageView {
    private float e;

    public HealthRatioImageView(Context context) {
        super(context);
    }

    public HealthRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cAP_(context, attributeSet);
    }

    public HealthRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cAP_(context, attributeSet);
    }

    private void cAP_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthRatioImageView);
        this.e = obtainStyledAttributes.getFloat(R$styleable.HealthRatioImageView_widthHeightRatio, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public float getWidthHeightRatio() {
        return this.e;
    }

    public void setWidthHeightRatio(float f) {
        this.e = f;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.e <= 0.0f) {
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        setMeasuredDimension(size, (int) (size / this.e));
    }
}
