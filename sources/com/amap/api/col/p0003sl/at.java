package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.InfoWindowParams;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction;

/* loaded from: classes2.dex */
public final class at {
    Context c;
    private View e;
    private TextView f;
    private TextView g;
    private IInfoWindowAction i;
    private IInfoWindowAction j;
    private BaseOverlay k;

    /* renamed from: a, reason: collision with root package name */
    AMap.InfoWindowAdapter f900a = null;
    AMap.CommonInfoWindowAdapter b = null;
    private boolean d = true;
    private Drawable h = null;
    private AMap.InfoWindowAdapter l = new AMap.InfoWindowAdapter() { // from class: com.amap.api.col.3sl.at.1
        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public final View getInfoContents(Marker marker) {
            return null;
        }

        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public final View getInfoWindow(Marker marker) {
            try {
                if (at.this.h == null) {
                    at atVar = at.this;
                    atVar.h = dk.a(atVar.c, "infowindow_bg.9.png");
                }
                if (at.this.e == null) {
                    at.this.e = new LinearLayout(at.this.c);
                    at.this.e.setBackground(at.this.h);
                    at.this.f = new TextView(at.this.c);
                    at.this.f.setText(marker.getTitle());
                    at.this.f.setTextColor(-16777216);
                    at.this.g = new TextView(at.this.c);
                    at.this.g.setTextColor(-16777216);
                    at.this.g.setText(marker.getSnippet());
                    ((LinearLayout) at.this.e).setOrientation(1);
                    ((LinearLayout) at.this.e).addView(at.this.f);
                    ((LinearLayout) at.this.e).addView(at.this.g);
                }
            } catch (Throwable th) {
                iv.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            return at.this.e;
        }
    };
    private AMap.CommonInfoWindowAdapter m = new AMap.CommonInfoWindowAdapter() { // from class: com.amap.api.col.3sl.at.2
        private InfoWindowParams b = null;

        @Override // com.amap.api.maps.AMap.CommonInfoWindowAdapter
        public final InfoWindowParams getInfoWindowParams(BasePointOverlay basePointOverlay) {
            try {
                if (this.b == null) {
                    this.b = new InfoWindowParams();
                    if (at.this.h == null) {
                        at atVar = at.this;
                        atVar.h = dk.a(atVar.c, "infowindow_bg.9.png");
                    }
                    at.this.e = new LinearLayout(at.this.c);
                    at.this.e.setBackground(at.this.h);
                    at.this.f = new TextView(at.this.c);
                    at.this.f.setText("标题");
                    at.this.f.setTextColor(-16777216);
                    at.this.g = new TextView(at.this.c);
                    at.this.g.setTextColor(-16777216);
                    at.this.g.setText("内容");
                    ((LinearLayout) at.this.e).setOrientation(1);
                    ((LinearLayout) at.this.e).addView(at.this.f);
                    ((LinearLayout) at.this.e).addView(at.this.g);
                    this.b.setInfoWindowType(2);
                    this.b.setInfoWindow(at.this.e);
                }
                return this.b;
            } catch (Throwable th) {
                iv.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
                return null;
            }
        }
    };

    public at(Context context) {
        this.c = context;
    }

    public final void a(IInfoWindowAction iInfoWindowAction) {
        synchronized (this) {
            this.i = iInfoWindowAction;
            if (iInfoWindowAction != null) {
                iInfoWindowAction.setInfoWindowAdapterManager(this);
            }
        }
    }

    public final void b(IInfoWindowAction iInfoWindowAction) {
        synchronized (this) {
            this.j = iInfoWindowAction;
            if (iInfoWindowAction != null) {
                iInfoWindowAction.setInfoWindowAdapterManager(this);
            }
        }
    }

    public final boolean a() {
        boolean z;
        synchronized (this) {
            z = this.d;
        }
        return z;
    }

    public final void a(String str, String str2) {
        TextView textView = this.f;
        if (textView != null) {
            textView.requestLayout();
            this.f.setText(str);
        }
        TextView textView2 = this.g;
        if (textView2 != null) {
            textView2.requestLayout();
            this.g.setText(str2);
        }
        View view = this.e;
        if (view != null) {
            view.requestLayout();
        }
    }

    public final void a(AMap.InfoWindowAdapter infoWindowAdapter) {
        synchronized (this) {
            this.f900a = infoWindowAdapter;
            this.b = null;
            if (b(infoWindowAdapter)) {
                this.f900a = this.l;
                this.d = true;
            } else {
                this.d = false;
            }
            IInfoWindowAction iInfoWindowAction = this.j;
            if (iInfoWindowAction != null) {
                iInfoWindowAction.hideInfoWindow();
            }
            IInfoWindowAction iInfoWindowAction2 = this.i;
            if (iInfoWindowAction2 != null) {
                iInfoWindowAction2.hideInfoWindow();
            }
        }
    }

    private static boolean b(AMap.InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            return true;
        }
        Marker marker = new Marker(null, new MarkerOptions(), "check");
        return infoWindowAdapter.getInfoWindow(marker) == null && infoWindowAdapter.getInfoContents(marker) == null;
    }

