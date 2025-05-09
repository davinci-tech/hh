package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class hcb extends TrackAnimationControl {

    /* renamed from: a, reason: collision with root package name */
    private b f13079a;
    private d b;
    private a c;
    private e d;

    public hcb(ArrayList<LatLong> arrayList, ArrayList<LenLatLong> arrayList2, ReTrackSimplify reTrackSimplify) {
        this.f13079a = new b();
        this.d = new e();
        this.b = new d();
        this.c = new a();
        this.mTrackData = arrayList;
        this.mLensData = arrayList2;
        this.mReTrackSimplify = reTrackSimplify;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public void update() {
        if (this.mLensData == null || this.mTrackData == null || this.mReTrackSimplify == null) {
            return;
        }
        this.mRunZoomLevel = this.f13079a.e().c();
        this.mMarkerControl.j();
        this.c.e();
        this.d.b();
        this.b.d();
        this.mDurationControl.d(this.mMarkerControl.e(), this.mMarkerControl.b(), this.mMarkerControl.a(), this.mMarkerControl.c());
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getSpinDuration(double d2, double d3) {
        return this.c.d(d2, d3);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLineDrawDuration(double d2) {
        return this.f13079a.c(d2) * this.mDrawLineDurationPerPixel;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double getLenMoveDuration(double d2) {
        return (this.f13079a.c(d2) * this.mLenAdvanceDurationPerPixel) + 1.0d;
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toScale(double d2) {
        return this.f13079a.a(d2);
    }

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.TrackAnimationControl
    public double toZoom(double d2) {
        return this.f13079a.b(d2);
    }

    class e {

        /* renamed from: a, reason: collision with root package name */
        private double f13082a;
        private double c;

        private e() {
            this.c = 1.0d;
            this.f13082a = 1.0d;
        }

        private void d() {
            if (hcb.this.mStrength == TrackAnimationControl.Strength.HIGH || hcb.this.mStrength == TrackAnimationControl.Strength.MIDDLE) {
                this.c = 3.8d;
                this.f13082a = 1.8d;
            } else {
                this.c = 4.2d;
                this.f13082a = 2.0d;
            }
        }

        private void c() {
            if (hcb.this.mRunScale < 1.0E-6d) {
                return;
            }
            double d = (hcb.this.mStrength == TrackAnimationControl.Strength.HIGH || hcb.this.mStrength == TrackAnimationControl.Strength.MIDDLE) ? 35000.0d : 30000.0d;
            if ((hcb.this.mReTrackSimplify.getTrackTotalDistance() / hcb.this.mRunScale) * hcb.this.mDrawLineDurationPerPixel > d) {
                hcb hcbVar = hcb.this;
                hcbVar.mDrawLineDurationPerPixel = d / (hcbVar.mReTrackSimplify.getTrackTotalDistance() / hcb.this.mRunScale);
            }
        }

        public void b() {
            d();
            double cycles = hcb.this.mReTrackSimplify.getCycles();
            if (cycles < 1.0d) {
                hcb.this.mDrawLineDurationPerPixel = this.c;
            } else {
                hcb hcbVar = hcb.this;
                double d = this.c;
                hcbVar.mDrawLineDurationPerPixel = d - ((cycles * (d - this.f13082a)) / 8.0d);
            }
            double d2 = hcb.this.mDrawLineDurationPerPixel;
            double d3 = this.f13082a;
            if (d2 < d3) {
                hcb.this.mDrawLineDurationPerPixel = d3;
            }
            c();
        }
    }

    class d {
        private d() {
        }

        public void d() {
            double c = (hcb.this.mDrawLineDurationPerPixel * hcb.this.f13079a.c(hcb.this.mReTrackSimplify.getTrackTotalDistance())) - hcb.this.mTotalSpinDuration;
            if (c < 1.0E-6d) {
                hcb.this.mLenAdvanceDurationPerPixel = 0.0d;
                return;
            }
            double c2 = hcb.this.f13079a.c(hcb.this.mReTrackSimplify.getLensTotalDistance());
            if (Math.abs(c2) > 1.0E-6d) {
                hcb.this.mLenAdvanceDurationPerPixel = c / c2;
            }
        }
    }

    class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double d(double d, double d2) {
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

        public void e() {
            if (hcb.this.mLensData == null) {
                LogUtil.b("Track_HmsAnimationControl", "Lens Data is null");
                return;
            }
            c();
            hcb.this.mTotalSpinDuration = 0;
            double angle = hcb.this.mLensData.get(0).getState() == -2 ? 360.0d - hcb.this.mLensData.get(0).getAngle() : 0.0d;
            for (int i = 1; i < hcb.this.mLensData.size(); i++) {
                if (hcb.this.mLensData.get(i).isTurnState()) {
                    double angle2 = 360.0d - hcb.this.mLensData.get(i).getAngle();
                    double abs = Math.abs(angle2 - angle);
                    if (abs >= 180.0d) {
                        abs = 360.0d - abs;
                    }
                    int i2 = (int) (abs * 5.8d);
                    if (i2 < 120) {
                        i2 = 120;
                    }
                    hcb.this.mTotalSpinDuration += i2;
                    angle = angle2;
                }
            }
        }

        private void c() {
            Iterator<LenLatLong> it = hcb.this.mLensData.iterator();
            while (it.hasNext()) {
                LenLatLong next = it.next();
                if (hcb.this.mStrength == TrackAnimationControl.Strength.HIGH || hcb.this.mStrength == TrackAnimationControl.Strength.MIDDLE) {
                    next.toOriginalState();
                } else {
                    next.forceStraight();
                }
            }
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private float f13081a;
        private float c;
        private double d;
        private double e;

        private b() {
            this.e = 0.0d;
            this.d = 0.0d;
            this.c = 0.0f;
            this.f13081a = 2.5f;
        }

        private void a() {
            this.f13081a = 2.5f;
        }

        private void b() {
            if (hcb.this.mTrackData == null || hcb.this.mTrackData.size() <= 1) {
                return;
            }
            this.d = hcb.this.mReTrackSimplify.getTrackTotalDistance() / (hcb.this.mTrackData.size() - 1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double b(double d) {
            return Math.log(d / 49671.0d) / (-0.688d);
        }

        private void j() {
            float b = (float) b(this.e);
            this.c = b;
            float f = b < hcb.this.mOrgZoomLevel ? hcb.this.mOrgZoomLevel : this.c;
            this.c = f;
            this.c = f > hcb.this.mMaxZoomLevel ? hcb.this.mMaxZoomLevel : this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double a(double d) {
            return Math.exp(d * (-0.688d)) * 49671.0d;
        }

        private void f() {
            double a2 = a(this.c);
            this.e = a2;
            if (a2 < 1.0E-6d) {
                this.e = this.d / hcb.this.mPixelDistance;
            }
            hcb.this.mRunScale = this.e;
        }

        private void i() {
            hcb.this.mPixelDistance = hag.a(this.f13081a);
            double a2 = this.d / a(hcb.this.mOrgZoomLevel);
            if (hcb.this.mStrength == TrackAnimationControl.Strength.HIGH || hcb.this.mStrength == TrackAnimationControl.Strength.MIDDLE) {
                int i = ((int) ((a2 * 120.0d) / 100.0d)) + 1;
                if (hcb.this.mPixelDistance < i) {
                    hcb.this.mPixelDistance = i;
                    return;
                }
                return;
            }
            hcb.this.mPixelDistance = ((int) ((a2 * 103.0d) / 100.0d)) + 1;
        }

        private void d() {
            this.e = this.d / hcb.this.mPixelDistance;
        }

        public b e() {
            b();
            a();
            i();
            d();
            j();
            f();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double c(double d) {
            double d2 = this.e;
            if (d2 >= 1.0E-6d) {
                return d / d2;
            }
            LogUtil.b("Track_HmsAnimationControl", "scale is zero");
            return 1.0d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float c() {
            return this.c;
        }
    }
}
