package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import com.huawei.health.R;

/* loaded from: classes5.dex */
public class ah extends AutoScaleSizeRelativeLayout implements com.huawei.openalliance.ad.views.interfaces.t {

    /* renamed from: a, reason: collision with root package name */
    private LinkedSurfaceView f8034a;
    private PPSWLSView b;
    private PPSSplashAdSourceView c;
    private ViewStub d;
    private PPSSplashProView e;
    private PPSSplashSwipeView f;
    private PPSSplashTwistView g;
    private PPSSplashSwipeClickView h;
    private PPSSplashTwistClickView i;

    public ViewStub getViewStub() {
        return this.d;
    }

    public PPSSplashTwistView getTwistView() {
        return this.g;
    }

    public PPSSplashTwistClickView getTwistClickView() {
        return this.i;
    }

    public PPSSplashSwipeView getSwipeView() {
        return this.f;
    }

    public PPSSplashSwipeClickView getSwipeClickView() {
        return this.h;
    }

    public int getStatusBarHeight() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        return iArr[1];
    }

    public PPSSplashProView getProView() {
        return this.e;
    }

    public PPSWLSView getPpswlsView() {
        return this.b;
    }

    public PPSSplashAdSourceView getPpsSplashAdSourceView() {
        return this.c;
    }

    public LinkedSurfaceView getLinkedVideoView() {
        return this.f8034a;
    }

    public void b() {
        removeAllViews();
        this.f8034a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }

    public void a() {
        if (this.f8034a.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.f8034a.getParent()).removeView(this.f8034a);
        }
    }

    private void a(Context context) {
        ((RelativeLayout) LayoutInflater.from(context).inflate(R.layout.hiad_splash_linked_video_view, this)).setBackgroundColor(0);
        this.f8034a = (LinkedSurfaceView) findViewById(R.id.hiad_linked_video_view);
        PPSWLSView pPSWLSView = (PPSWLSView) findViewById(R.id.splash_wls_view);
        this.b = pPSWLSView;
        pPSWLSView.setVisibility(8);
        PPSSplashAdSourceView pPSSplashAdSourceView = (PPSSplashAdSourceView) findViewById(R.id.splash_ad_source_view);
        this.c = pPSSplashAdSourceView;
        pPSSplashAdSourceView.setVisibility(8);
        this.d = (ViewStub) findViewById(R.id.hiad_logo_stub);
        this.e = (PPSSplashProView) findViewById(R.id.hiad_splash_pro_view);
        this.f = (PPSSplashSwipeView) findViewById(R.id.hiad_splash_swipe_view);
        this.g = (PPSSplashTwistView) findViewById(R.id.hiad_splash_twist_view);
        this.i = (PPSSplashTwistClickView) findViewById(R.id.hiad_splash_twist_click_view);
        this.h = (PPSSplashSwipeClickView) findViewById(R.id.hiad_splash_swipe_click_view);
        this.f8034a.setNeedPauseOnSurfaceDestory(false);
        this.f8034a.setScreenOnWhilePlaying(true);
        this.f8034a.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        this.f8034a.setVideoScaleMode(2);
    }

    public ah(Context context) {
        super(context);
        a(context);
    }
}
