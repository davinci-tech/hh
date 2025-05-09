package com.huawei.uikit.hwcolumnlayout.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;

/* loaded from: classes9.dex */
public class HwColumnFrameLayout extends FrameLayout implements HwColumnLayoutable {

    /* renamed from: a, reason: collision with root package name */
    private HwColumnSystem f10617a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private float f;
    private int h;
    private int j;

    public HwColumnFrameLayout(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        this.f10617a.e(this.c);
        this.f10617a.e(context);
        this.e = this.f10617a.h();
        this.b = this.f10617a.b();
    }

    private void b(Context context) {
        this.f10617a.e(this.c);
        this.f10617a.d(context, this.j, this.h, this.f);
        this.e = this.f10617a.h();
        this.b = this.f10617a.b();
    }

    private boolean e(int i, int i2, float f) {
        return i > 0 && i2 > 0 && f > 0.0f;
    }

    private void ebt_(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount == 0) {
            viewGroup.requestLayout();
            viewGroup.invalidate();
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ebt_((ViewGroup) childAt);
            } else if (childAt != null) {
                childAt.requestLayout();
                childAt.invalidate();
            }
        }
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void configureColumn(int i, int i2, float f) {
        if (!e(i, i2, f)) {
            if (this.d) {
                this.d = false;
                d(getContext());
                ebt_(this);
                return;
            }
            return;
        }
        this.d = true;
        this.j = i;
        this.h = i2;
        this.f = f;
        d(getContext());
        ebt_(this);
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public int getColumnType() {
        int i = this.c;
        if (i == 2) {
            return 1;
        }
        if (i == 18) {
            return 17;
        }
        return i;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f10617a.c(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
    
        if (r2 != 4) goto L23;
     */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r1 = android.view.View.MeasureSpec.getSize(r5)
            int r2 = r4.c
            r3 = 17
            if (r2 == r3) goto L33
            r3 = 18
            if (r2 == r3) goto L21
            if (r2 == 0) goto L21
            r3 = 1
            if (r2 == r3) goto L33
            r3 = 2
            if (r2 == r3) goto L21
            r3 = 3
            if (r2 == r3) goto L21
            r3 = 4
            if (r2 == r3) goto L21
            goto L3d
        L21:
            android.content.Context r2 = r4.getContext()
            r4.d(r2)
            int r2 = r4.e
            if (r2 >= r1) goto L3d
            if (r2 <= 0) goto L3d
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            goto L3d
        L33:
            int r1 = r4.a(r5, r6, r1)
            if (r1 <= 0) goto L3d
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
        L3d:
            super.onMeasure(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwcolumnlayout.widget.HwColumnFrameLayout.onMeasure(int, int):void");
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void setColumnType(int i) {
        this.c = i;
        ebt_(this);
    }

    public HwColumnFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HwColumnFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = Integer.MIN_VALUE;
        this.e = Integer.MIN_VALUE;
        this.b = Integer.MIN_VALUE;
        this.d = false;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context);
        this.f10617a = hwColumnSystem;
        hwColumnSystem.c(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100266_res_0x7f06026a});
        this.c = obtainStyledAttributes.getInteger(0, Integer.MIN_VALUE);
        obtainStyledAttributes.recycle();
    }

    private void d(Context context) {
        if (!this.d) {
            a(getContext());
        } else {
            b(getContext());
        }
        this.f10617a.c(false);
    }

    private int a(int i, int i2, int i3) {
        int childCount = getChildCount();
        int i4 = 0;
        if (childCount == 1) {
            d(getContext());
            View childAt = getChildAt(0);
            childAt.setMinimumWidth(this.e);
            measureChild(childAt, i, i2);
            int measuredWidth = childAt.getMeasuredWidth();
            int i5 = this.e;
            if ((measuredWidth < i5 && i5 < i3) || (measuredWidth > (i5 = this.b) && i5 < i3)) {
                i4 = i5;
            } else if (measuredWidth < i3) {
                i4 = measuredWidth;
            } else {
                Log.e("HwColumnFrameLayout", "invalid width");
            }
        }
        if (childCount != 2) {
            return i4;
        }
        if (this.c == 1) {
            this.c = 2;
        } else {
            this.c = 18;
        }
        d(getContext());
        int i6 = this.e;
        return i6 < i3 ? i6 : i4;
    }
}
