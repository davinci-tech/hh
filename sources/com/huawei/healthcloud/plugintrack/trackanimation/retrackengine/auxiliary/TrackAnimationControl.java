package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes4.dex */
public abstract class TrackAnimationControl {
    protected static final float DEFAULT_DP_DISTANCE = 2.5f;
    private static final float DEFAULT_MAX_ZOOM = 19.0f;
    private static final float DEFAULT_MIN_ZOOM = 3.0f;
    protected static final int DEFAULT_PX_DISTANCE = 10;
    public static final long DISAPPEAR_DECELERATION_DURATION = 150;
    public static final long DISAPPEAR_FRICTION_DURATION = 250;
    public static final long DISAPPEAR_INTERVAL = 100;
    public static final long DISPLAY_DECELERATION_DURATION = 150;
    public static final long DISPLAY_FRICTION_DURATION = 250;
    public static final long DISPLAY_PHOTO_DURATION = 1600;
    public static final long FINAL_TILT_DURATION = 500;
    public static final long FINAL_ZOOM_DURATION = 1000;
    public static final long INITIAL_TILT_DURATION = 500;
    public static final long INITIAL_WAIT_DURATION = 300;
    public static final long INITIAL_ZOOM_DURATION = 1000;
    public static final long PAUSE_REGAIN_DURATION = 300;
    public static final long SYNC_TIME_DIFF = 200;
    private static final String TAG = "Track_TrackAnimationControl";
    public ArrayList<LenLatLong> mLensData = null;
    public ArrayList<LatLong> mTrackData = null;
    public ReTrackSimplify mReTrackSimplify = null;
    public float mMaxZoomLevel = DEFAULT_MAX_ZOOM;
    public float mOrgZoomLevel = 3.0f;
    protected float mRunZoomLevel = 0.0f;
    public double mRunScale = 1.0d;
    public int mPixelDistance = 10;
    protected a mMarkerControl = new a();
    protected d mDurationControl = new d();
    public int mTotalSpinDuration = 0;
    public double mDrawLineDurationPerPixel = 0.0d;
    public double mLenAdvanceDurationPerPixel = 0.0d;
    public Strength mStrength = Strength.HIGH;
    protected boolean mIsSupportArea = true;
    public double mDefaultDistance = 0.0d;
    protected int mPhotosNum = 0;
    protected int mEditMarkersNum = 0;

    public enum Strength {
        HIGH,
        MIDDLE,
        LOW
    }

    public abstract double getLenMoveDuration(double d2);

    public abstract double getLineDrawDuration(double d2);

    public float getRunTiltAngle() {
        return 45.0f;
    }

    public abstract double getSpinDuration(double d2, double d3);

    protected abstract double toScale(double d2);

    protected abstract double toZoom(double d2);

    public abstract void update();

    public double getKmMarkerSpace() {
        this.mMarkerControl.j();
        return this.mMarkerControl.d();
    }

    public float getRunZoomLevel() {
        return this.mRunZoomLevel;
    }

    public float getNewZoomLevel(float f) {
        if (!this.mIsSupportArea) {
            return this.mRunZoomLevel;
        }
        double zoom = toZoom(toScale(this.mRunZoomLevel) * f);
        float f2 = this.mRunZoomLevel;
        double d2 = f2;
        if (zoom <= d2) {
            return f2;
        }
        if (zoom - d2 > 1.5d) {
            zoom = f2 + 1.5f;
        }
        float f3 = this.mMaxZoomLevel;
        return zoom > ((double) f3) ? f3 : (float) zoom;
    }

    public TrackAnimationControl setMaxZoomLevel(float f) {
        this.mMaxZoomLevel = f;
        return this;
    }

    public TrackAnimationControl setOrgZoomLevel(float f) {
        this.mOrgZoomLevel = f;
        return this;
    }

    public TrackAnimationControl setAlgorithmStrength(Strength strength) {
        this.mStrength = strength;
        return this;
    }

    public TrackAnimationControl setIsSupportArea(boolean z) {
        this.mIsSupportArea = z;
        return this;
    }

    public TrackAnimationControl setPhotosNumber(int i) {
        this.mPhotosNum = i;
        return this;
    }

