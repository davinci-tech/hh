package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.openalliance.ad.utils.dd;

/* loaded from: classes5.dex */
public class j extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    protected View f8109a;
    protected TextView b;
    protected TextView c;
    protected boolean d;
    protected int e;

    public void setShowLogo(boolean z) {
        this.d = z;
        a();
    }

    public void setOrientation(int i) {
        this.e = i;
    }

    public void a(String str, String str2) {
        if (this.b != null && !TextUtils.isEmpty(str)) {
            this.b.setText(str);
        }
        if (this.c == null || TextUtils.isEmpty(str2)) {
            return;
        }
        this.c.setText(str2);
    }

    protected void a() {
        if (this.d || this.e != 1) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin += dd.f(getContext());
            setLayoutParams(layoutParams2);
        }
    }

    public j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 1;
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 1;
    }

    public j(Context context) {
        super(context);
        this.e = 1;
    }
}
