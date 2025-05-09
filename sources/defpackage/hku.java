package defpackage;

import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hku extends GrowAnimationBuilder {

    /* renamed from: a, reason: collision with root package name */
    private Marker f13224a = null;
    private Animation b = null;

    public void d(Marker marker) {
        this.f13224a = marker;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void displayAnimation() {
        if (this.f13224a == null) {
            return;
        }
        this.b = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        if (this.mMarkerType == GrowAnimationBuilder.MarkerType.KM_MARKER) {
            this.b.setInterpolator(new OvershootInterpolator(2.6f));
        } else {
            this.b.setInterpolator(new OvershootInterpolator(3.2f));
        }
        LogUtil.a("Suggestion_GaoDeMarkerAnimation", "displayAnimation: new animation");
        e();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void disappearAnimation() {
        if (this.f13224a == null) {
            return;
        }
        this.b = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f);
        if (this.mMarkerType == GrowAnimationBuilder.MarkerType.KM_MARKER) {
            this.b.setInterpolator(new AnticipateInterpolator(2.6f));
        } else {
            this.b.setInterpolator(new AnticipateInterpolator(3.2f));
        }
        LogUtil.a("Suggestion_GaoDeMarkerAnimation", "disappearAnimation: new animation");
        e();
    }

    private void e() {
        this.b.setFillMode(0);
        this.b.setDuration(this.mDisplayFrictionAnimationDuration);
        this.b.setAnimationListener(new Animation.AnimationListener() { // from class: hku.3
            @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
            public void onAnimationStart() {
                LogUtil.a("Suggestion_GaoDeMarkerAnimation", "onAnimationStart: FrictionAnimation");
            }

            @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
            public void onAnimationEnd() {
                LogUtil.a("Suggestion_GaoDeMarkerAnimation", "onAnimationEnd: FrictionAnimation");
                if (hku.this.mAnimationCallback != null) {
                    hku.this.mAnimationCallback.onFinish();
                }
            }
        });
        this.f13224a.setAnimation(this.b);
        this.f13224a.startAnimation();
    }
}