    public TrackAnimationControl setMarkersNumber(int i) {
        this.mEditMarkersNum = i;
        return this;
    }

    public long getDurationMs() {
        return this.mDurationControl.c();
    }

    public class a {
        private double b = 1000.0d;
        private double e = 1.0d;

        /* renamed from: a, reason: collision with root package name */
        private int f3615a = 0;

        protected a() {
        }

        private double e(double d) {
            this.e = 1.0d;
            while (d > 25000.0d) {
                d /= 10.0d;
                this.e *= 10.0d;
            }
            return d;
        }

        private double b(double d) {
            LogUtil.a(TrackAnimationControl.TAG, "Go into obtainInterval");
            double e = e(d(d));
            double d2 = 5000.0d;
            if (e <= 5000.0d) {
                d2 = 1000.0d;
            } else if (e <= 10000.0d) {
                d2 = 2000.0d;
            }
            LogUtil.a(TrackAnimationControl.TAG, "Go out obtainInterval");
            return c(d2 * this.e);
        }

        private double d(double d) {
            return UnitUtil.h() ? UnitUtil.e(d / 1000.0d, 3) * 1000.0d : d;
        }

        private double c(double d) {
            return UnitUtil.h() ? UnitUtil.d(d / 1000.0d, 3) * 1000.0d : d;
        }

        public void j() {
            if (TrackAnimationControl.this.mReTrackSimplify == null) {
                return;
            }
            this.b = b(TrackAnimationControl.this.mReTrackSimplify.getValidTotalDistance());
            this.f3615a = h() + i();
        }

        public double d() {
            return this.b;
        }

        public int b() {
            return h();
        }

        public int e() {
            return this.f3615a;
        }

        public int a() {
            return TrackAnimationControl.this.mPhotosNum;
        }

        public int c() {
            return TrackAnimationControl.this.mEditMarkersNum;
        }

        private int i() {
            return (int) (TrackAnimationControl.this.mReTrackSimplify.getValidTotalDistance() / this.b);
        }

        private int h() {
            HashSet hashSet = new HashSet();
            if (((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxSpeed().first).intValue() != -1) {
                hashSet.add((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxSpeed().first);
            }
            if (((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxHeartRate().first).intValue() != -1) {
                hashSet.add((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxHeartRate().first);
            }
            if (((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxAltitude().first).intValue() != -1) {
                hashSet.add((Integer) TrackAnimationControl.this.mReTrackSimplify.getMaxAltitude().first);
            }
            return hashSet.size();
        }
    }

    public class d {
        private int d = 0;
        private int f = 0;
        private long c = 0;

        /* renamed from: a, reason: collision with root package name */
        private int f3616a = 0;
        private int e = 0;

        private long b() {
            return 0L;
        }

        private long d() {
            return 3300L;
        }

        private long j() {
            return ProfileExtendConstants.TIME_OUT;
        }

        protected d() {
        }

        public void d(int i, int i2, int i3, int i4) {
            this.d = i;
            this.f = i2;
            this.f3616a = i3;
            this.e = i4;
            this.c = f();
        }

        public long c() {
            return this.c + 500;
        }

        private long h() {
            return (this.f * 1000) + (this.e * 700);
        }

        private long i() {
            return this.f3616a * TrackAnimationControl.DISPLAY_PHOTO_DURATION;
        }

        private long g() {
            if (this.d <= 0) {
                return 0L;
            }
            return ((r0 - 1) * 100) + 400;
        }

        private long e() {
            return (long) (((TrackAnimationControl.this.mDrawLineDurationPerPixel * (TrackAnimationControl.this.mReTrackSimplify.getTrackTotalDistance() / TrackAnimationControl.this.mRunScale)) * 101.0d) / 100.0d);
        }

        private long a() {
            return TrackAnimationControl.this.mReTrackSimplify.getAreaNumber() * 3200;
        }

        private long f() {
            if (TrackAnimationControl.this.mReTrackSimplify == null) {
                return 0L;
            }
            long d = d();
            long h = h();
            long g = g();
            long i = d + h + g + i() + e() + b() + j() + 200;
            return TrackAnimationControl.this.mIsSupportArea ? i + a() : i;
        }
    }
}
