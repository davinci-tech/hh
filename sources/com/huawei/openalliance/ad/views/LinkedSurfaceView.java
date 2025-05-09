package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class LinkedSurfaceView extends RelativeLayout implements com.huawei.openalliance.ad.views.interfaces.u {

    /* renamed from: a, reason: collision with root package name */
    private b f7814a;
    private int b;
    private int c;

    public void setVideoScaleMode(int i) {
        this.f7814a.setVideoScaleMode(i);
    }

    public void setVideoRatio(Float f) {
        this.f7814a.setVideoRatio(f);
    }

    public void setScreenOnWhilePlaying(boolean z) {
        this.f7814a.setScreenOnWhilePlaying(z);
    }

    public void setNeedPauseOnSurfaceDestory(boolean z) {
        this.f7814a.setNeedPauseOnSurfaceDestory(z);
    }

    public void setAutoScaleResizeLayoutOnVideoSizeChange(boolean z) {
        this.f7814a.setAutoScaleResizeLayoutOnVideoSizeChange(z);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        super.onLayout(z, i, i2, i3, i4);
        if (this.c == 0 && this.b == 0) {
            this.b = i3 - i;
            this.c = i4 - i2;
        }
        if (this.f7814a.getVideoHeight() == 0 || (i5 = this.c) == 0) {
            return;
        }
        this.f7814a.a(this.b, i5);
        int i6 = this.b;
        int i7 = this.c;
        this.f7814a.a((r2.getVideoWidth() * 1.0f) / this.f7814a.getVideoHeight(), (i6 * 1.0f) / i7, i6, i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("LinkedSurfaceView", "onDetachedFromWindow");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.b("LinkedSurfaceView", "onAttachedToWindow");
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void c() {
        this.f7814a.c();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void b() {
        this.f7814a.b();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void a(g gVar) {
        this.f7814a.a(gVar);
    }

    public void a(float f, float f2, int i, int i2) {
        this.f7814a.a(f, f2, i, i2);
    }

    public void a(float f, float f2, float f3, int i, int i2) {
        super.setScaleY(f);
        super.setTranslationY(f2);
        super.setScaleX(f3);
        this.c = i2;
        this.b = i;
        if (this.f7814a.getVideoHeight() == 0 || i2 == 0) {
            return;
        }
        this.f7814a.a(i, i2);
        this.f7814a.a((r2.getVideoWidth() * 1.0f) / this.f7814a.getVideoHeight(), (i * 1.0f) / i2, i, i2);
    }

    public void a() {
        this.f7814a.destroyView();
    }

    private void a(Context context) {
        this.f7814a = new ai(context);
        addView(this.f7814a, new RelativeLayout.LayoutParams(-1, -1));
    }

    public LinkedSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public LinkedSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LinkedSurfaceView(Context context) {
        super(context);
        a(context);
    }
}
