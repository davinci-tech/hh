package com.huawei.ucd.medal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.huawei.ucd.gles.engine.android.StageView;
import com.huawei.ucd.helper.gles.Obj3DBufferLoadAider;
import com.huawei.ucd.medal.MedalBackContent;
import com.huawei.ucd.medal.MedalBackType;
import defpackage.njw;
import defpackage.nkf;
import defpackage.nki;
import defpackage.nkk;

/* loaded from: classes9.dex */
public class MedalView extends StageView {

    /* renamed from: a, reason: collision with root package name */
    private nkf f8751a;
    private nkf b;
    private nkf d;
    private nkf e;
    private TimeInterpolator f;
    private nkk g;
    private long h;
    private nkf i;
    private Context j;
    private boolean k;
    private boolean l;
    private GestureDetector m;
    private boolean n;
    private Medal3D o;
    private Rect p;
    private nkf q;
    private ValueAnimator s;
    private nkf t;

    public MedalView(Context context) {
        super(context);
        this.n = false;
        this.h = 800L;
        this.g = new nkk();
        this.l = false;
        this.k = false;
        e(context);
    }

    public MedalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = false;
        this.h = 800L;
        this.g = new nkk();
        this.l = false;
        this.k = false;
        e(context);
    }

    private void e(Context context) {
        this.j = context;
        this.m = new GestureDetector(this.j, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.ucd.medal.MedalView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (MedalView.this.o != null) {
                    MedalView.this.o.a(f);
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                njw.c("MedalView", njw.b());
                return super.onDoubleTap(motionEvent);
            }
        });
        requestFocus();
        setFocusableInTouchMode(true);
        setTranslucent(new nki());
        a();
        b();
    }

    private void b() {
        Medal3D medal3D = new Medal3D(this.j);
        this.o = medal3D;
        medal3D.setMaterialFromAssets("medal/medal.mat");
        this.o.e(-30.0f, 20.0f, 10.0f);
        this.o.e(0.5f, 0.3f, 0.4f, 93.0f);
        this.o.d(100.0f, -50.0f, 10.0f);
        this.o.c(0.1f, 0.2f, 0.4f, 91.0f);
        this.c.d(this.o);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (motionEvent.getAction() == 0) {
            Rect rect = this.p;
            this.n = rect == null || (rawX > ((float) rect.left) && rawX < ((float) this.p.right) && rawY > ((float) this.p.top) && rawY < ((float) this.p.bottom));
        }
        if (this.n) {
            this.m.onTouchEvent(motionEvent);
            Medal3D medal3D = this.o;
            if (medal3D != null) {
                medal3D.cwz_(motionEvent);
            }
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.n = false;
        }
        return this.n;
    }

    public void setTexture(Bitmap bitmap) {
        Medal3D medal3D = this.o;
        if (medal3D == null) {
            return;
        }
        medal3D.setTexture(bitmap);
    }

    public void setTexture(Bitmap bitmap, MedalViewCallback medalViewCallback) {
        Medal3D medal3D = this.o;
        if (medal3D == null) {
            medalViewCallback.onResponse(-1, "medal3D is null!");
        } else {
            medal3D.setTexture(bitmap, medalViewCallback);
        }
    }

    public void setTexture(Bitmap bitmap, boolean z) {
        Medal3D medal3D = this.o;
        if (medal3D == null) {
            return;
        }
        medal3D.setTexture(bitmap, z);
    }

    public void setObjData(Obj3DBufferLoadAider.d dVar) {
        Medal3D medal3D = this.o;
        if (medal3D == null) {
            return;
        }
        medal3D.a(dVar.b(), dVar.c(), dVar.d());
    }

    @Override // com.huawei.ucd.gles.engine.android.StageView, android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        Medal3D medal3D = this.o;
        if (medal3D != null) {
            medal3D.onResume();
        }
    }

    @Override // com.huawei.ucd.gles.engine.android.StageView, android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        Medal3D medal3D = this.o;
        if (medal3D != null) {
            medal3D.onPause();
        }
    }

    @Override // com.huawei.ucd.gles.engine.android.StageView
    public void c() {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.s.end();
            this.s = null;
        }
        Medal3D medal3D = this.o;
        if (medal3D != null) {
            medal3D.onDestroy();
            this.o = null;
        }
        this.p = null;
        nkk nkkVar = this.g;
        if (nkkVar != null) {
            nkkVar.c();
            this.g = null;
        }
        this.b = null;
        this.i = null;
        this.e = null;
        this.f8751a = null;
        this.d = null;
        this.t = null;
        this.q = null;
        this.f = null;
        super.c();
    }

    public void setTouchRect(Rect rect) {
        this.p = rect;
    }

    public void setAnimatorScaleX(float f, float f2) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            njw.c("MedalView", njw.b() + " mShowAnimator is running");
            return;
        }
        this.b = new nkf(f, f2);
    }

    public void setAnimatorScaleY(float f, float f2) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            njw.c("MedalView", njw.b() + " mShowAnimator is running");
            return;
        }
        this.i = new nkf(f, f2);
    }

    public void setAnimatorRotationY(float f, float f2) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.e = new nkf(f, f2);
        }
    }

    public void setAnimatorPositionX(float f, float f2) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.f8751a = new nkf(f, f2);
        }
    }

    public void setAnimatorPositionY(float f, float f2) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.d = new nkf(f, f2);
        }
    }

    public void setAnimatorDuration(long j) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.h = j;
        }
    }

    public void setAnimatorInterpolator(TimeInterpolator timeInterpolator) {
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.f = timeInterpolator;
        }
    }

    public void e() {
        this.o.runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.MedalView.5
            @Override // java.lang.Runnable
            public void run() {
                float f;
                float f2;
                float f3;
                float f4 = 0.0f;
                if (MedalView.this.f8751a != null) {
                    f = MedalView.this.f8751a.f15343a;
                    f2 = MedalView.this.f8751a.e;
                } else {
                    f = 0.0f;
                    f2 = 0.0f;
                }
                if (MedalView.this.d != null) {
                    f4 = MedalView.this.d.f15343a;
                    f3 = MedalView.this.d.e;
                } else {
                    f3 = 0.0f;
                }
                if (MedalView.this.o == null) {
                    return;
                }
                MedalView medalView = MedalView.this;
                medalView.t = medalView.o.e(f, f4);
                MedalView medalView2 = MedalView.this;
                medalView2.q = medalView2.o.e(f2, f3);
            }
        });
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.s.cancel();
        }
        this.s = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.g.b();
        this.s.setDuration(this.h);
        this.s.setInterpolator(this.f);
        this.s.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ucd.medal.MedalView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                if (MedalView.this.o == null) {
                    return;
                }
                final float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                MedalView.this.o.runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.MedalView.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        float f = MedalView.this.b != null ? MedalView.this.b.f15343a + ((MedalView.this.b.e - MedalView.this.b.f15343a) * floatValue) : 0.1f;
                        float f2 = MedalView.this.i != null ? MedalView.this.i.f15343a + ((MedalView.this.i.e - MedalView.this.i.f15343a) * floatValue) : 0.1f;
                        float f3 = MedalView.this.e != null ? MedalView.this.e.f15343a + ((MedalView.this.e.e - MedalView.this.e.f15343a) * floatValue) : 0.0f;
                        float f4 = MedalView.this.t != null ? MedalView.this.t.f15343a + ((MedalView.this.q.f15343a - MedalView.this.t.f15343a) * floatValue) : 0.0f;
                        float f5 = MedalView.this.q != null ? MedalView.this.t.e + ((MedalView.this.q.e - MedalView.this.t.e) * floatValue) : 0.0f;
                        if (MedalView.this.o == null) {
                            return;
                        }
                        MedalView.this.o.b(f, f2, 0.1f);
                        if (!MedalView.this.l) {
                            MedalView.this.o.e(f3);
                            float abs = Math.abs((Math.abs(0.5f - floatValue) * 2.0f) - 1.0f);
                            MedalView.this.o.b(12.0f * abs);
                            MedalView.this.o.d(abs * (-6.0f));
                        }
                        MedalView.this.o.a(f4, f5, -80.0f);
                    }
                });
                MedalView.this.o.requestRender();
            }
        });
        this.s.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.ucd.medal.MedalView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (MedalView.this.o == null) {
                    return;
                }
                MedalView.this.o.runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.medal.MedalView.3.5
                    @Override // java.lang.Runnable
                    public void run() {
                        MedalView.this.o.e(0.0f);
                        MedalView.this.o.b(0.0f);
                        MedalView.this.o.d(0.0f);
                    }
                });
                MedalView.this.o.requestRender();
            }
        });
        this.l = false;
        this.s.start();
    }

    public void setAutoRotate(final boolean z) {
        setRenderMode(z ? 1 : 0);
        ValueAnimator valueAnimator = this.s;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.o.a(z);
        } else {
            this.s.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.ucd.medal.MedalView.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (MedalView.this.o == null) {
                        return;
                    }
                    MedalView.this.o.a(z);
                }
            });
        }
    }

    public void setFrameRotationYAngle(float f) {
        this.o.c(f);
    }

    public void setBackContent(MedalBackContent medalBackContent) {
        Medal3D medal3D = this.o;
        if (medal3D != null) {
            medal3D.e(medalBackContent);
        }
    }

    public void setBackContent(String[] strArr, Bitmap bitmap, MedalBackType.ModelType modelType, MedalBackType.ColorType colorType) {
        setBackContent(new MedalBackContent.Builder(this.j).a(modelType).d(colorType).cwC_(bitmap).d(strArr).a());
    }

    public void setBackContent(String[] strArr, MedalBackType.ModelType modelType, MedalBackType.ColorType colorType) {
        setBackContent(strArr, null, modelType, colorType);
    }

    public void setBackContentTest(String[] strArr, Bitmap bitmap, MedalBackType.ModelType modelType, MedalBackType.ColorType colorType) {
        this.o.cwA_(new MedalBackContent.Builder(this.j).a(modelType).d(colorType).cwC_(bitmap).d(strArr).a(), bitmap);
    }
}
