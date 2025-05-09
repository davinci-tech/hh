package defpackage;

import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.dynamicanimation.SpringModelBase;

/* loaded from: classes7.dex */
public class skw {

    /* renamed from: a, reason: collision with root package name */
    private int f17097a;
    private int b;
    private int c;
    private long e;
    private float f;
    private long h;
    private bnh j;
    private int k;
    private int l;
    private int m;
    private b n;
    private View o;
    private bno d = null;
    private boolean g = true;
    private int i = 0;
    private double q = 1.0d;
    private float s = -1.0f;

    class b extends SpringModelBase {

        /* renamed from: a, reason: collision with root package name */
        private float f17098a;
        private float b;
        private long d;
        private float g;
        private float i;

        b(float f, float f2, float f3, float f4, float f5) {
            super(f, f2, 0.001f);
            this.b = f3;
            this.i = f3;
            this.g = f4;
            this.f17098a = f5;
            setValueThreshold(0.5f);
            e(0.0f);
            c(this.g - this.b, f5, -1L);
            this.d = AnimationUtils.currentAnimationTimeMillis();
        }

        boolean b() {
            float currentAnimationTimeMillis = (AnimationUtils.currentAnimationTimeMillis() - this.d) / 1000.0f;
            this.f17098a = getVelocity(currentAnimationTimeMillis);
            float position = getPosition(currentAnimationTimeMillis);
            float f = this.b;
            float f2 = position + f;
            this.i = f2;
            if (!isAtEquilibrium(f2 - f, this.f17098a)) {
                Log.d("HwSpringBackHelper", "isAtEquilibrium is false.");
                return false;
            }
            this.i = getEndPosition() + this.b;
            this.f17098a = 0.0f;
            return true;
        }
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int b(int i, int i2, int i3) {
        return Math.round(i2 * new bnm(i * 0.5f).getRate(Math.abs(i3)));
    }

    public void c(float f) {
        this.s = f;
    }

    public boolean c() {
        boolean z;
        if (this.g) {
            return false;
        }
        if (this.i == 3) {
            b bVar = this.n;
            if (bVar != null) {
                this.g = bVar.b();
                this.b = (int) this.n.i;
                this.f = this.n.f17098a;
            } else {
                Log.e("HwSpringBackHelper", "computeScrollOffset mSpringModel is null");
                this.g = true;
            }
            if (this.g) {
                d();
            }
            z = this.g;
        } else {
            if (this.h <= 0) {
                d();
                return false;
            }
            float currentAnimationTimeMillis = (AnimationUtils.currentAnimationTimeMillis() - this.e) / this.h;
            if (currentAnimationTimeMillis <= 1.0f) {
                this.g = false;
                if (this.i == 2) {
                    this.b = this.f17097a + ((int) (this.j.b(currentAnimationTimeMillis).b() * this.q));
                    float d = this.j.b(currentAnimationTimeMillis).d();
                    this.f = d;
                    int i = this.b;
                    int i2 = this.m;
                    if (i > i2 || d >= 0.0f) {
                        int i3 = this.k;
                        if (i >= i3 && d > 0.0f) {
                            this.l = i - i3;
                            ebs_(this.o, i3);
                        }
                    } else {
                        this.l = i - i2;
                        ebs_(this.o, i2);
                    }
                } else {
                    this.b = (int) (this.f17097a - (this.d.getInterpolation(currentAnimationTimeMillis) * (this.f17097a - this.c)));
                }
            } else {
                this.b = this.c;
                d();
            }
            z = this.g;
        }
        return !z;
    }

    public void d() {
        this.i = 0;
        this.f = 0.0f;
        this.g = true;
    }

    public boolean d(int i, int i2, int i3) {
        this.i = 1;
        int i4 = 0;
        this.g = false;
        this.e = AnimationUtils.currentAnimationTimeMillis();
        this.f17097a = i;
        if (i < i2) {
            i4 = i - i2;
            this.c = i2;
        } else if (i > i3) {
            i4 = i - i3;
            this.c = i3;
        } else {
            d();
        }
        bno bnoVar = new bno(DynamicAnimation.SCROLL_Y, 228.0f, 30.0f, i4);
        this.d = bnoVar;
        this.h = (long) bnoVar.getDuration();
        return !this.g;
    }

    public float e() {
        return this.f;
    }

    public void e(double d) {
        this.q = d;
        this.c = ((int) Math.round((this.c - this.f17097a) * d)) + this.f17097a;
    }

    public void ebr_(View view, int i, int i2, int i3, int i4) {
        if (i2 == 0) {
            d();
            return;
        }
        this.i = 2;
        if (Float.compare(this.s, -1.0f) == 0) {
            this.j = new bnh(i2, 0.5f);
        } else {
            this.j = new bnh(i2, this.s);
        }
        this.h = (long) this.j.getDuration();
        this.g = false;
        this.e = AnimationUtils.currentAnimationTimeMillis();
        this.b = i;
        this.f17097a = i;
        this.m = i3;
        this.k = i4;
        this.o = view;
        this.f = i2;
        this.l = 0;
        this.c = (int) (i + this.j.getEndOffset() + 0.5f);
    }

    public void ebs_(View view, int i) {
        this.i = 3;
        this.b = i;
        if (this.o == null) {
            if (view == null) {
                Log.e("HwSpringBackHelper", "overFling: the target view is null.");
                d();
                return;
            }
            this.o = view;
        }
        if (this.f == 0.0f) {
            d();
            return;
        }
        float f = this.l;
        if (this.o != null) {
            f += r0.getScrollY();
        }
        this.n = new b(228.0f, 30.0f, f, i, this.f);
        this.b = (int) f;
        this.g = false;
    }

    public int g() {
        return this.f17097a;
    }

    public boolean h() {
        return this.g;
    }

    public void b(float f, int i, int i2) {
        this.i = 3;
        this.b = i2;
        if (Float.compare(f, 0.0f) == 0) {
            d();
            return;
        }
        this.n = new b(228.0f, 30.0f, i, i2, f);
        this.b = i;
        this.f = f;
        this.g = false;
        this.e = AnimationUtils.currentAnimationTimeMillis();
    }
}
