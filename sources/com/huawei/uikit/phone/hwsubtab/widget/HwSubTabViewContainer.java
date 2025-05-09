package com.huawei.uikit.phone.hwsubtab.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.uikit.hwcolumnsystem.widget.HwColumnSystem;
import com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer;

/* loaded from: classes9.dex */
public class HwSubTabViewContainer extends com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer {

    /* renamed from: a, reason: collision with root package name */
    private HwColumnSystem f10790a;
    private int b;
    private boolean c;
    private int d;
    private int e;
    private int g;
    private float h;
    private Context i;
    private int j;

    public class c extends HwSubTabViewContainer.d {
        public c() {
            super();
        }

        @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer.d
        public void eju_(View view) {
            int startOriginPadding = HwSubTabViewContainer.this.getStartOriginPadding() - HwSubTabViewContainer.this.getSubTabItemMargin();
            view.setPadding(startOriginPadding, 0, startOriginPadding, 0);
        }
    }

    public HwSubTabViewContainer(Context context) {
        this(context, null);
    }

    private void d(Context context) {
        this.i = context;
        HwColumnSystem hwColumnSystem = new HwColumnSystem(this.i);
        this.f10790a = hwColumnSystem;
        this.c = false;
        hwColumnSystem.e(this.b);
        this.f10790a.e(this.i);
        this.g = getResources().getDimensionPixelOffset(R.dimen._2131364464_res_0x7f0a0a70);
        this.j = getResources().getDimensionPixelOffset(R.dimen._2131364465_res_0x7f0a0a71);
        i();
    }

    private void f() {
        this.f10790a.e(-1);
        this.f10790a.e(this.i);
    }

    private void h() {
        this.f10790a.e(-1);
        this.f10790a.d(this.i, this.e, this.d, this.h);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer
    public void a() {
        setChildPaddingClient(new c());
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.c) {
            h();
        } else {
            f();
        }
        i();
    }

    public HwSubTabViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = -1;
        d(context);
    }

    public HwSubTabViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = -1;
        d(context);
    }

    private void i() {
        if (this.f10790a.f() >= 8) {
            setStartOriginPadding(this.j);
            setStartScrollPadding(32);
        } else {
            setStartOriginPadding(this.g);
            setStartScrollPadding(28);
        }
    }
}
