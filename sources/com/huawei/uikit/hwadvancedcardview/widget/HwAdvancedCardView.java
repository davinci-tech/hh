package com.huawei.uikit.hwadvancedcardview.widget;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.cardview.widget.CardView;
import com.huawei.health.R;
import defpackage.sku;
import defpackage.slq;
import defpackage.smr;
import defpackage.sms;

/* loaded from: classes7.dex */
public class HwAdvancedCardView extends CardView {

    /* renamed from: a, reason: collision with root package name */
    private static final int f10587a = 7;
    private static final String b = "HwAdvancedCardView";
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private Context i;
    private AnimatorSet j;
    private AnimatorSet k;
    private slq l;
    private Path m;
    private Paint n;
    private boolean o;

    public HwAdvancedCardView(Context context) {
        this(context, null);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100176_res_0x7f060210, R.attr._2131100342_res_0x7f0602b6, R.attr._2131100522_res_0x7f06036a, R.attr._2131100523_res_0x7f06036b, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwAdvancedCardView);
        this.h = obtainStyledAttributes.getBoolean(0, false);
        this.o = obtainStyledAttributes.getBoolean(1, false);
        this.g = obtainStyledAttributes.getBoolean(2, false);
        this.c = obtainStyledAttributes.getInt(4, 0);
        this.d = obtainStyledAttributes.getInt(3, 4);
        obtainStyledAttributes.recycle();
        slq slqVar = new slq(this.i, this, this.d, this.c);
        this.l = slqVar;
        slqVar.a(this.g);
    }

    public static HwAdvancedCardView instantiate(Context context) {
        Object e = sms.e(context, sms.e(context, (Class<?>) HwAdvancedCardView.class, sms.c(context, 7, 1)), (Class<?>) HwAdvancedCardView.class);
        if (e instanceof HwAdvancedCardView) {
            return (HwAdvancedCardView) e;
        }
        return null;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (!this.o) {
            super.draw(canvas);
            return;
        }
        if (this.m == null) {
            return;
        }
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        super.draw(canvas);
        this.n.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPath(this.m, this.n);
        canvas.restoreToCount(saveLayer);
        this.n.setXfermode(null);
    }

    public boolean getClickAnimationEnable() {
        return this.h;
    }

    public int getClickAnimationType() {
        return this.f;
    }

    public boolean getForceClipRoundCorner() {
        return this.o;
    }

    public int getShadowSize() {
        return this.l == null ? this.e : this.d;
    }

    public int getShadowStyle() {
        return this.l == null ? this.e : this.c;
    }

    public boolean isShadowEnabled() {
        return this.g;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!this.o) {
            super.onSizeChanged(i, i2, i3, i4);
            return;
        }
        RectF rectF = new RectF(0.0f, 0.0f, i, i2);
        float radius = super.getRadius();
        Path path = new Path();
        path.addRoundRect(rectF, radius, radius, Path.Direction.CW);
        Path path2 = new Path();
        this.m = path2;
        path2.addRect(rectF, Path.Direction.CW);
        this.m.op(path, Path.Op.DIFFERENCE);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w(b, "onTouchEvent: MotionEvent motionEvent is null!");
            return false;
        }
        if (!this.h) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            AnimatorSet animatorSet = this.k;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet ebp_ = sku.ebp_(this, this.f);
            this.j = ebp_;
            ebp_.start();
        } else if (action == 1 || action == 3) {
            AnimatorSet animatorSet2 = this.j;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            AnimatorSet ebq_ = sku.ebq_(this, this.f);
            this.k = ebq_;
            ebq_.start();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setClickAnimationEnable(boolean z) {
        this.h = z;
    }

    public void setClickAnimationType(int i) {
        this.f = i;
    }

    public void setForceClipRoundCorner(boolean z) {
        this.o = z;
    }

    public void setInsideShadowClip(boolean z) {
        slq slqVar = this.l;
        if (slqVar != null) {
            slqVar.d(z);
        }
    }

    public void setShadowEnabled(boolean z) {
        this.g = z;
        if (this.l == null) {
            this.l = new slq(this.i, this, this.d, this.c);
        }
        this.l.a(this.g);
    }

    public void setShadowSize(int i) {
        if (this.d == i) {
            return;
        }
        this.d = i;
        slq slqVar = this.l;
        if (slqVar != null) {
            slqVar.a(i);
            if (this.g) {
                this.l.c();
            }
        }
    }

    public void setShadowStyle(int i) {
        if (this.c == i) {
            return;
        }
        this.c = i;
        slq slqVar = this.l;
        if (slqVar != null) {
            slqVar.e(i);
            if (this.g) {
                this.l.c();
            }
        }
    }

    public HwAdvancedCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100177_res_0x7f060211);
    }

    public HwAdvancedCardView(Context context, AttributeSet attributeSet, int i) {
        super(a(context, i), attributeSet, i);
        this.e = -1;
        this.f = 1;
        this.g = false;
        this.h = true;
        this.j = null;
        this.k = null;
        this.o = false;
        this.i = super.getContext();
        Paint paint = new Paint();
        this.n = paint;
        paint.setAntiAlias(true);
        this.n.setColor(-1);
        a(this.i, attributeSet, i);
    }

    private static Context a(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwAdvancedCardView);
    }
}
