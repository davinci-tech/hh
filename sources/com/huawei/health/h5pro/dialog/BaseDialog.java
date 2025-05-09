package com.huawei.health.h5pro.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class BaseDialog extends Dialog {
    public BaseDialog gravity(int i) {
        getWindow().setGravity(i);
        return this;
    }

    public BaseDialog contentView(View view) {
        getWindow().setContentView(view);
        return this;
    }

    public BaseDialog contentView(int i) {
        getWindow().setContentView(i);
        return this;
    }

    public BaseDialog canceledOnTouchOutside(boolean z) {
        setCanceledOnTouchOutside(z);
        return this;
    }

    private void b(float f) {
        getWindow().getAttributes().dimAmount = f;
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
        requestWindowFeature(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(0);
        getWindow().setBackgroundDrawable(gradientDrawable);
        getWindow().setWindowAnimations(R.style.dialog_anim_bottom_scale);
        b(0.2f);
    }

    public BaseDialog(Context context) {
        this(context, 0);
    }
}
