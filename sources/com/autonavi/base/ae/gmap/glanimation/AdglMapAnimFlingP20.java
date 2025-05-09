package com.autonavi.base.ae.gmap.glanimation;

import android.os.SystemClock;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;

/* loaded from: classes2.dex */
public class AdglMapAnimFlingP20 extends AbstractAdglAnimation {
    private boolean hasCheckParams;
    private AbstractAdglAnimationParam2V moveParam = null;
    private boolean needMove;
    private float velocityScreenX;
    private float velocityScreenY;

    public AdglMapAnimFlingP20(int i) {
        reset();
        this.duration = i;
    }

    public void reset() {
        AbstractAdglAnimationParam2V abstractAdglAnimationParam2V = this.moveParam;
        if (abstractAdglAnimationParam2V != null) {
            abstractAdglAnimationParam2V.reset();
        }
        this.velocityScreenX = 0.0f;
        this.velocityScreenY = 0.0f;
        this.needMove = false;
        this.hasCheckParams = false;
    }

    public void setPositionAndVelocity(float f, float f2) {
        this.moveParam = null;
        this.velocityScreenX = f;
        this.velocityScreenY = f2;
        AbstractAdglAnimationParam2V abstractAdglAnimationParam2V = new AbstractAdglAnimationParam2V();
        this.moveParam = abstractAdglAnimationParam2V;
        abstractAdglAnimationParam2V.setInterpolatorType(2, 1.2f);
        this.needMove = false;
        this.hasCheckParams = false;
    }

    public void commitAnimation(Object obj) {
        boolean z;
        GLMapState gLMapState = (GLMapState) obj;
        if (gLMapState == null) {
            return;
        }
        this.hasCheckParams = false;
        this.isOver = true;
        float f = (this.velocityScreenX * this.duration) / 2000.0f;
        float f2 = (this.velocityScreenY * this.duration) / 2000.0f;
        if (Math.abs(f) == 0.0f || Math.abs(f2) == 0.0f) {
            z = true;
        } else {
            this.isOver = false;
            IPoint obtain = IPoint.obtain();
            gLMapState.getMapGeoCenter(obtain);
            this.moveParam.setFromValue(obtain.x, obtain.y);
            double mapAngle = (gLMapState.getMapAngle() * 3.141592653589793d) / 180.0d;
            double mapLenWithWin = gLMapState.getMapLenWithWin(1);
            double d = f;
            double d2 = f2;
            this.moveParam.setToValue(obtain.x - (((Math.cos(mapAngle) * d) - (Math.sin(mapAngle) * d2)) * mapLenWithWin), obtain.y - (mapLenWithWin * ((Math.sin(mapAngle) * d) + (d2 * Math.cos(mapAngle)))));
            this.needMove = this.moveParam.needToCaculate();
            obtain.recycle();
            z = true;
        }
        this.hasCheckParams = z;
        this.startTime = SystemClock.uptimeMillis();
    }

    @Override // com.autonavi.base.ae.gmap.glanimation.AbstractAdglAnimation
    public void doAnimation(Object obj) {
        GLMapState gLMapState = (GLMapState) obj;
        if (gLMapState == null) {
            return;
        }
        if (!this.hasCheckParams) {
            commitAnimation(obj);
        }
        if (this.isOver) {
            return;
        }
        this.offsetTime = SystemClock.uptimeMillis() - this.startTime;
        float f = this.offsetTime / this.duration;
        if (f > 1.0f) {
            this.isOver = true;
            f = 1.0f;
        }
        if (f < 0.0f || f > 1.0f || !this.needMove) {
            return;
        }
        this.moveParam.setNormalizedTime(f);
        gLMapState.setMapGeoCenter(this.moveParam.getCurXValue(), this.moveParam.getCurYValue());
    }
}
