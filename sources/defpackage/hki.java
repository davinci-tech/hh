package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceSnapshotCallback;
import com.huawei.healthcloud.plugintrack.ui.map.animation.GrowAnimationBuilder;
import com.huawei.healthcloud.plugintrack.ui.map.mapdescription.MapTypeDescription;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class hki implements InterfaceHiMap {
    private List<hjd> ae;
    private PolylineOptions af;
    private Polyline ai;
    private Marker al;
    private SupportMapFragment ao;
    private List<LatLng> aq;
    private Context c;
    private String e;
    private Marker h;
    private Polyline i;
    private GoogleMap k;
    private String l;
    private List<Integer> o;
    private InterfaceMapCallback w;
    private SparseArray<Marker> x;
    private List<LatLng> y;
    private Marker g = null;
    private boolean p = true;
    private boolean t = true;
    private boolean q = true;
    private ArrayList<Marker> z = new ArrayList<>(16);
    private ArrayList<a> u = new ArrayList<>(16);
    private int ar = 0;
    private int j = 0;
    private int ak = 0;
    private int ac = 0;
    private int m = 0;

    /* renamed from: a, reason: collision with root package name */
    private CameraUpdate f13198a = null;
    private List<hjd> aj = new ArrayList(16);
    private List<LatLng> an = new ArrayList(16);
    private List<LatLng> f = new ArrayList(16);
    private List<Integer> am = new ArrayList(16);
    private List<PolylineOptions> ag = new ArrayList(16);
    private List<List<LatLng[]>> ab = new ArrayList(16);
    private ArrayList<MarkerOptions> ad = new ArrayList<>(16);
    private List<hjg> n = new ArrayList(16);
    private boolean r = true;
    private boolean s = true;
    private int v = -1;
    private Vector<Polyline> b = new Vector<>();
    private final Map<String, GroundOverlay> aa = new ConcurrentHashMap();
    private final Map<Polyline, PolylineOptions> ap = new ConcurrentHashMap();
    private final Map<String, Polyline> ah = new ConcurrentHashMap();
    private int as = gwh.q;
    private Handler d = new Handler() { // from class: hki.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("Track_GoogleMapModel", "msg is null");
                return;
            }
            super.handleMessage(message);
            if (hki.this.h != null) {
                if (message.what == 0) {
                    hki.this.h.setVisible(false);
                    hki.this.a(1);
                    return;
                }
                if (message.what == 1) {
                    hki.this.h.setVisible(true);
                    hki.this.a(0);
                    return;
                }
                if (message.what == 2) {
                    hki.this.c();
                    return;
                }
                if (message.what == 3) {
                    if (!koq.b(hki.this.y)) {
                        if (hki.this.ai != null) {
                            hki.this.ai.setPoints(hki.this.y);
                            int size = hki.this.y.size() - 1;
                            if (koq.d(hki.this.y, size)) {
                                hki hkiVar = hki.this;
                                hkiVar.addEndMarker(gwc.e((LatLng) hkiVar.y.get(size)));
                                hki hkiVar2 = hki.this;
                                hkiVar2.animateCamera(gwc.e((LatLng) hkiVar2.y.get(size)), 1000L, (InterfaceMapCallback) null);
                                return;
                            }
                            return;
                        }
                        LogUtil.h("Track_GoogleMapModel", "handleMessage: mPolyLine is null");
                        return;
                    }
                    LogUtil.h("Track_GoogleMapModel", "handleMessage: mLineList is empty");
                    return;
                }
                LogUtil.c("Track_GoogleMapModel", "wrong msg");
            }
        }
    };

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void cancelAnimation() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd convertLocationByCoordinate(Location location) {
        return null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, double[] dArr) {
        return dArr;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int getMapEngineType() {
        return 2;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getZoomToSpanLevel(hjd hjdVar, hjd hjdVar2) {
        return 15.0f;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public boolean isAnimationStart() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public boolean isClockwise() {
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCameraChangeListener(Handler handler) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPause() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResume() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStart() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStop() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerAnimationListener(InterfaceMapCallback interfaceMapCallback) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void saveAddress(MotionData motionData) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenter(int i, int i2) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPointToCenterWhole(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScaleControlsEnabled(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setShowMapEnd(boolean z) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setSportTabCenter(DisplayMetrics displayMetrics) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void startMapAnimation(boolean z) {
    }

    public hki(Context context, SupportMapFragment supportMapFragment, GoogleMap googleMap) {
        if (context == null || googleMap == null) {
            throw new RuntimeException("context or map is null");
        }
        this.c = context;
        this.k = googleMap;
        this.ao = supportMapFragment;
        PolylineOptions polylineOptions = new PolylineOptions();
        this.af = polylineOptions;
        polylineOptions.color(this.as).width(15.0f).zIndex(10.0f).visible(true);
        this.y = new ArrayList(16);
        this.ai = this.k.addPolyline(this.af);
        this.aq = new ArrayList(16);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawAddMapTracking(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            return;
        }
        this.aq.add(gwc.c(hjdVar));
        this.ai.setPoints(this.aq);
        animateCamera(hjdVar2, 1000L, (InterfaceMapCallback) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hjd hjdVar, long j, InterfaceMapCallback interfaceMapCallback) {
        float f;
        if (hjdVar == null || this.k == null) {
            return;
        }
        GoogleMap.CancelableCallback a2 = a(interfaceMapCallback);
        if (this.p) {
            this.p = false;
            f = 16.5f;
        } else if (this.k.getCameraPosition() == null) {
            return;
        } else {
            f = this.k.getCameraPosition().zoom;
        }
        CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(gwc.c(hjdVar)).zoom(f).bearing(0.0f).tilt(25.0f).build());
        if (this.s) {
            this.s = false;
            this.k.moveCamera(newCameraPosition);
        } else {
            this.k.animateCamera(newCameraPosition, a2);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addLinePoint(hld hldVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in drawLine");
            return;
        }
        if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
            return;
        }
        if (this.b.size() <= 0) {
            this.b.add(this.k.addPolyline(new PolylineOptions().add(gwc.c(hldVar.a())).width(hldVar.b() * 0.9f).color(hldVar.e())));
            return;
        }
        int size = this.b.size() - 1;
        if (this.b.get(size) == null || c(hldVar, this.b.get(size)) || koq.b(this.b.get(size).getPoints()) || this.b.get(size).getPoints().size() > 242) {
            PolylineOptions color = new PolylineOptions().add(gwc.c(hldVar.a())).width(hldVar.b() * 0.9f).color(hldVar.e());
            if (hldVar.h()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Dot());
                color.pattern(arrayList);
            } else {
                color.add(gwc.c(hldVar.c()));
            }
            this.b.add(this.k.addPolyline(color));
            return;
        }
        List<LatLng> points = this.b.get(size).getPoints();
        points.add(gwc.c(hldVar.a()));
        points.add(gwc.c(hldVar.c()));
        this.b.get(size).setPoints(points);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void removeLinePoint() {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in drawLine");
            return;
        }
        if (this.b.size() <= 0) {
            LogUtil.h("Track_GoogleMapModel", "mAheadMoveLines.size() <= 0");
            return;
        }
        int size = this.b.size() - 1;
        if (koq.b(this.b, size)) {
            LogUtil.h("Track_GoogleMapModel", "removeLinePoint mAheadMoveLines isOutOfBounds ", Integer.valueOf(size));
            return;
        }
        List<LatLng> points = this.b.get(size).getPoints();
        if (points != null) {
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                this.b.get(size).setPoints(points);
                return;
            } else {
                this.b.get(size).remove();
                this.b.remove(size);
                return;
            }
        }
        this.b.get(size).remove();
        this.b.remove(size);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(List<hiy> list) {
        if (koq.b(list)) {
            return;
        }
        hjd e = gwf.e(list.get(list.size() - 1));
        this.y.add(gwc.c(e));
        if (this.t) {
            if (this.ai == null) {
                LogUtil.h("Track_GoogleMapModel", "drawLine: mPolyLine is null");
                return;
            }
            if (koq.b(this.y)) {
                LogUtil.h("Track_GoogleMapModel", "drawLine: mLineList is empty");
                return;
            }
            this.ai.setPoints(this.y);
            if (list.size() > 0) {
                addEndMarker(e);
                animateCamera(e, 1000L, (InterfaceMapCallback) null);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineColor(int i, boolean z) {
        Polyline polyline;
        this.as = i;
        if (!z || (polyline = this.ai) == null) {
            return;
        }
        polyline.setColor(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "drawLine lastLatLng or currentLatLng is null");
            return;
        }
        addEndMarker(hjdVar2);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.as).width(15.0f).zIndex(10.0f).visible(true).add(gwc.c(hjdVar), gwc.c(hjdVar2));
        this.k.addPolyline(polylineOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawInterrupt(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "drawInterrupt lastLatLng or currentLatLng is null");
            return;
        }
        addEndMarker(hjdVar);
        Iterator<PolylineOptions> it = d(gwc.c(hjdVar), gwc.c(hjdVar2)).iterator();
        while (it.hasNext()) {
            this.k.addPolyline(it.next());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addStartMarker(hjd hjdVar, int i) {
        if (hjdVar == null) {
            return;
        }
        gwc.d(this.k, hjdVar, i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addSportStartMarker(hjd hjdVar, int i) {
        if (hjdVar == null) {
            return;
        }
        Marker marker = this.al;
        if (marker != null) {
            marker.remove();
        }
        this.al = gwc.d(this.k, hjdVar, i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIconPath(String str, String str2) {
        this.l = str;
        this.e = str2;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String addOverlay(hlj hljVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "mAMap is null in addGroundOverlay");
            return null;
        }
        if (hljVar == null) {
            LogUtil.b("Track_GoogleMapModel", "overlayDesc is null in addGroundOverlay");
            return null;
        }
        GroundOverlay addGroundOverlay = this.k.addGroundOverlay(new GroundOverlayOptions().position(gwc.c(hljVar.b()), hljVar.c()).image(BitmapDescriptorFactory.fromBitmap(hljVar.bhR_())).bearing(hljVar.d()).zIndex(hljVar.e()).visible(hljVar.h()));
        if (addGroundOverlay == null) {
            LogUtil.b("Track_GoogleMapModel", "add GroundOverlay fail");
            return null;
        }
        String id = addGroundOverlay.getId();
        this.aa.put(id, addGroundOverlay);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOverlayVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.a("Track_GoogleMapModel", "id is null in setOverlayVisible");
            return;
        }
        GroundOverlay groundOverlay = this.aa.get(str);
        if (groundOverlay == null) {
            return;
        }
        groundOverlay.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar, boolean z) {
        if (z) {
            this.g = null;
        }
        addEndMarker(hjdVar);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "updateMarkers latLng == null");
            return;
        }
        Marker marker = this.g;
        if (marker == null) {
            Marker e = gwc.e(this.k, hjdVar, this.e);
            this.h = e;
            if (this.r) {
                if (e != null) {
                    e.setVisible(false);
                }
                this.g = this.k.addMarker(new MarkerOptions().position(gwc.c(hjdVar)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428707_res_0x7f0b0563)));
                return;
            } else {
                this.g = gwc.b(this.k, hjdVar, this.l);
                a(1);
                return;
            }
        }
        marker.setPosition(gwc.c(hjdVar));
        Marker marker2 = this.h;
        if (marker2 != null) {
            marker2.setPosition(gwc.c(hjdVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (e()) {
            return;
        }
        Handler handler = this.d;
        if (handler == null) {
            ReleaseLogUtil.c("Track_GoogleMapModel", "mAddCircleHandler is null in updateMarkerBgMsg");
            return;
        }
        handler.removeMessages(0);
        this.d.removeMessages(1);
        Message obtainMessage = this.d.obtainMessage();
        obtainMessage.what = i;
        this.d.sendMessageDelayed(obtainMessage, 900L);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadMapWithPreprocessData(List<hkx> list) {
        if (list == null) {
            InterfaceMapCallback interfaceMapCallback = this.w;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
                return;
            }
            return;
        }
        this.ae = new ArrayList();
        this.ac = list.size();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int i = 0;
        if (this.ac > 0) {
            this.ak = list.get(0).g();
            this.j = list.get(0).a().size();
        }
        for (hkx hkxVar : list) {
            this.am.add(Integer.valueOf(hkxVar.g()));
            d(hkxVar, builder);
            b(hkxVar);
            a(i, hkxVar);
            i++;
            this.aj.addAll(this.ae);
            this.n = hkxVar.c();
            this.ad = gwe.d(hkxVar.c());
        }
        c(builder);
        Context context = this.c;
        if (context != null && (context instanceof Activity)) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: hkp
                @Override // java.lang.Runnable
                public final void run() {
                    hki.this.b();
                }
            });
        }
        InterfaceMapCallback interfaceMapCallback2 = this.w;
        if (interfaceMapCallback2 != null) {
            interfaceMapCallback2.onFinish();
        }
        LogUtil.a("Track_GoogleMapModel", "initGoogleMap loadMapWithPreprocessData end");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeTrackColor(int i) {
        Iterator<Polyline> it = this.ap.keySet().iterator();
        while (it.hasNext()) {
            it.next().setColor(i);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void resetTrackColor() {
        for (Map.Entry<Polyline, PolylineOptions> entry : this.ap.entrySet()) {
            entry.getKey().setColor(entry.getValue().getColor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.k == null || koq.b(this.ad)) {
            return;
        }
        for (int i = 1; i < this.ad.size() - 1; i++) {
            MarkerOptions markerOptions = this.ad.get(i);
            if (markerOptions != null) {
                this.k.addMarker(markerOptions);
            }
        }
    }

    private void c(LatLngBounds.Builder builder) {
        Context context = this.c;
        if (context == null) {
            LogUtil.h("Track_GoogleMapModel", "setCenterPoint() Context is null");
            return;
        }
        int dimension = (int) context.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f);
        int dimension2 = (int) (this.c.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d);
        try {
            LatLngBounds build = builder.build();
            LatLng latLng = build.southwest;
            LatLng latLng2 = build.northeast;
            LatLng latLng3 = new LatLng(latLng2.latitude - ((latLng2.latitude - latLng.latitude) * 1.55d), latLng.longitude);
            LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
            builder2.include(build.northeast);
            builder2.include(latLng3);
            this.f13198a = CameraUpdateFactory.newLatLngBounds(builder2.build(), dimension, dimension2, 10);
        } catch (IllegalArgumentException e) {
            LogUtil.b("Track_GoogleMapModel", e.getMessage());
        }
    }

    private void d(hkx hkxVar, LatLngBounds.Builder builder) {
        List<hjf> e = hkxVar.e();
        if (koq.b(e)) {
            LogUtil.a("Track_GoogleMapModel", "lineList is empty");
            InterfaceMapCallback interfaceMapCallback = this.w;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
                return;
            }
            return;
        }
        for (hjf hjfVar : e) {
            PolylineOptions a2 = a(hjfVar, hkxVar.g());
            ArrayList arrayList = new ArrayList();
            int e2 = hjfVar.e();
            for (hjd hjdVar : hjfVar.d()) {
                LatLng c = gwc.c(hjdVar);
                arrayList.add(c);
                builder.include(c);
                if (e2 != 2) {
                    this.ae.add(hjdVar);
                }
            }
            if (hjfVar.e() == 2) {
                this.ag.addAll(d((LatLng) arrayList.get(0), (LatLng) arrayList.get(arrayList.size() - 1)));
            } else {
                a2.addAll(arrayList);
            }
            this.ag.add(a2);
        }
    }

    private PolylineOptions a(hjf hjfVar, int i) {
        if ((kxb.c(i) || i == 222) && hjfVar.e() == 1 && hjfVar.a().size() > 0) {
            return new PolylineOptions().color(hjfVar.a().get(0).intValue()).width(15.0f).zIndex(10.0f).visible(true);
        }
        return new PolylineOptions().color(gwh.w).width(15.0f).zIndex(10.0f).visible(true);
    }

    private void b(hkx hkxVar) {
        List<hjd> d = hkxVar.d();
        if (!koq.b(d)) {
            Iterator<hjd> it = d.iterator();
            while (it.hasNext()) {
                this.an.add(gwc.c(it.next()));
            }
        }
        List<hjd> b = hkxVar.b();
        if (koq.b(b)) {
            return;
        }
        Iterator<hjd> it2 = b.iterator();
        while (it2.hasNext()) {
            this.f.add(gwc.c(it2.next()));
        }
    }

    private void a(int i, hkx hkxVar) {
        List<hjd> a2 = hkxVar.a();
        if (koq.b(a2)) {
            return;
        }
        for (hjd hjdVar : a2) {
            a aVar = new a();
            aVar.d = gwc.c(hjdVar);
            aVar.c = true;
            if (!koq.b(this.u)) {
                d(i, aVar);
            }
            this.u.add(aVar);
        }
    }

    private void d(int i, a aVar) {
        a aVar2 = this.u.get(r0.size() - 1);
        if (aVar2 == null || !aVar2.equals(aVar)) {
            return;
        }
        if (this.f.size() <= i || !aVar.d.equals(this.f.get(i))) {
            aVar2.c = false;
        } else {
            aVar.c = false;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(List<MotionData> list) {
        List<hjd> list2;
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "onMapLoaded data is null");
            return;
        }
        this.ak = list.get(0).getSportType();
        this.ac = list.size();
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (MotionData motionData : list) {
            ArrayList arrayList3 = new ArrayList(16);
            Map<Long, double[]> lbsDataMap = motionData.getLbsDataMap();
            if (lbsDataMap == null || lbsDataMap.isEmpty()) {
                return;
            }
            a(arrayList, arrayList2, arrayList3, lbsDataMap);
            LogUtil.a("Track_GoogleMapModel", "list SIZE:" + arrayList2.size());
            if (arrayList2.size() < 3 && this.r) {
                return;
            }
            e(arrayList3);
            b(arrayList3, motionData.getSportType());
            b(arrayList3);
            List<hjd> list3 = this.aj;
            if (list3 != null && (list2 = this.ae) != null) {
                list3.addAll(list2);
            }
        }
        if (koq.b(arrayList2)) {
            return;
        }
        ArrayList arrayList4 = new ArrayList(arrayList2.size());
        Iterator<LatLng> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList4.add(gwc.e(it.next()));
        }
        moveCameraLatLngBounds(arrayList4);
        e(arrayList2, list.get(0).getPaceMap());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(hjd hjdVar, float f) {
        CommonUtil.a("Track_GoogleMapModel", "into google map");
        if (hjdVar == null) {
            LogUtil.a("Track_GoogleMapModel", "onMapLoaded, hiHealthLatLng is null");
            return;
        }
        this.k.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(hjdVar.b, hjdVar.d)));
        this.k.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOnMapLoadedListener(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        GoogleMap googleMap = this.k;
        if (googleMap != null) {
            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: hki.12
                @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    InterfaceMapLoadedCallback interfaceMapLoadedCallback2 = interfaceMapLoadedCallback;
                    if (interfaceMapLoadedCallback2 != null) {
                        interfaceMapLoadedCallback2.onMapLoaded();
                    }
                }
            });
        }
    }

    private void a(List<LatLng> list, List<LatLng> list2, List<LatLng> list3, Map<Long, double[]> map) {
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            LatLng latLng = new LatLng(entry.getValue()[0], entry.getValue()[1]);
            if (list2.size() <= 0 || !gwe.b(gwc.e(list2.get(list2.size() - 1)), gwc.e(latLng))) {
                list2.add(latLng);
                list3.add(latLng);
                if (!gwe.c(gwc.e(latLng))) {
                    list.add(latLng);
                }
            }
        }
    }

    private void b(List<LatLng> list) {
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            LatLng latLng = list.get(size);
            if (!gwe.c(gwc.e(latLng))) {
                addEndMarker(gwc.e(latLng));
                this.m = size;
                LogUtil.a("Track_GoogleMapModel", "addEndMarker");
                return;
            }
        }
    }

    private void b(List<LatLng> list, int i) {
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            LatLng latLng = list.get(i2);
            if (!gwe.c(gwc.e(latLng))) {
                addSportStartMarker(gwc.e(latLng), i);
                this.ar = i2;
                List<hjd> list2 = this.ae;
                if (list2 != null) {
                    list2.add(0, c(latLng));
                }
                LogUtil.a("Track_GoogleMapModel", "addStartMarker");
                return;
            }
        }
    }

    private void e(List<LatLng> list) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.as).width(15.0f).zIndex(10.0f).visible(true);
        int size = list.size();
        ArrayList arrayList = new ArrayList(16);
        b(list, polylineOptions, size, arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        this.k.addPolyline(polylineOptions).setPoints(arrayList);
        if (this.ae != null) {
            d(arrayList);
        }
    }

    private void d(List<LatLng> list) {
        boolean z = true;
        for (LatLng latLng : list) {
            if (z) {
                z = false;
            } else {
                this.ae.add(c(latLng));
            }
        }
    }

    private void b(List<LatLng> list, PolylineOptions polylineOptions, int i, List<LatLng> list2) {
        for (int i2 = 0; i2 < i; i2++) {
            LatLng latLng = list.get(i2);
            if (gwe.c(gwc.e(latLng))) {
                if (!list2.isEmpty()) {
                    this.k.addPolyline(polylineOptions).setPoints(list2);
                    if (this.ae != null) {
                        d(list2);
                    }
                    list2.clear();
                }
                a(list, i2);
            } else {
                list2.add(latLng);
            }
        }
    }

    private hjd c(LatLng latLng) {
        return gwc.e(latLng);
    }

    private void a(List<LatLng> list, int i) {
        if (list == null) {
            return;
        }
        int size = list.size();
        LatLng latLng = i > 0 ? list.get(i - 1) : null;
        LatLng latLng2 = i < size + (-1) ? list.get(i + 1) : null;
        if (latLng == null || latLng2 == null) {
            return;
        }
        Iterator<PolylineOptions> it = d(latLng, latLng2).iterator();
        while (it.hasNext()) {
            this.k.addPolyline(it.next());
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadingEnd() {
        Marker d;
        CameraUpdate cameraUpdate = this.f13198a;
        if (cameraUpdate != null) {
            this.k.moveCamera(cameraUpdate);
        }
        for (int i = 0; i < this.ac; i++) {
            List<LatLng> list = this.an;
            if (list != null && (d = gwc.d(this.k, gwc.e(list.get(i)), this.am.get(i).intValue())) != null) {
                d.setZIndex(14.0f);
            }
            if (this.f != null) {
                if (i < this.ac - 1) {
                    this.g = this.k.addMarker(new MarkerOptions().position(this.f.get(i)).draggable(false).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable._2131428708_res_0x7f0b0564)).zIndex(14.0f));
                } else {
                    Bitmap aUI_ = gwe.aUI_(this.n);
                    if (aUI_ == null) {
                        aUI_ = nrf.cHR_(R.drawable._2131428707_res_0x7f0b0563);
                    }
                    this.g = this.k.addMarker(new MarkerOptions().position(this.f.get(i)).draggable(false).anchor(0.5f, 0.9f).icon(BitmapDescriptorFactory.fromBitmap(aUI_)).zIndex(14.0f));
                }
            }
            List<PolylineOptions> list2 = this.ag;
            if (list2 != null) {
                for (PolylineOptions polylineOptions : list2) {
                    this.ap.put(this.k.addPolyline(polylineOptions), polylineOptions);
                }
            }
            List<List<LatLng[]>> list3 = this.ab;
            if (list3 != null && koq.d(list3, i) && !this.ab.get(i).isEmpty()) {
                for (LatLng[] latLngArr : this.ab.get(i)) {
                    d(latLngArr[0], latLngArr[1]);
                }
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerLoadingListener(InterfaceMapCallback interfaceMapCallback) {
        this.w = interfaceMapCallback;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showOrHide(boolean z) {
        if (nrt.a(this.c)) {
            Object[] objArr = new Object[2];
            objArr[0] = "setMapType:";
            objArr[1] = z ? " google_night" : " google_night_hide";
            LogUtil.a("Track_GoogleMapModel", objArr);
            this.k.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.c, z ? R.raw._2131886210_res_0x7f120082 : R.raw._2131886211_res_0x7f120083));
            this.k.setMapType(1);
        } else {
            LogUtil.a("Track_GoogleMapModel", "setMapType: google_normal");
            this.k.setMapType(z ? 1 : 0);
        }
        LogUtil.a("Track_GoogleMapModel", "showOrHide isShow = ", Boolean.valueOf(z));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showSatelLiteState(boolean z, boolean z2, int i) {
        if (!z) {
            setMapShowType(2);
        } else if (z2) {
            setMapShowType(1);
        } else {
            setMapShowType(i);
        }
        LogUtil.a("Track_GoogleMapModel", "showSatelLiteState isShowMap = ", Boolean.valueOf(z), ",isShowTrackMapTypeSatellite=", Boolean.valueOf(z2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapShowType(int i) {
        if (this.v == i) {
            return;
        }
        this.v = i;
        if (this.k != null) {
            if (nrt.a(this.c)) {
                this.k.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.c, R.raw._2131886210_res_0x7f120082));
            } else {
                this.k.setMapStyle(null);
            }
            if (i == 0) {
                this.k.setMapType(1);
            } else if (i == 1) {
                this.k.setMapType(2);
            } else if (i == 2) {
                this.k.setMapType(0);
            }
            LogUtil.c("Track_GoogleMapModel", "mapType = ", Integer.valueOf(this.k.getMapType()));
            return;
        }
        LogUtil.h("Track_GoogleMapModel", "google map is null");
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraLatLngBounds(List<hjd> list) {
        SupportMapFragment supportMapFragment;
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "moveCameraLatLngBounds list is null");
            return;
        }
        if (this.c == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "moveCameraLatLngBounds context is null");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int size = list.size();
        Iterator<hjd> it = list.iterator();
        while (it.hasNext()) {
            builder.include(gwc.c(it.next()));
        }
        this.f13198a = CameraUpdateFactory.newLatLngBounds(builder.build(), (int) this.c.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.c.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d), 10);
        if (size > 1) {
            try {
                if (this.k != null && (supportMapFragment = this.ao) != null && supportMapFragment.getView() != null) {
                    View view = this.ao.getView();
                    int height = view.getHeight() / 2;
                    int width = view.getWidth() / 2;
                    if (10 < height && 10 < width) {
                        this.k.moveCamera(this.f13198a);
                        return;
                    }
                    LogUtil.b("Track_GoogleMapModel", "mapHeightHalf is ", Integer.valueOf(height), " mapWidthHalf is ", Integer.valueOf(width));
                    return;
                }
                LogUtil.b("Track_GoogleMapModel", "map is null in moveCameraLatLngBounds");
            } catch (Exception e) {
                ReleaseLogUtil.c("Track_GoogleMapModel", "moveCameraLatLngBounds:", LogAnonymous.b((Throwable) e));
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void getMapScreenShot(final Handler handler, MotionData motionData) {
        if (handler == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "getMapScreenShot handler is null");
            return;
        }
        if (this.k == null) {
            handler.sendEmptyMessageDelayed(1, 200L);
            return;
        }
        this.q = true;
        final Runnable runnable = new Runnable() { // from class: hki.13
            @Override // java.lang.Runnable
            public void run() {
                if (hki.this.q) {
                    LogUtil.a("Track_GoogleMapModel", "maps Bitmap fail to snapshot");
                    Message obtain = Message.obtain(handler);
                    obtain.obj = null;
                    obtain.what = 1;
                    obtain.sendToTarget();
                    hki.this.q = false;
                }
            }
        };
        handler.postDelayed(runnable, 1000L);
        this.k.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: hki.14
            @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
            public void onSnapshotReady(Bitmap bitmap) {
                if (hki.this.q) {
                    Message obtain = Message.obtain(handler);
                    obtain.obj = bitmap;
                    obtain.what = 1;
                    obtain.sendToTarget();
                    hki.this.q = false;
                    handler.removeCallbacks(runnable);
                }
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setAllGesturesEnabled(boolean z) {
        GoogleMap googleMap = this.k;
        if (googleMap != null) {
            googleMap.getUiSettings().setAllGesturesEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setZoomControlsEnabled(boolean z) {
        GoogleMap googleMap = this.k;
        if (googleMap != null) {
            googleMap.getUiSettings().setZoomControlsEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void pauseSportClear() {
        this.d.sendEmptyMessage(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        PolylineOptions polylineOptions = new PolylineOptions();
        this.af = polylineOptions;
        polylineOptions.color(this.as).width(15.0f).zIndex(10.0f).visible(true);
        this.ai = this.k.addPolyline(this.af);
        List<LatLng> list = this.aq;
        if (list != null && !list.isEmpty()) {
            this.aq.clear();
        }
        if (koq.c(this.y)) {
            this.y.clear();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreate(Bundle bundle, boolean z, boolean z2) {
        if (this.k == null) {
            ReleaseLogUtil.d("Track_GoogleMapModel", "onCreate Google Map is null");
            return;
        }
        LogUtil.a("Track_GoogleMapModel", "onCreate():");
        this.k.getUiSettings().setZoomControlsEnabled(false);
        this.k.getUiSettings().setMyLocationButtonEnabled(false);
        this.k.getUiSettings().setCompassEnabled(false);
        this.k.getUiSettings().setTiltGesturesEnabled(false);
        if (z) {
            Object systemService = this.c.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (!(systemService instanceof WindowManager)) {
                LogUtil.b("Track_GoogleMapModel", "not WindowManager");
                return;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
            this.k.setPadding(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 4, displayMetrics.widthPixels / 2, (displayMetrics.heightPixels * 3) / 4);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroy() {
        if (this.c != null) {
            File file = new File(this.c.getFilesDir(), "google_temp.png");
            if (file.exists()) {
                if (!file.delete()) {
                    LogUtil.a("Track_GoogleMapModel", "delete google temp error");
                } else {
                    LogUtil.a("Track_GoogleMapModel", "delete google temp success");
                }
            }
            this.c = null;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScreenOnOrForegrand(boolean z) {
        this.t = z;
        if (z) {
            forceDrawLine();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void screenShotToFile(final InterfaceSnapshotCallback interfaceSnapshotCallback) {
        SupportMapFragment supportMapFragment;
        synchronized (this) {
            if (interfaceSnapshotCallback == null) {
                LogUtil.a("Track_GoogleMapModel", "callback is null");
                return;
            }
            if (this.k != null && (supportMapFragment = this.ao) != null) {
                View view = supportMapFragment.getView();
                if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
                    return;
                }
                try {
                    this.k.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: hki.20
                        @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                        public void onSnapshotReady(Bitmap bitmap) {
                            interfaceSnapshotCallback.onSnapshotReady(bitmap);
                        }
                    });
                } catch (IllegalArgumentException e) {
                    LogUtil.b("Track_GoogleMapModel", LogAnonymous.b((Throwable) e));
                }
                return;
            }
            interfaceSnapshotCallback.onSnapshotReady(null);
        }
    }

    public List<PolylineOptions> d(LatLng latLng, LatLng latLng2) {
        Context context;
        String str;
        LatLng latLng3;
        LatLng latLng4 = latLng;
        ArrayList arrayList = new ArrayList();
        String str2 = "Track_GoogleMapModel";
        if (latLng2 == null || latLng4 == null || (context = this.c) == null) {
            LogUtil.h("Track_GoogleMapModel", "currentLatLng or lastLatLng or mContext is null");
            return arrayList;
        }
        int color = ContextCompat.getColor(context, R.color._2131298897_res_0x7f090a51);
        double d = (latLng2.latitude - latLng4.latitude) / 18.0d;
        double d2 = (latLng2.longitude - latLng4.longitude) / 18.0d;
        int i = (int) 18.0d;
        int i2 = 0;
        while (i2 < i) {
            LogUtil.a(str2, "zoomNew = 18.0");
            if (i2 > 0) {
                str = str2;
                latLng3 = new LatLng(latLng4.latitude + (d * 0.25d), latLng4.longitude + (0.25d * d2));
            } else {
                str = str2;
                latLng3 = latLng4;
            }
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.color(color).width(15).zIndex(10.0f).visible(gwe.d()).add(latLng3).add(new LatLng(latLng4.latitude + d, latLng4.longitude + d2));
            arrayList.add(polylineOptions);
            i2++;
            latLng4 = new LatLng(latLng4.latitude + d, latLng4.longitude + d2);
            str2 = str;
        }
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setIsStop(boolean z) {
        this.r = z;
    }

    private boolean e() {
        return this.r;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveToCenter() {
        CameraUpdate cameraUpdate = this.f13198a;
        if (cameraUpdate != null) {
            this.k.moveCamera(cameraUpdate);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void forceDrawLine() {
        if (this.y.size() <= 0) {
            return;
        }
        this.d.sendEmptyMessage(3);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showTrackMarkers(boolean z) {
        if (z) {
            a();
        } else {
            d();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public List<hjd> requestSimplePoints() {
        return this.aj;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom() {
        moveCameraByZoom(12.5f);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom(float f) {
        this.k.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByLatLng(hjd hjdVar) {
        this.k.moveCamera(CameraUpdateFactory.newLatLng(gwc.c(hjdVar)));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, Location location) {
        if (location == null) {
            ReleaseLogUtil.c("Track_GoogleMapModel", "getLastLocation location is null");
            return new double[0];
        }
        return new double[]{location.getLatitude(), location.getLongitude()};
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void updateSportMarker(hjd hjdVar, BitmapDrawable bitmapDrawable) {
        if (hjdVar == null || bitmapDrawable == null) {
            ReleaseLogUtil.c("Track_GoogleMapModel", "updateSportMarker hiHealthLatLng or bitmapDrawable is null");
            return;
        }
        Marker marker = this.g;
        if (marker != null) {
            marker.remove();
        }
        this.g = this.k.addMarker(new MarkerOptions().position(gwc.c(hjdVar)).draggable(false).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(bitmapDrawable.getBitmap())));
    }

    private void e(List<LatLng> list, Map<Integer, Float> map) {
        int i;
        LatLng latLng;
        Set<Map.Entry<Integer, Float>> entrySet = map.entrySet();
        int size = map.size();
        Integer[] numArr = new Integer[size];
        int c = c(entrySet, numArr, new Integer[size], 0);
        for (int i2 = 0; i2 < c; i2++) {
            try {
                int intValue = numArr[i2].intValue();
                int intValue2 = numArr[i2].intValue();
                int i3 = this.m;
                if (intValue2 > i3) {
                    latLng = list.get(i3);
                    i = this.m;
                } else {
                    int intValue3 = numArr[i2].intValue();
                    int i4 = this.ar;
                    if (intValue3 < i4) {
                        latLng = list.get(i4);
                        i = this.ar;
                    } else {
                        i = intValue;
                        latLng = list.get(intValue);
                    }
                }
                a aVar = new a();
                aVar.d = latLng;
                aVar.c = true;
                if (this.u.size() > 0) {
                    ArrayList<a> arrayList = this.u;
                    a aVar2 = arrayList.get(arrayList.size() - 1);
                    if (aVar2.d.equals(latLng)) {
                        if (i == this.m) {
                            aVar.c = false;
                        } else {
                            aVar2.c = false;
                        }
                    }
                }
                this.u.add(aVar);
            } catch (IndexOutOfBoundsException e) {
                LogUtil.h("Track_GoogleMapModel", "setUpKmLatLngList ", e.getMessage());
            }
        }
    }

    private int c(Set<Map.Entry<Integer, Float>> set, Integer[] numArr, Integer[] numArr2, int i) {
        Iterator<Map.Entry<Integer, Float>> it = set.iterator();
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            if (gvu.a(intValue)) {
                if (intValue > 100000) {
                    numArr[i] = Integer.valueOf((intValue % 100000) + 1);
                    numArr2[i] = Integer.valueOf((intValue / 100000) / 100);
                } else {
                    numArr[i] = Integer.valueOf(intValue + 1);
                    numArr2[i] = Integer.valueOf(i + 1);
                }
                i++;
            }
        }
        return i;
    }

    private void a() {
        if (this.c == null) {
            LogUtil.h("Track_GoogleMapModel", "mContext is null in setTrackMarkers");
            return;
        }
        int d = hji.d(this.ak, this.j);
        for (int i = d - 1; i < this.u.size(); i += d) {
            if (this.u.get(i).c) {
                this.z.add(this.k.addMarker(gwc.aUJ_(this.c.getResources(), this.u.get(i).d, UnitUtil.e(i + 1, 1, 0))));
            }
        }
        LogUtil.c("Track_GoogleMapModel", "mMarkersList.size = ", Integer.valueOf(this.z.size()));
    }

    private void d() {
        Iterator<Marker> it = this.z.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        this.z.clear();
    }

    static class a {
        private boolean c = true;
        private LatLng d;

        a() {
        }

        public String toString() {
            return "to show" + this.c;
        }
    }

    private GoogleMap.CancelableCallback a(final InterfaceMapCallback interfaceMapCallback) {
        if (interfaceMapCallback == null) {
            LogUtil.h("Track_GoogleMapModel", "transferCallback callback is null");
            return new GoogleMap.CancelableCallback() { // from class: hki.18
                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onCancel() {
                }

                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onFinish() {
                }
            };
        }
        return new GoogleMap.CancelableCallback() { // from class: hki.17
            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onFinish() {
                interfaceMapCallback.onFinish();
            }

            @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
            public void onCancel() {
                interfaceMapCallback.onCancel();
            }
        };
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLogoPadding(int i, int i2, int i3, int i4) {
        GoogleMap googleMap = this.k;
        if (googleMap != null) {
            googleMap.setPadding(i, i2, i3, i4 - nsn.c(BaseApplication.getContext(), 2.0f));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlh hlhVar, long j, final InterfaceMapCallback interfaceMapCallback) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in animateCamera");
            return;
        }
        CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(e(hlhVar));
        if (j <= 0) {
            this.k.moveCamera(newCameraPosition);
        } else {
            LogUtil.a("Track_GoogleMapModel", "animateCamera");
            this.k.animateCamera(newCameraPosition, (int) j, new GoogleMap.CancelableCallback() { // from class: hki.4
                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onFinish() {
                    if (interfaceMapCallback != null) {
                        LogUtil.a("Track_GoogleMapModel", "animateCamera onFinish");
                        interfaceMapCallback.onFinish();
                    }
                }

                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onCancel() {
                    if (interfaceMapCallback != null) {
                        LogUtil.a("Track_GoogleMapModel", "animateCamera onCancel");
                        interfaceMapCallback.onCancel();
                    }
                }
            });
        }
    }

    private CameraPosition e(hlh hlhVar) {
        if (hlhVar == null) {
            return null;
        }
        return new CameraPosition(gwc.c(hlhVar.d()), hlhVar.c(), hlhVar.b(), hlhVar.a());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void stopAnimation() {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "bounds is null in clear");
            return;
        }
        Context context = this.c;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: hki.5
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("Track_GoogleMapModel", "stopAnimation");
                    hki.this.k.stopAnimation();
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hld hldVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in drawLine");
            return;
        }
        if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
            return;
        }
        Polyline polyline = this.i;
        if (polyline == null || c(hldVar, polyline)) {
            PolylineOptions color = new PolylineOptions().add(gwc.c(hldVar.a()), gwc.c(hldVar.c())).width(hldVar.b() * 0.5f).color(hldVar.e());
            if (hldVar.h()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Dot());
                color.pattern(arrayList);
                this.i = this.k.addPolyline(color);
                return;
            }
            this.i = null;
            this.k.addPolyline(color);
            return;
        }
        List<LatLng> points = this.i.getPoints();
        points.add(gwc.c(hldVar.a()));
        points.add(gwc.c(hldVar.c()));
        this.i.setPoints(points);
    }

    private boolean c(hld hldVar, Polyline polyline) {
        return (hldVar.h() && koq.b(polyline.getPattern())) || !(hldVar.h() || koq.b(polyline.getPattern()));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String drawLines(hle hleVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in drawLine");
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
            arrayList.add(gwc.c(hleVar.a()));
            arrayList.add(gwc.c(hleVar.c()));
        } else {
            Iterator<hjd> it = hleVar.i().iterator();
            while (it.hasNext()) {
                arrayList.add(gwc.c(it.next()));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(hleVar.f())) {
            arrayList2.add(Integer.valueOf(hleVar.e()));
        } else {
            arrayList2.addAll(hleVar.f());
        }
        PolylineOptions visible = new PolylineOptions().addAll(arrayList).width(hleVar.b() * 0.9f).color(((Integer) arrayList2.get(0)).intValue()).zIndex(hleVar.d()).visible(hleVar.g());
        if (hleVar.h()) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new Dot());
            visible.pattern(arrayList3);
        }
        Polyline addPolyline = this.k.addPolyline(visible);
        String id = addPolyline.getId();
        this.ah.put(id, addPolyline);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.b("Track_GoogleMapModel", "id is null in setLineVisible");
            return;
        }
        Polyline polyline = this.ah.get(str);
        if (polyline == null) {
            return;
        }
        polyline.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public GrowAnimationBuilder getGrowAnimation() {
        return new hks();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void clear() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            LogUtil.b("Track_GoogleMapModel", "clear HuaweiMap is null");
            return;
        }
        googleMap.clear();
        this.x = new SparseArray<>();
        this.o = new ArrayList();
        this.i = null;
        this.b.clear();
        this.ap.clear();
        this.aa.clear();
        this.ah.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveMarker(final int i, final hjd hjdVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in moveMarker");
        } else {
            if (this.x.get(i, null) == null) {
                return;
            }
            Context context = this.c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: hki.3
                    @Override // java.lang.Runnable
                    public void run() {
                        ((Marker) hki.this.x.get(i)).setPosition(gwc.c(hjdVar));
                    }
                });
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int addMarker(final hlg hlgVar, final GrowAnimationBuilder growAnimationBuilder) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in addMarker");
            return -1;
        }
        if (hlgVar == null || hlgVar.bhO_() == null) {
            return -1;
        }
        if (this.o == null) {
            this.o = new ArrayList();
        }
        final int size = this.o.size();
        this.o.add(Integer.valueOf(size));
        Context context = this.c;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: hki.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        Marker addMarker = hki.this.k.addMarker(new MarkerOptions().flat(hlgVar.g()).anchor(((Float) hlgVar.bhN_().first).floatValue(), ((Float) hlgVar.bhN_().second).floatValue()).zIndex(hlgVar.d()).draggable(hlgVar.h()).icon(BitmapDescriptorFactory.fromBitmap(hlgVar.bhO_())).position(gwc.c(hlgVar.e())).visible(hlgVar.f()));
                        GrowAnimationBuilder growAnimationBuilder2 = growAnimationBuilder;
                        if (growAnimationBuilder2 instanceof hks) {
                            ((hks) growAnimationBuilder2).b(addMarker);
                            growAnimationBuilder.displayAnimation();
                        }
                        if (hki.this.x == null) {
                            hki.this.x = new SparseArray();
                        }
                        hki.this.x.put(size, addMarker);
                    }
                }
            });
        }
        return size;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in restoreMarker");
            return;
        }
        if (this.x == null || i < 0 || i >= this.o.size()) {
            return;
        }
        Marker marker = this.x.get(i);
        if (growAnimationBuilder == null) {
            marker.setVisible(true);
        }
        if (growAnimationBuilder instanceof hks) {
            ((hks) growAnimationBuilder).b(marker);
            growAnimationBuilder.displayAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void hideMarker(final int i, final GrowAnimationBuilder growAnimationBuilder) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in deleteMarker");
        } else {
            if (this.x.get(i, null) == null) {
                return;
            }
            Context context = this.c;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: hki.8
                    @Override // java.lang.Runnable
                    public void run() {
                        Marker marker = (Marker) hki.this.x.get(i);
                        if (marker != null) {
                            marker.setVisible(false);
                        }
                        GrowAnimationBuilder growAnimationBuilder2 = growAnimationBuilder;
                        if (growAnimationBuilder2 instanceof hks) {
                            ((hks) growAnimationBuilder2).b(marker);
                            growAnimationBuilder.disappearAnimation();
                        }
                    }
                });
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIcon(int i, Bitmap bitmap) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in setMarkerIcon");
            return;
        }
        List<Integer> list = this.o;
        if (list == null || i < 0 || i >= list.size()) {
            LogUtil.b("Track_GoogleMapModel", "id is error in setMarkerIcon");
            return;
        }
        Marker marker = this.x.get(i);
        if (marker == null) {
            return;
        }
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPreviewStatus(hla hlaVar, int i, int i2, int i3, int i4) {
        if (hlaVar == null) {
            LogUtil.b("Track_GoogleMapModel", "bounds is null in setPreviewStatus");
            return;
        }
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in setPreviewStatus");
        } else if (this.c == null) {
            LogUtil.h("Track_GoogleMapModel", "mContext is null in setPreviewStatus");
        } else {
            this.k.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().include(gwc.c(hlaVar.b())).include(gwc.c(hlaVar.c())).build(), (int) this.c.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.c.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.65d), 0));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hlh getMapStatus() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in getMapStatus");
            return new hlh();
        }
        if (googleMap.getCameraPosition() == null) {
            LogUtil.b("Track_GoogleMapModel", "mGoogleMap.getCameraPosition() is null in getMapStatus");
            return new hlh();
        }
        hlh d = d(this.k.getCameraPosition());
        d.c(c(this.k.getCameraPosition().zoom));
        return d;
    }

    private hlh d(CameraPosition cameraPosition) {
        if (cameraPosition == null) {
            return null;
        }
        hlh hlhVar = new hlh();
        hlhVar.d(cameraPosition.bearing).c(gwc.e(cameraPosition.target)).b(cameraPosition.tilt).e(cameraPosition.zoom);
        return hlhVar;
    }

    private float c(float f) {
        return (float) (Math.exp(f * (-0.688d)) * 49671.0d);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMaxZoomLevel() {
        return this.k.getMaxZoomLevel();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapLoadedCallback(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in setMapLoadedCallback");
        } else {
            if (interfaceMapLoadedCallback == null) {
                return;
            }
            this.k.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() { // from class: hki.6
                @Override // com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    interfaceMapLoadedCallback.onMapLoaded();
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setCameraChangeCallback(final InterfaceMapStatusChangeCallback interfaceMapStatusChangeCallback) {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in setCameraChangeCallback");
        } else {
            if (interfaceMapStatusChangeCallback == null) {
                return;
            }
            googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() { // from class: hki.7
                @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
                public void onCameraMove() {
                    interfaceMapStatusChangeCallback.onMapStatusChange(hki.this.getMapStatus());
                }
            });
            this.k.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() { // from class: hki.10
                @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
                public void onCameraIdle() {
                    interfaceMapStatusChangeCallback.onMapStatusChangeFinish(hki.this.getMapStatus());
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableAllGestures() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in disableAllGestures");
        } else {
            googleMap.getUiSettings().setAllGesturesEnabled(false);
            this.k.getUiSettings().setZoomControlsEnabled(false);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showPureMap() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in showPureMap");
        } else {
            googleMap.getUiSettings().setCompassEnabled(false);
            this.k.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeMapType(MapTypeDescription mapTypeDescription, hak hakVar) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in changeMapType");
            return;
        }
        if (mapTypeDescription == null) {
            return;
        }
        if (mapTypeDescription.c() == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.k.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.c, R.raw._2131886212_res_0x7f120084));
        } else if (mapTypeDescription.c() == MapTypeDescription.MapType.MAP_TYPE_CUSTOM) {
            d(hakVar);
        } else {
            this.k.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.c, R.raw._2131886184_res_0x7f120068));
        }
    }

    private void d(hak hakVar) {
        if (hakVar == null) {
            LogUtil.h("Track_GoogleMapModel", "initCustomMapStyle customMapInformation == null");
        } else {
            this.k.setMapStyle(new MapStyleOptions(Utils.c(hakVar.i())));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapZoom() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            return 0.0f;
        }
        return googleMap.getCameraPosition().zoom;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapTilt() {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            return 0.0f;
        }
        return googleMap.getCameraPosition().tilt;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public Point getScreenLocation(hjd hjdVar) {
        GoogleMap googleMap = this.k;
        if (googleMap == null || hjdVar == null) {
            LogUtil.b("Track_GoogleMapModel", "googleMap is null or latlng is null");
            return new Point();
        }
        try {
            return googleMap.getProjection().toScreenLocation(gwc.c(hjdVar));
        } catch (RuntimeRemoteException e) {
            LogUtil.b("Track_GoogleMapModel", LogAnonymous.b((Throwable) e));
            return new Point();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd getLatLngByScreenPoint(Point point) {
        GoogleMap googleMap = this.k;
        if (googleMap == null || point == null) {
            LogUtil.b("Track_GoogleMapModel", "googleMap is null or latlng is null");
            return new hjd(0.0d, 0.0d);
        }
        try {
            return gwc.e(googleMap.getProjection().fromScreenLocation(point));
        } catch (RuntimeRemoteException e) {
            LogUtil.b("Track_GoogleMapModel", LogAnonymous.b((Throwable) e));
            return new hjd(0.0d, 0.0d);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableMarkerClick() {
        this.k.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: hki.9
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addMarkerClickListener(final InterfaceMapMarkerClickCallback interfaceMapMarkerClickCallback) {
        GoogleMap googleMap = this.k;
        if (googleMap == null) {
            return;
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: hki.15
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                int i = 0;
                if (interfaceMapMarkerClickCallback == null || marker == null) {
                    LogUtil.h("Track_GoogleMapModel", "interfaceMapMarkerClickCallback or marker is null");
                    return false;
                }
                while (true) {
                    if (i >= hki.this.x.size()) {
                        i = -1;
                        break;
                    }
                    if (marker.equals(hki.this.x.valueAt(i))) {
                        break;
                    }
                    i++;
                }
                LogUtil.a("Track_GoogleMapModel", "click Marker count:", Integer.valueOf(i));
                interfaceMapMarkerClickCallback.onMarkerClick(i);
                return true;
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addHiHealthMarkers(List<hjg> list) {
        ArrayList<MarkerOptions> d = gwe.d(list);
        if (koq.b(d)) {
            return;
        }
        d.forEach(new Consumer() { // from class: hko
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hki.this.b((MarkerOptions) obj);
            }
        });
    }

    /* synthetic */ void b(MarkerOptions markerOptions) {
        this.k.addMarker(markerOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlc hlcVar, int i) {
        CameraUpdate newLatLngBounds;
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in animateCameraBounds");
            return;
        }
        if (hlcVar == null || koq.b(hlcVar.e())) {
            LogUtil.b("Track_GoogleMapModel", "param is null in animateCameraByBounds");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<hjd> it = hlcVar.e().iterator();
        while (it.hasNext()) {
            builder.include(gwc.c(it.next()));
        }
        LatLngBounds build = builder.build();
        int d = hlcVar.d();
        int a2 = hlcVar.a();
        if (d == 0 || a2 == 0) {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, hlcVar.c());
        } else {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, d, a2, hlcVar.c());
        }
        this.k.moveCamera(newLatLngBounds);
        float b = hlcVar.b();
        if (Float.compare(b, 0.0f) != 0) {
            b(b, build.getCenter(), i);
        }
    }

    private void b(float f, LatLng latLng, int i) {
        final CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(latLng).bearing(f).zoom(getMapZoom()).build());
        if (i <= 0) {
            this.k.moveCamera(newCameraPosition);
        } else {
            this.k.animateCamera(newCameraPosition, i, new GoogleMap.CancelableCallback() { // from class: hki.11
                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onFinish() {
                }

                @Override // com.google.android.gms.maps.GoogleMap.CancelableCallback
                public void onCancel() {
                    hki.this.k.moveCamera(newCameraPosition);
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void scrollBy(float f, float f2) {
        if (this.k == null) {
            LogUtil.b("Track_GoogleMapModel", "map is null in scrollBy");
        } else {
            this.k.moveCamera(CameraUpdateFactory.scrollBy(f, f2));
        }
    }
}
