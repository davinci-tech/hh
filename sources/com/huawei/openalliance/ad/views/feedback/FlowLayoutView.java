package com.huawei.openalliance.ad.views.feedback;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.openalliance.ad.utils.ao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class FlowLayoutView extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private e f8068a;
    private int b;
    private int c;
    private int d;
    private int e;
    private final List<e> f;

    public void setDefaultDisplayMode(int i) {
        this.d = i;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        b();
        a();
        int childCount = getChildCount();
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(size, mode == 1073741824 ? Integer.MIN_VALUE : mode), View.MeasureSpec.makeMeasureSpec((size2 - paddingBottom) - paddingTop, mode2 != 1073741824 ? mode2 : Integer.MIN_VALUE));
            int measuredWidth = childAt.getMeasuredWidth();
            if (this.e + measuredWidth > size) {
                c();
            }
            int i5 = this.e + this.b + measuredWidth;
            this.e = i5;
            this.f8068a.a(i5);
            this.f8068a.a(childAt);
        }
        e eVar = this.f8068a;
        if (eVar != null && !this.f.contains(eVar)) {
            c();
        }
        Iterator<e> it = this.f.iterator();
        while (it.hasNext()) {
            i3 += it.next().a();
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), resolveSize(i3 + (this.c * (this.f.size() - 1)) + getPaddingBottom() + getPaddingTop(), i2));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int measuredWidth = getMeasuredWidth();
        int i5 = paddingTop;
        for (e eVar : this.f) {
            eVar.a(this.d, getLeft(), paddingLeft, i5, (measuredWidth - paddingLeft) - paddingTop, this.b);
            i5 = this.c + eVar.a() + i5;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    private void c() {
        int i = this.e;
        if (i > 0) {
            this.f8068a.a(i - this.b);
        }
        e eVar = this.f8068a;
        if (eVar != null) {
            this.f.add(eVar);
        }
        this.e = 0;
        this.f8068a = new e();
    }

    private void b() {
        this.f.clear();
        this.f8068a = new e();
        this.e = 0;
    }

    private void a() {
        if (this.f8068a == null) {
            this.f8068a = new e();
        }
    }

    public FlowLayoutView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 1;
        this.b = ao.a(context, 8.0f);
        this.c = ao.a(context, 8.0f);
        this.f = new ArrayList();
        this.e = 0;
    }

    public FlowLayoutView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayoutView(Context context) {
        this(context, null);
    }
}
