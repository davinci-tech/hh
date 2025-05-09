package defpackage;

import android.location.Location;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LenLatLong;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class hau {
    public static void e(LatLong latLong, LatLong latLong2) {
        Location.distanceBetween(latLong.getLatLng().b, latLong.getLatLng().d, latLong2.getLatLng().b, latLong2.getLatLng().d, new float[2]);
        latLong.setDistance(Math.abs(r0[0]));
        latLong.setAngle((r0[1] + 360.0f) % 360.0f);
    }

    public static void c(LenLatLong lenLatLong, LenLatLong lenLatLong2, ArrayList<LatLong> arrayList) {
        Location.distanceBetween(lenLatLong.getLatLng().b, lenLatLong.getLatLng().d, lenLatLong2.getLatLng().b, lenLatLong2.getLatLng().d, new float[2]);
        lenLatLong.setAngle((r0[1] + 360.0f) % 360.0f);
        double d = 0.0d;
        for (int index = lenLatLong.getIndex(); index <= lenLatLong2.getIndex(); index++) {
            if (!koq.b(arrayList, index)) {
                d += arrayList.get(index).getDistance();
            }
        }
        lenLatLong.setDistance(d);
    }

    public static double b(LatLong latLong, LatLong latLong2) {
        Location.distanceBetween(latLong.getLatLng().b, latLong.getLatLng().d, latLong2.getLatLng().b, latLong2.getLatLng().d, new float[1]);
        return Math.abs(r0[0]);
    }

    public static double e(double d, double d2, double d3, double d4) {
        Location.distanceBetween(d, d2, d3, d4, new float[1]);
        return Math.abs(r0[0]);
    }
}
