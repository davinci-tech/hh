package com.huawei.uikit.hwcardview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes9.dex */
public class HwCardButtonBarLayout extends LinearLayout {
    private int b;
    private Comparator<d> c;
    private boolean e;

    class d {
        int b;
        int c;

        d(int i, int i2) {
            this.c = i;
            this.b = i2;
        }
    }

    class e implements Comparator<d> {
        e() {
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            return dVar.b - dVar2.b;
        }
    }

    public HwCardButtonBarLayout(Context context) {
        this(context, null);
    }

    private void a(int i, boolean z) {
        for (int i2 = 0; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                LinearLayout.LayoutParams layoutParams = childAt.getLayoutParams() instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) childAt.getLayoutParams() : null;
                if (layoutParams == null) {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                }
                if (z) {
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                } else {
                    layoutParams.width = -2;
                    layoutParams.weight = 0.0f;
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.e && d()) {
            d(i, i2, i3, i4);
        } else {
            super.onLayout(z, i, i2, i3, i4);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        setMeasuredDimension(size, getSuggestedMinimumHeight());
        int childCount = getChildCount();
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt != null && childAt.getVisibility() != 8) {
                i3++;
            }
        }
        int measuredWidth = i3 == 0 ? (getMeasuredWidth() - getPaddingStart()) - getPaddingEnd() : ((getMeasuredWidth() - getPaddingStart()) - getPaddingEnd()) / i3;
        ArrayList arrayList = new ArrayList(childCount);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt2 = getChildAt(i6);
            if (childAt2 != null && childAt2.getVisibility() != 8) {
                childAt2.measure(makeMeasureSpec, i2);
                int measuredWidth2 = childAt2.getMeasuredWidth();
                arrayList.add(new d(i6, measuredWidth2));
                if (measuredWidth2 > measuredWidth) {
                    i5++;
                }
            }
        }
        if (i5 >= i3) {
            a(childCount, true);
            super.onMeasure(i, i2);
        } else if (i5 == 0) {
            a(childCount, false);
            super.onMeasure(i, i2);
        } else {
            this.e = true;
            this.b = getMeasuredHeight();
            a(arrayList, i3, measuredWidth);
            setMeasuredDimension(size, this.b);
        }
    }

    public HwCardButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new e();
        setOrientation(0);
    }

    private void a(List<d> list, int i, int i2) {
        Collections.sort(list, this.c);
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int i3 = 0;
        for (d dVar : list) {
            View childAt = getChildAt(dVar.c);
            if (childAt != null) {
                LinearLayout.LayoutParams layoutParams = childAt.getLayoutParams() instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) childAt.getLayoutParams() : null;
                int i4 = layoutParams != null ? layoutParams.leftMargin + layoutParams.rightMargin : 0;
                int i5 = dVar.b;
                if (i5 <= i2) {
                    i3++;
                    measuredWidth -= i5 + i4;
                } else {
                    int i6 = i - i3;
                    dVar.b = i6 == 0 ? measuredWidth - i4 : (measuredWidth / i6) - i4;
                }
            }
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 0);
        for (d dVar2 : list) {
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(dVar2.b, Integer.MIN_VALUE);
            View childAt2 = getChildAt(dVar2.c);
            if (childAt2 != null) {
                childAt2.measure(makeMeasureSpec2, makeMeasureSpec);
                int measuredHeight = childAt2.getMeasuredHeight();
                int i7 = this.b;
                if (measuredHeight <= i7) {
                    measuredHeight = i7;
                }
                this.b = measuredHeight;
            }
        }
    }

    private void d(int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingStart = i3 - getPaddingStart();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null && childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                    int marginStart = paddingStart - layoutParams2.getMarginStart();
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (layoutParams2.gravity == 16) {
                        paddingTop = getPaddingTop() + (((((i4 - i2) - measuredHeight) - getPaddingTop()) - getPaddingBottom()) / 2);
                    }
                    childAt.layout(marginStart - measuredWidth, paddingTop, marginStart, measuredHeight + paddingTop);
                    paddingStart = marginStart - (measuredWidth + layoutParams2.getMarginEnd());
                }
            }
        }
    }

    private boolean d() {
        return getLayoutDirection() == 1;
    }
}
