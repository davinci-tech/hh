package defpackage;

import android.os.Handler;
import com.google.android.gms.maps.model.Marker;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hks extends GrowAnimationBuilder {

    /* renamed from: a, reason: collision with root package name */
    private Marker f13221a = null;

    public void b(Marker marker) {
        this.f13221a = marker;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void displayAnimation() {
        if (this.f13221a == null) {
            LogUtil.a("Track_GoogleMarkerAnimation", "displayAnimation mGrowMarker is null");
        } else {
            c(this.mDisplayFrictionAnimationDuration);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder
    public void disappearAnimation() {
        if (this.f13221a == null) {
            LogUtil.a("Track_GoogleMarkerAnimation", "disappearAnimation mGrowMarker is null");
        } else {
            c(this.mDisplayFrictionAnimationDuration);
        }
    }

    private void c(long j) {
        new Handler().postDelayed(new Runnable() { // from class: hks.2
            @Override // java.lang.Runnable
            public void run() {
                if (hks.this.mAnimationCallback != null) {
                    hks.this.mAnimationCallback.onFinish();
                }
            }
        }, j);
    }
}
