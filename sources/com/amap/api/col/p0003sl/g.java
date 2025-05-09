package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.autonavi.aps.amapapi.utils.h;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import java.util.List;

/* loaded from: classes2.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public static volatile AMapLocation f1061a = null;
    private static String b = "CoarseLocation";
    private static long q = 0;
    private static boolean r = false;
    private static boolean s = false;
    private static boolean t = false;
    private static boolean u = false;
    private com.autonavi.aps.amapapi.filters.a f;
    private Handler j;
    private Context k;
    private LocationManager n;
    private AMapLocationClientOption o;
    private long c = 0;
    private boolean d = false;
    private int e = 0;
    private int g = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
    private int h = 80;
    private int i = 0;
    private long l = 0;
    private int m = 0;
    private Object p = new Object();
    private boolean v = true;
    private AMapLocationClientOption.GeoLanguage w = AMapLocationClientOption.GeoLanguage.DEFAULT;
    private LocationListener x = null;

    public g(Context context, Handler handler) {
        this.f = null;
        this.k = context;
        this.j = handler;
        try {
            this.n = (LocationManager) context.getSystemService("location");
        } catch (Throwable th) {
            b.a(th, b, "<init>");
        }
        this.f = new com.autonavi.aps.amapapi.filters.a();
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.o = aMapLocationClientOption;
        if (aMapLocationClientOption == null) {
            this.o = new AMapLocationClientOption();
        }
        this.o.toString();
        if (!this.o.isOnceLocation()) {
            e();
        } else if (!c()) {
            d();
        } else {
            try {
                q = h.a(this.k, "pref", "lagt", q);
            } catch (Throwable unused) {
            }
            f();
        }
    }

    private boolean c() {
        boolean z = true;
        try {
            if (i.c() >= 28) {
                if (this.n == null) {
                    this.n = (LocationManager) this.k.getApplicationContext().getSystemService("location");
                }
                z = ((Boolean) e.a(this.n, "isLocationEnabled", new Object[0])).booleanValue();
            }
            if (i.c() >= 24 && i.c() < 28) {
                if (Settings.Secure.getInt(this.k.getContentResolver(), "location_mode", 0) == 0) {
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    private void d() {
        c(a(12, "定位服务没有开启，请在设置中打开定位服务开关#1206"));
    }

    private void e() {
        c(a(20, "模糊权限下不支持连续定位#2006"));
    }

    private static com.autonavi.aps.amapapi.model.a a(int i, String str) {
        com.autonavi.aps.amapapi.model.a aVar = new com.autonavi.aps.amapapi.model.a("");
        aVar.setErrorCode(i);
        aVar.setLocationDetail(str);
        return aVar;
    }

    public final void b(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        this.o = aMapLocationClientOption;
        this.o.toString();
        this.j.removeMessages(100);
        if (this.w != this.o.getGeoLanguage()) {
            synchronized (this.p) {
                f1061a = null;
            }
        }
        this.w = this.o.getGeoLanguage();
    }

    public final void a() {
        LocationManager locationManager = this.n;
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
        } catch (Throwable th) {
            th.getMessage();
        }
        try {
            Handler handler = this.j;
            if (handler != null) {
                handler.removeMessages(100);
            }
        } catch (Throwable unused) {
        }
        this.i = 0;
        this.c = 0L;
        this.l = 0L;
        this.e = 0;
        this.m = 0;
        this.f.a();
    }

    private void f() {
        if (this.n == null) {
            return;
        }
        try {
            this.v = true;
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                myLooper = this.k.getMainLooper();
            }
            this.c = i.b();
            if (b(this.n)) {
                if (this.x == null) {
                    this.x = new a(this);
                }
                this.n.requestLocationUpdates(HAWebViewInterface.NETWORK, this.o.getInterval(), this.o.getDeviceModeDistanceFilter(), this.x, myLooper);
            }
            if (a(this.n)) {
                try {
                    if (i.a() - q >= 259200000) {
                        if (i.c(this.k, "WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19MT0NBVElPTl9FWFRSQV9DT01NQU5EUw==")) {
                            this.n.sendExtraCommand(GeocodeSearch.GPS, "force_xtra_injection", null);
                            q = i.a();
                            SharedPreferences.Editor a2 = h.a(this.k, "pref");
                            h.a(a2, "lagt", q);
                            h.a(a2);
                        } else {
                            b.a(new Exception("n_alec"), "OPENSDK_CL", "rlu_n_alec");
                        }
                    }
                } catch (Throwable th) {
                    th.getMessage();
                }
                if (this.x == null) {
                    this.x = new a(this);
                }
                this.n.requestLocationUpdates(GeocodeSearch.GPS, this.o.getInterval(), this.o.getDeviceModeDistanceFilter(), this.x, myLooper);
            }
            if (s || u) {
                a(100, "系统返回定位结果超时#2002", this.o.getHttpTimeOut());
            }
            if (s || u) {
                return;
            }
            a(100, "系统定位当前不可用#2003", 0L);
        } catch (SecurityException e) {
            this.v = false;
            com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
            a(101, e.getMessage() + "#2004", 0L);
        } catch (Throwable th2) {
            th2.getMessage();
            b.a(th2, "CoarseLocation", "requestLocationUpdates part2");
        }
    }

    private static boolean a(LocationManager locationManager) {
        try {
            if (r) {
                return s;
            }
            List<String> allProviders = locationManager.getAllProviders();
            if (allProviders != null && allProviders.size() > 0) {
                s = allProviders.contains(GeocodeSearch.GPS);
            } else {
                s = false;
            }
            r = true;
            return s;
        } catch (Throwable th) {
            th.getMessage();
            return s;
        }
    }

    private static boolean b(LocationManager locationManager) {
        try {
            if (t) {
                return u;
            }
            boolean isProviderEnabled = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
            u = isProviderEnabled;
            t = true;
            return isProviderEnabled;
        } catch (Throwable th) {
            th.getMessage();
            return u;
        }
    }

    private void a(int i, String str, long j) {
        try {
            if (this.j != null) {
                Message obtain = Message.obtain();
                AMapLocation aMapLocation = new AMapLocation("");
                aMapLocation.setErrorCode(20);
                aMapLocation.setLocationDetail(str);
                aMapLocation.setLocationType(11);
                obtain.obj = aMapLocation;
                obtain.what = i;
                this.j.sendMessageDelayed(obtain, j);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Location location) {
        Handler handler = this.j;
        if (handler != null) {
            handler.removeMessages(100);
        }
        if (location == null) {
            return;
        }
        try {
            AMapLocation aMapLocation = new AMapLocation(location);
            if (i.a(aMapLocation)) {
                if (GeocodeSearch.GPS.equals(location.getProvider())) {
                    aMapLocation.setProvider("gps_coarse");
                } else {
                    aMapLocation.setProvider("network_coarse");
                }
                aMapLocation.setLocationType(11);
                if (!this.d && i.a(aMapLocation)) {
                    com.autonavi.aps.amapapi.utils.g.b(this.k, i.b() - this.c, b.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                    this.d = true;
                }
                Boolean bool = Boolean.FALSE;
                try {
                    Boolean bool2 = (Boolean) e.a(location, "isFromMockProvider", new Object[0]);
                    try {
                        "CoarseLocation | isFromMock=".concat(String.valueOf(bool2));
                    } catch (Throwable unused) {
                    }
                    bool = bool2;
                } catch (Throwable unused2) {
                }
                if (bool.booleanValue()) {
                    aMapLocation.setMock(true);
                    aMapLocation.setTrustedLevel(4);
                    if (!this.o.isMockEnable()) {
                        int i = this.m;
                        if (i > 3) {
                            com.autonavi.aps.amapapi.utils.g.a((String) null, 2152);
                            aMapLocation.setErrorCode(15);
                            aMapLocation.setLocationDetail("CoarseLocation has been mocked!#2007");
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
                        this.m = i + 1;
                        return;
                    }
                } else {
                    this.m = 0;
                }
                int b2 = b(location);
                this.i = b2;
                aMapLocation.setSatellites(b2);
                e(aMapLocation);
                g(aMapLocation);
                AMapLocation f = f(aMapLocation);
                a(f);
                b(f);
                synchronized (this.p) {
                    a(f, f1061a);
                }
                c(f);
            }
        } catch (Throwable th) {
            b.a(th, "CoarseLocation", "onLocationChanged");
        }
    }

    private static int b(Location location) {
        Bundle extras = location.getExtras();
        if (extras != null) {
            return extras.getInt("satellites");
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            this.i = 0;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0) {
            try {
                this.i = 0;
            } catch (Throwable unused) {
            }
        }
    }

    static final class a implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        private g f1062a;

        a(g gVar) {
            this.f1062a = gVar;
        }

        final void a() {
            this.f1062a = null;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                g gVar = this.f1062a;
                if (gVar != null) {
                    gVar.a(location);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
            try {
                g gVar = this.f1062a;
                if (gVar != null) {
                    gVar.g();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
            GeocodeSearch.GPS.equalsIgnoreCase(str);
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
            try {
                g gVar = this.f1062a;
                if (gVar != null) {
                    gVar.a(i);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private void a(AMapLocation aMapLocation) {
        if (i.a(aMapLocation)) {
            this.e++;
        }
    }

    private void b(AMapLocation aMapLocation) {
        if (i.a(aMapLocation) && this.j != null) {
            long b2 = i.b();
            if (this.o.getInterval() <= 8000 || b2 - this.l > this.o.getInterval() - 8000) {
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", aMapLocation.getLatitude());
                bundle.putDouble("lon", aMapLocation.getLongitude());
                bundle.putFloat("radius", aMapLocation.getAccuracy());
                bundle.putLong("time", aMapLocation.getTime());
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = 102;
                synchronized (this.p) {
                    if (f1061a == null) {
                        this.j.sendMessage(obtain);
                    } else if (i.a(aMapLocation, f1061a) > this.h) {
                        this.j.sendMessage(obtain);
                    }
                }
            }
        }
    }

    private void c(AMapLocation aMapLocation) {
        if (this.o.getLocationMode().equals(AMapLocationClientOption.AMapLocationMode.Device_Sensors) && this.o.getDeviceModeDistanceFilter() > 0.0f) {
            d(aMapLocation);
        } else if (i.b() - this.l >= this.o.getInterval() - 200) {
            this.l = i.b();
            d(aMapLocation);
        }
    }

    private void d(AMapLocation aMapLocation) {
        if (this.j != null) {
            Message obtain = Message.obtain();
            obtain.obj = aMapLocation;
            obtain.what = 101;
            this.j.sendMessage(obtain);
        }
    }

    private void e(AMapLocation aMapLocation) {
        try {
            if (b.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) && this.o.isOffset()) {
                DPoint a2 = d.a(this.k, new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                aMapLocation.setLatitude(a2.getLatitude());
                aMapLocation.setLongitude(a2.getLongitude());
                aMapLocation.setOffset(this.o.isOffset());
                aMapLocation.setCoordType(AMapLocation.COORD_TYPE_GCJ02);
                return;
            }
            aMapLocation.setOffset(false);
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
        } catch (Throwable th) {
            aMapLocation.setOffset(false);
            aMapLocation.setCoordType(AMapLocation.COORD_TYPE_WGS84);
            th.getMessage();
        }
    }

    private AMapLocation f(AMapLocation aMapLocation) {
        if (!i.a(aMapLocation) || this.e < 3) {
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
                synchronized (this.p) {
                    f1061a = aMapLocation;
                }
            } catch (Throwable th) {
                b.a(th, "CoarseLocation", "setLastGeoLocation");
            }
        }
    }

    private void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (aMapLocation2 == null || !this.o.isNeedAddress() || i.a(aMapLocation, aMapLocation2) >= this.g) {
            return;
        }
        b.a(aMapLocation, aMapLocation2);
    }

    public final int b() {
        LocationManager locationManager = this.n;
        if (locationManager == null || !a(locationManager)) {
            return 1;
        }
        int i = Settings.Secure.getInt(this.k.getContentResolver(), "location_mode", 0);
        if (i == 0) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        return !this.v ? 4 : 0;
    }

    private static void g(AMapLocation aMapLocation) {
        if (i.a(aMapLocation) && com.autonavi.aps.amapapi.utils.a.r()) {
            long time = aMapLocation.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            long a2 = c.a(time, currentTimeMillis, com.autonavi.aps.amapapi.utils.a.s());
            if (a2 != time) {
                aMapLocation.setTime(a2);
                com.autonavi.aps.amapapi.utils.g.a(time, currentTimeMillis);
            }
        }
    }
}
