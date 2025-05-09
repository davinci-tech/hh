package com.huawei.uikit.hwbubblelayout.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import defpackage.slq;
import defpackage.smr;

/* loaded from: classes7.dex */
public class HwBubbleLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f10606a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private Paint ae;
    private Path af;
    private Context ag;
    private int ah;
    private slq ai;
    private int aj;
    private boolean ak;
    private boolean al;
    private int am;
    private ArrowDirection an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private int aw;
    private int ax;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private float g;
    private int h;
    private int i;
    private HwColumnSystem j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public enum ArrowDirection {
        TOP(2),
        BOTTOM(4);

        int b;

        ArrowDirection(int i) {
            this.b = i;
        }

        public static ArrowDirection getDirection(int i) {
            return i != 2 ? BOTTOM : TOP;
        }
    }

    class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (outline != null) {
                outline.setRoundRect(HwBubbleLayout.this.ao, HwBubbleLayout.this.aw, HwBubbleLayout.this.ax, HwBubbleLayout.this.f10606a, HwBubbleLayout.this.d);
            }
        }
    }

    static /* synthetic */ class e {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[ArrowDirection.values().length];
            c = iArr;
            try {
                iArr[ArrowDirection.BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[ArrowDirection.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public HwBubbleLayout(Context context) {
        this(context, null);
    }

    public ArrowDirection getArrowDirection() {
        return this.an;
    }

    public int getArrowPosition() {
        return this.am;
    }

    public int getBubbleColor() {
        return this.b;
    }

    public int getBubbleRadius() {
        return this.d;
    }

    public int getBubbleWidth() {
        return ((this.ap - this.aq) - (this.d * 2)) + (this.aa * 2);
    }

    public int getShadowSize() {
        if (this.ai == null) {
            return -1;
        }
        return this.k;
    }

    public int getShadowStyle() {
        if (this.ai == null) {
            return -1;
        }
        return this.o;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f) {
            this.j.e(4);
            this.j.d(this.ag, this.h, this.i, this.g);
        } else {
            this.j.e(4);
            this.j.e(this.ag);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        RectF rectF = new RectF(this.ao, this.aw, this.ax, this.f10606a);
        float f = this.d;
        canvas.drawRoundRect(rectF, f, f, this.ae);
        canvas.drawPath(this.af, this.ae);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.e) {
            a(getContext());
            int size = View.MeasureSpec.getSize(i);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(d(size), View.MeasureSpec.getMode(i)), i2);
        } else {
            super.onMeasure(i, i2);
        }
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int e2 = e(this.ag);
        if (e2 > measuredWidth) {
            setMeasuredDimension(e2, measuredHeight);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        try {
            if (bundle.containsKey("arrow_position")) {
                this.am = bundle.getInt("arrow_position", 0);
            }
            Parcelable parcelable2 = bundle.getParcelable("instanceState");
            if (parcelable2 != null) {
                super.onRestoreInstanceState(parcelable2);
            }
        } catch (BadParcelableException unused) {
            Log.e("HwBubbleLayout", "Parcelable, onRestoreInstanceState error");
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        try {
            bundle.putParcelable("instanceState", super.onSaveInstanceState());
            bundle.putInt("arrow_position", this.am);
        } catch (BadParcelableException unused) {
            Log.e("HwBubbleLayout", "Parcelable, onSaveInstanceState error");
        }
        return bundle;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.ap = i;
        this.as = i2;
        d();
    }

    public void setArrowDirection(ArrowDirection arrowDirection) {
        this.an = arrowDirection;
        c();
        d();
    }

    public void setArrowPosition(int i) {
        this.am = i;
        d();
        invalidate();
    }

    public void setArrowPositionCenter(boolean z) {
        this.ak = z;
        d();
    }

    public void setArrowStartLocation(int i) {
        if (this.aj == i) {
            return;
        }
        this.aj = i;
        d();
        invalidate();
    }

    public void setBubbleColor(int i) {
        this.b = i;
        d();
    }

    public void setInsideShadowClip(boolean z) {
        slq slqVar = this.ai;
        if (slqVar != null) {
            slqVar.d(z);
        }
    }

    public void setShadowEnabled(boolean z) {
        this.n = z;
        if (this.ai == null) {
            this.ai = new slq(this.ag, this, this.k, this.o);
        }
        this.ai.a(this.n);
    }

    public void setShadowSize(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        slq slqVar = this.ai;
        if (slqVar != null) {
            slqVar.a(i);
            if (this.n) {
                this.ai.c();
            }
        }
    }

    public void setShadowStyle(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        slq slqVar = this.ai;
        if (slqVar != null) {
            slqVar.e(i);
            if (this.n) {
                this.ai.c();
            }
        }
    }

    public HwBubbleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100219_res_0x7f06023b);
    }

    private void a(Context context) {
        if (this.f) {
            c(context);
        } else {
            d(context);
        }
    }

    private static Context b(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwBubbleLayout);
    }

    private void c() {
        if (e.c[this.an.ordinal()] != 1) {
            setPadding(0, this.ar, 0, 0);
        } else {
            setPadding(0, 0, 0, this.ar);
        }
    }

    private void d(Context context) {
        this.j.e(4);
        this.j.e(context);
    }

    public HwBubbleLayout(Context context, AttributeSet attributeSet, int i) {
        super(b(context, i), attributeSet, i);
        this.aj = 1;
        this.al = false;
        this.e = false;
        this.f = false;
        Context context2 = getContext();
        this.ag = context2;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context2);
        this.j = hwColumnSystem;
        hwColumnSystem.e(4);
        this.f = false;
        this.j.e(this.ag);
        setWillNotDraw(false);
        eaI_(this.ag.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100182_res_0x7f060216, R.attr._2131100183_res_0x7f060217, R.attr._2131100184_res_0x7f060218, R.attr._2131100185_res_0x7f060219, R.attr._2131100217_res_0x7f060239, R.attr._2131100220_res_0x7f06023c, R.attr._2131100265_res_0x7f060269, R.attr._2131100522_res_0x7f06036a, R.attr._2131100523_res_0x7f06036b, R.attr._2131100606_res_0x7f0603be}, i, R.style.Widget_Emui_HwBubbleLayout));
        Paint paint = new Paint(5);
        this.ae = paint;
        paint.setStyle(Paint.Style.FILL);
        this.af = new Path();
        c();
    }

    private void eaI_(TypedArray typedArray) {
        this.an = ArrowDirection.getDirection(typedArray.getInt(0, ArrowDirection.BOTTOM.b));
        this.am = typedArray.getDimensionPixelSize(1, -1);
        this.ak = typedArray.getBoolean(2, false);
        if (this.am < 0) {
            this.ak = true;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362605_res_0x7f0a032d);
        this.c = dimensionPixelSize;
        this.d = typedArray.getDimensionPixelSize(5, dimensionPixelSize);
        this.aj = typedArray.getInt(3, 1);
        this.b = typedArray.getColor(4, getResources().getColor(R.color._2131297382_res_0x7f090466));
        this.e = typedArray.getBoolean(6, false);
        this.n = typedArray.getBoolean(7, false);
        this.o = typedArray.getInt(9, 0);
        this.k = typedArray.getInt(8, 2);
        typedArray.recycle();
        slq slqVar = new slq(this.ag, this, this.k, this.o);
        this.ai = slqVar;
        slqVar.a(this.n);
        double sqrt = Math.sqrt(2.0d);
        int i = this.d;
        int i2 = (int) ((2.0d - sqrt) * 2.0d * i);
        this.ah = i2;
        this.aa -= i2 - i;
        this.aq = getResources().getDimensionPixelSize(R.dimen._2131363996_res_0x7f0a089c);
        this.ar = getResources().getDimensionPixelSize(R.dimen._2131363995_res_0x7f0a089b);
        this.l = getResources().getDimensionPixelSize(R.dimen._2131363979_res_0x7f0a088b);
        this.m = getResources().getDimensionPixelSize(R.dimen._2131363987_res_0x7f0a0893);
        this.q = getResources().getDimensionPixelSize(R.dimen._2131363980_res_0x7f0a088c);
        this.s = getResources().getDimensionPixelSize(R.dimen._2131363988_res_0x7f0a0894);
        this.r = getResources().getDimensionPixelSize(R.dimen._2131363981_res_0x7f0a088d);
        this.t = getResources().getDimensionPixelSize(R.dimen._2131363989_res_0x7f0a0895);
        this.p = getResources().getDimensionPixelSize(R.dimen._2131363982_res_0x7f0a088e);
        this.w = getResources().getDimensionPixelSize(R.dimen._2131363990_res_0x7f0a0896);
        this.v = getResources().getDimensionPixelSize(R.dimen._2131363983_res_0x7f0a088f);
        this.y = getResources().getDimensionPixelSize(R.dimen._2131363991_res_0x7f0a0897);
        this.x = getResources().getDimensionPixelSize(R.dimen._2131363984_res_0x7f0a0890);
        this.u = getResources().getDimensionPixelSize(R.dimen._2131363992_res_0x7f0a0898);
        this.z = getResources().getDimensionPixelSize(R.dimen._2131363985_res_0x7f0a0891);
        this.ab = getResources().getDimensionPixelSize(R.dimen._2131363993_res_0x7f0a0899);
        this.ac = getResources().getDimensionPixelSize(R.dimen._2131363986_res_0x7f0a0892);
        this.ad = getResources().getDimensionPixelSize(R.dimen._2131363994_res_0x7f0a089a);
    }

    private boolean a() {
        return getLayoutDirection() == 1;
    }

    private void c(Context context) {
        this.j.e(4);
        this.j.d(context, this.h, this.i, this.g);
    }

    private void d() {
        if (this.ai == null) {
            slq slqVar = new slq(this.ag, this, this.k, this.o);
            this.ai = slqVar;
            slqVar.a(this.n);
        }
        this.ao = 0;
        this.aw = this.an == ArrowDirection.TOP ? this.ar : 0;
        this.ax = this.ap;
        this.f10606a = this.as - (this.an == ArrowDirection.BOTTOM ? this.ar : 0);
        this.ae.setColor(this.b);
        this.af.reset();
        if (this.al) {
            int i = this.ax;
            int i2 = this.ao;
            int i3 = this.d;
            int i4 = ((i - i2) - i3) - i3;
            if (this.aq > i4) {
                this.aq = i4;
                this.ak = true;
            }
        }
        int i5 = e.c[this.an.ordinal()];
        if (i5 == 1) {
            e(b());
        } else if (i5 == 2) {
            c(b());
        }
        this.af.close();
        setOutlineProvider(new a());
        setClipToOutline(false);
    }

    private int d(int i) {
        int h = this.j.h();
        return (h < 0 || h > i) ? i : h;
    }

    private void c(int i) {
        if (a()) {
            int i2 = (this.ax - i) + this.ao;
            this.af.moveTo(i2, this.aw);
            this.af.quadTo(i2 - this.v, this.aw, i2 - this.l, r2 - this.m);
            Path path = this.af;
            float f = i2 - this.x;
            int i3 = this.aw;
            path.quadTo(f, i3 - this.u, i2 - this.q, i3 - this.s);
            Path path2 = this.af;
            float f2 = i2 - this.z;
            int i4 = this.aw;
            path2.quadTo(f2, i4 - this.ab, i2 - this.r, i4 - this.t);
            Path path3 = this.af;
            float f3 = i2 - this.ac;
            float f4 = this.aw;
            path3.quadTo(f3, f4, i2 - this.p, f4);
            return;
        }
        this.af.moveTo(i, this.aw);
        this.af.quadTo(this.v + i, this.aw, this.l + i, r2 - this.m);
        Path path4 = this.af;
        float f5 = this.x + i;
        int i5 = this.aw;
        path4.quadTo(f5, i5 - this.u, this.q + i, i5 - this.s);
        Path path5 = this.af;
        float f6 = this.z + i;
        int i6 = this.aw;
        path5.quadTo(f6, i6 - this.ab, this.r + i, i6 - this.t);
        Path path6 = this.af;
        float f7 = this.ac + i;
        float f8 = this.aw;
        path6.quadTo(f7, f8, i + this.p, f8);
    }

    private int b() {
        int i = this.am;
        int i2 = this.ao;
        int i3 = this.d;
        int i4 = this.aa;
        int i5 = ((i + i2) + i3) - i4;
        if (this.ak) {
            return (((this.ax - i2) / 2) - (this.aq / 2)) + i2;
        }
        if (this.aj == 1) {
            int i6 = this.aq;
            int i7 = this.ax;
            return i5 + i6 > (i7 - i3) + i4 ? ((i7 - i6) - i3) + i4 : i5;
        }
        int i8 = (((this.ax - i3) - this.aq) - i) + i4;
        int i9 = (i2 + i3) - i4;
        return i8 < i9 ? i9 : i8;
    }

    private void e(int i) {
        if (a()) {
            int i2 = (this.ax - i) + this.ao;
            this.af.moveTo(i2, this.f10606a);
            this.af.quadTo(i2 - this.v, this.f10606a, i2 - this.l, r2 + this.m);
            Path path = this.af;
            float f = i2 - this.x;
            int i3 = this.f10606a;
            path.quadTo(f, this.u + i3, i2 - this.q, i3 + this.s);
            Path path2 = this.af;
            float f2 = i2 - this.z;
            int i4 = this.f10606a;
            path2.quadTo(f2, this.ab + i4, i2 - this.r, i4 + this.t);
            Path path3 = this.af;
            float f3 = i2 - this.ac;
            float f4 = this.f10606a;
            path3.quadTo(f3, f4, i2 - this.p, f4);
            return;
        }
        this.af.moveTo(i, this.f10606a);
        this.af.quadTo(this.v + i, this.f10606a, this.l + i, r2 + this.m);
        Path path4 = this.af;
        float f5 = this.x + i;
        int i5 = this.f10606a;
        path4.quadTo(f5, this.u + i5, this.q + i, i5 + this.s);
        Path path5 = this.af;
        float f6 = this.z + i;
        int i6 = this.f10606a;
        path5.quadTo(f6, this.ab + i6, this.r + i, i6 + this.t);
        Path path6 = this.af;
        float f7 = this.ac + i;
        float f8 = this.f10606a;
        path6.quadTo(f7, f8, i + this.p, f8);
    }

    private int e(Context context) {
        if (context == null) {
            return 0;
        }
        int h = ((this.j.h() - (this.d * 2)) - this.aq) + (this.aa * 2);
        if (!this.e || h < 0) {
            int i = context.getResources().getDisplayMetrics().widthPixels;
            int i2 = this.d;
            h = (this.aa * 2) + ((i - (i2 * 2)) - this.aq);
        }
        if (this.am > h) {
            this.am = h;
        }
        return (((this.d * 2) + this.aq) + this.am) - (this.aa * 2);
    }
}
