package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;

/* loaded from: classes4.dex */
public class TrackShareViewGroup extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f3809a;
    private int c;
    private int d;
    private Context e;

    public TrackShareViewGroup(Context context) {
        super(context);
        this.f3809a = 0;
        this.c = 0;
        this.d = 0;
        this.e = null;
        e(context);
    }

    public TrackShareViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3809a = 0;
        this.c = 0;
        this.d = 0;
        this.e = null;
        e(context);
    }

    private void e(Context context) {
        if (context == null) {
            return;
        }
        this.e = context;
        setOrientation(1);
        this.f3809a = nsn.c(this.e, 16.0f);
        this.d = nsn.c(this.e, 24.0f);
        this.c = nsn.c(this.e, 8.0f);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i6 > 0 && i6 < childCount - 1) {
                i5 += this.c;
                int i7 = this.d;
                childAt.layout(i7, i5, measuredWidth + i7, measuredHeight + i5);
            } else {
                childAt.layout(0, i5, measuredWidth, measuredHeight + i5);
            }
            i5 += measuredHeight;
        }
        LogUtil.a("Track_TrackShareViewGroup", "height = ", Integer.valueOf(i5));
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (i3 == 0) {
                measureChild(childAt, i, makeMeasureSpec);
            } else if (i3 == childCount - 1) {
                measureChild(childAt, View.MeasureSpec.makeMeasureSpec(size, mode), makeMeasureSpec);
            } else {
                int i4 = this.d;
                measureChild(childAt, View.MeasureSpec.makeMeasureSpec((size - i4) - i4, mode), makeMeasureSpec);
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            i5 += getChildAt(i7).getMeasuredHeight();
            if (getChildAt(i7).getMeasuredWidth() > i6) {
                i6 = getChildAt(i7).getMeasuredWidth();
            }
        }
        int i8 = childCount - 2;
        if (i8 >= 1) {
            i5 += (i8 * this.f3809a) / 2;
        }
        setMeasuredDimension(size, i5);
    }

    public void setAllChildViewBackgroudSource(int i, int i2) {
        int i3;
        int childCount = getChildCount();
        int i4 = i2 <= 1 ? 0 : i2;
        while (true) {
            i3 = childCount - 1;
            if (i4 >= i3) {
                break;
            }
            View childAt = getChildAt(i4);
            if (childAt != null) {
                childAt.setBackgroundResource(i);
            }
            i4++;
        }
        if (i2 != 1 || childCount < 1) {
            return;
        }
        getChildAt(i3).setBackground(this.e.getResources().getDrawable(R.drawable._2131431928_res_0x7f0b11f8));
    }
}
