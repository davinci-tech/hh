package defpackage;

import com.huawei.hihealth.util.HiInChinaUtil;
import com.huawei.route.Point;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class btw {
    public static List<Point> d(List<Point> list) {
        Point point = list.get(0);
        if (HiInChinaUtil.c(point.getLatitude(), point.getLongitude()) != 1) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Point point2 : list) {
            hjd d = gwf.d(new hjd(point2.getLatitude(), point2.getLongitude()));
            point2.setLatitude(d.b);
            point2.setLongitude(d.d);
            arrayList.add(point2);
        }
        return arrayList;
    }
}
