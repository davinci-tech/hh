package defpackage;

import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class iwf {
    public static List<HiHealthData> b(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        int size = list.size();
        int i = 0;
        while (i < size) {
            HiHealthData hiHealthData = list.get(i);
            arrayList.add(hiHealthData);
            if (i >= size - 1) {
                break;
            }
            i++;
            long startTime = list.get(i).getStartTime();
            long endTime = hiHealthData.getEndTime();
            long j = startTime - endTime;
            if (j > 180000 && j < 1800000) {
                while (endTime < startTime) {
                    HiHealthData hiHealthData2 = new HiHealthData(22003);
                    long j2 = 60000 + endTime;
                    hiHealthData2.setTimeInterval(endTime, j2);
                    arrayList.add(hiHealthData2);
                    endTime = j2;
                }
            }
        }
        return arrayList;
    }
}
