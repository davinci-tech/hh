package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.DPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.autonavi.aps.amapapi.utils.b;
import com.autonavi.aps.amapapi.utils.c;
import com.autonavi.aps.amapapi.utils.d;
import com.autonavi.aps.amapapi.utils.e;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class h {
    static AMapLocation j = null;
    static long k = 0;
    static Object l = new Object();
    static long q = 0;
    static boolean t = false;
    static boolean u = false;
    public static volatile AMapLocation y;
    private GnssStatus.Callback F;

    /* renamed from: a, reason: collision with root package name */
    Handler f1097a;
    LocationManager b;
    AMapLocationClientOption c;
    com.autonavi.aps.amapapi.filters.a f;
    private Context z;
    private long A = 0;
    long d = 0;
    boolean e = false;
    private int B = 0;
    int g = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
    int h = 80;
    AMapLocation i = null;
    long m = 0;
    float n = 0.0f;
    Object o = new Object();
    Object p = new Object();
    private int C = 0;
    private GpsStatus D = null;
    private GpsStatus.Listener E = null;
    AMapLocationClientOption.GeoLanguage r = AMapLocationClientOption.GeoLanguage.DEFAULT;
    boolean s = true;
    long v = 0;
    int w = 0;
    LocationListener x = null;
    private String G = null;
    private boolean H = false;
    private int I = 0;
    private boolean J = false;

    /* JADX INFO: Access modifiers changed from: private */
    public static void j() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
    }

    public h(Context context, Handler handler) {
        this.f = null;
        this.z = context;
        this.f1097a = handler;
        try {
            this.b = (LocationManager) context.getSystemService("location");
        } catch (Throwable th) {
            b.a(th, "GpsLocation", "<init>");
        }
        this.f = new com.autonavi.aps.amapapi.filters.a();
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.c = aMapLocationClientOption;
        if (aMapLocationClientOption == null) {
            this.c = new AMapLocationClientOption();
        }
        try {
            q = com.autonavi.aps.amapapi.utils.h.a(this.z, "pref", "lagt", q);
        } catch (Throwable unused) {
        }
        i();
    }

    public final void b(AMapLocationClientOption aMapLocationClientOption) {
        Handler handler;
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        this.c = aMapLocationClientOption;
        if (aMapLocationClientOption.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors && (handler = this.f1097a) != null) {
            handler.removeMessages(8);
        }
        if (this.r != this.c.getGeoLanguage()) {
            synchronized (this.o) {
                y = null;
            }
        }
        this.r = this.c.getGeoLanguage();
    }

    public final void a() {
        LocationManager locationManager = this.b;
        if (locationManager == null) {
            return;
        }
        try {
            LocationListener locationListener = this.x;
            if (locationListener != null) {
                locationManager.removeUpdates(locationListener);
                ((a) this.x).a();
                this.x = null;
            }
        } catch (Throwable unused) {
        }
        try {
            GpsStatus.Listener listener = this.E;
            if (listener != null) {
                this.b.removeGpsStatusListener(listener);
            }
        } catch (Throwable unused2) {
        }
        try {
            GnssStatus.Callback callback = this.F;
            if (callback != null) {
                this.b.unregisterGnssStatusCallback(callback);
            }
        } catch (Throwable unused3) {
        }
        try {
            Handler handler = this.f1097a;
            if (handler != null) {
                handler.removeMessages(8);
            }
        } catch (Throwable unused4) {
        }
        this.C = 0;
        this.A = 0L;
        this.v = 0L;
        this.d = 0L;
        this.B = 0;
        this.w = 0;
        this.f.a();
        this.i = null;
        this.m = 0L;
        this.n = 0.0f;
        this.G = null;
        this.J = false;
    }

    private void i() {
        if (this.b == null) {
            return;
        }
        try {
            n();
            this.s = true;
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = this.z.getMainLooper();
            }
            Looper looper = myLooper;
            this.A = i.b();
            if (a(this.b)) {
                try {
                    if (i.a() - q >= 259200000) {
                        if (i.c(this.z, "WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19MT0NBVElPTl9FWFRSQV9DT01NQU5EUw==")) {
                            this.b.sendExtraCommand(GeocodeSearch.GPS, "force_xtra_injection", null);
                            q = i.a();
                            SharedPreferences.Editor a2 = com.autonavi.aps.amapapi.utils.h.a(this.z, "pref");
                            com.autonavi.aps.amapapi.utils.h.a(a2, "lagt", q);
                            com.autonavi.aps.amapapi.utils.h.a(a2);
                        } else {
                            b.a(new Exception("n_alec"), "OPENSDK_GL", "rlu_n_alec");
                        }
                    }
                } catch (Throwable th) {
                    th.getMessage();
                }
                if (this.x == null) {
                    this.x = new a(this);
                }
                this.b.requestLocationUpdates(GeocodeSearch.GPS, this.c.getInterval(), this.c.getDeviceModeDistanceFilter(), this.x, looper);
                GnssStatus.Callback callback = new GnssStatus.Callback() { // from class: com.amap.api.col.3sl.h.1
                    @Override // android.location.GnssStatus.Callback
                    public final void onStarted() {
                        h.j();
                    }

                    @Override // android.location.GnssStatus.Callback
                    public final void onStopped() {
                        h.this.k();
                    }

                    @Override // android.location.GnssStatus.Callback
                    public final void onFirstFix(int i) {
                        h.l();
                    }

                    @Override // android.location.GnssStatus.Callback
                    public final void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                        h.this.a(gnssStatus);
                    }
                };
                this.F = callback;
                this.b.registerGnssStatusCallback(callback);
                a(8, 14, "no enough satellites#1401", this.c.getHttpTimeOut());
                return;
            }
            a(8, 14, "no gps provider#1402", 0L);
        } catch (SecurityException e) {
            this.s = false;
            g.a((String) null, 2121);
            a(2, 12, e.getMessage() + "#1201", 0L);
        } catch (Throwable th2) {
            th2.getMessage();
            b.a(th2, "GpsLocation", "requestLocationUpdates part2");
        }
    }

    /* renamed from: com.amap.api.col.3sl.h$2, reason: invalid class name */
    final class AnonymousClass2 implements GpsStatus.Listener {
        AnonymousClass2() {
        }

        @Override // android.location.GpsStatus.Listener
        public final void onGpsStatusChanged(int i) {
            try {
                if (h.this.b == null) {
                    return;
                }
                h hVar = h.this;
                hVar.D = hVar.b.getGpsStatus(h.this.D);
                if (i == 1) {
                    h.j();
                    return;
                }
                if (i == 2) {
                    h.this.k();
                } else if (i == 3) {
                    h.l();
                } else {
                    if (i != 4) {
                        return;
                    }
                    h.this.m();
                }
            } catch (Throwable th) {
                th.getMessage();
                b.a(th, "GpsLocation", "onGpsStatusChanged");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.C = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Iterable<GpsSatellite> satellites;
        int i = 0;
        try {
            GpsStatus gpsStatus = this.D;
            if (gpsStatus != null && (satellites = gpsStatus.getSatellites()) != null) {
                Iterator<GpsSatellite> it = satellites.iterator();
                int maxSatellites = this.D.getMaxSatellites();
                while (it.hasNext() && i < maxSatellites) {
                    if (it.next().usedInFix()) {
                        i++;
                    }
                }
            }
        } catch (Throwable th) {
            b.a(th, "GpsLocation", "GPS_EVENT_SATELLITE_STATUS");
        }
        this.C = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(GnssStatus gnssStatus) {
        int i = 0;
        if (gnssStatus != null) {
            try {
                int satelliteCount = gnssStatus.getSatelliteCount();
                int i2 = 0;
                while (i < satelliteCount) {
                    try {
                        if (gnssStatus.usedInFix(i)) {
                            i2++;
                        }
                        i++;
                    } catch (Throwable th) {
                        th = th;
                        i = i2;
                        b.a(th, "GpsLocation_Gnss", "GPS_EVENT_SATELLITE_STATUS");
                        this.C = i;
                    }
                }
                i = i2;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        this.C = i;
    }

    private static boolean a(LocationManager locationManager) {
        try {
            if (t) {
                return u;
            }
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders != null && allProviders.size() > 0) {
                u = allProviders.contains(GeocodeSearch.GPS);
            } else {
                u = false;
            }
            t = true;
            return u;
        } catch (Throwable th) {
            th.getMessage();
            return u;
        }
    }

    private void a(int i, int i2, String str, long j2) {
        try {
            if (this.f1097a == null || this.c.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                return;
            }
            Message obtain = Message.obtain();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider(GeocodeSearch.GPS);
            aMapLocation.setErrorCode(i2);
            aMapLocation.setLocationDetail(str);
            aMapLocation.setLocationType(1);
            obtain.obj = aMapLocation;
            obtain.what = i;
            this.f1097a.sendMessageDelayed(obtain, j2);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Location location) {
        Handler handler = this.f1097a;
        if (handler != null) {
            handler.removeMessages(8);
        }
        if (location == null) {
            return;
        }
        try {
            AMapLocation aMapLocation = new AMapLocation(location);
            if (i.a(aMapLocation)) {
                aMapLocation.setProvider(GeocodeSearch.GPS);
                aMapLocation.setLocationType(1);
                if (!this.e && i.a(aMapLocation)) {
                    g.a(this.z, i.b() - this.A, b.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    this.e = true;
                }
                if (i.a(aMapLocation, this.C)) {
                    aMapLocation.setMock(true);
                    aMapLocation.setTrustedLevel(4);
                    if (!this.c.isMockEnable()) {
                        int i = this.w;
                        if (i > 3) {
                            g.a((String) null, 2152);
                            aMapLocation.setErrorCode(15);
                            aMapLocation.setLocationDetail("GpsLocation has been mocked!#1501");
                            aMapLocation.setLatitude(0.0d);
                            aMapLocation.setLongitude(0.0d);
                            aMapLocation.setAltitude(0.0d);
                            aMapLocation.setSpeed(0.0f);
                            aMapLocation.setAccuracy(0.0f);
                            aMapLocation.setBearing(0.0f);
                            aMapLocation.setExtras(null);
                            c(aMapLocation);
                            return;
                        }
                        this.w = i + 1;
                        return;
                    }
                } else {
                    this.w = 0;
                }
                aMapLocation.setSatellites(this.C);
                e(aMapLocation);
                f(aMapLocation);
                h(aMapLocation);
                AMapLocation g = g(aMapLocation);
                a(g);
                b(g);
                synchronized (this.o) {
                    a(g, y);
                }
                try {
                    if (i.a(g)) {
                        if (this.i != null) {
                            this.m = location.getTime() - this.i.getTime();
                            this.n = i.a(this.i, g);
                        }
                        synchronized (this.p) {
                            this.i = g.m77clone();
                        }
                        this.G = null;
                        this.H = false;
                        this.I = 0;
                    }
                } catch (Throwable th) {
                    b.a(th, "GpsLocation", "onLocationChangedLast");
                }
                c(g);
            }
        } catch (Throwable th2) {
            b.a(th2, "GpsLocation", "onLocationChanged");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            if (GeocodeSearch.GPS.equalsIgnoreCase(str)) {
                this.d = 0L;
                this.C = 0;
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0) {
            try {
                this.d = 0L;
                this.C = 0;
            } catch (Throwable unused) {
            }
        }
    }

    static final class a implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        private h f1100a;

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        a(h hVar) {
            this.f1100a = hVar;
        }

        final void a() {
            this.f1100a = null;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                Thread.currentThread().getId();
                h hVar = this.f1100a;
                if (hVar != null) {
                    hVar.a(location);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                h hVar = this.f1100a;
                if (hVar != null) {
                    hVar.a(str);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
            try {
                h hVar = this.f1100a;
                if (hVar != null) {
                    hVar.a(i);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void a(AMapLocation aMapLocation) {
        if (i.a(aMapLocation)) {
            this.d = i.b();
            synchronized (l) {
                k = i.b();
                j = aMapLocation.m77clone();
            }
            this.B++;
        }
    }

    private void b(AMapLocation aMapLocation) {
        if (i.a(aMapLocation) && this.f1097a != null) {
            long b = i.b();
            if (this.c.getInterval() <= 8000 || b - this.v > this.c.getInterval() - 8000) {
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", aMapLocation.getLatitude());
                bundle.putDouble("lon", aMapLocation.getLongitude());
                bundle.putFloat("radius", aMapLocation.getAccuracy());
                bundle.putLong("time", aMapLocation.getTime());
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = 5;
                synchronized (this.o) {
                    if (y == null) {
                        this.f1097a.sendMessage(obtain);
                    } else if (i.a(aMapLocation, y) > this.h) {
                        this.f1097a.sendMessage(obtain);
                    }
                }
            }
        }
    }

    private void c(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() != 15 || AMapLocationClientOption.AMapLocationMode.Device_Sensors.equals(this.c.getLocationMode())) {
            if (this.c.getLocationMode().equals(AMapLocationClientOption.AMapLocationMode.Device_Sensors) && this.c.getDeviceModeDistanceFilter() > 0.0f) {
                d(aMapLocation);
            } else if (i.b() - this.v >= this.c.getInterval() - 200) {
                this.v = i.b();
                d(aMapLocation);
            }
        }
    }

    private void d(AMapLocation aMapLocation) {
        if (this.f1097a != null) {
            Message obtain = Message.obtain();
            obtain.obj = aMapLocation;
            obtain.what = 2;
            this.f1097a.sendMessage(obtain);
        }
    }

    public final boolean b() {
        return i.b() - this.d <= 2800;
    }

    private void e(AMapLocation aMapLocation) {
        try {
            if (b.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) && this.c.isOffset()) {
                DPoint a2 = d.a(this.z, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                aMapLocation.setLatitude(a2.getLatitude());
                aMapLocation.setLongitude(a2.getLongitude());
                aMapLocation.setOffset(this.c.isOffset());
                aMapLocation.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                return;
            }
            aMapLocation.setOffset(false);
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
        } catch (Throwable unused) {
            aMapLocation.setOffset(false);
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
        }
    }

    private void f(AMapLocation aMapLocation) {
        try {
            int i = this.C;
            if (i >= 4) {
                aMapLocation.setGpsAccuracyStatus(1);
            } else if (i == 0) {
                aMapLocation.setGpsAccuracyStatus(-1);
            } else {
                aMapLocation.setGpsAccuracyStatus(0);
            }
        } catch (Throwable unused) {
        }
    }

    private AMapLocation g(AMapLocation aMapLocation) {
        if (!i.a(aMapLocation) || this.B < 3) {
            return aMapLocation;
        }
        if (aMapLocation.getAccuracy() < 0.0f || aMapLocation.getAccuracy() == Float.MAX_VALUE) {
            aMapLocation.setAccuracy(0.0f);
        }
        if (aMapLocation.getSpeed() < 0.0f || aMapLocation.getSpeed() == Float.MAX_VALUE) {
            aMapLocation.setSpeed(0.0f);
        }
        return this.f.a(aMapLocation);
    }

    public final void a(Bundle bundle) {
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                this.g = bundle.getInt("I_MAX_GEO_DIS");
                this.h = bundle.getInt("I_MIN_GEO_DIS");
                AMapLocation aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                if (TextUtils.isEmpty(aMapLocation.getAdCode())) {
                    return;
                }
                synchronized (this.o) {
                    y = aMapLocation;
                }
            } catch (Throwable th) {
                b.a(th, "GpsLocation", "setLastGeoLocation");
            }
        }
    }

    private void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (aMapLocation2 == null || !this.c.isNeedAddress() || i.a(aMapLocation, aMapLocation2) >= this.g) {
            return;
        }
        b.a(aMapLocation, aMapLocation2);
    }

    public final void c() {
        this.w = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00a4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amap.api.location.AMapLocation a(com.amap.api.location.AMapLocation r17, java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.h.a(com.amap.api.location.AMapLocation, java.lang.String):com.amap.api.location.AMapLocation");
    }

    private boolean b(String str) {
        try {
            ArrayList<String> b = i.b(str);
            ArrayList<String> b2 = i.b(this.G);
            if (b.size() >= 8 && b2.size() >= 8) {
                return i.a(this.G, str);
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public final int d() {
        LocationManager locationManager = this.b;
        if (locationManager == null || !a(locationManager)) {
            return 1;
        }
        int i = Settings.Secure.getInt(this.z.getContentResolver(), "location_mode", 0);
        if (i == 0) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        return !this.s ? 4 : 0;
    }

    public final int e() {
        return this.C;
    }

    private void n() {
        if (i.b() - k > 5000 || !i.a(j)) {
            return;
        }
        if (this.c.isMockEnable() || !j.isMock()) {
            this.d = i.b();
            c(j);
        }
    }

    private static boolean o() {
        try {
            return ((Boolean) e.a(ia.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), ia.c("UaXNOYXZpU3RhcnRlZA=="), (Object[]) null, (Class<?>[]) null)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private AMapLocation p() {
        float f;
        float f2;
        try {
            if (i.a(this.i) && com.autonavi.aps.amapapi.utils.a.j() && o()) {
                JSONObject jSONObject = new JSONObject((String) e.a(ia.c("KY29tLmFtYXAuYXBpLm5hdmkuQU1hcE5hdmk="), ia.c("UZ2V0TmF2aUxvY2F0aW9u"), (Object[]) null, (Class<?>[]) null));
                long optLong = jSONObject.optLong("time");
                if (!this.J) {
                    this.J = true;
                    g.a("useNaviLoc", "use NaviLoc");
                }
                if (i.a() - optLong <= 5500) {
                    double optDouble = jSONObject.optDouble("lat", 0.0d);
                    double optDouble2 = jSONObject.optDouble("lng", 0.0d);
                    float f3 = 0.0f;
                    try {
                        f = Float.parseFloat(jSONObject.optString("accuracy", "0"));
                    } catch (NumberFormatException unused) {
                        f = 0.0f;
                    }
                    double optDouble3 = jSONObject.optDouble("altitude", 0.0d);
                    try {
                        f2 = Float.parseFloat(jSONObject.optString("bearing", "0"));
                    } catch (NumberFormatException unused2) {
                        f2 = 0.0f;
                    }
                    try {
                        f3 = (Float.parseFloat(jSONObject.optString("speed", "0")) * 10.0f) / 36.0f;
                    } catch (NumberFormatException unused3) {
                    }
                    AMapLocation aMapLocation = new AMapLocation("lbs");
                    aMapLocation.setLocationType(9);
                    aMapLocation.setLatitude(optDouble);
                    aMapLocation.setLongitude(optDouble2);
                    aMapLocation.setAccuracy(f);
                    aMapLocation.setAltitude(optDouble3);
                    aMapLocation.setBearing(f2);
                    aMapLocation.setSpeed(f3);
                    aMapLocation.setTime(optLong);
                    aMapLocation.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                    if (i.a(aMapLocation, this.i) <= 300.0f) {
                        synchronized (this.p) {
                            this.i.setLongitude(optDouble2);
                            this.i.setLatitude(optDouble);
                            this.i.setAccuracy(f);
                            this.i.setBearing(f2);
                            this.i.setSpeed(f3);
                            this.i.setTime(optLong);
                            this.i.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                        }
                        return aMapLocation;
                    }
                }
            }
        } catch (Throwable unused4) {
        }
        return null;
    }

    public final boolean f() {
        AMapLocationClientOption aMapLocationClientOption = this.c;
        return (aMapLocationClientOption == null || aMapLocationClientOption.isOnceLocation() || i.b() - this.d <= 300000) ? false : true;
    }

    private static void h(AMapLocation aMapLocation) {
        if (i.a(aMapLocation) && com.autonavi.aps.amapapi.utils.a.r()) {
            long time = aMapLocation.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            long a2 = c.a(time, currentTimeMillis, com.autonavi.aps.amapapi.utils.a.s());
            if (a2 != time) {
                aMapLocation.setTime(a2);
                g.a(time, currentTimeMillis);
            }
        }
    }
}
