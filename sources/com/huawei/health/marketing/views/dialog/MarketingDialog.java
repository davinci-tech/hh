package com.huawei.health.marketing.views.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.huawei.health.R;
import defpackage.ntd;

/* loaded from: classes8.dex */
public class MarketingDialog extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2904a;
    private boolean b;
    private Context c;
    private long e;

    public MarketingDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.e = 0L;
        this.b = false;
        this.c = context;
    }

    private boolean a() {
        Context context = this.c;
        return context == null || ((Activity) context).isFinishing();
    }

    private void c(int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f2904a, "scaleX", 0.3f, 1.0f);
        long j = i;
        ofFloat.setDuration(j);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f2904a, "scaleY", 0.3f, 1.0f);
        ofFloat2.setDuration(j);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f2904a, "rotationY", 280.0f, 360.0f);
        ofFloat3.setDuration(j);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f2904a, "alpha", 0.1f, 1.0f);
        ofFloat4.setStartDelay(50L);
        ofFloat4.setDuration(j);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat3, ofFloat4, ofFloat, ofFloat2);
        animatorSet.setStartDelay(500L);
        animatorSet.setInterpolator(new Interpolator() { // from class: com.huawei.health.marketing.views.dialog.MarketingDialog.5
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                return (float) Math.sin((float) (f * 1.5707963267948966d));
            }
        });
        animatorSet.start();
    }

    @Override // android.app.Dialog
    public void show() {
        if (a() || this.b) {
            return;
        }
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        c(400);
        this.e = System.currentTimeMillis();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        ntd.b().cMC_(this);
        super.setContentView(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        ntd.b().cMC_(this);
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ntd.b().cMC_(this);
        super.setContentView(view, layoutParams);
    }
}
