package com.autonavi.base.amap.mapcore.message;

import com.autonavi.ae.gmap.maploader.Pools;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;

/* loaded from: classes2.dex */
public class ScaleGestureMapMessage extends AbstractGestureMapMessage {
    private static final Pools.SynchronizedPool<ScaleGestureMapMessage> M_POOL = new Pools.SynchronizedPool<>(256);
    public int pivotX;
    public int pivotY;
    public float scaleDelta;

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage, com.autonavi.base.ae.gmap.AbstractMapMessage
    public int getType() {
        return 1;
    }

    public static ScaleGestureMapMessage obtain(int i, float f, int i2, int i3) {
        ScaleGestureMapMessage acquire = M_POOL.acquire();
        if (acquire == null) {
            return new ScaleGestureMapMessage(i, f, i2, i3);
        }
        acquire.reset();
        acquire.setParams(i, f, i2, i3);
        return acquire;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public static void destory() {
        M_POOL.destory();
    }

    private void setParams(int i, float f, int i2, int i3) {
        setState(i);
        this.scaleDelta = f;
        this.pivotX = i2;
        this.pivotY = i3;
    }

    public ScaleGestureMapMessage(int i, float f, int i2, int i3) {
        super(i);
        this.scaleDelta = 0.0f;
        this.pivotX = 0;
        this.pivotY = 0;
        setParams(i, f, i2, i3);
    }

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        IPoint obtain;
        IPoint obtain2;
        if (this.isUseAnchor) {
            setMapZoomer(gLMapState);
            return;
        }
        int i = this.pivotX;
        int i2 = this.pivotY;
        if (this.isGestureScaleByMapCenter) {
            i = this.width >> 1;
            i2 = this.height >> 1;
        }
        if (i > 0 || i2 > 0) {
            obtain = IPoint.obtain();
            obtain2 = IPoint.obtain();
            win2geo(gLMapState, i, i2, obtain);
            gLMapState.setMapGeoCenter(obtain.x, obtain.y);
        } else {
            obtain = null;
            obtain2 = null;
        }
        setMapZoomer(gLMapState);
        if (i > 0 || i2 > 0) {
            win2geo(gLMapState, i, i2, obtain2);
            if (obtain != null) {
                gLMapState.setMapGeoCenter((obtain.x * 2) - obtain2.x, (obtain.y * 2) - obtain2.y);
            }
            gLMapState.recalculate();
        }
        if (obtain != null) {
            obtain.recycle();
        }
        if (obtain2 != null) {
            obtain2.recycle();
        }
    }

    private void setMapZoomer(GLMapState gLMapState) {
        gLMapState.setMapZoomer(gLMapState.getMapZoomer() + this.scaleDelta);
        gLMapState.recalculate();
    }
}
