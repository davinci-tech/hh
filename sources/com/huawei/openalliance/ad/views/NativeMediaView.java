package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.jh;
import com.huawei.openalliance.ad.monitor.listener.ViewShowAreaListener;

/* loaded from: classes5.dex */
public abstract class NativeMediaView extends AutoScaleSizeRelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    boolean f7815a;
    boolean b;
    protected com.huawei.openalliance.ad.inter.data.e c;
    protected int d;
    protected int e;
    protected jh f;
    private ViewShowAreaListener g;

    protected void a() {
    }

    protected void b() {
    }

    protected void c() {
    }

    protected void d() {
    }

    protected void e() {
    }

    protected int getAutoPlayAreaPercentageThresshold() {
        return 100;
    }

    protected int getHiddenAreaPercentageThreshhold() {
        return 10;
    }

    public int getPlayedProgress() {
        return -1;
    }

    public int getPlayedTime() {
        return -1;
    }

    public void setViewShowAreaListener(ViewShowAreaListener viewShowAreaListener) {
        this.g = viewShowAreaListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNativeAd(INativeAd iNativeAd) {
        this.c = iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e ? (com.huawei.openalliance.ad.inter.data.e) iNativeAd : null;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        jh jhVar = this.f;
        if (jhVar != null) {
            jhVar.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        jh jhVar = this.f;
        if (jhVar != null) {
            jhVar.i();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        jh jhVar = this.f;
        if (jhVar != null) {
            jhVar.h();
        }
    }

    void a(int i) {
        ViewShowAreaListener viewShowAreaListener = this.g;
        if (viewShowAreaListener != null) {
            viewShowAreaListener.onUpdateShowArea(i);
        }
        if (i >= getAutoPlayAreaPercentageThresshold()) {
            this.b = false;
            if (this.f7815a) {
                return;
            }
            this.f7815a = true;
            c();
            return;
        }
        this.f7815a = false;
        if (i > 100 - getHiddenAreaPercentageThreshhold()) {
            if (this.b) {
                e();
            }
            this.b = false;
        } else {
            if (this.b) {
                return;
            }
            this.b = true;
            d();
        }
    }

    public NativeMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7815a = false;
        this.b = false;
        this.d = -1;
        this.e = 0;
        this.f = new jh(this) { // from class: com.huawei.openalliance.ad.views.NativeMediaView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i3) {
                NativeMediaView.this.a(0);
                NativeMediaView.this.a();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a(int i3) {
                NativeMediaView.this.a(i3);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                NativeMediaView.this.b();
            }
        };
    }

    public NativeMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7815a = false;
        this.b = false;
        this.d = -1;
        this.e = 0;
        this.f = new jh(this) { // from class: com.huawei.openalliance.ad.views.NativeMediaView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i3) {
                NativeMediaView.this.a(0);
                NativeMediaView.this.a();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a(int i3) {
                NativeMediaView.this.a(i3);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                NativeMediaView.this.b();
            }
        };
    }

    public NativeMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7815a = false;
        this.b = false;
        this.d = -1;
        this.e = 0;
        this.f = new jh(this) { // from class: com.huawei.openalliance.ad.views.NativeMediaView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i3) {
                NativeMediaView.this.a(0);
                NativeMediaView.this.a();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a(int i3) {
                NativeMediaView.this.a(i3);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                NativeMediaView.this.b();
            }
        };
    }

    public NativeMediaView(Context context) {
        super(context);
        this.f7815a = false;
        this.b = false;
        this.d = -1;
        this.e = 0;
        this.f = new jh(this) { // from class: com.huawei.openalliance.ad.views.NativeMediaView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i3) {
                NativeMediaView.this.a(0);
                NativeMediaView.this.a();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a(int i3) {
                NativeMediaView.this.a(i3);
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                NativeMediaView.this.b();
            }
        };
    }
}
