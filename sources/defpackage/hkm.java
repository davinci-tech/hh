package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.huawei.haf.handler.BaseHandler;
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
import com.huawei.hms.maps.CameraUpdate;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.Projection;
import com.huawei.hms.maps.model.BitmapDescriptor;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.CameraPosition;
import com.huawei.hms.maps.model.Dot;
import com.huawei.hms.maps.model.GroundOverlay;
import com.huawei.hms.maps.model.GroundOverlayOptions;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.model.MapStyleOptions;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;
import com.huawei.hms.maps.model.Polygon;
import com.huawei.hms.maps.model.PolygonOptions;
import com.huawei.hms.maps.model.Polyline;
import com.huawei.hms.maps.model.PolylineOptions;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class hkm implements InterfaceHiMap {

    /* renamed from: a, reason: collision with root package name */
    private float f13212a;
    private MapView aa;
    private Polyline ag;
    private Vector<Marker> ai;
    private List<hjd> aj;
    private Marker as;
    private Handler d;
    private String e;
    private Context g;
    private CameraPosition i;
    private Marker j;
    private Polyline k;
    private Marker n;
    private boolean p;
    private HuaweiMap r;
    private String t;
    private InterfaceMapCallback z;
    private boolean w = true;
    private boolean u = true;
    private boolean y = true;
    private List<LatLng> ax = new ArrayList(16);
    private List<hjd> am = new ArrayList(16);
    private List<LatLng> m = new ArrayList(16);
    private List<Integer> ao = new ArrayList(16);
    private List<LatLng> ar = new ArrayList(16);
    private ArrayList<a> x = new ArrayList<>(16);
    private ArrayList<Marker> ae = new ArrayList<>(16);
    private ArrayList<MarkerOptions> ad = new ArrayList<>(16);
    private List<hjg> l = new ArrayList(16);
    private int o = 0;
    private int ap = 0;
    private int af = 0;
    private CameraUpdate h = null;
    private List<PolylineOptions> al = new ArrayList();
    private int ab = -1;
    private volatile boolean q = false;
    private volatile boolean s = false;
    private int f = 0;
    private Map<Integer, Boolean> b = new Hashtable();
    private Observable aq = new Observable();
    private Vector<Polyline> c = new Vector<>();
    private Polygon an = null;
    private boolean ac = false;
    private final Map<String, GroundOverlay> ah = new ConcurrentHashMap();
    private final Map<Polyline, PolylineOptions> av = new ConcurrentHashMap();
    private final Map<String, Polyline> ak = new ConcurrentHashMap();
    private int au = gwh.q;
    private List<LatLng> v = new ArrayList(16);

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
        return 3;
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
        return true;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCameraChangeListener(Handler handler) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreatePurely(Bundle bundle) {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded() {
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onSaveInstanceState(Bundle bundle) {
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

    public hkm(Context context, HuaweiMap huaweiMap, MapView mapView) {
        this.g = context;
        this.r = huaweiMap;
        PolylineOptions polylineOptions = new PolylineOptions();
        this.aa = mapView;
        polylineOptions.color(this.au).width(5.0f).zIndex(10.0f).add(new LatLng(0.0d, 0.0d), new LatLng(0.0d, 0.0d)).visible(true);
        this.ag = this.r.addPolyline(polylineOptions);
        this.r.getUiSettings().setZoomGesturesEnabled(true);
        this.d = new b(this);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hjd hjdVar, long j, InterfaceMapCallback interfaceMapCallback) {
        float f;
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "animateCamera hiHealthLatLng is null, please check");
            return;
        }
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            ReleaseLogUtil.d("Track_HmsMap", "animateCamera mHuaweiMap is null, please check");
            return;
        }
        if (this.w) {
            this.w = false;
            f = 16.5f;
        } else {
            if (huaweiMap.getCameraPosition() == null) {
                ReleaseLogUtil.d("Track_HmsMap", "getCameraPosition is null, please check");
                return;
            }
            f = this.r.getCameraPosition().zoom;
        }
        this.r.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(gwe.a(hjdVar)).zoom(f).bearing(0.0f).tilt(25.0f).build()));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addLinePoint(hld hldVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in drawLine");
            return;
        }
        if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
            return;
        }
        if (this.c.size() <= 0) {
            this.c.add(this.r.addPolyline(new PolylineOptions().add(gwe.a(hldVar.a())).width(hldVar.b() * 0.3f).color(hldVar.e())));
            return;
        }
        int size = this.c.size() - 1;
        List<LatLng> points = this.c.get(size).getPoints();
        if (this.c.get(size) != null) {
            if (!e(hldVar, this.c.get(r0.size() - 1)) && !koq.b(points) && points.size() <= 242) {
                points.add(gwe.a(hldVar.a()));
                points.add(gwe.a(hldVar.c()));
                this.c.get(r5.size() - 1).setPoints(points);
                return;
            }
        }
        PolylineOptions color = new PolylineOptions().add(gwe.a(hldVar.a())).width(hldVar.b() * 0.3f).color(hldVar.e());
        if (hldVar.h()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Dot());
            color.pattern(arrayList);
        } else {
            color.add(gwe.a(hldVar.c()));
        }
        this.c.add(this.r.addPolyline(color));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void removeLinePoint() {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in drawLine");
            return;
        }
        if (this.c.size() <= 0) {
            LogUtil.b("Track_HmsMap", "mAheadMoveLines is empty");
            return;
        }
        int size = this.c.size() - 1;
        if (koq.b(this.c, size)) {
            LogUtil.b("Track_HmsMap", "removeLinePoint mAheadMoveLines isOutOfBounds ", Integer.valueOf(size));
            return;
        }
        List<LatLng> points = this.c.get(size).getPoints();
        if (points != null) {
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                points.remove(points.size() - 1);
            }
            if (points.size() > 0) {
                this.c.get(size).setPoints(points);
                return;
            } else {
                this.c.get(size).remove();
                this.c.remove(size);
                return;
            }
        }
        this.c.get(size).remove();
        this.c.remove(size);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(List<hiy> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_HmsMap", "drawLine listBasePoint is empty, please check");
            return;
        }
        hjd e = gwf.e(list.get(list.size() - 1));
        this.v.add(gwe.a(e));
        if (this.u) {
            if (this.v.size() >= 2) {
                this.ag.setPoints(this.v);
            }
            if (list.size() > 0) {
                addEndMarker(e);
                animateCamera(e, 1000L, (InterfaceMapCallback) null);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineColor(int i, boolean z) {
        Polyline polyline;
        this.au = i;
        if (!z || (polyline = this.ag) == null) {
            return;
        }
        polyline.setColor(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_HmsMap", "drawLine lastLatLng or currentLatLng is null");
            return;
        }
        addEndMarker(hjdVar2);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.au).width(5.0f).zIndex(10.0f).visible(true).add(gwe.a(hjdVar), gwe.a(hjdVar2));
        this.r.addPolyline(polylineOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawInterrupt(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_HmsMap", "drawInterrupt lastLatLng or currentLatLng is null");
            return;
        }
        addEndMarker(hjdVar);
        Iterator<PolylineOptions> it = e(gwe.a(hjdVar), gwe.a(hjdVar2)).iterator();
        while (it.hasNext()) {
            this.r.addPolyline(it.next());
        }
    }

    public List<PolylineOptions> e(LatLng latLng, LatLng latLng2) {
        LatLng latLng3 = latLng;
        ArrayList arrayList = new ArrayList();
        String str = "Track_HmsMap";
        if (latLng2 == null || latLng3 == null) {
            LogUtil.a("Track_HmsMap", "lastLatLng or currentLatLng is null");
            return arrayList;
        }
        double d = 18.0d;
        double d2 = (latLng2.latitude - latLng3.latitude) / 18.0d;
        double d3 = (latLng2.longitude - latLng3.longitude) / 18.0d;
        int i = (int) 18.0d;
        int i2 = 0;
        while (i2 < i) {
            LogUtil.a(str, "zoomNew = ", Double.valueOf(d));
            LatLng latLng4 = i2 > 0 ? new LatLng(latLng3.latitude + (d2 * 0.25d), latLng3.longitude + (0.25d * d3)) : latLng3;
            PolylineOptions polylineOptions = new PolylineOptions();
            double d4 = d2;
            polylineOptions.color(this.g.getResources().getColor(R.color._2131298897_res_0x7f090a51)).width(5).zIndex(10.0f).visible(gwe.d()).add(latLng4).add(new LatLng(latLng3.latitude + d2, latLng3.longitude + d3));
            arrayList.add(polylineOptions);
            i2++;
            latLng3 = new LatLng(latLng3.latitude + d4, latLng3.longitude + d3);
            str = str;
            d2 = d4;
            d = 18.0d;
        }
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addStartMarker(hjd hjdVar, int i) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "addStartMarker hiHealthLatLng is null");
        } else {
            gwe.c(this.r, hjdVar, i);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIconPath(String str, String str2) {
        this.t = str;
        this.e = str2;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String addOverlay(hlj hljVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "mHuaweiMap  is null in addGroundOverlay");
            return null;
        }
        if (hljVar == null) {
            LogUtil.b("Track_HmsMap", "overlayDesc is null in addGroundOverlay");
            return null;
        }
        LatLng a2 = gwe.a(hljVar.b());
        GroundOverlay addGroundOverlay = this.r.addGroundOverlay(new GroundOverlayOptions().position(a2, (float) (hljVar.c() * (1.0d / Math.cos(Math.toRadians(a2.latitude))))).image(BitmapDescriptorFactory.fromBitmap(hljVar.bhR_())).bearing(hljVar.d()).zIndex(hljVar.e()).visible(hljVar.h()));
        if (addGroundOverlay == null) {
            LogUtil.b("Track_HmsMap", "add GroundOverlay fail");
            return null;
        }
        String id = addGroundOverlay.getId();
        this.ah.put(id, addGroundOverlay);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOverlayVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.b("Track_HmsMap", "id is null in setOverlayVisible");
            return;
        }
        GroundOverlay groundOverlay = this.ah.get(str);
        if (groundOverlay == null) {
            return;
        }
        groundOverlay.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar, boolean z) {
        if (z) {
            this.j = null;
        }
        addEndMarker(hjdVar);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addEndMarker(hjd hjdVar) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "addEndMarker latLng == null");
            return;
        }
        Marker marker = this.j;
        if (marker == null) {
            Marker a2 = gwe.a(this.r, hjdVar, this.e);
            this.n = a2;
            if (this.y) {
                if (a2 != null) {
                    a2.setVisible(false);
                    this.n.hideInfoWindow();
                }
                this.j = this.r.addMarker(new MarkerOptions().position(gwe.a(hjdVar)).draggable(false));
                this.j.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.g.getResources(), R.drawable._2131428707_res_0x7f0b0563)));
                this.j.setMarkerAnchor(0.5f, 0.9f);
                this.j.hideInfoWindow();
                return;
            }
            this.j = gwe.e(this.r, hjdVar, this.t);
            c(1);
            return;
        }
        marker.setPosition(gwe.a(hjdVar));
        this.j.setIcon(gwe.b(this.t));
        this.j.hideInfoWindow();
        Marker marker2 = this.n;
        if (marker2 != null) {
            marker2.setPosition(gwe.a(hjdVar));
            this.n.hideInfoWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.y) {
            return;
        }
        Handler handler = this.d;
        if (handler == null) {
            ReleaseLogUtil.c("Track_HmsMap", "mAddCircleHandler is null in updateMarkerBgMsg");
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
            InterfaceMapCallback interfaceMapCallback = this.z;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
                return;
            }
            return;
        }
        this.aj = new ArrayList();
        this.af = list.size();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        int i = 0;
        this.ac = false;
        if (this.af > 0) {
            this.ap = list.get(0).g();
            this.o = list.get(0).a().size();
        }
        for (hkx hkxVar : list) {
            this.ao.add(Integer.valueOf(hkxVar.g()));
            c(hkxVar, builder);
            c(hkxVar);
            b(i, hkxVar);
            i++;
            this.am.addAll(this.aj);
            this.l = hkxVar.c();
            this.ad = gwe.c(hkxVar.c());
        }
        if (this.ac) {
            c(builder);
        } else {
            LogUtil.a("Track_HmsMap", "no points in the bounds");
        }
        d();
        InterfaceMapCallback interfaceMapCallback2 = this.z;
        if (interfaceMapCallback2 != null) {
            interfaceMapCallback2.onFinish();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeTrackColor(int i) {
        for (Map.Entry<Polyline, PolylineOptions> entry : this.av.entrySet()) {
            Polyline key = entry.getKey();
            PolylineOptions value = entry.getValue();
            if (value.getColorValues() == null) {
                int color = value.getColor();
                key.setColor(i);
                value.color(color);
            } else {
                List<Integer> colorValues = value.getColorValues();
                key.setColorValues(Collections.nCopies(2, Integer.valueOf(i)));
                value.colorValues(colorValues);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void resetTrackColor() {
        for (Map.Entry<Polyline, PolylineOptions> entry : this.av.entrySet()) {
            Polyline key = entry.getKey();
            PolylineOptions value = entry.getValue();
            if (value.getColorValues() == null) {
                key.setColor(value.getColor());
            } else {
                key.setColorValues(value.getColorValues());
            }
        }
    }

    private void d() {
        if (this.r == null || koq.b(this.ad)) {
            return;
        }
        for (int i = 1; i < this.ad.size() - 1; i++) {
            MarkerOptions markerOptions = this.ad.get(i);
            if (markerOptions != null) {
                this.r.addMarker(markerOptions);
            }
        }
    }

    private void c(LatLngBounds.Builder builder) {
        LatLngBounds build = builder.build();
        LatLng latLng = build.southwest;
        LatLng latLng2 = build.northeast;
        LatLng latLng3 = new LatLng(latLng2.latitude - ((latLng2.latitude - latLng.latitude) * 1.4d), latLng.longitude);
        LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
        builder2.include(build.northeast);
        builder2.include(latLng3);
        CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder2.build(), (int) this.g.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.g.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d), 10);
        this.h = newLatLngBounds;
        this.r.moveCamera(newLatLngBounds);
        this.i = this.r.getCameraPosition();
    }

    private void b(int i, hkx hkxVar) {
        List<hjd> a2 = hkxVar.a();
        if (koq.b(a2)) {
            return;
        }
        for (hjd hjdVar : a2) {
            a aVar = new a();
            aVar.c = gwe.a(hjdVar);
            aVar.d = true;
            if (!koq.b(this.x)) {
                c(i, aVar);
            }
            this.x.add(aVar);
        }
    }

    private void c(int i, a aVar) {
        a aVar2 = this.x.get(r0.size() - 1);
        if (aVar2.equals(aVar)) {
            if (this.m.size() <= i || !aVar.c.equals(this.m.get(i))) {
                aVar2.d = false;
            } else {
                aVar.d = false;
            }
        }
    }

    private void c(hkx hkxVar) {
        List<hjd> d = hkxVar.d();
        if (!koq.b(d)) {
            Iterator<hjd> it = d.iterator();
            while (it.hasNext()) {
                this.ar.add(gwe.a(it.next()));
            }
        }
        List<hjd> b2 = hkxVar.b();
        if (koq.b(b2)) {
            return;
        }
        Iterator<hjd> it2 = b2.iterator();
        while (it2.hasNext()) {
            this.m.add(gwe.a(it2.next()));
        }
    }

    private void c(hkx hkxVar, LatLngBounds.Builder builder) {
        List<hjf> e = hkxVar.e();
        if (koq.b(e)) {
            LogUtil.a("Track_HmsMap", "lineList is empty");
            InterfaceMapCallback interfaceMapCallback = this.z;
            if (interfaceMapCallback != null) {
                interfaceMapCallback.onCancel();
                return;
            }
            return;
        }
        for (hjf hjfVar : e) {
            ArrayList arrayList = new ArrayList();
            int e2 = hjfVar.e();
            for (hjd hjdVar : hjfVar.d()) {
                LatLng a2 = gwe.a(hjdVar);
                arrayList.add(a2);
                builder.include(a2);
                this.ac = true;
                if (e2 != 2) {
                    this.aj.add(hjdVar);
                }
            }
            if (e2 == 2) {
                this.al.addAll(e((LatLng) arrayList.get(0), (LatLng) arrayList.get(arrayList.size() - 1)));
            } else {
                PolylineOptions polylineOptions = new PolylineOptions();
                if (!CommonUtil.bv()) {
                    polylineOptions.width(5.0f).zIndex(10.0f).visible(true).addAll(arrayList);
                    if (e2 == 0) {
                        polylineOptions.colorValues(hjfVar.a());
                    } else {
                        polylineOptions.color(hjfVar.a().size() > 0 ? hjfVar.a().get(0).intValue() : gwh.w);
                    }
                } else {
                    int i = gwh.w;
                    if ((kxb.c(hkxVar.g()) || hkxVar.g() == 222) && hjfVar.e() == 1 && !koq.b(hjfVar.a())) {
                        i = hjfVar.a().get(0).intValue();
                    }
                    polylineOptions.color(i).width(5.0f).zIndex(10.0f).visible(true).addAll(arrayList);
                }
                this.al.add(polylineOptions);
            }
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(List<MotionData> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_HmsMap", "onMapLoaded data is null");
            return;
        }
        this.ap = list.get(0).getSportType();
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (MotionData motionData : list) {
            ArrayList arrayList3 = new ArrayList(16);
            Map<Long, double[]> lbsDataMap = motionData.getLbsDataMap();
            if (lbsDataMap == null || lbsDataMap.isEmpty()) {
                return;
            }
            c(arrayList, arrayList2, arrayList3, lbsDataMap);
            LogUtil.a("Track_HmsMap", "list SIZE:", Integer.valueOf(arrayList2.size()));
            if (arrayList2.size() < 3 && this.y) {
                return;
            }
            c(arrayList3);
            a(arrayList3, motionData.getSportType());
            b(arrayList3);
        }
        animateCamera(e(arrayList).get(r9.size() - 1), 0L, (InterfaceMapCallback) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onMapLoaded(hjd hjdVar, float f) {
        CommonUtil.a("Track_HmsMap", "into hms map");
        if (hjdVar == null) {
            LogUtil.a("Track_HmsMap", "onMapLoaded, hiHealthLatLng is null");
            return;
        }
        this.r.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(hjdVar.b, hjdVar.d)));
        this.r.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setOnMapLoadedListener(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap != null) {
            huaweiMap.setOnMapLoadedCallback(new HuaweiMap.OnMapLoadedCallback() { // from class: hkm.3
                @Override // com.huawei.hms.maps.HuaweiMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    InterfaceMapLoadedCallback interfaceMapLoadedCallback2 = interfaceMapLoadedCallback;
                    if (interfaceMapLoadedCallback2 != null) {
                        interfaceMapLoadedCallback2.onMapLoaded();
                    }
                }
            });
        }
    }

    private List<hjd> e(List<LatLng> list) {
        ArrayList arrayList = new ArrayList(16);
        for (LatLng latLng : list) {
            arrayList.add(new hjd(latLng.latitude, latLng.longitude));
        }
        return arrayList;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraLatLngBounds(List<hjd> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("Track_HmsMap", "moveCameraLatLngBounds list is null");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<hjd> it = list.iterator();
        while (it.hasNext()) {
            builder.include(gwe.a(it.next()));
        }
        CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), (int) this.g.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.g.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.75d), 10);
        this.h = newLatLngBounds;
        this.r.moveCamera(newLatLngBounds);
        this.i = this.r.getCameraPosition();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void getMapScreenShot(final Handler handler, MotionData motionData) {
        LogUtil.a("Track_HmsMap", "getMapScreenShot");
        if (handler == null || this.aa == null) {
            ReleaseLogUtil.c("Track_HmsMap", "getMapScreenShot handler or mMapView is null return");
        } else {
            this.r.snapshot(new HuaweiMap.SnapshotReadyCallback() { // from class: hkm.1
                @Override // com.huawei.hms.maps.HuaweiMap.SnapshotReadyCallback
                public void onSnapshotReady(Bitmap bitmap) {
                    LogUtil.a("Track_HmsMap", "snapshot in app is :", bitmap);
                    if (bitmap != null) {
                        Message obtain = Message.obtain(handler);
                        obtain.obj = bitmap;
                        obtain.what = 1;
                        obtain.sendToTarget();
                        LogUtil.c("Track_HmsMap", "snapshot end");
                    }
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setAllGesturesEnabled(boolean z) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap != null) {
            huaweiMap.getUiSettings().setAllGesturesEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setZoomControlsEnabled(boolean z) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap != null) {
            huaweiMap.getUiSettings().setZoomControlsEnabled(z);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void pauseSportClear() {
        this.d.sendEmptyMessage(2);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onCreate(Bundle bundle, boolean z, boolean z2) {
        if (this.r == null) {
            LogUtil.h("Track_HmsMap", "onCreate Hms is null");
            return;
        }
        LogUtil.a("Track_HmsMap", "onCreate():");
        a();
        if (z) {
            Object systemService = this.g.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (!(systemService instanceof WindowManager)) {
                LogUtil.b("Track_HmsMap", "not WindowManager");
                return;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
            this.r.setPadding(displayMetrics.widthPixels / 2, displayMetrics.heightPixels / 4, displayMetrics.widthPixels / 2, (displayMetrics.heightPixels * 3) / 4);
        }
    }

    private void a() {
        this.r.getUiSettings().setZoomControlsEnabled(false);
        this.r.getUiSettings().setMyLocationButtonEnabled(false);
        this.r.getUiSettings().setCompassEnabled(false);
        this.r.getUiSettings().setTiltGesturesEnabled(false);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStart() {
        MapView mapView = this.aa;
        if (mapView != null) {
            mapView.onStart();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResume() {
        MapView mapView = this.aa;
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPause() {
        MapView mapView = this.aa;
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onStop() {
        MapView mapView = this.aa;
        if (mapView != null) {
            mapView.onStop();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroy() {
        Handler handler = this.d;
        if (handler != null) {
            handler.removeMessages(0);
            this.d.removeMessages(1);
            this.d = null;
        }
        Marker marker = this.j;
        if (marker != null) {
            marker.remove();
            this.j = null;
        }
        Marker marker2 = this.n;
        if (marker2 != null) {
            marker2.remove();
            this.n = null;
        }
        Polyline polyline = this.ag;
        if (polyline != null) {
            polyline.remove();
            this.ag = null;
        }
        MapView mapView = this.aa;
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapShowType(int i) {
        if (this.ab == i) {
            return;
        }
        this.ab = i;
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.c("Track_HmsMap", "tomtomap is null");
            return;
        }
        if (i == 0) {
            huaweiMap.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886189_res_0x7f12006d));
        } else if (i == 2) {
            huaweiMap.setMapType(0);
        } else if (i == 3) {
            huaweiMap.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886213_res_0x7f120085));
        } else if (i == 4) {
            huaweiMap.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886190_res_0x7f12006e));
        } else if (i == 5) {
            huaweiMap.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886191_res_0x7f12006f));
        } else {
            huaweiMap.setMapType(1);
        }
        a();
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
        LogUtil.a("Track_HmsMap", "showSatelLiteState isShowMap = ", Boolean.valueOf(z), ",isShowTrackMapTypeSatellite=", Boolean.valueOf(z2));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showOrHide(boolean z) {
        Polygon polygon = this.an;
        if (polygon != null) {
            polygon.remove();
        }
        if (this.r == null) {
            ReleaseLogUtil.c("Track_HmsMap", "mHuaweiMap is null return in showOrHide");
            return;
        }
        if (z && nrt.a(this.g)) {
            setMapShowType(3);
        } else if (z) {
            setMapShowType(0);
        } else if (nrt.a(this.g)) {
            setMapShowType(2);
            this.an = this.r.addPolygon(new PolygonOptions().addAll(c()).zIndex(9.0f).fillColor(this.g.getResources().getColor(R.color._2131298016_res_0x7f0906e0)).strokeColor(0).strokeWidth(1.0f));
        } else {
            setMapShowType(2);
        }
        LogUtil.a("Track_HmsMap", "showOrHide isShow = ", Boolean.valueOf(z));
    }

    private List<LatLng> c() {
        int left = this.aa.getLeft();
        int top = this.aa.getTop();
        int right = this.aa.getRight();
        int bottom = this.aa.getBottom();
        LatLng fromScreenLocation = this.r.getProjection().fromScreenLocation(new Point((int) (this.aa.getX() + ((right - left) / 2)), (int) (this.aa.getY() + ((bottom - top) / 2))));
        return Arrays.asList(new LatLng(-85.0d, fromScreenLocation.longitude - 89.9d), new LatLng(-85.0d, fromScreenLocation.longitude + 89.9d), new LatLng(85.0d, fromScreenLocation.longitude + 89.9d), new LatLng(85.0d, fromScreenLocation.longitude - 89.9d));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setScreenOnOrForegrand(boolean z) {
        this.u = z;
        if (z) {
            forceDrawLine();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveToCenter() {
        CameraUpdate cameraUpdate;
        if (this.i == null || (cameraUpdate = this.h) == null) {
            return;
        }
        cameraUpdate.getCameraUpdate().setCameraPosition(this.i);
        this.r.moveCamera(this.h);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void forceDrawLine() {
        if (this.v.size() <= 0) {
            return;
        }
        this.d.sendEmptyMessage(3);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showTrackMarkers(boolean z) {
        if (z) {
            j();
        } else {
            b();
        }
    }

    private void b(List<LatLng> list) {
        if (list == null) {
            LogUtil.a("Track_HmsMap", "list is null");
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            LatLng latLng = list.get(size);
            if (!gwe.c(gwe.c(latLng))) {
                addEndMarker(gwe.c(latLng));
                LogUtil.a("Track_HmsMap", "addEndMarker");
                return;
            }
        }
    }

    private void a(List<LatLng> list, int i) {
        if (list == null) {
            LogUtil.a("Track_HmsMap", "list is null");
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            LatLng latLng = list.get(i2);
            if (!gwe.c(gwe.c(latLng))) {
                addSportStartMarker(gwe.c(latLng), i);
                List<hjd> list2 = this.aj;
                if (list2 != null) {
                    list2.add(0, c(latLng));
                }
                LogUtil.a("Track_HmsMap", "addStartMarker");
                return;
            }
        }
    }

    private void c(List<LatLng> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(16);
        c(list, size, arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.au).width(5.0f).zIndex(10.0f).visible(true);
        Iterator<LatLng> it = arrayList.iterator();
        while (it.hasNext()) {
            polylineOptions.add(it.next());
        }
        this.r.addPolyline(polylineOptions);
        if (this.aj != null) {
            a(arrayList);
        }
    }

    private void c(List<LatLng> list, int i, List<LatLng> list2) {
        for (int i2 = 0; i2 < i; i2++) {
            LatLng latLng = list.get(i2);
            if (gwe.c(gwe.c(latLng))) {
                if (!list2.isEmpty()) {
                    d(list2);
                }
                d(list, i2);
            } else {
                list2.add(latLng);
            }
        }
    }

    private void d(List<LatLng> list) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.au).width(5.0f).zIndex(10.0f).visible(true);
        Iterator<LatLng> it = list.iterator();
        while (it.hasNext()) {
            polylineOptions.add(it.next());
        }
        this.r.addPolyline(polylineOptions);
        if (this.aj != null) {
            a(list);
        }
        list.clear();
    }

    private void d(List<LatLng> list, int i) {
        if (list == null) {
            ReleaseLogUtil.d("Track_HmsMap", "drawPauseLine list is null");
            return;
        }
        int size = list.size();
        LatLng latLng = i > 0 ? list.get(i - 1) : null;
        LatLng latLng2 = i < size + (-1) ? list.get(i + 1) : null;
        if (latLng == null || latLng2 == null) {
            return;
        }
        Iterator<PolylineOptions> it = e(latLng, latLng2).iterator();
        while (it.hasNext()) {
            this.r.addPolyline(it.next());
        }
        List<hjd> list2 = this.aj;
        if (list2 != null) {
            list2.add(c(latLng2));
        }
    }

    private void a(List<LatLng> list) {
        boolean z = true;
        for (LatLng latLng : list) {
            if (z) {
                z = false;
            } else {
                this.aj.add(c(latLng));
            }
        }
    }

    private hjd c(LatLng latLng) {
        return gwe.c(latLng);
    }

    private void c(List<LatLng> list, List<LatLng> list2, List<LatLng> list3, Map<Long, double[]> map) {
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            if (entry.getValue().length <= 1) {
                LogUtil.h("Track_HmsMap", "unexpected point, length <= 1");
            } else {
                LatLng latLng = new LatLng(entry.getValue()[0], entry.getValue()[1]);
                if (list2.size() <= 0 || !gwe.b(gwe.c(list2.get(list2.size() - 1)), gwe.c(latLng))) {
                    list2.add(latLng);
                    list3.add(latLng);
                    if (!gwe.c(gwe.c(latLng))) {
                        list.add(latLng);
                    }
                }
            }
        }
    }

    private void j() {
        int d;
        if (this.p) {
            d = hji.e(this.o);
        } else {
            d = hji.d(this.ap, this.o);
        }
        for (int i = d - 1; i < this.x.size(); i += d) {
            if (this.x.get(i).d) {
                e(i);
            }
        }
        LogUtil.c("Track_HmsMap", "mMarkersList.size = ", Integer.valueOf(this.ae.size()));
    }

    private void e(int i) {
        String e = UnitUtil.e(i + 1, 1, 0);
        this.ae.add((this.p && !TextUtils.isEmpty(e) && mwy.a().isPluginAvaiable()) ? e(e, this.x.get(i).c) : gwe.aUB_(this.g.getResources(), this.x.get(i).c, e, this.r));
    }

    private Marker e(String str, LatLng latLng) {
        String a2 = hpt.a(str);
        hlg hlgVar = new hlg();
        hlgVar.d(new hjd(latLng.latitude, latLng.longitude));
        hlgVar.e(0.5f, 0.9f);
        hlgVar.e(a2);
        hlgVar.c(this.p);
        hlgVar.e(this.f13212a);
        return mwy.a().set3dMarker(this.r, hlgVar);
    }

    private void b() {
        Iterator<Marker> it = this.ae.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        this.ae.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public List<hjd> requestSimplePoints() {
        return this.am;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawAddMapTracking(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            ReleaseLogUtil.d("Track_HmsMap", "drawAddMapTracking lastHiHealthLatLng or currentHiHealthLatLng is null");
            return;
        }
        this.ax.add(gwe.a(hjdVar));
        this.ag.setPoints(this.ax);
        animateCamera(hjdVar2, 1000L, (InterfaceMapCallback) null);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addSportStartMarker(hjd hjdVar, int i) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "addSportStartMarker hiHealthLatLng is null");
            return;
        }
        Marker marker = this.as;
        if (marker != null) {
            marker.remove();
        }
        this.as = gwe.c(this.r, hjdVar, i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void screenShotToFile(final InterfaceSnapshotCallback interfaceSnapshotCallback) {
        synchronized (this) {
            this.r.snapshot(new HuaweiMap.SnapshotReadyCallback() { // from class: hkn
                @Override // com.huawei.hms.maps.HuaweiMap.SnapshotReadyCallback
                public final void onSnapshotReady(Bitmap bitmap) {
                    hkm.bhL_(InterfaceSnapshotCallback.this, bitmap);
                }
            });
        }
    }

    static /* synthetic */ void bhL_(InterfaceSnapshotCallback interfaceSnapshotCallback, Bitmap bitmap) {
        if (interfaceSnapshotCallback != null) {
            interfaceSnapshotCallback.onSnapshotReady(bitmap);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void loadingEnd() {
        CameraUpdate cameraUpdate = this.h;
        if (cameraUpdate != null) {
            this.r.moveCamera(cameraUpdate);
        }
        for (int i = 0; i < this.af; i++) {
            a(i);
        }
    }

    private void a(int i) {
        Marker c;
        List<LatLng> list = this.ar;
        if (list != null && (c = gwe.c(this.r, gwe.c((LatLng) c(list, i)), ((Integer) c(this.ao, i)).intValue())) != null) {
            c.setZIndex(14.0f);
        }
        LatLng latLng = (LatLng) c(this.m, i);
        if (latLng != null) {
            if (i < this.af - 1) {
                this.j = this.r.addMarker(new MarkerOptions().position(latLng).draggable(false).zIndex(14.0f));
                this.j.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.g.getResources(), R.drawable._2131428708_res_0x7f0b0564)));
                this.j.setMarkerAnchor(0.5f, 0.9f);
            } else {
                this.j = this.r.addMarker(new MarkerOptions().position(latLng).draggable(false).zIndex(14.0f));
                Bitmap aUI_ = gwe.aUI_(this.l);
                if (aUI_ == null) {
                    aUI_ = BitmapFactory.decodeResource(this.g.getResources(), R.drawable._2131428707_res_0x7f0b0563);
                }
                this.j.setIcon(BitmapDescriptorFactory.fromBitmap(aUI_));
                this.j.setMarkerAnchor(0.5f, 0.9f);
            }
        }
        if (koq.b(this.al)) {
            return;
        }
        for (PolylineOptions polylineOptions : this.al) {
            this.av.put(this.r.addPolyline(polylineOptions), polylineOptions);
        }
    }

    private <T> T c(List<T> list, int i) {
        if (list == null || list.size() <= i) {
            return null;
        }
        return list.get(i);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void registerLoadingListener(InterfaceMapCallback interfaceMapCallback) {
        this.z = interfaceMapCallback;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setIsStop(boolean z) {
        this.y = z;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom() {
        moveCameraByZoom(12.5f);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByZoom(float f) {
        this.r.moveCamera(CameraUpdateFactory.zoomTo(f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveCameraByLatLng(hjd hjdVar) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "moveCameraByLatLng hiHealthLatLng is null, please check");
        } else {
            this.r.moveCamera(CameraUpdateFactory.newLatLng(gwe.a(hjdVar)));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public double[] getLastLocation(Context context, Location location) {
        if (location == null) {
            ReleaseLogUtil.d("Track_HmsMap", "currentLocation is null, please check");
            return new double[0];
        }
        return new double[]{location.getLatitude(), location.getLongitude()};
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void updateSportMarker(hjd hjdVar, BitmapDrawable bitmapDrawable) {
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_HmsMap", "updateSportMarker hiHealthLatLng is null, please check");
            return;
        }
        if (bitmapDrawable == null) {
            ReleaseLogUtil.d("Track_HmsMap", "updateSportMarker bitmapDrawable is null, please check");
            return;
        }
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(bitmapDrawable.getBitmap());
        Marker marker = this.j;
        if (marker == null) {
            this.j = this.r.addMarker(new MarkerOptions().position(gwe.a(hjdVar)).draggable(false).icon(fromBitmap));
        } else {
            marker.setPosition(gwe.a(hjdVar));
            this.j.setIcon(fromBitmap);
        }
    }

    static class b extends BaseHandler<hkm> {
        private WeakReference<hkm> b;

        b(hkm hkmVar) {
            super(hkmVar);
            this.b = new WeakReference<>(hkmVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bhM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hkm hkmVar, Message message) {
            hkm hkmVar2 = this.b.get();
            if (hkmVar2 == null) {
                LogUtil.b("Track_HmsMap", "hmsMap is null");
                return;
            }
            if (message != null) {
                if (hkmVar2.n != null) {
                    if (message.what == 0) {
                        hkmVar2.n.setVisible(false);
                        hkmVar2.c(1);
                        return;
                    }
                    if (message.what == 1) {
                        hkmVar2.n.setVisible(true);
                        hkmVar2.c(0);
                        return;
                    }
                    if (message.what == 2) {
                        hkmVar2.e();
                        return;
                    }
                    if (message.what == 3) {
                        if (hkmVar2.v == null || hkmVar2.v.size() < 2) {
                            return;
                        }
                        hkmVar2.ag.setPoints(hkmVar2.v);
                        hkmVar2.addEndMarker(gwe.c((LatLng) hkmVar2.v.get(hkmVar2.v.size() - 1)));
                        hkmVar2.animateCamera(gwe.c((LatLng) hkmVar2.v.get(hkmVar2.v.size() - 1)), 1000L, (InterfaceMapCallback) null);
                        return;
                    }
                    LogUtil.c("Track_HmsMap", "wrong msg");
                    return;
                }
                return;
            }
            LogUtil.a("Track_HmsMap", "msg is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        PolylineOptions polylineOptions = new PolylineOptions();
        List<LatLng> list = this.v;
        if (list != null && list.size() >= 2) {
            PolylineOptions zIndex = polylineOptions.color(this.au).width(5.0f).zIndex(10.0f);
            List<LatLng> list2 = this.v;
            LatLng latLng = list2.get(list2.size() - 1);
            List<LatLng> list3 = this.v;
            zIndex.add(latLng, list3.get(list3.size() - 1)).visible(true);
            this.ag = this.r.addPolyline(polylineOptions);
            List<LatLng> list4 = this.ax;
            if (list4 != null && !list4.isEmpty()) {
                this.ax.clear();
            }
        }
        if (koq.c(this.v)) {
            this.v.clear();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLogoPadding(int i, int i2, int i3, int i4) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null || this.g == null) {
            return;
        }
        huaweiMap.setPadding(i, i2, i3, i4);
        this.r.getUiSettings().setLogoPadding(i, i2, i3, i4 + nsn.c(this.g, 2.0f));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlh hlhVar, long j, final InterfaceMapCallback interfaceMapCallback) {
        synchronized (this) {
            if (this.r == null) {
                LogUtil.b("Track_HmsMap", "map is null in animateCamera");
                return;
            }
            CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(d(hlhVar));
            if (j <= 0) {
                this.r.moveCamera(newCameraPosition);
            } else {
                LogUtil.a("Track_HmsMap", "animateCamera start");
                this.q = true;
                this.s = false;
                final int i = this.f + 1;
                this.f = i;
                this.b.put(Integer.valueOf(i), false);
                this.r.animateCamera(newCameraPosition, (int) j, new HuaweiMap.CancelableCallback() { // from class: hkm.6
                    @Override // com.huawei.hms.maps.HuaweiMap.CancelableCallback
                    public void onFinish() {
                        synchronized (this) {
                            hkm.this.b.put(Integer.valueOf(i), true);
                            hkm.this.d(interfaceMapCallback);
                        }
                    }

                    @Override // com.huawei.hms.maps.HuaweiMap.CancelableCallback
                    public void onCancel() {
                        synchronized (this) {
                            hkm.this.b.put(Integer.valueOf(i), true);
                            hkm.this.a(interfaceMapCallback);
                        }
                    }
                });
                c(i, j, interfaceMapCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(InterfaceMapCallback interfaceMapCallback) {
        if (!this.s) {
            LogUtil.a("Track_HmsMap", "camera is moving error onCancel");
            return;
        }
        this.s = false;
        if (interfaceMapCallback != null) {
            LogUtil.a("Track_HmsMap", "animateCamera onCancel");
            interfaceMapCallback.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(InterfaceMapCallback interfaceMapCallback) {
        if (this.s) {
            LogUtil.a("Track_HmsMap", "camera should cancel error onFinish");
            this.r.stopAnimation();
            interfaceMapCallback.onCancel();
            this.s = false;
            this.q = false;
            return;
        }
        if (!this.q) {
            LogUtil.a("Track_HmsMap", "camera is not moving error onFinish");
            return;
        }
        this.q = false;
        if (interfaceMapCallback != null) {
            LogUtil.a("Track_HmsMap", "animateCamera onFinish");
            interfaceMapCallback.onFinish();
        }
    }

    private void c(final int i, long j, final InterfaceMapCallback interfaceMapCallback) {
        this.aq.deleteObservers();
        this.aq.addObserver(new Observer() { // from class: hkm.7
            @Override // java.util.Observer
            public void update(Observable observable, Object obj) {
                new Handler().postDelayed(new Runnable() { // from class: hkm.7.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (this) {
                            LogUtil.a("Track_HmsMap", "forceCallbackHandle cancel observer", Boolean.valueOf(hkm.this.b.containsKey(Integer.valueOf(i))), " ", hkm.this.b.get(Integer.valueOf(i)), " ", Integer.valueOf(i));
                            if (hkm.this.b.containsKey(Integer.valueOf(i)) && ((Boolean) hkm.this.b.get(Integer.valueOf(i))).booleanValue()) {
                                return;
                            }
                            LogUtil.a("Track_HmsMap", "forceCallback");
                            if (!hkm.this.s) {
                                interfaceMapCallback.onCancel();
                            }
                        }
                    }
                }, 400L);
            }
        });
        new Handler().postDelayed(new Runnable() { // from class: hkm.9
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    LogUtil.a("Track_HmsMap", "forceCallbackHandle ", Boolean.valueOf(hkm.this.b.containsKey(Integer.valueOf(i))), " ", hkm.this.b.get(Integer.valueOf(i)), " ", Integer.valueOf(i));
                    if (hkm.this.b.containsKey(Integer.valueOf(i)) && ((Boolean) hkm.this.b.get(Integer.valueOf(i))).booleanValue()) {
                        return;
                    }
                    LogUtil.a("Track_HmsMap", "forceCallback");
                    if (!hkm.this.s) {
                        interfaceMapCallback.onFinish();
                    }
                }
            }
        }, j + 400);
    }

    private CameraPosition d(hlh hlhVar) {
        if (hlhVar == null) {
            return null;
        }
        return new CameraPosition(gwe.a(hlhVar.d()), hlhVar.c(), hlhVar.b(), hlhVar.a());
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void stopAnimation() {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "bounds is null in clear");
            return;
        }
        Context context = this.g;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: hkm.10
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        hkm.this.q = false;
                        hkm.this.s = true;
                        hkm.this.aq.notifyObservers();
                        LogUtil.a("Track_HmsMap", "stopAnimation");
                        hkm.this.r.stopAnimation();
                    }
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void drawLine(hld hldVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in drawLine");
            return;
        }
        if (hldVar == null || hldVar.a() == null || hldVar.c() == null) {
            return;
        }
        Polyline polyline = this.k;
        if (polyline == null || e(hldVar, polyline)) {
            PolylineOptions color = new PolylineOptions().add(gwe.a(hldVar.a()), gwe.a(hldVar.c())).width(hldVar.b() * 0.2f).color(hldVar.e());
            if (hldVar.h()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Dot());
                color.pattern(arrayList);
            }
            this.k = this.r.addPolyline(color);
            return;
        }
        List<LatLng> points = this.k.getPoints();
        if (points != null) {
            points.add(gwe.a(hldVar.a()));
            points.add(gwe.a(hldVar.c()));
            this.k.setPoints(points);
        }
    }

    private boolean e(hld hldVar, Polyline polyline) {
        return (hldVar.h() && koq.b(polyline.getPattern())) || !(hldVar.h() || koq.b(polyline.getPattern()));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public String drawLines(hle hleVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in drawLine");
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
            arrayList.add(gwe.a(hleVar.a()));
            arrayList.add(gwe.a(hleVar.c()));
        } else {
            Iterator<hjd> it = hleVar.i().iterator();
            while (it.hasNext()) {
                arrayList.add(gwe.a(it.next()));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(hleVar.f())) {
            arrayList2.add(Integer.valueOf(hleVar.e()));
        } else {
            arrayList2.addAll(hleVar.f());
        }
        PolylineOptions visible = new PolylineOptions().addAll(arrayList).width(hleVar.b() * 0.3f).color(((Integer) arrayList2.get(0)).intValue()).zIndex(hleVar.d()).visible(hleVar.g());
        if (hleVar.h()) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new Dot());
            visible.pattern(arrayList3);
        }
        Polyline addPolyline = this.r.addPolyline(visible);
        String id = addPolyline.getId();
        this.ak.put(id, addPolyline);
        return id;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setLineVisible(String str, boolean z) {
        if (StringUtils.g(str)) {
            LogUtil.b("Track_HmsMap", "id is null in setLineVisible");
            return;
        }
        Polyline polyline = this.ak.get(str);
        if (polyline == null) {
            return;
        }
        polyline.setVisible(z);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public GrowAnimationBuilder getGrowAnimation() {
        return new hkr();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void clear() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.b("Track_HmsMap", "clear HuaweiMap is null");
            return;
        }
        huaweiMap.clear();
        this.ai = new Vector<>();
        this.k = null;
        this.c.clear();
        synchronized (this) {
            this.f = 0;
        }
        this.b.clear();
        this.av.clear();
        this.ah.clear();
        this.ak.clear();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void moveMarker(int i, hjd hjdVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in moveMarker");
            return;
        }
        if (koq.b(this.ai, i)) {
            return;
        }
        Marker marker = this.ai.get(i);
        if (marker == null) {
            LogUtil.h("Track_HmsMap", "moveMarker: marker is null");
        } else {
            marker.setPosition(gwe.a(hjdVar));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public int addMarker(hlg hlgVar, GrowAnimationBuilder growAnimationBuilder) {
        Marker addMarker;
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in addMarker");
            return -1;
        }
        if (hlgVar == null || hlgVar.bhO_() == null) {
            return -1;
        }
        if (this.p && !TextUtils.isEmpty(hlgVar.a()) && mwy.a().isPluginAvaiable()) {
            hlgVar.c(this.p);
            hlgVar.e(this.f13212a);
            addMarker = mwy.a().set3dMarker(this.r, hlgVar);
        } else {
            addMarker = this.r.addMarker(new MarkerOptions().flat(hlgVar.g()).anchorMarker(((Float) hlgVar.bhN_().first).floatValue(), ((Float) hlgVar.bhN_().second).floatValue()).zIndex(hlgVar.d()).draggable(hlgVar.h()).icon(BitmapDescriptorFactory.fromBitmap(hlgVar.bhO_())).position(gwe.a(hlgVar.e())).visible(hlgVar.f()));
        }
        if (growAnimationBuilder instanceof hkr) {
            ((hkr) growAnimationBuilder).a(addMarker);
            growAnimationBuilder.displayAnimation();
        }
        if (this.ai == null) {
            this.ai = new Vector<>();
        }
        this.ai.add(addMarker);
        return this.ai.size() - 1;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in deleteMarker");
            return;
        }
        if (koq.b(this.ai, i)) {
            return;
        }
        Marker marker = this.ai.get(i);
        if (marker == null) {
            LogUtil.h("Track_HmsMap", "restoreMarker: marker is null");
            return;
        }
        if (growAnimationBuilder == null) {
            marker.setVisible(true);
        }
        if (growAnimationBuilder instanceof hkr) {
            ((hkr) growAnimationBuilder).a(marker);
            growAnimationBuilder.displayAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void hideMarker(int i, GrowAnimationBuilder growAnimationBuilder) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in deleteMarker");
            return;
        }
        if (koq.b(this.ai, i)) {
            return;
        }
        Marker marker = this.ai.get(i);
        if (marker == null) {
            LogUtil.h("Track_HmsMap", "deleteMarker: marker is null");
            return;
        }
        if (growAnimationBuilder == null) {
            marker.setVisible(false);
        }
        if (growAnimationBuilder instanceof hkr) {
            ((hkr) growAnimationBuilder).a(marker);
            growAnimationBuilder.disappearAnimation();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMarkerIcon(int i, Bitmap bitmap) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in setMarkerIcon");
            return;
        }
        Vector<Marker> vector = this.ai;
        if (vector == null || i < 0 || i >= vector.size()) {
            LogUtil.b("Track_HmsMap", "id is error in setMarkerIcon");
        } else {
            this.ai.get(i).setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setPreviewStatus(hla hlaVar, int i, int i2, int i3, int i4) {
        if (hlaVar == null) {
            LogUtil.b("Track_HmsMap", "bounds is null in setPreviewStatus");
        } else {
            if (this.r == null) {
                LogUtil.b("Track_HmsMap", "map is null in setPreviewStatus");
                return;
            }
            LatLngBounds build = new LatLngBounds.Builder().include(gwe.a(hlaVar.b())).include(gwe.a(hlaVar.c())).build();
            this.r.moveCamera(CameraUpdateFactory.newLatLngBounds(build, (int) this.g.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.g.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.65d), 0));
            this.r.moveCamera(CameraUpdateFactory.newLatLngBounds(build, (int) this.g.getResources().getDimension(R.dimen._2131362431_res_0x7f0a027f), (int) (this.g.getResources().getDimension(R.dimen._2131362430_res_0x7f0a027e) * 0.65d), 0));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hlh getMapStatus() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.b("Track_HmsMap", "map is null in getMapStatus");
            return new hlh();
        }
        hlh b2 = b(huaweiMap.getCameraPosition());
        if (b2 == null) {
            return new hlh();
        }
        b2.c(c(this.r.getCameraPosition().zoom));
        return b2;
    }

    private hlh b(CameraPosition cameraPosition) {
        if (cameraPosition == null) {
            return null;
        }
        hlh hlhVar = new hlh();
        hlhVar.d(cameraPosition.bearing).c(gwe.c(cameraPosition.target)).b(cameraPosition.tilt).e(cameraPosition.zoom);
        return hlhVar;
    }

    private float c(float f) {
        return (float) (Math.exp(f * (-0.688d)) * 49671.0d);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMaxZoomLevel() {
        return this.r.getMaxZoomLevel();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setMapLoadedCallback(final InterfaceMapLoadedCallback interfaceMapLoadedCallback) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in setMapLoadedCallback");
        } else {
            if (interfaceMapLoadedCallback == null) {
                return;
            }
            this.r.setOnMapLoadedCallback(new HuaweiMap.OnMapLoadedCallback() { // from class: hkm.8
                @Override // com.huawei.hms.maps.HuaweiMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    interfaceMapLoadedCallback.onMapLoaded();
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void setCameraChangeCallback(final InterfaceMapStatusChangeCallback interfaceMapStatusChangeCallback) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.b("Track_HmsMap", "map is null in setCameraChangeCallback");
        } else {
            if (interfaceMapStatusChangeCallback == null) {
                return;
            }
            huaweiMap.setOnCameraMoveListener(new HuaweiMap.OnCameraMoveListener() { // from class: hkm.12
                @Override // com.huawei.hms.maps.HuaweiMap.OnCameraMoveListener
                public void onCameraMove() {
                    interfaceMapStatusChangeCallback.onMapStatusChange(hkm.this.getMapStatus());
                    hkm.this.f13212a = (float) (Math.pow(0.5d, r0.r.getCameraPosition().zoom - 16.0f) * 5.300000190734863d);
                    hkm hkmVar = hkm.this;
                    hkmVar.f13212a = Math.min(hkmVar.f13212a, 650.0f);
                    if (hkm.this.p && koq.c(hkm.this.ai)) {
                        Iterator it = hkm.this.ai.iterator();
                        while (it.hasNext()) {
                            mwy.a().set3dMarkerScale((Marker) it.next(), hkm.this.f13212a);
                        }
                    }
                    if (hkm.this.p && koq.c(hkm.this.ae)) {
                        Iterator it2 = hkm.this.ae.iterator();
                        while (it2.hasNext()) {
                            mwy.a().set3dMarkerScale((Marker) it2.next(), hkm.this.f13212a);
                        }
                    }
                }
            });
            this.r.setOnCameraIdleListener(new HuaweiMap.OnCameraIdleListener() { // from class: hkm.15
                @Override // com.huawei.hms.maps.HuaweiMap.OnCameraIdleListener
                public void onCameraIdle() {
                    interfaceMapStatusChangeCallback.onMapStatusChangeFinish(hkm.this.getMapStatus());
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onPausePurely() {
        MapView mapView = this.aa;
        if (mapView == null) {
            LogUtil.b("Track_HmsMap", "mapview is null in onPausePurely");
        } else {
            mapView.onPause();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onResumePurely() {
        MapView mapView = this.aa;
        if (mapView == null) {
            LogUtil.b("Track_HmsMap", "mapview is null in onResumePurely");
        } else {
            mapView.onResume();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void onDestroyPurely() {
        MapView mapView = this.aa;
        if (mapView == null) {
            LogUtil.b("Track_HmsMap", "mapview is null in onDestroyPurely");
        } else {
            mapView.onDestroy();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableAllGestures() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.b("Track_HmsMap", "map is null in disableAllGestures");
        } else {
            huaweiMap.getUiSettings().setAllGesturesEnabled(false);
            this.r.getUiSettings().setZoomControlsEnabled(false);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void showPureMap() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            LogUtil.b("Track_HmsMap", "map is null in showPureMap");
        } else {
            huaweiMap.getUiSettings().setCompassEnabled(false);
            this.r.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void changeMapType(MapTypeDescription mapTypeDescription, hak hakVar) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in changeMapType");
            return;
        }
        if (mapTypeDescription == null) {
            return;
        }
        this.p = false;
        if (mapTypeDescription.c() == MapTypeDescription.MapType.DEFAULT_MAP_THREE_D) {
            this.p = true;
            this.r.setMapType(3);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886190_res_0x7f12006e));
        } else if (mapTypeDescription.c() == MapTypeDescription.MapType.MAP_TYPE_NIGHT) {
            this.r.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886191_res_0x7f12006f));
        } else if (mapTypeDescription.c() == MapTypeDescription.MapType.MAP_TYPE_CUSTOM) {
            this.r.setMapType(1);
            a(hakVar);
        } else {
            this.r.setMapType(1);
            this.r.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.g, R.raw._2131886190_res_0x7f12006e));
        }
        a(this.p);
    }

    private void a(boolean z) {
        if (this.r != null) {
            LogUtil.a("Track_HmsMap", "set3dMapType: ", Boolean.valueOf(z));
            LogUtil.a("Track_HmsMap", "set3dMapType: set3dType result = ", Boolean.valueOf(mwy.a().set3dMapType(this.r, z)));
        }
    }

    private void a(hak hakVar) {
        if (hakVar == null) {
            LogUtil.h("Track_HmsMap", "initCustomMapStyle customMapInformation == null");
        } else {
            this.r.setMapStyle(new MapStyleOptions(Utils.c(hakVar.i())));
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapZoom() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            return 0.0f;
        }
        return huaweiMap.getCameraPosition().zoom;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public float getMapTilt() {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            return 0.0f;
        }
        return huaweiMap.getCameraPosition().tilt;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public Point getScreenLocation(hjd hjdVar) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null || hjdVar == null) {
            LogUtil.b("Track_HmsMap", "huaweiMap is null or latlng is null");
            return new Point();
        }
        Projection projection = huaweiMap.getProjection();
        if (projection == null) {
            LogUtil.b("Track_HmsMap", "projection is null or latlng is null");
            return new Point();
        }
        return projection.toScreenLocation(gwe.a(hjdVar));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public hjd getLatLngByScreenPoint(Point point) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null || point == null) {
            LogUtil.b("Track_HmsMap", "huaweiMap is null or point is null");
            return new hjd(0.0d, 0.0d);
        }
        Projection projection = huaweiMap.getProjection();
        if (projection == null) {
            LogUtil.b("Track_HmsMap", "projection is null or latlng is null");
            return new hjd(0.0d, 0.0d);
        }
        return gwe.c(projection.fromScreenLocation(point));
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void disableMarkerClick() {
        this.r.setOnMarkerClickListener(new HuaweiMap.OnMarkerClickListener() { // from class: hkm.4
            @Override // com.huawei.hms.maps.HuaweiMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addMarkerClickListener(final InterfaceMapMarkerClickCallback interfaceMapMarkerClickCallback) {
        HuaweiMap huaweiMap = this.r;
        if (huaweiMap == null) {
            return;
        }
        huaweiMap.setOnMarkerClickListener(new HuaweiMap.OnMarkerClickListener() { // from class: hkm.5
            @Override // com.huawei.hms.maps.HuaweiMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                int i = 0;
                if (interfaceMapMarkerClickCallback == null || marker == null) {
                    LogUtil.h("Track_HmsMap", "interfaceMapMarkerClickCallback or marker is null");
                    return false;
                }
                while (true) {
                    if (i >= hkm.this.ai.size()) {
                        i = -1;
                        break;
                    }
                    if (marker.equals(hkm.this.ai.get(i))) {
                        break;
                    }
                    i++;
                }
                LogUtil.a("Track_HmsMap", "click Marker count:", Integer.valueOf(i));
                interfaceMapMarkerClickCallback.onMarkerClick(i);
                return true;
            }
        });
    }

    static class a {
        private LatLng c;
        private boolean d = true;

        a() {
        }

        public String toString() {
            return "to show" + this.d;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void addHiHealthMarkers(List<hjg> list) {
        ArrayList<MarkerOptions> c = gwe.c(list);
        if (koq.b(c)) {
            return;
        }
        c.forEach(new Consumer() { // from class: hkq
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hkm.this.a((MarkerOptions) obj);
            }
        });
    }

    /* synthetic */ void a(MarkerOptions markerOptions) {
        this.r.addMarker(markerOptions);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void animateCamera(hlc hlcVar, int i) {
        CameraUpdate newLatLngBounds;
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in animateCameraBounds");
            return;
        }
        if (hlcVar == null || koq.b(hlcVar.e())) {
            LogUtil.b("Track_HmsMap", "param is null in animateCameraByBounds");
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<hjd> it = hlcVar.e().iterator();
        while (it.hasNext()) {
            builder.include(gwe.a(it.next()));
        }
        LatLngBounds build = builder.build();
        int d = hlcVar.d();
        int a2 = hlcVar.a();
        if (d == 0 || a2 == 0) {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, hlcVar.c());
        } else {
            newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, d, a2, hlcVar.c());
        }
        this.r.moveCamera(newLatLngBounds);
        float b2 = hlcVar.b();
        if (Float.compare(b2, 0.0f) != 0) {
            c(b2, build.getCenter(), i);
        }
    }

    private void c(float f, LatLng latLng, int i) {
        final CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(latLng).bearing(360.0f - f).zoom(getMapZoom()).build());
        if (i <= 0) {
            this.r.moveCamera(newCameraPosition);
        } else {
            this.r.animateCamera(newCameraPosition, i, new HuaweiMap.CancelableCallback() { // from class: hkm.2
                @Override // com.huawei.hms.maps.HuaweiMap.CancelableCallback
                public void onFinish() {
                }

                @Override // com.huawei.hms.maps.HuaweiMap.CancelableCallback
                public void onCancel() {
                    hkm.this.r.moveCamera(newCameraPosition);
                }
            });
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap
    public void scrollBy(float f, float f2) {
        if (this.r == null) {
            LogUtil.b("Track_HmsMap", "map is null in scrollBy");
        } else {
            this.r.moveCamera(CameraUpdateFactory.scrollBy(-f, -f2));
        }
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
}
