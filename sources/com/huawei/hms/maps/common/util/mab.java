package com.huawei.hms.maps.common.util;

import android.app.Application;
import android.content.Context;
import android.os.RemoteException;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.maps.MapClientIdentify;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hms.maps.internal.ICoordinateConverterDelegate;
import com.huawei.hms.maps.internal.ICreator;
import com.huawei.hms.maps.internal.IDistanceCalculatorDelegate;
import com.huawei.hms.maps.model.CoordinateLatLng;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.maps.utils.MapClientUtil;
import com.huawei.hms.maps.utils.MapsAdvUtil;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
public class mab {

    /* renamed from: a, reason: collision with root package name */
    private static IDistanceCalculatorDelegate f4950a;
    private static ICoordinateConverterDelegate b;

    private static boolean b() {
        StringBuilder sb;
        int length;
        if (!MapClientUtil.getSystemProperties("ro.build.version.magic", "").equals("") || !MapClientUtil.getSystemProperties("ro.build.version.emui", "").contains("EmotionUI_10")) {
            return true;
        }
        String systemProperties = MapClientUtil.getSystemProperties("ro.huawei.build.display.id", "");
        LogM.d("MapUtil", "huaweiSubVersion is " + systemProperties);
        if ("".equals(systemProperties)) {
            systemProperties = MapClientUtil.getSystemProperties("ro.build.display.id", "");
            LogM.d("MapUtil", "emuiSubVersion is " + systemProperties);
        }
        String[] split = systemProperties.split(" ");
        boolean z = false;
        if (1 < split.length) {
            String[] split2 = split[1].split("\\.");
            if (3 < split2.length) {
                String str = split2[3];
                int indexOf = str.indexOf(Constants.LEFT_BRACKET_ONLY);
                if (-1 != indexOf) {
                    str = str.substring(0, indexOf);
                }
                try {
                    int parseInt = Integer.parseInt(split2[0]);
                    int parseInt2 = Integer.parseInt(split2[1]);
                    int parseInt3 = Integer.parseInt(split2[2]);
                    int parseInt4 = Integer.parseInt(str);
                    if (parseInt < 10 || (parseInt == 10 && parseInt2 == 0 && parseInt3 < 1 && parseInt4 < 122)) {
                        z = true;
                    }
                    return true ^ z;
                } catch (NumberFormatException e) {
                    LogM.e("MapUtil", "NumberFormatException" + e.toString());
                    return true;
                }
            }
            sb = new StringBuilder("versionStrs.length <= 3: ");
            length = split2.length;
        } else {
            sb = new StringBuilder("versions.length <= 1: ");
            length = split.length;
        }
        sb.append(length);
        LogM.e("MapUtil", sb.toString());
        return false;
    }

    public static CoordinateLatLng[] a(CoordinateLatLng[] coordinateLatLngArr) {
        if (coordinateLatLngArr == null || coordinateLatLngArr.length == 0) {
            LogM.e("MapUtil", "the input locations data is null.");
            return null;
        }
        try {
            if (MapsAdvUtil.containsMapsBasic()) {
                LogM.d("MapUtil", "rectifyCoordinate from basic.");
                if (b == null) {
                    b = new com.huawei.hms.maps.provider.inhuawei.ICoordinateConverterDelegate();
                }
                return b.rectifyCoordinate(coordinateLatLngArr);
            }
            if (b == null) {
                a(1);
            }
            ICoordinateConverterDelegate iCoordinateConverterDelegate = b;
            if (iCoordinateConverterDelegate != null) {
                return iCoordinateConverterDelegate.rectifyCoordinate(coordinateLatLngArr);
            }
            return null;
        } catch (RemoteException e) {
            LogM.i("MapUtil", "rectifyCoordinate RemoteException: " + e.toString());
            return null;
        }
    }

