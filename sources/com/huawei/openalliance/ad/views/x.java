package com.huawei.openalliance.ad.views;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.util.Property;
import android.view.View;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class x {

    /* renamed from: a, reason: collision with root package name */
    private static int f8200a = 80;
    private ValueAnimator b;
    private AnimatorSet c;
    private Bitmap d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;

    public Bitmap f() {
        return this.d;
    }

    public float e() {
        return this.f + (this.j * this.h);
    }

    public float d() {
        return this.e + (this.i * this.g);
    }

    public float c() {
        return this.f;
    }

    public float b() {
        return this.e;
    }

    public AnimatorSet a() {
        return this.c;
    }

    public x(float[][] fArr, int[] iArr, Bitmap bitmap, final View view) {
        this.d = bitmap;
        this.i = bitmap.getWidth();
        this.j = bitmap.getHeight();
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, Keyframe.ofFloat(0.0f, view.getWidth() * fArr[0][0]), Keyframe.ofFloat(1.0f, (view.getWidth() * fArr[0][1]) - (this.i / 2.0f)));
        Property property = View.TRANSLATION_Y;
        Keyframe[] keyframeArr = new Keyframe[2];
        keyframeArr[0] = Keyframe.ofFloat(0.0f, view.getHeight() * fArr[1][0]);
        keyframeArr[1] = Keyframe.ofFloat(1.0f, fArr[1][1] < 0.0f ? (view.getHeight() * fArr[1][1]) - this.j : view.getHeight() * fArr[1][1]);
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(ofKeyframe, PropertyValuesHolder.ofKeyframe(property, keyframeArr), PropertyValuesHolder.ofKeyframe(View.SCALE_X, Keyframe.ofFloat(0.0f, fArr[2][0]), Keyframe.ofFloat(1.0f, fArr[2][1])), PropertyValuesHolder.ofKeyframe(View.SCALE_Y, Keyframe.ofFloat(0.0f, fArr[2][0]), Keyframe.ofFloat(1.0f, fArr[2][1])));
        this.b = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setInterpolator(new gq(1.0f, 0.0f, 1.0f, 1.0f));
        this.b.setDuration(iArr[1] + f8200a);
        this.b.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.openalliance.ad.views.x.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    x.this.e = ((Float) valueAnimator.getAnimatedValue(View.TRANSLATION_X.getName())).floatValue();
                    x.this.f = ((Float) valueAnimator.getAnimatedValue(View.TRANSLATION_Y.getName())).floatValue();
                    x.this.g = ((Float) valueAnimator.getAnimatedValue(View.SCALE_X.getName())).floatValue();
                    x.this.h = ((Float) valueAnimator.getAnimatedValue(View.SCALE_Y.getName())).floatValue();
                    view.postInvalidate();
                } catch (Throwable th) {
                    ho.c("ParticleAnimator", "onAnimationUpdate: %s", th.getClass().getSimpleName());
                }
            }
        });
        this.c = new AnimatorSet();
        PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe(View.ALPHA, Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(1.0f, 1.0f));
        ValueAnimator ofPropertyValuesHolder2 = ValueAnimator.ofPropertyValuesHolder(ofKeyframe2);
        ofPropertyValuesHolder2.setDuration(iArr[0]);
        ValueAnimator ofPropertyValuesHolder3 = ValueAnimator.ofPropertyValuesHolder(ofKeyframe2);
        ofPropertyValuesHolder3.setDuration(((1400 - iArr[0]) - iArr[1]) - f8200a);
        this.c.playSequentially(ofPropertyValuesHolder2, this.b, ofPropertyValuesHolder3);
    }
}
