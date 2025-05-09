package com.amap.api.maps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amap.api.col.p0003sl.hm;
import com.amap.api.col.p0003sl.hn;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.col.p0003sl.v;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.PoiPara;
import com.amap.api.maps.model.RoutePara;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class AMapUtils {
    private static final String AMAPNAVIURL = "androidamap://navi?sourceApplication=%s&lat=%f&lon=%f&dev=0&style=%d";
    private static final String AMAPPOISEARCHURL = "androidamap://arroundpoi?sourceApplication=%s&keywords=%s&dev=0";
    private static final String AMAPROUTEURL = "androidamap://route?sourceApplication=%s&slat=%f&slon=%f&sname=%s&dlat=%f&dlon=%f&dname=%s&dev=0&t=%d";
    public static final int BUS_COMFORT = 4;
    public static final int BUS_MONEY_LITTLE = 1;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_TIME_FIRST = 0;
    public static final int BUS_TRANSFER_LITTLE = 2;
    public static final int BUS_WALK_LITTLE = 3;
    private static final double DEG_TO_RAD = 0.017453292519943295d;
    private static final int DRING_ROUTE_MODEL = 2;
    public static final int DRIVING_AVOID_CONGESTION = 4;
    public static final int DRIVING_DEFAULT = 0;
    public static final int DRIVING_NO_HIGHWAY = 3;
    public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
    public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
    public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SAVE_MONEY = 1;
    public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
    public static final int DRIVING_SHORT_DISTANCE = 2;
    private static final double EARTHRADIUS = 6378137.0d;
    private static final double NF_PI = 0.01745329251994329d;
    private static final double R = 6378137.0d;
    private static final int TRANSIT_ROUTE_MODEL = 1;

    public static float calculateLineDistance(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
            } catch (AMapException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
        try {
            double d = latLng.longitude;
            double d2 = latLng.latitude;
            double d3 = latLng2.longitude;
            double d4 = latLng2.latitude;
            double d5 = d * NF_PI;
            double d6 = d2 * NF_PI;
            double d7 = d3 * NF_PI;
            double d8 = d4 * NF_PI;
            double sin = Math.sin(d5);
            double sin2 = Math.sin(d6);
            double cos = Math.cos(d5);
            double cos2 = Math.cos(d6);
            double sin3 = Math.sin(d7);
            double sin4 = Math.sin(d8);
            double cos3 = Math.cos(d7);
            double cos4 = Math.cos(d8);
            double[] dArr = {cos * cos2, cos2 * sin, sin2};
            double d9 = dArr[0] - (cos3 * cos4);
            double d10 = dArr[1] - (cos4 * sin3);
            double d11 = dArr[2] - sin4;
            return (float) (Math.asin(Math.sqrt(((d9 * d9) + (d10 * d10)) + (d11 * d11)) / 2.0d) * 1.27420015798544E7d);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public static float calculateArea(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
            } catch (AMapException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
        try {
            double sin = Math.sin((latLng.latitude * 3.141592653589793d) / 180.0d);
            double sin2 = Math.sin((latLng2.latitude * 3.141592653589793d) / 180.0d);
            double d = (latLng2.longitude - latLng.longitude) / 360.0d;
            if (d < 0.0d) {
                d += 1.0d;
            }
            return (float) ((sin - sin2) * 2.5560394669790553E14d * d);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public static float calculateArea(List<LatLng> list) {
        List<LatLng> list2 = list;
        if (list2 == null || list.size() < 3) {
            return 0.0f;
        }
        double d = 0.0d;
        int i = 0;
        for (int size = list.size(); i < size; size = size) {
            LatLng latLng = list2.get(i);
            int i2 = i + 1;
            LatLng latLng2 = list2.get(i2 % size);
            double d2 = latLng.longitude;
            double cos = Math.cos(latLng.latitude * 0.017453292519943295d);
            double d3 = latLng.latitude;
            double d4 = latLng2.longitude;
            d += (((d2 * 111319.49079327357d) * cos) * (latLng2.latitude * 111319.49079327357d)) - (((d4 * 111319.49079327357d) * Math.cos(latLng2.latitude * 0.017453292519943295d)) * (d3 * 111319.49079327357d));
            list2 = list;
            i = i2;
        }
        return Math.abs((float) (d / 2.0d));
    }

    public static void getLatestAMapApp(Context context) {
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.addFlags(276824064);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("http://wap.amap.com/"));
            new a("glaa", context).start();
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void openAMapNavi(NaviPara naviPara, Context context) throws AMapException {
        if (a(context)) {
            if (naviPara.getTargetPoint() != null) {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(a(naviPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
                return;
            }
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
        throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
    }

    public static void openAMapPoiNearbySearch(PoiPara poiPara, Context context) throws AMapException {
        if (a(context)) {
            if (poiPara.getKeywords() != null && poiPara.getKeywords().trim().length() > 0) {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(a(poiPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
                return;
            }
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
        throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
    }

    public static void openAMapDrivingRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 2);
    }

    public static void openAMapTransitRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 1);
    }

    public static void openAMapWalkingRoute(RoutePara routePara, Context context) throws AMapException {
        a(routePara, context, 4);
    }

    private static void a(RoutePara routePara, Context context, int i) throws AMapException {
        if (a(context)) {
            if (a(routePara)) {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(b(routePara, context, i)));
                intent.setPackage("com.autonavi.minimap");
                new a("oan", context).start();
                context.startActivity(intent);
                return;
            }
            throw new AMapException(AMapException.ILLEGAL_AMAP_ARGUMENT);
        }
        throw new AMapException(AMapException.AMAP_NOT_SUPPORT);
    }

    private static boolean a(RoutePara routePara) {
        return (routePara.getStartPoint() == null || routePara.getEndPoint() == null || routePara.getStartName() == null || routePara.getStartName().trim().length() <= 0 || routePara.getEndName() == null || routePara.getEndName().trim().length() <= 0) ? false : true;
    }

    /* loaded from: classes8.dex */
    static final class a extends Thread {

        /* renamed from: a, reason: collision with root package name */
        String f1412a;
        Context b;

        public a(String str, Context context) {
            this.f1412a = str;
            if (context != null) {
                this.b = context.getApplicationContext();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            if (this.b != null) {
                try {
                    ho.a(this.b, new hz.a(this.f1412a, "9.3.0", v.c).a(new String[]{"com.amap.api.maps"}).a(), "", (Map<String, String>) null);
                    interrupt();
                } catch (hm e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String a(NaviPara naviPara, Context context) {
        return String.format(Locale.US, AMAPNAVIURL, hn.b(context), Double.valueOf(naviPara.getTargetPoint().latitude), Double.valueOf(naviPara.getTargetPoint().longitude), Integer.valueOf(naviPara.getNaviStyle()));
    }

    private static String b(RoutePara routePara, Context context, int i) {
        String format = String.format(Locale.US, AMAPROUTEURL, hn.b(context), Double.valueOf(routePara.getStartPoint().latitude), Double.valueOf(routePara.getStartPoint().longitude), routePara.getStartName(), Double.valueOf(routePara.getEndPoint().latitude), Double.valueOf(routePara.getEndPoint().longitude), routePara.getEndName(), Integer.valueOf(i));
        if (i == 1) {
            return format + "&m=" + routePara.getTransitRouteStyle();
        }
        if (i != 2) {
            return format;
        }
        return format + "&m=" + routePara.getDrivingRouteStyle();
    }

    private static String a(PoiPara poiPara, Context context) {
        String format = String.format(Locale.US, AMAPPOISEARCHURL, hn.b(context), poiPara.getKeywords());
        if (poiPara.getCenter() == null) {
            return format;
        }
        return format + "&lat=" + poiPara.getCenter().latitude + "&lon=" + poiPara.getCenter().longitude;
    }

    private static boolean a(Context context) {
        return context.getPackageManager().getPackageInfo("com.autonavi.minimap", 0) != null;
    }
}
