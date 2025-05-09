package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.openalliance.ad.beans.inner.LocationSwitches;
import com.huawei.openalliance.ad.beans.metadata.Location;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.utils.bh;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class bi {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7634a = new byte[0];
    private static LocationManager b = null;
    private static String c = null;
    private static Location d = null;
    private static long e = -1;
    private static long f = 1800000;
    private static volatile boolean g = false;

    private static boolean a() {
        return true;
    }

    private static boolean j(Context context) {
        if (context == null) {
            return false;
        }
        if (!a()) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!cd.a(context, (String) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v8 */
    private static LocationSwitches i(Context context) {
        ?? r6;
        boolean l = com.huawei.openalliance.ad.bz.a(context).l();
        boolean f2 = x.f(context);
        boolean z = false;
        try {
            r6 = j(context);
        } catch (Throwable th) {
            ho.d("LocationUtils", "loc_tag hasLocationPermission = " + th.getClass().getSimpleName());
            r6 = 0;
        }
        if (ho.a()) {
            ho.a("LocationUtils", "loc_tag isBaseLocationSwitch = %s", Boolean.valueOf(l));
            ho.a("LocationUtils", "loc_tag isGpsSwitchOpen = %s", Boolean.valueOf(f2));
            ho.a("LocationUtils", "loc_tag hasLocationPermission = %s", Boolean.valueOf((boolean) r6));
        }
        LocationSwitches locationSwitches = new LocationSwitches();
        locationSwitches.a(l ? 1 : 0);
        locationSwitches.b(f2 ? 1 : 0);
        locationSwitches.c(r6);
        if (l && f2 && r6 != 0) {
            z = true;
        }
        locationSwitches.b(z);
        return locationSwitches;
    }

    private static boolean h(Context context) {
        boolean z;
        try {
        } catch (Throwable unused) {
            ho.d("LocationUtils", "loc_tag check location sdk available error");
        }
        if (Class.forName("com.huawei.hms.location.LocationServices") != null) {
            if (Class.forName("com.huawei.hms.location.FusedLocationProviderClient") != null) {
                z = true;
                return z && i.a(context, i.e(context));
            }
        }
        z = false;
        if (z) {
            return false;
        }
    }

    private static void g(final Context context) {
        ho.a("LocationUtils", "loc_tag getLocationByKit");
        try {
            new bh(context, new bh.a() { // from class: com.huawei.openalliance.ad.utils.bi.5
                @Override // com.huawei.openalliance.ad.utils.bh.a
                public void a(android.location.Location location) {
                    try {
                        bi.a(context, location);
                    } catch (Throwable th) {
                        ho.b("LocationUtils", "onLocationAcquired ex: %s", th.getClass().getSimpleName());
                    }
                }

                @Override // com.huawei.openalliance.ad.utils.bh.a
                public void a() {
                    try {
                        bi.b(context, 2);
                    } catch (Throwable th) {
                        ho.b("LocationUtils", "onLocationAcquireFailed ex: %s", th.getClass().getSimpleName());
                    }
                }
            }).a();
        } catch (Throwable th) {
            ho.d("LocationUtils", "loc_tag getLocationByKit, exception = " + th.getClass().getSimpleName());
        }
    }

    private static void f(final Context context) {
        if (c(context)) {
            e = System.currentTimeMillis();
            ho.a("LocationUtils", "update lastRefreshTime");
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bi.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        bi.d(context);
                    } catch (Throwable th) {
                        ho.d("LocationUtils", "loc_tag asyncLocation exception: " + th.getClass().getSimpleName());
                    }
                }
            });
        }
    }

    private static void e(Context context) {
        if (d == null) {
            if (ho.a()) {
                ho.a("LocationUtils", "restoreLastKnownLocation");
            }
            Location bN = fh.b(context).bN();
            if (bN != null) {
                d = bN;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context) {
        if (h(context)) {
            ho.a("LocationUtils", "loc_tag asyncLocation has location-sdk");
            try {
                g(context);
                return;
            } catch (Throwable th) {
                ho.c("LocationUtils", "loc_tag get location by kit error, " + th.getClass().getSimpleName());
            }
        } else {
            ho.a("LocationUtils", "loc_tag asyncLocation has not location-sdk");
        }
        b(context, 2);
    }

    private static boolean c(Context context) {
        long abs = Math.abs(System.currentTimeMillis() - e);
        f = fh.b(context).aw();
        ho.a("LocationUtils", "loc_tag isRefreshOk intervalRefreshTime = " + f + ", intervalTime = " + abs);
        if (abs >= f) {
            return true;
        }
        ho.a("LocationUtils", "loc_tag isRefreshOk = false, too frequently (no ok)");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(LocationListener locationListener) {
        if (g || b == null || locationListener == null) {
            return;
        }
        ho.b("LocationUtils", "loc_tag remove native location updates");
        try {
            b.removeUpdates(locationListener);
        } catch (Throwable th) {
            ho.b("LocationUtils", "loc_tag remove native location updates ex: %s", th.getClass().getSimpleName());
        }
        g = true;
    }

    public static void b(final Context context, RequestOptions requestOptions) {
        if (a(context, requestOptions) && c(context)) {
            m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bi.1
                @Override // java.lang.Runnable
                public void run() {
                    if (bi.a(context).d()) {
                        bi.b(context, 1);
                    } else {
                        ho.b("LocationUtils", "loc_tag sendAsyncLocationByNative failed because switch is off");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, int i) {
        ho.a("LocationUtils", "loc_tag getLocationByNative");
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        b = locationManager;
        if (locationManager == null) {
            ho.d("LocationUtils", "loc_tag getLocationByNative, nativeLocationManager is null, return");
            return;
        }
        List<String> providers = locationManager.getProviders(true);
        String str = HAWebViewInterface.NETWORK;
        if (!providers.contains(HAWebViewInterface.NETWORK)) {
            str = GeocodeSearch.GPS;
            if (!providers.contains(GeocodeSearch.GPS)) {
                ho.c("LocationUtils", "loc_tag nativeLocationProvider wrong, return");
                return;
            }
        }
        c = str;
        if (ho.a()) {
            ho.a("LocationUtils", "loc_tag native location provider is: %s", c);
        }
        try {
            String str2 = c;
            if (str2 != null) {
                if (1 == i) {
                    android.location.Location lastKnownLocation = b.getLastKnownLocation(str2);
                    if (lastKnownLocation == null) {
                        ho.c("LocationUtils", "loc_tag getLocationByNative, but location is null");
                        return;
                    } else {
                        ho.a("LocationUtils", "loc_tag getLocationByNative getLastKnownLocation lat =  %s, lon = %s", dl.a(String.valueOf(lastKnownLocation.getLatitude())), dl.a(String.valueOf(lastKnownLocation.getLongitude())));
                        a(context, lastKnownLocation);
                        return;
                    }
                }
                if (2 != i) {
                    ho.a("LocationUtils", "loc_tag requestLocationByNative not correct type");
                    return;
                }
                ho.b("LocationUtils", "loc_tag getLocationByNative requestLocationUpdates");
                g = false;
                final LocationListener locationListener = new LocationListener() { // from class: com.huawei.openalliance.ad.utils.bi.3
                    @Override // android.location.LocationListener
                    public void onStatusChanged(String str3, int i2, Bundle bundle) {
                        ho.a("LocationUtils", "loc_tag getLocationByNative onStatusChanged");
                        bi.b(this);
                    }

                    @Override // android.location.LocationListener
                    public void onProviderEnabled(String str3) {
                        ho.a("LocationUtils", "loc_tag getLocationByNative onProviderEnabled");
                        bi.b(this);
                    }

                    @Override // android.location.LocationListener
                    public void onProviderDisabled(String str3) {
                        ho.a("LocationUtils", "loc_tag getLocationByNative onProviderDisabled");
                        bi.b(this);
                    }

                    @Override // android.location.LocationListener
                    public void onLocationChanged(android.location.Location location) {
                        try {
                            if (location != null) {
                                ho.a("LocationUtils", "loc_tag getLocationByNative Listener lat = %s, lon = %s", dl.a(String.valueOf(location.getLatitude())), dl.a(String.valueOf(location.getLongitude())));
                                bi.a(context, location);
                            } else {
                                ho.c("LocationUtils", "loc_tag getLocationByNative Listener, but location is null");
                            }
                        } catch (Throwable th) {
                            ho.b("LocationUtils", "onLocationChanged ex: %s", th.getClass().getSimpleName());
                        }
                        bi.b(this);
                    }
                };
                b.requestSingleUpdate(c, locationListener, Looper.getMainLooper());
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bi.4
                    @Override // java.lang.Runnable
                    public void run() {
                        bi.b(locationListener);
                    }
                }, OpAnalyticsConstants.H5_LOADING_DELAY);
            }
        } catch (Throwable th) {
            ho.d("LocationUtils", "loc_tag getLocationByNative, exception = " + th.getClass().getSimpleName());
        }
    }

    public static boolean a(Context context, RequestOptions requestOptions) {
        Boolean d2;
        if (requestOptions == null || requestOptions.d() == null) {
            RequestOptions requestConfiguration = HiAd.getInstance(context).getRequestConfiguration();
            if (requestConfiguration == null || requestConfiguration.d() == null) {
                return true;
            }
            d2 = requestConfiguration.d();
        } else {
            d2 = requestOptions.d();
        }
        return d2.booleanValue();
    }

    public static Location a(Context context, RequestOptions requestOptions, Location location) {
        Location a2;
        boolean a3 = a(context, requestOptions);
        ho.b("LocationUtils", "loc_tag isMediaAllow: %s", Boolean.valueOf(a3));
        LocationSwitches a4 = a(context);
        Location location2 = null;
        if (a3 && a4.d()) {
            if (location == null) {
                f(context);
                Location location3 = d;
                if (location3 != null) {
                    a2 = location3.a();
                }
                e(context);
            } else {
                a2 = location.a();
                a2.a(Long.valueOf(System.currentTimeMillis()));
                a2.a(1);
            }
            location2 = a2;
            e(context);
        } else {
            ho.b("LocationUtils", "loc_tag isLocationAvailable = false, return null");
        }
        if (location2 == null) {
            location2 = new Location();
        }
        location2.a(a4);
        return location2;
    }

    public static Location a(Context context, android.location.Location location) {
        if (location == null) {
            return null;
        }
        synchronized (f7634a) {
            if (d == null) {
                d = new Location();
            }
            d.a(Double.valueOf(location.getLongitude()));
            d.b(Double.valueOf(location.getLatitude()));
            d.a(Long.valueOf(System.currentTimeMillis()));
            fh.b(context).a(d);
        }
        return d;
    }

    public static LocationSwitches a(Context context) {
        boolean z;
        LocationSwitches i = i(context);
        if (i.e()) {
            z = fh.b(context).bc();
            ho.a("LocationUtils", "loc_tag isSdkServerLocationSwitch = %s", Boolean.valueOf(z));
        } else {
            z = false;
        }
        i.a(z);
        return i;
    }
}
