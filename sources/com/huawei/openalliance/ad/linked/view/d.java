package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.view.View;
import com.huawei.openalliance.ad.gs;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jh;
import com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout;

/* loaded from: classes9.dex */
public abstract class d extends AutoScaleSizeRelativeLayout {
    protected gs b;
    boolean c;
    boolean d;
    protected jh e;

    protected void a() {
    }

    protected void b() {
    }

    protected void c() {
    }

    protected void d() {
    }

    protected int getAutoPlayAreaPercentageThresshold() {
        return 100;
    }

    protected int getHiddenAreaPercentageThreshhold() {
        return 10;
    }

    public void setLinkedNativeAd(gs gsVar) {
        if (!(gsVar instanceof gs)) {
            gsVar = null;
        }
        this.b = gsVar;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        jh jhVar = this.e;
        if (jhVar != null) {
            jhVar.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        jh jhVar = this.e;
        if (jhVar != null) {
            jhVar.i();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        jh jhVar = this.e;
        if (jhVar != null) {
            jhVar.h();
        }
    }

    void b(int i) {
        ho.b("LinkedMediaView", "visiblePercentage is " + i);
        if (i >= getAutoPlayAreaPercentageThresshold()) {
            this.d = false;
            if (this.c) {
                return;
            }
            this.c = true;
            a();
            return;
        }
        this.c = false;
        int hiddenAreaPercentageThreshhold = getHiddenAreaPercentageThreshhold();
        ho.b("LinkedMediaView", "HiddenAreaPercentageThreshhold is " + hiddenAreaPercentageThreshhold);
        if (i > 100 - hiddenAreaPercentageThreshhold) {
            if (this.d) {
                c();
            }
            this.d = false;
        } else {
            if (this.d) {
                return;
            }
            this.d = true;
            b();
        }
    }

    public d(Context context) {
        super(context);
        this.c = false;
        this.d = false;
        this.e = new jh(this) { // from class: com.huawei.openalliance.ad.linked.view.d.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i) {
                d.this.b(0);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a(int i) {
                d.this.b(i);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                d.this.d();
            }
        };
    }
}
