package com.huawei.indoorequip.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes5.dex */
public class SportEquipItemDrawer extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private float f6439a;
    private int b;
    private float c;
    private boolean d;
    private float e;
    private int f;

    public SportEquipItemDrawer(Context context) {
        this(context, null);
    }

    public SportEquipItemDrawer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportEquipItemDrawer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 2;
        this.f = 0;
        bVN_(context, attributeSet);
        this.d = LanguageUtil.bc(context);
    }

    private void bVN_(Context context, AttributeSet attributeSet) {
        if (attributeSet == null || context == null) {
            return;
        }
        if (!(context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager)) {
            LogUtils.e("Track_IDEQ_SportEquipItemDrawer", "object is invalid type");
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099925_res_0x7f060115, R.attr._2131101127_res_0x7f0605c7, R.attr._2131101128_res_0x7f0605c8, R.attr._2131101339_res_0x7f06069b, R.attr._2131101345_res_0x7f0606a1});
        try {
            int h = nsn.h(context);
            this.b = obtainStyledAttributes.getInteger(0, 2);
            this.f6439a = obtainStyledAttributes.getFloat(1, context.getResources().getDimensionPixelSize(R.dimen._2131365224_res_0x7f0a0d68));
            float f = obtainStyledAttributes.getFloat(2, h / 2.0f);
            this.e = f;
            this.c = f;
            if (nsn.ag(context)) {
                this.e = (h - (nsn.c(context, 90.0f) * 2)) / 2.0f;
                this.f6439a = nsn.c(context, 80.0f);
            }
        } catch (Resources.NotFoundException e) {
            LogUtil.b("Track_IDEQ_SportEquipItemDrawer", "handleCustomAttrs notFoundException", LogAnonymous.b((Throwable) e));
        }
        obtainStyledAttributes.recycle();
    }

    public void e(int i) {
        this.b = i;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i4 = 0;
        if (childCount > 0) {
            int i5 = this.f;
            if (i5 == 0) {
                i5 = 0;
                for (int i6 = 0; i6 < this.b; i6++) {
                    i5 += getChildAt(i6).getMeasuredWidth();
                }
            }
            int i7 = this.b;
            if (i7 < 1) {
                LogUtils.e("Track_IDEQ_SportEquipItemDrawer", "mNumberOfColumns is error");
                return;
            }
            int i8 = childCount / i7;
            if (childCount % i7 != 0) {
                i8++;
            }
            int measuredHeight = getChildAt(0).getMeasuredHeight() * i8;
            if (this.b == 1) {
                for (int i9 = 0; i9 < childCount; i9++) {
                    i4 += getChildAt(i9).getMeasuredHeight();
                }
            } else {
                i4 = measuredHeight;
            }
            i3 = i4;
            i4 = i5;
        } else {
            i3 = 0;
        }
        setMeasuredDimension(i4, i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int measuredWidth = getMeasuredWidth();
        if (this.b < 1) {
            LogUtils.e("Track_IDEQ_SportEquipItemDrawer", "mNumberOfColumns is error");
            return;
        }
        LogUtils.d("Track_IDEQ_SportEquipItemDrawer", "layout " + childCount);
        boolean z2 = this.d && childCount % (this.b + 1) == 1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            int measuredWidth2 = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i6 >= measuredWidth || this.b == 1) {
                i7 += i8;
                i6 = 0;
                i8 = 0;
            }
            int i9 = measuredWidth2 + i6;
            if (measuredHeight > i8) {
                i8 = measuredHeight;
            }
            if (i5 != childCount - 1 || !z2) {
                childAt.layout(i6, i7, i9, measuredHeight + i7);
            }
            i5++;
            i6 = i9;
        }
        if (z2) {
            View childAt2 = getChildAt(childCount - 1);
            childAt2.layout(measuredWidth - childAt2.getMeasuredWidth(), i7, measuredWidth, childAt2.getMeasuredHeight() + i7);
        }
    }

    public float getItemWidth() {
        return this.e;
    }

    public float getRopeSkipItemWidth() {
        return this.c;
    }

    public float getItemHeight() {
        return this.f6439a;
    }
}
