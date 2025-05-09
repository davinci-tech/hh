package com.huawei.uikit.hwdotspageindicator.widget;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import defpackage.slg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
class HwDotsPageIndicatorAnimation implements Animator.AnimatorListener {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Integer, ValueAnimator> f10644a;
    private ValueAnimator b;
    private ConcurrentHashMap<Integer, ValueAnimator> c;
    private ValueAnimator d;
    private ValueAnimator e;
    private ValueAnimator f;
    private SpringAnimation g;
    private ValueAnimator h;
    private ValueAnimator i;
    private ValueAnimator j;
    private final ConcurrentHashMap<Animator, List<AnimationStateListener>> k = new ConcurrentHashMap<>();

    public static abstract class AnimationStateListener {
        void a() {
        }

        void a(float f) {
        }

        void b() {
        }

        void c() {
        }
    }

    public interface AnimationUpdateListener {
        void onAnimationUpdate(slg slgVar);

        void onDotCenterChanged(float[] fArr);

        void onFocusDotChanged(boolean z, float f);

        void onFocusSingleScaled(RectF rectF);

        void onSingleScaled(boolean z, int i, float f);

        void onSpringAnimationUpdate(boolean z, float f);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final slg f10645a;
        private final float[] b;
        private final slg c;
        private final float d;
        private final float e;
        private final RectF f;
        private final float[] g;
        private final float h;
        private final float i;
        private final RectF j;
        private final TimeInterpolator k;
        private final float l;
        private final AnimationUpdateListener m;
        private final long n;
        private final float o;
        private final AnimationStateListener s;

        public static class e {

            /* renamed from: a, reason: collision with root package name */
            private float f10646a;
            private float b;
            private float[] c;
            private float[] d;
            private float e;
            private float f;
            private float g;
            private long h;
            private float i;
            private RectF j;
            private TimeInterpolator k;
            private RectF l;
            private slg m;
            private slg n;
            private AnimationUpdateListener o;
            private AnimationStateListener r;

            public float a() {
                return this.i;
            }

            public e a(float f) {
                this.f = f;
                return this;
            }

            public e a(slg slgVar) {
                this.n = slgVar;
                return this;
            }

            public e a(float[] fArr) {
                this.d = fArr;
                return this;
            }

            public e b(float f) {
                this.f10646a = f;
                return this;
            }

            public e b(float[] fArr) {
                this.c = fArr;
                return this;
            }

            public slg b() {
                return this.n;
            }

            public e c(float f) {
                this.e = f;
                return this;
            }

            public e c(long j) {
                this.h = j;
                return this;
            }

            public e c(AnimationStateListener animationStateListener) {
                this.r = animationStateListener;
                return this;
            }

            public long d() {
                return this.h;
            }

            public e d(float f) {
                this.i = f;
                return this;
            }

            public e d(AnimationUpdateListener animationUpdateListener) {
                this.o = animationUpdateListener;
                return this;
            }

            public e e(float f) {
                this.g = f;
                return this;
            }

            public e e(slg slgVar) {
                this.m = slgVar;
                return this;
            }

            public a e() {
                return new a(this);
            }

            public RectF ecA_() {
                return this.j;
            }

            public RectF ecB_() {
                return this.l;
            }

            public e ecC_(TimeInterpolator timeInterpolator) {
                this.k = timeInterpolator;
                return this;
            }

            public e ecD_(RectF rectF) {
                this.j = rectF;
                return this;
            }

            public e ecE_(RectF rectF) {
                this.l = rectF;
                return this;
            }

            public TimeInterpolator ecz_() {
                return this.k;
            }

            public e f(float f) {
                this.b = f;
                return this;
            }

            public float[] g() {
                return this.d;
            }

            public float h() {
                return this.e;
            }

            public float i() {
                return this.f10646a;
            }

            public slg j() {
                return this.m;
            }

            public float l() {
                return this.g;
            }

            public AnimationStateListener m() {
                return this.r;
            }

            public float n() {
                return this.f;
            }

            public float[] o() {
                return this.c;
            }

            public AnimationUpdateListener r() {
                return this.o;
            }

            public float t() {
                return this.b;
            }
        }

