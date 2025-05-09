package defpackage;

import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.animation.Animation;
import com.huawei.hms.maps.model.animation.ScaleAnimation;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hkr extends GrowAnimationBuilder {
    private Marker d = null;

    /* renamed from: a, reason: collision with root package name */
    private Animation f13220a = null;
    private boolean c = false;

    public void a(Marker marker) {
        this.d = marker;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void displayAnimation() {
        if (this.d == null) {
            LogUtil.a("Track_HmsMarkerAnimation", "displayAnimation mGrowMarker is null");
            return;
        }
        this.c = false;
        this.f13220a = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        if (this.mMarkerType == GrowAnimationBuilder.MarkerType.KM_MARKER) {
            this.f13220a.setInterpolator(new OvershootInterpolator(2.6f));
        } else {
            this.f13220a.setInterpolator(new OvershootInterpolator(3.2f));
        }
        b();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void disappearAnimation() {
        if (this.d == null) {
            LogUtil.a("Track_HmsMarkerAnimation", "disappearAnimation mGrowMarker is null");
            return;
        }
        this.c = true;
        this.f13220a = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f);
        if (this.mMarkerType == GrowAnimationBuilder.MarkerType.KM_MARKER) {
            this.f13220a.setInterpolator(new AnticipateInterpolator(2.6f));
        } else {
            this.f13220a.setInterpolator(new AnticipateInterpolator(3.2f));
        }
        b();
    }

    private void b() {
        this.f13220a.setFillMode(0);
        this.f13220a.setDuration(this.mDisplayFrictionAnimationDuration);
        this.f13220a.setAnimationListener(new Animation.AnimationListener() { // from class: hkr.1
            @Override // com.huawei.hms.maps.model.animation.Animation.AnimationListener
            public void onAnimationStart() {
            }

            @Override // com.huawei.hms.maps.model.animation.Animation.AnimationListener
            public void onAnimationEnd() {
                if (hkr.this.c && hkr.this.d != null) {
                    hkr.this.d.setVisible(false);
                }
                if (hkr.this.mAnimationCallback != null) {
                    hkr.this.mAnimationCallback.onFinish();
                }
            }
        });
        this.d.setAnimation(this.f13220a);
        this.d.startAnimation();
    }
}
