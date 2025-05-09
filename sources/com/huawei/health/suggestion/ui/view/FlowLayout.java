package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FlowLayout extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private OnTextClickListener f3419a;
    private List<Integer> b;
    private int c;

    public interface OnTextClickListener {
        void onClick(String str);
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100684_res_0x7f06040c, R.attr._2131100790_res_0x7f060476, R.attr._2131100834_res_0x7f0604a2});
        this.c = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        View view;
        int i3;
        int i4;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int i5 = paddingLeft + paddingRight;
        int i6 = paddingTop;
        int i7 = i5;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    view = childAt;
                    measureChildWithMargins(childAt, i, 0, i2, i6);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int i10 = marginLayoutParams.leftMargin;
                    int i11 = marginLayoutParams.rightMargin;
                    i4 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    i3 = i10 + i11;
                } else {
                    view = childAt;
                    measureChild(view, i, i2);
                    i3 = 0;
                    i4 = 0;
                }
                int measuredWidth = i3 + view.getMeasuredWidth();
                int measuredHeight = i4 + view.getMeasuredHeight();
                if (i7 + measuredWidth > size) {
                    i6 += i8 + this.c;
                    i7 = i5;
                    i8 = 0;
                }
                if (measuredHeight > i8) {
                    i8 = measuredHeight;
                }
                i7 += measuredWidth;
            }
        }
        if (mode != 1073741824) {
            size2 = i6 + i8 + paddingBottom;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        View view;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int i15 = paddingRight + paddingLeft;
        this.b.clear();
        int i16 = paddingLeft;
        int i17 = i15;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (i18 < getChildCount()) {
            View childAt = getChildAt(i18);
            if (childAt.getVisibility() == 8) {
                i5 = i15;
                i6 = i18;
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                ViewGroup.MarginLayoutParams aMn_ = aMn_(childAt.getLayoutParams());
                int i21 = aMn_.leftMargin;
                int i22 = aMn_.rightMargin;
                int i23 = aMn_.topMargin;
                i5 = i15;
                int i24 = aMn_.bottomMargin;
                i6 = i18;
                int i25 = aMn_.leftMargin;
                int i26 = aMn_.topMargin;
                int i27 = aMn_.leftMargin;
                int i28 = aMn_.topMargin;
                int i29 = i21 + i22 + measuredWidth;
                int i30 = i23 + i24 + measuredHeight;
                if (i17 + i29 > i3 - i) {
                    this.b.add(Integer.valueOf(i19));
                    paddingTop += i20 + this.c;
                    i7 = aMn_.leftMargin + paddingLeft;
                    i9 = aMn_.topMargin + paddingTop;
                    i13 = aMn_.leftMargin + paddingLeft + measuredWidth;
                    i11 = i5;
                    i14 = paddingLeft;
                    i8 = aMn_.topMargin + paddingTop + measuredHeight;
                    view = childAt;
                    i12 = 0;
                    i10 = 0;
                } else {
                    i7 = i25 + i16;
                    int i31 = i28 + paddingTop + measuredHeight;
                    int i32 = i27 + i16 + measuredWidth;
                    view = childAt;
                    int i33 = i16;
                    i8 = i31;
                    i9 = i26 + paddingTop;
                    i10 = i20;
                    i11 = i17;
                    i12 = i19;
                    i13 = i32;
                    i14 = i33;
                }
                view.layout(i7, i9, i13, i8);
                i19 = i12 + 1;
                if (i30 > i10) {
                    i10 = i30;
                }
                i17 = i11 + i29;
                i16 = i14 + i29;
                i20 = i10;
            }
            i18 = i6 + 1;
            i15 = i5;
        }
        this.b.add(Integer.valueOf(i19));
    }

    private ViewGroup.MarginLayoutParams aMn_(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return (ViewGroup.MarginLayoutParams) layoutParams;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        marginLayoutParams.setMargins(0, 0, 0, 0);
        return marginLayoutParams;
    }

    public void c(Context context, List<String> list) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        removeAllViews();
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            HealthTextView c = c(context);
            final String str = list.get(i);
            c.setText(str);
            c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.view.FlowLayout.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (FlowLayout.this.f3419a != null) {
                        FlowLayout.this.f3419a.onClick(str);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            addView(c);
        }
    }

    private HealthTextView c(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        if (from.inflate(R.layout.sug_flow_item_search, (ViewGroup) this, false) instanceof HealthTextView) {
            return (HealthTextView) from.inflate(R.layout.sug_flow_item_search, (ViewGroup) this, false);
        }
        return new HealthTextView(context);
    }

    public void setTextOnClickListener(OnTextClickListener onTextClickListener) {
        this.f3419a = onTextClickListener;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(super.generateDefaultLayoutParams());
    }
}
