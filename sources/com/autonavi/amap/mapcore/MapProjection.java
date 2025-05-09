package com.autonavi.amap.mapcore;

import android.graphics.Point;

/* loaded from: classes8.dex */
public class MapProjection {
    public static void lonlat2Geo(double d, double d2, IPoint iPoint) {
        Point latLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d, 20);
        iPoint.x = latLongToPixels.x;
        iPoint.y = latLongToPixels.y;
    }

    public static void geo2LonLat(int i, int i2, DPoint dPoint) {
        DPoint pixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(i, i2, 20);
        dPoint.x = pixelsToLatLong.x;
        dPoint.y = pixelsToLatLong.y;
        pixelsToLatLong.recycle();
    }
}
