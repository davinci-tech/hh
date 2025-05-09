package com.amap.api.maps;

import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.interfaces.IInfoWindowManager;

/* loaded from: classes8.dex */
public class InfoWindowAnimationManager {

    /* renamed from: a, reason: collision with root package name */
    IInfoWindowManager f1415a;

    public InfoWindowAnimationManager(IInfoWindowManager iInfoWindowManager) {
        this.f1415a = iInfoWindowManager;
    }

    public void setInfoWindowAnimation(Animation animation, Animation.AnimationListener animationListener) {
        this.f1415a.setInfoWindowAnimation(animation, animationListener);
    }

    public void setInfoWindowAppearAnimation(Animation animation) {
        this.f1415a.setInfoWindowAppearAnimation(animation);
    }

    public void setInfoWindowBackColor(int i) {
        this.f1415a.setInfoWindowBackColor(i);
    }

    public void setInfoWindowBackEnable(boolean z) {
        this.f1415a.setInfoWindowBackEnable(z);
    }

    public void setInfoWindowBackScale(float f, float f2) {
        this.f1415a.setInfoWindowBackScale(f, f2);
    }

    public void setInfoWindowDisappearAnimation(Animation animation) {
        this.f1415a.setInfoWindowDisappearAnimation(animation);
    }

    public void setInfoWindowMovingAnimation(Animation animation) {
        this.f1415a.setInfoWindowMovingAnimation(animation);
    }

    public void startAnimation() {
        this.f1415a.startAnimation();
    }
}
