package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.core.view.GravityCompat;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.SplashIconListener;
import com.huawei.openalliance.ad.nt;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes9.dex */
public class PPSSplashIconView {

    /* renamed from: a, reason: collision with root package name */
    private ContentRecord f7957a;
    private ag b;
    private ae c;
    private af d;
    private CornerImageView e;
    private CornerImageView f;
    private CornerImageView g;
    private AutoScaleSizeRelativeLayout h;
    private WindowManager i;
    private SplashIconListener j;
    private nt k;
    private CountDownTimer l;
    private Context m;
    private int n;
    private int o;
    private int p;
    private String q;
    private String r;
    private boolean s;
    private int u;
    private int v;
    private AnimatorSet w;
    private AnimatorSet x;
    private MaterialClickInfo z;
    private boolean t = false;
    private boolean y = false;

    public void updatePos(int i, int i2) {
        if (!this.t) {
            ho.c("PPSSplashIconView", "updatePos, not start");
            return;
        }
        ae aeVar = this.c;
        if (aeVar == null || !aeVar.isAttachedToWindow()) {
            ho.c("PPSSplashIconView", "updatePos, not attach");
            return;
        }
        if (i2 <= 0 || i2 > this.u) {
            i2 = this.v;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.flags = 40;
        layoutParams.format = 1;
        layoutParams.gravity = i == 1 ? 8388659 : 8388661;
        layoutParams.x = ao.a(this.m, 4.0f);
        layoutParams.y = ao.a(this.m, i2 + 28);
        this.i.updateViewLayout(this.c, layoutParams);
        layoutParams.x = i == 1 ? ao.a(this.m, 44.0f) : 0;
        layoutParams.y = ao.a(this.m, i2);
        this.i.updateViewLayout(this.d, layoutParams);
    }

    public void start() {
        boolean z = this.t;
        if (z || !this.s) {
            ho.c("PPSSplashIconView", "already start or not init, hasStart= %s", Boolean.valueOf(z));
            h();
            return;
        }
        this.t = true;
        if (dc.k() == null || (dc.i() == null && dc.j() == null)) {
            ho.c("PPSSplashIconView", "not show icon");
            dc.a((Bitmap) null);
            dc.a((Drawable) null);
            h();
            return;
        }
        dc.c(null);
        if (this.b == null || this.e == null) {
            ho.c("PPSSplashIconView", "not real init");
            h();
            return;
        }
        if (dc.i() != null) {
            this.e.setImageDrawable(dc.i());
            dc.a((Drawable) null);
        } else {
            this.e.setImageBitmap(dc.j());
            dc.a((Bitmap) null);
        }
        if (this.q == null && !TextUtils.isEmpty(this.r)) {
            a(this.r);
            if (this.q == null) {
                ho.c("PPSSplashIconView", "cachedPath is null");
                nt ntVar = this.k;
                if (ntVar != null) {
                    ntVar.a(2);
                }
                h();
                return;
            }
        }
        ho.b("PPSSplashIconView", "start");
        az.a(this.m, this.q, new az.a() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.2
            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a(final Drawable drawable) {
                ho.b("PPSSplashIconView", "start - image load success");
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSSplashIconView.this.f != null) {
                            PPSSplashIconView.this.f.setImageDrawable(drawable);
                        }
                        if (PPSSplashIconView.this.g != null) {
                            PPSSplashIconView.this.g.setImageDrawable(drawable);
                        }
                        PPSSplashIconView.this.b();
                        if (PPSSplashIconView.this.k != null) {
                            PPSSplashIconView.this.k.a();
                        }
                    }
                });
            }

            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a() {
                ho.b("PPSSplashIconView", "start - image load failed");
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSSplashIconView.this.k != null) {
                            PPSSplashIconView.this.k.a(3);
                        }
                        PPSSplashIconView.this.i();
                        PPSSplashIconView.this.h();
                    }
                });
            }
        }, null);
    }

    public void setAdIconListener(SplashIconListener splashIconListener) {
        this.j = splashIconListener;
    }

    public void destroy() {
        SplashIconListener splashIconListener;
        i();
        if (this.c == null || (splashIconListener = this.j) == null || !this.y) {
            return;
        }
        splashIconListener.onIconDismiss(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        g();
        this.e = null;
        a(this.b);
        a(this.c);
        a(this.d);
        AnimatorSet animatorSet = this.x;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.x.cancel();
        }
        AnimatorSet animatorSet2 = this.w;
        if (animatorSet2 == null || !animatorSet2.isRunning()) {
            return;
        }
        this.w.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        SplashIconListener splashIconListener = this.j;
        if (splashIconListener != null) {
            splashIconListener.onIconDismiss(0);
        }
    }

    private void g() {
        ho.b("PPSSplashIconView", "cancelDisplayCountTask");
        CountDownTimer countDownTimer = this.l;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ho.b("PPSSplashIconView", "startDisplayCountTask, duration: %s", Integer.valueOf(this.p));
        CountDownTimer countDownTimer = this.l;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(this.p, 1000L) { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.8
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ho.b("PPSSplashIconView", "onFinish");
                PPSSplashIconView.this.i();
                if (PPSSplashIconView.this.j != null) {
                    PPSSplashIconView.this.j.onIconDismiss(2);
                }
            }
        };
        this.l = countDownTimer2;
        countDownTimer2.start();
    }

    private float e() {
        int f;
        float a2 = bz.a(this.m).a(this.b);
        int i = this.m.getResources().getDisplayMetrics().heightPixels;
        Point point = new Point();
        this.i.getDefaultDisplay().getRealSize(point);
        float g = (((float) point.y) - a2) - ((float) i) > ((float) dd.j(this.m)) ? dd.g(this.m) : 0.0f;
        if (!bz.a(this.m).a(this.m) || bz.a(this.m).a(this.b) <= 0) {
            f = dd.f(this.m) - ao.h(this.m);
        } else {
            if (g == 0.0f) {
                return 0.0f;
            }
            f = bz.a(this.m).a(this.b);
        }
        return (f * 1.0f) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.b == null || this.c == null || this.e == null) {
            ho.c("PPSSplashIconView", "initAndStartAnimation, not init");
            return;
        }
        int width = this.g.getWidth();
        int height = this.g.getHeight();
        float pivotX = this.g.getPivotX();
        float pivotY = this.g.getPivotY();
        int[] iArr = new int[2];
        this.g.getLocationOnScreen(iArr);
        float e = e();
        ho.b("PPSSplashIconView", "destWidth=%s destHeight=%s destX=%s  destY=%s locationD= %s %s systemDiff = %s", Integer.valueOf(width), Integer.valueOf(height), Float.valueOf(pivotX), Float.valueOf(pivotY), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Float.valueOf(e));
        ho.b("PPSSplashIconView", "S= %s %s, I=%s %s ", Float.valueOf(this.e.getPivotX()), Float.valueOf(this.e.getPivotY()), Float.valueOf(this.f.getPivotX()), Float.valueOf(this.f.getPivotY()));
        ho.b("PPSSplashIconView", "splash Width= %s Height=%s, splashIcon Width=%s Height=%s", Integer.valueOf(this.e.getWidth()), Integer.valueOf(this.e.getHeight()), Integer.valueOf(this.f.getWidth()), Integer.valueOf(this.f.getHeight()));
        if (this.e.getHeight() == 0 || this.e.getWidth() == 0 || this.f.getWidth() == 0 || this.f.getHeight() == 0) {
            i();
            h();
            return;
        }
        CornerImageView cornerImageView = this.e;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(cornerImageView, "translationX", 0.0f, (iArr[0] + pivotX) - cornerImageView.getPivotX());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.e, "translationY", 0.0f, ((ao.a(this.m, this.o + 32) + pivotY) - this.e.getPivotY()) - e);
        float f = width * 1.0f;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.e, "scaleX", 1.0f, f / (r14.getWidth() * 1.0f));
        float f2 = height * 1.0f;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.e, "scaleY", 1.0f, f2 / (r14.getHeight() * 1.0f));
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f, "translationX", 0.0f, (iArr[0] + pivotX) - this.e.getPivotX());
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.f, "translationY", 0.0f, ((ao.a(this.m, this.o + 32) + pivotY) - this.e.getPivotY()) - e);
        float width2 = f / (this.f.getWidth() * 1.0f);
        float height2 = f2 / (this.f.getHeight() * 1.0f);
        ho.b("PPSSplashIconView", "destScaleX= %s  destScaleY = %s", Float.valueOf(width2), Float.valueOf(height2));
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.f, "scaleX", 1.0f, width2);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.f, "scaleY", 1.0f, height2);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(this.f, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(this.h, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(this.d, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.x = animatorSet;
        animatorSet.setDuration(300L);
        this.x.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        this.x.playTogether(ofFloat11, ofFloat12);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.w = animatorSet2;
        animatorSet2.setDuration(500L);
        this.w.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
        this.w.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat6, ofFloat7, ofFloat8, ofFloat9, ofFloat5, ofFloat10);
        this.w.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.7
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
                ho.b("PPSSplashIconView", "onAnimationEnd");
                try {
                    PPSSplashIconView.this.g.setAlpha(1.0f);
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PPSSplashIconView.this.b != null) {
                                if (PPSSplashIconView.this.b.isAttachedToWindow()) {
                                    PPSSplashIconView.this.i.removeViewImmediate(PPSSplashIconView.this.b);
                                }
                                if (PPSSplashIconView.this.x != null) {
                                    PPSSplashIconView.this.x.start();
                                }
                            }
                        }
                    }, 20L);
                    PPSSplashIconView.this.f();
                } catch (Throwable th) {
                    ho.b("PPSSplashIconView", "onAnimationEnd err: %s", th.getClass().getSimpleName());
                }
            }
        });
        this.w.start();
    }

    private void c() {
        try {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.flags = 134218760;
            layoutParams.format = 1;
            layoutParams.gravity = GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
            this.i.addView(this.b, layoutParams);
            layoutParams.flags = 40;
            if (this.n == 1) {
                layoutParams.gravity = 8388659;
            } else {
                layoutParams.gravity = 8388661;
            }
            layoutParams.x = ao.a(this.m, 4.0f);
            layoutParams.y = ao.a(this.m, this.o + 28);
            this.i.addView(this.c, layoutParams);
            if (this.n == 1) {
                layoutParams.x = ao.a(this.m, 44.0f);
            } else {
                layoutParams.x = 0;
            }
            layoutParams.y = ao.a(this.m, this.o);
            this.i.addView(this.d, layoutParams);
            this.h.setAlpha(0.0f);
            this.g.setAlpha(0.0f);
            this.f.setAlpha(0.0f);
            this.d.setAlpha(0.0f);
            this.y = true;
        } catch (Throwable th) {
            ho.c("PPSSplashIconView", "add icon error: %s", th.getClass().getSimpleName());
            this.k.a(5);
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ae aeVar;
        if (this.b == null || (aeVar = this.c) == null) {
            ho.c("PPSSplashIconView", "onIconLoaded, not init");
            return;
        }
        ViewTreeObserver viewTreeObserver = aeVar.getViewTreeObserver();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            nt ntVar = this.k;
            if (ntVar != null) {
                ntVar.a(4);
            }
            i();
            h();
            return;
        }
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.3
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (PPSSplashIconView.this.c == null || PPSSplashIconView.this.b == null) {
                    ho.c("PPSSplashIconView", "onPreDraw, View is null");
                    return true;
                }
                PPSSplashIconView.this.c.getViewTreeObserver().removeOnPreDrawListener(this);
                ho.b("PPSSplashIconView", "onPreDraw: start anim");
                PPSSplashIconView.this.d();
                return true;
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSSplashIconView.this.k.a(PPSSplashIconView.this.f7957a.aK() != 0 ? Long.valueOf(System.currentTimeMillis() - PPSSplashIconView.this.f7957a.aK()) : null, 100, 1);
                boolean a2 = PPSSplashIconView.this.k.a(PPSSplashIconView.this.c.getContext(), PPSSplashIconView.this.z);
                PPSSplashIconView.this.z = null;
                ho.b("PPSSplashIconView", "onclick, result= %s", Boolean.valueOf(a2));
                if (PPSSplashIconView.this.j != null) {
                    PPSSplashIconView.this.j.onIconClick();
                }
                if (a2) {
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PPSSplashIconView.this.i();
                            if (PPSSplashIconView.this.j != null) {
                                PPSSplashIconView.this.j.onIconDismiss(4);
                            }
                        }
                    }, 200L);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    PPSSplashIconView.this.z = th.a(view, motionEvent);
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                th.a(view, motionEvent, null, PPSSplashIconView.this.z);
                return false;
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ho.b("PPSSplashIconView", "click close");
                PPSSplashIconView.this.i();
                if (PPSSplashIconView.this.j != null) {
                    PPSSplashIconView.this.j.onIconDismiss(1);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
    }

    private void a(String str) {
        if (str.startsWith("http")) {
            dk a2 = dh.a(this.m, "normal");
            this.q = a2.c(a2.e(str));
        }
    }

    private void a(View view) {
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        this.i.removeViewImmediate(view);
    }

    private void a(Activity activity, int i, int i2, MetaData metaData) {
        nt ntVar;
        a(this.r);
        this.p = (metaData.F() == null || metaData.F().intValue() <= 0) ? 10000 : metaData.F().intValue() * 1000;
        this.n = i;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        this.u = ao.b(this.m, r3.heightPixels) - 92;
        int b = (int) ((ao.b(this.m, r3.heightPixels) * 0.85f) - 92.0f);
        this.v = b;
        if (i2 <= 0 || i2 > this.u) {
            i2 = b;
        }
        this.o = i2;
        a();
        this.b = new ag(activity);
        ae aeVar = new ae(activity);
        this.c = aeVar;
        this.g = aeVar.getIcon();
        this.h = this.c.getRoundLayout();
        this.e = this.b.getSplash();
        this.d = new af(activity);
        this.f = this.b.getIcon();
        this.i = (WindowManager) activity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        ae aeVar2 = this.c;
        if (aeVar2 == null || (ntVar = this.k) == null) {
            return;
        }
        ntVar.a(aeVar2);
    }

    private void a() {
        if (this.q != null) {
            com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashIconView.1
                @Override // java.lang.Runnable
                public void run() {
                    rt rtVar = new rt();
                    rtVar.c(PPSSplashIconView.this.q);
                    az.a(PPSSplashIconView.this.m, rtVar, (az.a) null);
                }
            });
        }
    }

    public PPSSplashIconView(Activity activity, int i, int i2) {
        this.s = false;
        ho.b("PPSSplashIconView", "init");
        if (this.s) {
            ho.c("PPSSplashIconView", "already init");
            return;
        }
        this.s = true;
        if (dc.k() == null || dc.k().h() == null) {
            ho.c("PPSSplashIconView", "ad is null");
            return;
        }
        ContentRecord k = dc.k();
        this.f7957a = k;
        MetaData h = k.h();
        if (h == null || bg.a(h.g()) || h.g().get(0) == null) {
            ho.c("PPSSplashIconView", "Icon info is null");
            return;
        }
        Context applicationContext = activity.getApplicationContext();
        this.m = applicationContext;
        this.k = new nt(applicationContext, this.f7957a, activity.getLocalClassName());
        String c = h.g().get(0).c();
        this.r = c;
        if (!TextUtils.isEmpty(c)) {
            a(activity, i, i2, h);
        } else {
            ho.c("PPSSplashIconView", "Icon url is null");
            this.k.a(1);
        }
    }
}
