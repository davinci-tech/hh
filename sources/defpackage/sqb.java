package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.TimeSequence;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class sqb {
    private static int a(List<TimeSequence> list, long j) {
        if (koq.b(list)) {
            LogUtil.b("Track_DataIntervalParser", "parseInterval list isEmpty", " return interval 5");
            return 5;
        }
        if (list.size() == 1) {
            return e(list, j);
        }
        int i = 0;
        if (list.size() == 2) {
            int acquireTime = (int) ((list.get(1).acquireTime() - list.get(0).acquireTime()) / 1000);
            if (acquireTime <= 0) {
                return 5;
            }
            int i2 = Math.abs(acquireTime - 5) > Math.abs(60 - acquireTime) ? 60 : 5;
            LogUtil.a("Track_DataIntervalParser", "list.size():", Integer.valueOf(list.size()), " return interval ", Integer.valueOf(i2));
            return i2;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i + 1;
            if (i5 >= list.size() || i5 >= 6) {
                break;
            }
            int acquireTime2 = (int) ((list.get(i5).acquireTime() - list.get(i).acquireTime()) / 1000);
            if (acquireTime2 > 0) {
                if (Math.abs(acquireTime2 - 5) <= Math.abs(60 - acquireTime2)) {
                    i4++;
                } else {
                    i3++;
                }
            }
            i = i5;
        }
        if (i4 >= i3) {
            LogUtil.a("Track_DataIntervalParser", "list.size():", Integer.valueOf(list.size()), " return interval 5");
            return 5;
        }
        LogUtil.a("Track_DataIntervalParser", "list.size():", Integer.valueOf(list.size()), " return interval 60");
        return 60;
    }

    private static int e(List<TimeSequence> list, long j) {
        if (koq.b(list)) {
            LogUtil.a("Track_DataIntervalParser", "List<TimeSequence> list is null");
            return 5;
        }
        int acquireTime = (int) ((list.get(0).acquireTime() - j) / 1000);
        LogUtil.a("Track_DataIntervalParser", "list.size():", Integer.valueOf(list.size()), " interval:", Integer.valueOf(acquireTime), " acquireTime:", Long.valueOf(list.get(0).acquireTime()), " startTime:", Long.valueOf(j));
        int i = Math.abs(acquireTime + (-5)) > Math.abs(60 - acquireTime) ? 60 : 5;
        LogUtil.a("Track_DataIntervalParser", "list.size():", Integer.valueOf(list.size()), " interval:", Integer.valueOf(acquireTime), " return interval ", Integer.valueOf(i));
        return i;
    }

    public static int d(List list, long j) {
        return a(list, j);
    }
}