    private static boolean a() {
        return "cn".equalsIgnoreCase(MapClientUtil.getSystemProperties("ro.product.locale.region", "")) || "cn".equalsIgnoreCase(MapClientUtil.getSystemProperties(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP, "")) || "cn".equalsIgnoreCase(MapClientUtil.getSystemProperties(CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP_HN, ""));
    }

    private static void a(int i) {
        StringBuilder sb;
        String invocationTargetException;
        String str;
        try {
            Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
            MapClientIdentify mapClientIdentify = new MapClientIdentify();
            int isHmsAvailable = HmsUtil.isHmsAvailable(application);
            if (isHmsAvailable != 0) {
                LogM.e("MapUtil", "hmsState check failed: " + isHmsAvailable);
            }
            ICreator b2 = com.huawei.hms.maps.internal.mab.b(application);
            mapClientIdentify.regestIdentity(application, b2);
            if (b2 == null) {
                LogM.d("MapUtil", "creator is null");
                return;
            }
            Context d = com.huawei.hms.maps.internal.mab.d(application);
            if (i == 0) {
                f4950a = b2.newDistanceCalculatorDelegate(ObjectWrapper.wrap(d));
                str = "iDistanceCalculatorDelegate: ";
            } else {
                if (i != 1) {
                    return;
                }
                b = b2.newCoordinateConverterDelegate(ObjectWrapper.wrap(d));
                str = "iCoordinateConverterDelegate: ";
            }
            LogM.i("MapUtil", str);
        } catch (RemoteException e) {
            sb = new StringBuilder("RemoteException: ");
            invocationTargetException = e.toString();
            sb.append(invocationTargetException);
            LogM.e("MapUtil", sb.toString());
        } catch (ClassNotFoundException e2) {
            sb = new StringBuilder("ClassNotFoundException: ");
            invocationTargetException = e2.toString();
            sb.append(invocationTargetException);
            LogM.e("MapUtil", sb.toString());
        } catch (IllegalAccessException e3) {
            sb = new StringBuilder("IllegalAccessException: ");
            invocationTargetException = e3.toString();
            sb.append(invocationTargetException);
            LogM.e("MapUtil", sb.toString());
        } catch (NoSuchMethodException e4) {
            sb = new StringBuilder("NoSuchMethodException: ");
            invocationTargetException = e4.toString();
            sb.append(invocationTargetException);
            LogM.e("MapUtil", sb.toString());
        } catch (InvocationTargetException e5) {
            sb = new StringBuilder("InvocationTargetException: ");
            invocationTargetException = e5.toString();
            sb.append(invocationTargetException);
            LogM.e("MapUtil", sb.toString());
        }
    }

    protected static int a(Context context) {
        if (a() && !b()) {
            LogM.d("MapUtil", "isHmsMapAvailable is 2");
            return 2;
        }
        if (new AvailableAdapter(HmsUtil.getMinHmsVersion(context)).isHuaweiMobileServicesAvailable(context) != 0) {
            LogM.d("MapUtil", "isHmsMapAvailable is 1");
            return 1;
        }
        LogM.d("MapUtil", "isHmsMapAvailable is 0");
        return 0;
    }

    protected static double a(LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null) {
            try {
                if (MapsAdvUtil.containsMapsBasic()) {
                    LogM.d("MapUtil", "computeDistanceBetween from basic.");
                    if (f4950a == null) {
                        f4950a = new com.huawei.hms.maps.provider.inhuawei.IDistanceCalculatorDelegate();
                    }
                    return f4950a.computeDistanceBetween(latLng, latLng2);
                }
                if (f4950a == null) {
                    a(0);
                }
                IDistanceCalculatorDelegate iDistanceCalculatorDelegate = f4950a;
                if (iDistanceCalculatorDelegate != null) {
                    return iDistanceCalculatorDelegate.computeDistanceBetween(latLng, latLng2);
                }
            } catch (RemoteException e) {
                LogM.d("MapUtil", "RemoteException: " + e.toString());
            }
        }
        return 0.0d;
    }
}
