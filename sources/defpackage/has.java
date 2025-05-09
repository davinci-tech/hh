package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class has {
    public HashMap<Integer, hjg> b(List<hjg> list, ArrayList<LatLong> arrayList) {
        HashMap<Integer, hjg> hashMap = new HashMap<>();
        if (koq.b(list) || koq.b(arrayList)) {
            LogUtil.a("Track_CustomMarkerProcess", "no hiHealthMarkers or no reTrackDatas");
            return hashMap;
        }
        int i = 1;
        for (int i2 = 1; i2 < list.size() - 1; i2++) {
            hjg hjgVar = list.get(i2);
            if (hjgVar == null) {
                ReleaseLogUtil.c("Track_CustomMarkerProcess", "hiHealthMarker is null");
            } else {
                LatLong latLong = new LatLong(hjgVar.b());
                while (true) {
                    if (i < arrayList.size() - 1) {
                        int i3 = i + 1;
                        if (a(latLong, arrayList.get(i), arrayList.get(i - 1), arrayList.get(i3))) {
                            hashMap.put(Integer.valueOf(i), hjgVar);
                            break;
                        }
                        i = i3;
                    }
                }
            }
        }
        return hashMap;
    }

    private boolean a(LatLong latLong, LatLong latLong2, LatLong latLong3, LatLong latLong4) {
        double b = hau.b(latLong2, latLong);
        return b <= hau.b(latLong3, latLong) && b <= hau.b(latLong4, latLong);
    }
}
