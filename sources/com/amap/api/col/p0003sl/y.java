package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.ImageOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.base.ae.gmap.bean.MultiPointItemHitTest;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer;
import com.autonavi.base.amap.mapcore.FPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class y implements IGlOverlayLayer, AMapNativeGlOverlayLayer.NativeFunCallListener {

    /* renamed from: a, reason: collision with root package name */
    IAMapDelegate f1380a;
    private Context d;
    private ck j;
    private int e = 0;
    private final Object f = new Object();
    private BitmapDescriptor k = null;
    private BitmapDescriptor l = null;
    private BitmapDescriptor m = null;
    private BitmapDescriptor n = null;
    private BitmapDescriptor o = null;
    private BitmapDescriptor p = null;
    private BitmapDescriptor q = null;
    private BitmapDescriptor r = null;
    boolean b = false;
    List<String> c = new ArrayList();
    private final Map<String, BaseOverlay> h = new HashMap();
    private ArrayList<Pair<BaseOverlay, BaseOptions>> i = new ArrayList<>();
    private AMapNativeGlOverlayLayer g = new AMapNativeGlOverlayLayer();

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void changeOverlayIndex() {
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getInfoWindowClick(String str) {
        return null;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getOverturnInfoWindow(String str) {
        return null;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getOverturnInfoWindowClick(String str) {
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean removeOverlay(String str, boolean z) {
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final String createId(String str) {
        String str2;
        synchronized (this.f) {
            this.e++;
            str2 = str + this.e;
        }
        return str2;
    }

    public y(IAMapDelegate iAMapDelegate, Context context) {
        this.f1380a = iAMapDelegate;
        this.d = context;
        this.j = new ck(iAMapDelegate);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void clear(String... strArr) {
        int i;
        synchronized (this) {
            try {
                AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
                if (aMapNativeGlOverlayLayer != null && strArr != null) {
                    aMapNativeGlOverlayLayer.clear(strArr);
                }
                synchronized (this.h) {
                    if (strArr != null) {
                        Iterator<Map.Entry<String, BaseOverlay>> it = this.h.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, BaseOverlay> next = it.next();
                            int length = strArr.length;
                            while (true) {
                                if (i < length) {
                                    String str = strArr[i];
                                    i = (str == null || !str.equals(next.getKey())) ? i + 1 : 0;
                                } else {
                                    it.remove();
                                    break;
                                }
                            }
                        }
                    } else {
                        this.h.clear();
                    }
                }
                synchronized (this.i) {
                    this.i.clear();
                }
            } catch (Throwable th) {
                iv.c(th, "GlOverlayLayer", "clear");
                th.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void destroy() {
        synchronized (this) {
            try {
                if (this.g == null) {
                    return;
                }
                synchronized (this.h) {
                    this.h.clear();
                }
                synchronized (this.i) {
                    this.i.clear();
                }
                this.g.clear("");
                this.g.destroy();
                this.g = null;
            } catch (Throwable th) {
                iv.c(th, "GlOverlayLayer", "destroy");
                th.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean draw(int i, int i2, boolean z) {
        synchronized (this) {
            boolean z2 = false;
            try {
                ck ckVar = this.j;
                if (ckVar != null) {
                    ckVar.a();
                }
            } finally {
                return z2;
            }
            if (this.f1380a.getMapConfig() == null) {
                return false;
            }
            AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
            if (aMapNativeGlOverlayLayer != null) {
                aMapNativeGlOverlayLayer.render(i, i2, z);
            }
            z2 = true;
            return z2;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final BaseOverlay getHitBaseOverlay(LatLng latLng, int i) {
        BaseOverlay baseOverlay;
        synchronized (this) {
            AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
            if (aMapNativeGlOverlayLayer == null) {
                return null;
            }
            String contain = aMapNativeGlOverlayLayer.contain(latLng, i);
            if (TextUtils.isEmpty(contain)) {
                return null;
            }
            synchronized (this.h) {
                baseOverlay = this.h.get(contain);
            }
            return baseOverlay;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final BaseOverlay getHitBaseOverlay(MotionEvent motionEvent, int i) {
        if (this.f1380a == null) {
            return null;
        }
        DPoint obtain = DPoint.obtain();
        this.f1380a.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
        LatLng latLng = new LatLng(obtain.y, obtain.x);
        obtain.recycle();
        return getHitBaseOverlay(latLng, i);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final Polyline getHitOverlay(LatLng latLng, int i) {
        synchronized (this) {
            AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
            if (aMapNativeGlOverlayLayer != null) {
                String contain = aMapNativeGlOverlayLayer.contain(latLng, i);
                if (TextUtils.isEmpty(contain)) {
                    return null;
                }
                synchronized (this.h) {
                    BaseOverlay baseOverlay = this.h.get(contain);
                    r1 = baseOverlay instanceof Polyline ? (Polyline) baseOverlay : null;
                }
            }
            return r1;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final IAMapDelegate getMap() {
        return this.f1380a;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void setRunLowFrame(boolean z) {
        IAMapDelegate iAMapDelegate = this.f1380a;
        if (iAMapDelegate != null) {
            iAMapDelegate.setRunLowFrame(z);
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void onCreateAMapInstance() {
        if (this.g == null) {
            this.g = new AMapNativeGlOverlayLayer();
        }
        this.g.createNative(this.f1380a.getGLMapEngine().getNativeInstance());
        this.g.setNativeFunCallListener(this);
    }

    private void a(String str, BaseOptions baseOptions) {
        try {
            this.g.createOverlay(str, baseOptions);
        } catch (Throwable th) {
            iv.c(th, "GlOverlayLayer", "addOverlay");
            th.printStackTrace();
            Log.d("amapApi", "GlOverlayLayer addOverlay error:" + th.getMessage());
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void updateOption(String str, BaseOptions baseOptions) {
        try {
            if (this.g == null) {
                return;
            }
            setRunLowFrame(false);
            this.g.updateOptions(str, baseOptions);
        } catch (Throwable th) {
            iv.c(th, "GlOverlayLayer", "updateOption");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean removeOverlay(String str) {
        boolean z;
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            aMapNativeGlOverlayLayer.removeOverlay(str);
            z = true;
        } else {
            z = false;
        }
        synchronized (this.h) {
            this.h.remove(str);
        }
        return z;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final int getCurrentParticleNum(String str) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            return aMapNativeGlOverlayLayer.getCurrentParticleNum(str);
        }
        return 0;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final void onSetRunLowFrame(boolean z) {
        setRunLowFrame(z);
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final void onRedrawInfowindow() {
        IAMapDelegate iAMapDelegate = this.f1380a;
        if (iAMapDelegate != null) {
            iAMapDelegate.redrawInfoWindow();
        }
    }

    private BitmapDescriptor a(View view) {
        if (view == null) {
            return null;
        }
        if ((view instanceof RelativeLayout) && this.d != null) {
            LinearLayout linearLayout = new LinearLayout(this.d);
            linearLayout.setOrientation(1);
            linearLayout.addView(view);
            view = linearLayout;
        }
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(0);
        return BitmapDescriptorFactory.fromBitmap(dv.a(view));
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getInfoWindow(String str) {
        at infoWindowDelegate;
        IAMapDelegate iAMapDelegate = this.f1380a;
        if (iAMapDelegate == null || (infoWindowDelegate = iAMapDelegate.getInfoWindowDelegate()) == null) {
            return null;
        }
        BaseOverlay baseOverlay = this.h.get(str);
        if (baseOverlay instanceof BasePointOverlay) {
            return a(infoWindowDelegate.a((BasePointOverlay) baseOverlay));
        }
        return null;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getInfoContents(String str) {
        at infoWindowDelegate;
        IAMapDelegate iAMapDelegate = this.f1380a;
        if (iAMapDelegate == null || (infoWindowDelegate = iAMapDelegate.getInfoWindowDelegate()) == null) {
            return null;
        }
        BaseOverlay baseOverlay = this.h.get(str);
        if (baseOverlay instanceof BasePointOverlay) {
            return a(infoWindowDelegate.b((BasePointOverlay) baseOverlay));
        }
        return null;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final long getInfoWindowUpdateOffsetTime(String str) {
        at infoWindowDelegate;
        IAMapDelegate iAMapDelegate = this.f1380a;
        if (iAMapDelegate == null || (infoWindowDelegate = iAMapDelegate.getInfoWindowDelegate()) == null) {
            return 0L;
        }
        BaseOverlay baseOverlay = this.h.get(str);
        if (baseOverlay instanceof BasePointOverlay) {
            return infoWindowDelegate.c((BasePointOverlay) baseOverlay);
        }
        return 0L;
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getBuildInImageData(int i) {
        switch (i) {
            case 0:
                BitmapDescriptor bitmapDescriptor = this.k;
                if (bitmapDescriptor == null || bitmapDescriptor.getBitmap().isRecycled()) {
                    this.k = BitmapDescriptorFactory.fromBitmap(dv.a(this.d, "amap_sdk_lineTexture.png"));
                }
                return this.k;
            case 1:
                BitmapDescriptor bitmapDescriptor2 = this.n;
                if (bitmapDescriptor2 == null || bitmapDescriptor2.getBitmap().isRecycled()) {
                    this.n = BitmapDescriptorFactory.fromBitmap(dv.a(this.d, "amap_sdk_lineTexture.png"));
                }
                return this.n;
            case 2:
                BitmapDescriptor bitmapDescriptor3 = this.m;
                if (bitmapDescriptor3 == null || bitmapDescriptor3.getBitmap().isRecycled()) {
                    this.m = BitmapDescriptorFactory.fromBitmap(dv.a(this.d, "amap_sdk_lineDashTexture_circle.png"));
                }
                return this.m;
            case 3:
                BitmapDescriptor bitmapDescriptor4 = this.l;
                if (bitmapDescriptor4 == null || bitmapDescriptor4.getBitmap().isRecycled()) {
                    this.l = BitmapDescriptorFactory.fromBitmap(dv.a(this.d, "amap_sdk_lineDashTexture_square.png"));
                }
                return this.l;
            case 4:
                BitmapDescriptor bitmapDescriptor5 = this.o;
                if (bitmapDescriptor5 == null || bitmapDescriptor5.getBitmap().isRecycled()) {
                    this.o = BitmapDescriptorFactory.defaultMarker();
                }
                return this.o;
            case 5:
                BitmapDescriptor bitmapDescriptor6 = this.p;
                if (bitmapDescriptor6 == null || bitmapDescriptor6.getBitmap().isRecycled()) {
                    this.p = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_inner.png");
                }
                return this.p;
            case 6:
                BitmapDescriptor bitmapDescriptor7 = this.q;
                if (bitmapDescriptor7 == null || bitmapDescriptor7.getBitmap().isRecycled()) {
                    this.q = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_outer.png");
                }
                return this.q;
            case 7:
                BitmapDescriptor bitmapDescriptor8 = this.r;
                if (bitmapDescriptor8 == null || bitmapDescriptor8.getBitmap().isRecycled()) {
                    this.r = BitmapDescriptorFactory.fromAsset("arrow/arrow_line_shadow.png");
                }
                return this.r;
            default:
                return null;
        }
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.NativeFunCallListener
    public final BitmapDescriptor getCustomImageData(ImageOptions imageOptions) {
        if (imageOptions == null || imageOptions.radius == 0.0d || imageOptions.type != ImageOptions.ShapeType.CIRCLE.value()) {
            return null;
        }
        int i = (int) imageOptions.radius;
        int i2 = i * 2;
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(createBitmap);
        Path path = new Path();
        float f = i;
        path.addCircle(f, f, f, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawColor(imageOptions.color);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        return BitmapDescriptorFactory.fromBitmap(createBitmap);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final BaseOverlay addOverlayObject(String str, BaseOverlay baseOverlay, BaseOptions baseOptions) {
        a(str, baseOverlay, baseOptions);
        return baseOverlay;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final LatLng getNearestLatLng(PolylineOptions polylineOptions, LatLng latLng) {
        List<LatLng> points;
        if (latLng == null || polylineOptions == null || (points = polylineOptions.getPoints()) == null || points.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < points.size(); i2++) {
            try {
                if (i2 == 0) {
                    f = AMapUtils.calculateLineDistance(latLng, points.get(i2));
                } else {
                    float calculateLineDistance = AMapUtils.calculateLineDistance(latLng, points.get(i2));
                    if (f > calculateLineDistance) {
                        i = i2;
                        f = calculateLineDistance;
                    }
                }
            } catch (Throwable th) {
                iv.c(th, "PolylineDelegate", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return points.get(i);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean IsPolygonContainsPoint(PolygonOptions polygonOptions, LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        try {
            List<BaseHoleOptions> holeOptions = polygonOptions.getHoleOptions();
            if (holeOptions != null && holeOptions.size() > 0) {
                Iterator<BaseHoleOptions> it = holeOptions.iterator();
                while (it.hasNext()) {
                    if (dv.a(it.next(), latLng)) {
                        return false;
                    }
                }
            }
            return dv.a(latLng, polygonOptions.getPoints());
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean IsCircleContainPoint(CircleOptions circleOptions, LatLng latLng) {
        if (latLng != null && circleOptions != null) {
            try {
                synchronized (circleOptions) {
                    List<BaseHoleOptions> holeOptions = circleOptions.getHoleOptions();
                    if (holeOptions != null && holeOptions.size() > 0) {
                        Iterator<BaseHoleOptions> it = holeOptions.iterator();
                        while (it.hasNext()) {
                            if (dv.a(it.next(), latLng)) {
                                return false;
                            }
                        }
                    }
                    return circleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleOptions.getCenter(), latLng));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final Object getNativeProperties(String str, String str2, Object[] objArr) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            return aMapNativeGlOverlayLayer.getNativeProperties(str, str2, objArr);
        }
        return null;
    }

    private void a(String str, BaseOverlay baseOverlay, BaseOptions baseOptions) {
        a(str, baseOptions);
        synchronized (this.h) {
            this.h.put(str, baseOverlay);
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void showInfoWindow(String str) {
        Map<String, BaseOverlay> map;
        if (this.g == null || (map = this.h) == null) {
            return;
        }
        try {
            this.f1380a.showInfoWindow(map.get(str));
            setRunLowFrame(false);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void hideInfoWindow(String str) {
        if (this.g != null) {
            this.f1380a.hideInfoWindow();
            this.g.getNativeProperties(str, "setInfoWindowShown", new Object[]{Boolean.FALSE});
        }
        setRunLowFrame(false);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final boolean checkInBounds(String str) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer == null) {
            return true;
        }
        Object nativeProperties = aMapNativeGlOverlayLayer.getNativeProperties(str, "checkInBounds", new Object[]{str});
        if (nativeProperties instanceof Boolean) {
            return ((Boolean) nativeProperties).booleanValue();
        }
        return true;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void getMarkerInfoWindowOffset(String str, FPoint fPoint) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            Object nativeProperties = aMapNativeGlOverlayLayer.getNativeProperties(str, "getMarkerInfoWindowOffset", null);
            if (nativeProperties instanceof Point) {
                Point point = (Point) nativeProperties;
                fPoint.x = point.x;
                fPoint.y = point.y;
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void getOverlayScreenPos(String str, FPoint fPoint) {
        if (this.h.get(str) instanceof BasePointOverlay) {
            Object nativeProperties = this.g.getNativeProperties(str, "getMarkerScreenPos", null);
            if (nativeProperties instanceof Point) {
                Point point = (Point) nativeProperties;
                fPoint.x = point.x;
                fPoint.y = point.y;
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final MultiPointItem getMultiPointItem(LatLng latLng) {
        List<MultiPointItem> items;
        try {
            AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
            if (aMapNativeGlOverlayLayer == null) {
                return null;
            }
            Object nativeProperties = aMapNativeGlOverlayLayer.getNativeProperties("", "getMultiPointItem", new LatLng[]{latLng});
            if (!(nativeProperties instanceof MultiPointItemHitTest)) {
                return null;
            }
            MultiPointItemHitTest multiPointItemHitTest = (MultiPointItemHitTest) nativeProperties;
            if (multiPointItemHitTest.index == -1) {
                return null;
            }
            BaseOverlay baseOverlay = this.h.get(multiPointItemHitTest.overlayName);
            if (!(baseOverlay instanceof MultiPointOverlay) || (items = ((MultiPointOverlay) baseOverlay).getItems()) == null || items.size() <= multiPointItemHitTest.index) {
                return null;
            }
            return items.get(multiPointItemHitTest.index);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void set2Top(String str) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            aMapNativeGlOverlayLayer.getNativeProperties(str, "set2Top", null);
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final List<Marker> getMapScreenMarkers() {
        if (this.g == null) {
            return null;
        }
        this.c.clear();
        this.g.getNativeProperties("", "getMapScreenOverlays", new Object[]{this.c});
        if (this.c.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.c) {
            if (str != null && str.contains("MARKER")) {
                arrayList.add((Marker) this.h.get(str));
            }
        }
        return arrayList;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void setFlingState(boolean z) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            aMapNativeGlOverlayLayer.getNativeProperties("", "setFlingState", new Object[]{Boolean.valueOf(z)});
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public final void clearTileCache() {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.g;
        if (aMapNativeGlOverlayLayer != null) {
            aMapNativeGlOverlayLayer.getNativeProperties("", "clearTileCache", null);
        }
    }
}
