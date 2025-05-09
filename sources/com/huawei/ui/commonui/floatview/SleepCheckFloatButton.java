package com.huawei.ui.commonui.floatview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepCheckFloatButton extends FloatingSidingView {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8832a = "SleepCheckFloatButton";
    private static Map<String, SleepCheckFloatButton> c = new HashMap();
    Activity b;
    ImageView d;
    ImageView e;
    private boolean f;
    private Animator.AnimatorListener g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Runnable m;
    private Handler n;
    private ObjectAnimator o;

    private SleepCheckFloatButton(Activity activity) {
        super(activity);
        int i;
        this.h = true;
        this.i = false;
        this.j = true;
        this.f = true;
        this.n = new Handler(Looper.getMainLooper());
        this.m = new Runnable() { // from class: com.huawei.ui.commonui.floatview.SleepCheckFloatButton.2
            @Override // java.lang.Runnable
            public void run() {
                if (SleepCheckFloatButton.this.o != null) {
                    SleepCheckFloatButton.this.o.reverse();
                }
            }
        };
        this.g = new Animator.AnimatorListener() { // from class: com.huawei.ui.commonui.floatview.SleepCheckFloatButton.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int i2;
                LogUtil.a(SleepCheckFloatButton.f8832a, "animation end");
                SleepCheckFloatButton.this.h = !r3.h;
                SleepCheckFloatButton.this.j = true;
                SleepCheckFloatButton.this.f = true;
                ImageView imageView = SleepCheckFloatButton.this.d;
                if (SleepCheckFloatButton.this.h) {
                    i2 = R$drawable.btn_health_list_radio_nor;
                } else {
                    i2 = R$drawable.btn_health_list_radio_sel;
                }
                imageView.setImageResource(i2);
            }
        };
        this.b = activity;
        inflate(activity, R.layout.commonui_custom_sleep_check_float_button, this);
        this.e = (ImageView) findViewById(R.id.icon);
        ImageView imageView = (ImageView) findViewById(R.id.close);
        this.d = imageView;
        imageView.setVisibility(0);
        this.e.setImageResource(R$drawable.weight_card_bg_with_shadow_selected);
        ImageView imageView2 = this.d;
        if (this.h) {
            i = R$drawable.btn_health_list_radio_nor;
        } else {
            i = R$drawable.btn_health_list_radio_sel;
        }
        imageView2.setImageResource(i);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, "translationY", 0.0f, nsn.c(activity, 70.0f));
        this.o = ofFloat;
        ofFloat.setDuration(500L);
        this.o.addListener(this.g);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.floatview.SleepCheckFloatButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SleepCheckFloatButton.this.h) {
                    SleepCheckFloatButton.this.a();
                } else {
                    SleepCheckFloatButton.this.d();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static SleepCheckFloatButton cAu_(Activity activity) {
        String simpleName = activity.getClass().getSimpleName();
        if (!c.containsKey(simpleName)) {
            c.put(simpleName, new SleepCheckFloatButton(activity));
        }
        return c.get(simpleName);
    }

    @Override // com.huawei.ui.commonui.floatview.FloatingSidingView
    public void e() {
        c(true);
    }

    private void c(boolean z) {
        FrameLayout decorview = getDecorview();
        if (!z) {
            if (this.i) {
                decorview.removeView(this);
                return;
            }
            return;
        }
        if (decorview == null || this.i) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388693;
        if (LanguageUtil.bc(this.b)) {
            layoutParams.setMargins((int) this.b.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), layoutParams.topMargin, layoutParams.rightMargin, nrr.e(this.b, 100.0f));
            this.e.setRotationY(180.0f);
        } else {
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (int) this.b.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), nrr.e(this.b, 100.0f));
        }
        setLayoutParams(layoutParams);
        decorview.addView(this);
        this.i = true;
        this.h = true;
        this.j = true;
        this.f = true;
    }

    public void cAv_(Activity activity) {
        if (this.b != null) {
            this.b = null;
        }
        ObjectAnimator objectAnimator = this.o;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.o.end();
            this.o = null;
        }
        c.remove(activity.getClass().getSimpleName());
    }

    public void a() {
        synchronized (this) {
            if (this.j && this.h) {
                this.j = false;
                this.o.setStartDelay(200L);
                this.o.start();
            }
        }
    }

    public void d() {
        synchronized (this) {
            LogUtil.b(f8832a, "isreverseAnimator = " + this.f + " isShow = " + this.h);
            if (this.f) {
                this.f = false;
                if (!this.h) {
                    this.o.reverse();
                } else {
                    this.n.postDelayed(this.m, 500L);
                }
            }
        }
    }

    private FrameLayout getDecorview() {
        return (FrameLayout) this.b.getWindow().getDecorView().findViewById(android.R.id.content);
    }
}
