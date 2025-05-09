package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class SportControlButton extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private int f3797a;

    public SportControlButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3797a = getResources().getDimensionPixelOffset(R.dimen._2131363847_res_0x7f0a0807);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.f3797a;
        setMeasuredDimension(i3, i3);
    }
}
