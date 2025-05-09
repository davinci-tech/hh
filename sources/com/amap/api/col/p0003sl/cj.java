package com.amap.api.col.p0003sl;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;

/* loaded from: classes2.dex */
public final class cj {
    ValueAnimator b;
    private IAMapDelegate e;
    private Marker f;
    private Circle g;
    private LatLng i;
    private double j;
    private Context k;
    private ac l;
    private MyLocationStyle h = new MyLocationStyle();
    private int m = 4;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;

    /* renamed from: a, reason: collision with root package name */
    a f937a = null;
    Animator.AnimatorListener c = new Animator.AnimatorListener() { // from class: com.amap.api.col.3sl.cj.1
        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            cj.this.k();
        }
    };
    ValueAnimator.AnimatorUpdateListener d = new ValueAnimator.AnimatorUpdateListener() { // from class: com.amap.api.col.3sl.cj.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            try {
                if (cj.this.g != null) {
                    LatLng latLng = (LatLng) valueAnimator.getAnimatedValue();
                    cj.this.g.setCenter(latLng);
                    cj.this.f.setPosition(latLng);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };

    public cj(IAMapDelegate iAMapDelegate, Context context) {
        Context applicationContext = context.getApplicationContext();
        this.k = applicationContext;
        this.e = iAMapDelegate;
        this.l = new ac(applicationContext, iAMapDelegate);
        a(4, true);
    }

    public final void a(MyLocationStyle myLocationStyle) {
        try {
            this.h = myLocationStyle;
            a(myLocationStyle.isMyLocationShowing());
            if (!this.h.isMyLocationShowing()) {
                this.l.a(false);
                this.m = this.h.getMyLocationType();
                return;
            }
            l();
            Marker marker = this.f;
            if (marker == null && this.g == null) {
                return;
            }
            this.l.a(marker);
            a(this.h.getMyLocationType());
        } catch (Throwable th) {
            iv.c(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    public final MyLocationStyle a() {
        return this.h;
    }

    private void a(int i) {
        a(i, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r4 != 7) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r4, boolean r5) {
        /*
            r3 = this;
            r3.m = r4
            r0 = 0
            r3.n = r0
            r3.p = r0
            r3.o = r0
            r3.r = r0
            r3.s = r0
            r1 = 1
            if (r4 == r1) goto L36
            r2 = 2
            if (r4 == r2) goto L31
            r2 = 3
            if (r4 == r2) goto L2c
            r2 = 4
            if (r4 == r2) goto L25
            r2 = 5
            if (r4 == r2) goto L20
            r2 = 7
            if (r4 == r2) goto L2e
            goto L3c
        L20:
            r3.r = r1
            r3.q = r0
            goto L3c
        L25:
            r3.o = r1
            r3.r = r1
            r3.q = r0
            goto L3c
        L2c:
            r3.o = r1
        L2e:
            r3.s = r1
            goto L3c
        L31:
            r3.o = r1
            r3.q = r1
            goto L3c
        L36:
            r3.o = r1
            r3.p = r1
            r3.q = r1
        L3c:
            boolean r4 = r3.r
            if (r4 != 0) goto L56
            boolean r4 = r3.s
            if (r4 == 0) goto L45
            goto L56
        L45:
            com.amap.api.maps.model.Marker r4 = r3.f
            if (r4 == 0) goto L4c
            r4.setFlat(r0)
        L4c:
            r3.i()
            r3.h()
            r3.g()
            goto L88
        L56:
            boolean r4 = r3.s
            if (r4 == 0) goto L77
            com.amap.api.col.3sl.ac r4 = r3.l
            r4.a(r1)
            if (r5 != 0) goto L71
            com.autonavi.base.amap.api.mapcore.IAMapDelegate r4 = r3.e     // Catch: java.lang.Throwable -> L6d
            r5 = 1099431936(0x41880000, float:17.0)
            com.autonavi.amap.mapcore.AbstractCameraUpdateMessage r5 = com.amap.api.col.p0003sl.aj.a(r5)     // Catch: java.lang.Throwable -> L6d
            r4.moveCamera(r5)     // Catch: java.lang.Throwable -> L6d
            goto L71
        L6d:
            r4 = move-exception
            r4.printStackTrace()
        L71:
            r4 = 1110704128(0x42340000, float:45.0)
            r3.b(r4)
            goto L7c
        L77:
            com.amap.api.col.3sl.ac r4 = r3.l
            r4.a(r0)
        L7c:
            com.amap.api.col.3sl.ac r4 = r3.l
            r4.a()
            com.amap.api.maps.model.Marker r4 = r3.f
            if (r4 == 0) goto L88
            r4.setFlat(r1)
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.cj.a(int, boolean):void");
    }

    public final void b() {
        ac acVar;
        if (this.m != 3 || (acVar = this.l) == null) {
            return;
        }
        acVar.a();
    }

    private void g() {
        this.l.b();
    }

    private void h() {
        b(0.0f);
    }

    private void i() {
        j();
    }

    private void b(float f) {
        IAMapDelegate iAMapDelegate = this.e;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            iAMapDelegate.moveCamera(aj.c(f));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void j() {
        IAMapDelegate iAMapDelegate = this.e;
        if (iAMapDelegate == null) {
            return;
        }
        try {
            iAMapDelegate.moveCamera(aj.d(0.0f));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(Location location) {
        if (location == null) {
            return;
        }
        a(this.h.isMyLocationShowing());
        if (this.h.isMyLocationShowing()) {
            this.i = new LatLng(location.getLatitude(), location.getLongitude());
            this.j = location.getAccuracy();
            if (this.f == null && this.g == null) {
                l();
            }
            Circle circle = this.g;
            if (circle != null) {
                try {
                    double d = this.j;
                    if (d != -1.0d) {
                        circle.setRadius(d);
                    }
                } catch (Throwable th) {
                    iv.c(th, "MyLocationOverlay", "setCentAndRadius");
                    th.printStackTrace();
                }
            }
            c(location.getBearing());
            if (!this.i.equals(this.f.getPosition())) {
                a(this.i);
            } else {
                k();
            }
        }
    }

    private void c(float f) {
        if (this.q) {
            float f2 = f % 360.0f;
            if (f2 > 180.0f) {
                f2 -= 360.0f;
            } else if (f2 < -180.0f) {
                f2 += 360.0f;
            }
            Marker marker = this.f;
            if (marker != null) {
                marker.setRotateAngle(-f2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.i != null && this.o) {
            if (this.p && this.n) {
                return;
            }
            this.n = true;
            try {
                IPoint obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(this.i.longitude, this.i.latitude, obtain);
                this.e.animateCamera(aj.a(obtain));
            } catch (Throwable th) {
                iv.c(th, "MyLocationOverlay", "moveMapToLocation");
                th.printStackTrace();
            }
        }
    }

    private void l() {
        MyLocationStyle myLocationStyle = this.h;
        if (myLocationStyle == null) {
            MyLocationStyle myLocationStyle2 = new MyLocationStyle();
            this.h = myLocationStyle2;
            myLocationStyle2.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
        } else if (myLocationStyle.getMyLocationIcon() == null || this.h.getMyLocationIcon().getBitmap() == null) {
            this.h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
        }
        n();
    }

    public final void c() throws RemoteException {
        m();
        if (this.l != null) {
            g();
            this.l = null;
        }
    }

    private void m() {
        Circle circle = this.g;
        if (circle != null) {
            try {
                this.e.removeGLOverlay(circle.getId());
            } catch (Throwable th) {
                iv.c(th, "MyLocationOverlay", "locationIconRemove");
                th.printStackTrace();
            }
            this.g = null;
        }
        Marker marker = this.f;
        if (marker != null) {
            marker.remove();
            this.f = null;
            this.l.a((Marker) null);
        }
    }

    private void a(boolean z) {
        Circle circle = this.g;
        if (circle != null && circle.isVisible() != z) {
            this.g.setVisible(z);
        }
        Marker marker = this.f;
        if (marker == null || marker.isVisible() == z) {
            return;
        }
        this.f.setVisible(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0115 A[Catch: all -> 0x012a, TryCatch #0 {all -> 0x012a, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x0017, B:7:0x001c, B:9:0x002a, B:10:0x0035, B:12:0x0043, B:13:0x004e, B:15:0x005c, B:16:0x0067, B:18:0x006b, B:19:0x0070, B:20:0x007c, B:22:0x0081, B:23:0x0092, B:25:0x0096, B:27:0x00a4, B:29:0x00c5, B:31:0x00cd, B:34:0x00da, B:36:0x00e2, B:38:0x00fa, B:39:0x0111, B:41:0x0115, B:42:0x0106, B:43:0x00b4, B:44:0x011f), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void n() {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.cj.n():void");
    }

    public final void a(float f) {
        Marker marker = this.f;
        if (marker != null) {
            marker.setRotateAngle(f);
        }
    }

    public final String d() {
        Marker marker = this.f;
        if (marker != null) {
            return marker.getId();
        }
        return null;
    }

    public final String e() throws RemoteException {
        Circle circle = this.g;
        if (circle != null) {
            return circle.getId();
        }
        return null;
    }

    public final void f() {
        this.g = null;
        this.f = null;
    }

    private void a(LatLng latLng) {
        LatLng position = this.f.getPosition();
        if (position == null) {
            position = new LatLng(0.0d, 0.0d);
        }
        if (this.f937a == null) {
            this.f937a = new a();
        }
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator == null) {
            ValueAnimator ofObject = ValueAnimator.ofObject(new a(), position, latLng);
            this.b = ofObject;
            ofObject.addListener(this.c);
            this.b.addUpdateListener(this.d);
        } else {
            valueAnimator.setObjectValues(position, latLng);
            this.b.setEvaluator(this.f937a);
        }
        if (position.latitude == 0.0d && position.longitude == 0.0d) {
            this.b.setDuration(1L);
        } else {
            this.b.setDuration(1000L);
        }
        this.b.start();
    }

    public static final class a implements TypeEvaluator {
        @Override // android.animation.TypeEvaluator
        public final Object evaluate(float f, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            double d = f;
            return new LatLng(latLng.latitude + ((latLng2.latitude - latLng.latitude) * d), latLng.longitude + (d * (latLng2.longitude - latLng.longitude)));
        }
    }
}
