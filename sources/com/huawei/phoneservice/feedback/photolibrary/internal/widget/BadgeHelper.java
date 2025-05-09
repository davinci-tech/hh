package com.huawei.phoneservice.feedback.photolibrary.internal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.phoneservice.faq.base.util.i;

/* loaded from: classes5.dex */
public class BadgeHelper extends View {

    /* renamed from: a, reason: collision with root package name */
    private int f8273a;
    private final RectF b;
    private Paint c;
    private float d;
    private int e;
    private int h;
    private int i;
    private boolean j;

    public void setBadgeNumber(int i) {
        this.e = i;
        if (this.j) {
            setVisibility(i == 0 ? 4 : 0);
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = this.i;
        if (i4 <= 0 || (i3 = this.h) <= 0) {
            throw new IllegalStateException("如果你自定义了小红点的宽高,就不能设置其宽高小于0!");
        }
        setMeasuredDimension(i4, i3);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.b;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = getWidth();
        this.b.bottom = getHeight();
        canvas.drawRoundRect(this.b, getWidth() / 2.0f, getWidth() / 2.0f, this.c);
    }

    public BadgeHelper b(boolean z, boolean z2) {
        if (!z && z2) {
            i.a("BadgeHelper", "只有重叠模式isOverlap=true时，设置mIgnoreTargetPadding才生效");
        }
        return this;
    }

    public BadgeHelper a(boolean z) {
        return b(z, false);
    }

    public BadgeHelper ceA_(View view) {
        e();
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (view == null) {
            return this;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            a(view, viewGroup, indexOfChild);
            this.j = true;
        } else if (view.getParent() == null) {
            throw new IllegalStateException("目标View不能没有父布局!");
        }
        setVisibility(this.e == 0 ? 4 : 0);
        return this;
    }

    private void a(View view, ViewGroup viewGroup, int i) {
        FrameLayout frameLayout = new FrameLayout(getContext());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        frameLayout.setLayoutParams(layoutParams);
        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        viewGroup.addView(frameLayout, i, layoutParams);
        frameLayout.addView(view);
        frameLayout.addView(this);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams2.gravity = 8388661;
        setLayoutParams(layoutParams2);
    }

    private void e() {
        this.d = getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        this.c = paint;
        paint.setStyle(Paint.Style.FILL);
        this.c.setFlags(1);
        this.c.setColor(this.f8273a);
        this.h = Math.round(this.d * 7.0f);
        this.i = Math.round(this.d * 7.0f);
    }

    public BadgeHelper(Context context) {
        super(context);
        this.b = new RectF();
        this.f8273a = -2936293;
    }
}
