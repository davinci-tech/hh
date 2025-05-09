package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class ojq {
    public static List<Float> a(List<HiHealthData> list) {
        List<HiHealthData> c = c(list);
        List<HiHealthData> d = d(list);
        if (d.size() == 0) {
            return b(c);
        }
        return e(d, c);
    }

    private static List<Float> e(List<HiHealthData> list, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(2);
        if (koq.b(list2) || koq.b(list)) {
            return arrayList;
        }
        int size = list.size();
        int size2 = list2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3 = i2;
            while (true) {
                if (i2 < size2) {
                    if (list2.get(i2).getStartTime() <= list.get(i).getStartTime()) {
                        i3++;
                        arrayList.add(Float.valueOf(list2.get(i2).getFloatValue()));
                        i2++;
                    } else {
                        arrayList.add(Float.valueOf(0.0f));
                        break;
                    }
                } else {
                    break;
                }
            }
            i++;
            i2 = i3;
        }
        LogUtil.a("FunctionSetHeartRateManager", "getBreakRateValueList = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<Float> b(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(2);
        if (koq.b(list)) {
            return arrayList;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Float.valueOf(list.get(i).getFloatValue()));
        }
        return arrayList;
    }

    private static List<HiHealthData> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(2);
        if (list == null || list.size() <= 50) {
            return list;
        }
        int size = list.size();
        float f = size / 50.0f;
        int i = 0;
        for (int i2 = 0; i2 < 50; i2++) {
            i = (int) (i2 * f);
            arrayList.add(list.get(i));
        }
        int i3 = size - 1;
        if (i != i3) {
            arrayList.add(list.get(i3));
        }
        LogUtil.a("FunctionSetHeartRateManager", "getSampleData = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static List<HiHealthData> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(2);
        if (list != null && list.size() >= 2) {
            int size = list.size();
            for (int i = 1; i < size; i++) {
                int i2 = i - 1;
                if (list.get(i).getStartTime() - list.get(i2).getStartTime() > 2100000) {
                    arrayList.add(list.get(i2));
                }
            }
            if (arrayList.size() != 0) {
                arrayList.add(list.get(size - 1));
            }
            LogUtil.a("FunctionSetHeartRateManager", "getIntervalTimeList , size ", Integer.valueOf(arrayList.size()));
        }
        return arrayList;
    }
}
