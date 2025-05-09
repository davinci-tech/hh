package defpackage;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import com.huawei.operation.utils.Constants;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class ska extends Drawable {
    private boolean b;
    private final Paint e;
    private final ArgbEvaluator f;
    private final int[] g;
    private final int[] h;
    private final int[] i;
    private ValueAnimator j;
    private final float[] k;
    private float l;
    private int m;
    private int n;
    private int o;
    private boolean s;
    private static final int[] d = {-1, -394759, -1315861, -1973269, -2038542};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f17084a = {-8741633, -11108097, -14855696, -16762657, -16435250};
    private static final float[] c = {0.0f, 0.3f, 0.6f, 0.8f, 1.0f};

    class c implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ int[] b;
        final /* synthetic */ int[] c;

        c(int[] iArr, int[] iArr2) {
            this.b = iArr;
            this.c = iArr2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwFloatingBubbleRadialDrawable", "onAnimationUpdate: animation is null");
                return;
            }
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            for (int i = 0; i < ska.this.i.length; i++) {
                ska.this.i[i] = ((Integer) ska.this.f.evaluate(floatValue, Integer.valueOf(this.b[i]), Integer.valueOf(this.c[i]))).intValue();
            }
            ska.this.invalidateSelf();
        }
    }

    public float c() {
        return this.l;
    }

    public void d(int i, int i2, int i3, float f) {
        float max = Float.compare(f, 1.2f) == -1 ? Math.max(((f - 1.2f) * 1.111111f) + 1.0f, 0.0f) : 1.0f;
        boolean z = Float.compare(max, this.l) == 0;
        if (this.o == i && this.n == i2 && this.m == i3 && z) {
            return;
        }
        this.o = i;
        this.n = i2;
        this.m = i3;
        this.l = max;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        float width = bounds.width() * 0.5f;
        float[] fArr = {(this.o * 0.5f) - (this.n + (bounds.width() * 0.5f)), 0.0f - (this.m + (bounds.height() * 0.5f))};
        float sqrt = (float) Math.sqrt((r5 * r5) + (r6 * r6));
        float min = (Math.min(sqrt / ((float) Math.sqrt((r4 * r4) + 0.0f)), 1.0f) * width) / sqrt;
        float[] fArr2 = {fArr[0] * min, min * fArr[1]};
        float centerX = bounds.centerX() + fArr2[0];
        float centerY = bounds.centerY() + fArr2[1];
        float f = fArr2[0];
        float sqrt2 = ((float) Math.sqrt((f * f) + (r7 * r7))) + width;
        this.e.setShader(new RadialGradient(centerX, centerY, sqrt2, this.i, this.k, Shader.TileMode.CLAMP));
        if (Float.compare(this.l, 1.0f) == -1) {
            this.e.setAlpha((int) (this.l * 255.0f));
            if (this.s) {
                this.e.setMaskFilter(new BlurMaskFilter((1.0f - this.l) * 25.0f, BlurMaskFilter.Blur.INNER));
            }
        } else {
            this.e.setAlpha(255);
            if (this.s) {
                this.e.setMaskFilter(null);
            }
        }
        try {
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), width, this.e);
        } catch (IllegalArgumentException unused) {
            Log.w("HwFloatingBubbleRadialDrawable", "draw: bounds=" + bounds.toString() + " mParentWidth=" + this.o + ", mLeft=" + this.n + ", mTop=" + this.m);
            Log.e("HwFloatingBubbleRadialDrawable", "draw failed: cx=" + bounds.centerX() + ", cy=" + bounds.centerY() + ", radius=" + width + ", Shader(cx=" + centerX + ", cy=" + centerY + ", radius=" + sqrt2 + ", colors=" + Arrays.toString(this.i) + ", stops=" + Arrays.toString(this.k) + Constants.RIGHT_BRACKET_ONLY);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        if (iArr == null) {
            return false;
        }
        int length = iArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (iArr[i] == 16842913) {
                z = true;
                break;
            }
            i++;
        }
        if (z == this.b) {
            return false;
        }
        this.b = z;
        ValueAnimator valueAnimator = this.j;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.j.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.j = ofFloat;
        ofFloat.setInterpolator(new LinearInterpolator());
        this.j.setDuration(300L);
        this.j.addUpdateListener(new c(Arrays.copyOf(this.i, 5), this.b ? Arrays.copyOf(this.g, 5) : Arrays.copyOf(this.h, 5)));
        this.j.start();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
