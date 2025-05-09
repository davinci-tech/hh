package defpackage;

import android.content.Context;
import android.location.Location;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class ktr {
    public static int a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            default:
                return 0;
        }
    }

    public static float bQW_(Location location, Location location2) {
        if (location == null || location2 == null) {
            return 0.0f;
        }
        return AMapUtils.calculateLineDistance(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(location2.getLatitude(), location2.getLongitude()));
    }

    public static Location bQV_(Context context, Location location) {
        if (location == null) {
            LogUtil.c("Track_LocationManagerCommonUtils", "convertLocation, location is null");
            return location;
        }
        CoordinateConverter coordinateConverter = new CoordinateConverter(context);
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        coordinateConverter.coord(new LatLng(location.getLatitude(), location.getLongitude()));
        LatLng convert = coordinateConverter.convert();
        if (convert != null) {
            location.setLatitude(convert.latitude);
            location.setLongitude(convert.longitude);
        }
        return location;
    }
}
