package com.huawei.uikit.hwcolumnlayout.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;

/* loaded from: classes7.dex */
public class HwColumnLinearLayout extends LinearLayout implements HwColumnLayoutable {

    /* renamed from: a, reason: collision with root package name */
    private int f10618a;
    private HwColumnSystem b;
    private boolean c;
    private int d;
    private int e;
    private float f;

    public HwColumnLinearLayout(Context context) {
        this(context, null);
    }

    private void b(Context context) {
        if (this.c) {
            e(getContext());
        } else {
            c(getContext());
        }
        this.b.c(false);
    }

    private void c(Context context) {
        this.b.e(this.f10618a);
        this.b.e(context);
    }

    private boolean c(int i, int i2, float f) {
        return i > 0 && i2 > 0 && f > 0.0f;
    }

    private void e(Context context) {
        this.b.e(this.f10618a);
        this.b.d(context, this.d, this.e, this.f);
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void configureColumn(int i, int i2, float f) {
        if (!c(i, i2, f)) {
            if (this.c) {
                this.c = false;
                b(getContext());
                ebu_(this);
                return;
            }
            return;
        }
        this.c = true;
        this.d = i;
        this.e = i2;
        this.f = f;
        b(getContext());
        ebu_(this);
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public int getColumnType() {
        int i = this.f10618a;
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
        this.b.c(true);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        e();
        if (this.f10618a == Integer.MIN_VALUE) {
            super.onMeasure(i, i2);
            return;
        }
        b(getContext());
        int i3 = this.f10618a;
        if (i3 == 1 || i3 == 17) {
            b();
        }
        int size = View.MeasureSpec.getSize(i);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(a(size), View.MeasureSpec.getMode(i)), i2);
    }

    @Override // com.huawei.uikit.hwcolumnlayout.widget.HwColumnLayoutable
    public void setColumnType(int i) {
        this.f10618a = i;
        ebu_(this);
    }

    public HwColumnLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HwColumnLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(context);
        this.b = hwColumnSystem;
        hwColumnSystem.c(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100266_res_0x7f06026a});
        this.f10618a = obtainStyledAttributes.getInt(0, Integer.MIN_VALUE);
        obtainStyledAttributes.recycle();
    }

    private void e() {
        int childCount = getChildCount();
        int i = this.f10618a;
        if (i == 1) {
            if (childCount > 1) {
                this.f10618a = 2;
                return;
            } else if (childCount == 1) {
                this.f10618a = 1;
                return;
            } else {
                this.f10618a = Integer.MIN_VALUE;
                return;
            }
        }
        if (i == 17) {
            if (childCount > 1) {
                this.f10618a = 18;
            } else if (childCount == 1) {
                this.f10618a = 17;
            } else {
                this.f10618a = Integer.MIN_VALUE;
            }
        }
    }

    private void b() {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.setMinimumWidth(this.b.j());
        }
    }

    private int a(int i) {
        int b = this.b.b();
        return (b < 0 || b > i) ? i : b;
    }

    private void ebu_(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        if (childCount == 0) {
            viewGroup.requestLayout();
            viewGroup.invalidate();
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                ebu_((ViewGroup) childAt);
            } else if (childAt != null) {
                childAt.requestLayout();
                childAt.invalidate();
            }
        }
    }
}