        a(e eVar) {
            this.f10645a = eVar.j();
            this.c = eVar.b();
            this.d = eVar.i();
            this.e = eVar.t();
            this.b = eVar.g();
            this.g = eVar.o();
            this.h = eVar.h();
            this.i = eVar.n();
            this.j = eVar.ecA_();
            this.f = eVar.ecB_();
            this.o = eVar.l();
            this.l = eVar.a();
            this.n = eVar.d();
            this.k = eVar.ecz_();
            this.m = eVar.r();
            this.s = eVar.m();
        }

        public slg b() {
            return this.f10645a;
        }

        public float c() {
            return this.l;
        }

        public float[] d() {
            return this.b;
        }

        public long e() {
            return this.n;
        }

        public TimeInterpolator ecw_() {
            return this.k;
        }

        public RectF ecx_() {
            return this.j;
        }

        public RectF ecy_() {
            return this.f;
        }

        public float g() {
            return this.o;
        }

        public float h() {
            return this.d;
        }

        public float i() {
            return this.h;
        }

        public AnimationStateListener j() {
            return this.s;
        }

        public float k() {
            return this.e;
        }

        public slg l() {
            return this.c;
        }

        public float n() {
            return this.i;
        }

        public float[] o() {
            return this.g;
        }

        public AnimationUpdateListener t() {
            return this.m;
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f10647a;
        final /* synthetic */ RectF b;
        final /* synthetic */ float c;
        final /* synthetic */ float d;
        final /* synthetic */ float e;
        final /* synthetic */ float f;
        final /* synthetic */ a j;

        b(float f, RectF rectF, float f2, float f3, float f4, float f5, a aVar) {
            this.e = f;
            this.b = rectF;
            this.f10647a = f2;
            this.d = f3;
            this.c = f4;
            this.f = f5;
            this.j = aVar;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue() - this.e;
            RectF rectF = this.b;
            float f = floatValue / 2.0f;
            rectF.top = this.f10647a - f;
            rectF.left = this.d - floatValue;
            rectF.right = this.c + floatValue;
            rectF.bottom = this.f + f;
            if (this.j.m != null) {
                this.j.m.onFocusSingleScaled(this.b);
            }
        }
    }

    class c implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f10648a;
        final /* synthetic */ float c;
        final /* synthetic */ float e;

        c(a aVar, float f, float f2) {
            this.f10648a = aVar;
            this.e = f;
            this.c = f2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            HwDotsPageIndicatorAnimation.this.ecr_(valueAnimator, floatValue);
            float interpolation = this.f10648a.ecw_().getInterpolation(floatValue);
            if (this.f10648a.t() != null) {
                this.f10648a.t().onFocusDotChanged(true, HwDotsPageIndicatorAnimation.this.b(this.e, this.c, interpolation));
            }
        }
    }

    class d implements DynamicAnimation.OnAnimationUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f10649a;
        final /* synthetic */ a b;

