package com.huawei.ui.main.stories.history.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class MonthTitleContainer extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private int f10318a;
    private boolean c;
    private int d;
    private boolean e;

    public MonthTitleContainer(Context context) {
        super(context);
        this.f10318a = 4;
        this.c = false;
        this.d = 0;
        this.e = LanguageUtil.bc(context);
    }

    public MonthTitleContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10318a = 4;
        this.c = false;
        this.d = 0;
        this.e = LanguageUtil.bc(context);
    }

    public MonthTitleContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10318a = 4;
        this.c = false;
        this.d = 0;
        this.e = LanguageUtil.bc(context);
    }

    public void b(int i) {
        this.f10318a = i;
    }

    public void setExpandStatus(boolean z) {
        this.c = z;
        requestLayout();
        invalidate();
    }

    public boolean getExpandStatus() {
        return this.c;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        measureChildren(i, i2);
        int childCount = getChildCount();
        if (childCount > 0) {
            i3 = this.d;
            if (i3 == 0) {
                i3 = getChildAt(0).getMeasuredWidth() * this.f10318a;
            }
        } else {
            i3 = 0;
        }
        int i4 = this.f10318a;
        int i5 = (i4 - 1) + i4;
        int i6 = childCount / i5;
        if (childCount % i5 != 0) {
            i6++;
        }
        if (i6 > 2 && !this.c) {
            i6 = 2;
        }
        setMeasuredDimension(i3, getChildAt(0) != null ? getChildAt(0).getMeasuredHeight() * i6 : 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.e) {
            c();
        } else {
            e();
        }
    }

    private void c() {
        int childCount = getChildCount();
        int measuredWidth = getMeasuredWidth();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            if (i4 > 13 && !this.c) {
                return;
            }
            View childAt = getChildAt(i4);
            int measuredWidth2 = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i2 >= measuredWidth) {
                i3 += i;
                i = 0;
                i2 = 0;
            }
            int i5 = measuredWidth - i2;
            i2 += measuredWidth2;
            if (measuredHeight > i) {
                i = measuredHeight;
            }
            childAt.layout(i5 - measuredWidth2, i3, i5, measuredHeight + i3);
        }
    }

    private void e() {
        int childCount = getChildCount();
        int measuredWidth = getMeasuredWidth();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < childCount) {
            if (i > 13 && !this.c) {
                return;
            }
            View childAt = getChildAt(i);
            int measuredWidth2 = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i3 >= measuredWidth) {
                i4 += i2;
                i2 = 0;
                i3 = 0;
            }
            int i5 = measuredWidth2 + i3;
            if (measuredHeight > i2) {
                i2 = measuredHeight;
            }
            childAt.layout(i3, i4, i5, measuredHeight + i4);
            i++;
            i3 = i5;
        }
    }
}
