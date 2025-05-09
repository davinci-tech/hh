package com.huawei.uikit.hwcolumnlayout.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;

/* loaded from: classes7.dex */
public class HwColumnRelativeLayout extends RelativeLayout implements HwColumnLayoutable {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10619a;
    private int b;
    private int c;
    private HwColumnSystem d;
    private int e;
    private int f;
    private int h;
    private float i;

    public HwColumnRelativeLayout(Context context) {
        this(context, null);
    }

    private boolean a(int i, int i2, float f) {
        return i > 0 && i2 > 0 && f > 0.0f;
    }

    private void b(Context context) {
        this.d.e(this.e);
        this.d.e(context);
        this.c = this.d.h();
        this.b = this.d.b();
    }

    private void e(Context context) {
        this.d.e(this.e);
        this.d.d(context, this.f, this.h, this.i);
        this.c = this.d.h();
        this.b = this.d.b();
    }

    private void ebv_(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount == 0) {
            viewGroup.requestLayout();
            viewGroup.invalidate();
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ebv_((ViewGroup) childAt);
            } else if (childAt != null) {
                childAt.requestLayout();
                childAt.invalidate();
            }
        }
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void configureColumn(int i, int i2, float f) {
        if (!a(i, i2, f)) {
            if (this.f10619a) {
                this.f10619a = false;
                c(getContext());
                ebv_(this);
                return;
            }
            return;
        }
        this.f10619a = true;
        this.f = i;
        this.h = i2;
        this.i = f;
        c(getContext());
        ebv_(this);
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public int getColumnType() {
        int i = this.e;
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
        this.d.c(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
    
        if (r2 != 4) goto L23;
     */
    @Override // android.widget.RelativeLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            int r1 = android.view.View.MeasureSpec.getSize(r5)
            int r2 = r4.e
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
            r4.c(r2)
            int r2 = r4.c
            if (r2 >= r1) goto L3d
            if (r2 <= 0) goto L3d
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            goto L3d
        L33:
            int r1 = r4.c(r5, r6, r1)
            if (r1 <= 0) goto L3d
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
        L3d:
            super.onMeasure(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwcolumnlayout.widget.HwColumnRelativeLayout.onMeasure(int, int):void");
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void setColumnType(int i) {
        this.e = i;
        ebv_(this);
    }

    public HwColumnRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HwColumnRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.b = Integer.MIN_VALUE;
        this.f10619a = false;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context);
        this.d = hwColumnSystem;
        hwColumnSystem.c(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100266_res_0x7f06026a});
        this.e = obtainStyledAttributes.getInteger(0, Integer.MIN_VALUE);
        obtainStyledAttributes.recycle();
    }

    private void c(Context context) {
        if (!this.f10619a) {
            b(getContext());
        } else {
            e(getContext());
        }
        this.d.c(false);
    }

    private int c(int i, int i2, int i3) {
        int childCount = getChildCount();
        int i4 = 0;
        if (childCount == 1) {
            c(getContext());
            View childAt = getChildAt(0);
            childAt.setMinimumWidth(this.c);
            measureChild(childAt, i, i2);
            int measuredWidth = childAt.getMeasuredWidth();
            int i5 = this.c;
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
        if (this.e == 1) {
            this.e = 2;
        } else {
            this.e = 18;
        }
        c(getContext());
        int i6 = this.c;
        return i6 < i3 ? i6 : i4;
    }
}
