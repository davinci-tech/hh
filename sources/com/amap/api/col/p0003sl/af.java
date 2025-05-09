package com.amap.api.col.p0003sl;

import android.util.Pair;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.IPoint;

/* loaded from: classes2.dex */
public final class af extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void runCameraUpdate(IGLMapState iGLMapState) {
        Pair<Float, IPoint> a2 = dv.a(this, this.mapConfig);
        if (a2 == null) {
            return;
        }
        iGLMapState.setMapZoomer(((Float) a2.first).floatValue());
        iGLMapState.setMapGeoCenter(((IPoint) a2.second).x, ((IPoint) a2.second).y);
        iGLMapState.setCameraDegree(0.0f);
        iGLMapState.setMapAngle(0.0f);
    }
}
