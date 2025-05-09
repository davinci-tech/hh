package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.amap.api.col.p0003sl.cs;
import com.amap.api.col.p0003sl.ed;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.col.p0003sl.k;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.InfoWindowAnimationManager;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BuildingOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.HeatMapGridLayer;
import com.amap.api.maps.model.HeatMapGridLayerOptions;
import com.amap.api.maps.model.HeatMapLayer;
import com.amap.api.maps.model.HeatMapLayerOptions;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MVTTileOverlay;
import com.amap.api.maps.model.MVTTileOverlayOptions;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.amap.api.maps.model.particle.ParticleOverlay;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.base.ae.gmap.AMapAppRequestParam;
import com.autonavi.base.ae.gmap.GLMapEngine;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.ae.gmap.MapPoi;
import com.autonavi.base.ae.gmap.bean.NativeTextGenerate;
import com.autonavi.base.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.base.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.base.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.base.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.base.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.base.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.base.ae.gmap.style.StyleItem;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import com.autonavi.base.amap.api.mapcore.IProjectionDelegate;
import com.autonavi.base.amap.api.mapcore.IUiSettingsDelegate;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.Rectangle;
import com.autonavi.base.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage;
import com.autonavi.base.amap.mapcore.tools.GLConvertUtil;
import com.huawei.appgallery.marketinstallerservice.api.Constant;
import com.huawei.hms.network.embedded.w9;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public final class l implements cs.a, k.a, IAMapDelegate, IAMapListener {
    private boolean A;
    private final IGLSurfaceView B;
    private eg C;
    private final IGlOverlayLayer D;
    private boolean E;
    private int F;
    private AtomicBoolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private cj K;
    private LocationSource L;
    private boolean M;
    private Marker N;
    private BaseOverlayImp O;
    private Marker P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private int U;
    private boolean V;
    private boolean W;
    private Rect X;
    private int Y;
    private MyTrafficStyle Z;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1273a;
    private int aA;
    private int aB;
    private int aC;
    private b aD;
    private co aE;
    private AMap.OnMultiPointClickListener aF;
    private k aG;
    private long aH;
    private a aI;
    private a aJ;
    private a aK;
    private a aL;
    private a aM;
    private a aN;
    private a aO;
    private a aP;
    private a aQ;
    private a aR;
    private a aS;
    private a aT;
    private Runnable aU;
    private a aV;
    private com.autonavi.extra.b aW;
    private String aX;
    private String aY;
    private boolean aZ;
    private Thread aa;
    private Thread ab;
    private boolean ac;
    private boolean ad;
    private int ae;
    private CustomRenderer af;
    private int ag;
    private int ah;
    private List<aa> ai;
    private cq aj;
    private cs ak;
    private long al;
    private GLMapRender am;
    private x an;
    private boolean ao;
    private float ap;
    private float aq;
    private float ar;
    private boolean as;
    private boolean at;
    private boolean au;
    private volatile boolean av;
    private volatile boolean aw;
    private boolean ax;
    private boolean ay;
    private Lock az;
    protected MapConfig b;
    private boolean ba;
    private int bb;
    private EAMapPlatformGestureInfo bc;
    private long bd;
    private as be;
    private IPoint[] bf;
    protected as c;
    dm d;
    protected Context e;
    protected GLMapEngine f;
    public int g;
    public int h;
    boolean i;
    protected final Handler j;
    Point k;
    protected String l;
    float[] m;
    float[] n;
    float[] o;
    float[] p;
    String q;
    String r;
    int s;
    private p t;
    private q u;
    private AMapGestureListener v;
    private at w;
    private UiSettings x;
    private IProjectionDelegate y;
    private final ae z;

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final RouteOverlay addNaviRouteOverlay() {
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final AMapCameraInfo getCamerInfo() {
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final InfoWindowAnimationManager getInfoWindowAnimationManager() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean isLockMapCameraDegree(int i) {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void reloadMap() {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMaskLayerParams(int i, int i2, int i3, int i4, int i5, long j) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setZOrderOnTop(boolean z) throws RemoteException {
    }

    static /* synthetic */ boolean f(l lVar) {
        lVar.au = false;
        return false;
    }

    static /* synthetic */ boolean i(l lVar) {
        lVar.T = false;
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setMapWidgetListener(AMapWidgetListener aMapWidgetListener) {
        try {
            q qVar = this.u;
            if (qVar != null) {
                qVar.a(AMapWidgetListener.class.hashCode(), (int) aMapWidgetListener);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CameraPosition cameraPosition) {
        if (this.b.getMapLanguage().equals("en")) {
            boolean c2 = c(cameraPosition);
            if (c2 != this.W) {
                this.W = c2;
                b(this.F, c2);
                return;
            }
            return;
        }
        if (this.W) {
            return;
        }
        this.W = true;
        b(this.F, true);
    }

    private boolean c(CameraPosition cameraPosition) {
        if (cameraPosition.zoom < 6.0f) {
            return false;
        }
        if (cameraPosition.isAbroad) {
            return true;
        }
        if (this.b == null) {
            return false;
        }
        try {
            return !Cdo.a(r3.getGeoRectangle().getClipRect());
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
            return false;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setVisibilityEx(int i) {
        IGLSurfaceView iGLSurfaceView = this.B;
        if (iGLSurfaceView != null) {
            try {
                iGLSurfaceView.setVisibility(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void onActivityPause() {
        this.H = true;
        c(this.F);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void onActivityResume() {
        this.H = false;
        d(this.F);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void queueEvent(Runnable runnable) {
        long j;
        try {
            try {
                j = Thread.currentThread().getId();
            } catch (Throwable th) {
                dv.a(th);
                iv.c(th, "AMapdelegateImp", "queueEvent");
                j = -1;
            }
            if (j != -1 && j == this.al) {
                runnable.run();
            } else if (this.f != null) {
                this.B.queueEvent(runnable);
            }
        } catch (Throwable th2) {
            dv.a(th2);
            iv.c(th2, "AMapdelegateImp", "queueEvent");
        }
    }

    static abstract class a implements Runnable {
        boolean b;
        boolean c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        byte[] j;

        private a() {
            this.b = false;
            this.c = false;
            this.h = 0;
            this.i = 0;
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b = false;
        }
    }

    public l(IGLSurfaceView iGLSurfaceView, Context context) {
        this(iGLSurfaceView, context, false);
    }

    public l(IGLSurfaceView iGLSurfaceView, Context context, boolean z) {
        this.t = null;
        this.u = new q();
        byte b2 = 0;
        this.f1273a = false;
        this.A = false;
        this.E = false;
        this.G = new AtomicBoolean(false);
        this.H = false;
        this.b = new MapConfig(true);
        this.I = false;
        this.J = false;
        this.M = false;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.V = true;
        this.W = true;
        this.X = new Rect();
        this.Y = 1;
        this.Z = null;
        this.ac = false;
        this.ad = false;
        this.ae = 0;
        this.ag = -1;
        this.ah = -1;
        this.ai = new ArrayList();
        this.d = null;
        this.al = -1L;
        this.ao = false;
        this.ap = 0.0f;
        this.aq = 1.0f;
        this.ar = 1.0f;
        this.as = true;
        this.at = false;
        this.au = false;
        this.av = false;
        this.aw = false;
        this.ax = false;
        this.ay = false;
        this.az = new ReentrantLock();
        this.aA = 0;
        this.i = true;
        this.j = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.col.3sl.l.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i;
                ej f;
                if (message == null || l.this.G.get()) {
                    return;
                }
                try {
                    i = message.what;
                } catch (Throwable th) {
                    iv.c(th, "AMapDelegateImp", "handleMessage");
                    th.printStackTrace();
                }
                if (i == 2) {
                    StringBuilder sb = new StringBuilder("Key验证失败：[");
                    if (message.obj != null) {
                        sb.append(message.obj);
                    } else {
                        sb.append(ho.b);
                    }
                    sb.append("]");
                    Log.w("amapsdk", sb.toString());
                    return;
                }
                int i2 = 0;
                switch (i) {
                    case 10:
                        CameraPosition cameraPosition = (CameraPosition) message.obj;
                        try {
                            List a2 = l.this.u.a(AMap.OnCameraChangeListener.class.hashCode());
                            if (cameraPosition != null && a2 != null && a2.size() > 0) {
                                synchronized (a2) {
                                    Iterator it = a2.iterator();
                                    while (it.hasNext()) {
                                        ((AMap.OnCameraChangeListener) it.next()).onCameraChange(cameraPosition);
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            dv.a(th2);
                        }
                        l.this.b.addChangedCounter();
                        return;
                    case 11:
                        try {
                            CameraPosition cameraPosition2 = l.this.getCameraPosition();
                            if (cameraPosition2 != null && l.this.C != null) {
                                l.this.C.a(cameraPosition2);
                            }
                            l.this.b(cameraPosition2);
                            if (l.this.au) {
                                l.f(l.this);
                                if (l.this.D != null) {
                                    l.this.D.setFlingState(false);
                                }
                                l.this.b();
                            }
                            if (l.this.T) {
                                l.this.redrawInfoWindow();
                                l.i(l.this);
                            }
                            l.this.a(cameraPosition2);
                            return;
                        } catch (Throwable th3) {
                            iv.c(th3, "AMapDelegateImp", "CameraUpdateFinish");
                            dv.a(th3);
                            return;
                        }
                    case 12:
                        if (l.this.C != null) {
                            l.this.C.a(Float.valueOf(l.this.getZoomLevel()));
                            return;
                        }
                        return;
                    case 13:
                        if (l.this.C != null) {
                            l.this.C.h();
                            return;
                        }
                        return;
                    case 14:
                        try {
                            List a3 = l.this.u.a(AMap.OnMapTouchListener.class.hashCode());
                            if (a3 == null || a3.size() <= 0) {
                                return;
                            }
                            synchronized (a3) {
                                Iterator it2 = a3.iterator();
                                while (it2.hasNext()) {
                                    ((AMap.OnMapTouchListener) it2.next()).onTouch((MotionEvent) message.obj);
                                }
                            }
                            return;
                        } catch (Throwable th4) {
                            iv.c(th4, "AMapDelegateImp", "onTouchHandler");
                            th4.printStackTrace();
                            return;
                        }
                    case 15:
                        Bitmap bitmap = (Bitmap) message.obj;
                        int i3 = message.arg1;
                        if (bitmap == null || l.this.C == null) {
                            try {
                                List a4 = l.this.u.a(AMap.onMapPrintScreenListener.class.hashCode());
                                ArrayList arrayList = a4 != null ? new ArrayList(a4) : null;
                                List a5 = l.this.u.a(AMap.OnMapScreenShotListener.class.hashCode());
                                ArrayList arrayList2 = a5 != null ? new ArrayList(a5) : null;
                                l.this.u.a(Integer.valueOf(AMap.onMapPrintScreenListener.class.hashCode()));
                                l.this.u.a(Integer.valueOf(AMap.OnMapScreenShotListener.class.hashCode()));
                                if (arrayList != null && arrayList.size() > 0) {
                                    synchronized (arrayList) {
                                        for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                            ((AMap.onMapPrintScreenListener) arrayList.get(i4)).onMapPrint(null);
                                        }
                                    }
                                }
                                if (arrayList2 == null || arrayList2.size() <= 0) {
                                    return;
                                }
                                synchronized (arrayList2) {
                                    while (i2 < arrayList2.size()) {
                                        ((AMap.OnMapScreenShotListener) arrayList2.get(i2)).onMapScreenShot(null);
                                        ((AMap.OnMapScreenShotListener) arrayList2.get(i2)).onMapScreenShot(null, i3);
                                        i2++;
                                    }
                                }
                                return;
                            } catch (Throwable th5) {
                                th5.printStackTrace();
                                return;
                            }
                        }
                        Canvas canvas = new Canvas(bitmap);
                        if (l.this.V && (f = l.this.C.f()) != null) {
                            f.onDraw(canvas);
                        }
                        l.this.C.a(canvas);
                        try {
                            List a6 = l.this.u.a(AMap.onMapPrintScreenListener.class.hashCode());
                            ArrayList arrayList3 = a6 != null ? new ArrayList(a6) : null;
                            List a7 = l.this.u.a(AMap.OnMapScreenShotListener.class.hashCode());
                            ArrayList arrayList4 = a7 != null ? new ArrayList(a7) : null;
                            l.this.u.a(Integer.valueOf(AMap.onMapPrintScreenListener.class.hashCode()));
                            l.this.u.a(Integer.valueOf(AMap.OnMapScreenShotListener.class.hashCode()));
                            if (arrayList3 != null && arrayList3.size() > 0) {
                                synchronized (arrayList3) {
                                    for (int i5 = 0; i5 < arrayList3.size(); i5++) {
                                        ((AMap.onMapPrintScreenListener) arrayList3.get(i5)).onMapPrint(new BitmapDrawable(l.this.e.getResources(), bitmap));
                                    }
                                }
                            }
                            if (arrayList4 == null || arrayList4.size() <= 0) {
                                return;
                            }
                            synchronized (arrayList4) {
                                while (i2 < arrayList4.size()) {
                                    ((AMap.OnMapScreenShotListener) arrayList4.get(i2)).onMapScreenShot(bitmap);
                                    ((AMap.OnMapScreenShotListener) arrayList4.get(i2)).onMapScreenShot(bitmap, i3);
                                    i2++;
                                }
                            }
                            return;
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                            return;
                        }
                    case 16:
                        try {
                            List a8 = l.this.u.a(AMap.OnMapLoadedListener.class.hashCode());
                            if (a8 != null) {
                                synchronized (a8) {
                                    while (i2 < a8.size()) {
                                        ((AMap.OnMapLoadedListener) a8.get(i2)).onMapLoaded();
                                        i2++;
                                    }
                                }
                            }
                        } catch (Throwable th7) {
                            iv.c(th7, "AMapDelegateImp", "onMapLoaded");
                            th7.printStackTrace();
                            dv.a(th7);
                        }
                        if (l.this.C != null) {
                            l.this.C.i();
                            return;
                        }
                        return;
                    case 17:
                        if (!l.this.f.isInMapAnimation(l.this.F) || l.this.D == null) {
                            return;
                        }
                        l.this.D.setFlingState(false);
                        return;
                    case 18:
                        if (l.this.w != null) {
                            l.this.w.b();
                            return;
                        }
                        return;
                    case 19:
                        List a9 = l.this.u.a(AMap.OnMapClickListener.class.hashCode());
                        if (a9 != null) {
                            DPoint obtain = DPoint.obtain();
                            l.this.getPixel2LatLng(message.arg1, message.arg2, obtain);
                            try {
                                synchronized (a9) {
                                    Iterator it3 = a9.iterator();
                                    while (it3.hasNext()) {
                                        ((AMap.OnMapClickListener) it3.next()).onMapClick(new LatLng(obtain.y, obtain.x));
                                    }
                                }
                                obtain.recycle();
                                return;
                            } catch (Throwable th8) {
                                iv.c(th8, "AMapDelegateImp", "OnMapClickListener.onMapClick");
                                th8.printStackTrace();
                                return;
                            }
                        }
                        return;
                    case 20:
                        try {
                            List a10 = l.this.u.a(AMap.OnPOIClickListener.class.hashCode());
                            if (a10 == null || a10.size() <= 0) {
                                return;
                            }
                            synchronized (a10) {
                                while (i2 < a10.size()) {
                                    ((AMap.OnPOIClickListener) a10.get(i2)).onPOIClick((Poi) message.obj);
                                    i2++;
                                }
                            }
                            return;
                        } catch (Throwable th9) {
                            iv.c(th9, "AMapDelegateImp", "OnPOIClickListener.onPOIClick");
                            th9.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
                iv.c(th, "AMapDelegateImp", "handleMessage");
                th.printStackTrace();
            }
        };
        this.aI = new a() { // from class: com.amap.api.col.3sl.l.11
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setTrafficEnabled(this.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aJ = new a() { // from class: com.amap.api.col.3sl.l.21
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l lVar = l.this;
                    lVar.setCenterToPixel(lVar.aB, l.this.aC);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aK = new a() { // from class: com.amap.api.col.3sl.l.32
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                l.this.a(this.g, this.d, this.e, this.f);
            }
        };
        this.aL = new a() { // from class: com.amap.api.col.3sl.l.36
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                l.this.setMapCustomEnable(this.c);
            }
        };
        this.aM = new a() { // from class: com.amap.api.col.3sl.l.37
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                l.this.a(this.g, this.c);
            }
        };
        this.aN = new a() { // from class: com.amap.api.col.3sl.l.38
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setMapTextEnable(this.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aO = new a() { // from class: com.amap.api.col.3sl.l.39
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setRoadArrowEnable(this.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aP = new a() { // from class: com.amap.api.col.3sl.l.40
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setNaviLabelEnable(this.c, this.h, this.i);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aQ = new a() { // from class: com.amap.api.col.3sl.l.2
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setConstructingRoadEnable(this.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aR = new a() { // from class: com.amap.api.col.3sl.l.3
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setTrafficStyleWithTextureData(this.j);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aS = new a() { // from class: com.amap.api.col.3sl.l.4
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                l.this.b(this.g, this.c);
            }
        };
        this.aT = new a() { // from class: com.amap.api.col.3sl.l.5
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                try {
                    l.this.setIndoorEnabled(this.c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aU = new Runnable() { // from class: com.amap.api.col.3sl.l.6
            @Override // java.lang.Runnable
            public final void run() {
                ej f;
                if (l.this.C == null || (f = l.this.C.f()) == null) {
                    return;
                }
                f.c();
            }
        };
        this.aV = new a() { // from class: com.amap.api.col.3sl.l.7
            @Override // com.amap.api.col.3sl.l.a, java.lang.Runnable
            public final void run() {
                super.run();
                l.this.c(this.g, this.c);
            }
        };
        this.aX = "";
        this.aY = "";
        this.aZ = false;
        this.ba = false;
        this.bb = 0;
        this.bc = new EAMapPlatformGestureInfo();
        this.k = new Point();
        this.bd = 0L;
        this.l = null;
        this.be = null;
        this.m = new float[16];
        this.n = new float[16];
        this.o = new float[16];
        this.bf = null;
        this.p = new float[12];
        this.q = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
        this.r = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(1.0,0,0,1.0);\n}";
        this.s = -1;
        this.e = context;
        hx a2 = hw.a(context, dv.a());
        if (a2.f1161a == hw.c.SuccessCode) {
            dx.a(context);
            dx.a(dw.c, "init map delegate");
        }
        com.autonavi.extra.b bVar = new com.autonavi.extra.b();
        this.aW = bVar;
        bVar.a();
        this.aW.b();
        iv.a(this.e);
        dj.a().a(this.e);
        v.b = hn.c(context);
        da.a(this.e);
        this.an = new x(this);
        GLMapRender gLMapRender = new GLMapRender(this);
        this.am = gLMapRender;
        this.B = iGLSurfaceView;
        iGLSurfaceView.setRenderer(gLMapRender);
        y yVar = new y(this, this.e);
        this.D = yVar;
        this.f = new GLMapEngine(this.e, this);
        this.C = new ef(this.e, this, yVar);
        this.z = new ae(this);
        this.C.a(new c(this, b2));
        this.aD = new b();
        iGLSurfaceView.setRenderMode(0);
        this.am.setRenderFps(15.0f);
        this.f.setMapListener(this);
        this.y = new ab(this);
        this.t = new p(this);
        at atVar = new at(this.e);
        this.w = atVar;
        atVar.a(this.C);
        this.w.b(new cl(yVar, context));
        this.aa = new t(this.e, this);
        this.L = new au(this.e);
        this.aj = new cq(this.e, this);
        cs csVar = new cs(this.e);
        this.ak = csVar;
        csVar.a(this);
        a(z);
        MapConfig mapConfig = this.b;
        k kVar = new k(this, this.e, mapConfig != null ? mapConfig.isAbroadEnable() : false);
        this.aG = kVar;
        kVar.a(this);
        if (a2.f1161a != hw.c.SuccessCode) {
            this.b.setMapEnable(false);
        }
        this.F = this.f.getEngineIDWithType(1);
    }

    private void a(boolean z) {
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            Object j = bVar.j();
            if (j != null && (j instanceof Boolean)) {
                MapConfig mapConfig = this.b;
                if (mapConfig != null) {
                    mapConfig.setAbroadEnable(z && ((Boolean) j).booleanValue());
                }
                if (z && ((Boolean) j).booleanValue()) {
                    MapsInitializer.setSupportRecycleView(false);
                }
            }
            Object j2 = this.aW.j();
            if (j2 != null && (j2 instanceof Boolean)) {
                this.C.a(((Boolean) j2).booleanValue());
            }
            Object j3 = this.aW.j();
            if (j2 == null || !(j2 instanceof Integer)) {
                return;
            }
            this.ae = ((Integer) j3).intValue();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final com.autonavi.extra.b getAMapExtraInterfaceManager() {
        return this.aW;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final IGlOverlayLayer getGlOverlayLayer() {
        return this.D;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setMapEnable(boolean z) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            mapConfig.setMapEnable(z);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final GLMapEngine getGLMapEngine() {
        return this.f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setGestureStatus(int i, int i2) {
        if (this.aA == 0 || i2 != 5) {
            this.aA = i2;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getPreciseLevel(int i) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getSZ();
        }
        return 0.0f;
    }

    private float c() {
        if (this.b != null) {
            return getMapConfig().getSZ();
        }
        return 0.0f;
    }

    private boolean a(int i, int i2) {
        AbstractCameraUpdateMessage a2;
        if (!this.av || ((int) c()) >= this.b.getMaxZoomLevel()) {
            return false;
        }
        try {
            if (!this.I && !this.z.isZoomInByScreenCenter()) {
                this.k.x = i;
                this.k.y = i2;
                a2 = aj.a(1.0f, this.k);
            } else {
                a2 = aj.a(1.0f, (Point) null);
            }
            animateCamera(a2);
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "onDoubleTap");
            th.printStackTrace();
        }
        resetRenderTime();
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void zoomOut(int i) {
        if (this.av && ((int) c()) > this.b.getMinZoomLevel()) {
            try {
                animateCamera(aj.b());
            } catch (Throwable th) {
                iv.c(th, "AMapDelegateImp", "onDoubleTap");
                th.printStackTrace();
            }
            resetRenderTime();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean isLockMapAngle(int i) {
        return g(i);
    }

    private void d() {
        if (this.av) {
            this.an.a();
            this.ao = true;
            this.at = true;
            try {
                stopAnimation();
            } catch (RemoteException unused) {
            }
        }
    }

    private void e() {
        this.ao = true;
        this.at = false;
        if (this.R) {
            this.R = false;
        }
        if (this.Q) {
            this.Q = false;
        }
        if (this.S) {
            this.S = false;
        }
        try {
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "OnMarkerDragListener.onMarkerDragEnd");
            th.printStackTrace();
        }
        if (this.M) {
            List a2 = this.u.a(AMap.OnMarkerDragListener.class.hashCode());
            if (a2 != null && a2.size() > 0 && (this.N != null || this.P != null)) {
                synchronized (a2) {
                    for (int i = 0; i < a2.size(); i++) {
                        ((AMap.OnMarkerDragListener) a2.get(i)).onMarkerDragEnd(this.P);
                    }
                }
                this.N = null;
                this.P = null;
            }
            this.M = false;
        }
    }

    private void a(MotionEvent motionEvent) throws RemoteException {
        if (!this.M || this.P == null) {
            return;
        }
        int x = (int) motionEvent.getX();
        int y = (int) (motionEvent.getY() - 60.0f);
        if (this.P.getPosition() != null) {
            DPoint obtain = DPoint.obtain();
            getPixel2LatLng(x, y, obtain);
            LatLng latLng = new LatLng(obtain.y, obtain.x);
            obtain.recycle();
            this.P.setPosition(latLng);
            try {
                List a2 = this.u.a(AMap.OnMarkerDragListener.class.hashCode());
                if (a2 == null || a2.size() <= 0) {
                    return;
                }
                synchronized (a2) {
                    for (int i = 0; i < a2.size(); i++) {
                        ((AMap.OnMarkerDragListener) a2.get(i)).onMarkerDrag(this.P);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void b(boolean z) {
        this.as = z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void clearTileCache() {
        this.D.clearTileCache();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final GLMapState getMapProjection() {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.getMapState(this.F);
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showMyLocationOverlay(Location location) throws RemoteException {
        if (location == null) {
            return;
        }
        try {
            if (this.E && this.L != null) {
                if (this.K == null) {
                    this.K = new cj(this, this.e);
                }
                if (location.getLongitude() != 0.0d && location.getLatitude() != 0.0d) {
                    this.K.a(location);
                }
                List a2 = this.u.a(AMap.OnMyLocationChangeListener.class.hashCode());
                if (a2 != null && a2.size() > 0) {
                    synchronized (a2) {
                        for (int i = 0; i < a2.size(); i++) {
                            ((AMap.OnMyLocationChangeListener) a2.get(i)).onMyLocationChange(location);
                        }
                    }
                }
                resetRenderTime();
                return;
            }
            cj cjVar = this.K;
            if (cjVar != null) {
                cjVar.c();
            }
            this.K = null;
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "showMyLocationOverlay");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean removeGLOverlay(String str) throws RemoteException {
        resetRenderTime();
        return this.D.removeOverlay(str);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean removeGLModel(String str) {
        try {
            this.D.removeOverlay(str);
            return false;
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "removeGLModel");
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void changeGLOverlayIndex() {
        this.D.changeOverlayIndex();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float checkZoomLevel(float f) throws RemoteException {
        return dv.a(this.b, f);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void latlon2Geo(double d2, double d3, IPoint iPoint) {
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d3, 20);
        iPoint.x = latLongToPixels.x;
        iPoint.y = latLongToPixels.y;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void geo2Map(int i, int i2, FPoint fPoint) {
        fPoint.x = (int) (i - this.b.getSX());
        fPoint.y = (int) (i2 - this.b.getSY());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void geo2Latlng(int i, int i2, DPoint dPoint) {
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(i, i2, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        pixelsToLatLong.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getZoomLevel() {
        return c();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final IUiSettingsDelegate getUiSettings() {
        return this.z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final IProjectionDelegate getProjection() throws RemoteException {
        return this.y;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final k getCustomStyleManager() {
        return this.aG;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final AMap.OnCameraChangeListener getOnCameraChangeListener() throws RemoteException {
        try {
            List a2 = this.u.a(AMap.OnCameraChangeListener.class.hashCode());
            if (a2 == null && a2.size() != 0) {
                return (AMap.OnCameraChangeListener) a2.get(0);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showInfoWindow(BaseOverlayImp baseOverlayImp) throws RemoteException {
        at atVar;
        if (baseOverlayImp == null || (atVar = this.w) == null) {
            return;
        }
        try {
            atVar.a(baseOverlayImp);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showInfoWindow(BaseOverlay baseOverlay) throws RemoteException {
        at atVar;
        if (baseOverlay == null || (atVar = this.w) == null) {
            return;
        }
        try {
            atVar.a(baseOverlay);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void hideInfoWindow() {
        at atVar = this.w;
        if (atVar != null) {
            atVar.c();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void getLatLng2Map(double d2, double d3, FPoint fPoint) {
        IPoint obtain = IPoint.obtain();
        latlon2Geo(d2, d3, obtain);
        geo2Map(obtain.x, obtain.y, fPoint);
        obtain.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void getPixel2LatLng(int i, int i2, DPoint dPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (this.G.get() || !this.av || (gLMapEngine = this.f) == null || (mapState = gLMapEngine.getMapState(this.F)) == null) {
            return;
        }
        IPoint obtain = IPoint.obtain();
        mapState.screenToP20Point(i, i2, obtain);
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(obtain.x, obtain.y, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        obtain.recycle();
        pixelsToLatLong.recycle();
    }

    private void a(GLMapState gLMapState, int i, int i2, DPoint dPoint) {
        if (!this.av || this.f == null) {
            return;
        }
        gLMapState.screenToP20Point(i, i2, new Point());
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(r0.x, r0.y, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        pixelsToLatLong.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void getLatLng2Pixel(double d2, double d3, IPoint iPoint) {
        if (this.G.get() || !this.av || this.f == null) {
            return;
        }
        try {
            Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d3, 20);
            FPoint obtain = FPoint.obtain();
            a(latLongToPixels.x, latLongToPixels.y, obtain);
            if (obtain.x == -10000.0f && obtain.y == -10000.0f) {
                GLMapState gLMapState = (GLMapState) this.f.getNewMapState(this.F);
                gLMapState.setCameraDegree(0.0f);
                gLMapState.recalculate();
                gLMapState.p20ToScreenPoint(latLongToPixels.x, latLongToPixels.y, obtain);
                gLMapState.recycle();
            }
            iPoint.x = (int) obtain.x;
            iPoint.y = (int) obtain.y;
            obtain.recycle();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void getPixel2Geo(int i, int i2, IPoint iPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (this.G.get() || !this.av || (gLMapEngine = this.f) == null || (mapState = gLMapEngine.getMapState(this.F)) == null) {
            return;
        }
        mapState.screenToP20Point(i, i2, iPoint);
    }

    private void a(int i, int i2, FPoint fPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (this.G.get() || !this.av || (gLMapEngine = this.f) == null || (mapState = gLMapEngine.getMapState(this.F)) == null) {
            return;
        }
        mapState.p20ToScreenPoint(i, i2, fPoint);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void redrawInfoWindow() {
        if (!this.G.get() && this.av) {
            this.j.sendEmptyMessage(18);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final at getInfoWindowDelegate() {
        return this.w;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showZoomControlsEnabled(boolean z) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.b(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showIndoorSwitchControlsEnabled(boolean z) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.a(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showMyLocationButtonEnabled(boolean z) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.c(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showCompassEnabled(boolean z) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.d(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showScaleEnabled(boolean z) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.e(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setZoomPosition(int i) {
        eg egVar;
        if (this.G.get() || (egVar = this.C) == null) {
            return;
        }
        egVar.a(Integer.valueOf(i));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final Rect getRect() {
        return this.X;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final LatLngBounds getMapBounds(LatLng latLng, float f, float f2, float f3) {
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        if (mapWidth <= 0 || mapHeight <= 0 || this.G.get()) {
            return null;
        }
        float a2 = dv.a(this.b, f);
        GLMapState gLMapState = new GLMapState(this.F, this.f.getNativeInstance());
        if (latLng != null) {
            IPoint obtain = IPoint.obtain();
            latlon2Geo(latLng.latitude, latLng.longitude, obtain);
            gLMapState.setCameraDegree(f3);
            gLMapState.setMapAngle(f2);
            gLMapState.setMapGeoCenter(obtain.x, obtain.y);
            gLMapState.setMapZoomer(a2);
            gLMapState.recalculate();
            obtain.recycle();
        }
        DPoint obtain2 = DPoint.obtain();
        a(gLMapState, 0, 0, obtain2);
        LatLng latLng2 = new LatLng(obtain2.y, obtain2.x, false);
        a(gLMapState, mapWidth, mapHeight, obtain2);
        LatLng latLng3 = new LatLng(obtain2.y, obtain2.x, false);
        obtain2.recycle();
        gLMapState.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void onResume() {
        try {
            this.am.setRenderFps(15.0f);
            this.B.setRenderMode(0);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.setFlingState(true);
            }
            cj cjVar = this.K;
            if (cjVar != null) {
                cjVar.b();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void onPause() {
        f();
        IGlOverlayLayer iGlOverlayLayer = this.D;
        if (iGlOverlayLayer != null) {
            iGlOverlayLayer.setFlingState(false);
        }
    }

    private void f() {
        GLMapState gLMapState;
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine == null || (gLMapState = (GLMapState) gLMapEngine.getNewMapState(this.F)) == null) {
            return;
        }
        IPoint obtain = IPoint.obtain();
        gLMapState.recalculate();
        gLMapState.getMapGeoCenter(obtain);
        this.b.setSX(obtain.x);
        this.b.setSY(obtain.y);
        this.b.setSZ(gLMapState.getMapZoomer());
        this.b.setSC(gLMapState.getCameraDegree());
        this.b.setSR(gLMapState.getMapAngle());
        gLMapState.recycle();
        obtain.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.H && this.av && this.as) {
            this.bc.mGestureState = 3;
            this.bc.mGestureType = 8;
            z = true;
            this.bc.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            getEngineIDWithGestureInfo(this.bc);
            l();
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                m();
                d();
            } else if (action == 1) {
                e();
            }
            if (motionEvent.getAction() == 2 && this.M) {
                try {
                    a(motionEvent);
                } catch (Throwable th) {
                    iv.c(th, "AMapDelegateImp", "onDragMarker");
                    th.printStackTrace();
                }
                return true;
            }
            if (this.ao) {
                try {
                    this.an.a(motionEvent);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            try {
                List a2 = this.u.a(AMap.OnMapTouchListener.class.hashCode());
                if (a2 != null && a2.size() > 0) {
                    this.j.removeMessages(14);
                    Message obtainMessage = this.j.obtainMessage();
                    obtainMessage.what = 14;
                    obtainMessage.obj = MotionEvent.obtain(motionEvent);
                    obtainMessage.sendToTarget();
                }
            } catch (Throwable unused) {
            }
        }
        return z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void pixel2Map(int i, int i2, PointF pointF) {
        if (!this.av || this.H || this.f == null) {
            return;
        }
        IPoint obtain = IPoint.obtain();
        getPixel2Geo(i, i2, obtain);
        pointF.x = obtain.x - ((float) this.b.getSX());
        pointF.y = obtain.y - ((float) this.b.getSY());
        obtain.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void map2Geo(float f, float f2, IPoint iPoint) {
        iPoint.x = (int) (f + this.b.getSX());
        iPoint.y = (int) (f2 + this.b.getSY());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float toMapLenWithWin(int i) {
        GLMapEngine gLMapEngine;
        if (!this.av || this.H || (gLMapEngine = this.f) == null) {
            return 0.0f;
        }
        return gLMapEngine.getMapState(this.F).getGLUnitWithWin(i);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final CameraPosition getCameraPositionPrj(boolean z) {
        LatLng g;
        try {
            if (this.b == null) {
                return null;
            }
            if (this.av && !this.H && this.f != null) {
                if (z) {
                    DPoint obtain = DPoint.obtain();
                    getPixel2LatLng(this.b.getAnchorX(), this.b.getAnchorY(), obtain);
                    g = new LatLng(obtain.y, obtain.x, false);
                    obtain.recycle();
                } else {
                    g = g();
                }
                return CameraPosition.builder().target(g).bearing(this.b.getSR()).tilt(this.b.getSC()).zoom(this.b.getSZ()).build();
            }
            DPoint obtain2 = DPoint.obtain();
            geo2Latlng((int) this.b.getSX(), (int) this.b.getSY(), obtain2);
            LatLng latLng = new LatLng(obtain2.y, obtain2.x);
            obtain2.recycle();
            return CameraPosition.builder().target(latLng).bearing(this.b.getSR()).tilt(this.b.getSC()).zoom(this.b.getSZ()).build();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private LatLng g() {
        MapConfig mapConfig = this.b;
        if (mapConfig == null) {
            return null;
        }
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(mapConfig.getSX(), this.b.getSY(), 20);
        LatLng latLng = new LatLng(pixelsToLatLong.y, pixelsToLatLong.x, false);
        pixelsToLatLong.recycle();
        return latLng;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean isUseAnchor() {
        return this.I;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final Point getWaterMarkerPositon() {
        eg egVar = this.C;
        if (egVar != null) {
            return egVar.a();
        }
        return new Point();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final View getGLMapView() {
        Object obj = this.B;
        if (obj instanceof View) {
            return (View) obj;
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean canShowIndoorSwitch() {
        as asVar;
        if (getZoomLevel() < 17.0f || (asVar = this.c) == null || asVar.g == null) {
            return false;
        }
        FPoint obtain = FPoint.obtain();
        a(this.c.g.x, this.c.g.y, obtain);
        return this.X.contains((int) obtain.x, (int) obtain.y);
    }

    private void h() {
        synchronized (this) {
            synchronized (this.ai) {
                int size = this.ai.size();
                for (int i = 0; i < size; i++) {
                    this.ai.get(i).a().recycle();
                }
                this.ai.clear();
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final int getLogoPosition() {
        try {
            return this.z.getLogoPosition();
        } catch (RemoteException e) {
            iv.c(e, "AMapDelegateImp", "getLogoPosition");
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setLogoPosition(int i) {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.b(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setLogoBottomMargin(int i) {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.c(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setLogoLeftMargin(int i) {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.d(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getLogoMarginRate(int i) {
        eg egVar = this.C;
        if (egVar != null) {
            return egVar.a(i);
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setLogoMarginRate(int i, float f) {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.a(Integer.valueOf(i), Float.valueOf(f));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final int getMaskLayerType() {
        return this.ag;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void post(Runnable runnable) {
        IGLSurfaceView iGLSurfaceView = this.B;
        if (iGLSurfaceView != null) {
            iGLSurfaceView.post(runnable);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void onLongPress(int i, MotionEvent motionEvent) {
        int i2 = 0;
        try {
            this.Q = false;
            b(i);
            BaseOverlay hitBaseOverlay = this.D.getHitBaseOverlay(motionEvent, 1);
            if (hitBaseOverlay instanceof Marker) {
                this.P = (Marker) hitBaseOverlay;
            }
            Marker marker = this.P;
            if (marker != null && marker.isDraggable()) {
                LatLng position = this.P.getPosition();
                if (position != null) {
                    IPoint obtain = IPoint.obtain();
                    getLatLng2Pixel(position.latitude, position.longitude, obtain);
                    obtain.y -= 60;
                    DPoint obtain2 = DPoint.obtain();
                    getPixel2LatLng(obtain.x, obtain.y, obtain2);
                    this.P.setPosition(new LatLng(obtain2.y, obtain2.x));
                    this.D.set2Top(this.P.getId());
                    try {
                        List a2 = this.u.a(AMap.OnMarkerDragListener.class.hashCode());
                        if (a2 != null && a2.size() > 0) {
                            synchronized (a2) {
                                while (i2 < a2.size()) {
                                    ((AMap.OnMarkerDragListener) a2.get(i2)).onMarkerDragStart(this.P);
                                    i2++;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        iv.c(th, "AMapDelegateImp", "onMarkerDragStart");
                        th.printStackTrace();
                    }
                    this.M = true;
                    obtain.recycle();
                    obtain2.recycle();
                }
                this.am.resetTickCount(30);
            }
            List a3 = this.u.a(AMap.OnMapLongClickListener.class.hashCode());
            if (a3 != null && a3.size() > 0) {
                DPoint obtain3 = DPoint.obtain();
                getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), obtain3);
                synchronized (a3) {
                    while (i2 < a3.size()) {
                        ((AMap.OnMapLongClickListener) a3.get(i2)).onMapLongClick(new LatLng(obtain3.y, obtain3.x));
                        i2++;
                    }
                }
                this.R = true;
                obtain3.recycle();
            }
            this.am.resetTickCount(30);
        } catch (Throwable th2) {
            iv.c(th2, "AMapDelegateImp", "onLongPress");
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean onDoubleTap(int i, MotionEvent motionEvent) {
        if (!this.av) {
            return false;
        }
        a((int) motionEvent.getX(), (int) motionEvent.getY());
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final boolean onSingleTapConfirmed(int i, MotionEvent motionEvent) {
        if (!this.av) {
            return false;
        }
        try {
            b(i);
            if (f(motionEvent) || d(motionEvent) || e(motionEvent)) {
                return true;
            }
            c(motionEvent);
            b(motionEvent);
            return true;
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "onSingleTapUp");
            th.printStackTrace();
            return true;
        }
    }

    private void b(final MotionEvent motionEvent) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.8
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (l.this.b == null || !l.this.b.isTouchPoiEnable()) {
                        return;
                    }
                    l.this.f.addGestureSingleTapMessage(motionEvent.getX(), motionEvent.getY());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    private void a(final double d2, final double d3) {
        this.j.post(new Runnable() { // from class: com.amap.api.col.3sl.l.9
            @Override // java.lang.Runnable
            public final void run() {
                Message obtain = Message.obtain();
                obtain.what = 19;
                obtain.arg1 = (int) d2;
                obtain.arg2 = (int) d3;
                l.this.j.sendMessage(obtain);
            }
        });
    }

    private boolean c(MotionEvent motionEvent) {
        try {
            List a2 = this.u.a(AMap.OnPolylineClickListener.class.hashCode());
            if (a2 != null && a2.size() > 0) {
                DPoint obtain = DPoint.obtain();
                getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
                LatLng latLng = new LatLng(obtain.y, obtain.x);
                obtain.recycle();
                Polyline hitOverlay = this.D.getHitOverlay(latLng, 2);
                if (hitOverlay != null) {
                    synchronized (a2) {
                        Iterator it = a2.iterator();
                        while (it.hasNext()) {
                            ((AMap.OnPolylineClickListener) it.next()).onPolylineClick(hitOverlay);
                        }
                    }
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private boolean d(MotionEvent motionEvent) throws RemoteException {
        LatLng position;
        DPoint obtain = DPoint.obtain();
        getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
        LatLng latLng = new LatLng(obtain.y, obtain.x);
        obtain.recycle();
        boolean z = true;
        BaseOverlay hitBaseOverlay = this.D.getHitBaseOverlay(latLng, 1);
        if ((hitBaseOverlay instanceof Marker) && ((Marker) hitBaseOverlay).getId().contains("MARKER")) {
            try {
                Marker marker = (Marker) hitBaseOverlay;
                this.D.set2Top(marker.getId());
                List a2 = this.u.a(AMap.OnMarkerClickListener.class.hashCode());
                if (a2 != null && a2.size() > 0) {
                    synchronized (a2) {
                        if (a2.size() == 1) {
                            boolean onMarkerClick = ((AMap.OnMarkerClickListener) a2.get(0)).onMarkerClick(marker);
                            if (onMarkerClick) {
                                return true;
                            }
                            z = onMarkerClick;
                        } else {
                            Iterator it = a2.iterator();
                            boolean z2 = false;
                            while (it.hasNext()) {
                                z2 |= ((AMap.OnMarkerClickListener) it.next()).onMarkerClick(marker);
                            }
                            if (z2) {
                                return true;
                            }
                            z = z2;
                        }
                    }
                }
                this.D.showInfoWindow(marker.getId());
                if (!marker.isViewMode() && (position = marker.getPosition()) != null) {
                    IPoint obtain2 = IPoint.obtain();
                    latlon2Geo(position.latitude, position.longitude, obtain2);
                    moveCamera(aj.a(obtain2));
                }
                return z;
            } catch (Throwable th) {
                iv.c(th, "AMapDelegateImp", "onMarkerTap");
                th.printStackTrace();
            }
        }
        return false;
    }

    private boolean e(MotionEvent motionEvent) {
        if (this.D != null && this.aF != null) {
            DPoint obtain = DPoint.obtain();
            if (this.f != null) {
                getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
                MultiPointItem multiPointItem = this.D.getMultiPointItem(new LatLng(obtain.y, obtain.x));
                if (multiPointItem == null) {
                    return false;
                }
                boolean onPointClick = this.aF.onPointClick(multiPointItem);
                obtain.recycle();
                return onPointClick;
            }
        }
        return false;
    }

    private boolean f(MotionEvent motionEvent) throws RemoteException {
        try {
            List a2 = this.u.a(AMap.OnInfoWindowClickListener.class.hashCode());
            BaseOverlay a3 = this.w.a(motionEvent);
            if (a3 != null && (a3 instanceof Marker)) {
                synchronized (a2) {
                    for (int i = 0; i < a2.size(); i++) {
                        ((AMap.OnInfoWindowClickListener) a2.get(i)).onInfoWindowClick((Marker) a3);
                    }
                }
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void drawFrame(GL10 gl10) {
        if (this.G.get() || this.f == null || EGL14.eglGetCurrentContext() == EGL14.EGL_NO_CONTEXT) {
            return;
        }
        MapConfig mapConfig = this.b;
        if (mapConfig != null && !mapConfig.isMapEnable()) {
            GLES20.glClear(16640);
            return;
        }
        a(this.F);
        this.f.renderAMap();
        this.f.pushRendererState();
        k kVar = this.aG;
        if (kVar != null) {
            kVar.a();
        }
        i();
        k();
        if (!this.ax) {
            this.ax = true;
        }
        this.f.popRendererState();
        if (dm.a()) {
            try {
                if (this.B instanceof o) {
                    if (this.d == null) {
                        this.d = new dm();
                    }
                    this.d.e();
                    if (!this.d.f() || this.d.d()) {
                        return;
                    }
                    if (this.d.a(((o) this.B).getBitmap())) {
                        if (dm.b()) {
                            removecache();
                        }
                        if (dm.c()) {
                            dm.g();
                        }
                        dx.b(dw.g, "pure screen: found pure check");
                    }
                }
            } catch (Throwable th) {
                iv.c(th, "AMapDelegateImp", "PureScreenCheckTool.checkBlackScreen");
            }
        }
    }

    private void a(int i) {
        int i2 = this.ah;
        if (i2 != -1) {
            this.am.setRenderFps(i2);
            resetRenderTime();
        } else if (this.f.isInMapAction(i) || this.at) {
            this.am.setRenderFps(40.0f);
        } else if (this.f.isInMapAnimation(i)) {
            this.am.setRenderFps(30.0f);
            this.am.resetTickCount(15);
        } else {
            this.am.setRenderFps(15.0f);
        }
        if (this.b.isWorldMapEnable() != MapsInitializer.isLoadWorldGridMap()) {
            b();
            this.b.setWorldMapEnable(MapsInitializer.isLoadWorldGridMap());
        }
    }

    private void i() {
        if (this.U > 0) {
            boolean canStopMapRender = this.f.canStopMapRender(this.F);
            if (!canStopMapRender) {
                int i = this.U - 1;
                this.U = i;
                if (i > 0) {
                    this.f.renderAMap();
                    return;
                }
            }
            this.U = 0;
            Message obtainMessage = this.j.obtainMessage(15, this.f.getScreenShot(this.F, 0, 0, getMapWidth(), getMapHeight()));
            obtainMessage.arg1 = canStopMapRender ? 1 : 0;
            obtainMessage.sendToTarget();
        }
    }

    private void j() {
        GLMapState mapState;
        List a2;
        List a3;
        List a4;
        if (this.G.get() || (mapState = this.f.getMapState(this.F)) == null) {
            return;
        }
        mapState.getViewMatrix(this.b.getViewMatrix());
        mapState.getProjectionMatrix(this.b.getProjectionMatrix());
        this.b.updateFinalMatrix();
        DPoint mapGeoCenter = mapState.getMapGeoCenter();
        this.b.setSX(mapGeoCenter.x);
        this.b.setSY(mapGeoCenter.y);
        this.b.setSZ(mapState.getMapZoomer());
        this.b.setSC(mapState.getCameraDegree());
        this.b.setSR(mapState.getMapAngle());
        if (this.b.isMapStateChange()) {
            this.b.setSkyHeight(mapState.getSkyHeight());
            DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(mapGeoCenter.x, mapGeoCenter.y, 20);
            CameraPosition cameraPosition = new CameraPosition(new LatLng(pixelsToLatLong.y, pixelsToLatLong.x, false), this.b.getSZ(), this.b.getSC(), this.b.getSR());
            pixelsToLatLong.recycle();
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 10;
            obtainMessage.obj = cameraPosition;
            this.j.sendMessage(obtainMessage);
            this.au = true;
            redrawInfoWindow();
            try {
                if (this.z.isZoomControlsEnabled() && this.b.isNeedUpdateZoomControllerState() && (a4 = this.u.a(AMapWidgetListener.class.hashCode())) != null && a4.size() > 0) {
                    synchronized (a4) {
                        for (int i = 0; i < a4.size(); i++) {
                            ((AMapWidgetListener) a4.get(i)).invalidateZoomController(this.b.getSZ());
                        }
                    }
                }
                if (this.b.getChangeGridRatio() != 1.0d) {
                    b();
                }
                if (this.z.isCompassEnabled() && ((this.b.isTiltChanged() || this.b.isBearingChanged()) && (a3 = this.u.a(AMapWidgetListener.class.hashCode())) != null && a3.size() > 0)) {
                    synchronized (a3) {
                        for (int i2 = 0; i2 < a3.size(); i2++) {
                            ((AMapWidgetListener) a3.get(i2)).invalidateCompassView();
                        }
                    }
                }
                if (!this.z.isScaleControlsEnabled() || (a2 = this.u.a(AMapWidgetListener.class.hashCode())) == null || a2.size() <= 0) {
                    return;
                }
                synchronized (a2) {
                    for (int i3 = 0; i3 < a2.size(); i3++) {
                        ((AMapWidgetListener) a2.get(i3)).invalidateScaleView();
                    }
                }
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (!this.at && this.f.getAnimateionsCount() == 0 && this.f.getStateMessageCount() == 0) {
            onChangeFinish();
        }
    }

    private void k() {
        if (!this.J) {
            this.j.sendEmptyMessage(16);
            this.J = true;
            b();
        }
        long j = this.bd;
        if (j < 2) {
            this.bd = j + 1;
            return;
        }
        final eb d2 = this.C.d();
        if (d2 == null || d2.getVisibility() == 8) {
            return;
        }
        ds.a(this.e, System.currentTimeMillis() - this.aH);
        this.j.post(new Runnable() { // from class: com.amap.api.col.3sl.l.10
            @Override // java.lang.Runnable
            public final void run() {
                if (l.this.H) {
                    return;
                }
                try {
                    if (l.this.c != null) {
                        l lVar = l.this;
                        lVar.setIndoorBuildingInfo(lVar.c);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                d2.a();
            }
        });
        this.f.setStyleChangeGradualEnable(this.F, true);
    }

    final void b() {
        this.j.obtainMessage(17, 1, 0).sendToTarget();
    }

    private void b(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.12
            @Override // java.lang.Runnable
            public final void run() {
                if (!l.this.av || l.this.f == null) {
                    return;
                }
                l.this.f.setHighlightSubwayEnable(i, false);
            }
        });
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getMapAngle(int i) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getSR();
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void getGeoCenter(int i, IPoint iPoint) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            iPoint.x = (int) mapConfig.getSX();
            iPoint.y = (int) this.b.getSY();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getCameraDegree(int i) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getSC();
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void addGestureMapMessage(int i, AbstractGestureMapMessage abstractGestureMapMessage) {
        if (!this.av || this.f == null) {
            return;
        }
        try {
            abstractGestureMapMessage.isUseAnchor = this.I;
            abstractGestureMapMessage.anchorX = this.b.getAnchorX();
            abstractGestureMapMessage.anchorY = this.b.getAnchorY();
            this.f.addGestureMessage(i, abstractGestureMapMessage, this.z.isGestureScaleByMapCenter(), this.b.getAnchorX(), this.b.getAnchorY());
        } catch (RemoteException unused) {
        }
    }

    private void c(int i) {
        GLMapRender gLMapRender = this.am;
        if (gLMapRender != null) {
            gLMapRender.renderPause();
        }
        f(i);
    }

    private void d(int i) {
        f(i);
        GLMapRender gLMapRender = this.am;
        if (gLMapRender != null) {
            gLMapRender.renderResume();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void resetRenderTimeLongLong() {
        GLMapRender gLMapRender = this.am;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(30);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void resetRenderTime() {
        GLMapRender gLMapRender = this.am;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(2);
        }
    }

    private void l() {
        GLMapRender gLMapRender = this.am;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(2);
        }
    }

    private void m() {
        GLMapRender gLMapRender;
        if (!this.av || (gLMapRender = this.am) == null || gLMapRender.isRenderPause()) {
            return;
        }
        requestRender();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void requestRender() {
        GLMapRender gLMapRender = this.am;
        if (gLMapRender == null || gLMapRender.isRenderPause()) {
            return;
        }
        this.B.requestRender();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void changeMapLogo(int i, boolean z) {
        if (this.G.get()) {
            return;
        }
        try {
            List a2 = this.u.a(AMapWidgetListener.class.hashCode());
            if (a2 == null || a2.size() <= 0) {
                return;
            }
            this.C.g(Boolean.valueOf(!z));
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getRenderMode() {
        return this.B.getRenderMode();
    }

    private void n() {
        if (this.ac) {
            return;
        }
        try {
            this.aa.setName("AuthThread");
            this.aa.start();
            this.ac = true;
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    private void o() {
        if (this.ad) {
            return;
        }
        try {
            if (this.ab == null) {
                this.ab = new r(this.e, this);
            }
            this.ab.setName("AuthProThread");
            this.ab.start();
            this.ad = true;
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getMapZoomScale() {
        return this.aq;
    }

    public final void a(int i, int i2, int i3, int i4) {
        synchronized (this) {
            a(i, i2, i3, i4, false, false, (StyleItem[]) null);
        }
    }

    private void a(final int i, final int i2, final int i3, final int i4, final boolean z, final boolean z2, final StyleItem[] styleItemArr) {
        synchronized (this) {
            if (this.aw && this.av && this.f1273a) {
                e(i3);
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.13
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setMapModeAndStyle(i, i2, i3, i4, z, z2, styleItemArr);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                return;
            }
            this.aK.g = i;
            this.aK.d = i2;
            this.aK.e = i3;
            this.aK.f = i4;
            this.aK.b = true;
        }
    }

    private void e(int i) {
        eg egVar = this.C;
        if (egVar != null) {
            if (i == 0) {
                if (egVar.b()) {
                    this.C.g(Boolean.FALSE);
                    this.C.c();
                    return;
                }
                return;
            }
            if (egVar.b()) {
                return;
            }
            this.C.g(Boolean.TRUE);
            this.C.c();
        }
    }

    private void f(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.14
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    l.this.f.clearAllMessages(i);
                    l.this.f.clearAnimations(i, true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public final void a(final int i, final boolean z) {
        if (this.av && this.aw) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.15
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        l.this.f.setBuildingEnable(i, z);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            this.aM.c = z;
            this.aM.b = true;
            this.aM.g = i;
        }
    }

    public final void b(final int i, final boolean z) {
        if (this.av && this.aw) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.16
                @Override // java.lang.Runnable
                public final void run() {
                    if (l.this.f != null) {
                        if (z) {
                            l.this.f.setAllContentEnable(i, true);
                        } else {
                            l.this.f.setAllContentEnable(i, false);
                        }
                        l.this.f.setSimple3DEnable(i, false);
                    }
                }
            });
        } else {
            this.aS.c = z;
            this.aS.b = true;
            this.aS.g = i;
        }
    }

    public final void c(final int i, final boolean z) {
        if (this.av && this.aw) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.17
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (z) {
                            l.this.f.setBuildingTextureEnable(i, true);
                        } else {
                            l.this.f.setBuildingTextureEnable(i, false);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            this.aV.c = z;
            this.aV.b = true;
            this.aV.g = i;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void createSurface(int i, GL10 gl10, EGLConfig eGLConfig) {
        synchronized (this) {
            dx.a(dw.c, "createSurface");
            this.aH = System.currentTimeMillis();
            if (this.Y == 3) {
                this.C.d().a(eb.b);
            } else {
                this.C.d().a(eb.f986a);
            }
            this.aw = false;
            this.g = this.B.getWidth();
            this.h = this.B.getHeight();
            this.ay = false;
            try {
                AeUtil.loadLib(this.e);
                dx.a(dw.c, "load lib complete");
                AeUtil.initCrashHandle(this.e);
                GLMapEngine.InitParam initResource = AeUtil.initResource(this.e);
                dx.a(dw.c, "load res complete");
                this.f.createAMapInstance(initResource);
                dx.a(dw.c, "create engine complete");
                this.aE = new co();
                dx.a(dw.c, "init shader complete");
                com.autonavi.extra.b bVar = this.aW;
                if (bVar != null) {
                    bVar.i();
                }
                this.av = true;
                this.l = gl10.glGetString(7937);
            } catch (Throwable th) {
                dv.a(th);
                iv.c(th, "AMapDElegateImp", "createSurface");
                dx.b(dw.c, "createSurface failed " + th.getMessage());
                ds.b(this.e, "init failed:" + th.getMessage());
            }
            GLMapState mapState = this.f.getMapState(this.F);
            if (mapState != null && mapState.getNativeInstance() != 0) {
                mapState.setMapGeoCenter((int) this.b.getSX(), (int) this.b.getSY());
                mapState.setMapAngle(this.b.getSR());
                mapState.setMapZoomer(this.b.getSZ());
                mapState.setCameraDegree(this.b.getSC());
            }
            n();
            CustomRenderer customRenderer = this.af;
            if (customRenderer != null) {
                customRenderer.onSurfaceCreated(gl10, eGLConfig);
            }
            com.autonavi.extra.b bVar2 = this.aW;
            if (bVar2 != null) {
                bVar2.c();
            }
            this.D.onCreateAMapInstance();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void changeSurface(int i, GL10 gl10, int i2, int i3) {
        WindowManager windowManager;
        dx.a(dw.c, "changeSurface " + i2 + " " + i3);
        this.ay = false;
        if (!this.av) {
            createSurface(i, gl10, null);
        }
        x xVar = this.an;
        if (xVar != null && this.e != null && ((this.g != xVar.b() || this.h != this.an.c()) && (windowManager = (WindowManager) this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)) != null)) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (defaultDisplay != null) {
                defaultDisplay.getRealMetrics(displayMetrics);
                this.an.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
        }
        this.g = i2;
        this.h = i3;
        this.X = new Rect(0, 0, i2, i3);
        this.F = a(i, new Rect(0, 0, this.g, this.h), this.g, this.h);
        dx.a(dw.c, "create engine with frame complete");
        if (!this.aw) {
            MapConfig mapConfig = this.b;
            if (mapConfig != null) {
                mapConfig.setMapZoomScale(this.aq);
                this.b.setMapWidth(i2);
                this.b.setMapHeight(i3);
            }
            this.f.setIndoorEnable(this.F, false);
            this.f.setSimple3DEnable(this.F, false);
            this.f.setStyleChangeGradualEnable(this.F, false);
            this.f.initMapOpenLayer("{\"bounds\" : [{\"x2\" : 235405312,\"x1\" : 188874751,\"y2\" : 85065727,\"y1\" : 122421247}],\"sublyr\" : [{\"type\" : 4,\"sid\" : 9000006,\"zlevel\" : 2}],\"id\" : 9006,\"minzoom\" : 6,\"update_period\" : 90,\"maxzoom\" : 20,\"cachemode\" : 2,\"url\" : \"http://mpsapi.amap.com/ws/mps/lyrdata/ugc/\"}");
            GLMapEngine.InitParam initParam = new GLMapEngine.InitParam();
            AeUtil.initIntersectionRes(this.e, initParam);
            this.f.setVectorOverlayPath(initParam.mIntersectionResPath);
        }
        synchronized (this) {
            this.aw = true;
        }
        if (!this.I) {
            this.b.setAnchorX(i2 >> 1);
            this.b.setAnchorY(i3 >> 1);
        } else {
            this.b.setAnchorX(Math.max(1, Math.min(this.aB, i2 - 1)));
            this.b.setAnchorY(Math.max(1, Math.min(this.aC, i3 - 1)));
        }
        this.f.setProjectionCenter(this.F, this.b.getAnchorX(), this.b.getAnchorY());
        this.f1273a = true;
        if (this.aS.b) {
            this.aS.run();
        }
        if (this.aK.b) {
            this.aK.run();
        }
        if (this.aL.b) {
            this.aL.run();
        }
        if (this.aI.b) {
            this.aI.run();
        }
        if (this.aM.b) {
            this.aM.run();
        }
        if (this.aV.b) {
            this.aV.run();
        }
        if (this.aN.b) {
            this.aN.run();
        }
        if (this.aO.b) {
            this.aO.run();
        }
        if (this.aP.b) {
            this.aP.run();
        }
        if (this.aT.b) {
            this.aT.run();
        }
        if (this.aJ.b) {
            this.aJ.run();
        }
        if (this.aQ.b) {
            this.aQ.run();
        }
        a aVar = this.aR;
        if (aVar != null) {
            aVar.run();
        }
        CustomRenderer customRenderer = this.af;
        if (customRenderer != null) {
            customRenderer.onSurfaceChanged(gl10, i2, i3);
        }
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            bVar.d();
        }
        Handler handler = this.j;
        if (handler != null) {
            handler.post(this.aU);
        }
        redrawInfoWindow();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void destroySurface(int i) {
        this.az.lock();
        try {
            if (this.av) {
                EGL14.eglGetCurrentContext();
                EGLContext eGLContext = EGL14.EGL_NO_CONTEXT;
                r();
                GLMapEngine gLMapEngine = this.f;
                if (gLMapEngine != null) {
                    if (gLMapEngine.getOverlayBundle(this.F) != null) {
                        this.f.getOverlayBundle(this.F).removeAll(true);
                    }
                    this.f.destroyAMapEngine();
                    this.f = null;
                    int i2 = this.bb;
                    if (i2 > 0) {
                        ds.a(this.e, i2);
                    }
                    dx.a(dw.c, "destroy engine complete");
                }
                com.autonavi.extra.b bVar = this.aW;
                if (bVar != null) {
                    bVar.f();
                }
            }
            this.av = false;
            this.aw = false;
            this.ay = false;
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final Context getContext() {
        return this.e;
    }

    private int a(int i, Rect rect, int i2, int i3) {
        int i4;
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine == null || i < 0) {
            i4 = 0;
        } else {
            i4 = gLMapEngine.getEngineIDWithType(i);
            if (!this.f.isEngineCreated(i4)) {
                int i5 = this.e.getResources().getDisplayMetrics().densityDpi;
                float f = this.e.getResources().getDisplayMetrics().density;
                NativeTextGenerate.getInstance().setDensity(f);
                GLMapEngine.MapViewInitParam mapViewInitParam = new GLMapEngine.MapViewInitParam();
                mapViewInitParam.engineId = i4;
                mapViewInitParam.x = rect.left;
                mapViewInitParam.y = rect.top;
                mapViewInitParam.width = rect.width();
                mapViewInitParam.height = rect.height();
                mapViewInitParam.screenWidth = i2;
                mapViewInitParam.screenHeight = i3;
                mapViewInitParam.screenScale = f;
                mapViewInitParam.textScale = this.ar * f;
                mapViewInitParam.mapZoomScale = this.aq;
                mapViewInitParam.taskThreadCount = 3;
                this.f.createAMapEngineWithFrame(mapViewInitParam);
                GLMapState mapState = this.f.getMapState(i4);
                mapState.setMapZoomer(this.b.getSZ());
                mapState.setCameraDegree(this.b.getSC());
                mapState.setMapAngle(this.b.getSR());
                mapState.setMapGeoCenter(this.b.getSX(), this.b.getSY());
                this.f.setMapState(i4, mapState);
                this.aq = mapState.calMapZoomScalefactor(i2, i3, i5);
                this.f.setOvelayBundle(i4, new GLOverlayBundle<>(i4, this));
            } else {
                a(i4, rect.left, rect.top, rect.width(), rect.height(), i2, i3);
            }
        }
        this.G.set(false);
        return i4;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final int getEngineIDWithGestureInfo(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.getEngineIDWithGestureInfo(eAMapPlatformGestureInfo);
        }
        return this.F;
    }

    private void a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            gLMapEngine.setServiceViewRect(i, i2, i3, i4, i5, i6, i7);
        }
    }

    private boolean g(int i) {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.getSrvViewStateBoolValue(i, 7);
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final CameraPosition getCameraPosition() throws RemoteException {
        return getCameraPositionPrj(this.I);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getMaxZoomLevel() {
        try {
            MapConfig mapConfig = this.b;
            if (mapConfig != null) {
                return mapConfig.getMaxZoomLevel();
            }
            return 20.0f;
        } catch (Throwable th) {
            dv.a(th);
            return 20.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getMinZoomLevel() {
        try {
            MapConfig mapConfig = this.b;
            if (mapConfig != null) {
                return mapConfig.getMinZoomLevel();
            }
            return 3.0f;
        } catch (Throwable th) {
            dv.a(th);
            return 3.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void moveCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        try {
            moveCamera(cameraUpdate.getCameraUpdateFactoryDelegate());
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void moveCamera(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        if (this.f == null || this.G.get()) {
            return;
        }
        try {
            if (this.H && this.f.getStateMessageCount() > 0) {
                AbstractCameraUpdateMessage c2 = aj.c();
                c2.nowType = AbstractCameraUpdateMessage.Type.changeGeoCenterZoomTiltBearing;
                c2.geoPoint = new DPoint(this.b.getSX(), this.b.getSY());
                c2.zoom = this.b.getSZ();
                c2.bearing = this.b.getSR();
                c2.tilt = this.b.getSC();
                this.f.addMessage(abstractCameraUpdateMessage, false);
                while (this.f.getStateMessageCount() > 0) {
                    AbstractCameraUpdateMessage stateMessage = this.f.getStateMessage();
                    if (stateMessage != null) {
                        stateMessage.mergeCameraUpdateDelegate(c2);
                    }
                }
                abstractCameraUpdateMessage = c2;
            }
        } catch (Throwable th) {
            dv.a(th);
        }
        resetRenderTime();
        this.f.clearAnimations(this.F, false);
        abstractCameraUpdateMessage.isChangeFinished = true;
        a(abstractCameraUpdateMessage);
        this.f.addMessage(abstractCameraUpdateMessage, false);
    }

    private void a(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        abstractCameraUpdateMessage.isUseAnchor = this.I;
        if (this.I) {
            abstractCameraUpdateMessage.anchorX = this.b.getAnchorX();
            abstractCameraUpdateMessage.anchorY = this.b.getAnchorY();
        }
        if (abstractCameraUpdateMessage.width == 0) {
            abstractCameraUpdateMessage.width = getMapWidth();
        }
        if (abstractCameraUpdateMessage.height == 0) {
            abstractCameraUpdateMessage.height = getMapHeight();
        }
        abstractCameraUpdateMessage.mapConfig = this.b;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void animateCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        animateCamera(cameraUpdate.getCameraUpdateFactoryDelegate());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void animateCamera(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        animateCameraWithDurationAndCallback(abstractCameraUpdateMessage, 250L, (AMap.CancelableCallback) null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void animateCameraWithCallback(CameraUpdate cameraUpdate, AMap.CancelableCallback cancelableCallback) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        animateCameraWithDurationAndCallback(cameraUpdate, 250L, cancelableCallback);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void animateCameraWithDurationAndCallback(CameraUpdate cameraUpdate, long j, AMap.CancelableCallback cancelableCallback) {
        if (cameraUpdate == null) {
            return;
        }
        animateCameraWithDurationAndCallback(cameraUpdate.getCameraUpdateFactoryDelegate(), j, cancelableCallback);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void animateCameraWithDurationAndCallback(AbstractCameraUpdateMessage abstractCameraUpdateMessage, long j, AMap.CancelableCallback cancelableCallback) {
        if (abstractCameraUpdateMessage == null || this.G.get() || this.f == null) {
            return;
        }
        abstractCameraUpdateMessage.mCallback = cancelableCallback;
        abstractCameraUpdateMessage.mDuration = j;
        if (this.H || getMapHeight() == 0 || getMapWidth() == 0) {
            try {
                moveCamera(abstractCameraUpdateMessage);
                if (abstractCameraUpdateMessage.mCallback != null) {
                    abstractCameraUpdateMessage.mCallback.onFinish();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                dv.a(th);
                return;
            }
        }
        try {
            this.f.interruptAnimation();
            resetRenderTime();
            a(abstractCameraUpdateMessage);
            this.f.addMessage(abstractCameraUpdateMessage, true);
        } catch (Throwable th2) {
            dv.a(th2);
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void stopAnimation() throws RemoteException {
        try {
            GLMapEngine gLMapEngine = this.f;
            if (gLMapEngine != null) {
                gLMapEngine.interruptAnimation();
            }
            resetRenderTime();
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Polyline addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        if (polylineOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String createId = this.D.createId("POLYLINE");
            return (Polyline) this.D.addOverlayObject(createId, new Polyline(this.D, polylineOptions, createId), polylineOptions);
        } catch (Throwable th) {
            dv.a(th);
            dx.a(dw.d, "addPolyline failed " + th.getMessage(), polylineOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final BuildingOverlay addBuildingOverlay() {
        try {
            ds.h(this.e);
            String createId = this.D.createId("BUILDINGOVERLAY");
            BuildingOverlay buildingOverlay = new BuildingOverlay(this.D, createId);
            Field declaredField = buildingOverlay.getClass().getDeclaredField("buildingOverlayTotalOptions");
            if (declaredField == null) {
                return null;
            }
            resetRenderTime();
            declaredField.setAccessible(true);
            Object obj = declaredField.get(buildingOverlay);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return (iGlOverlayLayer == null || !(obj instanceof BaseOptions)) ? buildingOverlay : (BuildingOverlay) iGlOverlayLayer.addOverlayObject(createId, buildingOverlay, (BaseOptions) obj);
        } catch (Exception e) {
            e.printStackTrace();
            dv.a(e);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final GL3DModel addGLModel(GL3DModelOptions gL3DModelOptions) {
        resetRenderTime();
        String createId = this.D.createId("GL3DMODEL");
        GL3DModel gL3DModel = new GL3DModel(this.D, gL3DModelOptions, createId);
        this.D.addOverlayObject(createId, gL3DModel, gL3DModelOptions);
        return gL3DModel;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final ParticleOverlay addParticleOverlay(ParticleOverlayOptions particleOverlayOptions) {
        if (particleOverlayOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            ds.c(this.e);
            String createId = this.D.createId("PARTICLEOVERLAY");
            return (ParticleOverlay) this.D.addOverlayObject(createId, new ParticleOverlay(this.D, particleOverlayOptions, createId), particleOverlayOptions);
        } catch (Throwable th) {
            dv.a(th);
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        if (navigateArrowOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            NavigateArrowOptions m87clone = navigateArrowOptions.m87clone();
            String createId = this.D.createId("NAVIGATEARROW");
            NavigateArrow navigateArrow = new NavigateArrow(this.D, m87clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (NavigateArrow) iGlOverlayLayer.addOverlayObject(createId, navigateArrow, m87clone) : navigateArrow;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Polygon addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        if (polygonOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            PolygonOptions m88clone = polygonOptions.m88clone();
            String createId = this.D.createId("POLYGON");
            Polygon polygon = new Polygon(this.D, m88clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (Polygon) iGlOverlayLayer.addOverlayObject(createId, polygon, m88clone) : polygon;
        } catch (Throwable th) {
            dv.a(th);
            dx.a(dw.d, "addPolygon failed " + th.getMessage(), polygonOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Circle addCircle(CircleOptions circleOptions) throws RemoteException {
        if (circleOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            CircleOptions m82clone = circleOptions.m82clone();
            String createId = this.D.createId("CIRCLE");
            Circle circle = new Circle(this.D, m82clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (Circle) iGlOverlayLayer.addOverlayObject(createId, circle, m82clone) : circle;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Arc addArc(ArcOptions arcOptions) throws RemoteException {
        if (arcOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            ArcOptions m80clone = arcOptions.m80clone();
            String createId = this.D.createId("ARC");
            Arc arc = new Arc(this.D, m80clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (Arc) iGlOverlayLayer.addOverlayObject(createId, arc, m80clone) : arc;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        if (groundOverlayOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            GroundOverlayOptions m83clone = groundOverlayOptions.m83clone();
            String createId = this.D.createId("GROUNDOVERLAY");
            GroundOverlay groundOverlay = new GroundOverlay(this.D, m83clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (GroundOverlay) iGlOverlayLayer.addOverlayObject(createId, groundOverlay, m83clone) : groundOverlay;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final MultiPointOverlay addMultiPointOverlay(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        if (multiPointOverlayOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            MultiPointOverlayOptions m86clone = multiPointOverlayOptions.m86clone();
            String createId = this.D.createId("MULTIOVERLAY");
            MultiPointOverlay multiPointOverlay = new MultiPointOverlay(this.D, m86clone, createId);
            IGlOverlayLayer iGlOverlayLayer = this.D;
            return iGlOverlayLayer != null ? (MultiPointOverlay) iGlOverlayLayer.addOverlayObject(createId, multiPointOverlay, m86clone) : multiPointOverlay;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        try {
            resetRenderTime();
            MarkerOptions m85clone = markerOptions.m85clone();
            String createId = this.D.createId("MARKER");
            Marker marker = new Marker(this.D, m85clone, createId);
            this.D.addOverlayObject(createId, marker, m85clone);
            return marker;
        } catch (Throwable th) {
            dv.a(th);
            dx.a(dw.d, "addMarker failed " + th.getMessage(), markerOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Text addText(TextOptions textOptions) throws RemoteException {
        try {
            resetRenderTime();
            String createId = this.D.createId("TEXT");
            TextOptions m89clone = textOptions.m89clone();
            MarkerOptions a2 = cm.a(m89clone);
            Marker marker = new Marker(this.D, a2, createId);
            marker.setObject(m89clone.getObject());
            this.D.addOverlayObject(createId, marker, a2);
            return new Text(marker, m89clone);
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        try {
            resetRenderTime();
            ArrayList<Marker> arrayList2 = new ArrayList<>();
            final LatLngBounds.Builder builder = LatLngBounds.builder();
            for (int i = 0; i < arrayList.size(); i++) {
                MarkerOptions markerOptions = arrayList.get(i);
                if (arrayList.get(i) != null) {
                    arrayList2.add(addMarker(markerOptions));
                    if (markerOptions.getPosition() != null) {
                        builder.include(markerOptions.getPosition());
                    }
                }
            }
            if (z && arrayList2.size() > 0) {
                getMainHandler().postDelayed(new Runnable() { // from class: com.amap.api.col.3sl.l.18
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.moveCamera(aj.a(builder.build(), 50));
                        } catch (Throwable unused) {
                        }
                    }
                }, 50L);
            }
            return arrayList2;
        } catch (Throwable th) {
            dv.a(th);
            dx.a(dw.d, "addMarkers failed " + th.getMessage(), arrayList);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        try {
            TileProvider tileProvider = tileOverlayOptions.getTileProvider();
            if (tileProvider != null && (tileProvider instanceof HeatmapTileProvider)) {
                ds.a(this.e);
            }
            String createId = this.D.createId("TILEOVERLAY");
            TileOverlay tileOverlay = new TileOverlay(this.D, tileOverlayOptions, createId);
            this.D.addOverlayObject(createId, tileOverlay, tileOverlayOptions);
            return tileOverlay;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final MVTTileOverlay addMVTTileOverlay(MVTTileOverlayOptions mVTTileOverlayOptions) throws RemoteException {
        try {
            String createId = this.D.createId("MVTTILEOVERLAY");
            MVTTileOverlay mVTTileOverlay = new MVTTileOverlay(this.D, mVTTileOverlayOptions, createId);
            this.D.addOverlayObject(createId, mVTTileOverlay, mVTTileOverlayOptions);
            return mVTTileOverlay;
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final HeatMapLayer addHeatMapLayer(HeatMapLayerOptions heatMapLayerOptions) throws RemoteException {
        try {
            resetRenderTime();
            if (heatMapLayerOptions == null) {
                return null;
            }
            String createId = this.D.createId("HEATMAPLAYER");
            return (HeatMapLayer) this.D.addOverlayObject(createId, new HeatMapLayer(this.D, heatMapLayerOptions, createId), heatMapLayerOptions);
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final HeatMapGridLayer addHeatMapGridLayer(HeatMapGridLayerOptions heatMapGridLayerOptions) throws RemoteException {
        try {
            resetRenderTime();
            if (heatMapGridLayerOptions == null) {
                return null;
            }
            String createId = this.D.createId("HEATMAPGRIDLAYER");
            return (HeatMapGridLayer) this.D.addOverlayObject(createId, new HeatMapGridLayer(this.D, heatMapGridLayerOptions, createId), heatMapGridLayerOptions);
        } catch (Throwable th) {
            dv.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "clear");
            dv.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void clear(boolean z) throws RemoteException {
        String str;
        String str2;
        try {
            hideInfoWindow();
            cj cjVar = this.K;
            if (cjVar != null) {
                if (z) {
                    str2 = cjVar.d();
                    str = this.K.e();
                    this.D.clear(str2, str);
                    queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.19
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (l.this.f == null || l.this.G.get()) {
                                return;
                            }
                            l.this.f.removeNativeAllOverlay(l.this.F);
                        }
                    });
                    resetRenderTime();
                }
                cjVar.f();
            }
            str = "";
            str2 = null;
            this.D.clear(str2, str);
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.19
                @Override // java.lang.Runnable
                public final void run() {
                    if (l.this.f == null || l.this.G.get()) {
                        return;
                    }
                    l.this.f.removeNativeAllOverlay(l.this.F);
                }
            });
            resetRenderTime();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "clear");
            dv.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getMapType() throws RemoteException {
        return this.Y;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapType(int i) throws RemoteException {
        MapConfig mapConfig;
        if (i != this.Y || ((mapConfig = this.b) != null && mapConfig.isCustomStyleEnable())) {
            this.Y = i;
            h(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005e A[Catch: all -> 0x007a, TryCatch #0 {all -> 0x007a, blocks: (B:7:0x0023, B:9:0x003a, B:11:0x003e, B:13:0x0044, B:14:0x0058, B:15:0x0076, B:22:0x004a, B:23:0x005e, B:25:0x006c, B:26:0x0071, B:36:0x001d), top: B:35:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a A[Catch: all -> 0x007a, TryCatch #0 {all -> 0x007a, blocks: (B:7:0x0023, B:9:0x003a, B:11:0x003e, B:13:0x0044, B:14:0x0058, B:15:0x0076, B:22:0x004a, B:23:0x005e, B:25:0x006c, B:26:0x0071, B:36:0x001d), top: B:35:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void h(int r12) {
        /*
            r11 = this;
            r11.Y = r12
            r0 = 0
            r1 = 1
            if (r12 != r1) goto L7
            goto L1f
        L7:
            r2 = 2
            if (r12 != r2) goto Lc
            r2 = r1
            goto L20
        Lc:
            r3 = 3
            r4 = 4
            if (r12 != r3) goto L12
            r12 = r1
            goto L15
        L12:
            if (r12 != r4) goto L19
            r12 = r0
        L15:
            r6 = r12
            r5 = r0
            r7 = r4
            goto L23
        L19:
            r3 = 5
            if (r12 != r3) goto L1d
            goto L20
        L1d:
            r11.Y = r1     // Catch: java.lang.Throwable -> L7a
        L1f:
            r2 = r0
        L20:
            r6 = r0
            r7 = r6
            r5 = r2
        L23:
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            r12.setMapStyleMode(r5)     // Catch: java.lang.Throwable -> L7a
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            r12.setMapStyleTime(r6)     // Catch: java.lang.Throwable -> L7a
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            r12.setMapStyleState(r7)     // Catch: java.lang.Throwable -> L7a
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            boolean r12 = r12.isCustomStyleEnable()     // Catch: java.lang.Throwable -> L7a
            if (r12 == 0) goto L5e
            com.amap.api.col.3sl.k r12 = r11.aG     // Catch: java.lang.Throwable -> L7a
            if (r12 == 0) goto L4a
            boolean r12 = r12.d()     // Catch: java.lang.Throwable -> L7a
            if (r12 == 0) goto L4a
            com.amap.api.col.3sl.k r12 = r11.aG     // Catch: java.lang.Throwable -> L7a
            r12.e()     // Catch: java.lang.Throwable -> L7a
            goto L58
        L4a:
            int r4 = r11.F     // Catch: java.lang.Throwable -> L7a
            r8 = 1
            r9 = 0
            r10 = 0
            r3 = r11
            r3.a(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L7a
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            r12.setCustomStyleEnable(r0)     // Catch: java.lang.Throwable -> L7a
        L58:
            com.amap.api.col.3sl.ae r12 = r11.z     // Catch: java.lang.Throwable -> L7a
            r12.setLogoEnable(r1)     // Catch: java.lang.Throwable -> L7a
            goto L76
        L5e:
            com.autonavi.base.amap.mapcore.MapConfig r12 = r11.b     // Catch: java.lang.Throwable -> L7a
            java.lang.String r12 = r12.getMapLanguage()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r0 = "en"
            boolean r12 = r12.equals(r0)     // Catch: java.lang.Throwable -> L7a
            if (r12 == 0) goto L71
            java.lang.String r12 = "zh_cn"
            r11.setMapLanguage(r12)     // Catch: java.lang.Throwable -> L7a
        L71:
            int r12 = r11.F     // Catch: java.lang.Throwable -> L7a
            r11.a(r12, r5, r6, r7)     // Catch: java.lang.Throwable -> L7a
        L76:
            r11.resetRenderTime()     // Catch: java.lang.Throwable -> L7a
            return
        L7a:
            r12 = move-exception
            java.lang.String r0 = "AMapDelegateImp"
            java.lang.String r1 = "setMaptype"
            com.amap.api.col.p0003sl.iv.c(r12, r0, r1)
            r12.printStackTrace()
            com.amap.api.col.p0003sl.dv.a(r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.l.h(int):void");
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean isTrafficEnabled() throws RemoteException {
        return this.b.isTrafficEnabled();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setTrafficEnabled(final boolean z) throws RemoteException {
        try {
            if (this.av && !this.G.get()) {
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.20
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (l.this.b.isTrafficEnabled() != z) {
                                l.this.b.setTrafficEnabled(z);
                                l.this.am.setTrafficMode(z);
                                l.this.f.setTrafficEnable(l.this.F, z);
                                l.this.resetRenderTime();
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                            dv.a(th);
                        }
                    }
                });
                return;
            }
            this.aI.c = z;
            this.aI.b = true;
            this.aI.g = this.F;
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean isIndoorEnabled() throws RemoteException {
        return this.b.isIndoorEnable();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setIndoorEnabled(final boolean z) throws RemoteException {
        List a2;
        try {
            if (this.av && !this.G.get()) {
                this.b.setIndoorEnable(z);
                resetRenderTime();
                if (z) {
                    GLMapEngine gLMapEngine = this.f;
                    if (gLMapEngine != null) {
                        gLMapEngine.setIndoorEnable(this.F, true);
                    }
                } else {
                    GLMapEngine gLMapEngine2 = this.f;
                    if (gLMapEngine2 != null) {
                        gLMapEngine2.setIndoorEnable(this.F, false);
                    }
                    MapConfig mapConfig = this.b;
                    mapConfig.maxZoomLevel = mapConfig.isSetLimitZoomLevel() ? this.b.getMaxZoomLevel() : 20.0f;
                    try {
                        if (this.z.isZoomControlsEnabled() && (a2 = this.u.a(AMapWidgetListener.class.hashCode())) != null && a2.size() > 0) {
                            synchronized (a2) {
                                for (int i = 0; i < a2.size(); i++) {
                                    ((AMapWidgetListener) a2.get(i)).invalidateZoomController(this.b.getSZ());
                                }
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                ds.c(this.e, z);
                if (this.z.isIndoorSwitchEnabled()) {
                    this.j.post(new Runnable() { // from class: com.amap.api.col.3sl.l.22
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (!z) {
                                if (l.this.C != null) {
                                    l.this.C.i(Boolean.FALSE);
                                    return;
                                }
                                return;
                            }
                            l.this.showIndoorSwitchControlsEnabled(true);
                        }
                    });
                    return;
                }
                return;
            }
            this.aT.c = z;
            this.aT.b = true;
            this.aT.g = this.F;
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void set3DBuildingEnabled(boolean z) throws RemoteException {
        try {
            c(this.F);
            a(this.F, z);
            d(this.F);
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean isMyLocationEnabled() throws RemoteException {
        return this.E;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMyLocationEnabled(boolean z) throws RemoteException {
        if (this.G.get()) {
            return;
        }
        try {
            eg egVar = this.C;
            if (egVar != null) {
                LocationSource locationSource = this.L;
                if (locationSource == null) {
                    egVar.h(Boolean.FALSE);
                } else if (z) {
                    locationSource.activate(this.t);
                    this.C.h(Boolean.TRUE);
                    if (this.K == null) {
                        this.K = new cj(this, this.e);
                    }
                } else {
                    cj cjVar = this.K;
                    if (cjVar != null) {
                        cjVar.c();
                        this.K = null;
                    }
                    this.L.deactivate();
                }
            }
            if (!z) {
                this.z.setMyLocationButtonEnabled(z);
            }
            this.E = z;
            resetRenderTime();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "setMyLocationEnabled");
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.23
            @Override // java.lang.Runnable
            public final void run() {
                if (l.this.f != null) {
                    l.this.f.setOfflineDataEnable(l.this.F, z);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMyLocationStyle(MyLocationStyle myLocationStyle) throws RemoteException {
        if (this.G.get()) {
            return;
        }
        try {
            if (this.K == null) {
                this.K = new cj(this, this.e);
            }
            if (this.K != null) {
                if (myLocationStyle.getInterval() < 1000) {
                    myLocationStyle.interval(1000L);
                }
                LocationSource locationSource = this.L;
                if (locationSource != null && (locationSource instanceof au)) {
                    ((au) locationSource).a(myLocationStyle.getInterval());
                    ((au) this.L).a(myLocationStyle.getMyLocationType());
                }
                this.K.a(myLocationStyle);
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMyLocationType(int i) throws RemoteException {
        try {
            cj cjVar = this.K;
            if (cjVar == null || cjVar.a() == null) {
                return;
            }
            this.K.a().myLocationType(i);
            setMyLocationStyle(this.K.a());
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final List<Marker> getMapScreenMarkers() throws RemoteException {
        if (!dv.a(getMapWidth(), getMapHeight())) {
            return new ArrayList();
        }
        if (this.G.get()) {
            return new ArrayList();
        }
        return this.D.getMapScreenMarkers();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapTextEnable(final boolean z) throws RemoteException {
        try {
            if (this.av && this.aw) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.24
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setLabelEnable(l.this.F, z);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aN.c = z;
                this.aN.b = true;
                this.aN.g = this.F;
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setRoadArrowEnable(final boolean z) throws RemoteException {
        try {
            if (this.av && this.aw) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.25
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setRoadArrowEnable(l.this.F, z);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aO.c = z;
                this.aO.b = true;
                this.aO.g = this.F;
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setNaviLabelEnable(final boolean z, final int i, final int i2) throws RemoteException {
        try {
            if (this.av && this.aw) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.26
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setNaviLabelEnable(l.this.F, z, i, i2);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                return;
            }
            this.aP.c = z;
            this.aP.h = i;
            this.aP.i = i2;
            this.aP.b = true;
            this.aP.g = this.F;
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setConstructingRoadEnable(final boolean z) {
        try {
            if (this.av && this.aw) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.27
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setMapOpenLayerEnable(z);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aQ.c = z;
                this.aQ.b = true;
                this.aQ.g = this.F;
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setTrafficStyleWithTextureData(final byte[] bArr) {
        if (this.G.get()) {
            return;
        }
        try {
            if (this.av && this.aw && bArr != null) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.28
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l.this.f.setTrafficStyleWithTextureData(l.this.F, bArr);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aR.j = bArr;
                this.aR.b = true;
                this.aR.g = this.F;
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Location getMyLocation() throws RemoteException {
        if (this.L != null) {
            return this.t.f1352a;
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            if (this.G.get()) {
                return;
            }
            LocationSource locationSource2 = this.L;
            if (locationSource2 != null && (locationSource2 instanceof au)) {
                locationSource2.deactivate();
            }
            this.L = locationSource;
            if (locationSource != null) {
                this.C.h(Boolean.TRUE);
            } else {
                this.C.h(Boolean.FALSE);
            }
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "setLocationSource");
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMyLocationRotateAngle(float f) throws RemoteException {
        try {
            cj cjVar = this.K;
            if (cjVar != null) {
                cjVar.a(f);
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final UiSettings getAMapUiSettings() throws RemoteException {
        if (this.x == null) {
            this.x = new UiSettings(this.z);
        }
        return this.x;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Projection getAMapProjection() throws RemoteException {
        return new Projection(this.y);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMapClickListener.class.hashCode(), (int) onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMapTouchListener.class.hashCode(), (int) onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnPOIClickListener.class.hashCode(), (int) onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMapLongClickListener.class.hashCode(), (int) onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMarkerClickListener.class.hashCode(), (int) onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnPolylineClickListener.class.hashCode(), (int) onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMarkerDragListener.class.hashCode(), (int) onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMaploadedListener(AMap.OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMapLoadedListener.class.hashCode(), (int) onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnCameraChangeListener.class.hashCode(), (int) onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnInfoWindowClickListener.class.hashCode(), (int) onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnIndoorBuildingActiveListener.class.hashCode(), (int) onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(AMap.OnMyLocationChangeListener.class.hashCode(), (int) onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setInfoWindowAdapter(AMap.InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        at atVar;
        if (this.G.get() || (atVar = this.w) == null) {
            return;
        }
        atVar.a(infoWindowAdapter);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setInfoWindowAdapter(AMap.CommonInfoWindowAdapter commonInfoWindowAdapter) throws RemoteException {
        at atVar;
        if (this.G.get() || (atVar = this.w) == null) {
            return;
        }
        atVar.a(commonInfoWindowAdapter);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setOnMultiPointClickListener(AMap.OnMultiPointClickListener onMultiPointClickListener) {
        this.aF = onMultiPointClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getMapContentApprovalNumber() {
        MapConfig mapConfig = this.b;
        if (mapConfig == null || mapConfig.isCustomStyleEnable()) {
            return null;
        }
        ds.d(this.e);
        String a2 = dl.a(this.e, "approval_number", "mc", "");
        return !TextUtils.isEmpty(a2) ? a2 : "GS(2021)5875号 | GS(2020)2189号";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getSatelliteImageApprovalNumber() {
        ds.e(this.e);
        String a2 = dl.a(this.e, "approval_number", "si", "");
        return !TextUtils.isEmpty(a2) ? a2 : "GS(2021)1328号";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getTerrainApprovalNumber() {
        ds.f(this.e);
        String a2 = dl.a(this.e, "approval_number", w9.m, "");
        return !TextUtils.isEmpty(a2) ? a2 : "GS(2021)6352号";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapLanguage(String str) {
        MapConfig mapConfig;
        if (TextUtils.isEmpty(str) || (mapConfig = this.b) == null || mapConfig.isCustomStyleEnable() || this.b.getMapLanguage().equals(str)) {
            return;
        }
        if (!str.equals("en")) {
            this.b.setMapLanguage("zh_cn");
            this.ae = 0;
        } else {
            if (this.Y != 1) {
                try {
                    setMapType(1);
                } catch (Throwable th) {
                    dv.a(th);
                    th.printStackTrace();
                }
            }
            this.b.setMapLanguage("en");
            this.ae = Constant.INSTALL_FAILED_UNKNOW;
        }
        try {
            b(getCameraPosition());
        } catch (Throwable th2) {
            dv.a(th2);
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setWorldVectorMapStyle(String str) {
        if (a(false, true) || TextUtils.isEmpty(str) || this.b == null || this.aY.equals(str)) {
            return;
        }
        this.aY = str;
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            bVar.i();
        }
        resetRenderTime();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getWorldVectorMapStyle() {
        return this.aY;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getCurrentWorldVectorMapStyle() {
        try {
            com.autonavi.extra.b bVar = this.aW;
            if (bVar != null) {
                Object j = bVar.j();
                if (j instanceof String) {
                    return (String) j;
                }
            }
        } catch (Throwable th) {
            dv.a(th);
        }
        return "";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void accelerateNetworkInChinese(boolean z) {
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            bVar.i();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final String getWorldVectorMapLanguage() {
        return this.aX;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void getMapPrintScreen(AMap.onMapPrintScreenListener onmapprintscreenlistener) {
        try {
            this.u.a(Integer.valueOf(AMap.onMapPrintScreenListener.class.hashCode()), (Integer) onmapprintscreenlistener);
            this.U = 20;
            this.V = true;
            resetRenderTime();
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void getMapScreenShot(AMap.OnMapScreenShotListener onMapScreenShotListener, boolean z) {
        try {
            this.u.a(Integer.valueOf(AMap.OnMapScreenShotListener.class.hashCode()), (Integer) onMapScreenShotListener);
            this.U = 20;
            this.V = z;
            resetRenderTime();
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getScalePerPixel() throws RemoteException {
        try {
            return ((float) ((((Math.cos((getCameraPosition().target.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, getZoomLevel()) * 256.0d))) * getMapZoomScale();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "getScalePerPixel");
            dv.a(th);
            th.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setRunLowFrame(boolean z) {
        if (z) {
            return;
        }
        try {
            if (this.ah == -1) {
                m();
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removecache() throws RemoteException {
        removecache(null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removecache(AMap.OnCacheRemoveListener onCacheRemoveListener) throws RemoteException {
        if (this.j == null || this.f == null) {
            return;
        }
        try {
            d dVar = new d(this.e, onCacheRemoveListener);
            this.j.removeCallbacks(dVar);
            this.j.post(dVar);
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "removecache");
            th.printStackTrace();
            dv.a(th);
        }
    }

    final class d implements Runnable {
        private Context b;
        private AMap.OnCacheRemoveListener c;

        public d(Context context, AMap.OnCacheRemoveListener onCacheRemoveListener) {
            this.b = context;
            this.c = onCacheRemoveListener;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0039, code lost:
        
            if (com.amap.api.col.p0003sl.dv.e(r2) != false) goto L20;
         */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0045 A[Catch: all -> 0x0061, TRY_LEAVE, TryCatch #3 {all -> 0x0061, blocks: (B:3:0x0002, B:5:0x001b, B:11:0x003d, B:13:0x0045), top: B:2:0x0002 }] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0035 A[Catch: all -> 0x0030, TRY_LEAVE, TryCatch #1 {all -> 0x0030, blocks: (B:61:0x0023, B:9:0x0035), top: B:60:0x0023 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 1
                android.content.Context r2 = r6.b     // Catch: java.lang.Throwable -> L61
                android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L61
                java.lang.String r3 = com.amap.api.col.p0003sl.dv.c(r2)     // Catch: java.lang.Throwable -> L61
                java.lang.String r4 = com.amap.api.col.p0003sl.dv.a(r2)     // Catch: java.lang.Throwable -> L61
                java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L61
                r5.<init>(r3)     // Catch: java.lang.Throwable -> L61
                boolean r3 = r5.exists()     // Catch: java.lang.Throwable -> L61
                if (r3 == 0) goto L20
                boolean r3 = com.autonavi.base.amap.mapcore.FileUtil.deleteFile(r5)     // Catch: java.lang.Throwable -> L61
                goto L21
            L20:
                r3 = r1
            L21:
                if (r3 == 0) goto L32
                java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L30
                r5.<init>(r4)     // Catch: java.lang.Throwable -> L30
                boolean r3 = com.autonavi.base.amap.mapcore.FileUtil.deleteFile(r5)     // Catch: java.lang.Throwable -> L30
                if (r3 == 0) goto L32
                r3 = r1
                goto L33
            L30:
                r1 = move-exception
                goto L64
            L32:
                r3 = r0
            L33:
                if (r3 == 0) goto L3c
                boolean r2 = com.amap.api.col.p0003sl.dv.e(r2)     // Catch: java.lang.Throwable -> L30
                if (r2 == 0) goto L3c
                goto L3d
            L3c:
                r1 = r0
            L3d:
                com.amap.api.col.3sl.l r2 = com.amap.api.col.p0003sl.l.this     // Catch: java.lang.Throwable -> L61
                com.amap.api.maps.interfaces.IGlOverlayLayer r2 = com.amap.api.col.p0003sl.l.g(r2)     // Catch: java.lang.Throwable -> L61
                if (r2 == 0) goto L4e
                com.amap.api.col.3sl.l r2 = com.amap.api.col.p0003sl.l.this     // Catch: java.lang.Throwable -> L61
                com.amap.api.maps.interfaces.IGlOverlayLayer r2 = com.amap.api.col.p0003sl.l.g(r2)     // Catch: java.lang.Throwable -> L61
                r2.clearTileCache()     // Catch: java.lang.Throwable -> L61
            L4e:
                com.amap.api.col.3sl.l r0 = com.amap.api.col.p0003sl.l.this     // Catch: java.lang.Throwable -> L5c
                com.autonavi.base.ae.gmap.GLMapEngine r0 = r0.f     // Catch: java.lang.Throwable -> L5c
                if (r0 == 0) goto L60
                com.amap.api.maps.AMap$OnCacheRemoveListener r0 = r6.c     // Catch: java.lang.Throwable -> L5c
                if (r0 == 0) goto L60
                r0.onRemoveCacheFinish(r1)     // Catch: java.lang.Throwable -> L5c
                goto L60
            L5c:
                r0 = move-exception
                r0.printStackTrace()
            L60:
                return
            L61:
                r2 = move-exception
                r3 = r1
                r1 = r2
            L64:
                com.amap.api.col.p0003sl.dv.a(r1)     // Catch: java.lang.Throwable -> L81
                java.lang.String r2 = "AMapDelegateImp"
                java.lang.String r4 = "RemoveCacheRunnable"
                com.amap.api.col.p0003sl.iv.c(r1, r2, r4)     // Catch: java.lang.Throwable -> L81
                com.amap.api.col.3sl.l r1 = com.amap.api.col.p0003sl.l.this     // Catch: java.lang.Throwable -> L7c
                com.autonavi.base.ae.gmap.GLMapEngine r1 = r1.f     // Catch: java.lang.Throwable -> L7c
                if (r1 == 0) goto L80
                com.amap.api.maps.AMap$OnCacheRemoveListener r1 = r6.c     // Catch: java.lang.Throwable -> L7c
                if (r1 == 0) goto L80
                r1.onRemoveCacheFinish(r0)     // Catch: java.lang.Throwable -> L7c
                goto L80
            L7c:
                r0 = move-exception
                r0.printStackTrace()
            L80:
                return
            L81:
                r0 = move-exception
                com.amap.api.col.3sl.l r1 = com.amap.api.col.p0003sl.l.this     // Catch: java.lang.Throwable -> L90
                com.autonavi.base.ae.gmap.GLMapEngine r1 = r1.f     // Catch: java.lang.Throwable -> L90
                if (r1 == 0) goto L94
                com.amap.api.maps.AMap$OnCacheRemoveListener r1 = r6.c     // Catch: java.lang.Throwable -> L90
                if (r1 == 0) goto L94
                r1.onRemoveCacheFinish(r3)     // Catch: java.lang.Throwable -> L90
                goto L94
            L90:
                r1 = move-exception
                r1.printStackTrace()
            L94:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.3sl.l.d.run():void");
        }

        public final boolean equals(Object obj) {
            return obj instanceof d;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCustomRenderer(CustomRenderer customRenderer) throws RemoteException {
        this.af = customRenderer;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCenterToPixel(int i, int i2) throws RemoteException {
        this.I = true;
        this.aB = i;
        this.aC = i2;
        if (this.aw && this.av) {
            if (this.b.getAnchorX() == this.aB && this.b.getAnchorY() == this.aC) {
                return;
            }
            this.b.setAnchorX(this.aB);
            this.b.setAnchorY(this.aC);
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.29
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        l.this.b.setAnchorX(Math.max(0, Math.min(l.this.aB, l.this.g)));
                        l.this.b.setAnchorY(Math.max(0, Math.min(l.this.aC, l.this.h)));
                        l.this.f.setProjectionCenter(l.this.F, l.this.b.getAnchorX(), l.this.b.getAnchorY());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapTextZIndex(int i) throws RemoteException {
        this.ae = i;
        this.i = false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getMapTextZIndex() throws RemoteException {
        return this.ae;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setRenderFps(int i) {
        try {
            if (i == -1) {
                this.ah = i;
            } else {
                this.ah = Math.max(10, Math.min(i, 40));
            }
            ds.g(this.e);
        } catch (Throwable th) {
            dv.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) throws RemoteException {
        if (this.G.get() || indoorBuildingInfo == null || indoorBuildingInfo.activeFloorName == null || indoorBuildingInfo.poiid == null) {
            return;
        }
        this.c = (as) indoorBuildingInfo;
        resetRenderTime();
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.30
            @Override // java.lang.Runnable
            public final void run() {
                if (l.this.f != null) {
                    l.this.f.setIndoorBuildingToBeActive(l.this.F, l.this.c.activeFloorName, l.this.c.activeFloorIndex, l.this.c.poiid);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setAMapGestureListener(AMapGestureListener aMapGestureListener) {
        x xVar = this.an;
        if (xVar != null) {
            this.v = aMapGestureListener;
            xVar.a(aMapGestureListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        try {
            MapConfig mapConfig = getMapConfig();
            if (latLng != null && latLng2 != null && this.av && !this.G.get()) {
                Pair<Float, IPoint> a2 = dv.a(mapConfig, 0, 0, 0, 0, new LatLngBounds.Builder().include(latLng).include(latLng2).build(), getMapWidth(), getMapHeight());
                if (a2 != null) {
                    return ((Float) a2.first).floatValue();
                }
                GLMapState gLMapState = new GLMapState(this.F, this.f.getNativeInstance());
                float mapZoomer = gLMapState.getMapZoomer();
                gLMapState.recycle();
                return mapZoomer;
            }
            return mapConfig.getSZ();
        } catch (Throwable th) {
            dv.a(th);
            return 0.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Pair<Float, LatLng> calculateZoomToSpanLevel(int i, int i2, int i3, int i4, LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && i == i2 && i2 == i3 && i3 == i4 && latLng.latitude == latLng2.latitude && latLng.longitude == latLng2.longitude) {
            return new Pair<>(Float.valueOf(getMaxZoomLevel()), latLng);
        }
        MapConfig mapConfig = getMapConfig();
        if (latLng != null && latLng2 != null && this.av && !this.G.get()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(latLng);
            builder.include(latLng2);
            GLMapState gLMapState = new GLMapState(this.F, this.f.getNativeInstance());
            Pair<Float, IPoint> a2 = dv.a(mapConfig, i, i2, i3, i4, builder.build(), getMapWidth(), getMapHeight());
            gLMapState.recycle();
            if (a2 == null) {
                return null;
            }
            DPoint obtain = DPoint.obtain();
            GLMapState.geo2LonLat(((IPoint) a2.second).x, ((IPoint) a2.second).y, obtain);
            Pair<Float, LatLng> pair = new Pair<>(a2.first, new LatLng(obtain.y, obtain.x));
            obtain.recycle();
            return pair;
        }
        DPoint obtain2 = DPoint.obtain();
        GLMapState.geo2LonLat((int) mapConfig.getSX(), (int) mapConfig.getSY(), obtain2);
        Pair<Float, LatLng> pair2 = new Pair<>(Float.valueOf(mapConfig.getSZ()), new LatLng(obtain2.y, obtain2.x));
        obtain2.recycle();
        return pair2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMaxZoomLevel(float f) {
        this.b.setMaxZoomLevel(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMinZoomLevel(float f) {
        this.b.setMinZoomLevel(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void resetMinMaxZoomPreference() {
        List a2;
        this.b.resetMinMaxZoomPreference();
        try {
            if (!this.z.isZoomControlsEnabled() || !this.b.isNeedUpdateZoomControllerState() || (a2 = this.u.a(AMapWidgetListener.class.hashCode())) == null || a2.size() <= 0) {
                return;
            }
            synchronized (a2) {
                for (int i = 0; i < a2.size(); i++) {
                    ((AMapWidgetListener) a2.get(i)).invalidateZoomController(this.b.getSZ());
                }
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapStatusLimits(LatLngBounds latLngBounds) {
        try {
            this.b.setLimitLatLngBounds(latLngBounds);
            p();
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    private static boolean a(LatLngBounds latLngBounds) {
        return (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) ? false : true;
    }

    private void p() {
        try {
            LatLngBounds limitLatLngBounds = this.b.getLimitLatLngBounds();
            if (this.f != null && a(limitLatLngBounds)) {
                GLMapState gLMapState = new GLMapState(this.F, this.f.getNativeInstance());
                IPoint obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, obtain);
                IPoint obtain2 = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, obtain2);
                this.b.setLimitIPoints(new IPoint[]{obtain, obtain2});
                gLMapState.recycle();
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.b.setLimitIPoints(null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final Handler getMainHandler() {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void onChangeFinish() {
        Message obtainMessage = this.j.obtainMessage();
        obtainMessage.what = 11;
        this.j.sendMessage(obtainMessage);
    }

    protected final void a(CameraPosition cameraPosition) {
        MapConfig mapConfig = this.b;
        if (mapConfig == null || mapConfig.getChangedCounter() == 0) {
            return;
        }
        try {
            if (!this.at && this.f.getAnimateionsCount() == 0 && this.f.getStateMessageCount() == 0) {
                AMapGestureListener aMapGestureListener = this.v;
                if (aMapGestureListener != null) {
                    aMapGestureListener.onMapStable();
                }
                if (this.B.isEnabled()) {
                    try {
                        List a2 = this.u.a(AMap.OnCameraChangeListener.class.hashCode());
                        if (a2 != null && a2.size() != 0) {
                            if (cameraPosition == null) {
                                try {
                                    cameraPosition = getCameraPosition();
                                } catch (Throwable th) {
                                    iv.c(th, "AMapDelegateImp", "cameraChangeFinish");
                                    th.printStackTrace();
                                }
                            }
                            synchronized (a2) {
                                Iterator it = a2.iterator();
                                while (it.hasNext()) {
                                    ((AMap.OnCameraChangeListener) it.next()).onCameraChangeFinish(cameraPosition);
                                }
                            }
                        }
                    } catch (Throwable unused) {
                    }
                    this.b.resetChangedCounter();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
            dv.a(th2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setZoomScaleParam(float f) {
        this.aq = f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void onFling() {
        IGlOverlayLayer iGlOverlayLayer = this.D;
        if (iGlOverlayLayer != null) {
            iGlOverlayLayer.setFlingState(true);
        }
        this.T = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getMapWidth() {
        return this.g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getMapHeight() {
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getCameraAngle() {
        return getCameraDegree(this.F);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float getSkyHeight() {
        return this.b.getSkyHeight();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean isMaploaded() {
        return this.J;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final MapConfig getMapConfig() {
        return this.b;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final View getView() throws RemoteException {
        eg egVar = this.C;
        if (egVar != null) {
            return egVar.j();
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void onIndoorBuildingActivity(int i, byte[] bArr) {
        as asVar;
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    asVar = new as();
                    byte b2 = bArr[0];
                    asVar.f899a = new String(bArr, 1, b2, "utf-8");
                    int i2 = b2 + 1;
                    int i3 = b2 + 2;
                    byte b3 = bArr[i2];
                    asVar.b = new String(bArr, i3, b3, "utf-8");
                    int i4 = i3 + b3;
                    int i5 = i4 + 1;
                    byte b4 = bArr[i4];
                    asVar.activeFloorName = new String(bArr, i5, b4, "utf-8");
                    int i6 = i5 + b4;
                    asVar.activeFloorIndex = GLConvertUtil.getInt(bArr, i6);
                    int i7 = i6 + 4;
                    int i8 = i6 + 5;
                    byte b5 = bArr[i7];
                    asVar.poiid = new String(bArr, i8, b5, "utf-8");
                    int i9 = i8 + b5;
                    int i10 = i9 + 1;
                    byte b6 = bArr[i9];
                    asVar.h = new String(bArr, i10, b6, "utf-8");
                    int i11 = i10 + b6;
                    asVar.c = GLConvertUtil.getInt(bArr, i11);
                    int i12 = i11 + 4;
                    asVar.floor_indexs = new int[asVar.c];
                    asVar.floor_names = new String[asVar.c];
                    asVar.d = new String[asVar.c];
                    for (int i13 = 0; i13 < asVar.c; i13++) {
                        asVar.floor_indexs[i13] = GLConvertUtil.getInt(bArr, i12);
                        int i14 = i12 + 4;
                        int i15 = i12 + 5;
                        byte b7 = bArr[i14];
                        if (b7 > 0) {
                            asVar.floor_names[i13] = new String(bArr, i15, b7, "utf-8");
                            i15 += b7;
                        }
                        int i16 = i15 + 1;
                        byte b8 = bArr[i15];
                        if (b8 > 0) {
                            asVar.d[i13] = new String(bArr, i16, b8, "utf-8");
                            i16 += b8;
                        }
                        i12 = i16;
                    }
                    asVar.e = GLConvertUtil.getInt(bArr, i12);
                    int i17 = i12 + 4;
                    if (asVar.e > 0) {
                        asVar.f = new int[asVar.e];
                        for (int i18 = 0; i18 < asVar.e; i18++) {
                            asVar.f[i18] = GLConvertUtil.getInt(bArr, i17);
                            i17 += 4;
                        }
                    }
                    this.be = asVar;
                    post(new Runnable() { // from class: com.amap.api.col.3sl.l.31
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (l.this.aD != null) {
                                l.this.aD.a(l.this.be);
                            }
                        }
                    });
                }
            } catch (Throwable th) {
                dv.a(th);
                th.printStackTrace();
                return;
            }
        }
        asVar = null;
        this.be = asVar;
        post(new Runnable() { // from class: com.amap.api.col.3sl.l.31
            @Override // java.lang.Runnable
            public final void run() {
                if (l.this.aD != null) {
                    l.this.aD.a(l.this.be);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void destroy() {
        this.G.set(true);
        dx.a(dw.c, "destroy map");
        try {
            LocationSource locationSource = this.L;
            if (locationSource != null) {
                locationSource.deactivate();
            }
            this.L = null;
            this.aD = null;
            GLMapRender gLMapRender = this.am;
            if (gLMapRender != null) {
                gLMapRender.renderPause();
            }
            IGlOverlayLayer iGlOverlayLayer = this.D;
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.destroy();
            }
            h();
            Thread thread = this.aa;
            if (thread != null) {
                thread.interrupt();
                this.aa = null;
            }
            Thread thread2 = this.ab;
            if (thread2 != null) {
                thread2.interrupt();
                this.ab = null;
            }
            cq cqVar = this.aj;
            if (cqVar != null) {
                cqVar.a();
                this.aj = null;
            }
            cs csVar = this.ak;
            if (csVar != null) {
                csVar.a((cs.a) null);
                this.ak.a();
                this.ak = null;
            }
            dj.b();
            GLMapEngine gLMapEngine = this.f;
            if (gLMapEngine != null) {
                gLMapEngine.setMapListener(null);
                this.f.releaseNetworkState();
                queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.33
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            l lVar = l.this;
                            lVar.destroySurface(lVar.F);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            dv.a(th);
                        }
                    }
                });
                for (int i = 0; this.f != null && i < 50; i++) {
                    try {
                        Thread.sleep(20L);
                    } catch (InterruptedException e) {
                        dv.a(e);
                    }
                }
            }
            IGLSurfaceView iGLSurfaceView = this.B;
            if (iGLSurfaceView != null) {
                try {
                    iGLSurfaceView.onDetachedGLThread();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    dv.a(e2);
                }
            }
            eg egVar = this.C;
            if (egVar != null) {
                egVar.g();
                this.C = null;
            }
            cj cjVar = this.K;
            if (cjVar != null) {
                cjVar.c();
                this.K = null;
            }
            this.L = null;
            this.t = null;
            q();
            this.Z = null;
            dx.a();
            iv.b();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImp", "destroy");
            dv.a(th);
            th.printStackTrace();
        }
    }

    private void q() {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a();
        }
    }

    final class c implements ed.a {
        private c() {
        }

        /* synthetic */ c(l lVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.ed.a
        public final void a(int i) {
            if (l.this.c != null) {
                l.this.c.activeFloorIndex = l.this.c.floor_indexs[i];
                l.this.c.activeFloorName = l.this.c.floor_names[i];
                try {
                    l lVar = l.this;
                    lVar.setIndoorBuildingInfo(lVar.c);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    final class b {
        b() {
        }

        public final void a(as asVar) {
            List a2;
            List a3;
            if (l.this.b == null || !l.this.b.isIndoorEnable()) {
                return;
            }
            final ed e = l.this.C.e();
            if (asVar == null) {
                try {
                    List a4 = l.this.u.a(AMap.OnIndoorBuildingActiveListener.class.hashCode());
                    if (a4 != null && a4.size() > 0) {
                        synchronized (a4) {
                            for (int i = 0; i < a4.size(); i++) {
                                ((AMap.OnIndoorBuildingActiveListener) a4.get(i)).OnIndoorBuilding(asVar);
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (l.this.c != null) {
                    l.this.c.g = null;
                }
                if (e.b()) {
                    l.this.j.post(new Runnable() { // from class: com.amap.api.col.3sl.l.b.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            e.a(false);
                        }
                    });
                }
                l.this.b.maxZoomLevel = l.this.b.isSetLimitZoomLevel() ? l.this.b.getMaxZoomLevel() : 20.0f;
                try {
                    if (!l.this.z.isZoomControlsEnabled() || (a2 = l.this.u.a(AMapWidgetListener.class.hashCode())) == null || a2.size() <= 0) {
                        return;
                    }
                    synchronized (a2) {
                        for (int i2 = 0; i2 < a2.size(); i2++) {
                            ((AMapWidgetListener) a2.get(i2)).invalidateZoomController(l.this.b.getSZ());
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            if (asVar != null && asVar.floor_indexs != null && asVar.floor_names != null && asVar.floor_indexs.length == asVar.floor_names.length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= asVar.floor_indexs.length) {
                        break;
                    }
                    if (asVar.activeFloorIndex == asVar.floor_indexs[i3]) {
                        asVar.activeFloorName = asVar.floor_names[i3];
                        break;
                    }
                    i3++;
                }
            }
            if (asVar == null || l.this.c == null || l.this.c.activeFloorIndex == asVar.activeFloorIndex || !e.b()) {
                if (asVar != null && (l.this.c == null || !l.this.c.poiid.equals(asVar.poiid) || l.this.c.g == null)) {
                    l.this.c = asVar;
                    if (l.this.b != null) {
                        if (l.this.c.g == null) {
                            l.this.c.g = new Point();
                        }
                        DPoint mapGeoCenter = l.this.b.getMapGeoCenter();
                        if (mapGeoCenter != null) {
                            l.this.c.g.x = (int) mapGeoCenter.x;
                            l.this.c.g.y = (int) mapGeoCenter.y;
                        }
                    }
                }
                try {
                    List a5 = l.this.u.a(AMap.OnIndoorBuildingActiveListener.class.hashCode());
                    if (a5 != null && a5.size() > 0) {
                        synchronized (a5) {
                            for (int i4 = 0; i4 < a5.size(); i4++) {
                                ((AMap.OnIndoorBuildingActiveListener) a5.get(i4)).OnIndoorBuilding(asVar);
                            }
                        }
                    }
                    l.this.b.maxZoomLevel = l.this.b.isSetLimitZoomLevel() ? l.this.b.getMaxZoomLevel() : 20.0f;
                    if (l.this.z.isZoomControlsEnabled() && (a3 = l.this.u.a(AMapWidgetListener.class.hashCode())) != null && a3.size() > 0) {
                        synchronized (a3) {
                            for (int i5 = 0; i5 < a3.size(); i5++) {
                                ((AMapWidgetListener) a3.get(i5)).invalidateZoomController(l.this.b.getSZ());
                            }
                        }
                    }
                    if (!l.this.z.isIndoorSwitchEnabled()) {
                        if (l.this.z.isIndoorSwitchEnabled() || !e.b()) {
                            return;
                        }
                        l.this.z.setIndoorSwitchEnabled(false);
                        return;
                    }
                    if (!e.b()) {
                        l.this.z.setIndoorSwitchEnabled(true);
                    }
                    l.this.j.post(new Runnable() { // from class: com.amap.api.col.3sl.l.b.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                e.a(l.this.c.floor_names);
                                e.a(l.this.c.activeFloorName);
                                if (e.b()) {
                                    return;
                                }
                                e.a(true);
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                    });
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void beforeDrawLabel(int i, GLMapState gLMapState) {
        j();
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        this.bb = this.D.draw(0, this.ae, this.i) ? this.bb : this.bb + 1;
        GLMapEngine gLMapEngine2 = this.f;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void afterDrawLabel(int i, GLMapState gLMapState) {
        j();
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            bVar.e();
        }
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        this.bb = this.D.draw(1, this.ae, this.i) ? this.bb : this.bb + 1;
        GLMapEngine gLMapEngine2 = this.f;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void afterRendererOver(int i, GLMapState gLMapState) {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        this.D.draw(2, this.ae, this.i);
        GLMapEngine gLMapEngine2 = this.f;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
        CustomRenderer customRenderer = this.af;
        if (customRenderer != null) {
            customRenderer.onDrawFrame(null);
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void afterDrawFrame(int i, GLMapState gLMapState) {
        float mapZoomer = gLMapState.getMapZoomer();
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine == null || (!gLMapEngine.isInMapAction(i) && !this.f.isInMapAnimation(i))) {
            int i2 = this.ah;
            if (i2 != -1) {
                this.am.setRenderFps(i2);
            } else {
                this.am.setRenderFps(15.0f);
            }
            if (this.ap != mapZoomer) {
                this.ap = mapZoomer;
            }
        }
        if (this.ay) {
            return;
        }
        this.ay = true;
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void afterAnimation() {
        redrawInfoWindow();
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void onMapPOIClick(MapPoi mapPoi) {
        List a2 = this.u.a(AMap.OnPOIClickListener.class.hashCode());
        if (a2 == null || a2.size() <= 0 || mapPoi == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 20;
        obtain.obj = new Poi(mapPoi.getName(), new LatLng(mapPoi.getLatitude(), mapPoi.getLongitude()), mapPoi.getPoiid());
        this.j.sendMessage(obtain);
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public final void onMapBlankClick(double d2, double d3) {
        a(d2, d3);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final long createGLOverlay(int i) {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.createOverlay(this.F, i);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final long getGlOverlayMgrPtr() {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.getGlOverlayMgrPtr(this.F);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final CrossOverlay addCrossVector(CrossOverlayOptions crossOverlayOptions) {
        if (crossOverlayOptions == null || crossOverlayOptions.getRes() == null) {
            return null;
        }
        final CrossVectorOverlay crossVectorOverlay = new CrossVectorOverlay(this.F, getContext(), this);
        if (crossOverlayOptions != null) {
            crossVectorOverlay.setAttribute(crossOverlayOptions.getAttribute());
        }
        queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.34
            @Override // java.lang.Runnable
            public final void run() {
                GLOverlayBundle overlayBundle;
                if (l.this.f == null || (overlayBundle = l.this.f.getOverlayBundle(l.this.F)) == null) {
                    return;
                }
                overlayBundle.addOverlay(crossVectorOverlay);
            }
        });
        crossVectorOverlay.resumeMarker(crossOverlayOptions.getRes());
        return new CrossOverlay(crossOverlayOptions, crossVectorOverlay);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        GLOverlayBundle overlayBundle;
        try {
            GLMapEngine gLMapEngine = this.f;
            if (gLMapEngine != null && (overlayBundle = gLMapEngine.getOverlayBundle(i)) != null && gLTextureProperty != null && gLTextureProperty.mBitmap != null) {
                this.f.addOverlayTexture(i, gLTextureProperty);
                overlayBundle.addOverlayTextureItem(gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap.getWidth(), gLTextureProperty.mBitmap.getHeight());
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void onAMapAppResourceRequest(AMapAppRequestParam aMapAppRequestParam) {
        q qVar = this.u;
        if (qVar == null) {
            return;
        }
        for (AMap.AMapAppResourceRequestListener aMapAppResourceRequestListener : qVar.a(AMap.AMapAppResourceRequestListener.class.hashCode())) {
            if (aMapAppResourceRequestListener != null) {
                aMapAppResourceRequestListener.onRequest(aMapAppRequestParam);
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCustomMapStylePath(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.b.getCustomStylePath())) {
            return;
        }
        this.b.setCustomStylePath(str);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCustomMapStyleID(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.b.getCustomStyleID())) {
            return;
        }
        this.b.setCustomStyleID(str);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCustomTextureResourcePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.b.setCustomTextureResourcePath(str);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setCustomMapStyle(CustomMapStyleOptions customMapStyleOptions) {
        if (customMapStyleOptions != null) {
            try {
                if (a(true, false)) {
                    return;
                }
                if (customMapStyleOptions.isEnable() && (customMapStyleOptions.getStyleId() != null || customMapStyleOptions.getStyleTexturePath() != null || customMapStyleOptions.getStyleTextureData() != null || customMapStyleOptions.getStyleResDataPath() != null || customMapStyleOptions.getStyleResData() != null)) {
                    o();
                }
                this.aG.c();
                this.aG.a(customMapStyleOptions);
                com.autonavi.extra.b bVar = this.aW;
                if (bVar != null) {
                    bVar.i();
                }
            } catch (Throwable th) {
                dv.a(th);
                return;
            }
        }
        resetRenderTime();
    }

    @Override // com.amap.api.col.3sl.k.a
    public final void a() {
        com.autonavi.extra.b bVar = this.aW;
        if (bVar != null) {
            bVar.i();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final MyLocationStyle getMyLocationStyle() throws RemoteException {
        cj cjVar = this.K;
        if (cjVar != null) {
            return cjVar.a();
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void reloadMapCustomStyle() {
        k kVar = this.aG;
        if (kVar != null) {
            kVar.b();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setMapCustomEnable(boolean z, boolean z2) {
        cq cqVar;
        if (this.av && !this.G.get()) {
            boolean z3 = z2 ? z2 : false;
            if (TextUtils.isEmpty(this.b.getCustomStylePath()) && TextUtils.isEmpty(this.b.getCustomStyleID())) {
                return;
            }
            if (z) {
                try {
                    if (this.b.isProFunctionAuthEnable() && !TextUtils.isEmpty(this.b.getCustomStyleID()) && (cqVar = this.aj) != null) {
                        cqVar.a(this.b.getCustomStyleID());
                        this.aj.b();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    dv.a(th);
                    return;
                }
            }
            if (z2 || this.A || (this.b.isCustomStyleEnable() ^ z)) {
                a(z, (byte[]) null, z3);
            }
            this.A = false;
            return;
        }
        this.aL.b = true;
        this.aL.c = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setMapCustomEnable(boolean z) {
        if (z) {
            o();
        }
        setMapCustomEnable(z, false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setCustomMapStyle(boolean z, byte[] bArr) {
        a(z, bArr, false);
    }

    private void a(boolean z, byte[] bArr, boolean z2) {
        cy cyVar;
        try {
            this.b.setCustomStyleEnable(z);
            if (this.b.isHideLogoEnable()) {
                this.z.setLogoEnable(!z);
            }
            boolean z3 = false;
            if (z) {
                c(this.F, true);
                cx cxVar = new cx();
                MyTrafficStyle myTrafficStyle = this.Z;
                if (myTrafficStyle != null && myTrafficStyle.getTrafficRoadBackgroundColor() != -1) {
                    cxVar.a(this.Z.getTrafficRoadBackgroundColor());
                }
                if (this.b.isProFunctionAuthEnable() && !TextUtils.isEmpty(this.b.getCustomTextureResourcePath())) {
                    z3 = true;
                }
                StyleItem[] styleItemArr = null;
                if (bArr != null) {
                    cyVar = cxVar.a(bArr, z3);
                    if (cyVar != null && (styleItemArr = cyVar.c()) != null) {
                        this.b.setUseProFunction(true);
                    }
                } else {
                    cyVar = null;
                }
                if (styleItemArr == null && (cyVar = cxVar.a(this.b.getCustomStylePath(), z3)) != null) {
                    styleItemArr = cyVar.c();
                }
                if (cxVar.a() != 0) {
                    this.b.setCustomBackgroundColor(cxVar.a());
                }
                if (cyVar != null && cyVar.d() != null) {
                    if (this.ak != null) {
                        this.ak.a((String) cyVar.d());
                        this.ak.a(cyVar);
                        this.ak.b();
                        return;
                    }
                    return;
                }
                a(styleItemArr, z2);
                return;
            }
            c(this.F, false);
            a(this.F, this.b.getMapStyleMode(), this.b.getMapStyleTime(), this.b.getMapStyleState(), true, false, (StyleItem[]) null);
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.amap.api.col.3sl.cs.a
    public final void a(String str, cy cyVar) {
        setCustomTextureResourcePath(str);
        if (!this.b.isCustomStyleEnable() || cyVar == null) {
            return;
        }
        a(cyVar.c(), false);
    }

    private void a(StyleItem[] styleItemArr, boolean z) {
        if (z || (styleItemArr != null && styleItemArr.length > 0)) {
            a(this.F, 0, 0, 0, true, true, styleItemArr);
            ds.a(this.e, true);
        } else {
            ds.a(this.e, false);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void removeEngineGLOverlay(final BaseMapOverlay baseMapOverlay) {
        if (this.f != null) {
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.l.35
                @Override // java.lang.Runnable
                public final void run() {
                    l.this.f.getOverlayBundle(l.this.F).removeOverlay(baseMapOverlay);
                }
            });
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float[] getFinalMatrix() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getMvpMatrix();
        }
        return this.m;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final String createId(String str) {
        IGlOverlayLayer iGlOverlayLayer = this.D;
        if (iGlOverlayLayer != null) {
            return iGlOverlayLayer.createId(str);
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void showLogoEnabled(boolean z) {
        if (this.G.get()) {
            return;
        }
        this.C.f(Boolean.valueOf(z));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float[] getViewMatrix() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getViewMatrix();
        }
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final float[] getProjectionMatrix() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.getProjectionMatrix();
        }
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void changeSurface(GL10 gl10, int i, int i2) {
        try {
            changeSurface(1, gl10, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void createSurface(GL10 gl10, EGLConfig eGLConfig) {
        try {
            this.al = Thread.currentThread().getId();
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
        try {
            createSurface(1, gl10, eGLConfig);
        } catch (Throwable th2) {
            th2.printStackTrace();
            dv.a(th2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void renderSurface(GL10 gl10) {
        drawFrame(gl10);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean canStopMapRender() {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine == null) {
            return true;
        }
        gLMapEngine.canStopMapRender(this.F);
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void getLatLngRect(DPoint[] dPointArr) {
        try {
            Rectangle geoRectangle = this.b.getGeoRectangle();
            if (geoRectangle != null) {
                IPoint[] clipRect = geoRectangle.getClipRect();
                for (int i = 0; i < 4; i++) {
                    GLMapState.geo2LonLat(clipRect[i].x, clipRect[i].y, dPointArr[i]);
                }
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void checkMapState(IGLMapState iGLMapState) {
        if (this.b == null || this.G.get()) {
            return;
        }
        LatLngBounds limitLatLngBounds = this.b.getLimitLatLngBounds();
        try {
            if (limitLatLngBounds != null) {
                IPoint[] limitIPoints = this.b.getLimitIPoints();
                if (limitIPoints == null) {
                    IPoint obtain = IPoint.obtain();
                    GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, obtain);
                    IPoint obtain2 = IPoint.obtain();
                    GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, obtain2);
                    limitIPoints = new IPoint[]{obtain, obtain2};
                    this.b.setLimitIPoints(limitIPoints);
                }
                float a2 = dv.a(this.b, limitIPoints[0].x, limitIPoints[0].y, limitIPoints[1].x, limitIPoints[1].y, getMapWidth(), getMapHeight());
                float mapZoomer = iGLMapState.getMapZoomer();
                if (this.b.isSetLimitZoomLevel()) {
                    float maxZoomLevel = this.b.getMaxZoomLevel();
                    float minZoomLevel = this.b.getMinZoomLevel();
                    a2 = a2 > maxZoomLevel ? maxZoomLevel : Math.max(a2, Math.min(mapZoomer, maxZoomLevel));
                    if (a2 < minZoomLevel) {
                        a2 = minZoomLevel;
                    }
                } else if (a2 <= 0.0f || mapZoomer >= a2) {
                    a2 = mapZoomer;
                }
                iGLMapState.setMapZoomer(a2);
                IPoint obtain3 = IPoint.obtain();
                iGLMapState.getMapGeoCenter(obtain3);
                int[] a3 = dv.a(limitIPoints[0].x, limitIPoints[0].y, limitIPoints[1].x, limitIPoints[1].y, this.b, iGLMapState, obtain3.x, obtain3.y);
                iGLMapState.setMapGeoCenter(a3[0], a3[1]);
                obtain3.recycle();
                return;
            }
            if (this.b.isSetLimitZoomLevel()) {
                iGLMapState.setMapZoomer(Math.max(this.b.getMinZoomLevel(), Math.min(iGLMapState.getMapZoomer(), this.b.getMaxZoomLevel())));
            }
        } catch (Throwable th) {
            dv.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setRenderMode(int i) {
        try {
            IGLSurfaceView iGLSurfaceView = this.B;
            if (iGLSurfaceView != null) {
                iGLSurfaceView.setRenderMode(i);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void changeSize(int i, int i2) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            this.g = i;
            this.h = i2;
            mapConfig.setMapWidth(i);
            this.b.setMapHeight(i2);
        }
    }

    public final Size a(Size size) {
        Size size2 = new Size(getMapWidth(), getMapHeight());
        a(getNativeEngineID(), 0, 0, size.getWidth(), size.getHeight(), size.getWidth(), size.getHeight());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size.getWidth(), size.getHeight());
        getGLMapView().setLayoutParams(layoutParams);
        this.C.f().setLayoutParams(layoutParams);
        changeSize(size.getWidth(), size.getHeight());
        b(false);
        return size2;
    }

    public final void b(Size size) {
        a(getNativeEngineID(), 0, 0, size.getWidth(), size.getHeight(), size.getWidth(), size.getHeight());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        getGLMapView().setLayoutParams(layoutParams);
        this.C.f().setLayoutParams(layoutParams);
        changeSize(size.getWidth(), size.getHeight());
        b(true);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void setHideLogoEnble(boolean z) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            mapConfig.setHideLogoEnble(z);
            if (this.b.isCustomStyleEnable()) {
                this.z.setLogoEnable(!z);
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void changeLogoIconStyle(String str, boolean z, int i) {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.a(str, Boolean.valueOf(z), Integer.valueOf(i));
        }
        ae aeVar = this.z;
        if (aeVar != null) {
            aeVar.requestRefreshLogo();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final void refreshLogo() {
        eg egVar = this.C;
        if (egVar != null) {
            egVar.c();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public final float getUnitLengthByZoom(int i) {
        GLMapState gLMapState = new GLMapState(this.F, this.f.getNativeInstance());
        gLMapState.setMapZoomer(i);
        gLMapState.recalculate();
        float gLUnitWithWin = gLMapState.getGLUnitWithWin(1);
        gLMapState.recycle();
        return gLUnitWithWin;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void setTouchPoiEnable(boolean z) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            mapConfig.setTouchPoiEnable(z);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final boolean isTouchPoiEnable() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return mapConfig.isTouchPoiEnable();
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getSY() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return (int) mapConfig.getSY();
        }
        return -1;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getSX() {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            return (int) mapConfig.getSX();
        }
        return -1;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final long getNativeMapController() {
        GLMapEngine gLMapEngine = this.f;
        if (gLMapEngine != null) {
            return gLMapEngine.getNativeMapController(this.F);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final int getNativeEngineID() {
        return this.F;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnCameraChangeListener.class.hashCode()), (Integer) onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMapClickListener.class.hashCode()), (Integer) onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMarkerDragListener.class.hashCode()), (Integer) onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMapLoadedListener(AMap.OnMapLoadedListener onMapLoadedListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMapLoadedListener.class.hashCode()), (Integer) onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMapTouchListener.class.hashCode()), (Integer) onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMarkerClickListener.class.hashCode()), (Integer) onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnPolylineClickListener.class.hashCode()), (Integer) onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnPOIClickListener.class.hashCode()), (Integer) onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMapLongClickListener.class.hashCode()), (Integer) onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnInfoWindowClickListener.class.hashCode()), (Integer) onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnIndoorBuildingActiveListener.class.hashCode()), (Integer) onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.OnMyLocationChangeListener.class.hashCode()), (Integer) onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnCameraChangeListener.class.hashCode()), onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMapClickListener.class.hashCode()), onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMarkerDragListener.class.hashCode()), onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMapLoadedListener(AMap.OnMapLoadedListener onMapLoadedListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMapLoadedListener.class.hashCode()), onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMapTouchListener.class.hashCode()), onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMarkerClickListener.class.hashCode()), onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnPolylineClickListener.class.hashCode()), onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnPOIClickListener.class.hashCode()), onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMapLongClickListener.class.hashCode()), onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnInfoWindowClickListener.class.hashCode()), onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnIndoorBuildingActiveListener.class.hashCode()), onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.OnMyLocationChangeListener.class.hashCode()), onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void addAMapAppResourceListener(AMap.AMapAppResourceRequestListener aMapAppResourceRequestListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.a(Integer.valueOf(AMap.AMapAppResourceRequestListener.class.hashCode()), (Integer) aMapAppResourceRequestListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void removeAMapAppResourceListener(AMap.AMapAppResourceRequestListener aMapAppResourceRequestListener) {
        q qVar = this.u;
        if (qVar != null) {
            qVar.b(Integer.valueOf(AMap.AMapAppResourceRequestListener.class.hashCode()), aMapAppResourceRequestListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public final void loadWorldVectorMap(boolean z) {
        MapConfig mapConfig = this.b;
        if (mapConfig != null) {
            mapConfig.setAbroadEnable(z);
        }
    }

    private boolean a(boolean z, boolean z2) {
        if (z) {
            if (this.ba) {
                db.a("setCustomMapStyle 和 setWorldVectorMapStyle 不能同时使用，setCustomMapStyle将不会生效");
                return true;
            }
            this.aZ = true;
        }
        if (!z2) {
            return false;
        }
        if (this.aZ) {
            db.a("setCustomMapStyle 和 setWorldVectorMapStyle 不能同时使用，setWorldVectorMapStyle将不会生效");
            return true;
        }
        this.ba = true;
        return false;
    }

    private void r() {
        co coVar = this.aE;
        if (coVar != null) {
            coVar.a();
            this.aE = null;
        }
    }
}
