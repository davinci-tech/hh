package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class hbx extends TrackAnimationControl {

    /* renamed from: a, reason: collision with root package name */
    private e f13072a;
    private b b;
    private c d;
    private d e;

    public hbx(ArrayList<LatLong> arrayList, ArrayList<LenLatLong> arrayList2, ReTrackSimplify reTrackSimplify) {
        this.b = new b();
        this.d = new c();
        this.f13072a = new e();
        this.e = new d();
        this.mLensData = arrayList2;
        this.mTrackData = arrayList;
        this.mReTrackSimplify = reTrackSimplify;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public void update() {
        if (this.mLensData == null || this.mTrackData == null || this.mReTrackSimplify == null) {
            return;
        }
        this.mRunZoomLevel = this.b.a().d();
        this.mMarkerControl.j();
        this.e.a();
        this.d.b();
        this.f13072a.a();
        this.mDurationControl.d(this.mMarkerControl.e(), this.mMarkerControl.b(), this.mMarkerControl.a(), this.mMarkerControl.c());
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getSpinDuration(double d2, double d3) {
        return this.e.a(d2, d3);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLineDrawDuration(double d2) {
        return this.b.d(d2) * this.mDrawLineDurationPerPixel;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLenMoveDuration(double d2) {
        return (this.b.d(d2) * this.mLenAdvanceDurationPerPixel) + 1.0d;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toScale(double d2) {
        return this.b.c(d2);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toZoom(double d2) {
        return this.b.b(d2);
    }

    class c {
        private double b;
        private double c;

        private c() {
            this.b = 1.0d;
            this.c = 1.0d;
        }

        private void c() {
            if (hbx.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                this.b = 3.8d;
                this.c = 1.8d;
            } else {
                this.b = 4.2d;
                this.c = 2.0d;
            }
        }

        private void e() {
            if (hbx.this.mRunScale < 1.0E-6d) {
                return;
            }
            double d = hbx.this.mStrength == TrackAnimationControl.Strength.HIGH ? 35000.0d : 30000.0d;
            if ((hbx.this.mReTrackSimplify.getTrackTotalDistance() / hbx.this.mRunScale) * hbx.this.mDrawLineDurationPerPixel > d) {
                hbx hbxVar = hbx.this;
                hbxVar.mDrawLineDurationPerPixel = d / (hbxVar.mReTrackSimplify.getTrackTotalDistance() / hbx.this.mRunScale);
            }
        }

        public void b() {
            c();
            double cycles = hbx.this.mReTrackSimplify.getCycles();
            if (cycles < 1.0d) {
                hbx.this.mDrawLineDurationPerPixel = this.b;
            } else {
                hbx hbxVar = hbx.this;
                double d = this.b;
                hbxVar.mDrawLineDurationPerPixel = d - ((cycles * (d - this.c)) / 8.0d);
            }
            double d2 = hbx.this.mDrawLineDurationPerPixel;
            double d3 = this.c;
            if (d2 < d3) {
                hbx.this.mDrawLineDurationPerPixel = d3;
            }
            e();
        }
    }

    class e {
        private e() {
        }

        public void a() {
            double d = (hbx.this.mDrawLineDurationPerPixel * hbx.this.b.d(hbx.this.mReTrackSimplify.getTrackTotalDistance())) - hbx.this.mTotalSpinDuration;
            if (d >= 1.0E-6d) {
                double d2 = hbx.this.b.d(hbx.this.mReTrackSimplify.getLensTotalDistance());
                if (Math.abs(d2) > 1.0E-6d) {
                    hbx.this.mLenAdvanceDurationPerPixel = d / d2;
                    return;
                }
                return;
            }
            hbx.this.mLenAdvanceDurationPerPixel = 0.0d;
        }
    }

    class d {

        /* renamed from: a, reason: collision with root package name */
        private Map<Integer, Integer> f13074a;

        private d() {
        }

        public double a(double d, double d2) {
            double abs = Math.abs(d - d2);
            if (abs >= 180.0d) {
                abs = 360.0d - abs;
            }
            double d3 = abs * 5.8d;
            if (d3 < 120.0d) {
                return 120.0d;
            }
            return d3;
        }

        public void a() {
            if (hbx.this.mLensData == null) {
                LogUtil.b("Track_GaoDeAnimationControl", "Lens Data is null");
                return;
            }
            e();
            hbx.this.mTotalSpinDuration = 0;
            this.f13074a = new HashMap();
            double angle = hbx.this.mLensData.get(0).getState() == -2 ? hbx.this.mLensData.get(0).getAngle() : 0.0d;
            for (int i = 1; i < hbx.this.mLensData.size(); i++) {
                if (hbx.this.mLensData.get(i).isTurnState()) {
                    double angle2 = hbx.this.mLensData.get(i).getAngle();
                    double abs = Math.abs(angle2 - angle);
                    if (abs >= 180.0d) {
                        abs = 360.0d - abs;
                    }
                    int i2 = (int) (abs * 5.8d);
                    if (i2 < 120) {
                        i2 = 120;
                    }
                    hbx.this.mTotalSpinDuration += i2;
                    this.f13074a.put(Integer.valueOf(i), Integer.valueOf(i2));
                    angle = angle2;
                }
            }
        }

        private void e() {
            Iterator<LenLatLong> it = hbx.this.mLensData.iterator();
            while (it.hasNext()) {
                LenLatLong next = it.next();
                if (hbx.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                    next.toOriginalState();
                } else {
                    next.forceStraight();
                }
            }
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private double f13073a;
        private double b;
        private float c;
        private float e;

        private b() {
            this.f13073a = 0.0d;
            this.b = 0.0d;
            this.c = 0.0f;
            this.e = 2.5f;
        }

        private void b() {
            this.e = 2.5f;
        }

        private void c() {
            if (hbx.this.mTrackData == null || hbx.this.mTrackData.size() <= 1) {
                return;
            }
            this.b = hbx.this.mReTrackSimplify.getTrackTotalDistance() / (hbx.this.mTrackData.size() - 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double b(double d) {
            return Math.log(d / 60026.0d) / (-0.693d);
        }

        private void j() {
            float b = (float) b(this.f13073a);
            this.c = b;
            float f = b < hbx.this.mOrgZoomLevel ? hbx.this.mOrgZoomLevel : this.c;
            this.c = f;
            this.c = f > hbx.this.mMaxZoomLevel ? hbx.this.mMaxZoomLevel : this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double c(double d) {
            return Math.exp(d * (-0.693d)) * 60026.0d;
        }

        private void i() {
            double c = c(this.c);
            this.f13073a = c;
            if (c < 1.0E-6d) {
                this.f13073a = this.b / hbx.this.mPixelDistance;
            }
            hbx.this.mRunScale = this.f13073a;
            hbx hbxVar = hbx.this;
            hbxVar.mDefaultDistance = hbxVar.mRunScale * hag.a(2.5f);
        }

        private void h() {
            hbx.this.mPixelDistance = hag.a(this.e);
            double c = this.b / c(hbx.this.mOrgZoomLevel);
            if (hbx.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                int i = ((int) ((c * 120.0d) / 100.0d)) + 1;
                if (hbx.this.mPixelDistance < i) {
                    hbx.this.mPixelDistance = i;
                    return;
                }
                return;
            }
            hbx.this.mPixelDistance = ((int) ((c * 103.0d) / 100.0d)) + 1;
        }

        private void e() {
            this.f13073a = this.b / hbx.this.mPixelDistance;
        }

        public b a() {
            c();
            b();
            h();
            e();
            j();
            i();
            return this;
        }

        public double d(double d) {
            double d2 = this.f13073a;
            if (d2 >= 1.0E-6d) {
                return d / d2;
            }
            LogUtil.b("Track_GaoDeAnimationControl", "scale is zero");
            return 1.0d;
        }

        public float d() {
            return this.c;
        }
    }
}
