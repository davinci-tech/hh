package com.huawei.uikit.hwdotspageindicator.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation;
import defpackage.slg;
import defpackage.slk;
import defpackage.sll;
import defpackage.smr;

/* loaded from: classes7.dex */
class bzrwd extends View {

    /* renamed from: a, reason: collision with root package name */
    protected slg f10654a;
    private float b;
    protected HwDotsPageIndicatorAnimation c;
    protected float d;
    private TimeInterpolator e;
    protected float f;
    protected float g;
    protected slk h;
    protected float i;
    protected HwWatchDotsPageIndicatorAnimation j;
    private TimeInterpolator k;
    private TimeInterpolator l;
    private TimeInterpolator m;
    private TimeInterpolator n;
    private TimeInterpolator o;
    private TimeInterpolator r;
    private TimeInterpolator s;

    class a extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        final /* synthetic */ int c;

        a(int i) {
            this.c = i;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void b() {
            bzrwd.this.c.d(this.c);
        }
    }

    class b extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        final /* synthetic */ View b;

        b(View view) {
            this.b = view;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void a() {
            bzrwd.this.ecU_(this.b);
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void b() {
            bzrwd.this.ecU_(this.b);
        }
    }

    class c extends HwDotsPageIndicatorAnimation.AnimationStateListener {
        final /* synthetic */ int b;
        final /* synthetic */ View c;

        c(int i, View view) {
            this.b = i;
            this.c = view;
        }

        @Override // com.huawei.uikit.hwdotspageindicator.widget.HwDotsPageIndicatorAnimation.AnimationStateListener
        void b() {
            bzrwd.this.f10654a.m(this.b);
            bzrwd.this.c.b(this.b);
            this.c.invalidate();
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        float f10657a;
        float b;
        boolean c;
        float d;
        float e;

        e(boolean z, float f, float f2, float f3, float f4) {
            this.c = z;
            this.e = f;
            this.f10657a = f2;
            this.b = f3;
            this.d = f4;
        }
    }

    bzrwd(Context context, AttributeSet attributeSet, int i) {
        super(d(context, i), attributeSet, i);
        this.f10654a = new slg();
        this.h = new slk();
        this.d = 0.47f;
        this.f = 700.0f;
        this.g = 0.2f;
        this.i = 800.0f;
    }

    public void a(float f, float f2, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        if (this.c != null) {
            this.c.d(new HwDotsPageIndicatorAnimation.a.e().c(f).a(f2).c(400L).ecC_(getDecelerateInterpolator()).d(animationUpdateListener).c(animationStateListener).e());
        }
    }

    public void a(e eVar, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.e(eVar.c, new HwDotsPageIndicatorAnimation.a.e().c(eVar.e).a(eVar.f10657a).e(eVar.b).d(eVar.d).d(animationUpdateListener).e());
        }
    }

    public void a(float[] fArr, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.c(new HwDotsPageIndicatorAnimation.a.e().a(this.f10654a.g()).b(fArr).c(400L).ecC_(getAccelerateInterpolator()).d(animationUpdateListener).e());
        }
    }

    public boolean a() {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        return hwDotsPageIndicatorAnimation != null && hwDotsPageIndicatorAnimation.a();
    }

    public void b(int i, float f, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        if (hwDotsPageIndicatorAnimation != null) {
            hwDotsPageIndicatorAnimation.h(i);
            this.c.a(i, true, new HwDotsPageIndicatorAnimation.a.e().b(this.f10654a.l()).f(f).c(100L).ecC_(getAlphaInterpolator()).d(animationUpdateListener).c(new a(i)).e());
        }
    }

    public void b(String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        throw new IllegalThreadStateException("Only main thread can call #" + str);
    }