        d(a aVar, boolean z) {
            this.b = aVar;
            this.f10649a = z;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
        public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
            if (dynamicAnimation == null || this.b.t() == null) {
                return;
            }
            this.b.t().onSpringAnimationUpdate(this.f10649a, f);
        }
    }

    class e implements DynamicAnimation.OnAnimationEndListener {
        final /* synthetic */ a c;

        e(a aVar) {
            this.c = aVar;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
        public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            if (dynamicAnimation == null) {
                return;
            }
            this.c.j().b();
        }
    }

    class g implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f10650a;
        final /* synthetic */ ArgbEvaluator b;
        final /* synthetic */ slg c;
        final /* synthetic */ slg d;
        final /* synthetic */ slg e;

        g(a aVar, ArgbEvaluator argbEvaluator, slg slgVar, slg slgVar2, slg slgVar3) {
            this.f10650a = aVar;
            this.b = argbEvaluator;
            this.d = slgVar;
            this.e = slgVar2;
            this.c = slgVar3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float interpolation = this.f10650a.ecw_().getInterpolation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            this.c.t(((Integer) this.b.evaluate(interpolation, Integer.valueOf(this.d.ad()), Integer.valueOf(this.e.ad()))).intValue());
            float b = HwDotsPageIndicatorAnimation.this.b(this.d.l(), this.e.l(), interpolation);
            float b2 = HwDotsPageIndicatorAnimation.this.b(this.d.q(), this.e.q(), interpolation);
            float b3 = HwDotsPageIndicatorAnimation.this.b(this.d.p(), this.e.p(), interpolation);
            float b4 = HwDotsPageIndicatorAnimation.this.b(this.d.t(), this.e.t(), interpolation);
            float b5 = HwDotsPageIndicatorAnimation.this.b(this.d.r(), this.e.r(), interpolation);
            this.c.a(HwDotsPageIndicatorAnimation.this.b(this.d.ecI_().left, this.e.ecI_().left, interpolation), HwDotsPageIndicatorAnimation.this.b(this.d.ecI_().top, this.e.ecI_().top, interpolation), HwDotsPageIndicatorAnimation.this.b(this.d.ecI_().right, this.e.ecI_().right, interpolation), HwDotsPageIndicatorAnimation.this.b(this.d.ecI_().bottom, this.e.ecI_().bottom, interpolation));
            this.c.c(b);
            this.c.c(b2, b4, b3, b5);
            float[] fArr = new float[this.e.g().length];
            for (int i = 0; i < this.e.g().length; i++) {
                fArr[i] = HwDotsPageIndicatorAnimation.this.b(this.d.g()[i], this.e.g()[i], interpolation);
            }
            this.c.d(fArr);
            this.c.d(this.e.i());
            if (this.f10650a.t() != null) {
                this.f10650a.t().onAnimationUpdate(this.c);
            }
        }
    }

    class h implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f10651a;
        final /* synthetic */ float b;
        final /* synthetic */ a e;

        h(a aVar, float f, float f2) {
            this.e = aVar;
            this.f10651a = f;
            this.b = f2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float interpolation = this.e.ecw_().getInterpolation(floatValue);
            float f = this.f10651a;
            float f2 = this.b;
            HwDotsPageIndicatorAnimation.this.ecr_(valueAnimator, floatValue);
            if (this.e.t() != null) {
                this.e.t().onFocusDotChanged(false, f + ((f2 - f) * interpolation));
            }
        }
    }

    class i implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ a f10652a;
        final /* synthetic */ boolean c;
        final /* synthetic */ int e;

        i(a aVar, boolean z, int i) {
            this.f10652a = aVar;
            this.c = z;
            this.e = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (this.f10652a.t() != null) {
                this.f10652a.t().onSingleScaled(this.c, this.e, floatValue);
            }
        }
    }

    class j implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ float[] b;
        final /* synthetic */ a c;
        final /* synthetic */ float[] d;

        j(a aVar, float[] fArr, float[] fArr2) {
            this.c = aVar;
            this.d = fArr;
            this.b = fArr2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            float interpolation = this.c.ecw_().getInterpolation(((Float) valueAnimator.getAnimatedValue()).floatValue());
            float[] fArr = new float[this.d.length];
            int i = 0;
            while (true) {
                float[] fArr2 = this.d;
                if (i >= fArr2.length) {
                    break;
                }
                float f = fArr2[i];
                fArr[i] = f + ((this.b[i] - f) * interpolation);
                i++;
            }
            if (this.c.t() != null) {
                this.c.t().onDotCenterChanged(fArr);
            }
        }
    }

    HwDotsPageIndicatorAnimation() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(float f, float f2, float f3) {
        return f + ((f2 - f) * f3);
    }

    public boolean a() {
        ValueAnimator valueAnimator = this.e;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public boolean a(int i2) {
        ValueAnimator valueAnimator;
        ConcurrentHashMap<Integer, ValueAnimator> concurrentHashMap = this.f10644a;
        return (concurrentHashMap == null || (valueAnimator = concurrentHashMap.get(Integer.valueOf(i2))) == null || !valueAnimator.isRunning()) ? false : true;
    }

    public boolean b() {
        ValueAnimator valueAnimator = this.b;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void c(int i2) {
        if (e(i2)) {
            this.c.get(Integer.valueOf(i2)).cancel();
        }
    }

    public boolean c() {
        ValueAnimator valueAnimator = this.f;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public boolean d() {
        ValueAnimator valueAnimator = this.i;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public boolean e() {
        ValueAnimator valueAnimator = this.j;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void f() {
        if (c()) {
            this.f.cancel();
        }
    }

    public boolean g() {
        SpringAnimation springAnimation = this.g;
        return springAnimation != null && springAnimation.isRunning();
    }

    public void h() {
        if (d()) {
            this.i.cancel();
        }
    }

    public void i() {
        if (a()) {
            this.e.cancel();
        }
    }

    public boolean j() {
        ValueAnimator valueAnimator = this.d;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void k() {
        if (j()) {
            this.d.cancel();
        }
    }

    public void l() {
        if (g()) {
            this.g.cancel();
        }
    }

    public void n() {
        if (e()) {
            this.j.cancel();
        }
    }

    public void o() {
        if (b()) {
            this.b.cancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        List<AnimationStateListener> remove;
        Set<Animator> keySet = this.k.keySet();
        if (keySet == null || keySet.size() == 0) {
            return;
        }
        for (Animator animator2 : keySet) {
            if (animator2 == animator && (remove = this.k.remove(animator2)) != null && remove.size() != 0) {
                Iterator<AnimationStateListener> it = remove.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        List<AnimationStateListener> remove;
        Set<Animator> keySet = this.k.keySet();
        if (keySet == null || keySet.size() == 0) {
            return;
        }
        for (Animator animator2 : keySet) {
            if (animator2 == animator && (remove = this.k.remove(animator2)) != null && remove.size() != 0) {
                Iterator<AnimationStateListener> it = remove.iterator();
                while (it.hasNext()) {
                    it.next().b();
                }
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        List<AnimationStateListener> list;
        Set<Animator> keySet = this.k.keySet();
        if (keySet == null || keySet.size() == 0) {
            return;
        }
        for (Animator animator2 : keySet) {
            if (animator2 == animator && (list = this.k.get(animator2)) != null && list.size() != 0) {
                Iterator<AnimationStateListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().c();
                }
            }
        }
    }

    public void a(a aVar) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.b = ofFloat;
        ecs_(ofFloat, aVar);
    }

    public void d(int i2) {
        ConcurrentHashMap<Integer, ValueAnimator> concurrentHashMap = this.c;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i2));
        }
    }

    public void h(int i2) {
        if (a(i2)) {
            this.f10644a.get(Integer.valueOf(i2)).cancel();
        }
    }

    public boolean e(int i2) {
        ValueAnimator valueAnimator;
        ConcurrentHashMap<Integer, ValueAnimator> concurrentHashMap = this.c;
        return (concurrentHashMap == null || (valueAnimator = concurrentHashMap.get(Integer.valueOf(i2))) == null || !valueAnimator.isRunning()) ? false : true;
    }

    public void b(int i2) {
        ConcurrentHashMap<Integer, ValueAnimator> concurrentHashMap = this.f10644a;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i2));
        }
    }

    public void b(a aVar) {
        if (aVar.ecw_() == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.e = ofFloat;
        ofFloat.setInterpolator(new LinearInterpolator());
        this.e.setDuration(aVar.e());
        this.e.addListener(this);
        if (aVar.j() != null) {
            a(this.e, aVar.j());
        }
        this.e.addUpdateListener(new c(aVar, aVar.i(), aVar.n()));
        this.e.start();
    }

    private void ecv_(int i2, ValueAnimator valueAnimator) {
        if (this.f10644a == null) {
            this.f10644a = new ConcurrentHashMap<>();
        }
        this.f10644a.put(Integer.valueOf(i2), valueAnimator);
    }

    public void e(a aVar) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.d = ofFloat;
        ecs_(ofFloat, aVar);
    }

    private void ecs_(ValueAnimator valueAnimator, a aVar) {
        slg b2 = aVar.b();
        slg l = aVar.l();
        if (ecu_(b2, l, aVar.ecw_())) {
            slg f = b2.f();
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new g(aVar, new ArgbEvaluator(), b2, l, f));
            valueAnimator.addListener(this);
            if (aVar.j() != null) {
                a(valueAnimator, aVar.j());
            }
            valueAnimator.setDuration(aVar.e());
            valueAnimator.start();
        }
    }

    public void e(boolean z, a aVar) {
        this.g = new SpringAnimation(new FloatValueHolder(aVar.i()));
        float n = aVar.n();
        this.g.addUpdateListener(new d(aVar, z));
        if (aVar.j() != null) {
            this.g.addEndListener(new e(aVar));
        }
        SpringForce springForce = new SpringForce();
        springForce.setDampingRatio(aVar.c()).setStiffness(aVar.g()).setFinalPosition(n);
        this.g.setSpring(springForce);
        this.g.start();
    }

    public void d(a aVar) {
        if (aVar.ecw_() == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.i = ofFloat;
        ofFloat.setInterpolator(new LinearInterpolator());
        this.i.setDuration(aVar.n);
        this.i.addListener(this);
        if (aVar.s != null) {
            a(this.i, aVar.s);
        }
        this.i.addUpdateListener(new h(aVar, aVar.i(), aVar.n()));
        this.i.start();
    }

    private boolean ecu_(slg slgVar, slg slgVar2, TimeInterpolator timeInterpolator) {
        if (slgVar != null && slgVar2 != null && timeInterpolator != null && slgVar.ecI_() != null && slgVar2.ecI_() != null) {
            float[] g2 = slgVar2.g();
            float[] g3 = slgVar.g();
            if (g2 != null && g3 != null && g2.length == g3.length) {
                return true;
            }
        }
        return false;
    }

    public void d(boolean z, a aVar) {
        RectF ecx_ = aVar.ecx_();
        RectF ecy_ = aVar.ecy_();
        if (ecx_ == null || ecy_ == null || aVar.ecw_() == null) {
            return;
        }
        float f = ecx_.left;
        float f2 = ecx_.top;
        float f3 = ecx_.right;
        float f4 = ecx_.bottom;
        float f5 = f4 - f2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f5, ecy_.bottom - ecy_.top);
        if (z) {
            this.f = ofFloat;
        } else {
            this.j = ofFloat;
        }
        ofFloat.setInterpolator(aVar.ecw_());
        ofFloat.addUpdateListener(new b(f5, new RectF(), f2, f, f3, f4, aVar));
        ofFloat.start();
    }

    public void a(int i2, boolean z, a aVar) {
        if (aVar.ecw_() == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(aVar.h(), aVar.k());
        if (z) {
            ecq_(i2, ofFloat);
        } else {
            ecv_(i2, ofFloat);
        }
        ofFloat.setDuration(aVar.e());
        ofFloat.setInterpolator(aVar.ecw_());
        ofFloat.addListener(this);
        if (aVar.j() != null) {
            a(ofFloat, aVar.j());
        }
        ofFloat.addUpdateListener(new i(aVar, z, i2));
        ofFloat.start();
    }

    private void ecq_(int i2, ValueAnimator valueAnimator) {
        if (this.c == null) {
            this.c = new ConcurrentHashMap<>();
        }
        this.c.put(Integer.valueOf(i2), valueAnimator);
    }

    public void c(a aVar) {
        if (aVar.ecw_() == null) {
            return;
        }
        float[] d2 = aVar.d();
        float[] o = aVar.o();
        if (d2 == null || o == null || d2.length != o.length) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.h = ofFloat;
        ofFloat.setInterpolator(new LinearInterpolator());
        this.h.setDuration(aVar.e());
        this.h.addListener(this);
        if (aVar.s != null) {
            a(this.h, aVar.s);
        }
        this.h.addUpdateListener(new j(aVar, d2, o));
        this.h.start();
    }

    public void a(Animator animator, AnimationStateListener animationStateListener) {
        List<AnimationStateListener> list = this.k.get(animator);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(animationStateListener);
        this.k.put(animator, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ecr_(Animator animator, float f) {
        List<AnimationStateListener> list;
        Set<Animator> keySet = this.k.keySet();
        if (keySet == null || keySet.size() == 0) {
            return;
        }
        for (Animator animator2 : keySet) {
            if (animator2 == animator && (list = this.k.get(animator2)) != null && list.size() != 0) {
                Iterator<AnimationStateListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(f);
                }
            }
        }
    }
}
