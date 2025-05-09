package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class hmg {

    /* renamed from: a, reason: collision with root package name */
    private final float f13247a;
    private float b;
    private PointF c;
    private SecureRandom d = new SecureRandom();
    private ImageView e;
    private final float g;
    private PointF h;
    private float i;
    private float j;

    public hmg(ImageView imageView, PointF pointF) {
        imageView.setVisibility(4);
        this.e = imageView;
        this.c = pointF;
        this.b = 0.0f;
        this.g = c(0.2f, 1.0f);
        float c = c(0.4f, 1.6f);
        this.f13247a = c;
        this.i = c * 0.4f;
        this.j = c(0.0f, 360.0f);
        int h = nsn.h(imageView.getContext()) / 2;
        this.h = bkH_(c(h - 250, h + 250), pointF, c(0.1f, 6.27f));
        imageView.setAlpha(this.b);
        imageView.setRotation(this.j);
    }

    private PointF bkH_(double d, PointF pointF, double d2) {
        PointF pointF2 = new PointF();
        float cos = (float) (pointF.x + (Math.cos(d2) * d));
        float sin = (float) (pointF.y + (d * Math.sin(d2)));
        pointF2.x = cos;
        pointF2.y = sin;
        return pointF2;
    }

    private float c(float f, float f2) {
        return (this.d.nextFloat() * (f2 - f)) + f;
    }

    private int e() {
        return this.d.nextInt(400) + 2800;
    }

    public void b() {
        final long e = e();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, "alpha", this.b, this.g);
        ofFloat.setDuration(e / 4);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.e, "alpha", this.g, this.b);
        ofFloat2.setDuration((3 * e) / 4);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(ofFloat, ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.e, "scaleX", this.f13247a, this.i);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.e, "scaleY", this.f13247a, this.i);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(e);
        animatorSet2.playTogether(ofFloat3, ofFloat4);
        AnimatorSet animatorSet3 = new AnimatorSet();
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.e, "x", this.c.x, this.h.x);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.e, "y", this.c.y, this.h.y);
        animatorSet3.setDuration(e);
        animatorSet3.playTogether(ofFloat5, ofFloat6);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(animatorSet3, animatorSet2, animatorSet);
        animatorSet4.setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f));
        animatorSet4.addListener(new AnimatorListenerAdapter() { // from class: hmg.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                hmg.this.e.setVisibility(0);
                hmg.this.e.animate().rotationBy(180.0f).setDuration(e).start();
            }
        });
        animatorSet4.start();
    }
}
