package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class hca extends TrackAnimationControl {

    /* renamed from: a, reason: collision with root package name */
    private d f13077a;
    private a b;
    private e c;
    private c e;

    public hca(ArrayList<LatLong> arrayList, ArrayList<LenLatLong> arrayList2, ReTrackSimplify reTrackSimplify) {
        this.b = new a();
        this.c = new e();
        this.f13077a = new d();
        this.e = new c();
        this.mTrackData = arrayList;
        this.mLensData = arrayList2;
        this.mReTrackSimplify = reTrackSimplify;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public void update() {
        if (this.mLensData == null || this.mTrackData == null || this.mReTrackSimplify == null) {
            LogUtil.a("Track_HmsAnimationControl", "HmsAnimationControl update null data return");
            return;
        }
        this.mRunZoomLevel = this.b.c().e();
        this.mMarkerControl.j();
        this.e.a();
        this.c.a();
        this.f13077a.e();
        this.mDurationControl.d(this.mMarkerControl.e(), this.mMarkerControl.b(), this.mMarkerControl.a(), this.mMarkerControl.c());
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getSpinDuration(double d2, double d3) {
        return this.e.e(d2, d3);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLineDrawDuration(double d2) {
        return this.b.e(d2) * this.mDrawLineDurationPerPixel;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLenMoveDuration(double d2) {
        return (this.b.e(d2) * this.mLenAdvanceDurationPerPixel) + 1.0d;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toScale(double d2) {
        return this.b.d(d2);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toZoom(double d2) {
        return this.b.a(d2);
    }

    class e {
        private double c;
        private double e;

        private e() {
            this.e = 1.0d;
            this.c = 1.0d;
        }

        private void b() {
            if (hca.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                this.e = 3.8d;
                this.c = 1.8d;
            } else {
                this.e = 4.2d;
                this.c = 2.0d;
            }
        }

        private void c() {
            if (hca.this.mRunScale < 1.0E-6d) {
                return;
            }
            double d = hca.this.mStrength == TrackAnimationControl.Strength.HIGH ? 35000.0d : 30000.0d;
            if ((hca.this.mReTrackSimplify.getTrackTotalDistance() / hca.this.mRunScale) * hca.this.mDrawLineDurationPerPixel > d) {
                hca hcaVar = hca.this;
                hcaVar.mDrawLineDurationPerPixel = d / (hcaVar.mReTrackSimplify.getTrackTotalDistance() / hca.this.mRunScale);
            }
        }

        public void a() {
            b();
            double cycles = hca.this.mReTrackSimplify.getCycles();
            if (cycles < 1.0d) {
                hca.this.mDrawLineDurationPerPixel = this.e;
            } else {
                hca hcaVar = hca.this;
                double d = this.e;
                hcaVar.mDrawLineDurationPerPixel = d - ((cycles * (d - this.c)) / 8.0d);
            }
            double d2 = hca.this.mDrawLineDurationPerPixel;
            double d3 = this.c;
            if (d2 < d3) {
                hca.this.mDrawLineDurationPerPixel = d3;
            }
            c();
        }
    }

    class d {
        private d() {
        }

        public void e() {
            double e = (hca.this.mDrawLineDurationPerPixel * hca.this.b.e(hca.this.mReTrackSimplify.getTrackTotalDistance())) - hca.this.mTotalSpinDuration;
            if (e < 1.0E-6d) {
                hca.this.mLenAdvanceDurationPerPixel = 0.0d;
                return;
            }
            double e2 = hca.this.b.e(hca.this.mReTrackSimplify.getLensTotalDistance());
            if (Math.abs(e2) > 1.0E-6d) {
                hca.this.mLenAdvanceDurationPerPixel = e / e2;
            }
        }
    }

    class c {
        private c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double e(double d, double d2) {
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
            if (koq.b(hca.this.mLensData) || hca.this.mLensData.get(0) == null) {
                LogUtil.b("Track_HmsAnimationControl", "Lens Data is null");
                return;
            }
            d();
            hca.this.mTotalSpinDuration = 0;
            double angle = hca.this.mLensData.get(0).getState() == -2 ? 360.0d - hca.this.mLensData.get(0).getAngle() : 0.0d;
            for (int i = 1; i < hca.this.mLensData.size(); i++) {
                if (hca.this.mLensData.get(i) != null && hca.this.mLensData.get(i).isTurnState()) {
                    double angle2 = 360.0d - hca.this.mLensData.get(i).getAngle();
                    double abs = Math.abs(angle2 - angle);
                    if (abs >= 180.0d) {
                        abs = 360.0d - abs;
                    }
                    int i2 = (int) (abs * 5.8d);
                    if (i2 < 120) {
                        i2 = 120;
                    }
                    hca.this.mTotalSpinDuration += i2;
                    angle = angle2;
                }
            }
        }

        private void d() {
            Iterator<LenLatLong> it = hca.this.mLensData.iterator();
            while (it.hasNext()) {
                LenLatLong next = it.next();
                if (hca.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                    next.toOriginalState();
                } else {
                    next.forceStraight();
                }
            }
        }
    }

    class a {
        private double b;
        private float c;
        private float d;
        private double e;

        private a() {
            this.e = 0.0d;
            this.b = 0.0d;
            this.c = 0.0f;
            this.d = 2.5f;
        }

        private void a() {
            this.d = 2.5f;
        }

        private void b() {
            if (hca.this.mTrackData == null || hca.this.mTrackData.size() <= 1) {
                return;
            }
            this.b = hca.this.mReTrackSimplify.getTrackTotalDistance() / (hca.this.mTrackData.size() - 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double a(double d) {
            return Math.log(d / 49671.0d) / (-0.688d);
        }

        private void j() {
            float a2 = (float) a(this.e);
            this.c = a2;
            float f = a2 < hca.this.mOrgZoomLevel ? hca.this.mOrgZoomLevel : this.c;
            this.c = f;
            this.c = f > hca.this.mMaxZoomLevel ? hca.this.mMaxZoomLevel : this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double d(double d) {
            return Math.exp(d * (-0.688d)) * 49671.0d;
        }

        private void h() {
            double d = d(this.c);
            this.e = d;
            if (d < 1.0E-6d) {
                this.e = this.b / hca.this.mPixelDistance;
            }
            hca.this.mRunScale = this.e;
            hca hcaVar = hca.this;
            hcaVar.mDefaultDistance = hcaVar.mRunScale * hag.a(2.5f);
        }

        private void i() {
            hca.this.mPixelDistance = hag.a(this.d);
            double d = this.b / d(hca.this.mOrgZoomLevel);
            if (hca.this.mStrength == TrackAnimationControl.Strength.HIGH) {
                int i = ((int) ((d * 120.0d) / 100.0d)) + 1;
                if (hca.this.mPixelDistance < i) {
                    hca.this.mPixelDistance = i;
                    return;
                }
                return;
            }
            hca.this.mPixelDistance = ((int) ((d * 103.0d) / 100.0d)) + 1;
        }

        private void d() {
            this.e = this.b / hca.this.mPixelDistance;
        }

        public a c() {
            b();
            a();
            i();
            d();
            j();
            h();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double e(double d) {
            double d2 = this.e;
            if (d2 >= 1.0E-6d) {
                return d / d2;
            }
            LogUtil.b("Track_HmsAnimationControl", "scale is zero");
            return 1.0d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float e() {
            return this.c;
        }
    }
}
