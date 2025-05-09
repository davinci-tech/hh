package com.huawei.ui.device.views.music;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.huawei.health.R;
import defpackage.nsn;
import defpackage.ocm;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class AlphabetIndexWaveSideBarView extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f9315a;
    private int ab;
    private int b;
    private int c;
    private float d;
    private Context e;
    private int f;
    private int g;
    private int h;
    private int i;
    private List<String> j;
    private Paint k;
    private OnLetterChangeListener l;
    private int m;
    private int n;
    private ValueAnimator o;
    private RectF p;
    private int q;
    private int r;
    private int s;
    private TextPaint t;
    private Path u;
    private int v;
    private int w;
    private Paint x;
    private int y;

    public interface OnLetterChangeListener {
        void onLetterChange(String str);
    }

    public AlphabetIndexWaveSideBarView(Context context) {
        this(context, null);
    }

    public AlphabetIndexWaveSideBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AlphabetIndexWaveSideBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        cVt_(attributeSet, i);
        b();
    }

    private void cVt_(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099763_res_0x7f060073, R.attr._2131099765_res_0x7f060075, R.attr._2131099945_res_0x7f060129, R.attr._2131100166_res_0x7f060206, R.attr._2131100167_res_0x7f060207, R.attr._2131100171_res_0x7f06020b, R.attr._2131100172_res_0x7f06020c, R.attr._2131100676_res_0x7f060404, R.attr._2131101083_res_0x7f06059b, R.attr._2131101086_res_0x7f06059e, R.attr._2131101150_res_0x7f0605de, R.attr._2131101242_res_0x7f06063a, R.attr._2131101252_res_0x7f060644, R.attr._2131101358_res_0x7f0606ae, R.attr._2131101363_res_0x7f0606b3}, i, 0);
        if (nsn.v(this.e)) {
            this.s = obtainStyledAttributes.getColor(11, getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            this.s = obtainStyledAttributes.getColor(11, getResources().getColor(R.color._2131296471_res_0x7f0900d7));
        }
        setTextSize(obtainStyledAttributes);
        this.f = obtainStyledAttributes.getColor(6, getResources().getColor(R.color._2131296871_res_0x7f090267));
        this.g = obtainStyledAttributes.getDimensionPixelOffset(4, (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.i = obtainStyledAttributes.getColor(3, getResources().getColor(R.color._2131296472_res_0x7f0900d8));
        this.v = obtainStyledAttributes.getColor(13, getResources().getColor(R.color._2131296472_res_0x7f0900d8));
        this.ab = obtainStyledAttributes.getDimensionPixelOffset(14, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
        this.b = obtainStyledAttributes.getDimensionPixelOffset(2, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.f9315a = obtainStyledAttributes.getDimensionPixelOffset(0, (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics()));
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics()));
        this.c = dimensionPixelOffset;
        if (dimensionPixelOffset == 0) {
            this.c = this.y;
        }
        obtainStyledAttributes.recycle();
    }

    private void setTextSize(TypedArray typedArray) {
        if (typedArray.getBoolean(7, false)) {
            if (nsn.s()) {
                this.y = typedArray.getDimensionPixelOffset(12, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
                this.r = typedArray.getDimensionPixelOffset(8, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
                this.h = typedArray.getDimensionPixelOffset(5, (int) TypedValue.applyDimension(1, 32.0f, getResources().getDisplayMetrics()));
                return;
            } else {
                this.y = typedArray.getDimensionPixelOffset(12, (int) TypedValue.applyDimension(2, 10.0f, getResources().getDisplayMetrics()));
                this.r = typedArray.getDimensionPixelOffset(8, (int) TypedValue.applyDimension(2, 10.0f, getResources().getDisplayMetrics()));
                this.h = typedArray.getDimensionPixelOffset(5, (int) TypedValue.applyDimension(2, 16.0f, getResources().getDisplayMetrics()));
                return;
            }
        }
        this.y = typedArray.getDimensionPixelOffset(12, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()));
        this.r = typedArray.getDimensionPixelOffset(8, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()));
        this.h = typedArray.getDimensionPixelOffset(5, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
    }

    private void b() {
        this.j = Arrays.asList(getContext().getResources().getStringArray(R.array._2130968698_res_0x7f04007a));
        this.t = new TextPaint();
        Paint paint = new Paint();
        this.k = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.x = paint2;
        paint2.setAntiAlias(true);
        this.u = new Path();
        this.q = -1;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.p == null) {
            this.p = new RectF();
        }
        float measuredWidth = (getMeasuredWidth() - this.c) - this.f9315a;
        int measuredWidth2 = getMeasuredWidth();
        int i3 = this.f9315a;
        float measuredHeight = getMeasuredHeight() - this.f9315a;
        this.p.set(measuredWidth, i3, measuredWidth2 - i3, measuredHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cVq_(canvas);
        cVs_(canvas);
        cVp_(canvas);
        cVr_(canvas);
    }

    private void cVq_(Canvas canvas) {
        this.k.setStyle(Paint.Style.FILL);
        if (nsn.v(this.e)) {
            this.k.setColor(this.e.getResources().getColor(R.color._2131296657_res_0x7f090191));
        } else {
            this.k.setColor(-1);
        }
        canvas.drawRect(this.p, this.k);
        float size = this.j.size() > 0 ? ((this.p.bottom - this.p.top) - (this.b * 2)) / this.j.size() : 0.0f;
        for (int i = 0; i < this.j.size(); i++) {
            this.t.setTypeface(Typeface.create("Roboto-Regular", 0));
            this.t.setColor(this.s);
            this.t.setTextSize(this.y);
            this.t.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.j.get(i), this.p.left + ((this.p.right - this.p.left) / 2.0f), ocm.cVH_(this.p.top + this.b + (i * size) + (size / 2.0f), this.t, this.y), this.t);
        }
    }

    private void cVs_(Canvas canvas) {
        this.u.reset();
        this.u.moveTo(getMeasuredWidth(), this.w - (this.ab * 3));
        int measuredWidth = getMeasuredWidth();
        int i = this.w - (this.ab * 2);
        float measuredWidth2 = (int) (getMeasuredWidth() - ((this.ab * Math.cos(0.7853981633974483d)) * this.d));
        this.u.quadTo(measuredWidth, i, measuredWidth2, (int) (i + (this.ab * Math.sin(0.7853981633974483d))));
        this.u.quadTo((int) (getMeasuredWidth() - ((this.ab * 1.8f) * this.d)), this.w, measuredWidth2, (int) (((r1 * 2) + r3) - (r1 * Math.cos(0.7853981633974483d))));
        int measuredWidth3 = getMeasuredWidth();
        int i2 = this.w;
        int i3 = this.ab;
        this.u.quadTo(getMeasuredWidth(), this.w + (this.ab * 2), measuredWidth3, i2 + (i3 * 3));
        this.u.close();
        this.x.setStyle(Paint.Style.FILL);
        this.x.setColor(this.v);
        canvas.drawPath(this.u, this.x);
    }

    private void cVr_(Canvas canvas) {
        if (this.q != -1) {
            this.t.setColor(getResources().getColor(R.color._2131299061_res_0x7f090af5));
            this.t.setTextSize(this.r);
            this.t.setTextAlign(Paint.Align.CENTER);
            float size = this.j.size() > 0 ? ((this.p.bottom - this.p.top) - (this.b * 2)) / this.j.size() : 0.0f;
            canvas.drawText(this.j.get(this.q), this.p.left + ((this.p.right - this.p.left) / 2.0f), ocm.cVH_(this.p.top + this.b + (this.q * size) + (size / 2.0f), this.t, this.y), this.t);
        }
    }

    private void cVp_(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        float f = (measuredWidth + r1) - (((this.ab * 2.0f) + (this.g * 2.0f)) * this.d);
        this.x.setStyle(Paint.Style.FILL);
        this.x.setColor(this.i);
        canvas.drawCircle(f, this.w, this.g, this.x);
        if (this.q != -1) {
            this.t.setColor(this.f);
            this.t.setTextSize(this.h);
            this.t.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.j.get(this.q), f, ocm.cVH_(this.w, this.t, this.h), this.t);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002f, code lost:
    
        if (r5 != 3) goto L37;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r5.getY()
            float r1 = r5.getX()
            int r2 = r4.q
            r4.n = r2
            android.graphics.RectF r2 = r4.p
            float r2 = r2.bottom
            android.graphics.RectF r3 = r4.p
            float r3 = r3.top
            float r2 = r2 - r3
            float r2 = r0 / r2
            java.util.List<java.lang.String> r3 = r4.j
            int r3 = r3.size()
            float r3 = (float) r3
            float r2 = r2 * r3
            int r2 = (int) r2
            r4.m = r2
            int r5 = r5.getAction()
            r2 = 1
            if (r5 == 0) goto L85
            if (r5 == r2) goto L5d
            r1 = 2
            if (r5 == r1) goto L33
            r0 = 3
            if (r5 == r0) goto L5d
            goto Lac
        L33:
            int r5 = (int) r0
            r4.w = r5
            int r5 = r4.n
            int r0 = r4.m
            if (r5 == r0) goto L59
            if (r0 < 0) goto L59
            java.util.List<java.lang.String> r5 = r4.j
            int r5 = r5.size()
            if (r0 >= r5) goto L59
            int r5 = r4.m
            r4.q = r5
            com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView$OnLetterChangeListener r0 = r4.l
            if (r0 == 0) goto L59
            java.util.List<java.lang.String> r1 = r4.j
            java.lang.Object r5 = r1.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r0.onLetterChange(r5)
        L59:
            r4.invalidate()
            goto Lac
        L5d:
            int r5 = r4.n
            int r0 = r4.m
            if (r5 == r0) goto L80
            if (r0 < 0) goto L80
            java.util.List<java.lang.String> r5 = r4.j
            int r5 = r5.size()
            if (r0 >= r5) goto L80
            int r5 = r4.m
            r4.q = r5
            com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView$OnLetterChangeListener r0 = r4.l
            if (r0 == 0) goto L80
            java.util.List<java.lang.String> r1 = r4.j
            java.lang.Object r5 = r1.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r0.onLetterChange(r5)
        L80:
            r5 = 0
            r4.b(r5)
            goto Lac
        L85:
            android.graphics.RectF r5 = r4.p
            float r5 = r5.left
            int r3 = r4.f9315a
            float r3 = (float) r3
            float r5 = r5 - r3
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            r1 = 0
            if (r5 >= 0) goto L93
            return r1
        L93:
            android.graphics.RectF r5 = r4.p
            float r5 = r5.top
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 < 0) goto Lad
            android.graphics.RectF r5 = r4.p
            float r5 = r5.bottom
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 <= 0) goto La4
            goto Lad
        La4:
            int r5 = (int) r0
            r4.w = r5
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.b(r5)
        Lac:
            return r2
        Lad:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    private void b(float f) {
        if (this.o == null) {
            this.o = new ValueAnimator();
        }
        this.o.cancel();
        this.o.setFloatValues(f);
        this.o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.device.views.music.AlphabetIndexWaveSideBarView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AlphabetIndexWaveSideBarView.this.d = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (AlphabetIndexWaveSideBarView.this.d == 1.0f && AlphabetIndexWaveSideBarView.this.n != AlphabetIndexWaveSideBarView.this.m && AlphabetIndexWaveSideBarView.this.m >= 0 && AlphabetIndexWaveSideBarView.this.m < AlphabetIndexWaveSideBarView.this.j.size()) {
                    AlphabetIndexWaveSideBarView alphabetIndexWaveSideBarView = AlphabetIndexWaveSideBarView.this;
                    alphabetIndexWaveSideBarView.q = alphabetIndexWaveSideBarView.m;
                    if (AlphabetIndexWaveSideBarView.this.l != null) {
                        AlphabetIndexWaveSideBarView.this.l.onLetterChange((String) AlphabetIndexWaveSideBarView.this.j.get(AlphabetIndexWaveSideBarView.this.m));
                    }
                }
                AlphabetIndexWaveSideBarView.this.invalidate();
            }
        });
        this.o.start();
    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        this.l = onLetterChangeListener;
    }
}
