package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.Projection;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.trackanimationutil.SmoothMarkerUtil;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceSnapshotCallback;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSportType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.hkk;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class hkk implements InterfaceHiMap {
    private static float b = 15.0f;
    private boolean aa;
    private boolean ab;
    private boolean ae;
    private List<LatLng> aj;
    private TextureMapView al;
    private Vector<Marker> aq;
    private AMap.OnMapScreenShotListener at;
    private Polyline av;
    private PolylineOptions bc;
    private List<LatLng> be;
    private InterfaceMapCallback bf;
    private AMap e;
    private CameraUpdate g;
    private String h;
    private LatLngBounds i;
    private Marker l;
    private Context n;
    private List<Integer> o;
    private String u;
    private long y;
    private boolean z;
    private int k = Color.rgb(107, FitnessSleepType.HW_FITNESS_NOON, HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE);
    private int bd = Color.rgb(66, 150, 248);
    private List<Marker> ay = new ArrayList();
    private Marker m = null;
    private List<hjd> bb = new ArrayList(10);
    private boolean ac = false;
    private int s = 0;
    private long q = 0;
    private boolean ag = true;
    private Polygon aw = null;
    private boolean v = false;
    private ArrayList<Marker> as = new ArrayList<>(10);
    private ArrayList<MarkerOptions> ak = new ArrayList<>(10);
    private List<hjg> w = new ArrayList(10);
    private ArrayList<c> an = new ArrayList<>(10);

    /* renamed from: a, reason: collision with root package name */
    private List<List<hkf>> f13207a = new ArrayList(10);
    private int ao = 0;
    private List<List<PolylineOptions>> f = new ArrayList(10);
    private int p = 0;
    private boolean ai = true;
    private boolean ah = true;
    private Handler c = new b(Looper.getMainLooper(), this);
    private Handler ap = new a(Looper.getMainLooper(), this);
    private int az = 0;
    private List<Integer> ba = new ArrayList(10);
    private CameraUpdate j = null;
    private List<LatLng> t = new ArrayList(10);
    private List<LatLng> r = new ArrayList(10);
    private List<SmoothMarkerUtil> ar = new ArrayList();
    private boolean af = false;
    private boolean ad = false;
    private int am = -1;
    private Vector<Polyline> d = new Vector<>();
    private AMap.CancelableCallback x = new AMap.CancelableCallback() { // from class: hkk.5
        @Override // com.amap.api.maps.AMap.CancelableCallback
        public void onCancel() {
        }

        @Override // com.amap.api.maps.AMap.CancelableCallback
        public void onFinish() {
            hkk.this.ah = false;
        }
    };
    private final Map<String, GroundOverlay> au = new ConcurrentHashMap();
    private final Map<Polyline, PolylineOptions> bh = new ConcurrentHashMap();
    private final Map<String, Polyline> ax = new ConcurrentHashMap();

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int getMapEngineType() {
        return 1;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadingEnd() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStart() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStop() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerLoadingListener(InterfaceMapCallback interfaceMapCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void saveAddress(MotionData motionData) {
    }

    public hkk(Context context, TextureMapView textureMapView, boolean z) {
        if (textureMapView == null) {
            throw new RuntimeException("context or mapView is null");
        }
        this.n = BaseApplication.getContext();
        AMap map = textureMapView.getMap();
        this.e = map;
        if (map == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "GaoDeMap mAmap is null");
            return;
        }
        if (z) {
            map.reloadMap();
        }
        this.al = textureMapView;
        LogUtil.a("Track_GaoDeMap", "GaoDeMap instance hashcode: ", Integer.valueOf(hashCode()));
        PolylineOptions polylineOptions = new PolylineOptions();
        this.bc = polylineOptions;
        polylineOptions.color(this.bd).width(15.0f).zIndex(10.0f).visible(true);
        this.av = a(this.bc);
        this.be = new ArrayList(10);
        this.aj = new ArrayList(10);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setIsStop(boolean z) {
        this.ai = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hjd hjdVar, long j, InterfaceMapCallback interfaceMapCallback) {
        if (hjdVar == null) {
            return;
        }
        if (j == 0) {
            j = 1000;
        }
        if (this.e == null) {
            ReleaseLogUtil.c("Track_GaoDeMap", "animateCamera: mAMap is null!");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.q;
        if ((this.ac || elapsedRealtime - j2 <= 2000) && !i()) {
            return;
        }
        a(a(hjdVar), j, c(interfaceMapCallback));
    }

    private void a(LatLng latLng, long j, AMap.CancelableCallback cancelableCallback) {
        LogUtil.h("Track_GaoDeMap", "startMoveCamera: map is not ready ", Boolean.valueOf(this.ae));
        if (this.ae) {
            CameraPosition cameraPosition = this.e.getCameraPosition();
            float f = 16.5f;
            float f2 = cameraPosition == null ? 16.5f : cameraPosition.zoom;
            if (Math.abs(f2 - 10.0f) >= 1.0E-4f && !this.ah) {
                f = f2;
            }
            CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, f, 0.0f, 0.0f));
            this.y = SystemClock.elapsedRealtime();
            this.ac = true;
            if (this.ah) {
                this.e.animateCamera(newCameraPosition, j, this.x);
            } else {
                this.e.animateCamera(newCameraPosition, j, cancelableCallback);
            }
        }
    }

    private boolean i() {
        return SystemClock.elapsedRealtime() - this.y > PreConnectManager.CONNECT_INTERNAL;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawAddMapTracking(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "drawAddMapTracking lastHiHealthLatLng or currentHiHealthLatLng is null");
            return;
        }
        this.be.add(a(hjdVar));
        this.bc.setPoints(this.be);
        this.av = a(this.bc);
        animateCamera(hjdVar2, 1000L, (InterfaceMapCallback) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addSportStartMarker(hjd hjdVar, int i) {
        addStartMarker(hjdVar, i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void screenShotToFile(final InterfaceSnapshotCallback interfaceSnapshotCallback) {
        synchronized (this) {
            if (interfaceSnapshotCallback == null) {
                return;
            }
            this.e.getMapScreenShot(new AMap.OnMapScreenShotListener() { // from class: hkk.6
                @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
                public void onMapScreenShot(Bitmap bitmap, int i) {
                }

                @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
                public void onMapScreenShot(Bitmap bitmap) {
                    interfaceSnapshotCallback.onSnapshotReady(bitmap);
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(List<hiy> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_GaoDeMap", "drawLine listBasePoint is null");
            return;
        }
        hjd e = gwf.e(list.get(list.size() - 1));
        this.aj.add(gwe.d(e));
        if (this.ag) {
            this.bc.setPoints(this.aj);
            this.av = a(this.bc);
            addEndMarker(e);
            animateCamera(e, 0L, (InterfaceMapCallback) null);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineColor(int i, boolean z) {
        Polyline polyline;
        this.bd = i;
        PolylineOptions polylineOptions = this.bc;
        if (polylineOptions != null) {
            polylineOptions.color(i);
        }
        if (!z || (polyline = this.av) == null) {
            return;
        }
        polyline.setColor(i);
    }

    public void d(int i, int i2, List<LatLng> list, Map<Integer, Float> map) {
        if (list == null) {
            return;
        }
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(15.0f).zIndex(10.0f).visible(true);
        List<Integer> arrayList = new ArrayList<>(10);
        if (this.ad) {
            if (c(map)) {
                polylineOptions.color(this.k);
            } else if (map.size() == 1) {
                polylineOptions.color(e(map)).width(15.0f);
            } else {
                arrayList = a(list.size(), map);
            }
        } else {
            polylineOptions.color(this.bd);
        }
        int size = list.size();
        int i3 = size / 2500;
        ArrayList arrayList2 = new ArrayList(10);
        if (i3 > 1) {
            for (int i4 = 0; i4 < size; i4++) {
                if (i4 % i3 == 0 || i4 == size - 1) {
                    polylineOptions.add(list.get(i4));
                    e(i, arrayList, arrayList2, i4);
                }
            }
        } else {
            while (i < i2) {
                if (i < arrayList.size()) {
                    arrayList2.add(arrayList.get(i));
                }
                i++;
            }
            polylineOptions.addAll(list);
        }
        d(polylineOptions, arrayList2, arrayList2.size());
        e(polylineOptions, arrayList2);
        this.e.addPolyline(polylineOptions);
    }

    private boolean c(Map<Integer, Float> map) {
        return d(map) || a(map) || this.az == 266;
    }

    private boolean d(Map<Integer, Float> map) {
        return map == null || map.size() == 0;
    }

    private void d(PolylineOptions polylineOptions, List<Integer> list, int i) {
        int intValue;
        if (i < polylineOptions.getPoints().size()) {
            if (i == 0) {
                intValue = this.bd;
            } else {
                intValue = list.get(i - 1).intValue();
            }
            for (int i2 = 0; i2 < polylineOptions.getPoints().size() - i; i2++) {
                list.add(Integer.valueOf(intValue));
            }
        }
    }

    private void e(int i, List<Integer> list, List<Integer> list2, int i2) {
        int i3 = i2 + i;
        if (i3 < list.size()) {
            list2.add(list.get(i3));
        }
    }

    private boolean a(Map<Integer, Float> map) {
        boolean z = true;
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            if (entry.getKey().intValue() <= 0) {
                return true;
            }
            if (entry.getKey().intValue() % 100000 != 0) {
                z = false;
            }
        }
        return z;
    }

    private int e(Map<Integer, Float> map) {
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f = it.next().getValue().floatValue();
        }
        return gwe.c(f, this.az);
    }

    private List<Integer> a(int i, Map<Integer, Float> map) {
        return b(i, map);
    }

    private void e(PolylineOptions polylineOptions, List<Integer> list) {
        polylineOptions.colorValues(list);
        if (this.ai) {
            polylineOptions.width(15.0f);
        }
    }

    private List<Integer> b(int i, Map<Integer, Float> map) {
        int intValue;
        Set<Map.Entry<Integer, Float>> entrySet = map.entrySet();
        float[] fArr = new float[map.size()];
        float[] fArr2 = new float[map.size()];
        Integer[] numArr = new Integer[map.size()];
        int i2 = 0;
        for (Map.Entry<Integer, Float> entry : entrySet) {
            if (entry.getKey().intValue() > 100000) {
                fArr[i2] = entry.getKey().intValue() % 100000;
            } else {
                fArr[i2] = Float.valueOf(entry.getKey().intValue()).floatValue();
            }
            float floatValue = entry.getValue().floatValue();
            fArr2[i2] = floatValue;
            numArr[i2] = Integer.valueOf(gwe.c(floatValue, this.az));
            i2++;
        }
        if (this.o == null) {
            this.o = gwe.e(fArr, numArr);
        }
        LogUtil.a("Track_GaoDeMap", "colorList ", Integer.valueOf(this.o.size()), " pointSize ", Integer.valueOf(i));
        int size = this.o.size();
        if (size < i) {
            if (size == 0) {
                intValue = this.k;
            } else {
                intValue = this.o.get(size - 1).intValue();
            }
            for (int i3 = 0; i3 < i - size; i3++) {
                this.o.add(Integer.valueOf(intValue));
            }
        }
        return this.o;
    }

    private void c(int i, LatLng latLng, c cVar) {
        if (this.an.size() > 0) {
            c cVar2 = this.an.get(r0.size() - 1);
            if (cVar2.d.equals(latLng)) {
                if (this.r.size() <= i || !cVar.d.equals(this.r.get(i))) {
                    cVar2.c = false;
                } else {
                    cVar.c = false;
                }
            }
        }
    }

    private Polyline a(PolylineOptions polylineOptions) {
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "drawLine mAMap is null sth exception");
            return null;
        }
        return aMap.addPolyline(polylineOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "drawLine lastHiHealthLatLng or currentHiHealthLatLng is null");
            return;
        }
        addEndMarker(hjdVar2);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.bd).width(15.0f).zIndex(10.0f).visible(true).add(a(hjdVar), a(hjdVar2));
        a(polylineOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawInterrupt(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "drawInterrupt lastHiHealthLatLng or currentHiHealthLatLng is null");
            return;
        }
        addEndMarker(hjdVar2);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(BaseApplication.getContext().getResources().getColor(R.color._2131298897_res_0x7f090a51)).width(15.0f).zIndex(10.0f).setDottedLine(true).visible(gwe.d()).add(a(hjdVar), a(hjdVar2));
        a(polylineOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void pauseSportClear() {
        PolylineOptions polylineOptions = new PolylineOptions();
        this.bc = polylineOptions;
        polylineOptions.color(this.bd).width(15.0f).zIndex(10.0f).visible(true);
        this.av = a(this.bc);
        List<LatLng> list = this.be;
        if (list != null && !list.isEmpty()) {
            this.be.clear();
        }
        List<LatLng> list2 = this.aj;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        this.aj.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addStartMarker(hjd hjdVar, int i) {
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_GaoDeMap", "addStartMarker hiHealthLatLng is null!");
            return;
        }
        if (this.af) {
            ReleaseLogUtil.c("Track_GaoDeMap", "addStartMarker aMap is destroyed");
            return;
        }
        for (Marker marker : this.ay) {
            if (marker != null) {
                marker.remove();
            }
        }
        this.ay.clear();
        LogUtil.a("Track_GaoDeMap", "addStartMarker() GaoDeMap instance hashcode: ", Integer.valueOf(hashCode()));
        this.ay.add(gwe.c(this.e, gwe.b(a(hjdVar)), i));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIconPath(String str, String str2) {
        this.u = str;
        this.h = str2;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String addOverlay(hlj hljVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "mAMap is null in addGroundOverlay");
            return null;
        }
        if (hljVar == null) {
            LogUtil.b("Track_GaoDeMap", "overlayDesc is null in addGroundOverlay");
            return null;
        }
        GroundOverlay addGroundOverlay = this.e.addGroundOverlay(new GroundOverlayOptions().position(gwe.d(hljVar.b()), hljVar.c()).image(BitmapDescriptorFactory.fromBitmap(hljVar.bhR_())).bearing(hljVar.d()).zIndex(hljVar.e()).visible(hljVar.h()));
        if (addGroundOverlay == null) {
            LogUtil.b("Track_GaoDeMap", "add GroundOverlay fail");
            return null;
        }
        String id = addGroundOverlay.getId();
        this.au.put(id, addGroundOverlay);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOverlayVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.a("Track_GaoDeMap", "id is null in setOverlayVisible");
            return;
        }
        GroundOverlay groundOverlay = this.au.get(str);
        if (groundOverlay == null) {
            return;
        }
        groundOverlay.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar, boolean z) {
        if (z) {
            Marker marker = this.m;
            if (marker != null) {
                marker.remove();
                this.m = null;
            }
            Marker marker2 = this.l;
            if (marker2 != null) {
                marker2.remove();
                this.l = null;
            }
        }
        addEndMarker(hjdVar);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar) {
        Marker marker = this.m;
        if (marker != null || hjdVar == null) {
            if (hjdVar != null) {
                marker.setPosition(a(hjdVar));
                this.l.setPosition(a(hjdVar));
                return;
            } else {
                LogUtil.b("Track_GaoDeMap", "addEndMarker is wrong");
                return;
            }
        }
        Marker c2 = gwe.c(this.e, gwe.b(a(hjdVar)), this.h);
        this.l = c2;
        if (c2 == null) {
            return;
        }
        if (!this.ai) {
            LogUtil.a("Track_GaoDeMap", "addEndMarker() GaoDeMap instance hashcode: ", Integer.valueOf(hashCode()));
            this.m = gwe.d(this.e, gwe.b(a(hjdVar)), this.u);
            d(1);
        } else {
            c2.setVisible(false);
            this.m = this.e.addMarker(new MarkerOptions().position(a(hjdVar)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428707_res_0x7f0b0563)));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(List<MotionData> list) {
        CommonUtil.a("Track_GaoDeMap", "into gaode map");
        if (koq.b(list)) {
            ReleaseLogUtil.c("Track_GaoDeMap", "onMapLoaded motion data is null");
            return;
        }
        int sportType = list.get(0).getSportType();
        this.az = sportType;
        if (sportType == 266) {
            this.k = Color.rgb(49, FitnessSportType.HW_FITNESS_SPORT_ALL, 251);
        }
        b(list.get(0));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(hjd hjdVar, float f) {
        CommonUtil.a("Track_GaoDeMap", "into gaode map");
        if (hjdVar == null) {
            LogUtil.a("Track_GaoDeMap", "onMapLoaded, hiHealthLatLng is null");
            return;
        }
        this.e.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(hjdVar.b, hjdVar.d)));
        this.e.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadMapWithPreprocessData(List<hkx> list) {
        this.ao = 0;
        this.p = list.size();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (list.size() > 0) {
            this.az = list.get(0).g();
            this.s = list.get(0).a().size();
        }
        for (hkx hkxVar : list) {
            this.ba.add(Integer.valueOf(hkxVar.g()));
            this.f.add(c(hkxVar, builder));
            if (hkxVar.d().size() > 0) {
                this.t.add(gwe.d(hkxVar.d().get(0)));
            }
            if (hkxVar.b().size() > 0) {
                this.r.add(gwe.d(hkxVar.b().get(0)));
            }
            Iterator<hjd> it = hkxVar.a().iterator();
            int i = 1;
            while (it.hasNext()) {
                LatLng d2 = gwe.d(it.next());
                c cVar = new c();
                cVar.d = d2;
                cVar.c = true;
                cVar.e = i;
                c(this.ao, d2, cVar);
                this.an.add(cVar);
                i++;
            }
            this.ao++;
            List<hjg> c2 = hkxVar.c();
            this.w = c2;
            this.ak = gwe.e(c2);
        }
        this.i = builder.build();
        CommonUtil.a("Track_GaoDeMap", "setAnimationPolylineOptions");
        if (this.i == null) {
            LogUtil.h("Track_GaoDeMap", "loadMapWithPreprocessData show map mBounds null");
        } else {
            if (this.e == null) {
                return;
            }
            g();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeTrackColor(int i) {
        for (Map.Entry<Polyline, PolylineOptions> entry : this.bh.entrySet()) {
            Polyline key = entry.getKey();
            PolylineOptions value = entry.getValue();
            if (value.getColorValues() == null) {
                int color = value.getColor();
                value.color(i);
                key.setOptions(value);
                value.color(color);
            } else {
                new ArrayList();
                List<Integer> colorValues = value.getColorValues();
                value.colorValues(Collections.nCopies(2, Integer.valueOf(i)));
                key.setOptions(value);
                value.colorValues(colorValues);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void resetTrackColor() {
        for (Map.Entry<Polyline, PolylineOptions> entry : this.bh.entrySet()) {
            entry.getKey().setOptions(entry.getValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.e == null || koq.b(this.ak)) {
            return;
        }
        for (int i = 1; i < this.ak.size() - 1; i++) {
            MarkerOptions markerOptions = this.ak.get(i);
            if (markerOptions != null) {
                this.e.addMarker(markerOptions);
            }
        }
    }

    private void b(MotionData motionData) {
        int i;
        LatLng latLng;
        Map<Long, double[]> lbsDataMap = motionData.getLbsDataMap();
        if (lbsDataMap == null || lbsDataMap.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<Map.Entry<Long, double[]>> it = lbsDataMap.entrySet().iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Long, double[]> next = it.next();
            a(arrayList, builder, new LatLng(next.getValue()[0], next.getValue()[1]));
        }
        LogUtil.a("Track_GaoDeMap", "list SIZE:", Integer.valueOf(arrayList.size()));
        int size = arrayList.size();
        if (size >= 3 || !this.ai) {
            c(motionData.getPaceMap(), arrayList, new ArrayList(10));
            while (true) {
                if (i >= size) {
                    break;
                }
                LatLng latLng2 = arrayList.get(i);
                if (!gwe.c(gwe.b(latLng2))) {
                    addStartMarker(a(latLng2), this.az);
                    LogUtil.c("Track_GaoDeMap", "addStartMarker");
                    break;
                }
                i++;
            }
            int i2 = size - 1;
            while (true) {
                if (i2 < 0) {
                    latLng = null;
                    break;
                }
                latLng = arrayList.get(i2);
                if (!gwe.c(gwe.b(latLng))) {
                    addEndMarker(a(latLng));
                    LogUtil.c("Track_GaoDeMap", "addEndMarker");
                    break;
                }
                i2--;
            }
            if (builder.build() == null) {
                LogUtil.a("Track_GaoDeMap", "show map mBounds null");
            } else if (latLng != null) {
                this.e.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
                this.e.moveCamera(CameraUpdateFactory.zoomTo(16.5f));
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void startMapAnimation(boolean z) {
        if (!this.z) {
            LogUtil.a("Track_GaoDeMap", "startMapAnimation error ");
            InterfaceMapCallback interfaceMapCallback = this.bf;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
                return;
            }
            return;
        }
        AMap aMap = this.e;
        if (aMap == null) {
            return;
        }
        if (!z) {
            this.ab = true;
            cancelAnimation();
            return;
        }
        aMap.setOnMapTouchListener(new d(this));
        this.ab = true;
        if (koq.c(this.t) && koq.c(this.ba)) {
            addStartMarker(a(this.t.get(0)), this.ba.get(0).intValue());
            this.ay.get(0).setZIndex(14.0f);
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnimation.setDuration(300L);
        this.ay.get(0).setAnimation(scaleAnimation);
        this.ay.get(0).startAnimation();
        this.ap.postDelayed(new Runnable() { // from class: hkh
            @Override // java.lang.Runnable
            public final void run() {
                hkk.this.a();
            }
        }, 300L);
    }

    /* synthetic */ void a() {
        this.aa = true;
        this.ar.get(0).b();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public boolean isAnimationStart() {
        return this.aa;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void cancelAnimation() {
        if (this.ab) {
            this.aa = true;
            this.ab = false;
            if (f() || this.f == null || this.ba == null) {
                ReleaseLogUtil.c("Track_GaoDeMap", "cancel animal draw list is null");
                return;
            }
            if (this.e == null) {
                LogUtil.b("Track_GaoDeMap", "cancel animal mAMap is null");
                return;
            }
            e();
            for (int i = 0; i < this.p; i++) {
                this.ar.get(i).c();
                if (!c(i)) {
                    break;
                }
                Marker c2 = gwe.c(this.e, gwe.b(this.t.get(i)), this.ba.get(i).intValue());
                if (c2 != null) {
                    c2.setZIndex(14.0f);
                }
                if (i == this.p - 1) {
                    this.m = this.e.addMarker(new MarkerOptions().position(this.r.get(i)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428707_res_0x7f0b0563)).zIndex(14.0f));
                } else {
                    this.m = this.e.addMarker(new MarkerOptions().position(this.r.get(i)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428708_res_0x7f0b0564)).zIndex(14.0f));
                }
                for (PolylineOptions polylineOptions : this.f.get(i)) {
                    this.bh.put(this.e.addPolyline(polylineOptions), polylineOptions);
                }
            }
            InterfaceMapCallback interfaceMapCallback = this.bf;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
            }
        }
    }

    private boolean f() {
        return this.t == null || this.r == null;
    }

    private boolean c(int i) {
        return this.t.size() > i && this.r.size() > i && this.f.size() > i && this.ba.size() > i;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showOrHide(boolean z) {
        d(z);
        LogUtil.a("Track_GaoDeMap", "showOrHide isShow = ", Boolean.valueOf(z));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showSatelLiteState(boolean z, boolean z2, int i) {
        if (z2) {
            i = 1;
        }
        setMapShowType(i);
        LogUtil.a("Track_GaoDeMap", "showSatelLiteState isShowMap = ", Boolean.valueOf(z), ",isShowTrackMapTypeSatellite=", Boolean.valueOf(z2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapShowType(int i) {
        if (this.am == i) {
            return;
        }
        this.am = i;
        setMapStyle(i);
        AMap aMap = this.e;
        if (aMap != null) {
            if (i == 1) {
                aMap.setMapType(2);
            } else {
                if (i != 3) {
                    return;
                }
                aMap.setMapType(3);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapStyle(int i) {
        CustomMapStyleOptions customMapStyleOptions = new CustomMapStyleOptions();
        if (i != 0) {
            return;
        }
        try {
            InputStream open = BaseApplication.getContext().getAssets().open("maps/gaode_entrance_style.data");
            try {
                InputStream open2 = BaseApplication.getContext().getAssets().open("maps/gaode_entrance_style_extra.data");
                try {
                    byte[] bArr = new byte[open.available()];
                    byte[] bArr2 = new byte[open2.available()];
                    if (open.read(bArr) != -1) {
                        customMapStyleOptions.setStyleData(bArr);
                    }
                    if (open2.read(bArr2) != -1) {
                        customMapStyleOptions.setStyleExtraData(bArr2);
                    }
                    if (open2 != null) {
                        open2.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            LogUtil.b("Track_GaoDeMap", LogAnonymous.b((Throwable) e));
        }
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.setCustomMapStyle(customMapStyleOptions);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerAnimationListener(InterfaceMapCallback interfaceMapCallback) {
        this.bf = interfaceMapCallback;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public List<hjd> requestSimplePoints() {
        return this.bb;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public List<MotionData> convertCoordinate(List<MotionData> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<MotionData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(gwe.d(it.next(), getMapEngineType()));
        }
        return arrayList;
    }

    private void g() {
        Context context = this.n;
        if (context == null || context.getResources() == null) {
            ReleaseLogUtil.c("Track_GaoDeMap", "setBoundAndLine mContext is null");
            return;
        }
        LatLng latLng = this.i.southwest;
        LatLng latLng2 = this.i.northeast;
        LatLng latLng3 = new LatLng(latLng2.latitude - ((latLng2.latitude - latLng.latitude) * 1.55d), latLng.longitude);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(this.i.northeast);
        builder.include(latLng3);
        LatLngBounds build = builder.build();
        this.i = build;
        this.j = CameraUpdateFactory.newLatLngBounds(build, (int) this.n.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.n.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d), 20);
        this.g = CameraUpdateFactory.changeLatLng(new LatLng((this.i.northeast.latitude + this.i.southwest.latitude) / 2.0d, (this.i.northeast.longitude + this.i.southwest.longitude) / 2.0d));
        CommonUtil.a("Track_GaoDeMap", "before draw time");
        for (int i = 0; i < this.p; i++) {
            e(this.i, i);
        }
        CommonUtil.a("Track_GaoDeMap", "after draw time");
        moveToCenter();
        this.z = true;
        if (this.af) {
            this.e = null;
        }
    }

    private void e(LatLngBounds latLngBounds, final int i) {
        if (koq.b(this.t)) {
            ReleaseLogUtil.d("Track_GaoDeMap", "setLocationMarker mDetailStartList is empty");
            return;
        }
        final SmoothMarkerUtil smoothMarkerUtil = new SmoothMarkerUtil(this.e, this.t.get(i));
        smoothMarkerUtil.c(BitmapDescriptorFactory.fromResource(R.drawable._2131431797_res_0x7f0b1175));
        List<List<hkf>> list = this.f13207a;
        if (list != null && list.size() > i) {
            smoothMarkerUtil.c(this.f13207a.get(i), AMapUtils.calculateLineDistance(latLngBounds.northeast, latLngBounds.southwest));
        }
        smoothMarkerUtil.e(new SmoothMarkerUtil.SmoothMarkerListner() { // from class: hkk.9
            @Override // com.huawei.healthcloud.plugintrack.ui.fragmentutils.trackanimationutil.SmoothMarkerUtil.SmoothMarkerListner
            public void onFinished() {
                hkk.this.e();
                if (hkk.this.r != null && hkk.this.r.size() > i) {
                    if (hkk.this.p == i + 1) {
                        Bitmap aUI_ = gwe.aUI_(hkk.this.w);
                        if (aUI_ == null) {
                            aUI_ = BitmapFactory.decodeResource(hkk.this.n.getResources(), R.drawable._2131428707_res_0x7f0b0563);
                        }
                        hkk hkkVar = hkk.this;
                        hkkVar.m = hkkVar.e.addMarker(new MarkerOptions().position((LatLng) hkk.this.r.get(i)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromBitmap(aUI_)).zIndex(14.0f));
                    } else {
                        hkk hkkVar2 = hkk.this;
                        hkkVar2.m = hkkVar2.e.addMarker(new MarkerOptions().position((LatLng) hkk.this.r.get(i)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428708_res_0x7f0b0564)).zIndex(14.0f));
                    }
                } else {
                    LogUtil.b("Track_GaoDeMap", "Endlist is null");
                }
                hkk.this.b(i + 1);
                hkk.this.c(smoothMarkerUtil.a());
            }
        });
        this.ar.add(smoothMarkerUtil);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Polyline> list) {
        if (list == null) {
            return;
        }
        for (Polyline polyline : list) {
            this.bh.put(polyline, polyline.getOptions());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnimation.setDuration(500L);
        this.m.setAnimation(scaleAnimation);
        this.m.startAnimation();
        if (this.p == i) {
            this.ap.sendEmptyMessageDelayed(0, 500L);
        } else {
            this.ap.sendMessageDelayed(Message.obtain(this.ap, 1, Integer.valueOf(i)), 500L);
        }
    }

    private void a(List<LatLng> list, LatLngBounds.Builder builder, LatLng latLng) {
        if (koq.c(list)) {
            list.add(latLng);
            if (gwe.c(gwe.b(latLng))) {
                return;
            }
            builder.include(latLng);
            return;
        }
        if (gwe.c(gwe.b(latLng))) {
            return;
        }
        list.add(latLng);
        builder.include(latLng);
        this.v = true;
    }

    private List<PolylineOptions> c(hkx hkxVar, LatLngBounds.Builder builder) {
        if (koq.b(hkxVar.e())) {
            ReleaseLogUtil.d("Track_GaoDeMap", "otherPolylineOptions mAnimalLines is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        List<hjf> e = hkxVar.e();
        for (int i = 0; i < e.size(); i++) {
            hjf hjfVar = e.get(i);
            int e2 = hjfVar.e();
            ArrayList arrayList2 = new ArrayList(hjfVar.d().size());
            for (hjd hjdVar : hjfVar.d()) {
                LatLng latLng = new LatLng(hjdVar.b, hjdVar.d);
                if (e2 != 2) {
                    this.bb.add(hjdVar);
                    builder.include(latLng);
                }
                arrayList2.add(latLng);
            }
            arrayList.add(b(hjfVar, arrayList2));
        }
        return arrayList;
    }

    private PolylineOptions b(hjf hjfVar, List<LatLng> list) {
        PolylineOptions polylineOptions = new PolylineOptions();
        hkf hkfVar = new hkf();
        hkfVar.b(list);
        int e = hjfVar.e();
        if (e == 0) {
            e(hjfVar, list, polylineOptions, hkfVar);
        } else if (e == 1) {
            b(hjfVar, list, polylineOptions, hkfVar);
        } else if (e == 2) {
            c(hjfVar, list, polylineOptions, hkfVar);
        } else {
            LogUtil.a("Track_GaoDeMap", "makeLinePolylineOptions on errer type");
        }
        int size = this.f13207a.size();
        int i = this.ao;
        if (size > i) {
            this.f13207a.get(i).add(hkfVar);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(hkfVar);
            this.f13207a.add(arrayList);
        }
        return polylineOptions;
    }

    private void e(hjf hjfVar, List<LatLng> list, PolylineOptions polylineOptions, hkf hkfVar) {
        this.v = true;
        List<Integer> a2 = hjfVar.a();
        hkfVar.c(a2);
        hkfVar.d(0);
        polylineOptions.width(15.0f).zIndex(10.0f).visible(true).colorValues(a2).addAll(list);
    }

    private void b(hjf hjfVar, List<LatLng> list, PolylineOptions polylineOptions, hkf hkfVar) {
        this.v = true;
        int intValue = hjfVar.a().size() > 0 ? hjfVar.a().get(0).intValue() : this.k;
        hkfVar.b(intValue);
        hkfVar.d(1);
        polylineOptions.width(15.0f).zIndex(10.0f).visible(true).color(intValue).addAll(list);
    }

    private void c(hjf hjfVar, List<LatLng> list, PolylineOptions polylineOptions, hkf hkfVar) {
        int intValue = hjfVar.a().size() > 0 ? hjfVar.a().get(0).intValue() : BaseApplication.getContext().getResources().getColor(R.color._2131298897_res_0x7f090a51);
        hkfVar.b(intValue);
        hkfVar.d(2);
        polylineOptions.color(intValue).width(15.0f).zIndex(10.0f).setDottedLine(true).visible(gwe.d()).addAll(list);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded() {
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: hkk.8
                @Override // com.amap.api.maps.AMap.OnMapLoadedListener
                public void onMapLoaded() {
                    if (hkk.this.e == null) {
                        return;
                    }
                    hkk.this.ae = true;
                    Location aUH_ = gwe.aUH_(hkk.this.n);
                    if (aUH_ != null) {
                        double[] aUD_ = gwe.aUD_(hkk.this.n, aUH_);
                        hkk.this.e.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aUD_[0], aUD_[1])));
                        hkk.this.e.moveCamera(CameraUpdateFactory.zoomTo(16.5f));
                    }
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOnMapLoadedListener(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: hkk.13
                @Override // com.amap.api.maps.AMap.OnMapLoadedListener
                public void onMapLoaded() {
                    hkk.this.ae = true;
                    InterfaceMapLoadedCallback interfaceMapLoadedCallback2 = interfaceMapLoadedCallback;
                    if (interfaceMapLoadedCallback2 != null) {
                        interfaceMapLoadedCallback2.onMapLoaded();
                    }
                }
            });
        }
    }

    private void c(Map<Integer, Float> map, List<LatLng> list, List<LatLng> list2) {
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            LatLng latLng = list.get(i2);
            if (gwe.c(gwe.b(latLng))) {
                if (!list2.isEmpty()) {
                    d(i, i2, list2, map);
                    list2.clear();
                }
                b(list, i2);
                i = i2 + 1;
            } else {
                list2.add(latLng);
            }
        }
        LogUtil.a("Track_GaoDeMap", "sportList SIZE:", Integer.valueOf(list2.size()));
        if (list2.isEmpty()) {
            return;
        }
        d(i, size, list2, map);
    }

    private void b(List<LatLng> list, int i) {
        LatLng d2 = d(list, i);
        LatLng c2 = c(list, i);
        if (d2 == null || c2 == null) {
            return;
        }
        a(new PolylineOptions().color(BaseApplication.getContext().getResources().getColor(R.color._2131298897_res_0x7f090a51)).width(15.0f).zIndex(10.0f).setDottedLine(true).visible(gwe.d()).add(d2, c2));
    }

    private LatLng c(List<LatLng> list, int i) {
        if (i < list.size() - 1) {
            return list.get(i + 1);
        }
        return null;
    }

    private LatLng d(List<LatLng> list, int i) {
        if (i >= 1) {
            return list.get(i - 1);
        }
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraLatLngBounds(List<hjd> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_GaoDeMap", "moveCameraLatLngBounds list is null");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int size = list.size();
        Iterator<hjd> it = list.iterator();
        while (it.hasNext()) {
            builder.include(a(it.next()));
        }
        if (size > 1) {
            this.e.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), (int) this.n.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.n.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d), 20));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void getMapScreenShot(final Handler handler, MotionData motionData) {
        if (handler == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "getMapScreenShot handler is null");
            return;
        }
        AMap.OnMapScreenShotListener onMapScreenShotListener = new AMap.OnMapScreenShotListener() { // from class: hkk.14
            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap, int i) {
            }

            @Override // com.amap.api.maps.AMap.OnMapScreenShotListener
            public void onMapScreenShot(Bitmap bitmap) {
                Message obtain = Message.obtain(handler);
                obtain.obj = bitmap;
                obtain.what = 1;
                obtain.sendToTarget();
                hkk.this.at = null;
            }
        };
        this.at = onMapScreenShotListener;
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.getMapScreenShot(onMapScreenShotListener);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setAllGesturesEnabled(boolean z) {
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.getUiSettings().setAllGesturesEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScaleControlsEnabled(boolean z) {
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.getUiSettings().setScaleControlsEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setZoomControlsEnabled(boolean z) {
        AMap aMap = this.e;
        if (aMap != null) {
            aMap.getUiSettings().setZoomControlsEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreate(Bundle bundle, boolean z, boolean z2) {
        LogUtil.c("Track_GaoDeMap", "onCreate():");
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "onCreate mAmap is null");
            return;
        }
        if (this.al == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "onCreate mMapView is null");
            return;
        }
        aMap.getUiSettings().setZoomControlsEnabled(false);
        this.e.getUiSettings().setMyLocationButtonEnabled(false);
        this.e.getUiSettings().setTiltGesturesEnabled(false);
        if (z) {
            setPointToCenterWhole(true);
        }
        if (this.al.getChildCount() > 0) {
            LogUtil.h("Track_GaoDeMap", "onCreate: MapView ChildCount = ", Integer.valueOf(this.al.getChildCount()));
        } else {
            this.al.onCreate(bundle);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onSaveInstanceState(Bundle bundle) {
        TextureMapView textureMapView = this.al;
        if (textureMapView != null) {
            textureMapView.onSaveInstanceState(bundle);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenter(int i, int i2) {
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "setPointToCenter mAmap is null");
        } else {
            aMap.setPointToCenter(i, i2);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenterWhole(boolean z) {
        if (this.e == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "setPointToCenterWhole mAmap is null");
            return;
        }
        WindowManager windowManager = (WindowManager) this.n.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            this.e.setPointToCenter(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 4);
        } else {
            this.e.setPointToCenter(displayMetrics.widthPixels / 2, (displayMetrics.heightPixels * 2) / 5);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getZoomToSpanLevel(hjd hjdVar, hjd hjdVar2) {
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.d("Track_GaoDeMap", "getZoomToSpanLevel mAmap is null");
            return b;
        }
        return aMap.getZoomToSpanLevel(gwe.d(hjdVar), gwe.d(hjdVar2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScreenOnOrForegrand(boolean z) {
        TextureMapView textureMapView = this.al;
        if (textureMapView == null) {
            return;
        }
        this.ag = z;
        textureMapView.setBackgroundColor(this.n.getResources().getColor(R.color._2131296937_res_0x7f0902a9));
        if (z) {
            this.al.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.al.setVisibility(0);
            this.al.invalidate();
            forceDrawLine();
            return;
        }
        this.al.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        this.al.setVisibility(8);
        this.al.invalidate();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResume() {
        TextureMapView textureMapView = this.al;
        if (textureMapView != null) {
            textureMapView.onResume();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPause() {
        TextureMapView textureMapView = this.al;
        if (textureMapView != null) {
            textureMapView.onPause();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroy() {
        this.af = true;
        Marker marker = this.m;
        if (marker != null) {
            marker.setAnimationListener(null);
            this.m.destroy();
            this.m = null;
        }
        List<Marker> list = this.ay;
        if (list != null) {
            for (Marker marker2 : list) {
                if (marker2 != null) {
                    marker2.setAnimationListener(null);
                    marker2.destroy();
                }
            }
            this.ay.clear();
        }
        List<SmoothMarkerUtil> list2 = this.ar;
        if (list2 != null) {
            for (SmoothMarkerUtil smoothMarkerUtil : list2) {
                if (smoothMarkerUtil != null) {
                    smoothMarkerUtil.c();
                    smoothMarkerUtil.e();
                }
            }
            this.ar.clear();
        }
        b();
        if (this.n != null) {
            this.n = null;
        }
        Handler handler = this.ap;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        this.bf = null;
        AMap aMap = this.e;
        if (aMap != null) {
            if (!this.ad || this.z) {
                aMap.clear();
                this.e = null;
            }
        }
    }

    private void b() {
        TextureMapView textureMapView = this.al;
        if (textureMapView != null) {
            textureMapView.onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.ai) {
            return;
        }
        this.c.removeMessages(0);
        this.c.removeMessages(1);
        Message obtainMessage = this.c.obtainMessage();
        obtainMessage.what = i;
        this.c.sendMessageDelayed(obtainMessage, 900L);
    }

    public void d(boolean z) {
        if (!this.v) {
            ReleaseLogUtil.d("Track_GaoDeMap", "No PolygonCenter, no latlng point!");
            return;
        }
        Polygon polygon = this.aw;
        if (polygon != null) {
            polygon.remove();
        }
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.c("Track_GaoDeMap", "showMapLayer mAMap is null return");
        } else if (this.n != null) {
            if (z) {
                this.aw = aMap.addPolygon(new PolygonOptions().addAll(c()).zIndex(9.0f).fillColor(this.n.getResources().getColor(R.color._2131298134_res_0x7f090756)).strokeColor(0).strokeWidth(1.0f));
            } else {
                this.aw = aMap.addPolygon(new PolygonOptions().addAll(c()).zIndex(9.0f).fillColor(this.n.getResources().getColor(R.color._2131298016_res_0x7f0906e0)).strokeColor(0).strokeWidth(1.0f));
            }
        }
    }

    private List<LatLng> c() {
        return Arrays.asList(new LatLng(-89.9d, -179.9d), new LatLng(89.9d, -179.9d), new LatLng(89.9d, 179.9d), new LatLng(-89.9d, 179.9d));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setShowMapEnd(boolean z) {
        this.ad = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCameraChangeListener(Handler handler) {
        j();
    }

    private void j() {
        AMap aMap = this.e;
        if (aMap == null) {
            ReleaseLogUtil.c("Track_GaoDeMap", "setOnCameraChangeListener mAMap is null!");
        } else {
            aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() { // from class: hkk.15
                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChange(CameraPosition cameraPosition) {
                }

                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void forceDrawLine() {
        if (this.aj.size() <= 0) {
            return;
        }
        this.bc.setPoints(this.aj);
        this.av = a(this.bc);
        addEndMarker(a(this.aj.get(r0.size() - 1)));
        animateCamera(a(this.aj.get(r0.size() - 1)), 0L, (InterfaceMapCallback) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveToCenter() {
        AMap aMap;
        CameraUpdate cameraUpdate = this.j;
        if (cameraUpdate == null || (aMap = this.e) == null) {
            return;
        }
        aMap.moveCamera(cameraUpdate);
        this.e.moveCamera(this.g);
    }

    private void h() {
        int d2 = hji.d(this.az, this.s);
        for (int i = d2 - 1; i < this.an.size(); i += d2) {
            if (this.an.get(i).c) {
                this.as.add(this.e.addMarker(gwe.aUC_(this.n.getResources(), this.an.get(i).d, UnitUtil.e(this.an.get(i).e, 1, 0))));
            }
        }
        LogUtil.c("Track_GaoDeMap", "mMarkersList.size = ", Integer.valueOf(this.as.size()));
    }

    private void d() {
        Iterator<Marker> it = this.as.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.as.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showTrackMarkers(boolean z) {
        if (z) {
            h();
        } else {
            d();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom() {
        moveCameraByZoom(13.5f);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom(float f) {
        this.e.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByLatLng(hjd hjdVar) {
        this.e.moveCamera(CameraUpdateFactory.changeLatLng(gwe.d(hjdVar)));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, double[] dArr) {
        return gwe.c(this.n, dArr);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, Location location) {
        return gwe.aUD_(this.n, location);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void updateSportMarker(hjd hjdVar, BitmapDrawable bitmapDrawable) {
        Marker marker = this.m;
        if (marker != null) {
            marker.remove();
        }
        this.m = this.e.addMarker(new MarkerOptions().position(gwe.d(hjdVar)).draggable(false).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(bitmapDrawable.getBitmap())));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd convertLocationByCoordinate(Location location) {
        CoordinateConverter coordinateConverter = new CoordinateConverter(this.n);
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        coordinateConverter.coord(new LatLng(location.getLatitude(), location.getLongitude()));
        return gwe.b(coordinateConverter.convert());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setSportTabCenter(DisplayMetrics displayMetrics) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "mAmap is null");
            return;
        }
        this.e.setPointToCenter((displayMetrics.widthPixels / 2) - this.n.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), nsn.c(this.n, 120.0f));
        this.e.setMyLocationStyle(new MyLocationStyle());
        this.e.getUiSettings().setMyLocationButtonEnabled(false);
        this.e.setMyLocationEnabled(false);
        this.e.setMyLocationType(6);
    }

    static class d implements AMap.OnMapTouchListener {
        private WeakReference<hkk> e;

        d(hkk hkkVar) {
            this.e = new WeakReference<>(hkkVar);
        }

        @Override // com.amap.api.maps.AMap.OnMapTouchListener
        public void onTouch(MotionEvent motionEvent) {
            hkk hkkVar = this.e.get();
            if (hkkVar != null) {
                hkkVar.cancelAnimation();
            }
        }
    }

    static class c {
        private LatLng d;
        private boolean c = true;
        private int e = 0;

        c() {
        }

        public String toString() {
            return "to show" + this.c;
        }
    }

    private LatLng a(hjd hjdVar) {
        return gwe.e(hjdVar);
    }

    public static hjd a(LatLng latLng) {
        return new hjd(latLng.latitude, latLng.longitude);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public hlh e(CameraPosition cameraPosition) {
        if (cameraPosition == null) {
            return null;
        }
        hlh hlhVar = new hlh();
        hlhVar.d(cameraPosition.bearing).c(a(cameraPosition.target)).b(cameraPosition.tilt).e(cameraPosition.zoom);
        return hlhVar;
    }

    private CameraPosition a(hlh hlhVar) {
        if (hlhVar == null) {
            return null;
        }
        return new CameraPosition(a(hlhVar.d()), hlhVar.c(), hlhVar.b(), hlhVar.a());
    }

    private AMap.CancelableCallback c(final InterfaceMapCallback interfaceMapCallback) {
        return new AMap.CancelableCallback() { // from class: hkk.11
            @Override // com.amap.api.maps.AMap.CancelableCallback
            public void onFinish() {
                InterfaceMapCallback interfaceMapCallback2 = interfaceMapCallback;
                if (interfaceMapCallback2 != null) {
                    interfaceMapCallback2.onFinish();
                }
            }

            @Override // com.amap.api.maps.AMap.CancelableCallback
            public void onCancel() {
                InterfaceMapCallback interfaceMapCallback2 = interfaceMapCallback;
                if (interfaceMapCallback2 != null) {
                    interfaceMapCallback2.onCancel();
                }
            }
        };
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLogoPadding(int i, int i2, int i3, int i4) {
        AMap aMap = this.e;
        if (aMap != null) {
            UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setLogoBottomMargin(i4 + nsn.c(BaseApplication.getContext(), 2.0f));
            uiSettings.setLogoLeftMargin(i);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hlh getMapStatus() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in getMapStatus");
            return new hlh();
        }
        hlh e = e(aMap.getCameraPosition());
        if (e == null) {
            return new hlh();
        }
        e.c(this.e.getScalePerPixel());
        return e;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlh hlhVar, long j, final InterfaceMapCallback interfaceMapCallback) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in animateCamera");
            return;
        }
        CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(a(hlhVar));
        if (j <= 0) {
            this.e.moveCamera(newCameraPosition);
        } else {
            this.e.animateCamera(newCameraPosition, j, new AMap.CancelableCallback() { // from class: hkk.12
                @Override // com.amap.api.maps.AMap.CancelableCallback
                public void onFinish() {
                    InterfaceMapCallback interfaceMapCallback2 = interfaceMapCallback;
                    if (interfaceMapCallback2 != null) {
                        interfaceMapCallback2.onFinish();
                    }
                }

                @Override // com.amap.api.maps.AMap.CancelableCallback
                public void onCancel() {
                    InterfaceMapCallback interfaceMapCallback2 = interfaceMapCallback;
                    if (interfaceMapCallback2 != null) {
                        interfaceMapCallback2.onCancel();
                    }
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addLinePoint(hld hldVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in drawLine");
            return;
        }
        if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
            return;
        }
        if (this.d.size() <= 0) {
            this.d.add(this.e.addPolyline(new PolylineOptions().add(a(hldVar.a())).width(hldVar.b()).color(hldVar.e()).setDottedLine(hldVar.h()).setDottedLineType(1)));
            return;
        }
        int size = this.d.size() - 1;
        if (this.d.get(size) == null || d(this.d.get(size), hldVar) || koq.b(this.d.get(size).getPoints()) || this.d.get(size).getPoints().size() > 242) {
            PolylineOptions dottedLineType = new PolylineOptions().add(a(hldVar.a())).width(hldVar.b()).color(hldVar.e()).setDottedLine(hldVar.h()).setDottedLineType(1);
            if (!hldVar.h()) {
                dottedLineType.add(a(hldVar.c()));
            }
            this.d.add(this.e.addPolyline(dottedLineType));
            return;
        }
        PolylineOptions options = this.d.get(size).getOptions();
        List<LatLng> points = options.getPoints();
        if (points != null) {
            points.add(gwe.d(hldVar.a()));
            points.add(gwe.d(hldVar.c()));
            options.setPoints(points);
            Polyline addPolyline = this.e.addPolyline(options);
            this.d.get(size).remove();
            this.d.remove(size);
            this.d.add(addPolyline);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void removeLinePoint() {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in drawLine");
            return;
        }
        if (this.d.size() <= 0) {
            return;
        }
        int size = this.d.size() - 1;
        List<LatLng> points = this.d.get(size).getPoints();
        if (points != null) {
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                PolylineOptions options = this.d.get(size).getOptions();
                options.setPoints(points);
                Polyline addPolyline = this.e.addPolyline(options);
                this.d.get(size).remove();
                this.d.remove(size);
                this.d.add(addPolyline);
                return;
            }
            this.d.get(size).remove();
            this.d.remove(size);
            return;
        }
        this.d.get(size).remove();
        this.d.remove(size);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hld hldVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in drawLine");
        } else {
            if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
                return;
            }
            this.e.addPolyline(new PolylineOptions().add(a(hldVar.a()), a(hldVar.c())).width(hldVar.b()).color(hldVar.e()).setDottedLine(hldVar.h()).setDottedLineType(1));
        }
    }

    private boolean d(Polyline polyline, hld hldVar) {
        return (polyline.isDottedLine() && !hldVar.h()) || (!polyline.isDottedLine() && hldVar.h());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String drawLines(hle hleVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in drawLine");
            return null;
        }
        if (hleVar == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (hleVar.i() == null) {
            if (hleVar.a() == null || hleVar.c() == null) {
                return null;
            }
            arrayList.add(a(hleVar.a()));
            arrayList.add(a(hleVar.c()));
        } else {
            Iterator<hjd> it = hleVar.i().iterator();
            while (it.hasNext()) {
                arrayList.add(a(it.next()));
            }
        }
        PolylineOptions visible = new PolylineOptions().addAll(arrayList).width(hleVar.b()).setDottedLine(hleVar.h()).setDottedLineType(1).zIndex(hleVar.d()).useGradient(hleVar.j()).visible(hleVar.g());
        if (koq.b(hleVar.f())) {
            visible.color(hleVar.e());
        } else {
            visible.colorValues(hleVar.f());
        }
        Polyline addPolyline = this.e.addPolyline(visible);
        String id = addPolyline.getId();
        this.ax.put(id, addPolyline);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.b("Track_GaoDeMap", "id is null in setLineVisible");
            return;
        }
        Polyline polyline = this.ax.get(str);
        if (polyline == null) {
            return;
        }
        polyline.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMaxZoomLevel() {
        return this.e.getMaxZoomLevel();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public GrowAnimationBuilder getGrowAnimation() {
        return new hku();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int addMarker(hlg hlgVar, GrowAnimationBuilder growAnimationBuilder) {
        if (hlgVar == null || hlgVar.bhO_() == null) {
            return -1;
        }
        MarkerOptions visible = new MarkerOptions().setFlat(hlgVar.g()).anchor(((Float) hlgVar.bhN_().first).floatValue(), ((Float) hlgVar.bhN_().second).floatValue()).zIndex(hlgVar.d()).draggable(hlgVar.h()).icon(BitmapDescriptorFactory.fromBitmap(hlgVar.bhO_())).position(a(hlgVar.e())).visible(hlgVar.f());
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in addMarker");
            return -1;
        }
        Marker addMarker = aMap.addMarker(visible);
        if (growAnimationBuilder instanceof hku) {
            ((hku) growAnimationBuilder).d(addMarker);
            growAnimationBuilder.displayAnimation();
        }
        addMarker.setToTop();
        if (this.aq == null) {
            this.aq = new Vector<>(10);
        }
        this.aq.add(addMarker);
        return this.aq.size() - 1;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in restoreMarker");
            return;
        }
        Vector<Marker> vector = this.aq;
        if (vector == null || i < 0 || i >= vector.size()) {
            return;
        }
        Marker marker = this.aq.get(i);
        if (growAnimationBuilder == null) {
            marker.setVisible(true);
        }
        if (growAnimationBuilder instanceof hku) {
            ((hku) growAnimationBuilder).d(marker);
            growAnimationBuilder.displayAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void hideMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in deleteMarker");
            return;
        }
        Vector<Marker> vector = this.aq;
        if (vector == null || i < 0 || i >= vector.size()) {
            return;
        }
        Marker marker = this.aq.get(i);
        if (growAnimationBuilder == null) {
            marker.setVisible(false);
        }
        if (growAnimationBuilder instanceof hku) {
            ((hku) growAnimationBuilder).d(marker);
            growAnimationBuilder.disappearAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIcon(int i, Bitmap bitmap) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in setMarkerIcon");
            return;
        }
        Vector<Marker> vector = this.aq;
        if (vector == null || i < 0 || i >= vector.size()) {
            LogUtil.b("Track_GaoDeMap", "id is error in setMarkerIcon");
        } else {
            this.aq.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveMarker(int i, hjd hjdVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in deleteMarker");
            return;
        }
        Vector<Marker> vector = this.aq;
        if (vector == null || i < 0 || i >= vector.size()) {
            return;
        }
        this.aq.get(i).setPosition(a(hjdVar));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void clear() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "bounds is null in clear");
            return;
        }
        aMap.clear();
        this.aq = new Vector<>(10);
        this.d.clear();
        this.bh.clear();
        this.au.clear();
        this.ax.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void stopAnimation() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "bounds is null in clear");
        } else {
            aMap.stopAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPreviewStatus(hla hlaVar, int i, int i2, int i3, int i4) {
        if (hlaVar == null) {
            LogUtil.b("Track_GaoDeMap", "bounds is null in setPreviewStatus");
        } else if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in setPreviewStatus");
        } else {
            this.e.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(new LatLngBounds.Builder().include(a(hlaVar.b())).include(a(hlaVar.c())).build(), i, i2, i3, i4));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapLoadedCallback(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in setMapLoadedCallback");
        } else {
            if (interfaceMapLoadedCallback == null) {
                return;
            }
            this.e.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: hkk.4
                @Override // com.amap.api.maps.AMap.OnMapLoadedListener
                public void onMapLoaded() {
                    hkk.this.ae = true;
                    interfaceMapLoadedCallback.onMapLoaded();
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setCameraChangeCallback(final InterfaceMapStatusChangeCallback interfaceMapStatusChangeCallback) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in setCameraChangeCallback");
        } else {
            if (interfaceMapStatusChangeCallback == null) {
                return;
            }
            this.e.setOnCameraChangeListener(new AMap.OnCameraChangeListener() { // from class: hkk.1
                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChange(CameraPosition cameraPosition) {
                    interfaceMapStatusChangeCallback.onMapStatusChange(hkk.this.e(cameraPosition));
                }

                @Override // com.amap.api.maps.AMap.OnCameraChangeListener
                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                    interfaceMapStatusChangeCallback.onMapStatusChangeFinish(hkk.this.e(cameraPosition));
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreatePurely(Bundle bundle) {
        TextureMapView textureMapView = this.al;
        if (textureMapView == null) {
            LogUtil.b("Track_GaoDeMap", "mapview is null in onCreatePurely");
        } else if (textureMapView.getChildCount() <= 0) {
            this.al.onCreate(bundle);
        } else {
            LogUtil.h("Track_GaoDeMap", "onCreatePurely: MapView ChildCount = ", Integer.valueOf(this.al.getChildCount()));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPausePurely() {
        TextureMapView textureMapView = this.al;
        if (textureMapView == null) {
            LogUtil.b("Track_GaoDeMap", "mapview is null in onPausePurely");
        } else {
            textureMapView.onPause();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResumePurely() {
        TextureMapView textureMapView = this.al;
        if (textureMapView == null) {
            LogUtil.b("Track_GaoDeMap", "mapview is null in onResumePurely");
        } else {
            textureMapView.onResume();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroyPurely() {
        TextureMapView textureMapView = this.al;
        if (textureMapView == null) {
            LogUtil.b("Track_GaoDeMap", "mapview is null in onDestroyPurely");
        } else {
            textureMapView.onDestroy();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableAllGestures() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in disableAllGestures");
            return;
        }
        aMap.getUiSettings().setAllGesturesEnabled(false);
        this.e.getUiSettings().setScaleControlsEnabled(false);
        this.e.getUiSettings().setZoomControlsEnabled(false);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showPureMap() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in showPureMap");
            return;
        }
        aMap.showMapText(false);
        this.e.getUiSettings().setCompassEnabled(false);
        this.e.getUiSettings().setIndoorSwitchEnabled(false);
        this.e.getUiSettings().setMyLocationButtonEnabled(false);
    }

    private void b(hak hakVar) {
        if (hakVar == null) {
            LogUtil.h("Track_GaoDeMap", "initCustomMapStyle customMapInformation == null");
            return;
        }
        CustomMapStyleOptions customMapStyleOptions = new CustomMapStyleOptions();
        String i = hakVar.i();
        String g = hakVar.g();
        if (TextUtils.isEmpty(i) || TextUtils.isEmpty(g)) {
            LogUtil.h("Track_GaoDeMap", "TextUtils.isEmpty(dataName) || TextUtils.isEmpty(dataExtraName)");
            return;
        }
        customMapStyleOptions.setStyleDataPath(i);
        customMapStyleOptions.setStyleExtraPath(g);
        this.e.setCustomMapStyle(customMapStyleOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeMapType(MapTypeDescription mapTypeDescription, hak hakVar) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in changeMapType");
            return;
        }
        if (mapTypeDescription == null) {
            LogUtil.b("Track_GaoDeMap", "type == null");
            return;
        }
        int i = AnonymousClass10.c[mapTypeDescription.c().ordinal()];
        if (i == 1) {
            this.e.setMapType(2);
            return;
        }
        if (i == 2) {
            this.e.setMapType(1);
            return;
        }
        if (i == 3) {
            this.e.setMapType(3);
        } else if (i == 4) {
            this.e.setMapType(4);
        } else {
            if (i != 5) {
                return;
            }
            b(hakVar);
        }
    }

    /* renamed from: hkk$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[MapTypeDescription.MapType.values().length];
            c = iArr;
            try {
                iArr[MapTypeDescription.MapType.MAP_TYPE_SATELLITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[MapTypeDescription.MapType.MAP_TYPE_NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[MapTypeDescription.MapType.MAP_TYPE_NIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[MapTypeDescription.MapType.MAP_TYPE_NAVI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[MapTypeDescription.MapType.MAP_TYPE_CUSTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static class a extends Handler {
        private WeakReference<hkk> e;

        private a(Looper looper, hkk hkkVar) {
            super(looper);
            this.e = new WeakReference<>(hkkVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            hkk hkkVar = this.e.get();
            if (hkkVar == null) {
                LogUtil.h("Track_GaoDeMap", "MultiDrawHandler handleMessage gaoDeMap is null");
                return;
            }
            if (message == null) {
                LogUtil.h("Track_GaoDeMap", "MultiDrawHandler msg is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                hkkVar.ab = false;
                if (hkkVar.bf != null) {
                    hkkVar.bf.onFinish();
                    return;
                }
                return;
            }
            if (i != 1) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                if (hkkVar.t.size() > intValue && hkkVar.ba.size() > intValue) {
                    Marker c = gwe.c(hkkVar.e, gwe.b((LatLng) hkkVar.t.get(intValue)), ((Integer) hkkVar.ba.get(intValue)).intValue());
                    if (c == null) {
                        LogUtil.b("Track_GaoDeMap", "handleMessage marker is null");
                        return;
                    } else {
                        e(hkkVar, intValue, c);
                        return;
                    }
                }
                LogUtil.b("Track_GaoDeMap", "wrong branch return");
            }
        }

        private void e(final hkk hkkVar, final int i, Marker marker) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
            scaleAnimation.setDuration(300L);
            marker.setAnimation(scaleAnimation);
            marker.startAnimation();
            postDelayed(new Runnable() { // from class: hkg
                @Override // java.lang.Runnable
                public final void run() {
                    hkk.a.b(i, hkkVar);
                }
            }, 300L);
            hkkVar.ay.add(marker);
        }

        static /* synthetic */ void b(int i, hkk hkkVar) {
            if (i < hkkVar.ar.size()) {
                hkkVar.aa = true;
                hkkVar.ab = true;
                ((SmoothMarkerUtil) hkkVar.ar.get(i)).b();
                return;
            }
            LogUtil.h("Track_GaoDeMap", "setMarkerAnimation markerIndex is out of bounds");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapTilt() {
        AMap aMap = this.e;
        if (aMap == null) {
            return 0.0f;
        }
        return aMap.getCameraPosition().tilt;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapZoom() {
        AMap aMap = this.e;
        if (aMap == null) {
            return 0.0f;
        }
        return aMap.getCameraPosition().zoom;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public Point getScreenLocation(hjd hjdVar) {
        AMap aMap = this.e;
        if (aMap == null || hjdVar == null) {
            LogUtil.b("Track_GaoDeMap", "huaweiMap is null or latlng is null");
            return new Point();
        }
        Projection projection = aMap.getProjection();
        if (projection == null) {
            LogUtil.b("Track_GaoDeMap", "projection is null or latlng is null");
            return new Point();
        }
        return projection.toScreenLocation(gwe.d(hjdVar));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd getLatLngByScreenPoint(Point point) {
        AMap aMap = this.e;
        if (aMap == null || point == null) {
            LogUtil.b("Track_GaoDeMap", "huaweiMap is null or point is null");
            return new hjd(0.0d, 0.0d);
        }
        Projection projection = aMap.getProjection();
        if (projection == null) {
            LogUtil.b("Track_GaoDeMap", "projection is null");
            return new hjd(0.0d, 0.0d);
        }
        return gwe.b(projection.fromScreenLocation(point));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableMarkerClick() {
        AMap aMap = this.e;
        if (aMap == null) {
            LogUtil.b("Track_GaoDeMap", "mAMap is null in disableMarkerClick");
        } else {
            aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() { // from class: hkk.2
                @Override // com.amap.api.maps.AMap.OnMarkerClickListener
                public boolean onMarkerClick(Marker marker) {
                    return true;
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addMarkerClickListener(final InterfaceMapMarkerClickCallback interfaceMapMarkerClickCallback) {
        AMap aMap = this.e;
        if (aMap == null) {
            return;
        }
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() { // from class: hkk.3
            @Override // com.amap.api.maps.AMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                int i = 0;
                if (interfaceMapMarkerClickCallback == null || marker == null) {
                    LogUtil.h("Track_GaoDeMap", "interfaceMapMarkerClickCallback or marker is null");
                    return false;
                }
                while (true) {
                    if (i >= hkk.this.aq.size()) {
                        i = -1;
                        break;
                    }
                    if (marker.equals(hkk.this.aq.get(i))) {
                        break;
                    }
                    i++;
                }
                LogUtil.a("Track_GaoDeMap", "click Marker count:", Integer.valueOf(i));
                interfaceMapMarkerClickCallback.onMarkerClick(i);
                return true;
            }
        });
    }

    static class b extends BaseHandler<hkk> {
        b(Looper looper, hkk hkkVar) {
            super(looper, hkkVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bhG_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hkk hkkVar, Message message) {
            if (message != null) {
                if (hkkVar.l != null) {
                    if (message.what == 0) {
                        if (BaseApplication.isRunningForeground() && hkkVar.ag) {
                            hkkVar.l.setVisible(false);
                        }
                        hkkVar.d(1);
                        return;
                    }
                    if (message.what == 1) {
                        if (BaseApplication.isRunningForeground() && hkkVar.ag) {
                            hkkVar.l.setVisible(true);
                        }
                        hkkVar.d(0);
                        return;
                    }
                    LogUtil.b("Track_GaoDeMap", "mAddCircleHandler msg is wrong");
                    return;
                }
                return;
            }
            LogUtil.h("Track_GaoDeMap", "msg is null");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addHiHealthMarkers(List<hjg> list) {
        ArrayList<MarkerOptions> e = gwe.e(list);
        if (koq.b(e)) {
            return;
        }
        e.forEach(new Consumer() { // from class: hkj
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hkk.this.d((MarkerOptions) obj);
            }
        });
    }

    /* synthetic */ void d(MarkerOptions markerOptions) {
        this.e.addMarker(markerOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlc hlcVar, int i) {
        CameraUpdate newLatLngBounds;
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in animateCameraBounds");
            return;
        }
        if (hlcVar == null || koq.b(hlcVar.e())) {
            LogUtil.b("Track_GaoDeMap", "param is null in animateCameraByBounds");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<hjd> it = hlcVar.e().iterator();
        while (it.hasNext()) {
            builder.include(gwe.d(it.next()));
        }
        LatLngBounds build = builder.build();
        LatLng latLng = new LatLng((build.northeast.latitude + build.southwest.latitude) / 2.0d, (build.northeast.longitude + build.southwest.longitude) / 2.0d);
        int d2 = hlcVar.d();
        int a2 = hlcVar.a();
        if (d2 == 0 || a2 == 0) {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, hlcVar.c());
        } else {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, d2, a2, hlcVar.c());
        }
        this.e.moveCamera(newLatLngBounds);
        float f = newLatLngBounds.getCameraUpdateFactoryDelegate().zoom;
        float b2 = hlcVar.b();
        if (Float.compare(b2, 0.0f) != 0) {
            c(b2, latLng, f, i);
        }
    }

    private void c(float f, LatLng latLng, float f2, int i) {
        final CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(latLng).bearing(f).zoom(f2).build());
        if (i <= 0) {
            this.e.moveCamera(newCameraPosition);
        } else {
            this.e.animateCamera(newCameraPosition, i, new AMap.CancelableCallback() { // from class: hkk.7
                @Override // com.amap.api.maps.AMap.CancelableCallback
                public void onFinish() {
                }

                @Override // com.amap.api.maps.AMap.CancelableCallback
                public void onCancel() {
                    hkk.this.e.moveCamera(newCameraPosition);
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void scrollBy(float f, float f2) {
        if (this.e == null) {
            LogUtil.b("Track_GaoDeMap", "map is null in scrollBy");
        } else {
            this.e.moveCamera(CameraUpdateFactory.scrollBy(f, f2));
        }
    }
}
