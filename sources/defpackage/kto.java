package defpackage;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwlocationmgr.model.IGpsStatusCallback;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlocationmgr.model.IOriginalGpsStatusListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* loaded from: classes5.dex */
public class kto {
    private static kto d;
    private static final Object e = new Object();
    private StorageParams aa;
    private String ab;
    private Object ac;
    private Context b;
    private HandlerThread g;
    private boolean j;
    private d n;
    private LocationManager s;
    private Runnable v;
    private long x = 2000;

    /* renamed from: a, reason: collision with root package name */
    private long f14586a = 1000;
    private float w = 0.0f;
    private long y = 0;
    private float u = 0.0f;
    private boolean i = true;
    private boolean m = false;
    private LocationListener c = null;
    private LocationListener r = null;
    private LocationListener t = null;
    private ConcurrentHashMap<String, e> p = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, b> h = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, c> q = new ConcurrentHashMap<>();
    private boolean l = false;
    private int f = 3;
    private Location o = null;
    private long k = 0;
    private List<ktn> z = new ArrayList(10);

    private kto(Context context) {
        this.g = null;
        this.n = null;
        this.b = context;
        if (this.g == null) {
            HandlerThread handlerThread = new HandlerThread("Track_LocationManagerUtils");
            this.g = handlerThread;
            handlerThread.start();
        }
        if (this.n == null) {
            this.n = new d(this.g.getLooper());
        }
        j();
        h();
        g();
    }

    public static kto a(Context context) {
        kto ktoVar;
        synchronized (e) {
            kto ktoVar2 = d;
            if (ktoVar2 == null) {
                d = new kto(context);
            } else {
                ktoVar2.b();
            }
            ktoVar = d;
        }
        return ktoVar;
    }

    public void b() {
        if (this.l) {
            return;
        }
        j();
        f();
    }

