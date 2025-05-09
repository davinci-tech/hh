package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.togglebutton.HealthToggleButton;
import defpackage.koq;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class FilterFlowLayout extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private int f3417a;
    private boolean b;
    private CompoundButton.OnCheckedChangeListener c;
    private OnCheckChangeListener d;
    private int e;
    private int f;
    private int h;

    public interface OnCheckChangeListener {
        void onCancelCheck(int i);

        void onChecked(int i);
    }

    public FilterFlowLayout(Context context) {
        this(context, null);
    }

    public FilterFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FilterFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.suggestion.ui.view.FilterFlowLayout.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int i2;
                if (FilterFlowLayout.this.d != null && (compoundButton.getTag() instanceof String) && Pattern.compile("^[-\\+]?[\\d]*$").matcher((String) compoundButton.getTag()).matches()) {
                    try {
                        i2 = Integer.parseInt((String) compoundButton.getTag());
                    } catch (NumberFormatException unused) {
                        LogUtil.b("FilterFlowLayout", "onCheckedChanged NumberFormatException.");
                        i2 = 0;
                    }
                    if (z) {
                        FilterFlowLayout.this.d.onChecked(i2);
                    } else {
                        FilterFlowLayout.this.d.onCancelCheck(i2);
                    }
                } else {
                    LogUtil.c("FilterFlowLayout", "tag is error");
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        };
        this.b = false;
        aMm_(context, attributeSet);
    }

    private void aMm_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100684_res_0x7f06040c, R.attr._2131100790_res_0x7f060476}, 0, 0);
        this.f3417a = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.e = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f = R.layout.item_popwindow_checkbox;
        obtainStyledAttributes.recycle();
    }

    protected int getLineSpacing() {
        return this.f3417a;
    }

    protected void setLineSpacing(int i) {
        this.f3417a = i;
    }

    protected int getItemSpacing() {
        return this.e;
    }

    protected void setItemSpacing(int i) {
        this.e = i;
    }

    public boolean a() {
        return this.b;
    }

    public void setSingleLine(boolean z) {
        this.b = z;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i6 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i7 = paddingTop;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                int i10 = i7;
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i4 = marginLayoutParams.leftMargin;
                    i3 = marginLayoutParams.rightMargin;
                } else {
                    i3 = 0;
                    i4 = 0;
                }
                int i11 = paddingLeft;
                if (paddingLeft + i4 + childAt.getMeasuredWidth() <= i6 - paddingRight || a()) {
                    i5 = i11;
                } else {
                    i5 = getPaddingLeft();
                    i10 = paddingTop + this.f3417a;
                }
                int measuredWidth = i5 + i4 + childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (measuredWidth > i8) {
                    i8 = measuredWidth;
                }
                paddingLeft = i5 + i4 + i3 + childAt.getMeasuredWidth() + this.e;
                if (i9 == getChildCount() - 1) {
                    i8 += i3;
                }
                paddingTop = i10 + measuredHeight;
                i7 = i10;
            }
        }
        setMeasuredDimension(a(size, mode, i8 + getPaddingRight()), a(size2, mode2, paddingTop + getPaddingBottom()));
    }

    private static int a(int i, int i2, int i3) {
        if (i2 != Integer.MIN_VALUE) {
            return i2 != 1073741824 ? i3 : i;
        }
        return Math.min(i3, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (getChildCount() == 0) {
            this.h = 0;
            return;
        }
        this.h = 1;
        boolean z2 = ViewCompat.getLayoutDirection(this) == 1;
        int paddingRight = z2 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z2 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i7 = (i3 - i) - paddingLeft;
        int i8 = paddingRight;
        int i9 = paddingTop;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(R.id.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i6 = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
                    i5 = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
                } else {
                    i5 = 0;
                    i6 = 0;
                }
                int measuredWidth = childAt.getMeasuredWidth();
                if (!this.b && i8 + i6 + measuredWidth > i7) {
                    paddingTop = this.f3417a + i9;
                    this.h++;
                    i8 = paddingRight;
                }
                childAt.setTag(R.id.row_index_key, Integer.valueOf(this.h - 1));
                int i11 = i8 + i6;
                int measuredWidth2 = childAt.getMeasuredWidth() + i11;
                int measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                if (z2) {
                    childAt.layout(i7 - measuredWidth2, paddingTop, (i7 - i8) - i6, measuredHeight);
                } else {
                    childAt.layout(i11, paddingTop, measuredWidth2, measuredHeight);
                }
                i8 += i6 + i5 + childAt.getMeasuredWidth() + this.e;
                i9 = measuredHeight;
            }
        }
    }

    protected int getRowCount() {
        return this.h;
    }

    public void d(Context context, List<Attribute> list) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        removeAllViews();
        if (koq.b(list)) {
            LogUtil.h("FilterFlowLayout", "showData() datas is null");
            return;
        }
        if (koq.c(list)) {
            for (Attribute attribute : list) {
                if (attribute != null) {
                    HealthToggleButton a2 = a(context);
                    a2.setText(attribute.getName());
                    a2.setTag(attribute.getId());
                    a2.setOnCheckedChangeListener(this.c);
                    addView(a2);
                }
            }
        }
    }

    public void d(Context context, List<Attribute> list, List<Integer> list2) {
        if (koq.b(list)) {
            LogUtil.h("FilterFlowLayout", "showData() datas is null");
            return;
        }
        if (context == null) {
            context = BaseApplication.getContext();
        }
        removeAllViews();
        for (Attribute attribute : list) {
            if (attribute != null) {
                HealthToggleButton a2 = a(context);
                a2.setText(attribute.getName());
                a2.setTag(attribute.getId());
                if (!koq.b(list2)) {
                    d(attribute, list2, a2);
                }
                a2.setOnCheckedChangeListener(this.c);
                addView(a2);
            }
        }
    }

    public void setData(Context context, List<Attribute> list, List<Long> list2) {
        if (koq.b(list)) {
            LogUtil.h("FilterFlowLayout", "showData() datas is null");
            return;
        }
        if (context == null) {
            context = BaseApplication.getContext();
        }
        removeAllViews();
        for (Attribute attribute : list) {
            if (attribute != null) {
                HealthToggleButton a2 = a(context);
                a2.setText(attribute.getName());
                a2.setTag(attribute.getId());
                if (koq.c(list2)) {
                    Iterator<Long> it = list2.iterator();
                    while (it.hasNext()) {
                        if (TextUtils.equals(attribute.getId(), String.valueOf(it.next()))) {
                            a2.setChecked(true);
                        }
                    }
                }
                a2.setOnCheckedChangeListener(this.c);
                addView(a2);
            }
        }
    }

    private void d(Attribute attribute, List<Integer> list, HealthToggleButton healthToggleButton) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(attribute.getId(), String.valueOf(it.next()))) {
                healthToggleButton.setChecked(true);
            }
        }
    }

    public void setToggleButtonSourceId(int i) {
        this.f = i;
    }

    private HealthToggleButton a(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        if (from.inflate(this.f, (ViewGroup) this, false) instanceof HealthToggleButton) {
            return (HealthToggleButton) from.inflate(this.f, (ViewGroup) this, false);
        }
        return new HealthToggleButton(context);
    }

    public void setOnCheckedChangeListener(OnCheckChangeListener onCheckChangeListener) {
        this.d = onCheckChangeListener;
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
