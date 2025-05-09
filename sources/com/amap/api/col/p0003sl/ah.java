package com.amap.api.col.p0003sl;

import android.graphics.Point;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* loaded from: classes2.dex */
public final class ah extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void runCameraUpdate(IGLMapState iGLMapState) {
        float f = this.xPixel;
        float f2 = this.yPixel;
        Point point = new Point();
        a(iGLMapState, (int) ((this.width / 2.0f) + f), (int) ((this.height / 2.0f) + f2), point);
        iGLMapState.setMapGeoCenter(point.x, point.y);
    }

    private static void a(IGLMapState iGLMapState, int i, int i2, Point point) {
        iGLMapState.screenToP20Point(i, i2, point);
    }
}
