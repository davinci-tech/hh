package com.huawei.uikit.animations.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.huawei.health.R;

/* loaded from: classes7.dex */
public class HwGravitationalLoadingDrawable extends Drawable implements Animatable {

    /* renamed from: a, reason: collision with root package name */
    private final c f10571a;
    private final c b;
    public f c;
    private final c d;
    public i e;
    private final c f;
    private Animator h;
    private Animator j;
    private boolean i = false;
    private float g = 0.0f;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final b f10572a;
        private final m b;
        private final boolean c;
        private final h d;
        private final k e;

        a(m mVar, k kVar, h hVar, boolean z) {
            this(mVar, kVar, hVar, null, z);
        }

        public static a dYo_(Context context, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100435_res_0x7f060313, R.attr._2131100436_res_0x7f060314, R.attr._2131100437_res_0x7f060315, R.attr._2131100438_res_0x7f060316, R.attr._2131100439_res_0x7f060317, R.attr._2131100440_res_0x7f060318, R.attr._2131100441_res_0x7f060319, R.attr._2131100442_res_0x7f06031a, R.attr._2131100443_res_0x7f06031b, R.attr._2131100444_res_0x7f06031c, R.attr._2131100445_res_0x7f06031d, R.attr._2131100446_res_0x7f06031e, R.attr._2131100447_res_0x7f06031f, R.attr._2131100448_res_0x7f060320, R.attr._2131100449_res_0x7f060321}, i, i2);
            m dYF_ = m.dYF_(context, obtainStyledAttributes);
            k b = k.b(context, obtainStyledAttributes);
            h dYw_ = h.dYw_(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
            return new a(dYF_, b, dYw_, false);
        }

        public static a dYp_(Context context, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100435_res_0x7f060313, R.attr._2131100436_res_0x7f060314, R.attr._2131100437_res_0x7f060315, R.attr._2131100438_res_0x7f060316, R.attr._2131100439_res_0x7f060317, R.attr._2131100440_res_0x7f060318, R.attr._2131100441_res_0x7f060319, R.attr._2131100442_res_0x7f06031a, R.attr._2131100443_res_0x7f06031b, R.attr._2131100444_res_0x7f06031c, R.attr._2131100445_res_0x7f06031d, R.attr._2131100446_res_0x7f06031e, R.attr._2131100447_res_0x7f06031f, R.attr._2131100448_res_0x7f060320, R.attr._2131100449_res_0x7f060321}, i, i2);
            m dYF_ = m.dYF_(context, obtainStyledAttributes);
            b dYu_ = b.dYu_(context, obtainStyledAttributes);
            k b = k.b(context, obtainStyledAttributes);
            h dYw_ = h.dYw_(obtainStyledAttributes);
            obtainStyledAttributes.recycle();
            return new a(dYF_, b, dYw_, dYu_, true);
        }

        a(m mVar, k kVar, h hVar, b bVar, boolean z) {
            this.b = mVar;
            this.e = kVar;
            this.d = hVar;
            this.c = z;
            this.f10572a = bVar;
        }
    }

    static abstract class aijfr {

        /* renamed from: a, reason: collision with root package name */
        final Camera f10573a;
        private final Matrix b;

        class d extends aijfr {
            d(float f) {
                super(f, null);
            }

            @Override // com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable.aijfr
            protected void b(float f) {
                this.f10573a.rotateY(f);
            }
        }

        /* synthetic */ aijfr(float f, l lVar) {
            this(f);
        }

        PointF a(PointF pointF, float f) {
            PointF pointF2 = new PointF();
            b(f);
            a(pointF, pointF2);
            a();
            return pointF2;
        }

        abstract void b(float f);

        void c(float f) {
            Camera camera = this.f10573a;
            camera.setLocation(f, camera.getLocationY(), this.f10573a.getLocationZ());
        }

        void d(float f) {
            Camera camera = this.f10573a;
            camera.setLocation(camera.getLocationX(), f, this.f10573a.getLocationZ());
        }

        void e(float f) {
            Camera camera = this.f10573a;
            camera.setLocation(camera.getLocationX(), this.f10573a.getLocationY(), f);
        }

        private aijfr(float f) {
            Camera camera = new Camera();
            this.f10573a = camera;
            this.b = new Matrix();
            b(f);
            camera.save();
        }

        void a(PointF pointF, PointF pointF2, float f) {
            b(f);
            a(pointF, pointF2);
            a();
        }

        private void a(PointF pointF, PointF pointF2) {
            float[] a2 = a(pointF);
            this.f10573a.getMatrix(this.b);
            this.b.mapPoints(a2);
            pointF2.x = a2[0];
            pointF2.y = a2[1];
        }

        private static float[] a(PointF pointF) {
            return new float[]{pointF.x, pointF.y};
        }

        private void a() {
            this.f10573a.restore();
            this.f10573a.save();
        }

        static aijfr a(float f) {
            return new d(f);
        }
    }

    interface avpbg {
        void a(Paint paint);
    }

    static class b {
        private final float b;
        private final float c;
        private final int e;

        b(float f, float f2, int i) {
            this.b = f;
            this.c = f2;
            this.e = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static b dYu_(Context context, TypedArray typedArray) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return new b(typedArray.getDimension(2, HwGravitationalLoadingDrawable.dYn_(3.0f, displayMetrics)), typedArray.getDimension(1, HwGravitationalLoadingDrawable.dYn_(2.0f, displayMetrics)), typedArray.getInt(0, 135));
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private final PointF f10574a;
        private final PointF d;
        private final PointF e;

        c(PointF pointF, PointF pointF2, PointF pointF3) {
            a(pointF.x, "start.x");
            a(pointF.y, "start.y");
            a(pointF2.y, "middle.y");
            a(pointF3.y, "end.y");
            if (Float.compare(pointF.x, pointF2.x) >= 0) {
                throw new IllegalArgumentException("start.x >= middle.x");
            }
            if (Float.compare(pointF2.x, pointF3.x) >= 0) {
                throw new IllegalArgumentException("middle.x >= end.x");
            }
            this.e = pointF;
            this.d = pointF2;
            this.f10574a = pointF3;
        }

        private float a(float f, float f2, float f3) {
            return (f * (f3 - f2)) + f2;
        }

        private static void a(float f, String str) {
            if (f >= 0.0f) {
                return;
            }
            throw new IllegalArgumentException(str + " is negative");
        }

        float a(float f) {
            if (Float.compare(f, this.e.x) <= 0) {
                return this.e.y;
            }
            if (Float.compare(f, this.f10574a.x) >= 0) {
                return this.f10574a.y;
            }
            if (Float.compare(f, this.e.x) <= 0 || Float.compare(f, this.d.x) > 0) {
                PointF pointF = this.d;
                float f2 = pointF.x;
                PointF pointF2 = this.f10574a;
                return a((f - f2) / (pointF2.x - f2), pointF.y, pointF2.y);
            }
            PointF pointF3 = this.e;
            float f3 = pointF3.x;
            PointF pointF4 = this.d;
            return a((f - f3) / (pointF4.x - f3), pointF3.y, pointF4.y);
        }
    }

    class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwLoadingAnim", "onAnimationUpdate: null animator");
                return;
            }
            HwGravitationalLoadingDrawable.this.c.c(((Float) valueAnimator.getAnimatedValue("degrees")).floatValue(), ((Integer) valueAnimator.getAnimatedValue("alpha")).intValue(), ((Float) valueAnimator.getAnimatedValue("scale")).floatValue());
            HwGravitationalLoadingDrawable.this.invalidateSelf();
        }
    }

    static class e {
        private static final float[] e = {16.0f, 40.0f, 76.0f};

        /* renamed from: a, reason: collision with root package name */
        private static final float[] f10575a = {2.8f, 1.9f, 1.2f};
        private static final float[] c = {0.5f, 0.2f, 0.1f};
        private static final float[] d = {3.0f, 3.0f, 2.0f};
        private static final float[] b = {3.0f, 3.0f, 2.2f};

        e() {
        }

        static c a(float f) {
            return d(d, f);
        }

        static c b(float f) {
            return d(f10575a, f);
        }

        static c c(float f) {
            return d(c, f);
        }

        static c e(float f) {
            return d(b, f);
        }

        private static PointF b(float[] fArr) {
            return dYq_(1, fArr);
        }

        static c d(float[] fArr, float f) {
            return new c(dYr_(dYs_(fArr), f), dYr_(b(fArr), f), dYr_(a(fArr), f));
        }

        private static PointF dYs_(float[] fArr) {
            return dYq_(0, fArr);
        }

        private static PointF a(float[] fArr) {
            return dYq_(2, fArr);
        }

        private static PointF dYq_(int i, float[] fArr) {
            if (i >= 0) {
                float[] fArr2 = e;
                if (i < fArr2.length && i < fArr.length) {
                    return new PointF(fArr2[i], fArr[i]);
                }
            }
            return new PointF();
        }

        private static PointF dYr_(PointF pointF, float f) {
            pointF.x *= f;
            pointF.y *= f;
            return pointF;
        }
    }

    public static class f {

        /* renamed from: a, reason: collision with root package name */
        private final c f10576a;
        private final float b;
        private final float c;
        private final e d;
        private float f;
        private Canvas g;
        private float h;
        private Bitmap i;
        private float o;
        private final Paint e = new Paint(1);
        private float j = 1.0f;

        static class a {
            private static final FloatEvaluator c = new FloatEvaluator();

            /* renamed from: a, reason: collision with root package name */
            private int f10577a;
            private final aijfr b;
            private final PointF d = new PointF();

            a(aijfr aijfrVar, int i) {
                this.b = aijfrVar;
                this.f10577a = i;
            }

            void dYD_(Canvas canvas, Paint paint, float f) {
                int i = this.f10577a;
                if (i == 0) {
                    return;
                }
                paint.setAlpha(i);
                PointF pointF = this.d;
                canvas.drawCircle(pointF.x, pointF.y, f, paint);
            }

            void dYC_(float f, PointF pointF, float f2, float f3) {
                this.b.a(pointF, this.d, c.evaluate(f, (Number) Float.valueOf(f2), (Number) Float.valueOf(f2 - f3)).floatValue());
            }
        }

        static class c {

            /* renamed from: a, reason: collision with root package name */
            private final a[] f10578a;
            private final Paint b = new Paint(1);
            private final PointF c;
            private final int d;
            private final int e;
            private float g;
            private int h;

            c(PointF pointF, aijfr aijfrVar, h hVar, int i) {
                this.c = pointF;
                int i2 = hVar.f10580a;
                this.e = i2;
                this.d = hVar.d;
                this.g = hVar.d;
                this.f10578a = new a[i2];
                float f = hVar.c;
                int i3 = 0;
                while (true) {
                    a[] aVarArr = this.f10578a;
                    if (i3 >= aVarArr.length) {
                        c(i);
                        return;
                    }
                    if (i3 == 0) {
                        aVarArr[i3] = new a(aijfrVar, a(255, f));
                    } else {
                        aVarArr[i3] = new a(aijfrVar, a(aVarArr[i3 - 1].f10577a, f));
                    }
                    i3++;
                }
            }

            private int a(int i, float f) {
                return (int) (i * f);
            }

            void d(float f) {
                this.g = Math.min(f, this.d);
            }

            private void c(int i) {
                this.b.setColor(i);
                this.b.setStyle(Paint.Style.FILL);
            }

            void a() {
                this.g = this.d;
            }

            void a(float f) {
                int i = this.d;
                int i2 = 0;
                if (i <= 0) {
                    this.h = 0;
                    return;
                }
                this.h = (int) ((this.g / i) * this.e);
                while (true) {
                    int i3 = this.h;
                    if (i2 >= i3) {
                        return;
                    }
                    int i4 = i2 + 1;
                    this.f10578a[i2].dYC_(i4 / i3, this.c, f, this.g);
                    i2 = i4;
                }
            }

            void a(Canvas canvas, float f) {
                for (int i = 0; i < this.h; i++) {
                    this.f10578a[i].dYD_(canvas, this.b, f);
                }
            }
        }

        static class e {

            /* renamed from: a, reason: collision with root package name */
            private final Paint f10579a = new Paint(1);
            private final PointF b;
            private final PointF d;
            private final aijfr e;

            e(aijfr aijfrVar, PointF pointF, int i) {
                this.e = aijfrVar;
                this.d = pointF;
                this.b = new PointF(pointF.x, pointF.y);
                d(i);
            }

            private void d(int i) {
                this.f10579a.setStyle(Paint.Style.FILL);
                this.f10579a.setColor(i);
            }

            void b(float f) {
                this.e.a(this.d, this.b, f);
            }

            void dYE_(Canvas canvas, float f) {
                PointF pointF = this.b;
                canvas.drawCircle(pointF.x, pointF.y, f, this.f10579a);
            }
        }

        f(int i, float f, float f2, float f3, h hVar) {
            this.c = f;
            d(300);
            this.f = f2;
            this.o = f2;
            PointF pointF = new PointF(f3, 0.0f);
            aijfr a2 = aijfr.a(-90.0f);
            a2.d(1.0f);
            this.b = a2.a(pointF, 0.0f).y;
            this.d = new e(a2, pointF, i);
            this.f10576a = new c(pointF, a2, hVar, i);
        }

        void b(int i) {
            float f = i;
            if (f > this.h) {
                d(i);
            }
            this.j = f / this.c;
        }

        private void d(int i) {
            this.i = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            this.g = new Canvas(this.i);
            this.h = i;
        }

        public void b(float f) {
            this.f10576a.d(f);
        }

        void c(int i) {
            this.d.f10579a.setColor(i);
            this.f10576a.b.setColor(i);
        }

        void a(Canvas canvas, Rect rect, float f) {
            this.i.eraseColor(0);
            this.g.save();
            Canvas canvas2 = this.g;
            float f2 = this.j;
            canvas2.scale(f2, f2);
            float c2 = HwGravitationalLoadingDrawable.c(this.c);
            this.g.translate(c2, c2);
            this.g.rotate(-f, 0.0f, 0.0f);
            this.g.translate(0.0f, -this.b);
            this.d.dYE_(this.g, this.o);
            this.f10576a.a(this.g, this.o);
            canvas.drawBitmap(this.i, rect.left, rect.top, this.e);
            this.g.restore();
        }

        void c(float f, int i, float f2) {
            e(f);
            this.e.setAlpha(i);
            this.o = this.f * f2;
        }

        public void e(float f) {
            this.d.b(f);
            this.f10576a.a(f);
        }
    }

    static final class h {

        /* renamed from: a, reason: collision with root package name */
        private final int f10580a;
        private final float c;
        private final int d;

        h(int i, int i2, float f) {
            this.f10580a = i;
            this.d = i2;
            this.c = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static h dYw_(TypedArray typedArray) {
            int integer = typedArray.getInteger(5, 20);
            int i = integer >= 0 ? integer : 20;
            int integer2 = typedArray.getInteger(6, 60);
            int i2 = integer2 > 0 ? integer2 : 60;
            float fraction = typedArray.getFraction(4, 1, 1, 0.82f);
            return new h(i, i2, fraction >= 0.0f ? fraction : 0.82f);
        }
    }

    public static class i {

        /* renamed from: a, reason: collision with root package name */
        private final Paint f10581a;
        private final o b;
        private final boolean c;
        private final float d;
        private final o e;
        private Bitmap f;
        private float g;
        private Canvas h;
        private float i;
        private float j;

        i(float f, o oVar, boolean z) {
            this(f, oVar, null, z);
        }

        i(float f, o oVar, o oVar2, boolean z) {
            this.f10581a = new Paint(1);
            this.i = 1.0f;
            this.g = 1.0f;
            this.d = f;
            this.b = oVar;
            this.e = oVar2;
            this.c = z;
            d(300);
            e();
        }

        private void d(int i) {
            this.f = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            this.h = new Canvas(this.f);
            this.j = i;
        }

        void c(int i) {
            if (this.f == null || i > this.j) {
                d(i);
            } else {
                a();
            }
            this.g = i / this.d;
            e();
        }

        void e(int i) {
            this.b.d(i);
            o oVar = this.e;
            if (oVar != null) {
                oVar.d(i);
            }
            a();
            e();
        }

        private void a() {
            this.f.eraseColor(0);
        }

        public void e(float f) {
            this.i = f;
        }

        void a(Canvas canvas, Rect rect) {
            canvas.save();
            float f = this.i;
            canvas.scale(f, f, rect.exactCenterX(), rect.exactCenterY());
            canvas.drawBitmap(this.f, rect.left, rect.top, this.f10581a);
            canvas.restore();
        }

        private void e() {
            this.h.save();
            Canvas canvas = this.h;
            float f = this.g;
            canvas.scale(f, f);
            float c = HwGravitationalLoadingDrawable.c(this.d);
            o oVar = this.e;
            if (oVar != null) {
                oVar.dYH_(this.h, c, c);
            }
            this.b.dYH_(this.h, c, c);
            this.h.restore();
        }
    }

    static class k {

        /* renamed from: a, reason: collision with root package name */
        private final float f10582a;
        private final float b;
        private final float c;

        k(float f, float f2, float f3) {
            this.f10582a = f;
            this.b = f2;
            this.c = f3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static k b(Context context, TypedArray typedArray) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dimension = typedArray.getDimension(3, HwGravitationalLoadingDrawable.dYn_(3.0f, displayMetrics));
            float dimension2 = typedArray.getDimension(10, HwGravitationalLoadingDrawable.dYn_(17.0f, displayMetrics));
            float f = displayMetrics.density * 40.0f;
            float dimension3 = typedArray.getDimension(7, f);
            if (dimension3 > 0.0f) {
                f = dimension3;
            }
            return new k(dimension, dimension2, f);
        }
    }

    class l implements ValueAnimator.AnimatorUpdateListener {
        l() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwLoadingAnim", "onAnimationUpdate:null animator");
                return;
            }
            HwGravitationalLoadingDrawable.this.g = ((Float) valueAnimator.getAnimatedValue(TypedValues.CycleType.S_WAVE_OFFSET)).floatValue() * HwGravitationalLoadingDrawable.this.e.g;
            HwGravitationalLoadingDrawable.this.invalidateSelf();
        }
    }

    static class m {

        /* renamed from: a, reason: collision with root package name */
        private final float f10583a;
        private final float b;
        private final float c;
        private final float d;
        private final int e;

        m(float f, float f2, int i, float f3, float f4) {
            this.f10583a = f;
            this.c = f2;
            this.e = i;
            this.b = f3;
            this.d = f4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static m dYF_(Context context, TypedArray typedArray) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dimension = typedArray.getDimension(13, HwGravitationalLoadingDrawable.dYn_(10.5f, displayMetrics));
            float dimension2 = typedArray.getDimension(14, HwGravitationalLoadingDrawable.dYn_(1.9f, displayMetrics));
            int integer = typedArray.getInteger(11, 200);
            float dimension3 = typedArray.getDimension(12, HwGravitationalLoadingDrawable.dYn_(0.2f, displayMetrics));
            float f = displayMetrics.density * 40.0f;
            float dimension4 = typedArray.getDimension(7, f);
            return new m(dimension, dimension2, integer, dimension3, dimension4 <= 0.0f ? f : dimension4);
        }
    }

    static class o {

        /* renamed from: a, reason: collision with root package name */
        private float f10584a;
        private final int b;
        private final int c;
        private final Paint d;
        private final float e;

        o(int i, float f, float f2) {
            this(i, f, f2, 255);
        }

        o(int i, float f, float f2, int i2) {
            this.d = new Paint(1);
            this.c = i;
            this.e = f;
            this.f10584a = f2;
            this.b = i2;
            e();
        }

        private void e() {
            this.d.setStyle(Paint.Style.STROKE);
            this.d.setStrokeWidth(this.f10584a);
            this.d.setColor(this.c);
            this.d.setAlpha(this.b);
        }

        void d(int i) {
            this.d.setColor(i);
            this.d.setAlpha(this.b);
        }

        void b(float f) {
            this.f10584a = f;
            this.d.setStrokeWidth(f);
        }

        void b(avpbg avpbgVar) {
            avpbgVar.a(this.d);
        }

        void dYH_(Canvas canvas, float f, float f2) {
            canvas.drawCircle(f, f2, this.e, this.d);
        }
    }

    public HwGravitationalLoadingDrawable(i iVar, f fVar, int i2, float f2) {
        this.e = iVar;
        this.c = fVar;
        e(i2);
        a(i2);
        this.d = e.b(f2);
        this.f10571a = e.c(f2);
        this.b = e.a(f2);
        this.f = e.e(f2);
    }

    private void a(int i2) {
        this.j = g.dYy_(i2, 35.0f, new d());
    }

    private void b(int i2) {
        float f2 = i2;
        this.e.b.b(this.d.a(f2));
        this.c.f = this.f.a(f2);
        f fVar = this.c;
        fVar.o = fVar.f;
        if (this.e.c) {
            this.e.b.b(j.c(this.f10571a.a(f2)));
            if (this.e.e != null) {
                this.e.e.b(this.b.a(f2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float c(float f2) {
        return f2 / 2.0f;
    }

    private void e(int i2) {
        this.h = g.dYx_(i2, this.e.b.e * 2.0f, new l());
    }

    public void d(int i2) {
        int c2 = c(i2);
        this.c.c(c2);
        this.e.e(c2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(0.0f, this.g);
        this.e.a(canvas, getBounds());
        this.c.a(canvas, getBounds(), 23.3f);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        if (rect == null) {
            return;
        }
        super.onBoundsChange(rect);
        int min = Math.min(rect.width(), rect.height());
        b(min);
        this.e.c(min);
        this.c.b(min);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        b(true);
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        a();
    }

    static class j implements avpbg {
        private final float b;
        private final BlurMaskFilter.Blur c;

        j(float f, BlurMaskFilter.Blur blur) {
            this.b = f;
            this.c = blur;
        }

        @Override // com.huawei.uikit.animations.drawable.HwGravitationalLoadingDrawable.avpbg
        public void a(Paint paint) {
            paint.setMaskFilter(null);
            paint.setMaskFilter(new BlurMaskFilter(this.b, this.c));
        }

        static j c(float f) {
            return new j(f, BlurMaskFilter.Blur.NORMAL);
        }
    }

    public static i c(int i2, a aVar) {
        if (aVar.c && aVar.f10572a == null) {
            throw new IllegalArgumentException("create for night mode, but BackgroundRingParams is null");
        }
        return aVar.c ? c(i2, aVar.b, aVar.f10572a) : b(i2, aVar.b);
    }

    public void b(boolean z) {
        if (this.i) {
            return;
        }
        if (z) {
            this.c.f10576a.a();
        }
        this.j.start();
        this.h.start();
        this.i = true;
    }

    static class g {
        g() {
        }

        private static ValueAnimator dYA_(long j, float f) {
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat(TypedValues.CycleType.S_WAVE_OFFSET, 0.0f, -f));
            ofPropertyValuesHolder.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.67f, 1.0f));
            ofPropertyValuesHolder.setDuration(j / 4);
            return ofPropertyValuesHolder;
        }

        static Animator dYx_(long j, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            float f2 = f * 0.06f;
            ValueAnimator dYA_ = dYA_(j, f2);
            dYA_.addUpdateListener(animatorUpdateListener);
            ValueAnimator dYz_ = dYz_(j, f2);
            dYz_.addUpdateListener(animatorUpdateListener);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(dYA_, dYz_);
            return animatorSet;
        }

        private static ValueAnimator dYz_(long j, float f) {
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat(TypedValues.CycleType.S_WAVE_OFFSET, -f, f));
            ofPropertyValuesHolder.setDuration(j / 2);
            ofPropertyValuesHolder.setInterpolator(PathInterpolatorCompat.create(0.33f, 0.0f, 0.67f, 1.0f));
            ofPropertyValuesHolder.setRepeatCount(-1);
            ofPropertyValuesHolder.setRepeatMode(2);
            return ofPropertyValuesHolder;
        }

        static ValueAnimator dYy_(int i, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofKeyframe("scale", Keyframe.ofFloat(0.0f, 0.93f), Keyframe.ofFloat(0.4f, 0.65f), Keyframe.ofFloat(0.9f, 1.0f), Keyframe.ofFloat(1.0f, 0.93f)), PropertyValuesHolder.ofKeyframe("alpha", Keyframe.ofInt(0.0f, 255), Keyframe.ofInt(0.4f, 51), Keyframe.ofInt(0.8f, 255), Keyframe.ofInt(1.0f, 255)), PropertyValuesHolder.ofFloat("degrees", f, 360.0f + f));
            ofPropertyValuesHolder.setDuration(i);
            ofPropertyValuesHolder.setInterpolator(new LinearInterpolator());
            ofPropertyValuesHolder.setRepeatCount(-1);
            ofPropertyValuesHolder.setRepeatMode(1);
            ofPropertyValuesHolder.addUpdateListener(animatorUpdateListener);
            return ofPropertyValuesHolder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float dYn_(float f2, DisplayMetrics displayMetrics) {
        return f2 * displayMetrics.density;
    }

    void a() {
        if (this.i) {
            this.j.end();
            this.h.end();
            this.i = false;
        }
    }

    private static i b(int i2, m mVar) {
        return new i(mVar.d, new o(i2, mVar.f10583a, mVar.c, mVar.e), false);
    }

    private static i c(int i2, m mVar, b bVar) {
        o oVar = new o(i2, mVar.f10583a, bVar.b, bVar.e);
        oVar.b(j.c(bVar.c));
        o oVar2 = new o(i2, mVar.f10583a, mVar.c);
        oVar2.b(j.c(mVar.b));
        return new i(mVar.d, oVar2, oVar, true);
    }

    public static f e(int i2, a aVar) {
        return new f(i2, aVar.e.c, aVar.e.f10582a, aVar.e.b, aVar.d);
    }

    public static int c(int i2) {
        return ColorUtils.setAlphaComponent(i2, 255);
    }
}
