package com.huawei.pluginsocialshare.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* loaded from: classes6.dex */
public class ClipImageLayout extends RelativeLayout {
    private ClipImageView b;

    public ClipImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ClipImageView(context);
        ClipImageMaskView clipImageMaskView = new ClipImageMaskView(context);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.b, layoutParams);
        addView(clipImageMaskView, layoutParams);
        int applyDimension = (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
        this.b.setHorizontalPadding(applyDimension);
        clipImageMaskView.setHorizontalPadding(applyDimension);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.b.setImageBitmap(bitmap);
    }

    public Bitmap cpu_() {
        return this.b.cpx_();
    }
}
