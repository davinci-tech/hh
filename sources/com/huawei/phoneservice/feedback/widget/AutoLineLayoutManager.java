package com.huawei.phoneservice.feedback.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class AutoLineLayoutManager extends RecyclerView.LayoutManager {

    /* renamed from: a, reason: collision with root package name */
    private int f8299a;

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
        this.f8299a = (View.MeasureSpec.getSize(i) - getPaddingEnd()) - getPaddingStart();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        detachAndScrapAttachedViews(recycler);
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < getItemCount(); i6++) {
            View viewForPosition = recycler.getViewForPosition(i6);
            measureChildWithMargins(viewForPosition, 0, 0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            addView(viewForPosition);
            if (i4 + decoratedMeasuredWidth > this.f8299a) {
                i5 += decoratedMeasuredHeight;
                i4 = 0;
            }
            measureChildWithMargins(viewForPosition, 0, 0);
            if (d()) {
                int i7 = this.f8299a;
                int i8 = i7 - i4;
                i3 = i7 - (i4 + decoratedMeasuredWidth);
                i = decoratedMeasuredHeight + i5;
                i2 = i8;
            } else {
                i = decoratedMeasuredHeight + i5;
                i2 = i4 + decoratedMeasuredWidth;
                i3 = i4;
            }
            layoutDecoratedWithMargins(viewForPosition, i3, i5, i2, i);
            i4 += decoratedMeasuredWidth;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    protected boolean d() {
        return getLayoutDirection() == 1;
    }

    public AutoLineLayoutManager() {
        setAutoMeasureEnabled(true);
    }
}
