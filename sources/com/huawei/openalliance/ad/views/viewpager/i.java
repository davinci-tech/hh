package com.huawei.openalliance.ad.views.viewpager;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public class i extends ViewGroup.LayoutParams {

    /* renamed from: a, reason: collision with root package name */
    static final int[] f8173a = {R.attr.layout_gravity};
    public boolean b;
    public int c;
    float d;
    boolean e;
    int f;
    int g;

    public i(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f8173a);
        this.c = obtainStyledAttributes.getInteger(0, 48);
        obtainStyledAttributes.recycle();
    }

    public i() {
        super(-1, -1);
        this.d = 0.0f;
    }
}