    public final void a(AMap.CommonInfoWindowAdapter commonInfoWindowAdapter) {
        synchronized (this) {
            this.b = commonInfoWindowAdapter;
            this.f900a = null;
            if (commonInfoWindowAdapter == null) {
                this.b = this.m;
                this.d = true;
            } else {
                this.d = false;
            }
            IInfoWindowAction iInfoWindowAction = this.j;
            if (iInfoWindowAction != null) {
                iInfoWindowAction.hideInfoWindow();
            }
            IInfoWindowAction iInfoWindowAction2 = this.i;
            if (iInfoWindowAction2 != null) {
                iInfoWindowAction2.hideInfoWindow();
            }
        }
    }

    private static void a(View view, BasePointOverlay basePointOverlay) {
        if (view == null || basePointOverlay == null || basePointOverlay.getPosition() == null || !dj.c()) {
            return;
        }
        String b = dv.b(view);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        dj.a().a(basePointOverlay.getPosition(), b, "");
    }

    public final View a(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f900a;
        if (infoWindowAdapter != null) {
            View infoWindow = infoWindowAdapter.getInfoWindow((Marker) basePointOverlay);
            a(infoWindow, basePointOverlay);
            return infoWindow;
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.b;
        if (commonInfoWindowAdapter != null && (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) != null) {
            View infoWindow2 = infoWindowParams.getInfoWindow();
            a(infoWindow2, basePointOverlay);
            return infoWindow2;
        }
        InfoWindowParams infoWindowParams2 = this.m.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams2 != null) {
            return infoWindowParams2.getInfoWindow();
        }
        return null;
    }

    public final View b(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f900a;
        if (infoWindowAdapter != null) {
            View infoContents = infoWindowAdapter.getInfoContents((Marker) basePointOverlay);
            a(infoContents, basePointOverlay);
            return infoContents;
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.b;
        if (commonInfoWindowAdapter != null && (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) != null) {
            View infoContents2 = infoWindowParams.getInfoContents();
            a(infoContents2, basePointOverlay);
            return infoContents2;
        }
        InfoWindowParams infoWindowParams2 = this.m.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams2 != null) {
            return infoWindowParams2.getInfoContents();
        }
        return null;
    }

    public final long c(BasePointOverlay basePointOverlay) {
        InfoWindowParams infoWindowParams;
        AMap.InfoWindowAdapter infoWindowAdapter = this.f900a;
        if (infoWindowAdapter != null && (infoWindowAdapter instanceof AMap.ImageInfoWindowAdapter)) {
            return ((AMap.ImageInfoWindowAdapter) infoWindowAdapter).getInfoWindowUpdateTime();
        }
        AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.b;
        if (commonInfoWindowAdapter == null || (infoWindowParams = commonInfoWindowAdapter.getInfoWindowParams(basePointOverlay)) == null) {
            return 0L;
        }
        return infoWindowParams.getInfoWindowUpdateTime();
    }

    public final void b() {
        IInfoWindowAction d = d();
        if (d != null) {
            d.redrawInfoWindow();
        }
    }

    private IInfoWindowAction d() {
        synchronized (this) {
            AMap.InfoWindowAdapter infoWindowAdapter = this.f900a;
            if (infoWindowAdapter != null) {
                if (infoWindowAdapter instanceof AMap.ImageInfoWindowAdapter) {
                    return this.j;
                }
                if (infoWindowAdapter instanceof AMap.MultiPositionInfoWindowAdapter) {
                    return this.j;
                }
            }
            AMap.CommonInfoWindowAdapter commonInfoWindowAdapter = this.b;
            if (commonInfoWindowAdapter != null && commonInfoWindowAdapter.getInfoWindowParams(null).getInfoWindowType() == 1) {
                return this.j;
            }
            return this.i;
        }
    }

    public final BaseOverlay a(MotionEvent motionEvent) {
        IInfoWindowAction d = d();
        if (d == null || !d.onInfoWindowTap(motionEvent)) {
            return null;
        }
        return this.k;
    }

    public final void c() {
        IInfoWindowAction d = d();
        if (d != null) {
            d.hideInfoWindow();
        }
    }

    public final void a(BaseOverlayImp baseOverlayImp) throws RemoteException {
        IInfoWindowAction d = d();
        if (d != null) {
            d.showInfoWindow(baseOverlayImp);
        }
    }

    public final void a(BaseOverlay baseOverlay) throws RemoteException {
        IInfoWindowAction d = d();
        if (d == null || !(baseOverlay instanceof BasePointOverlay)) {
            return;
        }
        d.showInfoWindow((BasePointOverlay) baseOverlay);
        this.k = baseOverlay;
    }
}
