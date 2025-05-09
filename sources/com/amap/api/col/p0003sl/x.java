package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.amap.api.col.p0003sl.an;
import com.amap.api.col.p0003sl.ao;
import com.amap.api.col.p0003sl.aq;
import com.amap.api.col.p0003sl.ar;
import com.amap.api.maps.model.AMapGestureListener;
import com.autonavi.base.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.base.amap.mapcore.message.ScaleGestureMapMessage;

/* loaded from: classes2.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    IAMapDelegate f1374a;
    Context b;
    GestureDetector c;
    public AMapGestureListener d;
    private aq e;
    private ao f;
    private an g;
    private ar h;
    private int r;
    private int s;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private Handler t = new Handler(Looper.getMainLooper());

    static /* synthetic */ int g(x xVar) {
        int i = xVar.k;
        xVar.k = i + 1;
        return i;
    }

    static /* synthetic */ int h(x xVar) {
        int i = xVar.l;
        xVar.l = i + 1;
        return i;
    }

    static /* synthetic */ int l(x xVar) {
        int i = xVar.j;
        xVar.j = i + 1;
        return i;
    }

    static /* synthetic */ int m(x xVar) {
        int i = xVar.m;
        xVar.m = i + 1;
        return i;
    }

    static /* synthetic */ boolean n(x xVar) {
        xVar.q = true;
        return true;
    }

    public final void a(AMapGestureListener aMapGestureListener) {
        this.d = aMapGestureListener;
    }

    public final void a() {
        this.j = 0;
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
    }

    public x(IAMapDelegate iAMapDelegate) {
        byte b2 = 0;
        this.b = iAMapDelegate.getContext();
        this.f1374a = iAMapDelegate;
        a aVar = new a(this, b2);
        GestureDetector gestureDetector = new GestureDetector(this.b, aVar, this.t);
        this.c = gestureDetector;
        gestureDetector.setOnDoubleTapListener(aVar);
        this.e = new aq(this.b, new d(this, b2));
        this.f = new ao(this.b, new c(this, b2));
        this.g = new an(this.b, new b(this, b2));
        this.h = new ar(this.b, new e(this, b2));
    }

    public final void a(int i, int i2) {
        this.r = i;
        this.s = i2;
        aq aqVar = this.e;
        if (aqVar != null) {
            aqVar.a(i, i2);
        }
        ao aoVar = this.f;
        if (aoVar != null) {
            aoVar.a(i, i2);
        }
        an anVar = this.g;
        if (anVar != null) {
            anVar.a(i, i2);
        }
        ar arVar = this.h;
        if (arVar != null) {
            arVar.a(i, i2);
        }
    }

    public final int b() {
        return this.r;
    }

    public final int c() {
        return this.s;
    }

    public final boolean a(MotionEvent motionEvent) {
        if (this.n < motionEvent.getPointerCount()) {
            this.n = motionEvent.getPointerCount();
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.p = false;
            this.q = false;
        }
        if (motionEvent.getAction() == 6 && motionEvent.getPointerCount() > 0) {
            this.p = true;
        }
        if (this.o && this.n >= 2) {
            this.o = false;
        }
        try {
            int[] iArr = {0, 0};
            IAMapDelegate iAMapDelegate = this.f1374a;
            if (iAMapDelegate != null && iAMapDelegate.getGLMapView() != null) {
                this.f1374a.getGLMapView().getLocationOnScreen(iArr);
            }
            if (this.d != null) {
                if (motionEvent.getAction() == 0) {
                    this.d.onDown(motionEvent.getX(), motionEvent.getY());
                } else if (motionEvent.getAction() == 1) {
                    this.d.onUp(motionEvent.getX(), motionEvent.getY());
                }
            }
            this.c.onTouchEvent(motionEvent);
            this.g.b(motionEvent, iArr[0], iArr[1]);
            if (!this.i || this.m <= 0) {
                this.h.b(motionEvent, iArr[0], iArr[1]);
                if (!this.o) {
                    this.e.a(motionEvent);
                    this.f.b(motionEvent, iArr[0], iArr[1]);
                }
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    final class a implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

        /* renamed from: a, reason: collision with root package name */
        float f1375a;
        long b;
        private int d;
        private EAMapPlatformGestureInfo e;

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        private a() {
            this.d = 0;
            this.f1375a = 0.0f;
            this.e = new EAMapPlatformGestureInfo();
            this.b = 0L;
        }

        /* synthetic */ a(x xVar, byte b) {
            this();
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            x.this.o = false;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (x.this.d != null) {
                x.this.d.onFling(f, f2);
            }
            try {
                if (x.this.f1374a.getUiSettings().isScrollGesturesEnabled() && x.this.m <= 0 && x.this.k <= 0 && x.this.l == 0 && !x.this.q) {
                    this.e.mGestureState = 3;
                    this.e.mGestureType = 3;
                    this.e.mLocation = new float[]{motionEvent2.getX(), motionEvent2.getY()};
                    int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.e);
                    x.this.f1374a.onFling();
                    x.this.f1374a.getGLMapEngine().startMapSlidAnim(engineIDWithGestureInfo, new Point((int) motionEvent2.getX(), (int) motionEvent2.getY()), f, f2);
                }
                return true;
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onFling");
                th.printStackTrace();
                return true;
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            if (x.this.n == 1) {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                x.this.f1374a.onLongPress(x.this.f1374a.getEngineIDWithGestureInfo(this.e), motionEvent);
                if (x.this.d != null) {
                    x.this.d.onLongPress(motionEvent.getX(), motionEvent.getY());
                }
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (x.this.d == null) {
                return false;
            }
            x.this.d.onScroll(f, f2);
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public final void onShowPress(MotionEvent motionEvent) {
            try {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                x.this.f1374a.getGLMapEngine().clearAnimations(x.this.f1374a.getEngineIDWithGestureInfo(this.e), false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTap(MotionEvent motionEvent) {
            x.this.c.setIsLongpressEnabled(false);
            this.d = motionEvent.getPointerCount();
            if (x.this.d != null) {
                x.this.d.onDoubleTap(motionEvent.getX(), motionEvent.getY());
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.d < motionEvent.getPointerCount()) {
                this.d = motionEvent.getPointerCount();
            }
            int action = motionEvent.getAction() & 255;
            if (this.d != 1) {
                return false;
            }
            try {
                if (!x.this.f1374a.getUiSettings().isZoomGesturesEnabled()) {
                    return false;
                }
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onDoubleTapEvent");
                th.printStackTrace();
            }
            if (action == 0) {
                this.e.mGestureState = 1;
                this.e.mGestureType = 9;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.e);
                this.f1375a = motionEvent.getY();
                x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(100, 1.0f, 0, 0));
                this.b = SystemClock.uptimeMillis();
                return true;
            }
            if (action == 2) {
                x.this.o = true;
                float y = this.f1375a - motionEvent.getY();
                if (Math.abs(y) < 20.0f) {
                    return true;
                }
                this.e.mGestureState = 2;
                this.e.mGestureType = 9;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                x.this.f1374a.addGestureMapMessage(x.this.f1374a.getEngineIDWithGestureInfo(this.e), ScaleGestureMapMessage.obtain(101, (y * 4.0f) / x.this.f1374a.getMapHeight(), 0, 0));
                this.f1375a = motionEvent.getY();
                return true;
            }
            this.e.mGestureState = 3;
            this.e.mGestureType = 9;
            this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            int engineIDWithGestureInfo2 = x.this.f1374a.getEngineIDWithGestureInfo(this.e);
            x.this.c.setIsLongpressEnabled(true);
            x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo2, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
            if (action != 1) {
                x.this.o = false;
                return true;
            }
            x.this.f1374a.setGestureStatus(engineIDWithGestureInfo2, 3);
            long uptimeMillis = SystemClock.uptimeMillis();
            long j = this.b;
            if (x.this.o && uptimeMillis - j >= 200) {
                x.this.o = false;
                return true;
            }
            return x.this.f1374a.onDoubleTap(engineIDWithGestureInfo2, motionEvent);
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (x.this.n != 1) {
                return false;
            }
            this.e.mGestureState = 3;
            this.e.mGestureType = 8;
            this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.e);
            if (x.this.d != null) {
                try {
                    x.this.d.onSingleTap(motionEvent.getX(), motionEvent.getY());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return x.this.f1374a.onSingleTapConfirmed(engineIDWithGestureInfo, motionEvent);
        }
    }

    final class d extends aq.a {
        private boolean b;
        private boolean c;
        private boolean d;
        private Point e;
        private float[] f;
        private float g;
        private float[] h;
        private float i;
        private EAMapPlatformGestureInfo j;

        private d() {
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = new Point();
            this.f = new float[10];
            this.g = 0.0f;
            this.h = new float[10];
            this.i = 0.0f;
            this.j = new EAMapPlatformGestureInfo();
        }

        /* synthetic */ d(x xVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.aq.a
        public final boolean a(aq aqVar) {
            float j;
            this.j.mGestureState = 2;
            this.j.mGestureType = 4;
            boolean z = false;
            boolean z2 = true;
            this.j.mLocation = new float[]{aqVar.a().getX(), aqVar.a().getY()};
            int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.j);
            float h = aqVar.h();
            float i = aqVar.i();
            int b = (int) aqVar.b();
            int c = (int) aqVar.c();
            float abs = Math.abs(b - this.e.x);
            float abs2 = Math.abs(c - this.e.y);
            this.e.x = b;
            this.e.y = c;
            float log = (float) Math.log(h);
            if (x.this.k <= 0 && Math.abs(log) > 0.2f) {
                this.d = true;
            }
            try {
                if (x.this.f1374a.getUiSettings().isZoomGesturesEnabled()) {
                    if (!this.b && 0.06f < Math.abs(log)) {
                        this.b = true;
                    }
                    if (this.b) {
                        if (0.01f < Math.abs(log)) {
                            if (abs > 2.0f || abs2 > 2.0f) {
                                try {
                                    if (Math.abs(log) < 0.02f) {
                                        z = true;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    z = true;
                                    iv.c(th, "GLMapGestrureDetector", "onScaleRotate");
                                    th.printStackTrace();
                                    if (x.this.f1374a.getUiSettings().isRotateGesturesEnabled()) {
                                        j = aqVar.j();
                                        if (!this.c) {
                                            this.c = true;
                                        }
                                        if (this.c) {
                                            float f = j / i;
                                            this.i = f;
                                            this.h[x.this.l % 10] = Math.abs(f);
                                            x.h(x.this);
                                            x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, RotateGestureMapMessage.obtain(101, j, b, c));
                                            try {
                                                x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 6);
                                                return true;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                iv.c(th, "GLMapGestrureDetector", "onScaleRotate");
                                                th.printStackTrace();
                                                return z2;
                                            }
                                        }
                                    }
                                    return z;
                                }
                            }
                            if (i > 0.0f) {
                                float f2 = log / i;
                                this.g = f2;
                                this.f[x.this.k % 10] = Math.abs(f2);
                                x.g(x.this);
                                x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(101, log, b, c));
                                if (log > 0.0f) {
                                    x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 1);
                                } else {
                                    x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 2);
                                }
                            }
                            z = true;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
            try {
                if (x.this.f1374a.getUiSettings().isRotateGesturesEnabled() && !x.this.f1374a.isLockMapAngle(engineIDWithGestureInfo) && !this.d) {
                    j = aqVar.j();
                    if (!this.c && Math.abs(j) >= 4.0f) {
                        this.c = true;
                    }
                    if (this.c && 1.0f < Math.abs(j) && ((abs <= 4.0f && abs2 <= 4.0f) || Math.abs(j) >= 2.0f)) {
                        float f3 = j / i;
                        this.i = f3;
                        this.h[x.this.l % 10] = Math.abs(f3);
                        x.h(x.this);
                        x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, RotateGestureMapMessage.obtain(101, j, b, c));
                        x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 6);
                        return true;
                    }
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                z2 = z;
            }
        }

        @Override // com.amap.api.col.3sl.aq.a
        public final boolean b(aq aqVar) {
            this.j.mGestureState = 1;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{aqVar.a().getX(), aqVar.a().getY()};
            int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.j);
            int b = (int) aqVar.b();
            int c = (int) aqVar.c();
            this.d = false;
            this.e.x = b;
            this.e.y = c;
            this.b = false;
            this.c = false;
            x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(100, 1.0f, b, c));
            try {
                if (x.this.f1374a.getUiSettings().isRotateGesturesEnabled() && !x.this.f1374a.isLockMapAngle(engineIDWithGestureInfo)) {
                    x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, RotateGestureMapMessage.obtain(100, x.this.f1374a.getMapAngle(engineIDWithGestureInfo), b, c));
                }
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onScaleRotateBegin");
                th.printStackTrace();
            }
            return true;
        }

        @Override // com.amap.api.col.3sl.aq.a
        public final void c(aq aqVar) {
            float f;
            float f2;
            float f3;
            this.j.mGestureState = 3;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{aqVar.a().getX(), aqVar.a().getY()};
            int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.j);
            this.d = false;
            x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
            if (x.this.k > 0) {
                int i = x.this.k > 10 ? 10 : x.this.k;
                float f4 = 0.0f;
                for (int i2 = 0; i2 < 10; i2++) {
                    float[] fArr = this.f;
                    f4 += fArr[i2];
                    fArr[i2] = 0.0f;
                }
                float f5 = f4 / i;
                if (0.004f <= f5) {
                    float f6 = f5 * 300.0f;
                    if (f6 >= 1.5f) {
                        f6 = 1.5f;
                    }
                    if (this.g < 0.0f) {
                        f6 = -f6;
                    }
                    f3 = x.this.f1374a.getPreciseLevel(engineIDWithGestureInfo) + f6;
                } else {
                    f3 = -9999.0f;
                }
                this.g = 0.0f;
                f = f3;
            } else {
                f = -9999.0f;
            }
            if (x.this.f1374a.isLockMapAngle(engineIDWithGestureInfo)) {
                f2 = -9999.0f;
            } else {
                try {
                    if (x.this.f1374a.getUiSettings().isRotateGesturesEnabled()) {
                        x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, RotateGestureMapMessage.obtain(102, x.this.f1374a.getMapAngle(engineIDWithGestureInfo), 0, 0));
                    }
                } catch (Throwable th) {
                    iv.c(th, "GLMapGestrureDetector", "onScaleRotateEnd");
                    th.printStackTrace();
                }
                if (x.this.l > 0) {
                    x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 6);
                    int i3 = x.this.l > 10 ? 10 : x.this.l;
                    float f7 = 0.0f;
                    for (int i4 = 0; i4 < 10; i4++) {
                        float[] fArr2 = this.h;
                        f7 += fArr2[i4];
                        fArr2[i4] = 0.0f;
                    }
                    float f8 = f7 / i3;
                    if (0.1f <= f8) {
                        float f9 = f8 * 200.0f;
                        int mapAngle = (int) x.this.f1374a.getMapAngle(engineIDWithGestureInfo);
                        if (f9 >= 60.0f) {
                            f9 = 60.0f;
                        }
                        if (this.i < 0.0f) {
                            f9 = -f9;
                        }
                        f2 = ((int) ((mapAngle % 360) + f9)) % 360;
                        this.g = 0.0f;
                    }
                }
                f2 = -9999.0f;
                this.g = 0.0f;
            }
            if (f == -9999.0f && f2 == -9999.0f) {
                return;
            }
            x.this.f1374a.getGLMapEngine().startPivotZoomRotateAnim(engineIDWithGestureInfo, this.e, f, (int) f2, 500);
        }
    }

    final class c implements ao.a {
        private EAMapPlatformGestureInfo b;

        private c() {
            this.b = new EAMapPlatformGestureInfo();
        }

        /* synthetic */ c(x xVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.ao.a
        public final boolean a(ao aoVar) {
            if (x.this.i) {
                return true;
            }
            try {
                if (x.this.f1374a.getUiSettings().isScrollGesturesEnabled()) {
                    if (!x.this.p) {
                        this.b.mGestureState = 2;
                        this.b.mGestureType = 3;
                        this.b.mLocation = new float[]{aoVar.c().getX(), aoVar.c().getY()};
                        int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.b);
                        PointF d = aoVar.d();
                        float f = x.this.j == 0 ? 4.0f : 1.0f;
                        if (Math.abs(d.x) <= f && Math.abs(d.y) <= f) {
                            return false;
                        }
                        if (x.this.j == 0) {
                            x.this.f1374a.getGLMapEngine().clearAnimations(engineIDWithGestureInfo, false);
                        }
                        x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, MoveGestureMapMessage.obtain(101, d.x, d.y, aoVar.c().getX(), aoVar.c().getY()));
                        x.l(x.this);
                    }
                }
                return true;
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onMove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.col.3sl.ao.a
        public final boolean b(ao aoVar) {
            try {
                if (!x.this.f1374a.getUiSettings().isScrollGesturesEnabled()) {
                    return true;
                }
                this.b.mGestureState = 1;
                this.b.mGestureType = 3;
                this.b.mLocation = new float[]{aoVar.c().getX(), aoVar.c().getY()};
                x.this.f1374a.addGestureMapMessage(x.this.f1374a.getEngineIDWithGestureInfo(this.b), MoveGestureMapMessage.obtain(100, 0.0f, 0.0f, aoVar.c().getX(), aoVar.c().getY()));
                return true;
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onMoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.col.3sl.ao.a
        public final void c(ao aoVar) {
            try {
                if (x.this.f1374a.getUiSettings().isScrollGesturesEnabled()) {
                    this.b.mGestureState = 3;
                    this.b.mGestureType = 3;
                    this.b.mLocation = new float[]{aoVar.c().getX(), aoVar.c().getY()};
                    int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.b);
                    if (x.this.j > 0) {
                        x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 5);
                    }
                    x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, MoveGestureMapMessage.obtain(102, 0.0f, 0.0f, aoVar.c().getX(), aoVar.c().getY()));
                }
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onMoveEnd");
                th.printStackTrace();
            }
        }
    }

    final class b implements an.a {
        private EAMapPlatformGestureInfo b;

        private b() {
            this.b = new EAMapPlatformGestureInfo();
        }

        /* synthetic */ b(x xVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.an.a
        public final boolean a(an anVar) {
            this.b.mGestureState = 2;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{anVar.c().getX(), anVar.c().getY()};
            try {
                if (!x.this.f1374a.getUiSettings().isTiltGesturesEnabled()) {
                    return true;
                }
                int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.b);
                if (x.this.f1374a.isLockMapCameraDegree(engineIDWithGestureInfo) || x.this.l > 3) {
                    return false;
                }
                float f = anVar.d().x;
                float f2 = anVar.d().y;
                if (!x.this.i) {
                    PointF a2 = anVar.a(0);
                    PointF a3 = anVar.a(1);
                    if (((a2.y > 10.0f && a3.y > 10.0f) || (a2.y < -10.0f && a3.y < -10.0f)) && Math.abs(f2) > 10.0f && Math.abs(f) < 10.0f) {
                        x.this.i = true;
                    }
                }
                if (x.this.i) {
                    x.this.i = true;
                    float f3 = f2 / 6.0f;
                    if (Math.abs(f3) > 1.0f) {
                        x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(101, f3));
                        x.m(x.this);
                    }
                }
                return true;
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onHove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.col.3sl.an.a
        public final boolean b(an anVar) {
            this.b.mGestureState = 1;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{anVar.c().getX(), anVar.c().getY()};
            try {
                if (!x.this.f1374a.getUiSettings().isTiltGesturesEnabled()) {
                    return true;
                }
                int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.b);
                if (x.this.f1374a.isLockMapCameraDegree(engineIDWithGestureInfo)) {
                    return false;
                }
                x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(100, x.this.f1374a.getCameraDegree(engineIDWithGestureInfo)));
                return true;
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onHoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.amap.api.col.3sl.an.a
        public final void c(an anVar) {
            this.b.mGestureState = 3;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{anVar.c().getX(), anVar.c().getY()};
            try {
                if (x.this.f1374a.getUiSettings().isTiltGesturesEnabled()) {
                    int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.b);
                    if (x.this.f1374a.isLockMapCameraDegree(engineIDWithGestureInfo)) {
                        return;
                    }
                    if (x.this.f1374a.getCameraDegree(engineIDWithGestureInfo) >= 0.0f && x.this.m > 0) {
                        x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 7);
                    }
                    x.this.i = false;
                    x.this.f1374a.addGestureMapMessage(engineIDWithGestureInfo, HoverGestureMapMessage.obtain(102, x.this.f1374a.getCameraDegree(engineIDWithGestureInfo)));
                }
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onHoveEnd");
                th.printStackTrace();
            }
        }
    }

    final class e extends ar.b {

        /* renamed from: a, reason: collision with root package name */
        EAMapPlatformGestureInfo f1379a;

        private e() {
            this.f1379a = new EAMapPlatformGestureInfo();
        }

        /* synthetic */ e(x xVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.ar.b, com.amap.api.col.3sl.ar.a
        public final void a(ar arVar) {
            try {
                if (x.this.f1374a.getUiSettings().isZoomGesturesEnabled() && Math.abs(arVar.d()) <= 10.0f && Math.abs(arVar.e()) <= 10.0f && arVar.b() < 200) {
                    x.n(x.this);
                    this.f1379a.mGestureState = 2;
                    this.f1379a.mGestureType = 2;
                    this.f1379a.mLocation = new float[]{arVar.c().getX(), arVar.c().getY()};
                    int engineIDWithGestureInfo = x.this.f1374a.getEngineIDWithGestureInfo(this.f1379a);
                    x.this.f1374a.setGestureStatus(engineIDWithGestureInfo, 4);
                    x.this.f1374a.zoomOut(engineIDWithGestureInfo);
                }
            } catch (Throwable th) {
                iv.c(th, "GLMapGestrureDetector", "onZoomOut");
                th.printStackTrace();
            }
        }
    }
}
