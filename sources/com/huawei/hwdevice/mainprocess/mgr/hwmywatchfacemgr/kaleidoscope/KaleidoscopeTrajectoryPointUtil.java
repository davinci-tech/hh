package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.kaleidoscope;

import android.graphics.Point;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class KaleidoscopeTrajectoryPointUtil {
    private static final Object CLASS_LOCK = new Object();
    private static final int FOUR_LEAF_COEF = 2;
    private static final int HALF_SCREEN_SIZE = 233;
    private static final float PI = 3.1415927f;
    private static final float START_ROTATE_TANGLE = 90.0f;
    private static final String TAG = "KaleidoscopeRectUtil";
    private static final String TAG_RELEASE = "R_KaleidoscopeRectUtil";
    private static final int TWO_TIMES = 2;
    private static volatile KaleidoscopeTrajectoryPointUtil sInstance;
    private Point mTrajectoryPoint = new Point();
    private Point mTrajectoryCenter = new Point();
    private float mThetaVal = 0.0f;
    private int mTrajectoryRadius = HALF_SCREEN_SIZE;

    private KaleidoscopeTrajectoryPointUtil() {
    }

    public static KaleidoscopeTrajectoryPointUtil getInstance() {
        if (sInstance == null) {
            synchronized (CLASS_LOCK) {
                if (sInstance == null) {
                    sInstance = new KaleidoscopeTrajectoryPointUtil();
                }
            }
        }
        return sInstance;
    }

    private void updateTrajectoryPoint() {
        if (Float.compare(Math.abs(this.mThetaVal), 6.2831855f) < 0) {
            double sin = (float) (this.mTrajectoryRadius * Math.sin(this.mThetaVal * 2.0f));
            this.mTrajectoryPoint.x = this.mTrajectoryCenter.x + ((int) (Math.cos(this.mThetaVal) * sin));
            this.mTrajectoryPoint.y = this.mTrajectoryCenter.y + ((int) (sin * Math.sin(this.mThetaVal)));
        }
    }

    public Point geTrajectoryPoint() {
        return this.mTrajectoryPoint;
    }

    public float getRotateTangle() {
        return START_ROTATE_TANGLE - ((float) Math.toDegrees(this.mThetaVal));
    }

    public void initWatchFaceIdPoint(int i) {
        initParams(i);
        updateTrajectoryPoint();
        ReleaseLogUtil.e(TAG_RELEASE, "initWatchFaceIdPoint srcBitmapSize: ", Integer.valueOf(i));
    }

    private void initParams(int i) {
        int i2 = i / 2;
        this.mTrajectoryCenter.x = i2;
        this.mTrajectoryCenter.y = i2;
        this.mTrajectoryRadius = i2;
        this.mThetaVal = 0.0f;
    }
}
