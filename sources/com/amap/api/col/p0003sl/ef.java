package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.amap.api.col.p0003sl.ed;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;

/* loaded from: classes2.dex */
public final class ef extends ViewGroup implements eg {

    /* renamed from: a, reason: collision with root package name */
    eh f997a;
    at b;
    private IAMapDelegate c;
    private IGlOverlayLayer d;
    private Context e;
    private ej f;
    private ee g;
    private ec h;
    private ei i;
    private eb j;
    private ed k;
    private ek l;
    private View m;
    private BasePointOverlay n;
    private Drawable o;
    private boolean p;
    private View q;
    private boolean r;
    private boolean s;
    private boolean t;

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final boolean isInfoWindowShown() {
        return false;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final View j() {
        return this;
    }

    static /* synthetic */ View f(ef efVar) {
        efVar.m = null;
        return null;
    }

    public ef(Context context, IAMapDelegate iAMapDelegate, IGlOverlayLayer iGlOverlayLayer) {
        super(context);
        this.o = null;
        int i = 1;
        this.p = true;
        this.s = true;
        this.t = true;
        try {
            this.d = iGlOverlayLayer;
            this.c = iAMapDelegate;
            this.e = context;
            this.f997a = new eh();
            this.j = new eb(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (this.c.getGLMapView() != null) {
                addView(this.c.getGLMapView(), 0, layoutParams);
            } else {
                i = 0;
            }
            addView(this.j, i, layoutParams);
            if (this.s) {
                return;
            }
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    private void a(Context context) {
        ej ejVar = new ej(context);
        this.f = ejVar;
        ejVar.c(this.t);
        this.i = new ei(context, this.c);
        this.k = new ed(context);
        this.l = new ek(context, this.c);
        this.g = new ee(context, this.c);
        this.h = new ec(context, this.c);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        addView(this.f, layoutParams);
        addView(this.i, layoutParams);
        addView(this.k, new ViewGroup.LayoutParams(-2, -2));
        addView(this.l, new a(new FPoint(0.0f, 0.0f), 83));
        addView(this.g, new a(FPoint.obtain(0.0f, 0.0f), 83));
        addView(this.h, new a(FPoint.obtain(0.0f, 0.0f), 51));
        this.h.setVisibility(8);
        this.c.setMapWidgetListener(new AMapWidgetListener() { // from class: com.amap.api.col.3sl.ef.1
            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public final void setFrontViewVisibility(boolean z) {
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public final void invalidateScaleView() {
                if (ef.this.i == null) {
                    return;
                }
                ef.this.i.post(new Runnable() { // from class: com.amap.api.col.3sl.ef.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ef.this.i.b();
                    }
                });
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public final void invalidateCompassView() {
                if (ef.this.h == null) {
                    return;
                }
                ef.this.h.post(new Runnable() { // from class: com.amap.api.col.3sl.ef.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        ef.this.h.b();
                    }
                });
            }

            @Override // com.autonavi.base.ae.gmap.listener.AMapWidgetListener
            public final void invalidateZoomController(final float f) {
                if (ef.this.l == null) {
                    return;
                }
                ef.this.l.post(new Runnable() { // from class: com.amap.api.col.3sl.ef.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        ef.this.l.a(f);
                    }
                });
            }
        });
        try {
            if (this.c.getUiSettings().isMyLocationButtonEnabled()) {
                return;
            }
            this.g.setVisibility(8);
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImpGLSurfaceView", "locationView gone");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(Boolean bool) {
        ed edVar = this.k;
        if (edVar == null) {
            this.f997a.a(this, bool);
        } else if (edVar != null && bool.booleanValue() && this.c.canShowIndoorSwitch()) {
            this.k.a(true);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void b(Boolean bool) {
        ek ekVar = this.l;
        if (ekVar == null) {
            this.f997a.a(this, bool);
        } else {
            ekVar.a(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void c(Boolean bool) {
        if (this.g == null) {
            this.f997a.a(this, bool);
        } else if (bool.booleanValue()) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void d(Boolean bool) {
        ec ecVar = this.h;
        if (ecVar == null) {
            this.f997a.a(this, bool);
        } else {
            ecVar.a(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void e(Boolean bool) {
        ei eiVar = this.i;
        if (eiVar == null) {
            this.f997a.a(this, bool);
        } else {
            eiVar.a(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void f(Boolean bool) {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, bool);
        } else {
            ejVar.setVisibility(bool.booleanValue() ? 0 : 8);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(String str, Boolean bool, Integer num) {
        if (this.f == null) {
            this.f997a.a(this, str, bool, num);
            return;
        }
        if (num.intValue() == 2) {
            this.f.b(bool.booleanValue());
        } else {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f.a(str, num.intValue());
            this.f.d(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(Float f) {
        ek ekVar = this.l;
        if (ekVar == null) {
            this.f997a.a(this, f);
        } else if (ekVar != null) {
            ekVar.a(f.floatValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(Integer num) {
        ek ekVar = this.l;
        if (ekVar == null) {
            this.f997a.a(this, num);
        } else if (ekVar != null) {
            ekVar.a(num.intValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void b(Integer num) {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, num);
        } else if (ejVar != null) {
            ejVar.a(num.intValue());
            this.f.postInvalidate();
            k();
        }
    }

    private void k() {
        ei eiVar = this.i;
        if (eiVar == null) {
            this.f997a.a(this, new Object[0]);
        } else {
            if (eiVar == null || eiVar.getVisibility() != 0) {
                return;
            }
            this.i.postInvalidate();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void c(Integer num) {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, num);
        } else if (ejVar != null) {
            ejVar.b(num.intValue());
            k();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void d(Integer num) {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, num);
        } else if (ejVar != null) {
            ejVar.c(num.intValue());
            k();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final float a(int i) {
        if (this.f == null) {
            return 0.0f;
        }
        k();
        return this.f.d(i);
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(Integer num, Float f) {
        ej ejVar = this.f;
        if (ejVar != null) {
            this.f997a.a(this, num, f);
        } else if (ejVar != null) {
            ejVar.a(num.intValue(), f.floatValue());
            k();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final void setInfoWindowAdapterManager(at atVar) {
        this.b = atVar;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final Point a() {
        ej ejVar = this.f;
        if (ejVar == null) {
            return null;
        }
        return ejVar.b();
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void g(Boolean bool) {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, bool);
            return;
        }
        if (ejVar != null && bool.booleanValue()) {
            this.f.a(true);
            return;
        }
        ej ejVar2 = this.f;
        if (ejVar2 != null) {
            ejVar2.a(false);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final boolean b() {
        ej ejVar = this.f;
        if (ejVar != null) {
            return ejVar.d();
        }
        return false;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void c() {
        ej ejVar = this.f;
        if (ejVar == null) {
            this.f997a.a(this, new Object[0]);
        } else if (ejVar != null) {
            ejVar.c();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final eb d() {
        return this.j;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final ed e() {
        return this.k;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final ej f() {
        return this.f;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(CameraPosition cameraPosition) {
        if (this.f == null) {
            this.f997a.a(this, cameraPosition);
            return;
        }
        if (this.c.getUiSettings().isLogoEnable()) {
            if (MapsInitializer.isLoadWorldGridMap() && cameraPosition.zoom >= 6.0f && !Cdo.a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                this.f.setVisibility(8);
            } else if (this.c.getMaskLayerType() == -1) {
                this.f.setVisibility(0);
            }
        }
    }

    private void l() {
        ek ekVar = this.l;
        if (ekVar != null) {
            ekVar.a();
        }
        ei eiVar = this.i;
        if (eiVar != null) {
            eiVar.a();
        }
        ej ejVar = this.f;
        if (ejVar != null) {
            ejVar.a();
        }
        ee eeVar = this.g;
        if (eeVar != null) {
            eeVar.a();
        }
        ec ecVar = this.h;
        if (ecVar != null) {
            ecVar.a();
        }
        ed edVar = this.k;
        if (edVar != null) {
            edVar.a();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(boolean z) {
        ej ejVar = this.f;
        if (ejVar != null) {
            ejVar.c(z);
        }
        this.t = z;
    }

    public static final class a extends ViewGroup.LayoutParams {

        /* renamed from: a, reason: collision with root package name */
        public FPoint f1003a;
        public boolean b;
        public int c;
        public int d;
        public int e;

        public a(FPoint fPoint, int i) {
            this(-2, -2, fPoint.x, fPoint.y, 0, 0, i);
        }

        public a(int i, int i2, float f, float f2, int i3, int i4, int i5) {
            super(i, i2);
            FPoint fPoint = new FPoint();
            this.f1003a = fPoint;
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = 51;
            fPoint.x = f;
            this.f1003a.y = f2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    if (childAt.getLayoutParams() instanceof a) {
                        a(childAt, (a) childAt.getLayoutParams());
                    } else {
                        a(childAt, childAt.getLayoutParams());
                    }
                }
            }
            ej ejVar = this.f;
            if (ejVar != null) {
                ejVar.c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, ViewGroup.LayoutParams layoutParams) {
        int[] iArr = new int[2];
        a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof ed) {
            a(view, iArr[0], iArr[1], 20, (this.c.getWaterMarkerPositon().y - 80) - iArr[1], 51);
            return;
        }
        a(view, iArr[0], iArr[1], 0, 0, 51);
    }

    private void a(View view, a aVar) {
        int[] iArr = new int[2];
        a(view, aVar.width, aVar.height, iArr);
        if (view instanceof ek) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), aVar.e);
            return;
        }
        if (view instanceof ee) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], aVar.e);
            return;
        }
        if (view instanceof ec) {
            a(view, iArr[0], iArr[1], 0, 0, aVar.e);
            return;
        }
        if (aVar.f1003a != null) {
            IPoint obtain = IPoint.obtain();
            MapConfig mapConfig = this.c.getMapConfig();
            GLMapState mapProjection = this.c.getMapProjection();
            if (mapConfig != null && mapProjection != null) {
                obtain.x = (int) aVar.f1003a.x;
                obtain.y = (int) aVar.f1003a.y;
            }
            obtain.x += aVar.c;
            obtain.y += aVar.d;
            a(view, iArr[0], iArr[1], obtain.x, obtain.y, aVar.e);
            obtain.recycle();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final void showInfoWindow(BasePointOverlay basePointOverlay) {
        if (basePointOverlay == null) {
            return;
        }
        try {
            at atVar = this.b;
            if (!(atVar != null && atVar.a() && basePointOverlay.getTitle() == null && basePointOverlay.getSnippet() == null) && basePointOverlay.isInfoWindowEnable()) {
                BasePointOverlay basePointOverlay2 = this.n;
                if (basePointOverlay2 != null && !basePointOverlay2.getId().equals(basePointOverlay.getId())) {
                    hideInfoWindow();
                }
                if (this.b != null) {
                    this.n = basePointOverlay;
                    this.r = true;
                    this.d.getNativeProperties(basePointOverlay.getId(), "setInfoWindowShown", new Object[]{Boolean.TRUE});
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final void showInfoWindow(BaseOverlayImp baseOverlayImp) {
        if (baseOverlayImp == null) {
            return;
        }
        try {
            at atVar = this.b;
            if (!(atVar != null && atVar.a() && baseOverlayImp.getTitle() == null && baseOverlayImp.getSnippet() == null) && baseOverlayImp.isInfoWindowEnable()) {
                BasePointOverlay basePointOverlay = this.n;
                if (basePointOverlay != null && !basePointOverlay.getId().equals(baseOverlayImp.getId())) {
                    hideInfoWindow();
                }
                if (this.b != null) {
                    baseOverlayImp.setInfoWindowShown(true);
                    this.r = true;
                }
            }
        } catch (Throwable unused) {
        }
    }

    private View a(BasePointOverlay basePointOverlay) throws RemoteException {
        View view;
        View view2;
        View view3 = null;
        if (basePointOverlay instanceof Marker) {
            try {
                if (this.o == null) {
                    this.o = dk.a(this.e, "infowindow_bg.9.png");
                }
            } catch (Throwable th) {
                iv.c(th, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            try {
                if (this.r) {
                    view = this.b.a(basePointOverlay);
                    if (view == null) {
                        try {
                            view = this.b.b(basePointOverlay);
                        } catch (Throwable th2) {
                            th = th2;
                            iv.c(th, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                            th.printStackTrace();
                            return view;
                        }
                    }
                    this.q = view;
                    this.r = false;
                } else {
                    view = this.q;
                }
                if (view != null) {
                    view3 = view;
                } else {
                    if (!this.b.a()) {
                        return null;
                    }
                    view3 = this.b.a(basePointOverlay);
                }
                if (view3 == null || view3.getBackground() != null) {
                    return view3;
                }
                view3.setBackground(this.o);
                return view3;
            } catch (Throwable th3) {
                th = th3;
                view = view3;
            }
        } else {
            try {
                if (this.o == null) {
                    this.o = dk.a(this.e, "infowindow_bg.9.png");
                }
            } catch (Throwable th4) {
                iv.c(th4, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th4.printStackTrace();
            }
            try {
                if (this.r) {
                    view2 = this.b.a(basePointOverlay);
                    if (view2 == null) {
                        try {
                            view2 = this.b.b(basePointOverlay);
                        } catch (Throwable th5) {
                            th = th5;
                            view3 = view2;
                            iv.c(th, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                            th.printStackTrace();
                            return view3;
                        }
                    }
                    this.q = view2;
                    this.r = false;
                } else {
                    view2 = this.q;
                }
                if (view2 != null) {
                    view3 = view2;
                } else {
                    if (!this.b.a()) {
                        return null;
                    }
                    view3 = this.b.a(basePointOverlay);
                }
                if (view3.getBackground() == null) {
                    view3.setBackground(this.o);
                }
                return view3;
            } catch (Throwable th6) {
                th = th6;
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final void redrawInfoWindow() {
        try {
            BasePointOverlay basePointOverlay = this.n;
            if (basePointOverlay != null && this.d.checkInBounds(basePointOverlay.getId())) {
                if (this.p) {
                    FPoint obtain = FPoint.obtain();
                    this.d.getMarkerInfoWindowOffset(this.n.getId(), obtain);
                    int i = (int) obtain.x;
                    int i2 = (int) (obtain.y + 2.0f);
                    obtain.recycle();
                    View a2 = a(this.n);
                    if (a2 == null) {
                        View view = this.m;
                        if (view == null || view.getVisibility() != 0) {
                            return;
                        }
                        hideInfoWindow();
                        return;
                    }
                    FPoint obtain2 = FPoint.obtain();
                    this.d.getOverlayScreenPos(this.n.getId(), obtain2);
                    a(a2, (int) obtain2.x, (int) obtain2.y, i, i2);
                    View view2 = this.m;
                    if (view2 != null) {
                        a aVar = (a) view2.getLayoutParams();
                        if (aVar != null) {
                            aVar.f1003a = FPoint.obtain(obtain2.x, obtain2.y);
                            aVar.c = i;
                            aVar.d = i2;
                        }
                        onLayout(false, 0, 0, 0, 0);
                        if (this.b.a()) {
                            this.b.a(this.n.getTitle(), this.n.getSnippet());
                        }
                        if (this.m.getVisibility() == 8) {
                            this.m.setVisibility(0);
                        }
                    }
                    obtain2.recycle();
                    return;
                }
                return;
            }
            View view3 = this.m;
            if (view3 == null || view3.getVisibility() != 0) {
                return;
            }
            this.m.setVisibility(8);
        } catch (Throwable th) {
            iv.c(th, "MapOverlayViewGroup", "redrawInfoWindow");
            dv.a(th);
        }
    }

    private void a(View view, int i, int i2, int i3, int i4) throws RemoteException {
        int i5;
        int i6;
        if (view == null) {
            return;
        }
        View view2 = this.m;
        if (view2 != null) {
            if (view == view2) {
                return;
            }
            view2.clearFocus();
            removeView(this.m);
        }
        this.m = view;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        this.m.setDrawingCacheEnabled(true);
        this.m.setDrawingCacheQuality(0);
        if (layoutParams != null) {
            int i7 = layoutParams.width;
            i6 = layoutParams.height;
            i5 = i7;
        } else {
            i5 = -2;
            i6 = -2;
        }
        addView(this.m, new a(i5, i6, i, i2, i3, i4, 81));
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final void hideInfoWindow() {
        try {
            IAMapDelegate iAMapDelegate = this.c;
            if (iAMapDelegate == null || iAMapDelegate.getMainHandler() == null) {
                return;
            }
            this.c.getMainHandler().post(new Runnable() { // from class: com.amap.api.col.3sl.ef.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (ef.this.m != null) {
                        ef.this.m.clearFocus();
                        ef efVar = ef.this;
                        efVar.removeView(efVar.m);
                        dv.a(ef.this.m.getBackground());
                        dv.a(ef.this.o);
                        ef.f(ef.this);
                    }
                }
            });
            BasePointOverlay basePointOverlay = this.n;
            if (basePointOverlay != null) {
                this.d.getNativeProperties(basePointOverlay.getId(), "setInfoWindowShown", new Object[]{Boolean.FALSE});
            }
            this.n = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, int i, int i2, int[] iArr) {
        View view2;
        if ((view instanceof ListView) && (view2 = (View) view.getParent()) != null) {
            iArr[0] = view2.getWidth();
            iArr[1] = view2.getHeight();
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7 = i5 & 7;
        int i8 = i5 & 112;
        if (i7 == 5) {
            i3 -= i;
        } else if (i7 == 1) {
            i3 -= i / 2;
        }
        if (i8 == 80) {
            i4 -= i2;
        } else {
            if (i8 == 17) {
                i6 = i2 / 2;
            } else if (i8 == 16) {
                i4 /= 2;
                i6 = i2 / 2;
            }
            i4 -= i6;
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
        if (view instanceof IGLSurfaceView) {
            this.c.changeSize(i, i2);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void g() {
        hideInfoWindow();
        dv.a(this.o);
        l();
        removeAllViews();
        this.q = null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public final boolean onInfoWindowTap(MotionEvent motionEvent) {
        View view = this.m;
        return (view == null || this.n == null || !dv.a(new Rect(view.getLeft(), this.m.getTop(), this.m.getRight(), this.m.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) ? false : true;
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(Canvas canvas) {
        Bitmap drawingCache;
        View view = this.m;
        if (view == null || this.n == null || (drawingCache = view.getDrawingCache(true)) == null) {
            return;
        }
        canvas.drawBitmap(drawingCache, this.m.getLeft(), this.m.getTop(), new Paint());
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void a(ed.a aVar) {
        ed edVar = this.k;
        if (edVar == null) {
            this.f997a.a(this, aVar);
        } else {
            edVar.a(aVar);
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void h() {
        ec ecVar = this.h;
        if (ecVar == null) {
            this.f997a.a(this, new Object[0]);
        } else {
            ecVar.b();
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void h(Boolean bool) {
        ee eeVar = this.g;
        if (eeVar == null) {
            this.f997a.a(this, bool);
        } else {
            eeVar.a(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void i(Boolean bool) {
        ed edVar = this.k;
        if (edVar == null) {
            this.f997a.a(this, bool);
        } else {
            edVar.a(bool.booleanValue());
        }
    }

    @Override // com.amap.api.col.p0003sl.eg
    public final void i() {
        Context context;
        if (!this.s || (context = this.e) == null) {
            return;
        }
        a(context);
        eh ehVar = this.f997a;
        if (ehVar != null) {
            ehVar.a();
        }
    }
}
