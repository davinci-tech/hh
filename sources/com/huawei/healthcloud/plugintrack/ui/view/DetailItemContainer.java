package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class DetailItemContainer extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3779a;
    private int d;
    private int e;

    public DetailItemContainer(Context context) {
        super(context);
        this.d = 2;
        this.e = 0;
        this.f3779a = LanguageUtil.bc(context);
    }

    public DetailItemContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DetailItemContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 2;
        this.e = 0;
        this.f3779a = LanguageUtil.bc(context);
    }

    public void e(int i) {
        this.d = i;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i4 = 0;
        if (childCount > 0) {
            int i5 = this.e;
            if (i5 == 0) {
                i5 = getChildAt(0).getMeasuredWidth() * this.d;
            }
            int i6 = this.d;
            if (i6 < 1) {
                LogUtil.b("Track_DetailItemContainer", "mNumberOfLine is error");
                return;
            }
            int i7 = i6 + 1;
            int i8 = childCount / i7;
            if (childCount % i7 != 0) {
                i8++;
            }
            int i9 = 0;
            int i10 = 0;
            for (int i11 = 0; i11 < i8; i11++) {
                int i12 = 0;
                for (int i13 = 0; i10 < childCount && i13 < this.d + 1; i13++) {
                    i12 = Math.max(i12, getChildAt(i10).getMeasuredHeight());
                    i10++;
                }
                i9 += i12;
            }
            if (this.d == 1) {
                i3 = 0;
                while (i4 < childCount) {
                    i3 += getChildAt(i4).getMeasuredHeight();
                    i4++;
                }
                i4 = i5;
            } else {
                i4 = i5;
                i3 = i9;
            }
        } else {
            i3 = 0;
        }
        setMeasuredDimension(i4, i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int measuredWidth = getMeasuredWidth();
        int i5 = this.d;
        if (i5 < 1) {
            LogUtil.b("Track_DetailItemContainer", "mNumberOfLine is error");
            return;
        }
        boolean z2 = this.f3779a && childCount % (i5 + 1) == 1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            int measuredWidth2 = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i7 >= measuredWidth || this.d == 1) {
                i8 += i9;
                i7 = 0;
                i9 = 0;
            }
            int i10 = measuredWidth2 + i7;
            if (measuredHeight > i9) {
                i9 = measuredHeight;
            }
            if (i6 != childCount - 1 || !z2) {
                childAt.layout(i7, i8, i10, measuredHeight + i8);
            }
            i6++;
            i7 = i10;
        }
        if (z2) {
            View childAt2 = getChildAt(childCount - 1);
            childAt2.layout(measuredWidth - childAt2.getMeasuredWidth(), i8, measuredWidth, childAt2.getMeasuredHeight() + i8);
        }
    }

    public void setTextColor(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof ImageView) {
                ((ImageView) childAt).setBackgroundColor(i);
            } else if (childAt instanceof SportDetailItem) {
                ((SportDetailItem) childAt).setTextColor(i);
            } else {
                LogUtil.b("Track_DetailItemContainer", "unexpected item type");
            }
        }
    }
}
