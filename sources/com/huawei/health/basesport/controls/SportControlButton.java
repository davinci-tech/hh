package com.huawei.health.basesport.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.huawei.health.R;

/* loaded from: classes3.dex */
public class SportControlButton extends ImageView {
    private int e;

    public SportControlButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = getResources().getDimensionPixelOffset(R.dimen._2131363847_res_0x7f0a0807);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.e;
        setMeasuredDimension(i3, i3);
    }
}
