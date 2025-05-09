package com.amap.api.col.p0003sl;

import com.amap.api.maps.model.LatLng;

/* loaded from: classes2.dex */
public final class dq {
    private static double a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d = latLng.latitude;
        double d2 = latLng3.latitude;
        return ((latLng.longitude - latLng2.longitude) * (latLng.latitude - latLng2.latitude)) - ((latLng.longitude - latLng2.longitude) * (d - d2));
    }

    private static boolean b(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d = latLng.longitude - latLng2.longitude > 0.0d ? latLng.longitude : latLng2.longitude;
        return (((latLng.longitude - latLng2.longitude) > 0.0d ? 1 : ((latLng.longitude - latLng2.longitude) == 0.0d ? 0 : -1)) < 0 ? latLng.longitude : latLng2.longitude) <= latLng3.longitude && latLng3.longitude <= d && (((latLng.latitude - latLng2.latitude) > 0.0d ? 1 : ((latLng.latitude - latLng2.latitude) == 0.0d ? 0 : -1)) < 0 ? latLng.latitude : latLng2.latitude) <= latLng3.latitude && latLng3.latitude <= (((latLng.latitude - latLng2.latitude) > 0.0d ? 1 : ((latLng.latitude - latLng2.latitude) == 0.0d ? 0 : -1)) > 0 ? latLng.latitude : latLng2.latitude);
    }

    public static boolean a(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4) {
        double a2 = a(latLng3, latLng4, latLng);
        double a3 = a(latLng3, latLng4, latLng2);
        double a4 = a(latLng, latLng2, latLng3);
        double a5 = a(latLng, latLng2, latLng4);
        if (((a2 > 0.0d && a3 < 0.0d) || (a2 < 0.0d && a3 > 0.0d)) && ((a4 > 0.0d && a5 < 0.0d) || (a4 < 0.0d && a5 > 0.0d))) {
            return true;
        }
        if (a2 == 0.0d && b(latLng3, latLng4, latLng)) {
            return true;
        }
        if (a3 == 0.0d && b(latLng3, latLng4, latLng2)) {
            return true;
        }
        if (a4 == 0.0d && b(latLng, latLng2, latLng3)) {
            return true;
        }
        return a5 == 0.0d && b(latLng, latLng2, latLng4);
    }
}
