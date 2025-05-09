package com.amap.api.col.p0003sl;

import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;

/* loaded from: classes2.dex */
public final class ai extends AbstractCameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void runCameraUpdate(IGLMapState iGLMapState) {
        this.zoom = iGLMapState.getMapZoomer() + this.amount;
        this.zoom = dv.a(this.mapConfig, this.zoom);
        normalChange(iGLMapState);
    }

    @Override // com.autonavi.amap.mapcore.AbstractCameraUpdateMessage
    public final void mergeCameraUpdateDelegate(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.zoom += this.amount;
    }
}
