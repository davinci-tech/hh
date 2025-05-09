package com.huawei.ui.commonui.ratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class HealthStarRatingBar extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f8923a;
    private Drawable b;
    private Drawable c;
    private int e;

    public HealthStarRatingBar(Context context) {
        super(context);
        cEH_(context, null);
    }

    public HealthStarRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        cEH_(context, attributeSet);
    }

    public HealthStarRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cEH_(context, attributeSet);
    }

    private void cEH_(Context context, AttributeSet attributeSet) {
        setOrientation(0);
        setGravity(17);
        if (attributeSet == null) {
            this.e = 5;
        } else {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthStarRatingBar);
            this.e = obtainStyledAttributes.getInteger(R$styleable.HealthStarRatingBar_starCount, 5);
            obtainStyledAttributes.recycle();
        }
        this.c = ContextCompat.getDrawable(context, R.mipmap._2131821467_res_0x7f11039b);
        this.b = ContextCompat.getDrawable(context, R.mipmap._2131821469_res_0x7f11039d);
        this.f8923a = ContextCompat.getDrawable(context, R.mipmap._2131821468_res_0x7f11039c);
        for (int i = 0; i < this.e; i++) {
            addView(getStarImageView());
        }
    }

    private ImageView getStarImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageDrawable(this.c);
        return imageView;
    }

    public void setStar(int i) {
        int i2 = this.e * 2;
        if (i >= i2) {
            i = i2;
        }
        if (i <= 0) {
            i = 0;
        }
        int i3 = i / 2;
        for (int i4 = 0; i4 < i3; i4++) {
            cEI_(getChildAt(i4), this.f8923a);
        }
        for (int i5 = i3; i5 < this.e; i5++) {
            cEI_(getChildAt(i5), this.c);
        }
        if (i % 2 == 1) {
            cEI_(getChildAt(i3), this.b);
        }
    }

    private void cEI_(View view, Drawable drawable) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }
}