    public void d(float f, float f2, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.b(new HwDotsPageIndicatorAnimation.a.e().c(f).a(f2).c(400L).ecC_(getAccelerateInterpolator()).d(animationUpdateListener).e());
        }
    }

    public void d(float f, float f2, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        if (this.c != null) {
            this.c.b(new HwDotsPageIndicatorAnimation.a.e().c(f).a(f2).c(400L).ecC_(getAccelerateInterpolator()).d(animationUpdateListener).c(animationStateListener).e());
        }
    }

    public void e(slg slgVar, boolean z, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener, HwDotsPageIndicatorAnimation.AnimationStateListener animationStateListener) {
        if (this.c != null) {
            this.c.e(new HwDotsPageIndicatorAnimation.a.e().e(this.f10654a.f()).a(slgVar).ecC_(getAlphaInterpolator()).c(250L).d(animationUpdateListener).c(animationStateListener).e());
        }
    }

    public void ecW_(RectF rectF, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.d(true, new HwDotsPageIndicatorAnimation.a.e().ecD_(this.f10654a.ecH_()).ecE_(rectF).c(100L).ecC_(getAlphaInterpolator()).d(animationUpdateListener).e());
            this.f10654a.a(true);
        }
    }

    public void ecX_(RectF rectF, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.d(false, new HwDotsPageIndicatorAnimation.a.e().ecD_(this.f10654a.ecH_()).ecE_(rectF).c(150L).ecC_(getAlphaInterpolator()).d(animationUpdateListener).e());
            this.f10654a.a(false);
        }
    }

    public void ecY_(boolean z, slg slgVar, View view, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        if (this.c != null) {
            this.c.a(new HwDotsPageIndicatorAnimation.a.e().e(this.f10654a.f()).a(slgVar).ecC_(getAlphaInterpolator()).c(z ? 250L : 300L).d(animationUpdateListener).c(new b(view)).e());
            this.f10654a.q(-1);
            this.f10654a.a(false);
            this.f10654a.h();
        }
    }

    public void ecZ_(int i, View view, HwDotsPageIndicatorAnimation.AnimationUpdateListener animationUpdateListener) {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        if (hwDotsPageIndicatorAnimation != null) {
            hwDotsPageIndicatorAnimation.c(i);
            this.c.a(i, false, new HwDotsPageIndicatorAnimation.a.e().b(this.f10654a.ab()).f(this.f10654a.l()).c(150L).ecC_(getAlphaInterpolator()).d(animationUpdateListener).c(new c(i, view)).e());
        }
    }

    public boolean g() {
        HwDotsPageIndicatorAnimation hwDotsPageIndicatorAnimation = this.c;
        return hwDotsPageIndicatorAnimation != null && hwDotsPageIndicatorAnimation.g();
    }

    public TimeInterpolator getAccelerateInterpolator() {
        if (this.k == null) {
            this.k = sll.ecJ_();
        }
        return this.k;
    }

    public TimeInterpolator getAlphaInterpolator() {
        if (this.e == null) {
            this.e = sll.ecM_();
        }
        return this.e;
    }

    public TimeInterpolator getDecelerateInterpolator() {
        if (this.n == null) {
            this.n = sll.ecN_();
        }
        return this.n;
    }

    public float getMaxDiffFraction() {
        if (this.b == 0.0f) {
            this.b = sll.a(getAccelerateInterpolator(), getDecelerateInterpolator());
        }
        return this.b;
    }

    public TimeInterpolator getNavigationPointInterpolator() {
        if (this.m == null) {
            this.m = sll.ecP_();
        }
        return this.m;
    }

    public TimeInterpolator getScaleInterpolator() {
        if (this.s == null) {
            this.s = sll.ecO_();
        }
        return this.s;
    }

    public TimeInterpolator getWatchAccelerateInterpolator() {
        if (this.k == null) {
            this.k = sll.ecQ_();
        }
        return this.k;
    }

    public TimeInterpolator getWatchDecelerateInterpolator() {
        if (this.n == null) {
            this.n = sll.g();
        }
        return this.n;
    }

    public TimeInterpolator getWatchDotTouchAndSlideInterpolator() {
        if (this.r == null) {
            this.r = sll.ecR_();
        }
        return this.r;
    }

    public TimeInterpolator getWatchTouchFocusAccelerateInterpolator() {
        if (this.o == null) {
            this.o = sll.ecS_();
        }
        return this.o;
    }

    public TimeInterpolator getWatchTouchFocusDecelerateInterpolator() {
        if (this.l == null) {
            this.l = sll.ecT_();
        }
        return this.l;
    }

    public void i() {
        if (g()) {
            this.c.l();
        }
    }

    public void setAlphaInterpolator(TimeInterpolator timeInterpolator) {
        this.e = timeInterpolator;
    }

    public void setDragAccelerateInterpolator(TimeInterpolator timeInterpolator) {
        this.k = timeInterpolator;
        this.b = sll.a(timeInterpolator, getDecelerateInterpolator());
    }

    public void setDragDecelerateInterpolator(TimeInterpolator timeInterpolator) {
        this.n = timeInterpolator;
        this.b = sll.a(getAccelerateInterpolator(), this.n);
    }

    public void setScaleInterpolator(TimeInterpolator timeInterpolator) {
        this.s = timeInterpolator;
    }

    public void setSpringAnimationDamping(float f) {
        if (f <= 0.0f) {
            f = this.d;
        }
        this.d = f;
    }

    public void setSpringAnimationStiffness(float f) {
        if (f <= 0.0f) {
            f = this.f;
        }
        this.f = f;
    }

    private static Context d(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwDotsPageIndicator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ecU_(View view) {
        if (this.f10654a.j()) {
            return;
        }
        this.f10654a.h();
        view.invalidate();
    }
}
