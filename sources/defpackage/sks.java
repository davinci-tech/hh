package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.Log;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;

/* loaded from: classes7.dex */
public class sks extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Context f17096a;
    private Drawable b;
    protected int c;
    private Drawable d;
    protected Rect e;
    private int f;
    private ValueAnimator g;
    private ValueAnimator h;
    private int i;
    private int j;
    private int k;
    private Path l;
    private ValueAnimator.AnimatorUpdateListener n;

    class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("ComplexDrawable", "onAnimationUpdate: Param valueAnimator is null");
                return;
            }
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue == null || !(animatedValue instanceof Integer)) {
                return;
            }
            sks.this.e(((Integer) animatedValue).intValue());
        }
    }

    public sks(Context context, Drawable drawable) {
        this(context, drawable, 0);
    }

    private void dZt_(Drawable drawable, int i) {
        this.i = this.f17096a.getResources().getInteger(R.integer._2131623962_res_0x7f0e001a);
        if (i == 0) {
            this.c = this.f17096a.getResources().getDimensionPixelSize(R.dimen._2131363955_res_0x7f0a0873);
        } else {
            this.c = i;
        }
        a();
        a(drawable);
        this.n = new d();
        this.l = new Path();
        b();
    }

    protected void a() {
        int i = this.c;
        this.e = new Rect(0, 0, i, i);
    }

    public void a(int i) {
        Drawable drawable = this.d;
        if (drawable == null) {
            return;
        }
        this.f = i;
        drawable.setTint(i);
        invalidateSelf();
    }

    public void b(int i) {
        Drawable drawable = this.b;
        if (drawable == null) {
            return;
        }
        this.k = i;
        drawable.setTint(i);
        invalidateSelf();
    }

    public void c(boolean z, boolean z2) {
        if (z2) {
            b(z);
        } else {
            e(z ? (int) (this.c * 1.42f) : 0);
        }
    }

    public Drawable dZv_() {
        return this.b;
    }

    public void dZw_(Drawable drawable) {
        a(drawable);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (canvas == null) {
            Log.w("ComplexDrawable", "draw: Param canvas is null");
            return;
        }
        this.l.reset();
        this.l.addCircle(c() ? this.e.right : this.e.left, this.e.bottom, this.j, Path.Direction.CCW);
        canvas.save();
        canvas.clipPath(this.l, Region.Op.DIFFERENCE);
        if (this.b.isAutoMirrored() && c()) {
            Rect rect = this.e;
            canvas.translate(rect.right + rect.left, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.b.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.clipPath(this.l);
        if (this.b.isAutoMirrored() && c()) {
            Rect rect2 = this.e;
            canvas.translate(rect2.right + rect2.left, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.d.draw(canvas);
        canvas.restore();
    }

    public void e(int i) {
        this.j = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
        Drawable drawable2 = this.d;
        if (drawable2 != null) {
            drawable2.setAlpha(i);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        Drawable drawable2 = this.d;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
        }
    }

    public sks(Context context, Drawable drawable, int i) {
        this.j = 0;
        this.f17096a = context;
        dZt_(drawable, i);
    }

    private boolean c() {
        return getLayoutDirection() == 1;
    }

    private void b() {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, (int) (this.c * 1.42f));
        this.g = ofInt;
        ofInt.setDuration(this.i);
        this.g.addUpdateListener(this.n);
        this.g.setInterpolator(new HwCubicBezierInterpolator(0.2f, 0.0f, 0.2f, 1.0f));
        ValueAnimator ofInt2 = ValueAnimator.ofInt((int) (this.c * 1.42f), 0);
        this.h = ofInt2;
        ofInt2.setDuration(this.i);
        this.h.addUpdateListener(this.n);
        this.h.setInterpolator(new HwCubicBezierInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
    }

    public void d(int i) {
        this.c = i;
        this.e.set(0, 0, i, i);
        this.g.setIntValues(0, (int) (this.c * 1.42f));
        this.h.setIntValues((int) (this.c * 1.42f), 0);
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setBounds(this.e);
        }
        Drawable drawable2 = this.d;
        if (drawable2 != null) {
            drawable2.setBounds(this.e);
        }
        invalidateSelf();
    }

    private void a(Drawable drawable) {
        Drawable a2;
        if (!(drawable instanceof StateListDrawable)) {
            if (drawable != null) {
                dZu_(drawable, drawable.getConstantState().newDrawable().mutate());
                return;
            }
            return;
        }
        StateListDrawable stateListDrawable = (StateListDrawable) drawable;
        int identifier = this.f17096a.getResources().getIdentifier("state_selected", "attr", OsType.ANDROID);
        int[] iArr = new int[0];
        int[] iArr2 = {identifier};
        int[] iArr3 = {~identifier};
        Drawable drawable2 = null;
        if (Build.VERSION.SDK_INT >= 29) {
            int findStateDrawableIndex = stateListDrawable.findStateDrawableIndex(iArr3);
            a2 = findStateDrawableIndex != -1 ? stateListDrawable.getStateDrawable(findStateDrawableIndex) : null;
            int findStateDrawableIndex2 = stateListDrawable.findStateDrawableIndex(iArr2);
            if (findStateDrawableIndex2 != -1) {
                drawable2 = stateListDrawable.getStateDrawable(findStateDrawableIndex2);
            }
        } else {
            int dZs_ = dZs_(stateListDrawable, iArr3);
            a2 = dZs_ != -1 ? a(stateListDrawable, dZs_) : null;
            int dZs_2 = dZs_(stateListDrawable, iArr2);
            if (dZs_2 != -1) {
                drawable2 = a(stateListDrawable, dZs_2);
            }
        }
        if (a2 == null && drawable2 == null) {
            dZu_(drawable, drawable.getConstantState().newDrawable().mutate());
            return;
        }
        if (a2 != null && drawable2 != null) {
            dZu_(a2, drawable2);
            return;
        }
        if (dZs_(stateListDrawable, iArr) != -1) {
            int dZs_3 = dZs_(stateListDrawable, iArr);
            if (a2 == null) {
                a2 = a(stateListDrawable, dZs_3);
            }
            if (drawable2 == null) {
                drawable2 = a(stateListDrawable, dZs_3);
            }
            dZu_(a2, drawable2);
            return;
        }
        throw new IllegalArgumentException("no resource available to provide");
    }

    private Drawable a(StateListDrawable stateListDrawable, int i) {
        Object c = slc.c(stateListDrawable, "getStateDrawable", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)}, StateListDrawable.class);
        if (c != null) {
            return (Drawable) c;
        }
        return null;
    }

    private int dZs_(StateListDrawable stateListDrawable, int[] iArr) {
        Object c = slc.c(stateListDrawable, "getStateDrawableIndex", new Class[]{iArr.getClass()}, new Object[]{iArr}, StateListDrawable.class);
        if (c != null) {
            return ((Integer) c).intValue();
        }
        return -1;
    }

    private void dZu_(Drawable drawable, Drawable drawable2) {
        if (drawable == null || drawable2 == null) {
            return;
        }
        this.b = drawable;
        drawable.setBounds(this.e);
        this.d = drawable2;
        drawable2.setBounds(this.e);
        invalidateSelf();
    }

    private void b(boolean z) {
        ValueAnimator valueAnimator = z ? this.h : this.g;
        ValueAnimator valueAnimator2 = z ? this.g : this.h;
        if (valueAnimator.isRunning()) {
            valueAnimator.reverse();
        } else {
            valueAnimator2.start();
        }
    }
}
