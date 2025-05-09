package com.autonavi.base.amap.mapcore.message;

import com.autonavi.ae.gmap.maploader.Pools;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;

/* loaded from: classes2.dex */
public class MoveGestureMapMessage extends AbstractGestureMapMessage {
    private static final Pools.SynchronizedPool<MoveGestureMapMessage> M_POOL = new Pools.SynchronizedPool<>(1024);
    static int newCount;
    public float touchDeltaX;
    public float touchDeltaY;
    public int touchX;
    public int touchY;

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage, com.autonavi.base.ae.gmap.AbstractMapMessage
    public int getType() {
        return 0;
    }

    public static MoveGestureMapMessage obtain(int i, float f, float f2, float f3, float f4) {
        MoveGestureMapMessage acquire;
        synchronized (MoveGestureMapMessage.class) {
            acquire = M_POOL.acquire();
            if (acquire == null) {
                acquire = new MoveGestureMapMessage(i, f, f2);
            } else {
                acquire.reset();
                acquire.setParams(i, f, f2);
            }
            acquire.touchX = (int) f3;
            acquire.touchY = (int) f4;
        }
        return acquire;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public static void destory() {
        M_POOL.destory();
    }

    private void setParams(int i, float f, float f2) {
        setState(i);
        this.touchDeltaX = f;
        this.touchDeltaY = f2;
    }

    public MoveGestureMapMessage(int i, float f, float f2) {
        super(i);
        this.touchX = 0;
        this.touchY = 0;
        this.touchDeltaX = f;
        this.touchDeltaY = f2;
        newCount++;
    }

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        int i = (int) this.touchDeltaX;
        int i2 = (int) this.touchDeltaY;
        int i3 = this.touchX;
        int i4 = this.touchY;
        IPoint obtain = IPoint.obtain();
        win2geo(gLMapState, this.touchX, this.touchY, obtain);
        IPoint obtain2 = IPoint.obtain();
        win2geo(gLMapState, i3 - i, i4 - i2, obtain2);
        IPoint obtain3 = IPoint.obtain();
        gLMapState.getMapGeoCenter(obtain3);
        gLMapState.setMapGeoCenter(obtain3.x + (obtain2.x - obtain.x), obtain3.y + (obtain2.y - obtain.y));
        gLMapState.recalculate();
        obtain3.recycle();
        obtain.recycle();
        obtain2.recycle();
    }
}
