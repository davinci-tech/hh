package com.huawei.uikit.hwviewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes9.dex */
public class HwPagerTabStrip extends HwPagerTitleStrip {
    private int f;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private float m;
    private int n;
    private float o;
    private final Rect p;
    private boolean q;
    private boolean r;
    private final Paint s;
    private boolean t;
    private int u;
    private int w;

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwViewPager hwViewPager = HwPagerTabStrip.this.b;
            hwViewPager.setCurrentItem(hwViewPager.getCurrentItem() + 1);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwPagerTabStrip.this.b.setCurrentItem(r0.getCurrentItem() - 1);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public HwPagerTabStrip(Context context) {
        this(context, null);
    }

    private void b() {
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
    }

    private void d(float f, float f2) {
        if (this.b.getPageScrollDirection() != 0) {
            if (f2 < this.e.getTop() - this.i) {
                this.b.setCurrentItem(r3.getCurrentItem() - 1);
                return;
            } else {
                if (f2 > this.e.getBottom() + this.i) {
                    HwViewPager hwViewPager = this.b;
                    hwViewPager.setCurrentItem(hwViewPager.getCurrentItem() + 1);
                    return;
                }
                return;
            }
        }
        boolean isRtlLayout = this.b.isRtlLayout();
        if (f < this.e.getLeft() - this.i) {
            HwViewPager hwViewPager2 = this.b;
            hwViewPager2.setCurrentItem(isRtlLayout ? hwViewPager2.getCurrentItem() + 1 : hwViewPager2.getCurrentItem() - 1);
        } else if (f > this.e.getRight() + this.i) {
            HwViewPager hwViewPager3 = this.b;
            hwViewPager3.setCurrentItem(isRtlLayout ? hwViewPager3.getCurrentItem() - 1 : hwViewPager3.getCurrentItem() + 1);
        }
    }

    public boolean getDrawFullUnderline() {
        return this.t;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerTitleStrip
    int getMinHeight() {
        return Math.max(super.getMinHeight(), this.n);
    }

    public int getTabIndicatorColor() {
        return this.u;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        HwViewPager hwViewPager = this.b;
        boolean z = false;
        if (hwViewPager != null) {
            r2 = hwViewPager.getPageScrollDirection() == 0;
            z = this.b.isRtlLayout();
        }
        boolean z2 = r2;
        boolean z3 = z;
        int height = getHeight();
        int bottom = z2 ? height : this.e.getBottom() + this.w;
        int left = this.e.getLeft();
        int i = this.i;
        int right = this.e.getRight();
        int i2 = this.i;
        int i3 = this.w;
        this.s.setColor((this.h << 24) | (this.u & ViewCompat.MEASURED_SIZE_MASK));
        canvas.drawRect(left - i, bottom - i3, right + i2, bottom, this.s);
        if (this.t) {
            this.s.setColor((this.u & ViewCompat.MEASURED_SIZE_MASK) | (-16777216));
            eiD_(canvas, z2, z3, height);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0 && this.q) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (action == 0) {
            this.m = x;
            this.o = y;
            this.q = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x - this.m) > this.j || Math.abs(y - this.o) > this.j)) {
                this.q = true;
            }
        } else if (this.b != null) {
            d(x, y);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        if (this.r) {
            return;
        }
        this.t = (i & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.r) {
            return;
        }
        this.t = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.r) {
            return;
        }
        this.t = i == 0;
    }

    public void setDrawFullUnderline(boolean z) {
        this.t = z;
        this.r = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        int i5 = this.l;
        if (i4 < i5) {
            i4 = i5;
        }
        super.setPadding(i, i2, i3, i4);
    }

    public void setTabIndicatorColor(int i) {
        this.u = i;
        this.s.setColor(i);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i) {
        setTabIndicatorColor(ContextCompat.getColor(getContext(), i));
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerTitleStrip
    public void setTextSpacing(int i) {
        int i2 = this.k;
        if (i < i2) {
            i = i2;
        }
        super.setTextSpacing(i);
    }

    public HwPagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.s = paint;
        this.p = new Rect();
        this.h = 255;
        this.t = false;
        this.r = false;
        int i = this.c;
        this.u = i;
        paint.setColor(i);
        float f = context.getResources().getDisplayMetrics().density;
        this.w = (int) ((3.0f * f) + 0.5f);
        this.i = (int) ((16.0f * f) + 0.5f);
        this.f = (int) ((1.0f * f) + 0.5f);
        this.n = (int) ((32.0f * f) + 0.5f);
        this.l = (int) ((6.0f * f) + 0.5f);
        this.k = (int) (f * 64.0f);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
        b();
        setWillNotDraw(false);
        this.f10776a.setFocusable(true);
        this.f10776a.setOnClickListener(new d());
        this.d.setFocusable(true);
        this.d.setOnClickListener(new b());
        if (getBackground() == null) {
            this.t = true;
        }
    }

    private void eiD_(Canvas canvas, boolean z, boolean z2, int i) {
        if (z) {
            canvas.drawRect(getPaddingLeft(), i - this.f, getWidth() - getPaddingRight(), i, this.s);
        } else if (z2) {
            canvas.drawRect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + this.f, i, this.s);
        } else {
            canvas.drawRect((getWidth() - getPaddingRight()) - this.f, getPaddingTop(), getWidth() - getPaddingRight(), i, this.s);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerTitleStrip
    void a(int i, float f, boolean z) {
        Rect rect = this.p;
        int height = getHeight();
        int left = this.e.getLeft();
        int i2 = this.i;
        int right = this.e.getRight();
        int i3 = this.i;
        int i4 = height - this.w;
        rect.set(left - i2, i4, right + i3, height);
        super.a(i, f, z);
        this.h = (int) (Math.abs(f - 0.5f) * 2.0f * 255.0f);
        rect.union(this.e.getLeft() - this.i, i4, this.e.getRight() + this.i, height);
        invalidate(rect);
    }
}
