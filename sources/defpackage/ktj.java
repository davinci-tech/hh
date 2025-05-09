package defpackage;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.SystemClock;
import android.provider.Settings;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ktj {
    public static kth bQU_(GpsStatus gpsStatus) {
        if (gpsStatus == null) {
            LogUtil.c("Track_DealGpsUtils", "getGpsSnr gpsStatus is null");
            return null;
        }
        int maxSatellites = gpsStatus.getMaxSatellites();
        ArrayList arrayList = new ArrayList();
        if (gpsStatus.getSatellites() == null) {
            return null;
        }
        Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
        int i = 0;
        while (it.hasNext() && i <= maxSatellites) {
            arrayList.add(Float.valueOf(it.next().getSnr()));
            i++;
        }
        if (i != 0) {
            return new kth(arrayList, i);
        }
        return null;
    }

    public static kth bQT_(GnssStatus gnssStatus) {
        if (gnssStatus == null) {
            LogUtil.c("Track_DealGpsUtils", "getGpsSnr gpsStatus is null");
            return null;
        }
        int satelliteCount = gnssStatus.getSatelliteCount();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < satelliteCount; i2++) {
            if (gnssStatus.usedInFix(i2)) {
                arrayList.add(Float.valueOf(gnssStatus.getCn0DbHz(i2)));
                i++;
            }
        }
        if (i != 0) {
            return new kth(arrayList, i);
        }
        return null;
    }

    public static int b(kth kthVar) {
        if (kthVar == null) {
            return 0;
        }
        float b = b(kthVar.d());
        int b2 = kthVar.b();
        if (b >= 30.0f && b2 >= 11) {
            return 6;
        }
        if (b <= 20.0f || b2 < 9) {
            return (b <= 20.0f || b2 < 4) ? 2 : 4;
        }
        return 5;
    }

    public static float b(List<Float> list) {
        if (list == null) {
            return 0.0f;
        }
        Collections.sort(list, new Comparator<Float>() { // from class: ktj.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(Float f, Float f2) {
                return f.floatValue() > f2.floatValue() ? -1 : 0;
            }
        });
        float floatValue = list.get(0).floatValue();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - ktk.a() > OpAnalyticsConstants.H5_LOADING_DELAY) {
            ktk.e(elapsedRealtime);
        }
        return floatValue;
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            Settings.Secure.putInt(context.getContentResolver(), "location_mode", 3);
        } catch (SecurityException unused) {
            LogUtil.b("Track_DealGpsUtils", "isOpenGps: No permission or not release version");
        }
        return e(context);
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.b("Track_DealGpsUtils", "isNetworkEnable: context is null");
            return false;
        }
        Object systemService = context.getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            return false;
        }
        LocationManager locationManager = (LocationManager) systemService;
        return locationManager.isProviderEnabled(HAWebViewInterface.NETWORK) || locationManager.isProviderEnabled("GpsMockProvider");
    }

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.h("Track_DealGpsUtils", "isGpsEnabled, context is null.");
            return false;
        }
        final Object systemService = context.getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        ThreadPoolManager.d().execute(new Runnable() { // from class: ktm
            @Override // java.lang.Runnable
            public final void run() {
                ktj.d(systemService, zArr, countDownLatch);
            }
        });
        try {
            LogUtil.a("Track_DealGpsUtils", "isGpsEnabled isOnTime:", Boolean.valueOf(countDownLatch.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.h("Track_DealGpsUtils", "isGpsEnabled InterruptedException");
        }
        return zArr[0];
    }

    static /* synthetic */ void d(Object obj, boolean[] zArr, CountDownLatch countDownLatch) {
        LocationManager locationManager = (LocationManager) obj;
        if (locationManager.isProviderEnabled(GeocodeSearch.GPS)) {
            if (locationManager.isProviderEnabled(HAWebViewInterface.NETWORK)) {
                LogUtil.a("Track_DealGpsUtils", "isGpsEnabled current is gps & network provider support.");
            } else {
                LogUtil.a("Track_DealGpsUtils", "isGpsEnabled current is only gps provider support");
            }
            zArr[0] = true;
        } else if (locationManager.isProviderEnabled("GpsMockProvider")) {
            LogUtil.a("Track_DealGpsUtils", " Mock GPS provider running!!!");
            zArr[0] = true;
        } else {
            LogUtil.h("Track_DealGpsUtils", "isGpsEnabled gps disabled");
            zArr[0] = false;
        }
        countDownLatch.countDown();
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        try {
            Settings.Secure.putInt(context.getContentResolver(), "location_mode", 3);
        } catch (SecurityException unused) {
            LogUtil.b("Track_DealGpsUtils", "openNetworkLocation: No permission or not release version");
        }
    }
}