    /* renamed from: kto$4, reason: invalid class name */
    class AnonymousClass4 implements GpsStatus.Listener {
        final /* synthetic */ kto b;

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            this.b.e(i);
        }
    }

    private void g() {
        this.ac = new GnssStatus.Callback() { // from class: kto.3
            @Override // android.location.GnssStatus.Callback
            public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                super.onSatelliteStatusChanged(gnssStatus);
                if (kto.this.s != null) {
                    kth bQT_ = ktj.bQT_(gnssStatus);
                    kto.this.bRc_(gnssStatus);
                    if (bQT_ != null) {
                        kto.this.d(ktj.b(bQT_));
                        LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_SATELLITE_STATUS ", bQT_.toString());
                    } else {
                        kto.this.d(1);
                        LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_SATELLITE_STATUS GPS_STATE_OUT_OF_SERVICE");
                    }
                }
            }

            @Override // android.location.GnssStatus.Callback
            public void onStarted() {
                LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_STARTED");
                kto.this.d(2);
                kto.this.b(3);
            }

            @Override // android.location.GnssStatus.Callback
            public void onStopped() {
                LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_STOPPED");
                if (kto.this.b == null || ktj.e(kto.this.b)) {
                    return;
                }
                kto.this.d(0);
                kto.this.b(0);
            }

            @Override // android.location.GnssStatus.Callback
            public void onFirstFix(int i) {
                LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_FIRST_FIX");
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LocationManager locationManager;
        if (i == 1) {
            LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_STARTED");
            d(2);
            b(3);
            return;
        }
        if (i == 2) {
            LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_STOPPED");
            Context context = this.b;
            if (context == null || ktj.e(context)) {
                return;
            }
            d(0);
            b(0);
            return;
        }
        if (i == 3) {
            LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_FIRST_FIX");
            return;
        }
        if (i == 4 && (locationManager = this.s) != null) {
            GpsStatus gpsStatus = locationManager.getGpsStatus(null);
            kth bQU_ = ktj.bQU_(gpsStatus);
            bRd_(gpsStatus);
            if (bQU_ != null) {
                d(ktj.b(bQU_));
                LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_SATELLITE_STATUS ", bQU_.toString());
            } else {
                d(1);
                LogUtil.c("Track_LocationManagerUtils", "test GPS Signal GPS_EVENT_SATELLITE_STATUS GPS_STATE_OUT_OF_SERVICE");
            }
        }
    }

    private void h() {
        this.ab = Integer.toString(20002);
        StorageParams storageParams = new StorageParams();
        this.aa = storageParams;
        storageParams.d(0);
        f();
    }

    private void f() {
        String b2 = SharedPreferenceManager.b(this.b, this.ab, "min_location_request_interval_time");
        this.x = 2000L;
        if (b2 != null && !b2.isEmpty()) {
            this.x = CommonUtil.g(b2);
        }
        this.w = 0.0f;
        String b3 = SharedPreferenceManager.b(this.b, this.ab, "min_location_request_interval_distance");
        if (b3 != null && !b3.isEmpty()) {
            this.w = CommonUtil.j(b3);
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", "mRequestMinTime :", Long.valueOf(this.x), " -- mRequestMinDistance :", Float.valueOf(this.w));
    }

    public boolean e() {
        return this.l;
    }

    public void d() {
        d dVar = this.n;
        if (dVar != null) {
            dVar.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        Context context;
        if (this.j) {
            return;
        }
        if (this.s == null && (context = this.b) != null) {
            if (context.getSystemService("location") instanceof LocationManager) {
                this.s = (LocationManager) this.b.getSystemService("location");
            } else {
                ReleaseLogUtil.d("Track_LocationManagerUtils", "get LocationService failed");
                return;
            }
        }
        if (ActivityCompat.checkSelfPermission(BaseApplication.e(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(BaseApplication.e(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            ReleaseLogUtil.e("Track_LocationManagerUtils", "changeLocationRequestTime ,mTime:", Long.valueOf(j));
            if (this.l && this.c != null) {
                ReleaseLogUtil.e("Track_LocationManagerUtils", "changeLocationRequestTime mIsRequestLocation is true");
                LocationManager locationManager = this.s;
                if (locationManager != null) {
                    locationManager.requestLocationUpdates(GeocodeSearch.GPS, j, this.w, this.c);
                }
            }
            this.x = j;
            this.i = false;
        }
    }

    public void a(ILoactionCallback iLoactionCallback, String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "regLocationListener keyName: ", str);
        if (!i() || this.n == null || this.p.containsKey(str)) {
            return;
        }
        this.p.putIfAbsent(str, new e(System.currentTimeMillis(), iLoactionCallback));
        if (!this.l) {
            this.n.sendEmptyMessage(1);
            this.l = true;
        } else {
            if (this.o == null || System.currentTimeMillis() - this.k >= 60000) {
                return;
            }
            bRb_(this.o);
        }
    }

    public void d(String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "unregLocationListener, keyName: ", str);
        if (this.p.containsKey(str)) {
            this.p.remove(str);
            if (m()) {
                this.n.sendEmptyMessage(2);
                return;
            }
            return;
        }
        ReleaseLogUtil.d("Track_LocationManagerUtils", str, " is not exist, cannot Deregister");
    }

    public void c(IGpsStatusCallback iGpsStatusCallback, String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "regGpsStatusListener, keyName: ", str);
        if (!i() || this.n == null || this.h.containsKey(str)) {
            return;
        }
        this.h.putIfAbsent(str, new b(System.currentTimeMillis(), iGpsStatusCallback));
        if (this.l) {
            return;
        }
        this.n.sendEmptyMessage(1);
        this.l = true;
    }

    public void e(String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "unRegGpsStatusListener, keyName: ", str);
        if (this.h.containsKey(str)) {
            this.h.remove(str);
            if (m()) {
                this.n.sendEmptyMessage(2);
            }
        }
    }

    public void a(IOriginalGpsStatusListener iOriginalGpsStatusListener, String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "regOriginalGpsStatusListener, keyName: ", str);
        if (!i() || this.n == null || this.q.containsKey(str)) {
            return;
        }
        this.q.putIfAbsent(str, new c(System.currentTimeMillis(), iOriginalGpsStatusListener));
        if (this.l) {
            return;
        }
        this.n.sendEmptyMessage(1);
        this.l = true;
    }

    public void c(String str) {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "unRegOriginalGpsStatusListener, keyName: ", str);
        if (this.q.containsKey(str)) {
            this.q.remove(str);
            if (m()) {
                this.n.sendEmptyMessage(2);
            }
        }
    }

    public void c(boolean z) {
        this.m = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bRb_(Location location) {
        if (location == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "updateLocation location null");
            return;
        }
        ConcurrentHashMap<String, e> concurrentHashMap = this.p;
        if (concurrentHashMap != null) {
            for (e eVar : concurrentHashMap.values()) {
                if (eVar != null) {
                    ILoactionCallback b2 = eVar.b();
                    if (b2 != null) {
                        b2.dispatchLocationChanged(location);
                    } else {
                        ReleaseLogUtil.d("Track_LocationManagerUtils", "locationCallback is null");
                    }
                } else {
                    ReleaseLogUtil.d("Track_LocationManagerUtils", "partLocation is null");
                }
            }
            return;
        }
        ReleaseLogUtil.d("Track_LocationManagerUtils", "updateLocation mLocationsCallbackMapList null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "regMyLocationListener.");
        Context context = this.b;
        if (context == null) {
            return;
        }
        if (context.getSystemService("location") instanceof LocationManager) {
            this.s = (LocationManager) this.b.getSystemService("location");
            n();
            if (this.m) {
                return;
            }
            q();
            return;
        }
        ReleaseLogUtil.d("Track_LocationManagerUtils", "regMyLocationListener get PartLocation Service");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Context context;
        LogUtil.a("Track_LocationManagerUtils", " requestMockLocation.");
        if (this.s == null && (context = this.b) != null) {
            if (context.getSystemService("location") instanceof LocationManager) {
                this.s = (LocationManager) this.b.getSystemService("location");
            } else {
                LogUtil.a("Track_LocationManagerUtils", "requestMockLocation get PartLocation Service failed");
                return;
            }
        }
        if (this.s == null) {
            return;
        }
        this.t = new a(this, null);
        if (this.s.isProviderEnabled("GpsMockProvider")) {
            this.s.requestLocationUpdates("GpsMockProvider", this.y, this.u, this.t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.s == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "mMyLocationManager is null");
            return;
        }
        t();
        p();
        this.s = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.s == null) {
            return;
        }
        s();
        this.s = null;
    }

    private void n() {
        try {
            this.c = new a(this, null);
            if (this.i) {
                ReleaseLogUtil.e("Track_LocationManagerUtils", "startGpsLocation FirstRequest");
                this.s.requestLocationUpdates(GeocodeSearch.GPS, this.f14586a, this.w, this.c);
            } else {
                ReleaseLogUtil.e("Track_LocationManagerUtils", "startGpsLocation");
                this.s.requestLocationUpdates(GeocodeSearch.GPS, this.x, this.w, this.c);
            }
            Object obj = this.ac;
            if (obj instanceof GnssStatus.Callback) {
                this.s.registerGnssStatusCallback((GnssStatus.Callback) obj);
            }
        } catch (Exception unused) {
            ReleaseLogUtil.c("Track_LocationManagerUtils", "Exception: provider doesn't exist: GPS");
        }
    }

    private void q() {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "startNetworkLocation");
        try {
            a aVar = new a(this, null);
            this.r = aVar;
            this.s.requestLocationUpdates(HAWebViewInterface.NETWORK, this.x, this.w, aVar);
        } catch (Exception unused) {
            ReleaseLogUtil.c("Track_LocationManagerUtils", "Exception: provider doesn't exist: network");
        }
    }

    private void t() {
        if (this.c == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", " stopGpsLocation mGpsListener is null");
            return;
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", " stopGpsLocation removeUpdates");
        this.s.removeUpdates(this.c);
        Object obj = this.ac;
        if (obj instanceof GnssStatus.Callback) {
            this.s.unregisterGnssStatusCallback((GnssStatus.Callback) obj);
        }
        this.c = null;
    }

    private void s() {
        if (this.t == null) {
            return;
        }
        LogUtil.a("Track_LocationManagerUtils", " stopMockLocation removeUpdates");
        this.s.removeUpdates(this.t);
        this.t = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.r == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "mNetworkListener is null");
            return;
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", " stopNetworkLocation removeUpdates");
        this.s.removeUpdates(this.r);
        this.r = null;
    }

    public Location bRe_(List<String> list) {
        Location location = null;
        if (this.s == null && this.b != null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "mMyLocationManager is null");
            if (this.b.getSystemService("location") instanceof LocationManager) {
                this.s = (LocationManager) this.b.getSystemService("location");
            } else {
                ReleaseLogUtil.d("Track_LocationManagerUtils", "getLastKnownLocation get PartLocation Service failed");
                return null;
            }
        }
        if (!i()) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "don't have location permission");
            return null;
        }
        try {
            if (koq.b(list)) {
                LocationManager locationManager = this.s;
                if (locationManager != null) {
                    for (String str : locationManager.getProviders(true)) {
                        location = this.s.getLastKnownLocation(str);
                        if (location != null) {
                            ReleaseLogUtil.e("Track_LocationManagerUtils", "The provider is ", str);
                            return location;
                        }
                    }
                }
            } else {
                for (String str2 : list) {
                    LocationManager locationManager2 = this.s;
                    if (locationManager2 != null) {
                        location = locationManager2.getLastKnownLocation(str2);
                    }
                    if (location != null) {
                        ReleaseLogUtil.e("Track_LocationManagerUtils", "The provider is ", str2);
                        return location;
                    }
                }
            }
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "IllegalArgumentException happen : provider is null or not exist");
        } catch (NullPointerException unused2) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "NullPointerException happen");
        }
        ReleaseLogUtil.d("Track_LocationManagerUtils", "lastKnownLocation is null");
        return location;
    }

    public Location bRf_() {
        return this.o;
    }

    private boolean i() {
        Context context = this.b;
        if (context == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "checkSelfPermission(), mContext is null");
            return false;
        }
        if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 && ActivityCompat.checkSelfPermission(this.b, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        ReleaseLogUtil.d("Track_LocationManagerUtils", "checkSelfPermission is false");
        return false;
    }

    private void j() {
        Context context;
        if (this.s == null && (context = this.b) != null) {
            if (!(context.getSystemService("location") instanceof LocationManager)) {
                return;
            } else {
                this.s = (LocationManager) this.b.getSystemService("location");
            }
        }
        LocationManager locationManager = this.s;
        if (locationManager == null) {
            return;
        }
        boolean isProviderEnabled = locationManager.isProviderEnabled("GpsMockProvider");
        this.j = isProviderEnabled;
        LogUtil.a("Track_LocationManagerUtils", " checkMockProvider mIsMocking:", Boolean.valueOf(isProviderEnabled));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        IGpsStatusCallback a2;
        this.f = i;
        ConcurrentHashMap<String, b> concurrentHashMap = this.h;
        if (concurrentHashMap == null) {
            return;
        }
        for (b bVar : concurrentHashMap.values()) {
            if (bVar != null && (a2 = bVar.a()) != null) {
                a2.updateGpsStatus(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bRc_(GnssStatus gnssStatus) {
        IOriginalGpsStatusListener e2;
        ConcurrentHashMap<String, c> concurrentHashMap = this.q;
        if (concurrentHashMap == null) {
            return;
        }
        for (c cVar : concurrentHashMap.values()) {
            if (cVar != null && (e2 = cVar.e()) != null) {
                e2.updateGnssStatus(gnssStatus);
            }
        }
    }

    private void bRd_(GpsStatus gpsStatus) {
        IOriginalGpsStatusListener e2;
        ConcurrentHashMap<String, c> concurrentHashMap = this.q;
        if (concurrentHashMap == null) {
            return;
        }
        for (c cVar : concurrentHashMap.values()) {
            if (cVar != null && (e2 = cVar.e()) != null) {
                e2.updateGpsStatus(gpsStatus);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        IOriginalGpsStatusListener e2;
        ConcurrentHashMap<String, c> concurrentHashMap = this.q;
        if (concurrentHashMap == null) {
            return;
        }
        for (c cVar : concurrentHashMap.values()) {
            if (cVar != null && (e2 = cVar.e()) != null) {
                e2.updateGpsStatus(i);
            }
        }
    }

    private boolean m() {
        boolean z = this.n == null;
        Object[] objArr = new Object[2];
        objArr[0] = "mLocationHandler is ";
        objArr[1] = !z ? "not null" : Constants.NULL;
        ReleaseLogUtil.e("Track_LocationManagerUtils", objArr);
        a(this.p, this.h, this.q);
        if (z) {
            return false;
        }
        return (this.p.size() + this.h.size()) + this.q.size() == 0;
    }

    private void a(ConcurrentHashMap<String, e> concurrentHashMap, ConcurrentHashMap<String, b> concurrentHashMap2, ConcurrentHashMap<String, c> concurrentHashMap3) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (Map.Entry<String, e> entry : concurrentHashMap.entrySet()) {
            sb.append("{ keyName = ");
            sb.append(entry.getKey());
            sb.append(", ");
            sb.append(entry.getValue().toString());
            sb.append(" }");
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", "mLocationsCallbackMapList = {", sb.toString(), "}");
        for (Map.Entry<String, b> entry2 : concurrentHashMap2.entrySet()) {
            sb2.append("{ keyName = ");
            sb2.append(entry2.getKey());
            sb2.append(", ");
            sb2.append(entry2.getValue().toString());
            sb2.append(" }");
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", "mGpsStatusCallbackMapList = {", sb2.toString(), "}");
        for (Map.Entry<String, c> entry3 : concurrentHashMap3.entrySet()) {
            sb3.append("{ keyName = ");
            sb3.append(entry3.getKey());
            sb3.append(", ");
            sb3.append(entry3.getValue().toString());
            sb3.append("}");
        }
        ReleaseLogUtil.e("Track_LocationManagerUtils", "mOriginalGpsStatusListenerMapList = {", sb3.toString(), " }");
    }

    public int c() {
        if (e()) {
            return this.f;
        }
        return 3;
    }

    public boolean bRg_(String str, String str2, Bundle bundle) {
        if (!i()) {
            ReleaseLogUtil.e("Track_LocationManagerUtils", "checkSelfPermission false");
            return false;
        }
        if (this.s == null && this.b != null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "mMyLocationManager is null");
            if (this.b.getSystemService("location") instanceof LocationManager) {
                this.s = (LocationManager) this.b.getSystemService("location");
            } else {
                ReleaseLogUtil.d("Track_LocationManagerUtils", "sendExtraCommand  get PartLocation Service failed");
                return false;
            }
        }
        LocationManager locationManager = this.s;
        if (locationManager == null) {
            ReleaseLogUtil.d("Track_LocationManagerUtils", "sendExtraCommand  get PartLocation Service failed");
            return false;
        }
        return locationManager.sendExtraCommand(str, str2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        ReleaseLogUtil.e("Track_LocationManagerUtils", "startPrintKeyName");
        if (this.v == null) {
            this.v = new Runnable() { // from class: kto.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        kto.this.o();
                    } catch (Exception e2) {
                        ReleaseLogUtil.c("Track_LocationManagerUtils", "startPrintKeyName exception is  ", ExceptionUtils.d(e2));
                    }
                    if (kto.this.n != null) {
                        kto.this.n.postDelayed(this, 1800000L);
                    }
                }
            };
        }
        d dVar = this.n;
        if (dVar != null) {
            dVar.postDelayed(this.v, 1800000L);
            ReleaseLogUtil.e("Track_LocationManagerUtils", "startPrintKeyName success");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("Track_LocationManagerUtils", "mLocationsCallbackMapList.size() is ", Integer.valueOf(this.p.size()), ", mGpsStatusCallbackMapList.size() is ", Integer.valueOf(this.h.size()), ", mOriginalGpsStatusListenerMapList.size() is  ", Integer.valueOf(this.q.size()));
        if (this.p.size() > 0) {
            this.p.forEach(1L, new BiConsumer<String, e>() { // from class: kto.5
                @Override // java.util.function.BiConsumer
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void accept(String str, e eVar) {
                    LogUtil.a("Track_LocationManagerUtils", "mLocationsCallbackMapList key is ", str, ", and mTime is ", Long.valueOf(eVar != null ? eVar.d() : 0L));
                }
            });
        }
        if (this.h.size() > 0) {
            this.h.forEach(1L, new BiConsumer<String, b>() { // from class: kto.1
                @Override // java.util.function.BiConsumer
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void accept(String str, b bVar) {
                    LogUtil.a("Track_LocationManagerUtils", "mGpsStatusCallbackMapList key is ", str, ", and mTime is ", Long.valueOf(bVar != null ? bVar.b() : 0L));
                }
            });
        }
        if (this.q.size() > 0) {
            this.q.forEach(1L, new BiConsumer<String, c>() { // from class: kto.7
                @Override // java.util.function.BiConsumer
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void accept(String str, c cVar) {
                    LogUtil.a("Track_LocationManagerUtils", "mOriginalGpsStatusListenerMapList key is ", str, ",mTime is ", Long.valueOf(cVar != null ? cVar.b() : 0L));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        d dVar;
        ReleaseLogUtil.e("Track_LocationManagerUtils", "stopPrintKey");
        Runnable runnable = this.v;
        if (runnable == null || (dVar = this.n) == null) {
            return;
        }
        dVar.removeCallbacks(runnable);
        ReleaseLogUtil.e("Track_LocationManagerUtils", "stopPrintKey success");
    }

    static class e {
        private long b;
        private ILoactionCallback e;

        e(long j, ILoactionCallback iLoactionCallback) {
            this.b = j;
            this.e = iLoactionCallback;
        }

        public long d() {
            return this.b;
        }

        public ILoactionCallback b() {
            return this.e;
        }

        public String toString() {
            return "registerTime = " + this.b;
        }
    }

    static class b {
        private long b;
        private IGpsStatusCallback e;

        b(long j, IGpsStatusCallback iGpsStatusCallback) {
            this.b = j;
            this.e = iGpsStatusCallback;
        }

        public long b() {
            return this.b;
        }

        public IGpsStatusCallback a() {
            return this.e;
        }

        public String toString() {
            return "registerTime = " + this.b;
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private long f14589a;
        private IOriginalGpsStatusListener d;

        c(long j, IOriginalGpsStatusListener iOriginalGpsStatusListener) {
            this.f14589a = j;
            this.d = iOriginalGpsStatusListener;
        }

        public long b() {
            return this.f14589a;
        }

        public IOriginalGpsStatusListener e() {
            return this.d;
        }

        public String toString() {
            return "registerTime = " + this.f14589a;
        }
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                String e = SharedPreferenceManager.e("privacy_center", "geo_location", "");
                if (!TextUtils.isEmpty(e)) {
                    kto.this.z.clear();
                    for (String str : e.split("_")[0].split(";")) {
                        String[] split = str.split(",");
                        ktn ktnVar = new ktn(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
                        if (kto.this.z.size() >= 10) {
                            kto.this.z.remove(0);
                        }
                        kto.this.z.add(ktnVar);
                    }
                }
                ReleaseLogUtil.e("Track_LocationManagerUtils", "begin to request location");
                if (kto.this.j) {
                    kto.this.k();
                } else {
                    kto.this.l();
                }
                kto.this.r();
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                ReleaseLogUtil.e("Track_LocationManagerUtils", "change location request time");
                kto ktoVar = kto.this;
                ktoVar.a(ktoVar.x);
                return;
            }
            ReleaseLogUtil.e("Track_LocationManagerUtils", "cancel requesting location");
            StringBuilder sb = new StringBuilder(16);
            if (!kto.this.z.isEmpty()) {
                for (ktn ktnVar2 : kto.this.z) {
                    sb.append(ktnVar2.b());
                    sb.append(",");
                    sb.append(ktnVar2.c());
                    sb.append(",");
                    sb.append(ktnVar2.e());
                    sb.append(";");
                }
                SharedPreferenceManager.c("privacy_center", "geo_location", sb.substring(0, sb.length() - 1) + "_" + System.currentTimeMillis());
            }
            if (kto.this.j) {
                kto.this.v();
            } else {
                kto.this.y();
            }
            kto.this.i = true;
            kto.this.m = false;
            kto.this.x = 2000L;
            kto.this.l = false;
            kto.this.u();
        }
    }

    class a implements LocationListener {
        private long b;
        private int c;
        private boolean e;

        private a() {
            this.e = false;
            this.b = 0L;
            this.c = 0;
        }

        /* synthetic */ a(kto ktoVar, AnonymousClass4 anonymousClass4) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            ktn ktnVar = new ktn(location.getLongitude(), location.getLatitude(), location.getAltitude());
            if (kto.this.z.size() >= 10) {
                kto.this.z.remove(0);
            }
            kto.this.z.add(ktnVar);
            kto.this.bRb_(location);
            kto.this.o = location;
            kto.this.k = System.currentTimeMillis();
            if (location != null && !this.e && location.getProvider().equals(GeocodeSearch.GPS) && location.getAccuracy() <= 35.0f) {
                ReleaseLogUtil.e("Track_LocationManagerUtils", " stop network location!");
                kto.this.p();
                this.e = true;
                kto.this.m = true;
            }
            if (location == null) {
                ReleaseLogUtil.d("Track_LocationManagerUtils", "MyLocationListener onLocationChanged location null");
            } else {
                b();
            }
        }

        private void b() {
            long currentTimeMillis = System.currentTimeMillis();
            int i = this.c;
            if (i == 0) {
                this.b = currentTimeMillis;
            }
            int i2 = i + 1;
            this.c = i2;
            if (currentTimeMillis - this.b >= 60000) {
                ReleaseLogUtil.e("Track_LocationManagerUtils", "gpsLocationSizePrint onLocationChanged ", Integer.valueOf(i2));
                this.c = 0;
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            LogUtil.a("Track_LocationManagerUtils", "MyLocationListener onProviderDisabled provider=", str);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            LogUtil.a("Track_LocationManagerUtils", "MyLocationListener onProviderEnabled provider=", str);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            ReleaseLogUtil.e("Track_LocationManagerUtils", "MyLocationListener onStatusChanged ", Integer.valueOf(i));
        }
    }
}
